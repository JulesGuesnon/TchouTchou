package com.example.tchoutchou.story

import com.example.tchoutchou.R
import com.example.tchoutchou.logic.character.Modifier
import com.example.tchoutchou.logic.character.Stats
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode
import com.example.tchoutchou.logic.train.Upgrade
import com.example.tchoutchou.logic.train.Upgrades

val yellowStory = arrayOf(
    StoryNode(
        "Y1",
        t(R.string.Y1_title),
        t(R.string.Y1_subtitle),
        t(R.string.Y1_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.Y1_choice_1), "Y2") {
                it.train.upgrade(
                    Upgrade(Upgrades.RAILCAR, 1.0)
                )
            },
            Choice(t(R.string.Y1_choice_2), "Y1") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    ),
    StoryNode(
        "Y2",
        t(R.string.Y2_title),
        t(R.string.Y2_subtitle),
        t(R.string.Y2_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.Y2_choice_1), "Y3") {
                it.train.upgrade(
                    Upgrade(Upgrades.WEAPON, 1.0)
                )
            },
            Choice(t(R.string.Y2_choice_2), "Y3") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    ),
    StoryNode(
        "Y3",
        t(R.string.Y3_title),
        t(R.string.Y3_subtitle),
        t(R.string.Y3_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.Y3_choice_1), "Y4"),
            Choice(t(R.string.Y3_choice_2), "Y4")
        )
    ),
    StoryNode(
        "Y4",
        t(R.string.Y4_title),
        t(R.string.Y4_subtitle),
        t(R.string.Y4_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.Y4_choice_1), "Y5"),
            Choice(t(R.string.Y4_choice_2), "death") {
                it.train.driver.setDead()
            }
        )
    ),
    StoryNode(
        "Y5",
        t(R.string.Y5_title),
        t(R.string.Y5_subtitle),
        t(R.string.Y5_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.Y5_choice_1), "death") {
                it.train.driver.setDead()
            },
            Choice(t(R.string.Y5_choice_1), "G1")
        )
    )
)