package com.example.tchoutchou.logic

import android.content.Context
import android.os.Handler
import android.view.Display
import android.view.View
import com.example.tchoutchou.R
import com.example.tchoutchou.logic.api.ApiService
import com.example.tchoutchou.logic.api.StationsModel
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.elements.MainMenuElements
import com.example.tchoutchou.logic.events.EventManager
import com.example.tchoutchou.logic.events.EventType
import com.example.tchoutchou.logic.managers.BackgroundManager
import com.example.tchoutchou.logic.managers.MusicManager
import com.example.tchoutchou.logic.managers.TransitionManager
import com.example.tchoutchou.logic.managers.StoryManager
import com.example.tchoutchou.logic.train.Station
import com.example.tchoutchou.logic.train.Train
import com.example.tchoutchou.story.t
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class GameState {
    RUNNING,
    WON,
    OVER
}

class Game(val context: Context, val display: Display) {

    val eventManager = EventManager(this)
    val musicManager = MusicManager(
        context,
        R.raw.sound_home
    )
    val soundEffectManager = MusicManager(context, R.raw.sound_effect_modal_pop, false)

    val storyManager = StoryManager(this, soundEffectManager)

    val backgroundManager = BackgroundManager()
    val transitionManager = TransitionManager()
    var state = GameState.RUNNING
    lateinit var stations: List<Station>

    lateinit var mainMenuElements: MainMenuElements
    lateinit var train: Train
    var step = 0

    fun init() {
        train = Train
            .Builder()
            .driver(
                Character("Billy"){_, _, _, _ -> }
            )
            .currentStation(Station("Montparnasse"))
            .build()

        train.driver.game = this

        storyManager.goTo("G1")
        GlobalScope.async {
            backgroundManager.animateFromLeftToRight()
        }

        ApiService.create().getStation().enqueue(object: Callback<StationsModel> {
            override fun onFailure(call: Call<StationsModel>, t: Throwable) {
                println("OOOOOOF")
                println(t)
            }

            override fun onResponse(call: Call<StationsModel>, response: Response<StationsModel>) {
                val body = response.body()
                if (body == null) return
                stations = body.stopAreas.map {
                    Station(it.name)
                }
                train.currentStation = stations[0]
            }

        })
    }

    suspend fun animateTrainArrive() {
        train.animateFromOutsideToLeft(false)
        backgroundManager.animateOnTrainArriving()
    }

    suspend fun run() {
        musicManager.load(R.raw.sound_mission, playOnReady = true, isLooping = true, volume = 0.7f)

        hideMainMenu()

        train.animateFromOutsideLeftToOutsideRight()
        mainLoop@ while (train.driver.isAlive()) {
            println("Begin Game Loop $step")
            train.currentStation = stations[step % stations.size]

            animateTrainArrive()

            train.pauseAnimation()

            train.stationManager.show(train.currentStation.name)

            storyManager.currentNode.characters.forEachIndexed {i, it ->
                val characterWidth = it.gifImageView?.layoutParams?.width ?: 1
                it.animateEnter(
                    - (i * 0.3 * characterWidth).toFloat()
                )
            }

            eventManager.emit(EventType.BEFOREEVENT)

            storyManager.modalManager.say(storyManager.currentNode.title, storyManager.currentNode.subtitle, 3000)

            delay(300)

            storyManager.modalManager.show()

            eventManager.emit(EventType.BEFORECHOICE)

            val choice = storyManager.waitForChoice()

            storyManager.modalManager.hide()

            println("Got choice: " + choice.choice)

            choice.callback(this)

            train.stationManager.hide()

            train.runAnimation()

            train.animateFromPositionToOutsideRight()

            if (train.driver.isDead()) {
                state = GameState.OVER
                storyManager.currentNode.characters.forEach {
                    it.deleteCharacterView(mainMenuElements.constraintLayout)
                }
                break@mainLoop
            }

            transitionManager.show(choice.transition)

            delay(500)

            eventManager.emit(EventType.AFTERCHOICE)

            storyManager.currentNode.characters.forEach {
                it.deleteCharacterView(mainMenuElements.constraintLayout)
            }

            storyManager.goTo(choice.to)

            println("Went to ${choice.to}")

            delay(500)

            backgroundManager.resetBackgroundPosition()

            backgroundManager.loadBackground(storyManager.currentNode.background)


            storyManager.currentNode.characters.forEach {
                it.display = display
                it.constraintLayout = mainMenuElements.constraintLayout
                it.context = context

                it.loadCharacterOutsideRight(context, mainMenuElements.constraintLayout)
            }

            delay(2000)

            eventManager.emit(EventType.AFTEREVENT)

            transitionManager.hide()
            println("End of loop $step")
            step++
        }

        println("Game ended")
        val endTransition = when (state) {
            GameState.OVER -> t(R.string.transition_over)
            GameState.WON -> t(R.string.transition_win)
            else -> t(R.string.transition_standard_1)
        }

        train.stationManager.hide()
        transitionManager.show(endTransition)

        delay(500)

        end()

        showMainMenu()
        delay(2000)

        musicManager.load(R.raw.sound_home, true)
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