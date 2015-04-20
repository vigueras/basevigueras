package com.example.guadalupe.basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    // Creamos el constructor
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Se crea la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table producto (id_producto integer primary key unique,nombre_producto text, Tipo_producto text, descripcion_producto text, precio text, cantidad_existente text, color text) ");
    }

    // borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists producto");
        db.execSQL("create table producto (id_producto integer primary key unique,  nombre_producto text, Tipo_producto text, descripcion_producto text, precio text, cantidad_existente text, color text) ");
    }
}
