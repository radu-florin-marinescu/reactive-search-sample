package com.verdite.samples.reactivesearch.utils

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.verdite.samples.reactivesearch.data.model.Stock
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockUtils @Inject constructor() {
    fun Context.parseJSONStockData(): List<Stock> =
        try {
            val gson = GsonBuilder().serializeNulls().create()
            val inputStream: InputStream = this.assets.open("stocks.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val listType: Type = object : TypeToken<List<Stock>?>() {}.type
            gson.fromJson(jsonString, listType) ?: emptyList()
        } catch (e: IOException) {
            Log.e("JSONParse", "Cannot read local JSON - ${e.message}}")
            emptyList()
        }
}