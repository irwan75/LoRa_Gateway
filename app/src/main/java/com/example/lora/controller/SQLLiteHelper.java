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
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbPengguna = "CREATE TABLE pengguna(nama VARCHAR(50), no_hp BIGINT PRIMARY KEY);";
        String tbMessage = "create TABLE message(id INTEGER PRIMARY KEY, no_hp BIGINT, pesan TEXT, tanggal DATE, " +
                "waktu TIME, rule VARCHAR(10), FOREIGN KEY(no_hp) REFERENCES pengguna(no_hp) " +
                "ON DELETE CASCADE ON UPDATE CASCADE);";
        db.execSQL(tbPengguna);
        db.execSQL(tbMessage);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+dbName);
        onCreate(db);
    }
}
