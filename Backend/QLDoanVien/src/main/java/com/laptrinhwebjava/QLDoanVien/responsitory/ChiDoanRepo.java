package com.laptrinhwebjava.QLDoanVien.responsitory;

import com.laptrinhwebjava.QLDoanVien.DTO.ChiDoanDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.ChiDoanDetailDTO;
import com.laptrinhwebjava.QLDoanVien.model.ChiDoan;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

public interface ChiDoanRepo extends JpaRepository<ChiDoan,Long> {
    List<ChiDoan> findByKhoaId(Long khoaId);

    @Query("SELECT MAX(c.id) FROM ChiDoan c")
    Optional<Long> findMaxId();
    default Optional<Long> findTop() {
        return findMaxId();
    }


    @Query(value = "SELECT c.id, c.ma_chi_doan as maChiDoan, c.ten_chi_doan as tenChiDoan, c.dia_chi as diaChi, c.dien_thoai as dienThoai, k.id as khoa_id, k.ten_khoa as tenKhoa FROM chi_doan c JOIN khoa k ON c.khoa_id = k.id ORDER BY c.id DESC",nativeQuery = true)
    Page<ChiDoanDetailDTO> listChiDoan(Pageable pageable);
    @Query(value = "SELECT c.id, c.ma_chi_doan as maChiDoan, c.ten_chi_doan as tenChiDoan, c.dia_chi as diaChi, c.dien_thoai as dienThoai, k.id as khoa_id, k.ten_khoa as tenKhoa FROM chi_doan c JOIN khoa k ON c.khoa_id = k.id WHERE c.ma_chi_doan LIKE %:search% OR c.ten_chi_doan LIKE %:search% OR k.ten_khoa LIKE %:search% ORDER BY c.id DESC", nativeQuery = true)
    Page<ChiDoanDetailDTO> searchChiDoan(@Param(value = "search") String search, Pageable pageable);
    @Modifying
    @Query(value = "INSERT INTO chi_doan (ma_chi_doan, ten_chi_doan, dia_chi, dien_thoai, khoa_id) " +
            "VALUES (:ma_chi_doan, :ten_chi_doan, :dia_chi, :dien_thoai, :khoa_id)",nativeQuery = true)
    void createChiDoan(@Param("ma_chi_doan") String maChiDoan,
                       @Param("ten_chi_doan") String tenChiDoan,
                       @Param("dia_chi") String diaChi,
                       @Param("dien_thoai") String dienThoai,
                       @Param("khoa_id") Long khoa_id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE chi_doan c SET c.ma_chi_doan = :ma_chi_doan, c.ten_chi_doan = :ten_chi_doan, c.dia_chi = :dia_chi, c.dien_thoai = :dien_thoai, c.khoa_id = :khoa_id WHERE c.id = :id", nativeQuery = true)
    void updateChiDoan(@Param("ma_chi_doan") String maChiDoan,
                       @Param("ten_chi_doan") String tenChiDoan,
                       @Param("dia_chi") String diaChi,
                       @Param("dien_thoai") String dienThoai,
                       @Param("khoa_id") Long khoa_id,
                       @Param("id") Long id);


    @Query(value = "SELECT c.id, c.ma_chi_doan as maChiDoan, c.ten_chi_doan as tenChiDoan, c.dia_chi as diaChi, c.dien_thoai as dienThoai, k.id as khoa_id, k.ten_khoa as tenKhoa FROM chi_doan c JOIN khoa k ON c.khoa_id = k.id WHERE c.id = :id", nativeQuery = true)
    ChiDoanDetailDTO findChiDoanById(@Param("id") Long id);

    @Query(value = "SELECT c.id, c.ma_chi_doan as maChiDoan, c.ten_chi_doan as tenChiDoan, c.dia_chi as diaChi, c.dien_thoai as dienThoai, c.khoa_id as khoa_id, k.ten_khoa as tenKhoa FROM chi_doan c JOIN khoa k ON c.khoa_id = k.id WHERE c.khoa_id = :khoa_id", nativeQuery = true)
    List<ChiDoanDetailDTO> findChiDoanByIdKhoa(@Param("khoa_id") Long khoa_id);

    @Query(value = "SELECT c.id, c.ma_chi_doan as maChiDoan, c.ten_chi_doan as tenChiDoan, c.dia_chi as diaChi, c.dien_thoai as dienThoai, c.khoa_id as khoa_id, k.ten_khoa as tenKhoa FROM chi_doan c JOIN khoa k ON c.khoa_id = k.id", nativeQuery = true)
    List<ChiDoanDetailDTO> findChiDoan();
}
