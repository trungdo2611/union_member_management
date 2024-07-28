package com.laptrinhwebjava.QLDoanVien.responsitory;

import com.laptrinhwebjava.QLDoanVien.DTO.*;
import com.laptrinhwebjava.QLDoanVien.model.DoanVien;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DoanVienRepo extends JpaRepository<DoanVien,Long> {
    @Query("SELECT MAX(c.id) FROM DoanVien c")
    Optional<Long> findMaxId();
    default Optional<Long> findTop() {
        return findMaxId();
    }

    @Modifying
    @Query(value = "INSERT INTO doan_vien (ma_doan_vien, ten_doan_vien, ngay_sinh, gioi_tinh, dan_toc, email, dien_thoai, ngay_vao_doan, password, hinh_anh, chi_doan_id, chuc_vu_id, phan_quyen_id) " +
            "VALUES (:ma_doan_vien, :ten_doan_vien, :ngay_sinh, :gioi_tinh, :dan_toc, :email, :dien_thoai, :ngay_vao_doan, :password, :hinh_anh, :chi_doan_id, :chuc_vu_id, :phan_quyen_id)", nativeQuery = true)
    void createDoanVien(@Param("ma_doan_vien") String maDoanVien,
                               @Param("ten_doan_vien") String tenDoanVien,
                               @Param("ngay_sinh") Date ngaySinh,
                               @Param("gioi_tinh") Boolean gioiTinh,
                               @Param("dan_toc") String danToc,
                               @Param("email") String email,
                               @Param("dien_thoai") String dienThoai,
                               @Param("ngay_vao_doan") Date ngayVaoDoan,
                               @Param("password") String password,
                               @Param("hinh_anh") String hinhAnh,
                               @Param("chi_doan_id") Long chiDoan_id,
                               @Param("chuc_vu_id") Long chucVu_id,
                               @Param("phan_quyen_id") Long phanQuyen_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE doan_vien dv SET dv.ma_doan_vien = :ma_doan_vien, dv.ten_doan_vien = :ten_doan_vien, dv.ngay_sinh = :ngay_sinh, dv.gioi_tinh = :gioi_tinh, dv.dan_toc = :dan_toc, dv.dien_thoai = :dien_thoai, dv.ngay_vao_doan = :ngay_vao_doan, dv.password = :password, dv.chi_doan_id = :chi_doan_id, dv.chuc_vu_id = :chuc_vu_id, dv.phan_quyen_id = :phan_quyen_id WHERE dv.id = :id", nativeQuery = true)
    void updateDoanVien(@Param("ma_doan_vien") String maDoanVien,
                        @Param("ten_doan_vien") String tenDoanVien,
                        @Param("ngay_sinh") Date ngaySinh,
                        @Param("gioi_tinh") Boolean gioiTinh,
                        @Param("dan_toc") String danToc,
                        @Param("dien_thoai") String dienThoai,
                        @Param("ngay_vao_doan") Date ngayVaoDoan,
                        @Param("password") String password,
                        @Param("chi_doan_id") Long chiDoan_id,
                        @Param("chuc_vu_id") Long chucVu_id,
                        @Param("phan_quyen_id") Long phanQuyen_id,
                        @Param("id") Long id);

    @Query(value = "SELECT dv.id FROM DoanVien dv WHERE dv.email = :email")
    Long findDoanVienIdByEmail(@Param("email") String email);
    @Transactional
    @Modifying
    @Query(value = "UPDATE doan_vien dv SET dv.password = :password WHERE dv.id = :id", nativeQuery = true)
    void updatePassword(
                        @Param("password") String password,
                        @Param("id") Long id);

    @Query(value = "SELECT dv.id as id, dv.ma_doan_vien as maDoanVien, dv.ten_doan_vien as tenDoanVien, dv.ngay_sinh as ngaySinh, dv.gioi_tinh as gioiTinh, dv.dan_toc as danToc, dv.email as email, dv.dien_thoai as dienThoai, dv.ngay_vao_doan as ngayVaoDoan, dv.password as password, dv.hinh_anh as hinhAnh,dv.chuc_vu_id as chucVu_id, dv.chi_doan_id as chiDoan_id, dv.phan_quyen_id as phanQuyen_id, k.ten_khoa as tenKhoa, cd.ten_chi_doan as tenChiDoan, cd.khoa_id as khoa_id, cv.ten_chuc_vu as tenChucVu, pq.ten_quyen as tenQuyen FROM doan_vien dv JOIN chi_doan cd ON dv.chi_doan_id = cd.id JOIN khoa k ON cd.khoa_id = k.id JOIN chuc_vu cv ON dv.chuc_vu_id = cv.id JOIN phan_quyen pq ON dv.phan_quyen_id = pq.id WHERE dv.id = :id", nativeQuery = true)
    DoanVienDetailDTO findDoanVienById(@Param("id") Long id);

   @Query(value = "SELECT dv.id as id, dv.ma_doan_vien as maDoanVien, dv.ten_doan_vien as tenDoanVien, dv.ngay_sinh as ngaySinh, dv.gioi_tinh as gioiTinh, dv.dan_toc as danToc, dv.email as email, dv.dien_thoai as dienThoai, dv.ngay_vao_doan as ngayVaoDoan, dv.password as password,dv.hinh_anh as hinhAnh, dv.chuc_vu_id as chucVu_id, dv.chi_doan_id as chiDoan_id, dv.phan_quyen_id as phanQuyen_id, k.ten_khoa as tenKhoa, cd.ten_chi_doan as tenChiDoan, cv.ten_chuc_vu as tenChucVu, pq.ten_quyen as tenQuyen FROM doan_vien dv JOIN chi_doan cd ON dv.chi_doan_id = cd.id JOIN khoa k ON cd.khoa_id = k.id JOIN chuc_vu cv ON dv.chuc_vu_id = cv.id JOIN phan_quyen pq ON dv.phan_quyen_id = pq.id ORDER BY dv.id DESC", nativeQuery = true)
   List<DoanVienDetailDTO> ListDetailDV();

    @Query(value = "SELECT dv.id as id, dv.ma_doan_vien as maDoanVien, dv.ten_doan_vien as tenDoanVien, dv.ngay_sinh as ngaySinh, dv.gioi_tinh as gioiTinh, dv.dan_toc as danToc, dv.email as email, dv.dien_thoai as dienThoai, dv.ngay_vao_doan as ngayVaoDoan, dv.password as password,dv.hinh_anh as hinhAnh, dv.chuc_vu_id as chucVu_id, dv.chi_doan_id as chiDoan_id, dv.phan_quyen_id as phanQuyen_id, k.ten_khoa as tenKhoa, cd.ten_chi_doan as tenChiDoan, cv.ten_chuc_vu as tenChucVu, pq.ten_quyen as tenQuyen FROM doan_vien dv JOIN chi_doan cd ON dv.chi_doan_id = cd.id JOIN khoa k ON cd.khoa_id = k.id JOIN chuc_vu cv ON dv.chuc_vu_id = cv.id JOIN phan_quyen pq ON dv.phan_quyen_id = pq.id WHERE dv.ma_doan_vien LIKE %:keyword% OR dv.ten_doan_vien LIKE %:keyword% OR k.ten_khoa LIKE %:keyword% OR cd.ten_chi_doan LIKE %:keyword% ORDER BY dv.id DESC", nativeQuery = true)
    List<DoanVienDetailDTO> findDoanVienByKeyword(@Param("keyword") String keyword);

    //page list doan vien
    @Query(value = "SELECT dv.id as id, dv.ma_doan_vien as maDoanVien, dv.ten_doan_vien as tenDoanVien, dv.ngay_sinh as ngaySinh, dv.gioi_tinh as gioiTinh, dv.dan_toc as danToc, dv.email as email, dv.dien_thoai as dienThoai, dv.ngay_vao_doan as ngayVaoDoan, dv.password as password,dv.hinh_anh as hinhAnh, dv.chuc_vu_id as chucVu_id, dv.chi_doan_id as chiDoan_id, dv.phan_quyen_id as phanQuyen_id, k.ten_khoa as tenKhoa, cd.ten_chi_doan as tenChiDoan, cv.ten_chuc_vu as tenChucVu, pq.ten_quyen as tenQuyen FROM doan_vien dv JOIN chi_doan cd ON dv.chi_doan_id = cd.id JOIN khoa k ON cd.khoa_id = k.id JOIN chuc_vu cv ON dv.chuc_vu_id = cv.id JOIN phan_quyen pq ON dv.phan_quyen_id = pq.id ORDER BY dv.id DESC", nativeQuery = true)
    Page<DoanVienDetailDTO> ListPageDetailDV(Pageable pageable);

    @Query(value = "SELECT dv.id as id, dv.ma_doan_vien as maDoanVien, dv.ten_doan_vien as tenDoanVien, dv.ngay_sinh as ngaySinh, dv.gioi_tinh as gioiTinh, dv.dan_toc as danToc, dv.email as email, dv.dien_thoai as dienThoai, dv.ngay_vao_doan as ngayVaoDoan, dv.password as password,dv.hinh_anh as hinhAnh, dv.chuc_vu_id as chucVu_id, dv.chi_doan_id as chiDoan_id, dv.phan_quyen_id as phanQuyen_id, k.ten_khoa as tenKhoa, cd.ten_chi_doan as tenChiDoan, cv.ten_chuc_vu as tenChucVu, pq.ten_quyen as tenQuyen FROM doan_vien dv JOIN chi_doan cd ON dv.chi_doan_id = cd.id JOIN khoa k ON cd.khoa_id = k.id JOIN chuc_vu cv ON dv.chuc_vu_id = cv.id JOIN phan_quyen pq ON dv.phan_quyen_id = pq.id WHERE dv.ma_doan_vien LIKE %:keyword% OR dv.ten_doan_vien LIKE %:keyword% OR k.ten_khoa LIKE %:keyword% OR cd.ten_chi_doan LIKE %:keyword% ORDER BY dv.id DESC", nativeQuery = true)
    Page<DoanVienDetailDTO> findPageDoanVienByKeyword(@Param("keyword") String keyword, Pageable pageable);


    @Query(value = "SELECT dv.id, dv.ten_doan_vien as tenDoanVien FROM doan_vien dv  WHERE dv.chi_doan_id = :chi_doan_id", nativeQuery = true)
    List<DoanVienSelectDTO> findDoanVienByIdChiDoan(@Param("chi_doan_id") Long chiDoan_id);

    @Query(value = "SELECT dv.id, dv.ten_doan_vien as tenDoanVien FROM doan_vien dv", nativeQuery = true)
    List<DoanVienSelectDTO> findDoanVien();
    //security
    DoanVien findByEmail(String email);
    boolean existsByMaDoanVien(String maDoanVien);
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(dv.id) FROM DoanVien dv WHERE dv.email = :email")
    int countByEmail(@Param("email") String email);

    default boolean checkDuplicateEmail(String email) {
        int count = countByEmail(email);
        return count < 2;
    }

    @Query(value = "SELECT k.id, k.ten_khoa as tenKhoa, COUNT(dv.id) AS totalDoanVien " +
            "FROM doan_vien dv " +
            "JOIN chi_doan cd ON dv.chi_doan_id = cd.id " +
            "JOIN khoa k ON cd.khoa_id = k.id " +
            "GROUP BY k.ten_khoa", nativeQuery = true)
    List<Object[]> countDoanVienByKhoaNative();

    default List<KhoaDoanVienDTO> countDoanVienByKhoa() {
        List<Object[]> results = countDoanVienByKhoaNative();
        List<KhoaDoanVienDTO> dtos = new ArrayList<>();
        for (Object[] result : results) {
            KhoaDoanVienDTO dto = new KhoaDoanVienDTO();
            dto.setId((Long) result[0]);
            dto.setTenKhoa((String) result[1]);
            dto.setTotalDoanVien((Long) result[2]);
            dtos.add(dto);
        }
        return dtos;
    }

    @Query(value = "SELECT COUNT(dv.id) FROM doan_vien dv", nativeQuery = true)
    long countTotalDoanVien();

    @Query(value = "SELECT COUNT(*) FROM doan_vien dv WHERE dv.chi_doan_id = :chiDoanId AND dv.chuc_vu_id = :chucVu_id", nativeQuery = true)
    int countBiThuInChiDoan(@Param("chiDoanId") Long chiDoanId,
                            @Param("chucVu_id") Long chucVu_id);

    @Query(value = "SELECT COUNT(*) FROM doan_vien dv WHERE dv.id = :id AND dv.chuc_vu_id = 1", nativeQuery = true)
    int isBiThu(@Param("id") Long id);
}
