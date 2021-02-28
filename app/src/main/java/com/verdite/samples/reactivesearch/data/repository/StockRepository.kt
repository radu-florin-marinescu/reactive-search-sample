package com.verdite.samples.reactivesearch.data.repository

import com.verdite.samples.reactivesearch.data.dao.StockDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(
    private val stockDao: StockDao
) {
}