package com.noyize.deepsky.app.util

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun <reified T> parseListFromAssets(context: Context, filename: String): List<T> {
    val myJson = context.assets.open(filename).bufferedReader().use { it.readText() }
    val json = Json {
        ignoreUnknownKeys = true
    }
    return json.decodeFromString(myJson)
}