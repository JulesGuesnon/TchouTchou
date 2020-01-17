package com.example.tchoutchou.constants

import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.utils.Size
import kotlinx.coroutines.delay

suspend fun enterFromRight(character: Character, gap: Float) {
    val gifImageView = character.gifImageView
    if (gifImageView == null) return

    val animationTime = 500L
    gifImageView.post {
        gifImageView
            .animate()
            .setDuration(animationTime)
            .translationX(
                (Size.getWidthFromDisplay(character.display).toFloat() - gifImageView.layoutParams.width) + gap
            )
    }

    delay(animationTime)
}