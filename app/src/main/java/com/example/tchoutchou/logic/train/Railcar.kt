package com.example.tchoutchou.logic.train

import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.managers.SlotManager

class Railcar(val capacity: Int) {
    val slots =
        SlotManager<Character?>(
            capacity
        )

    init {
        for (i in 0 until capacity) {
            slots.push(null)
        }
    }

    fun canAddPassenger(): Boolean {
        return slots.hasFreeSlot()
    }

    fun addPassenger(passenger: Character) {
        slots.push(passenger)
    }
}