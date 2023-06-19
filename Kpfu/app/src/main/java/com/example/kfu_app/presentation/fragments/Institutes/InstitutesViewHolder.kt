package com.example.kfu_app.presentation.fragments.Institutes

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kfu_app.R
import com.example.kfu_app.databinding.InstitutesItemBinding
import com.example.kfu_app.presentation.fragments.webView.WebViewFragment

class InstitutesViewHolder(
    private val binding: InstitutesItemBinding,
    private val context: Context,
    val listener: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: InstitutesItem){
        Log.d("MYTAG", "bind $item")
        Glide
            .with(context)
            .load(item.imgUrl)
            .into(binding.ivImg)
        binding.tvText.text = item.title
        binding.root.setOnClickListener {
            listener.invoke(item.link)
        }

    }
}
