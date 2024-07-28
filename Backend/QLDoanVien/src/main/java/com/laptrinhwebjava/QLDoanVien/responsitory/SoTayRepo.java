package com.laptrinhwebjava.QLDoanVien.responsitory;

import com.laptrinhwebjava.QLDoanVien.DTO.SoTayDetailDTO;
import com.laptrinhwebjava.QLDoanVien.DTO.SoTayDoanVienDTO;
import com.laptrinhwebjava.QLDoanVien.model.SoTayDV;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoTayRepo extends JpaRepository<SoTayDV, Long> {

    @Modifying
    @Query(value = "INSERT INTO so_taydv (noi_dung, doan_vien_id) " +
            "VALUES (:noi_dung, :doan_vien_id)", nativeQuery = true)
    void createSoTayDoanVien(@Param("noi_dung") String noiDung,
                             @Param("doan_vien_id") Long doanVien_id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE so_taydv s SET s.noi_dung = :noi_dung WHERE s.id = :id", nativeQuery = true)
    void updateSoTayDoanVien(@Param("noi_dung") String noiDung,
                             @Param("id") Long id);

    @Query(value = "SELECT st.id as id ,st.noi_dung as noiDung FROM so_taydv st where st.doan_vien_id = :doan_vien_id", nativeQuery = true)
    List<SoTayDetailDTO> getSoTayDoanVien(@Param("doan_vien_id") Long doanVien_id);

}
