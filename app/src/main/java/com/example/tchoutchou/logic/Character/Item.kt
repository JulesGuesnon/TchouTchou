package com.example.tchoutchou.logic.Character

import java.lang.Exception

interface IsNull {
    val isNull: Boolean
}

enum class ItemTypes {
    PERMANENT,
    TEMPORARY,
    CONSUMABLE
}

open class Item private constructor(val type: ItemTypes, val name: String, val description: String, val stats: Statistics) {
    open fun effect(owner: Character) {
        println("Item effect $name")
    }

    data class Builder(var type: ItemTypes = ItemTypes.PERMANENT, var name: String = "", var description: String = "", var stats: Statistics = Statistics.Builder().build(), var expiresAt: Int = -1) {
        fun type(type: ItemTypes) = apply { this.type =  type}
        fun name(name: String) = apply { this.name =  name}
        fun stats(stats: Statistics) = apply { this.stats =  stats}
        fun expiresAt(expiresAt: Int) = apply { this.expiresAt = expiresAt }

        fun build(): Item {
            if (type == ItemTypes.TEMPORARY && expiresAt <= 0) {
                throw Exception("The item $name is a temporary item but you didn't specified an expiration to the item")
            }
            return Item(
                type,
                name,
                description,
                stats
            )
        }
    }
}