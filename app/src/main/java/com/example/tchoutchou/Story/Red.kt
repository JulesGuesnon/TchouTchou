package com.example.tchoutchou.Story

import com.example.tchoutchou.logic.Character.Character
import com.example.tchoutchou.logic.Character.Modifier
import com.example.tchoutchou.logic.Character.Statistics
import com.example.tchoutchou.logic.Character.Stats
import com.example.tchoutchou.logic.Story.Choice
import com.example.tchoutchou.logic.Story.StoryNode

val redStory = arrayOf(
    StoryNode(
        "R1",
        "Dans l’adrénaline de la fuite, vous prenez un mauvais virage et vous atterissez dans la station des traîtres, bien connue pour accueillir toute sorte de rascal",
        arrayOf(
            Choice("Rascal ou pas, ça fait des hommes en plus", "R2") {
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
            Choice("Waaaaaah je m’arrête pas je mets les gaz ", "P1")
        )
    ),
    StoryNode(
        "R2",
        "ça fait quelques jours que vous êtes partis, et vous arrivez enfin à la première station civilisée de votre aventure. Vous en profitez pour vous arrêter et explorer.",
        arrayOf(
            Choice("Réparer ma locomotive", "R3") {
                if (it.train.driver.money < 10) {
                    it.train.driver.setDead()
                } else {
                    it.train.driver.money -= 10
                }
            }
        )
    ),
    StoryNode(
        "R3",
        "Un gros boum, puis un zblah, puis un zcraaaatch, un regard, un wagon disparu, {PASSENGER_NUMBER} morts.",
        arrayOf(
            Choice("Meh, 1 de perdu 10 de retrouvés", "B1") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            },
            Choice("Je vais les enterrer quand-même", "G6") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    )
)