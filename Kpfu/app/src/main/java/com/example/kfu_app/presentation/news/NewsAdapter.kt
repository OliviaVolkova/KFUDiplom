package com.example.kfu_app.presentation.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.kfu_app.databinding.NewsItemBinding

class NewsAdapter(
    private val context: Context
) : ListAdapter<NewsItem, NewViewHolder>(

    object : DiffUtil.ItemCallback<NewsItem>() {

        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean = oldItem == newItem

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        return NewViewHolder(
            NewsItemBinding.inflate(LayoutInflater.from(parent.context)),
            context
        )
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}


