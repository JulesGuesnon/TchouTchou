package com.example.tchoutchou.logic.story

class StoryManager () {
    val history = mutableListOf<StoryNode>()
    var currentNode
        get() = history[history.size - 1]
        set(value) {
            history.add(value)
        }

    val sideQuests = mutableListOf<SideQuest>()

    fun goTo(questId: Int) {
        //currentNode = quests[questId]
    }
}