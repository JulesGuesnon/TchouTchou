package com.example.tchoutchou.logic.Character


enum class Stats {
    LIFE,
    STRENGTH,
    FOOD,
    LUCK
}

class Statistics private constructor(val baseLife: Double, val baseStrength: Double, val baseFood: Double, val baseLuck: Double, val baseLimited: Boolean = true, validator: (Statistics) -> Unit) {
    var life = baseLife
        set(value) {
            if (value > baseLife && baseLimited) {
                field = baseLife
            } else if (value < 0.0) {
                field = 0.0
            } else {
                field = value
            }
        }

    var strength = baseStrength

    var food = baseFood
        set(value) {
            if (value > baseFood && baseLimited) {
                field = baseFood
            } else {
                field = value
            }
        }

    var luck = baseLuck
        set(value) {
            if (value > 1.0) {
                field = 1.0
            } else if (value < 0.0) {
                field = 0.0
            } else {
                field = value
            }
        }

    init {
        validator(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Statistics

        if (life != other.life || strength != other.strength || food != other.food || luck != other.luck) return false

        return true
    }

    fun applyBonuses(bonuses: Statistics) {
        life = baseLife + bonuses.life
        strength = baseStrength + bonuses.strength
        luck = baseLuck + bonuses.luck
        food = baseFood + bonuses.food
    }

    fun print() {
        println("--------- Statistics ------------")
        println("Life: " + life)
        println("Strength: " + strength)
        println("Food: " + food)
        println("Luck: " + luck)
        println("\n")

    }


    operator fun plus(stats: Statistics): Statistics =
        Statistics(
            life + stats.life,
            strength + stats.strength,
            food + stats.food,
            luck + stats.luck,
            false,
            {})

    data class Builder(var life: Double = 0.0, var strength: Double = 0.0, var food: Double = 0.0, var luck: Double = 0.0, var baseLimited: Boolean = true, var validator: (Statistics) -> Unit = {}) {

        fun life(life: Double) = apply { this.life = life }
        fun strength(strength: Double) = apply { this.strength= strength }
        fun food(food: Double) = apply { this.food= food }
        fun luck(luck: Double) = apply { this.luck = luck }
        fun baseLimited(baseLimited: Boolean) = apply { this.baseLimited = baseLimited }
        fun validator(validator: (Statistics) -> Unit) = apply { this.validator = validator }

        fun build() = Statistics(
            this.life,
            this.strength,
            this.food,
            this.luck,
            baseLimited,
            this.validator
        )
    }
}