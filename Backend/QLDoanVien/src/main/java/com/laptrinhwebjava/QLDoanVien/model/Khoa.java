package com.laptrinhwebjava.QLDoanVien.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Khoa")

public class Khoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maKhoa",nullable = false, length = 100)
    private String maKhoa;

    @Column(name = "tenKhoa")
    private String tenKhoa;
    @Column(name = "dienThoai", length = 10)
    private String dienThoai;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<ChiDoan> chiDoans;
    public Khoa() {
    }

    public Khoa(Long id, String maKhoa, String tenKhoa, String dienThoai, String email, List<ChiDoan> chiDoans) {
        this.id = id;
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.dienThoai = dienThoai;
        this.email = email;
        this.chiDoans = chiDoans;
    }

    public Long getId() {
        return id;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ChiDoan> getChiDoans() {
        return chiDoans;
    }

    public void setChiDoans(List<ChiDoan> chiDoans) {
        this.chiDoans = chiDoans;
    }
}
