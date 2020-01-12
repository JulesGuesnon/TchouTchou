package com.example.tchoutchou.logic

import java.lang.Exception

class Train private constructor(val owner: Character, val fuel: Int, railcarNumber: Int, val currentStation: Station) {
    val stationHistory = mutableListOf<Station>()
    val railcars = mutableListOf<Railcar>()

    init {
        for (i in 0 until railcarNumber) {
            railcars.add(Railcar(5))
        }
    }

    data class Builder(var owner: Character,  var fuel: Int =  10, var railcarNumber: Int = 1, var currentStation: Station) {
        fun owner(owner: Character) = apply { this.owner = owner }
        fun fuel(fuel: Int) = apply { this.fuel = fuel }
        fun railcarNumber(railcarNumber: Int) = apply { this.railcarNumber = railcarNumber }
        fun currentStation(currentStation: Station) = apply { this.currentStation = currentStation }

        fun build(): Train {
            if (owner == null) {
                throw Exception("The owner of the train is not defined, please provide one")
            }

            return Train(owner, fuel, railcarNumber, currentStation)
        }
    }
}