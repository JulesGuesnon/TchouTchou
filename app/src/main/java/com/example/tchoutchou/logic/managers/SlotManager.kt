package com.example.tchoutchou.logic.managers

class SlotManager<T> (size: Int){
    private val slots = mutableListOf<T>()

    fun isEmpty (): Boolean {
        var isEmpty = true
        for (obj in slots) {
            if (obj != null) {
                isEmpty = false
                break
            }

        }

        return isEmpty
    }

    fun has(obj: T): Boolean {
        return slots.contains(obj)
    }

    fun hasFreeSlot (): Boolean {
        var hasFreeSlot = false

        for (obj in slots) {
            if (obj == null) {
                hasFreeSlot = true
                break
            }
        }

        return hasFreeSlot
    }

    fun getFreeSlot(): Int {
        var freeSlot = -1

        for ((i, obj) in slots.withIndex()) {
            if (obj != null) {
                freeSlot = i
                break
            }
        }

        return freeSlot
    }


    fun getSlotNumber(obj: T): Int {
        return slots.indexOf(obj)
    }

    fun get(i: Int): T? {
        return slots[i]
    }

    fun set(i: Int, obj: T) {
        slots[i] = obj
    }

    fun push(obj: T) {
        slots.add(obj)
    }

    fun delete(obj: T): Boolean {
        return slots.remove(obj)
    }

    fun delete(index: Int): Boolean {
        return slots.removeAt(index) != null
    }

    fun forEach(action: (T) -> Unit) {
        slots.forEach {
            if (it != null) {
                action(it)
            }
        }
    }

}