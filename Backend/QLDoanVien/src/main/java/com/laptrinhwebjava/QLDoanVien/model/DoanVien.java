package com.laptrinhwebjava.QLDoanVien.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DoanVien")
public class DoanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maDoanVien", nullable = false, length = 100)
    private String maDoanVien;

    @Column(name = "tenDoanVien")
    private String tenDoanVien;

    @Column(name = "hinhAnh")
    private String hinhAnh;

    @Column(name = "ngaySinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "danToc")
    private String danToc;

    @Column(name = "email")
    private String email;

    @Column(name = "dienThoai", length = 10)
    private String dienThoai;

    @Column(name = "ngayVaoDoan")
    @Temporal(TemporalType.DATE)
    private Date ngayVaoDoan;

    @Column(name = "password")
    private String password;

   @ManyToOne
   @JoinColumn(name = "chiDoan_id",nullable = false)
    private ChiDoan chiDoan;

    @OneToMany(mappedBy = "doanVien",cascade=CascadeType.ALL)
    private List<doanPhi> doanPhis = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "chucVu_id",nullable = false)
    private ChucVu chucVu;
    @OneToMany(mappedBy = "doanVien", cascade = CascadeType.ALL)
    private List<SoTayDV> soTayDV;

    @ManyToOne
    @JoinColumn(name = "phanQuyen_id", nullable = false)
    private PhanQuyen phanQuyen;

    public DoanVien() {
    }

    public DoanVien(Long id, String maDoanVien, String tenDoanVien, String hinhAnh, Date ngaySinh, boolean gioiTinh, String danToc, String email, String dienThoai, Date ngayVaoDoan, String password, ChiDoan chiDoan, List<doanPhi> doanPhis, ChucVu chucVu, List<SoTayDV> soTayDV, PhanQuyen phanQuyen) {
        this.id = id;
        this.maDoanVien = maDoanVien;
        this.tenDoanVien = tenDoanVien;
        this.hinhAnh = hinhAnh;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.danToc = danToc;
        this.email = email;
        this.dienThoai = dienThoai;
        this.ngayVaoDoan = ngayVaoDoan;
        this.password = password;
        this.chiDoan = chiDoan;
        this.doanPhis = doanPhis;
        this.chucVu = chucVu;
        this.soTayDV = soTayDV;
        this.phanQuyen = phanQuyen;
    }



    public Long getId() {
        return id;
    }

    public String getMaDoanVien() {
        return maDoanVien;
    }

    public void setMaDoanVien(String maDoanVien) {
        this.maDoanVien = maDoanVien;
    }

    public String getTenDoanVien() {
        return tenDoanVien;
    }

    public void setTenDoanVien(String tenDoanVien) {
        this.tenDoanVien = tenDoanVien;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public Date getNgayVaoDoan() {
        return ngayVaoDoan;
    }

    public void setNgayVaoDoan(Date ngayVaoDoan) {
        this.ngayVaoDoan = ngayVaoDoan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @JsonBackReference(value = "chidoan-doanvien")
    public ChiDoan getChiDoan() {
        return chiDoan;
    }

    public void setChiDoan(ChiDoan chiDoan) {
        this.chiDoan = chiDoan;
    }


    public List<doanPhi> getDoanPhis() {
        return doanPhis;
    }

    public void setDoanPhis(List<doanPhi> doanPhis) {
        this.doanPhis = doanPhis;
    }


    @JsonBackReference(value = "doanvien-chucvu")
    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    @JsonManagedReference(value = "doanvien-sotay")
    public List<SoTayDV> getSoTayDV() {
        return soTayDV;
    }

    public void setSoTayDV(List<SoTayDV> soTayDV) {
        this.soTayDV = soTayDV;
    }

    @JsonBackReference(value = "doanvien-phanquyen")
    public PhanQuyen getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(PhanQuyen phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
