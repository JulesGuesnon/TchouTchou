package com.example.tchoutchou

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tchoutchou.logic.Character
import com.example.tchoutchou.logic.Inventory
import com.example.tchoutchou.logic.Item
import com.example.tchoutchou.logic.Statistics

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val character = Character(
            "Billy",
            Statistics.Builder().food(10.0).life(10.0).luck(10.0).strength(10.0).build()
        )

        character.stats.print()
        character.inventory.add(Item(
            false,
            "Lamp",
            "Nice in the dark",
            Statistics.Builder().strength(10.0).build()
        ))

        character.computeItemsBonuses()

        character.stats.print()

        character.inventory.remove(0)

        character.computeItemsBonuses()

        character.stats.print()
    }
}
