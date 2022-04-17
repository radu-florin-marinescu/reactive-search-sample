package com.verdite.samples.reactivesearch.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.verdite.samples.reactivesearch.data.dao.StockDao
import com.verdite.samples.reactivesearch.data.model.Stock

/**
 * Created by RaduMarinescu.
 */

@Database(entities = [Stock::class], version = 1, exportSchema = false)
@TypeConverters(Stock.DBConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun stockDao(): StockDao
}