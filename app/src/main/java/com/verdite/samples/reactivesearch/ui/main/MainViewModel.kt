package com.verdite.samples.reactivesearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.verdite.samples.reactivesearch.core.structure.BaseViewModel
import com.verdite.samples.reactivesearch.data.model.Stock
import com.verdite.samples.reactivesearch.data.repository.StockRepository
import com.verdite.samples.reactivesearch.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : BaseViewModel() {

    private val _stocks = MutableLiveData<List<Stock>>()
    val stocks: LiveData<List<Stock>> get() = _stocks

    private val _state = SingleLiveEvent<State>()
    val state: SingleLiveEvent<State> get() = _state

    private val cachedStocks: MutableList<Stock> = mutableListOf()

    init {
        getStocks()
    }

    private fun getStocks() {
        viewModelScope.launch {
            _state.value = State.NORMAL
            val stocks = stockRepository.getStocks()
            cachedStocks.addAll(stocks)
            _stocks.postValue(stocks)
        }
    }

    fun performSearch(term: String) {
        coroutineDebounce {
            _stocks.postValue(stockRepository.performSearch(term))
        }
    }

    fun revert() {
        cancelDebounce()
        if (cachedStocks.isEmpty()) {
            getStocks()
        } else {
            _stocks.postValue(cachedStocks)
        }
    }

    enum class State {
        NORMAL,
        SEARCH,
        SEARCH_NO_RESULTS
    }
}