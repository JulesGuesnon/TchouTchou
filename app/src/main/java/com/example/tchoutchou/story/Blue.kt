package com.example.tchoutchou.story

import com.example.tchoutchou.logic.character.Character
import com.example.tchoutchou.logic.character.Modifier
import com.example.tchoutchou.logic.character.Stats
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode
import kotlin.random.Random

val blueStory = arrayOf(
    StoryNode(
        "B1",
        "En jetant le corps des loustiques dans un fossé près de la voie, vous remarquez qu’ils ont tous les deux le même symbole tatoué sur le bras",
        arrayOf(
            Choice("Il est pas très beau", "B2") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            },
            Choice("Je devrais peut-être les enterrer quand-même", "G5") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    ),
    StoryNode(
        "B2",
        "Vous retournez dans votre train lorsque des bruits viennent éveiller votre curiosité. Ces bruits vous font penser à des bruits d’animaux et vous croyez entendre des rires.",
        arrayOf(
            Choice("Je vais jeter un coup d’oeil, ça doit sûrement être des rats.", "B3") {
                val deathChance = Random.nextDouble(0.0, 1.0)
                if (deathChance < 0.5) it.train.driver.setDead()
            },
            Choice("Je ferai mieux de sortir mes armes", "B3"),
            Choice("J’reconnais ça, c’pas cool, j’mets les gaz", "P1")
        )
    ),
    StoryNode(
        "B3",
        "Et voilà, vous êtes encerclé, pas la meilleure situation, pas la pire non plus, vous auriez pu tomber sur le faucheur, bon, qu’est-ce que vous allez faire ?",
        arrayOf(
            Choice("Les Rascals, pas de ça chez moi.", "B4"),
            Choice("La meilleure attaque c’est la fuite.", "G6") {
                val deathChance = Random.nextDouble(0.0, 1.0)
                if (deathChance < 0.5) it.train.driver.setDead()
            }
        )
    ),
    StoryNode(
        "B4",
        "Se retrouver devant une centaine de Rascals, c’est pas rien, et clairement y a sûrement des choses plus tranquilles à faire. ",
        arrayOf(
            Choice("Utiliser l’arme de niveau 4", "G6"),
            Choice("Tiens, un tonneau rouge au milieu des Rascals, si je tirai dessus...", "B5") {
                val deathChance = Random.nextDouble(0.0, 1.0)
                if (deathChance < 0.5) it.train.driver.setDead()
            },
            Choice("Vous commencez à aligner les têtes une par une, même si ça doit prendre une heure.", "B5") {
                val deathChance = Random.nextDouble(0.0, 1.0)
                if (deathChance < 0.9) it.train.driver.setDead()
            }
        )
    ),
    StoryNode(
        "B5",
        "Les génocides, c’est pas une bonne chose, sauf là. Malheureusement, il reste quelques survivants...",
        arrayOf(
            Choice("Je suis un philanthrope avant tout.", "G6") {
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
            Choice("Sont résistants ceux-là, bonne occasion pour vider mon chargeur", "G6") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, -0.1, -1)
                )
            }
        )
    )
)