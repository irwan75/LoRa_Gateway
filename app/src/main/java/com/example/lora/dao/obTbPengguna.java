package com.example.lora.dao;

public class obTbPengguna {

    private int id;
    private String nama;
    private String no_hp;

    public obTbPengguna(){

    }

    public obTbPengguna(int id, String nama, String no_hp) {
        this.id = id;
        this.nama = nama;
        this.no_hp = no_hp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
}
