package com.example.tchoutchou.story

import com.example.tchoutchou.R
import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.character.Modifier
import com.example.tchoutchou.logic.character.Stats
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode
import com.example.tchoutchou.utils.Resource
import kotlin.random.Random

fun t (id: Int) = Resource.context.getString(id)

val blueStory = arrayOf(
    StoryNode(
        "B1",
        t(R.string.B1_title),
        t(R.string.B1_subtitle),
        t(R.string.B1_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.B1_choice_1), "B2") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            },
            Choice(t(R.string.B1_choice_2), "G5") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    ),
    StoryNode(
        "B2",
        t(R.string.B2_title),
        t(R.string.B2_subtitle),
        t(R.string.B2_sentence),
        R.drawable.background_tunnel,
        arrayOf(
            Choice(t(R.string.B2_choice_1), "B3") {
                val deathChance = Random.nextDouble(0.0, 1.0)
                if (deathChance < 0.5) it.train.driver.setDead()
            },
            Choice(t(R.string.B2_choice_2), "B3"),
            Choice(t(R.string.B2_choice_3), "P1")
        )
    ),
    StoryNode(
        "B3",
        t(R.string.B3_title),
        t(R.string.B3_subtitle),
        t(R.string.B3_sentence),
        R.drawable.background_camp,
        arrayOf(
            Choice(t(R.string.B3_choice_1), "B4"),
            Choice(t(R.string.B3_choice_2), "G6") {
                val deathChance = Random.nextDouble(0.0, 1.0)
                if (deathChance < 0.5) it.train.driver.setDead()
            }
        )
    ),
    StoryNode(
        "B4",
        t(R.string.B4_title),
        t(R.string.B4_subtitle),
        t(R.string.B4_sentence),
        R.drawable.background_camp,
        arrayOf(
            Choice(t(R.string.B4_choice_1), "G6"),
            Choice(t(R.string.B4_choice_2), "B5") {
                val deathChance = Random.nextDouble(0.0, 1.0)
                if (deathChance < 0.5) it.train.driver.setDead()
            },
            Choice(t(R.string.B4_choice_3), "B5") {
                val deathChance = Random.nextDouble(0.0, 1.0)
                if (deathChance < 0.9) it.train.driver.setDead()
            }
        )
    ),
    StoryNode(
        "B5",
        t(R.string.B5_title),
        t(R.string.B5_subtitle),
        t(R.string.B5_sentence),
        R.drawable.background_camp,
        arrayOf(
            Choice(t(R.string.B5_choice_1), "G6") {
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
            Choice(t(R.string.B5_choice_2), "G6") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            }
        )
    )
)