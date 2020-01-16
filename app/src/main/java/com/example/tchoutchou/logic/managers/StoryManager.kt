package com.example.tchoutchou.logic.managers

import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.SideQuest
import com.example.tchoutchou.logic.story.StoryNode
import com.example.tchoutchou.logic.story.StoryNodeId
import com.example.tchoutchou.story.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import java.lang.Exception

class StoryManager(val game: Game, val soundEffectManager: MusicManager) {

    private var history = mutableListOf<StoryNode>()
    private val choiceChannel = Channel<Choice>(1)
    val modalManager =
        ModalManager(choiceChannel, soundEffectManager)

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
        modalManager.setSentence(
            replaceStringConstant(currentNode.sentence)
        )
        println("> Before setting choices")
        modalManager.setChoices(
            currentNode.choices
        )
        println("> After choices")
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

    fun reset() {
        history = mutableListOf()
    }

    fun replaceStringConstant(input: String): String {
        return input
            .replace("{PASSENGER_NUMBER}", game.train.countPassengers().toString())
    }
}