package com.example.kfu_app.presentation.fragments.Institutes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.kfu_app.databinding.InstitutesItemBinding

class InstitutesAdapter(
    private val context: Context,
    val listener: (String) -> Unit
) : ListAdapter<InstitutesItem, InstitutesViewHolder>(

    object : DiffUtil.ItemCallback<InstitutesItem>() {

        override fun areItemsTheSame(oldItem: InstitutesItem, newItem: InstitutesItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: InstitutesItem, newItem: InstitutesItem): Boolean =
            oldItem == newItem

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutesViewHolder {
        return InstitutesViewHolder(
            InstitutesItemBinding.inflate(LayoutInflater.from(parent.context)),
            context,
            listener
        )
    }

    override fun onBindViewHolder(holder: InstitutesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}