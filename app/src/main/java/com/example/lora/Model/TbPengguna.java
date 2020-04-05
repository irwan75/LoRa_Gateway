package com.example.lora.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TbPengguna {

    SQLiteDatabase db;

    public TbPengguna(SQLiteDatabase db) {
        this.db = db;
    }

    public String insertData(String nama, String no_hp, String pesan){
        String query1 = "INSERT OR REPLACE INTO pengguna(nama, no_hp) VALUES('" + nama + "'," + no_hp + ");";
        String query2 = "INSERT OR REPLACE INTO message(no_hp, pesan, tanggal, waktu, rule) VALUES(" + no_hp + ",'" + pesan + "', date('now') , time('now') , 'sender');";
        db.execSQL(query1);
        db.execSQL(query2);
        return "...Sukess Terkirim...";
    }

    public void deleteData(){
//        String query = "update pengguna set no_hp = " + no_hp_baru + " where no_hp = " + no_hp_lama + ";";
//        db.execSQL(query);
    }

    public void updateData(){
//        String query = "delete from pengguna where no_hp = " + no_hp + ";";
//        db.execSQL(query);
    }

//    public ArrayList<String> select() {
//        ArrayList<String> noname = new ArrayList<>();
//
//        String query = "select *from pengguna";
//        Cursor cursor = db.rawQuery(query, null);;
//
//        while (cursor.moveToNext()) {
//            String nama = cursor.getString(1);
////            String nomor = cursor.getString(2);
//            noname.add(nama);
//        }
//
//        cursor.close();
//
//        return noname;
//    }

}
