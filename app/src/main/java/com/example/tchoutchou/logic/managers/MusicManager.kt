package com.example.tchoutchou.logic.managers

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import com.example.tchoutchou.R

class MusicManager(val context: Context, firstMusic: Int, playOnReady: Boolean = true) {
    private var mp = MediaPlayer.create(context, firstMusic)
    private var handler = Handler(context.mainLooper)

    var currentMusic = -1
    init {
        mp.setOnPreparedListener {
            if (playOnReady) {
                it.start()
                it.isLooping = true
            }
        }
    }

    fun load(music: Int, playOnReady: Boolean, isLooping: Boolean = true, volume: Float = 1f) {
        handler.post {
            if (music != currentMusic) {
                mp.release()
                mp = MediaPlayer.create(context, music)
                mp.setOnPreparedListener {
                    if (playOnReady) {
                        it.setVolume(volume, volume)
                        it.start()
                        it.isLooping = isLooping
                    }
                }
            } else if (music == currentMusic && playOnReady) {
                mp.setVolume(volume, volume)
                mp.reset()
                mp.start()
                mp.isLooping = isLooping
            }
        }
    }

    fun play() {
        handler.post {
            mp.start()
        }
    }

    fun pause() {
        handler.post {
            mp.pause()
        }
    }

    fun free() {
        handler.post {
            mp.release()
        }
    }
}