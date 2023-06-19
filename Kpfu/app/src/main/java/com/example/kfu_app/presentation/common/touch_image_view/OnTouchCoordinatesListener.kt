package com.example.kfu_app.presentation.common.touch_image_view

import android.graphics.PointF
import android.view.MotionEvent
import android.view.View

interface OnTouchCoordinatesListener {
    fun onTouchCoordinate(view: View, event: MotionEvent, bitmapPoint: PointF)
}