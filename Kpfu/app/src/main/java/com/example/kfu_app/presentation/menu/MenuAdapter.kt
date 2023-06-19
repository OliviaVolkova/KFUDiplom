package com.example.kfu_app.presentation.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.kfu_app.databinding.MenuItemBinding
import com.example.kfu_app.presentation.menu.MenuItem
import com.example.kfu_app.presentation.menu.MenuViewHolder

class MenuAdapter(
    private val context: Context,
    private val onItemClick: (String) -> Unit
) : ListAdapter<MenuItem, MenuViewHolder>(
    object : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean = oldItem == newItem

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            MenuItemBinding.inflate(LayoutInflater.from(parent.context)),
            context,
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
