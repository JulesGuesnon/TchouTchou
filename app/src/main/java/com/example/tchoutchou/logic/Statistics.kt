package com.example.tchoutchou.logic

open class Statistics(var life: Double = 0.0, var strength: Double = 0.0, var food: Double = 0.0, var luck: Double = 0.0, validator: (Statistics) -> Unit = {}) {
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

    fun isStronger(other: Statistics): Boolean {
        return strength >= other.strength
    }

    fun getStrengthDiff(other: Statistics): Double {
        return strength - other.strength
    }
}