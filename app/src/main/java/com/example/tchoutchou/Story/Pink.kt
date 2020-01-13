package com.example.tchoutchou.Story

import com.example.tchoutchou.logic.Character.Modifier
import com.example.tchoutchou.logic.Character.Stats
import com.example.tchoutchou.logic.Story.Choice
import com.example.tchoutchou.logic.Story.StoryNode

val pinkStory = arrayOf(
    StoryNode(
        "P1",
        "Eh oui, vous êtes bloqué, vous faites moins le malin d’un coup. Ce serait quand même pas top de se faire manger par les goules ",
        arrayOf(
            Choice("J’en fais de la bouillie ", "P2") {
                // Attaque des goules
            },
            Choice("Oui non pas ouf en effet", "P2") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    ),
    StoryNode(
        "P2",
        "Vous vous remettez de vos émotions lorsque un petit homme vous susurre à l’oreille “tout va bien ?”. Vous êtes surpris, ce petit homme à une salopette et plein d’outils.",
        arrayOf(
            Choice("Vous avez le mauvais reflexe de lui mettre un coup de coude, il tombe raide mort", "PU1"),
            Choice("Tu susurres souvent à l’oreille des gens comme ça ?", "P3")
        )
    ),
    StoryNode(
        "P3",
        "Il s’excuse de vous avoir surpris, pour se faire pardonner, il vous propose un réparation gratuite.",
        arrayOf(
            Choice("On dit pas non quand c’est gratuit", "G6"),
            Choice("Vous dites non même si vous savez que vous allez vous faire manger par les goules", "death") {
                it.train.driver.setDead()
            }
        )
    )
)