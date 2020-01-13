package com.example.tchoutchou.story

import com.example.tchoutchou.logic.character.Modifier
import com.example.tchoutchou.logic.character.Stats
import com.example.tchoutchou.logic.story.Choice
import com.example.tchoutchou.logic.story.StoryNode
import com.example.tchoutchou.logic.train.Upgrade
import com.example.tchoutchou.logic.train.Upgrades

val yellowStory = arrayOf(
    StoryNode(
        "Y1",
        "Minecraft.exe... Nouveau monde... génération normale... cheat : activé... on peut commencer.",
        arrayOf(
            Choice("Couper du bois", "Y2") {
                it.train.upgrade(
                    Upgrade(Upgrades.RAILCAR, 1.0)
                )
            },
            Choice("Explorer le monde", "Y1") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    ),
    StoryNode(
        "Y2",
        "Vous arrivez devant une grotte, vous entendez un squelette avancer...",
        arrayOf(
            Choice("Faire une épée en bois et attaquer", "Y3") {
                it.train.upgrade(
                    Upgrade(Upgrades.WEAPON, 1.0)
                )
            },
            Choice("Continuer d’explorer la surface", "Y3") {
                it.train.driver.addModifier(
                    Modifier(Stats.LUCK, 0.1, -1)
                )
            }
        )
    ),
    StoryNode(
        "Y3",
        "Une petite plage, quelques arbres et une montagne au loin",
        arrayOf(
            Choice("Parfait pour ma maison", "Y4"),
            Choice("Je peux mieux trouver", "Y4")
        )
    ),
    StoryNode(
        "Y4",
        "Un bruit au loin, puis un peu plus près, puis encore plus près, c’est comme si c’était réel...",
        arrayOf(
            Choice("J’enlève mon casque et je me retourne", "Y5"),
            Choice("Meh, sûrement un truc dans les canalisations", "death") {
                it.train.driver.setDead()
            }
        )
    ),
    StoryNode(
        "Y5",
        "Vous vous retournez et vous voyez devant vous une goule.",
        arrayOf(
            Choice("Je ne regrette rien.", "death") {
                it.train.driver.setDead()
            },
            Choice("Vous prenez la tête de la goule et vous l’éclatez sur l’ordinateur. Maintenant sans ordi, vous vous décidez à partir à l’aventure.", "G1")
        )
    )
)