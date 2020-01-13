package com.example.tchoutchou.logic.Character

enum class CharacterState {
    ALIVE,
    DEAD
}

open class Character(val name: String = "", val stats: Statistics = Statistics.Builder().build()) {
    val inventory = Inventory(5)
    var state = CharacterState.ALIVE
    val money = 0.0
    val modifiers = mutableListOf<Modifier>()

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
        val modifiersBonuses = Statistics.Builder().baseLimited(false).build()

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
                stats.life,
                -1
            )
        )
    }
}