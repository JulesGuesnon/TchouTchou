package com.example.tchoutchou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tchoutchou.constants.backgroundRatio
import com.example.tchoutchou.constants.reaper
import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.elements.MainMenuElements
import com.example.tchoutchou.logic.elements.ModalElements
import com.example.tchoutchou.logic.elements.StationElements
import com.example.tchoutchou.logic.elements.TransitionElements
import com.example.tchoutchou.logic.train.TrainElements
import com.example.tchoutchou.utils.Size
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class GameFragment(val game: Game): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onStart() {
        super.onStart()
        setBackgroundSize()

        val display = activity?.windowManager?.defaultDisplay
        val context = activity?.baseContext

        if (display == null || context == null) return

        game.mainMenuElements =
            MainMenuElements(
                main_title,
                start_game,
                options,
                quit_game,
                shop_text,
                shop_image,
                main_layout
            )
        game.storyManager.modalManager.setModalElements(context,
            ModalElements(
                modal,
                modal_sentence,
                choice_recycler,
                modal_title,
                modal_subtitle
            )
        )
        game.backgroundManager.setElement(display, game_background)
        game.transitionManager.transitionElements = TransitionElements(game_transition)

        game.init()

        game.train.setElements(display, TrainElements(game_train, train_smoke))
        game.train.driver.display = display

        game.train.loadTexture()
        setHomeEvents()
        setShopEvents()
    }

    fun setHomeEvents() {
        start_game.setOnClickListener {
            game.soundEffectManager.load(R.raw.sound_effect_click_validation, true, false)
            GlobalScope.async {
                game.run()
            }
        }

        options.setOnClickListener {
            game.soundEffectManager.load(R.raw.sound_effect_click_validation, true, false)
            println("options")
        }

        quit_game.setOnClickListener {
            game.soundEffectManager.load(R.raw.sound_effect_click_validation, true, false)
            System.exit(0)
        }
    }

    fun setShopEvents() {
        shop_image.setOnClickListener {
            val supportFragmentManager = activity?.supportFragmentManager

            if (supportFragmentManager != null) {
                game.soundEffectManager.load(R.raw.sound_effect_click_validation, true, false)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ShopFragment.newInstance(game))
                .addToBackStack("tchou")
                .commit()

                game.musicManager.load(R.raw.sound_shop, true, true, 1f)
            }
        }

        shop_text.setOnClickListener {
            val supportFragmentManager = activity?.supportFragmentManager

            if (supportFragmentManager != null) {
                game.soundEffectManager.load(R.raw.sound_effect_click_validation, true, false)
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, ShopFragment.newInstance(game))
                    .addToBackStack("tchou")
                    .commit()

                game.musicManager.load(R.raw.sound_shop, true, true, 1f)
            }
        }
    }

    fun setBackgroundSize() {
        val display = activity?.windowManager?.defaultDisplay
        if (display != null) {
            val windowHeight = Size.getHeightFromDisplay(display)

            game_background.layoutParams.height = windowHeight
            game_background.layoutParams.width = windowHeight * backgroundRatio.toInt()
        }
    }

    companion object {
        fun newInstance(game: Game) = GameFragment(game)
    }
}