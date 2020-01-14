package com.example.tchoutchou.logic

import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.events.EventManager
import com.example.tchoutchou.logic.events.EventType
import com.example.tchoutchou.logic.story.StoryManager
import com.example.tchoutchou.logic.train.Station
import com.example.tchoutchou.logic.train.Train
import com.example.tchoutchou.story.greenStory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class Game() {

    val storyManager = StoryManager()
    val eventManager = EventManager(this)

    lateinit var train: Train
    var step = 0

    fun init() {
        train = Train
            .Builder()
            .driver(
                Character("Billy")
            )
            .currentStation(Station("Montparnasse"))
            .build()

        train.driver.game = this

        storyManager.goTo("G1")
    }


    suspend fun run() {
        mainLoop@ while (train.driver.isAlive()) {
            eventManager.emit(EventType.BEFOREEVENT)
            step++
            println("Waiting for choice")
            eventManager.emit(EventType.BEFORECHOICE)
            val choice = storyManager.waitForChoice()

            println("Got choice: " + choice.choice)
            choice.callback(this)

            println("Triggered choice callback")
            println("Will go to ${choice.to}")

            eventManager.emit(EventType.AFTERCHOICE)

            storyManager.goTo(choice.to)

            eventManager.emit(EventType.AFTEREVENT)

            println("Went to ${choice.to}")

            delay(1000)
            println("END")
        }
    }
}