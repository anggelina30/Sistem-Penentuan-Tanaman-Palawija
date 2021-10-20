package com.apllication.aplikasipendeteksitanah;

public class HistoryList {

    String hari, bulan, tahun, suhu, kekeruhan, ph;


    public HistoryList() {
    }

    public HistoryList(String hari, String bulan, String tahun, String suhu, String kekeruhan, String ph) {
        this.hari = hari;
        this.bulan = bulan;
        this.tahun = tahun;
        this.suhu = suhu;
        this.kekeruhan = kekeruhan;
        this.ph = ph;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getKekeruhan() {
        return kekeruhan;
    }

    public void setKekeruhan(String kekeruhan) {
        this.kekeruhan = kekeruhan;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }
}
