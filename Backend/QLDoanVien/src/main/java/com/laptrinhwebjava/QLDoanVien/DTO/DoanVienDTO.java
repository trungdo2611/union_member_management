package com.laptrinhwebjava.QLDoanVien.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoanVienDTO {
    Long id;
    String maDoanVien;
    String tenDoanVien;
    Date ngaySinh;
    Boolean gioiTinh;
    String danToc;
    String email;
    String dienThoai;
    Date ngayVaoDoan;
    String password;
    String hinhAnh;
    Long chiDoan_id;
    Long chucVu_id;
    Long phanQuyen_id;
}
