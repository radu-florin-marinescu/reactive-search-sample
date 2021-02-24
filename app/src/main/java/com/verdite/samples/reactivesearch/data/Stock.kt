package com.verdite.samples.reactivesearch.data

data class Stock(
    val id: Long,
    val ticker: String,
    val name: String,
    val logo: String,
    val price: Double,
    val change: Double,
    val changePercentage: Double
)