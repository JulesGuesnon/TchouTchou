package com.example.tchoutchou.logic.managers

import android.content.Context
import android.media.MediaPlayer
import com.example.tchoutchou.R

class MusicManager(val context: Context, firstMusic: Int, playOnReady: Boolean = true) {
    private var mp = MediaPlayer.create(context, firstMusic)

    init {
        mp.setOnPreparedListener {
            if (playOnReady) {
                it.start()
                it.isLooping = true
            }
        }
    }

    fun load(music: Int, playOnReady: Boolean) {
        mp.release()
        mp = MediaPlayer.create(context, music)
        mp.setOnPreparedListener {
            if (playOnReady) {
                it.start()
                it.isLooping = true
            }
        }
    }

    fun play() {
        mp.start()
    }

    fun pause() {
        mp.pause()
    }

    fun free() {
        mp.release()
    }
}