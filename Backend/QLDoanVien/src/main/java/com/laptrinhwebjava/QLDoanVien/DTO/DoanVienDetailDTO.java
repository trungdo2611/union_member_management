package com.laptrinhwebjava.QLDoanVien.DTO;

import java.util.Date;

public interface DoanVienDetailDTO {
    Long getId();
    String getMaDoanVien();
    String getTenDoanVien();
    Date getNgaySinh();
    boolean isGioiTinh();
    String getDanToc();
    String getEmail();
    String getDienThoai();
    Date getNgayVaoDoan();
    String getPassword();
    String getHinhAnh();
    Long getChiDoan_id();
    Long getChucVu_id();
    Long getPhanQuyen_id();
    String getTenKhoa();
    String getTenChucVu();
    String getTenQuyen();
    String getTenChiDoan();
    Long getKhoa_id();
}
