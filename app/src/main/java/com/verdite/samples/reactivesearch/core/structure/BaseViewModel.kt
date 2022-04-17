package com.verdite.samples.reactivesearch.core.structure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    private var debounceJob: Job? = null

    internal fun coroutineDebounce(
        duration: Long = DEBOUNCE_TIME,
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> Unit
    ) {
        debounceJob?.cancel()
        debounceJob = viewModelScope.launch(context) {
            delay(duration)
            block.invoke()
        }
    }

    internal fun cancelDebounce() {
        debounceJob?.cancel()
    }

    companion object {
        private const val DEBOUNCE_TIME = 350L
    }
}