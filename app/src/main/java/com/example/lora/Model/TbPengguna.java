package com.example.lora.Model;

import android.database.sqlite.SQLiteDatabase;

public class TbPengguna {

    SQLiteDatabase db;

    public TbPengguna(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertData(){
//        String query = "insert into pengguna(nama, no_hp) values('" + nama + "'," + no_hp + ");";
//        db.execSQL(query);
    }

    public void deleteData(){
//        String query = "update pengguna set no_hp = " + no_hp_baru + " where no_hp = " + no_hp_lama + ";";
//        db.execSQL(query);
    }

    public void updateData(){
//        String query = "delete from pengguna where no_hp = " + no_hp + ";";
//        db.execSQL(query);
    }


}
