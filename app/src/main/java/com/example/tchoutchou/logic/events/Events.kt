package com.example.tchoutchou.logic.events

import com.example.tchoutchou.logic.Game

interface Events {
    fun beforeEvent(game: Game)

    fun beforeChoice(game: Game)

    fun afterChoice(game: Game)

    fun afterEvent(game: Game)
}