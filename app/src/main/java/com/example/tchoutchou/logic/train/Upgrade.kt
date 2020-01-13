package com.example.tchoutchou.logic.train

enum class Upgrades {
    POWER,
    RAILCAR,
    WEAPON,
    RESISTANCE
}

data class Upgrade (val type: Upgrades, val value: Double)