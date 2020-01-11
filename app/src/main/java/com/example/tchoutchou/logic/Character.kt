package com.example.tchoutchou.logic

import com.example.tchoutchou.R

enum class CharacterState {
    ALIVE,
    DEAD
}
open class Character(private val name: String, val stats: Statistics) {

    private val inventory = Inventory(R.integer.INVENTORY_SIZE)
    private val state = CharacterState.ALIVE
    private val money = 0.0

    open fun effect() {
        println("$name effect")
    }

    fun isStronger(other: Statistics): Boolean {
        return stats.strength >= other.strength
    }

    fun getStrengthDiff(other: Statistics): Double {
        return stats.strength - other.strength
    }

    fun isDead(): Boolean {
        return stats.life <= 0.0
    }

    fun isAlive(): Boolean {
        return stats.life > 0.0
    }

    fun computeItemsBonuses() {
        val bonuses = inventory.getAllBonuses()


    }
}