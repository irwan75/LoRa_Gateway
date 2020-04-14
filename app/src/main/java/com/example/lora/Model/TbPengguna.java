package com.example.lora.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lora.dao.*;
import java.util.ArrayList;

public class TbPengguna {

    SQLiteDatabase db;

    public TbPengguna(SQLiteDatabase db) {
        this.db = db;
    }

    public String insertData(String nama, String nomor, String pesan){
        String query1 = "INSERT INTO pengguna VALUES ('"+ nama +"',"+nomor+")";
        String query2 = "INSERT INTO message(no_hp, pesan, tanggal, waktu, rule) VALUES ("+nomor+",'"+pesan+"'," +
                "DATE('now'), TIME('now'), 'sender')";
        String query3 = "INSERT INTO message(no_hp, pesan, tanggal, waktu, rule) VALUES ("+nomor+",'"+pesan+"'," +
                "DATE('now'), TIME('now'), 'receiver')";
        String query = "select pesan, tanggal, waktu from message where no_hp = "+nomor+"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToNext()){
        }else {
            db.execSQL(query1);
        }
        db.execSQL(query2);
        db.execSQL(query3);
        return "Sukses Terinput";
    }

    public String insertDataTanpaNama(String nomor, String pesan){
        String query2 = "INSERT INTO message(no_hp, pesan, tanggal, waktu, rule) VALUES ("+nomor+",'"+pesan+"'," +
                "DATE('now'), TIME('now'), 'sender')";
        db.execSQL(query2);
        return "Sukses Terinput";
    }

    public void deleteData(){
//        String query = "update pengguna set no_hp = " + no_hp_baru + " where no_hp = " + no_hp_lama + ";";
//        db.execSQL(query);
    }

    public void updateData(){
//        String query = "delete from pengguna where no_hp = " + no_hp + ";";
//        db.execSQL(query);
    }

    public ArrayList<allMessage> selectLastMessage(){
        ArrayList<allMessage> listMessage = new ArrayList<>();

        String query = "select pengguna.nama, message.no_hp, message.pesan, message.tanggal\n" +
                "from pengguna, message\n" +
                "where message.id = (SELECT max(id) FROM message WHERE pengguna.no_hp = message.no_hp);";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            do {
                allMessage allPesan = new allMessage(
                        ""+cursor.getString(cursor.getColumnIndex("pengguna.nama")),
                        ""+cursor.getString(cursor.getColumnIndex("message.no_hp")),
                        ""+cursor.getString(cursor.getColumnIndex("message.pesan")),
                        ""+cursor.getString(cursor.getColumnIndex("message.tanggal"))
                );
                listMessage.add(allPesan);
            }while (cursor.moveToNext());
        }

        return listMessage;
    }

    public ArrayList<loadMessage> select(String kondisi){
        ArrayList<loadMessage> loadPesan = new ArrayList<>();

        String query = "select pesan, tanggal, waktu, rule from message where no_hp = "+kondisi+"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext()){
            do {
                loadMessage lMessage = new loadMessage(
                        ""+cursor.getString(cursor.getColumnIndex("tanggal"))+
                                " "+cursor.getString(cursor.getColumnIndex("waktu")),
                        ""+cursor.getString(cursor.getColumnIndex("pesan")),
                        ""+cursor.getString(cursor.getColumnIndex("rule"))
                );
                loadPesan.add(lMessage);
            }while (cursor.moveToNext());
        }
        return loadPesan;
    }

}
