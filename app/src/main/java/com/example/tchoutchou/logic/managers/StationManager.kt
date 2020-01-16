package com.example.tchoutchou.logic.managers

import android.view.View
import com.example.tchoutchou.logic.elements.StationElements
import kotlinx.coroutines.delay

class StationManager {
    lateinit var elements: StationElements

    fun show(text: String) {
        elements.station.post {
            elements.station.visibility = View.VISIBLE
            elements.station.text = text
            elements
                .station
                .animate()
                .setDuration(300)
                .alpha(1f)
        }
    }

    suspend fun hide() {
        val animationTime = 300L
        elements.station.post {
            elements
                .station
                .animate()
                .setDuration(animationTime)
                .alpha(0f)
                .withEndAction {
                    elements.station.visibility = View.GONE
                }
        }

        delay(animationTime)
    }
}