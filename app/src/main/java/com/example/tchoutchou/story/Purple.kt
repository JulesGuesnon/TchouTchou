package com.example.tchoutchou.story

import com.example.tchoutchou.logic.character.Modifier
import com.example.tchoutchou.logic.character.Stats
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode

val purpleStory = arrayOf(
    StoryNode(
        "PU1",
        "Bon, c’est bête de l’avoir tué comme ça, mais c’est pas la fin du monde, vous avez des outils pour réparer la locomotive maintenant, non ?",
        arrayOf(
            Choice("Réparer la locomotive", "G6") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            },
            Choice("Faire un in memoriam pour le soldat tombé et réparer la locomotive", "G6") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            }
        )
    )
)