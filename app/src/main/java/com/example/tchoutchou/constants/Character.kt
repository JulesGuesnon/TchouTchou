package com.example.tchoutchou.constants

import android.view.ViewGroup
import com.example.tchoutchou.R
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.character.Statistics
import com.example.tchoutchou.utils.Size
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import pl.droidsonroids.gif.GifImageView
import java.lang.Exception

val characterStatsValidator: (Statistics) -> Unit = {

        if (it.luck > 1.0 || it.luck < 0.0) {
            throw Exception("Luck is greater than 1. or lower than 0. (provided value: ${it.luck}), it must be between 0. and 1.")
        }

        if (it.life > 10.0) {
            throw Exception("Life is greater than 10 (provided value: ${it.luck}), it must be between 0. and 10.")
        }

        if (it.food > 10.0) {
            throw Exception("Life is greater than 10 (provided value: ${it.food}), it must be between 0. and 10.")
        }

}

val rascal1 = Character("Cantal", Statistics.Builder().build(), R.drawable.character_rascal1) { character, gap, _, _ ->
    enterFromRight(character, gap)
}
val rascal2 = Character("Pascal", Statistics.Builder().build(), R.drawable.character_rascal2) { character, gap, _, _ ->
    enterFromRight(character, gap)
}
val rascal3 = Character("Chantal", Statistics.Builder().build(), R.drawable.character_rascal3) { character, gap, _, _ ->
    enterFromRight(character, gap)
}

val seller = Character("Paul", Statistics.Builder().build(), R.drawable.character_seller) { character, gap, _, _ ->
    enterFromRight(character, gap)
}

val reaper = Character("Reaper", Statistics.Builder().build(), R.drawable.character_reaper) { character, _, context, gameLayout ->
    val gifImageView = character.gifImageView
    if (gifImageView != null) {
        gifImageView.post {

            var smokeView = GifImageView(context)
            smokeView.setImageResource(R.drawable.smoke_reaper)
            smokeView.layoutParams = ViewGroup.LayoutParams(gifImageView.layoutParams.width, gifImageView.layoutParams.width)
            smokeView
                .animate()
                .translationY((Size.getHeightFromDisplay(character.display) - smokeView.layoutParams.height).toFloat())
                .translationX((Size.getWidthFromDisplay(character.display) - smokeView.layoutParams.width).toFloat())

            gameLayout.addView(smokeView)

            gifImageView.translationX = (Size.getWidthFromDisplay(character.display) - smokeView.layoutParams.width).toFloat()
            gifImageView.alpha = 0f

            gifImageView
                .animate()
                .setStartDelay(600)
                .setDuration(600)
                .alpha(1f)
                .withEndAction {
                    gameLayout.removeView(smokeView)
                }

        }
    }
}

val theManWhoSuffer = Character("TheManWhoSuffer", Statistics.Builder().build(), R.drawable.character_lemecquiamal) { character, gap, _, _ ->
    enterFromRight(character, gap)
}

val joe = Character("Joe", Statistics.Builder().build(), R.drawable.character_joe) { character, gap, _, _ ->
    enterFromRight(character, gap)
}

val goul = Character("Goul", Statistics.Builder().build(), R.drawable.character_goul) { character, gap, _, _ ->
    enterFromRight(character, gap)
}