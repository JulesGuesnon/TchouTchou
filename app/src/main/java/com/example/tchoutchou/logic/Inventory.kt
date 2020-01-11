package com.example.tchoutchou.logic

class Inventory (val size: Int) {
    private val slots = SlotManager<Item>(size)


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
        val stats = Statistics.Builder().build()

        slots.forEach {
            stats.food += it.stats.food
            stats.life += it.stats.life
            stats.luck += it.stats.luck
            stats.strength += it.stats.strength
        }

        return stats
    }
}