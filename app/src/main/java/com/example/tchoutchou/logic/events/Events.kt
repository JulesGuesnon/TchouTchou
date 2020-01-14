package com.example.tchoutchou.logic.events

import com.example.tchoutchou.logic.Game

open class Events(var game: Game?) {

    fun registerEvent(eventType: EventType) {
         when (eventType) {
             EventType.BEFOREEVENT -> game?.eventManager?.on(eventType) {
                 beforeEvent(it)
             }

             EventType.BEFORECHOICE -> game?.eventManager?.on(eventType) {
                 beforeChoice(it)
             }
             EventType.AFTERCHOICE -> game?.eventManager?.on(eventType) {
                 afterChoice(it)
             }
             EventType.AFTEREVENT -> game?.eventManager?.on(eventType) {
                 afterEvent(it)
             }
         }
    }

    open fun beforeEvent(game: Game) {}

    open fun beforeChoice(game: Game) {}

    open fun afterChoice(game: Game) {}

    open fun afterEvent(game: Game) {}
}