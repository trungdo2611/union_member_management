package com.laptrinhwebjava.QLDoanVien.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PhanQuyen")
public class PhanQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenQuyen")
    private String tenQuyen;


    @OneToMany(mappedBy = "phanQuyen")
    private List<DoanVien> doanVien;

    public PhanQuyen() {
    }

    public PhanQuyen(Long id, String tenQuyen, List<DoanVien> doanVien) {
        this.id = id;
        this.tenQuyen = tenQuyen;
        this.doanVien = doanVien;
    }

    public Long getId() {
        return id;
    }


    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonManagedReference(value = "doanvien-phanquyen")
    public List<DoanVien> getDoanVien() {
        return doanVien;
    }

    public void setDoanVien(List<DoanVien> doanVien) {
        this.doanVien = doanVien;
    }
}
