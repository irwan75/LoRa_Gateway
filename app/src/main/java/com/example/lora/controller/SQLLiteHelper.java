package com.example.lora.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLLiteHelper extends SQLiteOpenHelper {

    private static final String dbName = "chatting";
    private static final int dbVersion = 1;

    public SQLLiteHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbPengguna = "create table pengguna(id int not null auto_increment unique, nama varchar(50), " +
                "no_hp bigint primary key);";
        String tbMessage = "create table message(id int auto_increment primary key not null, no_hp bigint," +
                " foreign key(no_hp) references pengguna(no_hp) on delete cascade on update cascade, " +
                "pesan text, tanggal date, waktu time, rule enum('sender','receiver'));";
        String viewLastMessage = "create view lastMessage as select distinct pengguna.nama, pengguna.no_hp, message.pesan," +
                "message.tanggal, message.waktu from pengguna, message where pengguna.no_hp=message.no_hp;";
        db.execSQL(tbPengguna);
        db.execSQL(tbMessage);
        db.execSQL(viewLastMessage);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
