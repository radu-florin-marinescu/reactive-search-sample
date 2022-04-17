package com.verdite.samples.reactivesearch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "stocks")
data class Stock(
    var ticker: String,
    var name: String,
    var logo: String,
    var price: Double,
    var change: Double,
    var changePercentage: Double,
    var changeType: StockChangeType
) {
    @PrimaryKey
    var id: Long? = null

    internal class DBConverter {
        @TypeConverter
        fun to(value: String) = enumValueOf<StockChangeType>(value)

        @TypeConverter
        fun from(value: StockChangeType) = value.name
    }
}