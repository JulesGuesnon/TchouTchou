package com.example.tchoutchou.logic.character

import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.events.EventType
import com.example.tchoutchou.logic.events.Events

enum class CharacterState {
    ALIVE,
    DEAD
}

open class Character(val name: String = "", var stats: Statistics = Statistics.Builder().build()): Events(null) {

    var inventory = Inventory(5)
    var state = CharacterState.ALIVE
    var money = 100.0
    var modifiers = mutableListOf<Modifier>()

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
}