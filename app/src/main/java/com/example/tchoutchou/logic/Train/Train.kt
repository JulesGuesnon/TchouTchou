package com.example.tchoutchou.logic.Train

import com.example.tchoutchou.logic.Character.Character
import com.example.tchoutchou.logic.Character.Modifier
import java.lang.Exception

class Train private constructor(val driver: Character, val stats: Statistics, currentStation: Station) {

    val stationHistory = mutableListOf<Station>()
    var currentStation: Station
        get() = stationHistory[stationHistory.size - 1]
        set(value: Station) {
            stationHistory.add(value)
        }

    val railcars = mutableListOf<Railcar>()
    val upgrades = HashMap<Upgrades, Upgrade>()

    init {
        stationHistory.add(currentStation)

        upgrades.set(
            Upgrades.POWER,
            Upgrade(
                Upgrades.POWER,
                1.0
            )
        )
        upgrades.set(
            Upgrades.RAILCAR,
            Upgrade(
                Upgrades.RAILCAR,
                1.0
            )
        )
        upgrades.set(
            Upgrades.RESISTANCE,
            Upgrade(
                Upgrades.RESISTANCE,
                1.0
            )
        )
        upgrades.set(
            Upgrades.WEAPON,
            Upgrade(
                Upgrades.WEAPON,
                0.0
            )
        )

        for (i in 0 until (upgrades[Upgrades.RAILCAR]?.value?.toInt() ?: 1)) {
            railcars.add(Railcar(5))
        }
    }

    data class Builder(var driver: Character, var maxFuel: Int =  10, var stats: Statistics = Statistics.Builder().build(), var currentStation: Station) {
        fun driver(driver: Character) = apply { this.driver = driver }
        fun maxFuel(fuel: Int) = apply { this.maxFuel = maxFuel }
        fun stats(stats: Statistics) = apply { this.stats = stats }
        fun currentStation(currentStation: Station) = apply { this.currentStation = currentStation }

        fun build(): Train {
            if (driver == null) {
                throw Exception("The owner of the train is not defined, please provide one")
            }

            return Train(
                driver,
                stats,
                currentStation
            )
        }
    }

    fun upgrade(upgrade: Upgrade) {
        upgrades[upgrade.type] = upgrade
    }

    fun canAddPassenger(): Boolean {
        for (railcar in railcars) {
            if (railcar.slots.hasFreeSlot()) return true
        }

        return false
    }

    fun addPassenger(passenger: Character): Boolean {
        for (railcar in railcars) {
            if (railcar.canAddPassenger()) {
                railcar.addPassenger(passenger)
                return true
            }
        }

        return false
    }
}