package com.example.tchoutchou.logic

import android.view.Display
import com.example.tchoutchou.utils.Size
import kotlinx.coroutines.delay
import pl.droidsonroids.gif.GifImageView

class BackgroundManager {
    lateinit var background: GifImageView
    lateinit var display: Display

    fun setElement(display: Display, background: GifImageView) {
        this.background = background
        this.display = display
    }

    suspend fun animateFromLeftToRight() {
        val animationDuration = 10000L

        background.post {
            val widthToTranslate = - (background.layoutParams.width - Size.getWidthFromDisplay(display)).toFloat()
            background
                .animate()
                .setDuration(animationDuration)
                .translationX(
                    widthToTranslate
                )
        }

        delay(animationDuration)
    }
}