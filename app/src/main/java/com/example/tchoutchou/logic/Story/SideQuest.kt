package com.example.tchoutchou.logic.Story

import com.example.tchoutchou.logic.Events.Event
import com.example.tchoutchou.logic.Train.Train

class SideQuest (val startedAt: Int, val expiresAt: Int):
    Event {
    override fun beforeEvent(event: StoryNode, train: Train) {}

    override fun beforeChoice(event: StoryNode, train: Train) {}

    override fun afterChoice(event: StoryNode, train: Train) {}

    override fun afterEvent(event: StoryNode, train: Train) {}

}