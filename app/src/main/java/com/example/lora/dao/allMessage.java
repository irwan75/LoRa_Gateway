package com.example.lora.dao;

public class allMessage {

    String nama_nomor;
    String pesan;
    String waktu;

    public allMessage(String nama_nomor, String pesan, String waktu) {
        this.nama_nomor = nama_nomor;
        this.pesan = pesan;
        this.waktu = waktu;
    }


    public String getNama_nomor() {
        return nama_nomor;
    }

    public void setNama_nomor(String nama_nomor) {
        this.nama_nomor = nama_nomor;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
