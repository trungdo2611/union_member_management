package com.laptrinhwebjava.QLDoanVien.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DoanPhi")
public class doanPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenPhi")
    private String tenPhi;

    @Column(name = "ngayNop")
    @Temporal(TemporalType.DATE)
    private Date ngayNop;

    @Column(name = "soTien")
    private int soTien;

    @ManyToOne
    @JoinColumn(name = "doanVien_id")
    private DoanVien doanVien;

    public doanPhi() {
    }

    public doanPhi(Long id, String tenPhi, Date ngayNop, int soTien, DoanVien doanVien) {
        this.id = id;
        this.tenPhi = tenPhi;
        this.ngayNop = ngayNop;
        this.soTien = soTien;
        this.doanVien = doanVien;
    }

    public Long getId() {
        return id;
    }

    public String getTenPhi() {
        return tenPhi;
    }

    public void setTenPhi(String tenPhi) {
        this.tenPhi = tenPhi;
    }

    public Date getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(Date ngayNop) {
        this.ngayNop = ngayNop;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }


    public DoanVien getDoanVien() {
        return doanVien;
    }

    public void setDoanVien(DoanVien doanVien) {
        this.doanVien = doanVien;
    }
}
