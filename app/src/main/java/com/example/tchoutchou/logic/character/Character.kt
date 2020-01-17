package com.example.tchoutchou.logic.character

import android.content.Context
import android.view.Display
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.tchoutchou.constants.backgroundHeight
import com.example.tchoutchou.constants.trackHeight
import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.elements.CharacterElements
import com.example.tchoutchou.logic.events.EventType
import com.example.tchoutchou.logic.events.Events
import com.example.tchoutchou.utils.Size
import kotlinx.coroutines.delay
import pl.droidsonroids.gif.GifImageView

enum class CharacterState {
    ALIVE,
    DEAD
}

open class Character(val name: String = "", var stats: Statistics = Statistics.Builder().build(), val texture: Int = -1, val enterAnimation: suspend (Character, Float, Context, ConstraintLayout) -> Unit): Events(null) {

    var inventory = Inventory(5)
    var state = CharacterState.ALIVE
    var money = 100.0
    var modifiers = mutableListOf<Modifier>()

    var gifImageView: GifImageView? = null
    lateinit var display: Display
    lateinit var context: Context
    lateinit var constraintLayout: ConstraintLayout

    init {
        this.registerEvent(EventType.BEFORECHOICE)
    }

    open fun effect() {
        println("$name effect")
    }

    fun isStronger(other: Character): Boolean {
        return stats.strength >= other.stats.strength
    }

    fun getStrengthDiff(other: Character): Double {
        return stats.strength - other.stats.strength
    }

    fun isDead(): Boolean {
        return stats.life <= 0.0
    }

    fun isAlive(): Boolean {
        return stats.life > 0.0
    }

    fun computeBonuses() {
        val modifiersBonuses = Statistics
            .Builder()
            .baseLimited(false)
            .food(0.0)
            .life(0.0)
            .luck(0.0)
            .strength(0.0)
            .build()

        for (modifier in modifiers) {
            when (modifier.type) {
                Stats.LIFE -> modifiersBonuses.life += modifier.value
                Stats.FOOD -> modifiersBonuses.food += modifier.value
                Stats.STRENGTH -> modifiersBonuses.strength += modifier.value
                Stats.LUCK -> modifiersBonuses.luck += modifier.value
            }
        }

        stats.applyBonuses(
            inventory.getAllBonuses() + modifiersBonuses
        )
    }

    fun addModifier(modifier: Modifier) {
        modifiers.add(modifier)
        computeBonuses()
    }

    fun setDead() {
        addModifier(
            Modifier(
                Stats.LIFE,
                -stats.life,
                -1
            )
        )
        computeBonuses()
    }

    override fun beforeChoice(game: Game) {
        println("BEFORE CHOICE OF ")
    }

    fun reset() {
        modifiers = mutableListOf()
        inventory = Inventory(5)
        stats = Statistics.Builder().build()
        state = CharacterState.ALIVE
    }

    fun setCharacterOutsideRight() {
        val gifImageView = this.gifImageView
        if (gifImageView == null) return

        gifImageView.post {
            val windowHeight = Size.getHeightFromDisplay(display).toFloat()
            val backgroundHeightRatio = backgroundHeight / windowHeight

            println(((backgroundHeightRatio + 1) * trackHeight))
            gifImageView
                .animate()
                .setDuration(0)
                .translationX(Size.getWidthFromDisplay(display).toFloat() + gifImageView.layoutParams.width)
                .translationY( windowHeight - gifImageView.layoutParams.height - ((backgroundHeightRatio + 1) * trackHeight * 4).toFloat())
        }
    }

    fun loadCharacterOutsideRight(context: Context, constraintLayout: ConstraintLayout) {
        gifImageView = GifImageView(context)
        val gifImageView = this.gifImageView
        if (gifImageView == null) return

        constraintLayout.post {

            gifImageView.setImageResource(texture)
            val imageSize = (Size.getHeightFromDisplay(display) * 0.3).toInt()
            gifImageView.layoutParams = ViewGroup.LayoutParams(imageSize, imageSize)

            setCharacterOutsideRight()

            constraintLayout.addView(gifImageView)
        }
    }

    suspend fun animateEnter(gap: Float) {
        enterAnimation(this, gap, context, constraintLayout)
    }


    fun deleteCharacterView(constraintLayout: ConstraintLayout) {
        constraintLayout.post {
            constraintLayout.removeView(gifImageView)
            gifImageView = null
        }
    }
}