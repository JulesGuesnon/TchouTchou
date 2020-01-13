package com.example.tchoutchou.logic.Events

import com.example.tchoutchou.logic.Story.StoryNode
import com.example.tchoutchou.logic.Train.Train

interface Events {
    fun beforeEvent(event: StoryNode, train: Train)

    fun beforeChoice(event: StoryNode, train: Train)

    fun afterChoice(event: StoryNode, train: Train)

    fun afterEvent(event: StoryNode, train: Train)
}