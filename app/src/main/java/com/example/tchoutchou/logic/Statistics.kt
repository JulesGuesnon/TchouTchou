package com.example.tchoutchou.logic

class Statistics private constructor(val baseLife: Double, val baseStrength: Double, val baseFood: Double, val baseLuck: Double, validator: (Statistics) -> Unit) {
    var life = baseLife
        set(value) {
            if (value > 10.0) {
                field = 10.0
            } else {
                field = value
            }
        }

    var strength = baseStrength

    var food = baseFood
        set(value) {
            if (value > 10.0) {
                field = 10.0
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

    data class Builder(var life: Double = 0.0, var strength: Double = 0.0, var food: Double = 0.0, var luck: Double = 0.0, var validator: (Statistics) -> Unit = {}) {

        fun life(life: Double) = apply { this.life = life }
        fun strength(strength: Double) = apply { this.strength= strength }
        fun food(food: Double) = apply { this.food= food }
        fun luck(luck: Double) = apply { this.luck = luck }
        fun validator(validator: (Statistics) -> Unit) = apply { this.validator = validator }
        fun build() = Statistics(this.life, this.strength, this.food, this.luck, this.validator)
    }
}