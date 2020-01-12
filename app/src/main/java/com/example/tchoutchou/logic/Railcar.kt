package com.example.tchoutchou.logic

class Railcar(val capacity: Int) {
    val slots = SlotManager<Character>(capacity)

    init {
        for (i in 0 until capacity) {
            slots.push(Character())
        }
    }
}