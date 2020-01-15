package com.example.tchoutchou.logic

import android.content.Context
import android.view.View
import com.example.tchoutchou.R
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

class Game(context: Context) {

    val storyManager = StoryManager()
    val eventManager = EventManager(this)
    val musicManager = MusicManager(context, R.raw.home_sound)

    lateinit var mainMenuElements: MainMenuElements
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
        musicManager.load(R.raw.mission_sound, true)

        mainLoop@ while (train.driver.isAlive()) {
            eventManager.emit(EventType.BEFOREEVENT)
            step++
            println("Waiting for choice")
            storyManager.modalManager.say("Titre sérieux", "blague de yoan", 2000)

            delay(100)

            storyManager.modalManager.show()

            eventManager.emit(EventType.BEFORECHOICE)

            val choice = storyManager.waitForChoice()

            storyManager.modalManager.hide()
            println("Got choice: " + choice.choice)
            choice.callback(this)

            println("Triggered choice callback")
            println("Will go to ${choice.to}")

            println("Before emit AFTERCHOICE")
            eventManager.emit(EventType.AFTERCHOICE)
            println("After emit AFTERCHOICE")

            println("Before goTo")

            storyManager.goTo(choice.to)

            println("After goTo")

            println("Before emit AFTEREVENT")
            eventManager.emit(EventType.AFTEREVENT)
            println("After emit AFTEREVENT")

            println("Went to ${choice.to}")

            delay(1000)
            println("END")
        }
    }

    fun hideMainMenu() {
        mainMenuElements.options.visibility = View.GONE
        mainMenuElements.quitGame.visibility = View.GONE
        mainMenuElements.startGame.visibility = View.GONE
        mainMenuElements.title.visibility = View.GONE
    }

    fun showMainMenu() {
        mainMenuElements.options.visibility = View.VISIBLE
        mainMenuElements.quitGame.visibility = View.VISIBLE
        mainMenuElements.startGame.visibility = View.VISIBLE
        mainMenuElements.title.visibility = View.VISIBLE
    }
}