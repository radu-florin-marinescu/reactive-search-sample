package com.verdite.samples.reactivesearch.data.repository

import com.verdite.samples.reactivesearch.data.dao.StockDao
import com.verdite.samples.reactivesearch.data.model.Stock
import com.verdite.samples.reactivesearch.data.model.StockChangeType
import com.verdite.samples.reactivesearch.data.model.StockResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class StockRepository @Inject constructor(
    private val stockDao: StockDao
) {

    suspend fun replaceStocks(data: List<Stock>) = withContext(Dispatchers.IO) {
        stockDao.replace(data)
    }

    suspend fun getStocks(): List<Stock> = withContext(Dispatchers.IO) {
        stockDao.get()
    }

    suspend fun performSearch(term: String) = withContext(Dispatchers.IO) {
        stockDao.search(term)
    }

    companion object {
        suspend fun List<StockResponse>.parseRawStockData() = withContext(Dispatchers.Default) {
            this@parseRawStockData.map { rawStock ->
                val generatedPrice = generateRandomPrice()
                val generatedChangePercentage = generateRandomChangePercentage()
                val generatedChangeType = generateRandomDeliveryMethod()
                Stock(
                    ticker = rawStock.ticker,
                    name = rawStock.name,
                    logo = rawStock.image,
                    price = generatedPrice,
                    change = when (generatedChangeType) {
                        StockChangeType.GAIN -> {
                            (generatedPrice * (100 + generatedChangePercentage) / 100) - generatedPrice
                        }
                        StockChangeType.LOSS -> {
                            generatedPrice - (generatedPrice * (100 - generatedChangePercentage) / 100)
                        }
                    }.round(),
                    changePercentage = generatedChangePercentage,
                    changeType = generatedChangeType
                )
            }
        }

        private fun generateRandomPrice() = Random.nextDouble(50.0, 1000.0).round()
        private fun generateRandomChangePercentage() = Random.nextDouble(0.0, 40.0).round()
        private fun generateRandomDeliveryMethod() = StockChangeType.values().random()
        private fun Double.round(): Double = "%.${2}f".format(this).toDouble()
    }
}