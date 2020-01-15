package com.example.tchoutchou.logic.managers

import android.view.View
import com.example.tchoutchou.logic.elements.TransitionElements
import kotlinx.coroutines.delay

class TransitionManager {
    lateinit var transitionElements: TransitionElements

    fun setElements(elements: TransitionElements) {
        transitionElements = elements
    }

    fun show(text: String) {
        transitionElements.transition.post {
            transitionElements.transition.text = text
            transitionElements.transition.visibility = View.VISIBLE
            transitionElements
                .transition
                .animate()
                .setDuration(500)
                .alpha(1f)
        }
    }

    suspend fun hide() {
        transitionElements.transition.post {
            transitionElements
                .transition
                .animate()
                .setDuration(500)
                .alpha(0f)
        }

        delay(500)

        transitionElements.transition.post {
            transitionElements.transition.visibility = View.GONE
        }
    }
}