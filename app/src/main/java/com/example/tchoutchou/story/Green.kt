package com.example.tchoutchou.story

import com.example.tchoutchou.R
import com.example.tchoutchou.constants.seller
import com.example.tchoutchou.constants.theManWhoSuffer
import com.example.tchoutchou.logic.character.*
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode
import com.example.tchoutchou.logic.train.Upgrade
import com.example.tchoutchou.logic.train.Upgrades
import kotlin.random.Random

val greenStory = arrayOf(
    StoryNode(
        "G1",
        t(R.string.G1_title),
        t(R.string.G1_subtitle),
        t(R.string.G1_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.G1_choice_1), t(R.string.transition_standard_1),"G2"),
            Choice(t(R.string.G1_choice_2), t(R.string.transition_standard_2), "Y1")
        ),
        arrayOf()
    ),
    StoryNode(
        "G2",
        t(R.string.G2_title),
        t(R.string.G2_subtitle),
        t(R.string.G2_sentence),
        R.drawable.background_destroyed,
        arrayOf(
            Choice(t(R.string.G2_choice_1), t(R.string.transition_standard_2), "G3"),
            Choice(t(R.string.G2_choice_2), t(R.string.transition_flee), "R1")
        ),
        arrayOf()
    ),
    StoryNode(
        "G3",
        t(R.string.G3_title),
        t(R.string.G3_subtitle),
        t(R.string.G3_sentence),
        R.drawable.background_destroyed,
        arrayOf(
            Choice(t(R.string.G3_choice_1), t(R.string.transition_luck_nerf), "G4") {
                it.train.driver.addModifier(Modifier(Stats.LUCK, -0.1, -1))
            },
            Choice(t(R.string.G3_choice_2), t(R.string.transition_passenger_buff), "G4") {
                val character = Character(
                    "Bobby",
                    Statistics.Builder().build()
                ) {_, _ , _, _ ->}

                it.train.addPassenger(character)

                // alert("${character.name} ce join à la fête !")
            }
        ),
        arrayOf(
            theManWhoSuffer
        )
    ),
    StoryNode(
        "G4",
        t(R.string.G4_title),
        t(R.string.G4_subtitle),
        t(R.string.G4_sentence),
        R.drawable.background_camp,
        arrayOf(
            Choice(t(R.string.G4_choice_1), t(R.string.transition_standard_3), "G5"),
            Choice(t(R.string.G4_choice_2), t(R.string.transition_standard_4),"R2")
        ),
        arrayOf()
    ),
    StoryNode(
        "G5",
        t(R.string.G5_title),
        t(R.string.G5_subtitle),
        t(R.string.G5_sentence),
        R.drawable.background_camp,
        arrayOf(
            Choice(t(R.string.G5_choice_1), t(R.string.transition_luck_nerf),"P1") {
                it.train.driver.addModifier(Modifier(Stats.LUCK, -0.1, -1))
                it.train.upgrade(Upgrade(Upgrades.WEAPON, 1.0))
            },
            Choice(t(R.string.G5_choice_2), t(R.string.transition_standard_1),"G6") {
                // it.shop.augmenterde10%
            }
        ),
        arrayOf(
            seller
        )
    ),
    StoryNode(
        "G6",
        t(R.string.G6_title),
        t(R.string.G6_subtitle),
        t(R.string.G6_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.G6_choice_1), t(R.string.transition_standard_3),"RAND1"),
            Choice(t(R.string.G6_choice_2), t(R.string.transition_flee),"G7") {
                val fleeValue = Random.nextDouble(0.0, 1.0)
                if (fleeValue < 0.2) {
                    it.train.driver.setDead()
                }
            }
        ),
        arrayOf()
    ),
    StoryNode(
        "G7",
        t(R.string.G7_title),
        t(R.string.G7_subtitle),
        t(R.string.G7_sentence),
        R.drawable.background_terminus,
        arrayOf(
            Choice(t(R.string.G7_choice_1),  t(R.string.transition_over),"death") {
                it.train.driver.setDead()
            },
            Choice(t(R.string.G7_choice_2), t(R.string.transition_over),"death") {
                it.train.driver.setDead()
            }
        ),
        arrayOf()
    )
)