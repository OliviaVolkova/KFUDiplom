package com.example.kfu_app.presentation.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.kfu_app.databinding.NewsItemBinding


class NewViewHolder(
    private val newsItemBinding: NewsItemBinding,
    val context: Context
): ViewHolder(newsItemBinding.root){

    fun bind(item: NewsItem){
        Log.d("MYTAG", "bind $item")
        Glide
            .with(context)
            .load(item.imageUrl)
            .into(newsItemBinding.ivNews)
        newsItemBinding.nameTextView.text = item.title
        newsItemBinding.root.setOnClickListener {

            try{
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(item.link))
            context.startActivity(intent)
            }
            catch (e: Exception){
                val toast = Toast.makeText(context,"Новости не существует", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}