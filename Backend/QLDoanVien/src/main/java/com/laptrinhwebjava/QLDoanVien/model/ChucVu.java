package com.laptrinhwebjava.QLDoanVien.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ChucVu")
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maChucVu", nullable = false, length = 100)
    private String maChucVu;

    @Column(name = "tenChucVu")
    private String tenChucVu;


    @OneToMany(mappedBy = "chucVu",cascade = CascadeType.ALL)
    private List<DoanVien> doanVien;

    public ChucVu() {
    }

    public ChucVu(Long id, String maChucVu, String tenChucVu, List<DoanVien> doanVien) {
        this.id = id;
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.doanVien = doanVien;
    }

    public Long getId() {
        return id;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    @JsonManagedReference(value = "doanvien-chucvu")
    public List<DoanVien> getDoanVien() {
        return doanVien;
    }

    public void setDoanVien(List<DoanVien> doanVien) {
        this.doanVien = doanVien;
    }
}
