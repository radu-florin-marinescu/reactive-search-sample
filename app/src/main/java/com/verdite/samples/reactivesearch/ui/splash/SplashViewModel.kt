package com.verdite.samples.reactivesearch.ui.splash

import androidx.lifecycle.viewModelScope
import com.verdite.samples.reactivesearch.core.structure.BaseViewModel
import com.verdite.samples.reactivesearch.data.model.StockResponse
import com.verdite.samples.reactivesearch.data.repository.StockRepository
import com.verdite.samples.reactivesearch.data.repository.StockRepository.Companion.parseRawStockData
import com.verdite.samples.reactivesearch.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : BaseViewModel(){

    private val _stockWritten: SingleLiveEvent<Unit> = SingleLiveEvent()
    val stockWritten: SingleLiveEvent<Unit> get() = _stockWritten

    fun initStocks(stocks: List<StockResponse>) {
        viewModelScope.launch(Dispatchers.IO) {
            stockRepository.replaceStocks(stocks.parseRawStockData())
            delay(4000)
            _stockWritten.postValue(Unit)
        }
    }
}