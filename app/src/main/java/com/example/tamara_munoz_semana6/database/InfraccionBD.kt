package com.example.tamara_munoz_semana6.database

import android.content.ContentValues
import android.content.Context
import com.example.tamara_munoz_semana6.entidades.Infraccion

class InfraccionBD(context: Context) {
    private val dbHelper = InfraccionDatabaseHelper(context)

    fun insertarInfraccion(rut: String, local: String, direccion: String, infraccion: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(InfraccionDatabaseHelper.COLUMN_RUT, rut)
            put(InfraccionDatabaseHelper.COLUMN_LOCAL, local)
            put(InfraccionDatabaseHelper.COLUMN_DIRECCION, direccion)
            put(InfraccionDatabaseHelper.COLUMN_INFRACCION, infraccion)
        }
        return db.insert(InfraccionDatabaseHelper.TABLE_NAME, null, values)
    }

    fun actualizarInfraccion(folio: Int, rut: String, local: String, direccion: String, infraccion: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(InfraccionDatabaseHelper.COLUMN_RUT, rut)
            put(InfraccionDatabaseHelper.COLUMN_LOCAL, local)
            put(InfraccionDatabaseHelper.COLUMN_DIRECCION, direccion)
            put(InfraccionDatabaseHelper.COLUMN_INFRACCION, infraccion)
        }
        return db.update(
            InfraccionDatabaseHelper.TABLE_NAME,
            values,
            "${InfraccionDatabaseHelper.COLUMN_FOLIO} = ?",
            arrayOf(folio.toString())
        )
    }

    fun obtenerInfracciones(): ArrayList<Infraccion> {
        val lista = ArrayList<Infraccion>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${InfraccionDatabaseHelper.TABLE_NAME}", null)

        if (cursor.moveToFirst()) {
            do {
                val folio = cursor.getInt(cursor.getColumnIndexOrThrow(InfraccionDatabaseHelper.COLUMN_FOLIO))
                val rut = cursor.getString(cursor.getColumnIndexOrThrow(InfraccionDatabaseHelper.COLUMN_RUT))
                val local = cursor.getString(cursor.getColumnIndexOrThrow(InfraccionDatabaseHelper.COLUMN_LOCAL))
                val direccion = cursor.getString(cursor.getColumnIndexOrThrow(InfraccionDatabaseHelper.COLUMN_DIRECCION))
                val infraccion = cursor.getString(cursor.getColumnIndexOrThrow(InfraccionDatabaseHelper.COLUMN_INFRACCION))

                lista.add(Infraccion(folio, rut, local, direccion, infraccion))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }
}