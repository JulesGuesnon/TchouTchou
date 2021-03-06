package com.example.tchoutchou.logic.character

import com.example.tchoutchou.logic.managers.SlotManager

class Inventory (val size: Int) {
    private val slots =
        SlotManager<Item?>(
            size
        )

    init {
        for (i in 0 until size) {
            slots.push(null)
        }
    }

    fun add(item: Item): Boolean {
        if (slots.hasFreeSlot()) {
            slots.set(slots.getFreeSlot(), item)

            return true
        }

        return false
    }

    fun remove(item: Item): Boolean {
        return slots.delete(item)
    }

    fun remove(index: Int): Boolean {
        return slots.delete(index)
    }

    fun replace(itemToRemove: Item, itemToAdd: Item): Boolean {
        if (!slots.has(itemToRemove)) {
            return false
        }

        if (slots.has(itemToAdd)) {
            slots.delete(itemToAdd)

        }

        slots.set(
            slots.getSlotNumber(itemToRemove),
            itemToAdd
        )

        return true
    }

    fun print() {
        for (i in 0 until size) {
            println("-----------${i}-----------")
            println("Name: " + slots.get(i)?.name)
            println("Descritpion: " + slots.get(i)?.description)
            println("\n")
        }
    }

    fun getAllBonuses(): Statistics {
        val stats = Statistics
            .Builder()
            .life(0.0)
            .food(0.0)
            .luck(0.0)
            .strength(0.0)
            .baseLimited(false)
            .build()

        slots.forEach {
            it as Item
            stats.food += it.stats.food
            stats.life += it.stats.life
            stats.luck += it.stats.luck
            stats.strength += it.stats.strength
        }

        return stats
    }
}
