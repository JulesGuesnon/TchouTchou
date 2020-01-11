package com.example.tchoutchou.logic

class Statistics<T>() {
    private val stats = HashMap<T, Double>()

    fun set(stat: T, value: Double) {
        stats[stat] = value
    }

    fun get(stat: T): Double {
        return stats[stat] ?: -1.0
    }
}