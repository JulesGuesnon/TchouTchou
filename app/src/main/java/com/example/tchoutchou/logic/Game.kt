package com.example.tchoutchou.logic

import com.example.tchoutchou.logic.events.EventManager
import com.example.tchoutchou.logic.story.StoryManager
import com.example.tchoutchou.logic.train.Train
import com.example.tchoutchou.story.greenStory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class Game(val train: Train) {

    val storyManager = StoryManager()
    val eventManager = EventManager(this)

    var step = 0

    fun init() {
        storyManager.goTo("G1")
    }


    suspend fun run() {
        mainLoop@ while (train.driver.isAlive()) {
            step++
            println("Waiting for choice")
            val choice = storyManager.waitForChoice()

            println("Got choice: " + choice.choice)
            choice.callback(this)

            println("Triggered choice callback")
            println("Will go to ${choice.to}")

            storyManager.goTo(choice.to)

            println("Went to ${choice.to}")

            delay(1000)
            println("END")
        }
    }
}