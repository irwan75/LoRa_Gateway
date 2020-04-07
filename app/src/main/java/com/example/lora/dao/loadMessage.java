package com.example.lora.dao;

public class loadMessage {

    private String tglwaktu, message;

    public loadMessage(String tglwaktu, String message) {
        this.tglwaktu = tglwaktu;
        this.message = message;
    }

    public String getTglwaktu() {
        return tglwaktu;
    }

    public void setTglwaktu(String tglwaktu) {
        this.tglwaktu = tglwaktu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
