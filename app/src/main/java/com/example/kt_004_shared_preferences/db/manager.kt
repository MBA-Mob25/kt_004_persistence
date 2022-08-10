package com.example.kt_004_shared_preferences.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context: Context, name: String?) : SQLiteOpenHelper(context, name, null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
            "CREATE TABLE TREATMENT(" +
                    "ID INT NOT NULL," +
                    "NAME VARCHAR(45) NOT NULL," +
                    "TREATMENT VARCHAR(45) NOT NULL," +
                    "PRIMARY KEY (ID)" +
                    ")"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS TREATMENT")
        p0?.execSQL(
            "CREATE TABLE TREATMENT(" +
                    "ID INT NOT NULL," +
                    "NAME VARCHAR(45) NOT NULL," +
                    "TREATMENT VARCHAR(45) NOT NULL," +
                    "PRIMARY KEY (ID)" +
                    ")"
        )
    }

    fun save(id: Int, name: String, treatment: String) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("ID", id)
        cv.put("NAME", name)
        cv.put("TREATMENT", treatment)

        db.insert("TREATMENT", "ID", cv)
    }

    fun select(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT NAME, TREATMENT FROM TREATMENT", null)
    }

    fun delete() {
        val db = this.writableDatabase
        db.delete("TREATMENT", "ID=1", null)
    }
}