package com.example.tchoutchou.logic.managers

import android.view.Display
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import com.example.tchoutchou.utils.Size
import kotlinx.coroutines.delay
import pl.droidsonroids.gif.GifImageView

class BackgroundManager {
    lateinit var background: GifImageView
    lateinit var display: Display
    var widthToTranslate = 0f
    var currentBackground: Int = -1

    fun setElement(display: Display, background: GifImageView) {
        this.background = background
        this.display = display
        widthToTranslate = - (background.layoutParams.width - Size.getWidthFromDisplay(display)).toFloat() + 120f
    }

    suspend fun animateFromLeftToRight(blocking: Boolean = false) {
        val animationDuration = 300000L

        background.post {

            background
                .animate()
                .setDuration(animationDuration)
                .setInterpolator(LinearInterpolator())
                .translationX(widthToTranslate)
        }

        if (!blocking) delay(animationDuration)
    }

    suspend fun animateOnTrainArriving() {
        val animationDuration = 1100L

        background.post {
            background
                .animate()
                .setDuration(animationDuration)
                .setInterpolator(DecelerateInterpolator())
                .translationX(widthToTranslate)
        }

        delay(animationDuration)
    }

    suspend fun resetBackgroundPosition() {
        background.post {
            background
                .animate()
                .setDuration(0)
                .translationX(0f)
        }
    }

    suspend fun loadBackground(background: Int) {
        if (currentBackground != background) {
            currentBackground = background

            this.background.post {
                println("Have to load background")
                this.background.setImageResource(background)
            }
            delay(500)
        }
    }
}