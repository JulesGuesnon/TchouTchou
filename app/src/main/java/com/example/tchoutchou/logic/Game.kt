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
import com.example.tchoutchou.story.greenStory
import com.example.tchoutchou.story.t
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

enum class GameState {
    RUNNING,
    WON,
    OVER
}

class Game(context: Context) {

    val storyManager = StoryManager()
    val eventManager = EventManager(this)
    val musicManager = MusicManager(
        context,
        R.raw.home_sound
    )
    val backgroundManager = BackgroundManager()
    val transitionManager = TransitionManager()
    var state = GameState.RUNNING

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
        GlobalScope.async {
            backgroundManager.animateFromLeftToRight()
        }
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

            storyManager.modalManager.say(storyManager.currentNode.title, storyManager.currentNode.subtitle, 2000)

            delay(300)

            storyManager.modalManager.show()

            eventManager.emit(EventType.BEFORECHOICE)

            val choice = storyManager.waitForChoice()

            storyManager.modalManager.hide()

            println("Got choice: " + choice.choice)

            choice.callback(this)

            train.animateFromPositionToOutsideRight()

            if (train.driver.isDead()) {
                state = GameState.OVER
                break@mainLoop
            }

            transitionManager.show(choice.transition)

            eventManager.emit(EventType.AFTERCHOICE)

            println("Before goTo")

            storyManager.goTo(choice.to)

            println("Went to ${choice.to}")

            delay(500)

            backgroundManager.resetBackgroundPosition()

            backgroundManager.loadBackground(storyManager.currentNode.background)



            delay(2000)

            eventManager.emit(EventType.AFTEREVENT)

            println("Transition before hide")
            transitionManager.hide()
            println("Transition afterhide")
            println("End of loop $step")
            step++
        }

        println("Game ended")
        val endTransition = when (state) {
            GameState.OVER -> t(R.string.transition_over)
            GameState.WON -> t(R.string.transition_win)
            else -> t(R.string.transition_standard_1)
        }

        transitionManager.show(endTransition)

        delay(500)

        end()

        showMainMenu()
        delay(2000)

        musicManager.load(R.raw.home_sound, true)
        //backgroundManager.animateFromLeftToRight()

        transitionManager.hide()
    }

    suspend fun hideMainMenu() {
        mainMenuElements.options.post {
            mainMenuElements.options.visibility = View.GONE
            mainMenuElements.quitGame.visibility = View.GONE
            mainMenuElements.startGame.visibility = View.GONE
            mainMenuElements.title.visibility = View.GONE
            mainMenuElements.shopImage.visibility = View.GONE
            mainMenuElements.shopText.visibility = View.GONE
        }

        delay(300)
    }

    fun showMainMenu() {
        mainMenuElements.options.post {
            mainMenuElements.options.visibility = View.VISIBLE
            mainMenuElements.quitGame.visibility = View.VISIBLE
            mainMenuElements.startGame.visibility = View.VISIBLE
            mainMenuElements.title.visibility = View.VISIBLE
            mainMenuElements.shopImage.visibility = View.VISIBLE
            mainMenuElements.shopText.visibility = View.VISIBLE
        }
    }


    suspend fun end() {
        train.setTrainOutLeft()
        train.driver.reset()
        backgroundManager.resetBackgroundPosition()
        backgroundManager.loadBackground(R.drawable.background_tunnel)
        storyManager.reset()
        storyManager.goTo("G1")
    }
}