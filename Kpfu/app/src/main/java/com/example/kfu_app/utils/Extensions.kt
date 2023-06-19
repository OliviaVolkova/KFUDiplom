package com.example.kfu_app.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.example.kfu_app.R

fun FragmentActivity.showDialog(dialog: DialogFragment) {
    dialog.show(this.supportFragmentManager, "decline")
}

fun Context.clickSound() {
    val click: MediaPlayer = MediaPlayer.create(this, R.raw.click)
    click.start()
}