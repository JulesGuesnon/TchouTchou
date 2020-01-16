package com.example.tchoutchou

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tchoutchou.constants.backgroundRatio
import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.api.ApiService
import com.example.tchoutchou.logic.api.StationsModel
import com.example.tchoutchou.logic.elements.MainMenuElements
import com.example.tchoutchou.logic.elements.ModalElements
import com.example.tchoutchou.logic.elements.TransitionElements
import com.example.tchoutchou.logic.train.TrainElements
import com.example.tchoutchou.utils.Size
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var game : Game


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        game = Game(this, windowManager.defaultDisplay)

        supportFragmentManager
            .beginTransaction()
            .add(fragment_container.id, GameFragment.newInstance(game))
            .addToBackStack("tchou")
            .commit()
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

    fun getContext(): Context {
        return this
    }
}
