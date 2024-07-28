package com.laptrinhwebjava.QLDoanVien.responsitory;

import com.laptrinhwebjava.QLDoanVien.DTO.DoanPhiDetailDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.DoanPhiSummaryDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.KhoaDoanVienDTO;
import com.laptrinhwebjava.QLDoanVien.model.doanPhi;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DoanPhiRepo extends JpaRepository<doanPhi,Long> {
    @Modifying
    @Query(value = "INSERT INTO doan_phi (ten_phi, ngay_nop, so_tien, doan_vien_id) " +
            "VALUES (:ten_phi, :ngay_nop, :so_tien, :doan_vien_id)", nativeQuery = true)
    void createDoanPhi(@Param("ten_phi") String tenPhi,
                       @Param("ngay_nop") Date ngayNop,
                       @Param("so_tien") int soTien,
                       @Param("doan_vien_id") Long doanVien_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE doan_phi SET ten_phi = :ten_phi, ngay_nop = :ngay_nop, so_tien = :so_tien WHERE id = :id", nativeQuery = true)
    void updateDoanPhiByDoanVienId(@Param("ten_phi") String tenPhi,
                                   @Param("ngay_nop") Date ngayNop,
                                   @Param("so_tien") int soTien,
                                   @Param("id") Long id);


    @Query(value = "SELECT dp.id as id, dp.doan_vien_id as doanVien_id, dp.ten_phi as tenPhi, dp.ngay_nop as ngayNop, dp.so_tien as soTien,dv.ten_doan_vien as tenDoanVien, dv.ma_doan_vien as maDoanVien FROM doan_vien dv JOIN doan_phi dp ON dv.id = dp.doan_vien_id ORDER BY dp.id DESC", nativeQuery = true)
    Page<DoanPhiDetailDTO> getListDoanPhi(Pageable pageable);

    @Query(value = "SELECT dp.id as id, dp.doan_vien_id as doanVien_id, dv.ten_doan_vien as tenDoanVien, dp.ten_phi as tenPhi, dp.ngay_nop as ngayNop, dp.so_tien as soTien,dv.ten_doan_vien as tenDoanVien, dv.ma_doan_vien as maDoanVien FROM doan_vien dv JOIN doan_phi dp ON dv.id = dp.doan_vien_id WHERE dp.ten_phi LIKE %:keyword% OR dv.ten_doan_vien LIKE %:keyword% OR dv.ma_doan_vien LIKE %:keyword% ORDER BY dp.id DESC", nativeQuery = true)
    Page<DoanPhiDetailDTO> getListDoanPhiByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT dp.id as id, dp.doan_vien_id as doanVien_id, dv.ten_doan_vien as tenDoanVien, dp.ten_phi as tenPhi, dp.ngay_nop as ngayNop, dp.so_tien as soTien,dv.ten_doan_vien as tenDoanVien, dv.ma_doan_vien as maDoanVien FROM doan_vien dv JOIN doan_phi dp ON dv.id = dp.doan_vien_id WHERE dp.id = :id", nativeQuery = true)
    DoanPhiDetailDTO getDoanPhiDetail(@Param("id") Long id);

    @Query(value = "SELECT dv.id as id, dv.ten_doan_vien as tenDoanVien, SUM(dp.so_tien) as tongSoTien, " +
            "cd.ten_chi_doan as tenChiDoan, k.ten_khoa as tenKhoa " +
            "FROM doan_phi dp " +
            "JOIN doan_vien dv ON dp.doan_vien_id = dv.id " +
            "JOIN chi_doan cd ON dv.chi_doan_id = cd.id " +
            "JOIN khoa k ON cd.khoa_id = k.id " +
            "GROUP BY dp.doan_vien_id, dv.ten_doan_vien, cd.ten_chi_doan, k.ten_khoa", nativeQuery = true)
    List<Object[]> listDoanPhiSummaryNative();

    default List<DoanPhiSummaryDTO> listDoanPhiSummary() {
        List<Object[]> results = listDoanPhiSummaryNative();
        List<DoanPhiSummaryDTO> dtos = new ArrayList<>();
        for (Object[] result : results) {
            DoanPhiSummaryDTO dto = new DoanPhiSummaryDTO();
            dto.setId((Long) result[0]);
            dto.setTenDoanVien((String) result[1]);
            dto.setTongSoTien((BigDecimal) result[2]);
            dto.setTenChiDoan((String) result[3]);
            dto.setTenKhoa((String) result[4]);
            dtos.add(dto);
        }
        return dtos;
    }

    @Query(value = "SELECT COUNT(DISTINCT dp.doan_vien_id) FROM doan_phi dp", nativeQuery = true)
    long countTotalDoanVienInDoanPhi();

    @Query(value = "SELECT SUM(dp.so_tien) FROM doan_phi dp", nativeQuery = true)
    BigDecimal sumTotalMoneyInDoanPhi();
}
