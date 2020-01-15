package com.example.tchoutchou.logic.story

import com.example.tchoutchou.logic.managers.ModalManager
import com.example.tchoutchou.story.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import java.lang.Exception

class StoryManager {

    private val history = mutableListOf<StoryNode>()
    private val choiceChannel = Channel<Choice>(1)
    val modalManager =
        ModalManager(choiceChannel)

    var currentNode
        get() = history[history.size - 1]
        set(value) {
            history.add(value)
        }

    val sideQuests = mutableListOf<SideQuest>()


    fun goTo(questId: StoryNodeId): Boolean {
        println("> Before death verification")
        if (questId == "death") {
            println("DEAD")
            return false
        }

        println("> Before setting currentNode")
        currentNode = parseQuestId(questId)

        println("> Before setting sentence")
        modalManager.setSentence(currentNode.sentence)
        println("> Before setting choices")
        modalManager.setChoices(currentNode.choices)
        return true
    }

    private fun parseQuestId(questId: StoryNodeId): StoryNode {
        val (color) = questId.split(Regex("([0-9]+)"))
        val id = questId.split(Regex("([A-Z]+)"))[1].toInt() - 1

        when (color) {
            "G" -> return greenStory[id]
            "B" -> return blueStory[id]
            "Y" -> return yellowStory[id]
            "PU" -> return purpleStory[id]
            "P" -> return pinkStory[id]
            "G" -> return greenStory[id]
            "R" -> return redStory[id]
        }

        throw Exception("Wtf was that provided id: $questId")
    }

    suspend fun waitForChoice(): Choice {
        while (choiceChannel.isEmpty) {
            delay(300)
        }
        return choiceChannel.receive()
    }
}