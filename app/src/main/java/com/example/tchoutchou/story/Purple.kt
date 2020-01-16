package com.example.tchoutchou.story

import com.example.tchoutchou.R
import com.example.tchoutchou.logic.character.Modifier
import com.example.tchoutchou.logic.character.Stats
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode

val purpleStory = arrayOf(
    StoryNode(
        "PU1",
        t(R.string.PU1_title),
        t(R.string.PU1_subtitle),
        t(R.string.PU1_sentence),
        R.drawable.background_destroyed,
        arrayOf(
            Choice(t(R.string.PU1_choice_1), "G6") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            },
            Choice(t(R.string.PU1_choice_2), "G6") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            }
        )
    )
)