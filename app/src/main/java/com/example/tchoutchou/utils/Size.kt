package com.example.tchoutchou.utils

import android.graphics.Point
import android.view.Display

class Size {
    companion object {
        fun getWidthFromDisplay(display: Display): Int {
            var point = Point()
            display.getSize(point)

            return point.x
        }

        fun getHeightFromDisplay(display: Display): Int {
            var point = Point()
            display.getSize(point)

            return point.y
        }
    }
}