package com.verdite.samples.reactivesearch.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.verdite.samples.reactivesearch.core.dao.StockDao

/**
 * Created by RaduMarinescu.
 */

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun stockDao(): StockDao
}