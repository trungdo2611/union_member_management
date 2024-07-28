package com.laptrinhwebjava.QLDoanVien.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ChiDoan")
public class ChiDoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maChiDoan", nullable = false, length = 100)
    private String maChiDoan;

    @Column(name = "tenChiDoan")
    private String tenChiDoan;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "dienThoai", length = 10)
    private String dienThoai;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "khoa_id",nullable = false)
    private Khoa khoa;


    @OneToMany(mappedBy = "chiDoan",cascade = CascadeType.ALL)
    private List<DoanVien> doanViens = new ArrayList<>();

    public ChiDoan() {
    }

    public ChiDoan(Long id, String maChiDoan, String tenChiDoan, String diaChi, String dienThoai, Khoa khoa, List<DoanVien> doanViens) {
        this.id = id;
        this.maChiDoan = maChiDoan;
        this.tenChiDoan = tenChiDoan;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.khoa = khoa;
        this.doanViens = doanViens;
    }


    public Long getId() {
        return id;
    }

    public String getMaChiDoan() {
        return maChiDoan;
    }

    public void setMaChiDoan(String maChiDoan) {
        this.maChiDoan = maChiDoan;
    }

    public String getTenChiDoan() {
        return tenChiDoan;
    }

    public void setTenChiDoan(String tenChiDoan) {
        this.tenChiDoan = tenChiDoan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    @JsonBackReference(value = "khoa-chidoan")
    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }


    @JsonManagedReference(value = "chidoan-doanvien")
    public List<DoanVien> getDoanViens() {
        return doanViens;
    }

    public void setDoanViens(List<DoanVien> doanViens) {
        this.doanViens = doanViens;
    }
}
