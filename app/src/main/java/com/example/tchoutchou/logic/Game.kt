package com.example.tchoutchou.logic

import com.example.tchoutchou.logic.Story.StoryManager
import com.example.tchoutchou.logic.Train.Train

class Game(val train: Train) {

    val story = StoryManager()
    var step = 0

    fun init() {}

    fun run() {
        mainLoop@ while (train.driver.isAlive()) {
            step++
        }
    }
}