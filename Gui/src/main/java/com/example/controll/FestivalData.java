package com.example.controll;

public class FestivalData {
    private String ten;
    private String ngayBatDau;
    private String viTri;
    private String nhanVatLienQuan;
    private String lanDauToChuc;
    private String ghiChu;

    public FestivalData(String ten, String ngayBatDau, String viTri, String nhanVatLienQuan, String lanDauToChuc, String ghiChu) {
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.viTri = viTri;
        this.nhanVatLienQuan = nhanVatLienQuan;
        this.lanDauToChuc = lanDauToChuc;
        this.ghiChu = ghiChu;
    }

    public FestivalData() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getNhanVatLienQuan() {
        return nhanVatLienQuan;
    }

    public void setNhanVatLienQuan(String nhanVatLienQuan) {
        this.nhanVatLienQuan = nhanVatLienQuan;
    }

    public String getLanDauToChuc() {
        return lanDauToChuc;
    }

    public void setLanDauToChuc(String lanDauToChuc) {
        this.lanDauToChuc = lanDauToChuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
