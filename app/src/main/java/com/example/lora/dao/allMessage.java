package com.example.lora.dao;

public class allMessage {

    String nama;
    String nomor;
    String pesan;
    String tanggal;

    public allMessage(String nama, String nomor, String pesan, String tanggal) {
        this.nama = nama;
        this.nomor = nomor;
        this.pesan = pesan;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
