package com.example.tchoutchou.logic.train


enum class Stats {
    FUEL,
    SPEED,
    LIFE,
}

class Statistics private constructor(val baseFuel: Double, val baseSpeed: Double, val baseLife: Double, val baseLimited: Boolean) {
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

    var fuel = baseFuel
        set(value) {
            if (value > baseFuel && baseLimited) {
                field = baseLife
            } else if (value < 0.0) {
                field = 0.0
            } else {
                field = value
            }
        }

    var speed = baseSpeed
        set(value) {
            if (value < 0.0) {
                field = 0.0
            } else {
                field = value
            }
        }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Statistics

        if (life != other.life || fuel != other.fuel|| speed != other.speed) return false

        return true
    }

    fun applyBonuses(bonuses: Statistics) {
        life = baseLife + bonuses.life
        fuel = baseFuel + bonuses.fuel
        speed = speed + bonuses.speed
    }

    fun print() {
        println("--------- Statistics ------------")
        println("Life: " + life)
        println("Speed: " + speed)
        println("Fuel: " + fuel)
        println("\n")

    }


    operator fun plus(stats: Statistics): Statistics =
        Statistics(
            life + stats.life,
            fuel + stats.fuel,
            life + stats.life,
            false
            )

    data class Builder(var fuel: Double = 10.0, var speed: Double = 5.0, var life: Double = 10.0, var baseLimited: Boolean = false) {

        fun life(life: Double) = apply { this.life = life }
        fun fuel(fuel: Double) = apply { this.fuel = fuel }
        fun speed(speed: Double) = apply { this.speed = speed }
        fun baseLimited(baseLimited: Boolean) = apply { this.baseLimited = baseLimited }

        fun build() = Statistics(
            this.fuel,
            this.speed,
            this.life,
            baseLimited
        )
    }
}