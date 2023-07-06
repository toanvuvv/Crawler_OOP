package com.example.controll;

public class EventData {
    private String nhanVat;
    private String batDau;
    private String ketThuc;
    private String suKien;

    public EventData(String nhanVat, String batDau, String ketThuc, String suKien){
        this.nhanVat = nhanVat;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.suKien = suKien;
    }
    public EventData(){
    }
    public String getNhanVat() {
        return nhanVat;
    }

    public void setNhanVat(String nhanVat) {
        this.nhanVat = nhanVat;
    }

    public String getBatDau() {
        return batDau;
    }

    public void setBatDau(String batDau) {
        this.batDau = batDau;
    }

    public String getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(String ketThuc) {
        this.ketThuc = ketThuc;
    }

    public String getSuKien() {
        return suKien;
    }

    public void setSuKien(String suKien) {
        this.suKien = suKien;
    }
}
