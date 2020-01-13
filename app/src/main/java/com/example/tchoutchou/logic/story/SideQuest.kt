package com.example.tchoutchou.logic.story

import com.example.tchoutchou.logic.events.Event
import com.example.tchoutchou.logic.train.Train

class SideQuest (val startedAt: Int, val expiresAt: Int):
    Event {
    override fun beforeEvent(event: StoryNode, train: Train) {}

    override fun beforeChoice(event: StoryNode, train: Train) {}

    override fun afterChoice(event: StoryNode, train: Train) {}

    override fun afterEvent(event: StoryNode, train: Train) {}

}