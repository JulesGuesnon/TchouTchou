package com.example.tchoutchou.logic

import com.example.tchoutchou.R
import com.example.tchoutchou.constants.characterValidator
import java.lang.Exception

enum class CharacterState {
    ALIVE,
    DEAD
}
open class Character(private val name: String, val stats: Statistics) {

    val inventory = Inventory(R.integer.INVENTORY_SIZE)
    val state = CharacterState.ALIVE

    open fun effect() {
        println("$name effect")
    }
}