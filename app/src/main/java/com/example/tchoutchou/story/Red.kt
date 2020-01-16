package com.example.tchoutchou.story

import com.example.tchoutchou.R
import com.example.tchoutchou.constants.rascal1
import com.example.tchoutchou.constants.rascal2
import com.example.tchoutchou.constants.rascal3
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.character.Modifier
import com.example.tchoutchou.logic.character.Stats
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode

val redStory = arrayOf(
    StoryNode(
        "R1",
        t(R.string.R1_title),
        t(R.string.R1_subtitle),
        t(R.string.R1_sentence),
        R.drawable.background_camp,
        arrayOf(
            Choice(t(R.string.R1_choice_1), t(R.string.transition_passenger_buff),"R2") {
                val pascal = Character("Pascal")
                val chantal = Character("Chantal")

                if (it.train.canAddPassenger(2)) {
                    it.train.addPassenger(
                        pascal
                    )

                    it.train.addPassenger(
                        chantal
                    )
                } else if (it.train.canAddPassenger()) {
                    it.train.addPassenger(chantal)

                    // alert("ADIEU PASCAL :''''(")
                } else {
                    // alert("I'm sooooo full")
                }
            },
            Choice(t(R.string.R1_choice_2), t(R.string.transition_flee),"P1")
        ),
        arrayOf(
            rascal1,
            rascal2,
            rascal3
        )
    ),
    StoryNode(
        "R2",
        t(R.string.R2_title),
        t(R.string.R2_subtitle),
        t(R.string.R2_sentence),
        R.drawable.background_camp,
        arrayOf(
            Choice(t(R.string.R2_choice_2), t(R.string.transition_standard_3),"R3") {
                if (it.train.driver.money < 10) {
                    it.train.driver.setDead()
                } else {
                    it.train.driver.money -= 10
                }
            }
        ),
        arrayOf()
    ),
    StoryNode(
        "R3",
        t(R.string.R3_title),
        t(R.string.R3_subtitle),
        t(R.string.R3_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.R3_choice_1), t(R.string.transition_luck_nerf),"B1") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            },
            Choice(t(R.string.R3_choice_2), t(R.string.transition_luck_buff),"G6") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        ),
        arrayOf()
    )
)