package com.example.tchoutchou.logic.events

import com.example.tchoutchou.logic.Game

enum class EventType {
    BEFOREEVENT,
    BEFORECHOICE,
    AFTERCHOICE,
    AFTEREVENT
}

typealias EventCallback = (Game) -> Unit

class EventManager(val game: Game) {
    val events = HashMap<EventType, MutableList<EventCallback>>()

    fun on(eventType: EventType, callback: EventCallback) {
        if (events[eventType] == null) {
            events[eventType] = mutableListOf()
        }

        events[eventType]?.add(callback)
    }

    fun disable(eventType: EventType, callback: EventCallback) {
        events[eventType]?.remove(callback)
    }

    fun emit(eventType: EventType) {
        for (event in (events[eventType] ?: mutableListOf())) {
            event(game)
        }
    }

}