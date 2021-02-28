package com.verdite.samples.reactivesearch.ui.main

import androidx.lifecycle.ViewModel
import com.verdite.samples.reactivesearch.data.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stockRepository: StockRepository
): ViewModel() {
}