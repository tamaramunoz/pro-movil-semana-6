package com.example.tamara_munoz_semana6.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.tamara_munoz_semana6.database.InfraccionDatabaseHelper

class InfraccionProvider : ContentProvider() {

    private lateinit var dbHelper: InfraccionDatabaseHelper

    companion object {
        private const val INFRACCIONES = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(InfraccionContract.AUTHORITY, "infracciones", INFRACCIONES)
        }
    }

    override fun onCreate(): Boolean {
        dbHelper = InfraccionDatabaseHelper(context!!)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val db = dbHelper.readableDatabase
        val cursor = when (uriMatcher.match(uri)) {
            INFRACCIONES -> db.query(InfraccionDatabaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
            else -> throw IllegalArgumentException("URI desconocida: $uri")
        }
        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbHelper.writableDatabase
        val id = when (uriMatcher.match(uri)) {
            INFRACCIONES -> db.insert(InfraccionDatabaseHelper.TABLE_NAME, null, values)
            else -> throw IllegalArgumentException("URI desconocida: $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return Uri.withAppendedPath(InfraccionContract.CONTENT_URI, id.toString())
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbHelper.writableDatabase
        val count = when (uriMatcher.match(uri)) {
            INFRACCIONES -> db.update(InfraccionDatabaseHelper.TABLE_NAME, values, selection, selectionArgs)
            else -> throw IllegalArgumentException("URI desconocida: $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbHelper.writableDatabase
        val count = when (uriMatcher.match(uri)) {
            INFRACCIONES -> db.delete(InfraccionDatabaseHelper.TABLE_NAME, selection, selectionArgs)
            else -> throw IllegalArgumentException("URI desconocida: $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            INFRACCIONES -> "vnd.android.cursor.dir/vnd.${InfraccionContract.AUTHORITY}.infracciones"
            else -> throw IllegalArgumentException("URI desconocida: $uri")
        }
    }
}