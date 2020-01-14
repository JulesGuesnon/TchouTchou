package com.example.tchoutchou

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.view.View
import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.character.Item
import com.example.tchoutchou.logic.character.Statistics
import com.example.tchoutchou.logic.story.StoryManager
import com.example.tchoutchou.logic.story.StoryUiElements
import com.example.tchoutchou.logic.train.Station
import com.example.tchoutchou.logic.train.Train
import com.example.tchoutchou.story.greenStory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    //lateinit var game : Game

    lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mp = MediaPlayer.create(this, R.raw.home_sound)
        mp.setOnPreparedListener {
            it.start()
            it.isLooping = true
            println("azehbquhfbsqiufb")
        }
    }

    override fun onPause() {
        super.onPause()
        mp.pause()
    }

    override fun onResume() {
        super.onResume()
        mp.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
        mp.release()
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

}
