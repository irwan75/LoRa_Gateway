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
        String tbPengguna = "create table pengguna(id int auto_increment unique, nama varchar(25), " +
                "no_hp bigint primary key);";
        String tbMessage = "create table message(id int auto_increment primary key not null, no_hp varchar(50)," +
                " foreign key(no_hp) references pengguna(no_hp) on delete cascade on update cascade, " +
                "message text, tanggal date, waktu time, rule enum('sender','receiver'));";
        db.execSQL(tbPengguna);
        db.execSQL(tbMessage);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
