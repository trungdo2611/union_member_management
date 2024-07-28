package com.laptrinhwebjava.QLDoanVien.responsitory;

import com.laptrinhwebjava.QLDoanVien.DTO.KhoaDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.KhoaDetailDTO;
import com.laptrinhwebjava.QLDoanVien.model.Khoa;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface KhoaRepo extends JpaRepository<Khoa,Long> {

    @Query(value = "SELECT k.id, k.ten_khoa as tenKhoa, k.ma_khoa as maKhoa, k.dien_thoai as dienThoai, k.email FROM khoa k ORDER BY k.id DESC",nativeQuery = true)
    Page<KhoaDetailDTO> getAllKhoa(Pageable pageable);
    @Query(value = "SELECT k.id, k.ten_khoa as tenKhoa, k.ma_khoa as maKhoa, k.dien_thoai as dienThoai, k.email FROM Khoa k WHERE k.ten_khoa LIKE %:keyword% OR k.ma_khoa LIKE %:keyword% ORDER BY k.id DESC", nativeQuery = true)
    Page<KhoaDetailDTO> searchKhoa(@Param(value = "keyword") String keyword,Pageable pageable);

    @Query(value = "SELECT k.id as id, k.ten_khoa as tenKhoa FROM khoa k",nativeQuery = true)
    List<KhoaDTO> getKhoaSelect();

    @Query(value = "SELECT k.id, k.ten_khoa as tenKhoa, k.ma_khoa as maKhoa, k.dien_thoai as dienThoai, k.email FROM khoa k WHERE id = :id",nativeQuery = true)
    KhoaDetailDTO findKhoaById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE khoa k SET k.ten_khoa = :ten_khoa, k.ma_khoa = :ma_khoa, k.email = :email, k.dien_thoai = :dien_thoai WHERE k.id = :id",nativeQuery = true)
    void updateKhoa(@Param("ten_khoa") String tenKhoa,
                    @Param("ma_khoa") String maKhoa,
                    @Param("email") String email,
                    @Param("dien_thoai") String dienThoai,
                    @Param("id") Long id);

}
