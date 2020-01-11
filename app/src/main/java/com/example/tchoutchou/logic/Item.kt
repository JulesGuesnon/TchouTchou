package com.example.tchoutchou.logic

open class Item(val name: String, val description: String, val stats: Statistics) {
    open fun effect() {
        println("Item effect $name")
    }
}