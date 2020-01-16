package com.example.tchoutchou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tchoutchou.constants.backgroundRatio
import com.example.tchoutchou.logic.Game
import com.example.tchoutchou.logic.elements.MainMenuElements
import com.example.tchoutchou.logic.elements.ModalElements
import com.example.tchoutchou.logic.elements.TransitionElements
import com.example.tchoutchou.logic.train.TrainElements
import com.example.tchoutchou.utils.Size
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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
                shop_image
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

        setHomeEvents()
        setShopEvents()
    }

    fun setHomeEvents() {
        start_game.setOnClickListener {
            GlobalScope.async {
                game.run()
            }
        }

        options.setOnClickListener {
            println("options")
        }

        quit_game.setOnClickListener {
            System.exit(0)
        }
    }

    fun setShopEvents() {
        shop_image.setOnClickListener {
            val supportFragmentManager = activity?.supportFragmentManager

            if (supportFragmentManager != null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ShopFragment.newInstance())
                .addToBackStack("tchou")
                .commit()
            }
        }

        shop_text.setOnClickListener {
            val supportFragmentManager = activity?.supportFragmentManager

            if (supportFragmentManager != null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, ShopFragment.newInstance())
                    .addToBackStack("tchou")
                    .commit()
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