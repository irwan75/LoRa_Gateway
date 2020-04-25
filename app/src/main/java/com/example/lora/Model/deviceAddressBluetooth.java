package com.example.lora.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class deviceAddressBluetooth {

    SQLiteDatabase db;

    public deviceAddressBluetooth(SQLiteDatabase db) {
        this.db = db;
    }

    public void updateData(String alamat) {
        String query = "UPDATE deviceAddress SET address = '" + alamat + "' WHERE kode = 75;";
        db.execSQL(query);
    }

    public String select(){
        String query = "SELECT *FROM deviceAddress WHERE kode = 75;";
        Cursor cursor = db.rawQuery(query, null);
        String alamat = null;
        if (cursor.moveToNext()){
            alamat = cursor.getString(1);
        }
        return alamat;
    }
}
