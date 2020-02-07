package com.example.appsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "DBLogin.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbUsuario(email text primary key, senha text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tbUsuario");
    }
    //inserindo valores no banco de dados

    public boolean insert(String email, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("senha", senha);

        long inserido = db.insert("tbUsuario", null, contentValues);

        if (inserido == 1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean validarEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from tbUsuario where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }

    }
}
