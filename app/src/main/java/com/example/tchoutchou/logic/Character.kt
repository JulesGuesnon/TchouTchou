package com.example.tchoutchou.logic

import java.lang.Exception

enum class CharacterStatsEnum {
    STRENGTH,
    LIFE,
    LUCK,
    FOOD,
}

enum class CharacterState {
    ALIVE,
    DEAD
}


data class CharacterStats(var life: Double, var strength: Double, var food: Double, var luck: Double) {
    init {
        // Validations of the inputs
        if (luck > 1.0 || luck < 0.0) {
            throw Exception("Luck is greater than 1 (provided value: $luck), it must be between 0. and 1.")
        }
    }
}

open class Character(private val name: String, stats: CharacterStats) {

    val statistics = Statistics<CharacterStatsEnum>()
    val state = CharacterState.ALIVE

    init {
        statistics.set(CharacterStatsEnum.FOOD, stats.food)
        statistics.set(CharacterStatsEnum.STRENGTH, stats.strength)
        statistics.set(CharacterStatsEnum.LIFE, stats.life)
        statistics.set(CharacterStatsEnum.LUCK, stats.luck)
    }

    open fun effect() {
        println("$name effect")
    }
}