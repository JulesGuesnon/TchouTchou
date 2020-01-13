package com.example.tchoutchou.story

import com.example.tchoutchou.logic.character.*
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode
import com.example.tchoutchou.logic.train.Upgrade
import com.example.tchoutchou.logic.train.Upgrades
import kotlin.random.Random

val greenStory = arrayOf(
    StoryNode(
        "G1",
        "Vous vous apprêtez à vous aventurer sur une ligne sans foi ni loi, êtes-vous sûr de bien vouloir sortir de la station protectrice ?",
        arrayOf(
            Choice("Oui je veux y aller ! C’est parti pour l’aventure !", "G2"),
            Choice("Non c’est bon, je veux rester chez moi et jouer à Minecraft...", "Y1")
        )
    ),
    StoryNode(
        "G2",
        "Vous avez pris la route et vous arrivez à la première station, le voyage s’est bien passé.\n" +
                "Vous vous reposez avant de reprendre la route et au loin vous entendez une voix... ",
        arrayOf(
            Choice("Je vais aller voir", "G3"),
            Choice("Je mets l’accélérateur à fond et je fonce dans l’tas", "R1")
        )
    ),
    StoryNode(
        "G3",
        "Vous découvrez un jeune homme sur les rails, il semble blessé. Vous vous demandez si vous devez l’aider ou pas.",
        arrayOf(
            Choice("Ici, pas de pitié", "G4") {
                it.train.driver.addModifier(Modifier(Stats.LUCK, -0.1, -1))
            },
            Choice("Bon allez, ça a pas l’air d’être un mauvais gars", "G4") {
                val character = Character(
                    "Bobby",
                    Statistics.Builder().build()
                )

                it.train.addPassenger(character)

                // alert("${character.name} ce join à la fête !")
            }
        )
    ),
    StoryNode(
        "G4",
        "Vous arrivez dans une station complètement déserte, pourtant vous voyez bien qu’il y a eu des habitants récemment.",
        arrayOf(
            Choice("J’explore, doit y avoir du bon loot", "G5"),
            Choice("Oulah, très peu pour moi", "R2")
        )
    ),
    StoryNode(
        "G5",
        "Au loin, des lumières, ce qui est plutôt rare dans ce coin. vous vous approchez doucement de ces lumières et vous vous rendez compte que ce sont des marchands.",
        arrayOf(
            Choice("Je vais les attaquer et me faire du stuff :3", "P1") {
                it.train.driver.addModifier(Modifier(Stats.LUCK, -0.1, -1))
                it.train.upgrade(Upgrade(Upgrades.WEAPON, 1.0))
            },
            Choice("J’ai fait école de commerce, je peux négocier un bon prix ", "G6") {
                // it.shop.augmenterde10%
            }
        )
    ),
    StoryNode(
        "G6",
        "Vous aviez entendu une légende avant de partir à l’aventure, celle d’un homme qui découpe ses proies en deux et laisse seulement la tête. En voyant devant vous un cimetière de têtes, vous vous demandez si elle serait quand-même pas un peu vraie cette légende...",
        arrayOf(
            Choice("Pfff, c’est des bêtises, jmets d’la musique et j’avance pépère", "death") {
                it.train.driver.setDead()
            },
            Choice("Je fuis très très vite", "G7") {
                val fleeValue = Random.nextDouble(0.0, 1.0)
                if (fleeValue < 0.2) {
                    it.train.driver.setDead()
                }
            }
        )
    ),
    StoryNode(
        "G7",
        "Le terminus, est-ce donc la terre promise ? Vous arrivez en station, seule les lumières d’urgences éclairent ce qu’il reste de cette station pleine de poussière, vous vous demandez si vous avez bien fait de partir.",
        arrayOf(
            Choice("J’aurais dû rester jouer à Minecraft", "death") {
                it.train.driver.setDead()
            },
            Choice("J’ai pu me faire un ami, et c’est ça l’important :)", "death") {
                it.train.driver.setDead()
            }
        )
    )
)