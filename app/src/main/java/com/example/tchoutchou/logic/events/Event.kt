package com.example.tchoutchou.logic.events

import com.example.tchoutchou.logic.Game


class Event<T: Events>: Events {
    val events = mutableListOf<T>()

    fun registerEvents(obj: T) {
        events.add(obj)
    }

    override fun beforeEvent(game: Game) {
        for (gameEvent in events) {
            gameEvent.beforeEvent(game)
        }
    }

    override fun beforeChoice(game: Game) {
        for (gameEvent in events) {
            gameEvent.beforeChoice(game)
        }
    }

    override fun afterChoice(game: Game) {
        for (gameEvent in events) {
            gameEvent.afterChoice(game)
        }
    }

    override fun afterEvent(game: Game) {
        for (gameEvent in events) {
            gameEvent.afterEvent(game)
        }
    }

}