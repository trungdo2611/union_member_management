package com.laptrinhwebjava.QLDoanVien.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "SoTayDV")
public class SoTayDV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "noiDung", columnDefinition = "TEXT")
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "doanVien_id", nullable = false)
    private DoanVien doanVien;

    public SoTayDV() {

    }
    public SoTayDV(Long id, String noiDung, DoanVien doanVien) {
        this.id = id;
        this.noiDung = noiDung;
        this.doanVien = doanVien;
    }

    public Long getId() {
        return id;
    }


    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @JsonBackReference(value = "doanvien-sotay")
    public DoanVien getDoanVien() {
        return doanVien;
    }

    public void setDoanVien(DoanVien doanVien) {
        this.doanVien = doanVien;
    }
}
