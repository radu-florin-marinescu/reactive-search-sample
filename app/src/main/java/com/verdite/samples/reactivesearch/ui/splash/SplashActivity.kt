package com.verdite.samples.reactivesearch.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.verdite.samples.reactivesearch.R
import com.verdite.samples.reactivesearch.core.structure.BaseActivity
import com.verdite.samples.reactivesearch.databinding.ActivitySplashBinding
import com.verdite.samples.reactivesearch.ui.main.MainActivity
import com.verdite.samples.reactivesearch.utils.Functions.start
import com.verdite.samples.reactivesearch.utils.StockUtils
import com.verdite.samples.reactivesearch.utils.StockUtils.parseJSONStockData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        onSetupUI()
        onSetupData()
        onObserve()
    }

    private fun onSetupUI() {
        setTransparentStatusBar()
    }

    private fun onSetupData() {
        this.parseJSONStockData().let { stockResponse ->
            viewModel.initStocks(stockResponse)
        }
    }

    private fun onObserve(){
        viewModel.stockWritten.observe(this) {
            start<MainActivity>()
            finish()
        }
    }
}