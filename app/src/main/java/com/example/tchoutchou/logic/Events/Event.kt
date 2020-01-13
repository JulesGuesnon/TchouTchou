package com.example.tchoutchou.logic.Events

import com.example.tchoutchou.logic.Story.StoryNode
import com.example.tchoutchou.logic.Train.Train



class Event<T: Events>: Events {
    val events = mutableListOf<T>()

    fun registerEvents(obj: T) {
        events.add(obj)
    }

    override fun beforeEvent(event: StoryNode, train: Train) {
        for (gameEvent in events) {
            gameEvent.beforeEvent(event, train)
        }
    }

    override fun beforeChoice(event: StoryNode, train: Train) {
        for (gameEvent in events) {
            gameEvent.beforeChoice(event, train)
        }
    }

    override fun afterChoice(event: StoryNode, train: Train) {
        for (gameEvent in events) {
            gameEvent.afterChoice(event, train)
        }
    }

    override fun afterEvent(event: StoryNode, train: Train) {
        for (gameEvent in events) {
            gameEvent.afterEvent(event, train)
        }
    }

}