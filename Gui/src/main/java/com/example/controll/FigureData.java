package com.example.controll;

public class FigureData {
    private String ten;
    private String trieuDai;
    private String mat;
    private String sinh;
    private String noiMat;
    private String noiSinh;
    private String url;

    public FigureData(String ten, String trieuDai, String mat, String sinh, String noiMat, String noiSinh, String url) {
        this.ten = ten;
        this.trieuDai = trieuDai;
        this.mat = mat;
        this.sinh = sinh;
        this.noiMat = noiMat;
        this.noiSinh = noiSinh;
        this.url = url;
    }

    public FigureData() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTrieuDai() {
        return trieuDai;
    }

    public void setTrieuDai(String trieuDai) {
        this.trieuDai = trieuDai;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getSinh() {
        return sinh;
    }

    public void setSinh(String sinh) {
        this.sinh = sinh;
    }

    public String getNoiMat() {
        return noiMat;
    }

    public void setNoiMat(String noiMat) {
        this.noiMat = noiMat;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
