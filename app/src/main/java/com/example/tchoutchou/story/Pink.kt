package com.example.tchoutchou.story

import com.example.tchoutchou.R
import com.example.tchoutchou.logic.character.Modifier
import com.example.tchoutchou.logic.character.Stats
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode

val pinkStory = arrayOf(
    StoryNode(
        "P1",
        t(R.string.P1_title),
        t(R.string.P1_subtitle),
        t(R.string.P1_sentence),
        R.drawable.background_destroyed,
        arrayOf(
            Choice(t(R.string.P1_choice_1), "P2") {
                // Attaque des goules
            },
            Choice(t(R.string.P1_choice_2), "P2") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    ),
    StoryNode(
        "P2",
        t(R.string.P2_title),
        t(R.string.P2_subtitle),
        t(R.string.P2_sentence),
        R.drawable.background_destroyed,
        arrayOf(
            Choice(t(R.string.P1_choice_1), "PU1"),
            Choice(t(R.string.P1_choice_2), "P3")
        )
    ),
    StoryNode(
        "P3",
        t(R.string.P1_title),
        t(R.string.P1_subtitle),
        t(R.string.P1_sentence),
        R.drawable.background_destroyed,
        arrayOf(
            Choice(t(R.string.P1_choice_1), "G6"),
            Choice(t(R.string.P1_choice_2), "death") {
                it.train.driver.setDead()
            }
        )
    )
)