package com.verdite.samples.reactivesearch.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class Stock(
    var ticker: String,
    var name: String,
    var logo: String,
    var price: Double,
    var change: Double,
    var changePercentage: Double
) {
    @PrimaryKey
    var id: Long? = null
}