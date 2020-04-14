package com.example.lora.dao;

public class loadMessage {

    private String tglwaktu, message, rule;

    public loadMessage(String tglwaktu, String message, String rule) {
        this.tglwaktu = tglwaktu;
        this.message = message;
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
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
