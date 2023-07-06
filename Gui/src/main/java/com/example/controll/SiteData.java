package com.example.controll;

public class SiteData {
    private String viTri;
    private String diTich;
    private String ghiChu;
    private String nhanVatLienQuan;
    private String namCN;

    public SiteData(String viTri, String diTich, String ghiChu, String nhanVatLienQuan, String namCN) {
        this.viTri = viTri;
        this.diTich = diTich;
        this.ghiChu = ghiChu;
        this.nhanVatLienQuan = nhanVatLienQuan;
        this.namCN = namCN;
    }

    public SiteData() {
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getDiTich() {
        return diTich;
    }

    public void setDiTich(String diTich) {
        this.diTich = diTich;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNhanVatLienQuan() {
        return nhanVatLienQuan;
    }

    public void setNhanVatLienQuan(String nhanVatLienQuan) {
        this.nhanVatLienQuan = nhanVatLienQuan;
    }

    public String getNamCN() {
        return namCN;
    }

    public void setNamCN(String namCN) {
        this.namCN = namCN;
    }
}
