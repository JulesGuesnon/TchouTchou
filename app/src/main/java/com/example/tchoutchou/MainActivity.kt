package com.example.tchoutchou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tchoutchou.constants.backgroundRatio
import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.MainMenuElements
import com.example.tchoutchou.logic.ModalElements
import com.example.tchoutchou.utils.Size
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBackgroundSize()

        game = Game(this)

        game.mainMenuElements = MainMenuElements(main_title, start_game, options, quit_game)
        game.storyManager.modalManager.setModalElements(this, ModalElements(modal, modal_sentence, choice_recycler))

        game.init()

        setHomeEvents()
    }

    override fun onPause() {
        super.onPause()
        game.musicManager.pause()
    }

    override fun onResume() {
        super.onResume()
        game.musicManager.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        game.musicManager.free()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    fun setHomeEvents() {
        start_game.setOnClickListener {
            game.hideMainMenu()
            GlobalScope.async {
                game.run()
            }
        }

        options.setOnClickListener {
            println("options")
        }

        quit_game.setOnClickListener {
            System.exit(0)
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    fun setBackgroundSize() {
        val windowHeight = Size.getHeightFromDisplay(windowManager.defaultDisplay)

        game_background.layoutParams.height = windowHeight
        game_background.layoutParams.width = windowHeight * backgroundRatio.toInt()
    }
}
