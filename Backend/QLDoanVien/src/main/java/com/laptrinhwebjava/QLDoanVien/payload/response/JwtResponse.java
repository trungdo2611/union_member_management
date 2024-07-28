package com.laptrinhwebjava.QLDoanVien.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String maDoanVien;
    private String tenDoanVien;
    private Date ngaySinh;
    private Boolean gioiTinh;
    private String danToc;
    private String email;
    private String dienThoai;
    private Date ngayVaoDoan;
    private String password;
    private String hinhAnh;

    public JwtResponse(String token, Long id, String maDoanVien, String tenDoanVien, Date ngaySinh, Boolean gioiTinh, String danToc, String email, String dienThoai, Date ngayVaoDoan, String password, String hinhAnh, Long chucVu_id, Long phanQuyen_id, String tenChiDoan, String tenChucVu, String tenPhanQuyen, String tenKhoa, List<String> roles) {
        this.token = token;
        this.id = id;
        this.maDoanVien = maDoanVien;
        this.tenDoanVien = tenDoanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.danToc = danToc;
        this.email = email;
        this.dienThoai = dienThoai;
        this.ngayVaoDoan = ngayVaoDoan;
        this.password = password;
        this.hinhAnh = hinhAnh;
        this.chucVu_id = chucVu_id;
        this.phanQuyen_id = phanQuyen_id;
        this.tenChiDoan = tenChiDoan;
        this.tenChucVu = tenChucVu;
        this.tenPhanQuyen = tenPhanQuyen;
        this.tenKhoa = tenKhoa;
        this.roles = roles;
    }

    private Long chucVu_id;
    private Long phanQuyen_id;
    private String tenChiDoan;
    private String tenChucVu;
    private String tenPhanQuyen;
    private String tenKhoa;
    private List<String> roles;
}
