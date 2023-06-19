package com.example.kfu_app.presentation.fragments.navigationDrawer.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.kfu_app.databinding.InstitutesItemBinding
import com.example.kfu_app.databinding.LessonItemBinding
import com.example.kfu_app.presentation.fragments.Institutes.InstitutesItem
import com.example.kfu_app.presentation.fragments.Institutes.InstitutesViewHolder

class LessonAdapter(
    private val context: Context,
) : ListAdapter<LessonItem, LessonViewHolder>(

    object : DiffUtil.ItemCallback<LessonItem>() {

        override fun areItemsTheSame(oldItem: LessonItem, newItem: LessonItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LessonItem, newItem: LessonItem): Boolean =
            oldItem == newItem

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            LessonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}