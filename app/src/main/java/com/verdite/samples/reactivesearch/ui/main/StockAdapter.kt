package com.verdite.samples.reactivesearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.verdite.samples.reactivesearch.R
import com.verdite.samples.reactivesearch.data.model.Stock
import com.verdite.samples.reactivesearch.databinding.ItemStockBinding
import javax.inject.Inject

class StockAdapter @Inject constructor() :
    ListAdapter<Stock, StockAdapter.StockHolder>(StockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StockHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_stock, parent, false
            )
        )

    override fun onBindViewHolder(viewHolder: StockHolder, position: Int) =
        viewHolder.bind(getItem(position))

    inner class StockHolder(
        private val binding: ItemStockBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stock: Stock) = with(binding) {
            data = stock
            cardView.cardRadius =
                root.context.resources.getDimensionPixelSize(R.dimen._20sdp).toFloat()
            executePendingBindings()
        }
    }

    private class StockDiffCallback : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(oldItem: Stock, newItem: Stock) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock) =
            oldItem.id == newItem.id && oldItem.ticker == newItem.ticker
    }

    companion object {
        @JvmStatic
        @BindingAdapter("stockLogo")
        fun loadStockLogo(imageView: AppCompatImageView?, imageUrl: String?) {
            if (imageView == null) return
            if (imageUrl.isNullOrEmpty()) return

            Glide.with(imageView.context)
                .load(imageUrl)
                .centerCrop()
                .transition(
                    DrawableTransitionOptions.withCrossFade(
                        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                    )
                )
                .into(imageView)
        }
    }
}


