package com.example.kfu_app.presentation.fragments.navigationDrawer.schedule

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.kfu_app.databinding.LessonItemBinding

class LessonViewHolder (
    private val binding: LessonItemBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LessonItem){
        Log.d("MYTAG", "bind $item")

        binding.lessonName.text = item.name
        binding.lessonTeacher.text = item.teacher
        binding.lessonRoom.text = item.room
        binding.lessonTime.text = item.dateStart + "-" + item.dateEnd

    }
}
