package com.example.tchoutchou.story

import com.example.tchoutchou.R
import com.example.tchoutchou.constants.reaper
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode

val randomStory = arrayOf(
    StoryNode(
        "RAND1",
        t(R.string.RAND1_title),
        t(R.string.RAND1_subtitle),
        t(R.string.RAND1_sentence),
        R.drawable.background_reaper,
        arrayOf(
            Choice(t(R.string.RAND1_choice_1), t(R.string.transition_over), "death") {
                it.train.driver.setDead()
            }
        ),
        arrayOf(
            reaper
        )
    )
)