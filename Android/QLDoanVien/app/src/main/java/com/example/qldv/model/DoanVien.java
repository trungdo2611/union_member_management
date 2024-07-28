package com.example.qldv.model;

public class DoanVien {

    String id,maDv,tenDv,dob,genDer,danToc,mail,sdt,ngayvaodoan,imagepath,chucVu,chiDoan,Khoa;

    public DoanVien(String id, String maDv, String tenDv, String dob, String genDer, String danToc, String mail, String sdt, String ngayvaodoan, String imagepath, String chucVu, String chiDoan, String khoa) {
        this.id = id;
        this.maDv = maDv;
        this.tenDv = tenDv;
        this.dob = dob;
        this.genDer = genDer;
        this.danToc = danToc;
        this.mail = mail;
        this.sdt = sdt;
        this.ngayvaodoan = ngayvaodoan;
        this.imagepath = imagepath;
        this.chucVu = chucVu;
        this.chiDoan = chiDoan;
        this.Khoa = khoa;
    }

    public DoanVien() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDv() {
        return maDv;
    }

    public void setMaDv(String maDv) {
        this.maDv = maDv;
    }

    public String getTenDv() {
        return tenDv;
    }

    public void setTenDv(String tenDv) {
        this.tenDv = tenDv;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGenDer() {
        return genDer;
    }

    public void setGenDer(String genDer) {
        this.genDer = genDer;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgayvaodoan() {
        return ngayvaodoan;
    }

    public void setNgayvaodoan(String ngayvaodoan) {
        this.ngayvaodoan = ngayvaodoan;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getChiDoan() {
        return chiDoan;
    }

    public void setChiDoan(String chiDoan) {
        this.chiDoan = chiDoan;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String khoa) {
        Khoa = khoa;
    }
}
