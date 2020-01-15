package com.example.tchoutchou.logic

import android.content.Context
import android.view.View
import com.example.tchoutchou.R
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.elements.MainMenuElements
import com.example.tchoutchou.logic.events.EventManager
import com.example.tchoutchou.logic.events.EventType
import com.example.tchoutchou.logic.managers.BackgroundManager
import com.example.tchoutchou.logic.managers.MusicManager
import com.example.tchoutchou.logic.managers.TransitionManager
import com.example.tchoutchou.logic.story.StoryManager
import com.example.tchoutchou.logic.train.Station
import com.example.tchoutchou.logic.train.Train
import kotlinx.coroutines.delay

class Game(context: Context) {

    val storyManager = StoryManager()
    val eventManager = EventManager(this)
    val musicManager = MusicManager(
        context,
        R.raw.home_sound
    )
    val backgroundManager = BackgroundManager()
    val transitionManager = TransitionManager()

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

    suspend fun animateTrainArrive() {
        train.animateFromOutsideToLeft(false)
        backgroundManager.animateOnTrainArriving()
    }

    suspend fun run() {
        musicManager.load(R.raw.mission_sound, true)

        hideMainMenu()

        train.animateFromOutsideLeftToOutsideRight()
        mainLoop@ while (train.driver.isAlive()) {
            println("Begin Game Loop $step")
            animateTrainArrive()
            eventManager.emit(EventType.BEFOREEVENT)

            storyManager.modalManager.say("Titre sérieux", "blague de yoan", 2000)

            delay(300)

            storyManager.modalManager.show()

            eventManager.emit(EventType.BEFORECHOICE)

            val choice = storyManager.waitForChoice()

            storyManager.modalManager.hide()

            println("Got choice: " + choice.choice)

            choice.callback(this)

            train.animateFromPositionToOutsideRight()

            transitionManager.show("Texte de yoan")

            eventManager.emit(EventType.AFTERCHOICE)

            println("Before goTo")

            storyManager.goTo(choice.to)

            println("After goTo")

            eventManager.emit(EventType.AFTEREVENT)

            println("Went to ${choice.to}")

            delay(2000)

            println("Transition before hide")
            transitionManager.hide()
            println("Transition afterhide")
            println("End of loop $step")
            step++
        }
    }

    suspend fun hideMainMenu() {
        mainMenuElements.options.post {
            mainMenuElements.options.visibility = View.GONE
            mainMenuElements.quitGame.visibility = View.GONE
            mainMenuElements.startGame.visibility = View.GONE
            mainMenuElements.title.visibility = View.GONE
        }

        delay(300)
    }

    fun showMainMenu() {
        mainMenuElements.options.post {
            mainMenuElements.options.visibility = View.VISIBLE
            mainMenuElements.quitGame.visibility = View.VISIBLE
            mainMenuElements.startGame.visibility = View.VISIBLE
            mainMenuElements.title.visibility = View.VISIBLE
        }
    }
}