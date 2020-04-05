package com.example.lora.dao;

public class etMessage {

    String nama, nomor;

    public etMessage(String nama, String nomor) {
        this.nama = nama;
        this.nomor = nomor;
    }

    public etMessage(){

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
}
