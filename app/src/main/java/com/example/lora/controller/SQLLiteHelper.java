package com.example.lora.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLLiteHelper extends SQLiteOpenHelper {

    private static final String dbName = "loraProject";
    private static final int dbVersion = 3;

    public SQLLiteHelper(Context context) {
        super(context, dbName, null, dbVersion);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pengguna(nama VARCHAR(50), no_hp bigint PRIMARY KEY)");
        db.execSQL("CREATE TABLE message(id INT PRIMARY KEY NOT NULL, no_hp BIGINT, pesan TEXT, " +
                "tanggal DATE, waktu TIME, rule VARCHAR(10), FOREIGN KEY(no_hp) REFERENCES pengguna(no_hp)" +
                " ON DELETE CASCADE ON UPDATE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
