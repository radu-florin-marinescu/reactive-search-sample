package com.verdite.samples.reactivesearch.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.verdite.samples.reactivesearch.data.dao.StockDao
import com.verdite.samples.reactivesearch.data.model.Stock

/**
 * Created by RaduMarinescu.
 */

@Database(entities = [Stock::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun stockDao(): StockDao
}