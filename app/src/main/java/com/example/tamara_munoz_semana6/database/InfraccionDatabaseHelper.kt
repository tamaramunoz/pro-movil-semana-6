package com.example.tamara_munoz_semana6.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class InfraccionDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "infracciones_pelotillehue.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "infracciones"

        const val COLUMN_FOLIO = "folio"
        const val COLUMN_RUT = "rut_inspector"
        const val COLUMN_LOCAL = "nombre_local"
        const val COLUMN_DIRECCION = "direccion"
        const val COLUMN_INFRACCION = "infraccion"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_FOLIO INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_RUT TEXT NOT NULL, "
                + "$COLUMN_LOCAL TEXT NOT NULL, "
                + "$COLUMN_DIRECCION TEXT NOT NULL, "
                + "$COLUMN_INFRACCION TEXT NOT NULL)")
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}