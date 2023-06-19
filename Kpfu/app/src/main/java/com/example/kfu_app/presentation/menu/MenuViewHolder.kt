package com.example.kfu_app.presentation.menu

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kfu_app.databinding.MenuItemBinding

class MenuViewHolder(
    private val menuItemBinding: MenuItemBinding,
    val context: Context,
    private val onItemClick: (String) -> Unit
) : RecyclerView.ViewHolder(menuItemBinding.root) {

    fun bind(item: MenuItem) {
        Glide
            .with(context)
            .load(item.imageUrl)
            .into(menuItemBinding.ivImg)
        menuItemBinding.tvText.text = item.title
        menuItemBinding.root.setOnClickListener {
            onItemClick(item.id)
        }
    }

}