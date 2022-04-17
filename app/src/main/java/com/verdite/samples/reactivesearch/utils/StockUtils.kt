package com.verdite.samples.reactivesearch.utils

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.verdite.samples.reactivesearch.data.model.Stock
import com.verdite.samples.reactivesearch.data.model.StockResponse
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

object StockUtils {
    fun Context.parseJSONStockData(): List<StockResponse> =
        try {
            val gson = GsonBuilder().serializeNulls().create()
            val inputStream: InputStream = this.assets.open("stocks.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val listType: Type = object : TypeToken<List<StockResponse>?>() {}.type
            gson.fromJson(jsonString, listType) ?: emptyList()
        } catch (e: IOException) {
            Log.e("JSONParse", "Cannot read local JSON - ${e.message}}")
            emptyList()
        }
}