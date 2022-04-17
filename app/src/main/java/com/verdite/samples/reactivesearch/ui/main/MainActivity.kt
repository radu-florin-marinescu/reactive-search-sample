package com.verdite.samples.reactivesearch.ui.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.verdite.samples.reactivesearch.R
import com.verdite.samples.reactivesearch.core.structure.BaseActivity
import com.verdite.samples.reactivesearch.databinding.ActivityMainBinding
import com.verdite.samples.reactivesearch.utils.SimpleTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var stockAdapter: StockAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerViewAnimator: DefaultItemAnimator

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewAnimator = DefaultItemAnimator()
        setupUI()
        setupControls()
        setupObservables()
    }

    private fun setupUI() = with(binding) {
        setTransparentStatusBar()
        recyclerView.apply {
            adapter = stockAdapter
            layoutManager = linearLayoutManager
            itemAnimator = recyclerViewAnimator
        }
    }

    private fun setupControls() = with(binding) {
        searchModule.input.addTextChangedListener(
            object : SimpleTextWatcher() {
                override fun onTextChanged(text: String) {
                    if (text.isEmpty()) {
                        viewModel.revert()
                        return
                    }

                    viewModel.performSearch(text)
                }

            })

        searchModule.input.setOnEditorActionListener { _, action, _ ->
            if (action == EditorInfo.IME_ACTION_DONE) {
                searchModule.input.clearFocus()
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setupObservables() = with(viewModel) {
        stocks.observe(this@MainActivity) { stocks ->
            stocks?.let {
                stockAdapter.submitList(it) {
                    binding.recyclerView.smoothScrollToPosition(0)
                }
            }
        }
    }

}