package com.example.tchoutchou.logic

class Train private constructor(val fuel: Int, railcarNumber: Int, val currentStation: Station) {
    val stationHistory = mutableListOf<Station>()
    val railcars = mutableListOf<Railcar>()

    init {
        for (i in 0 until railcarNumber) {
            railcars.add(Railcar(5))
        }
    }

    data class Builder(var fuel: Int =  10, var railcarNumber: Int = 1, var currentStation: Station) {
        fun fuel(fuel: Int) = apply { this.fuel = fuel }
        fun railcarNumber(railcarNumber: Int) = apply { this.railcarNumber = railcarNumber }
        fun currentStation(currentStation: Station) = apply { this.currentStation = currentStation }

        fun build() = Train(fuel, railcarNumber, currentStation)
    }
}