package com.example.tamara_munoz_semana6.provider

import android.net.Uri

object InfraccionContract {
    const val AUTHORITY = "com.example.tamara_munoz_semana6.provider.infraccionprovider"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/infracciones")

    const val TABLE_NAME = "infracciones"
}