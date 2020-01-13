package com.example.tchoutchou.logic.Train

import com.example.tchoutchou.logic.Character.Character
import com.example.tchoutchou.logic.SlotManager

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