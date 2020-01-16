package com.example.tchoutchou.logic.managers

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tchoutchou.ChoiceItem
import com.example.tchoutchou.R
import com.example.tchoutchou.logic.elements.ModalElements
import com.example.tchoutchou.logic.story.Choice
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay


class ModalManager (val choiceChannel: Channel<Choice>, val soundEffectManager: MusicManager) {
    private lateinit var elements: ModalElements
    lateinit var choiceItemAdapter: ItemAdapter<ChoiceItem>
    val mainHandler = Handler(Looper.getMainLooper())

    suspend fun hide() {
        elements.modal.post {
            elements
                .modal
                .animate()
                .setDuration(500)
                .alpha(0f)
                .withEndAction {
                    elements.modal.visibility = View.GONE
                }
        }
        delay(500)
    }

    suspend fun show() {
        soundEffectManager.load(R.raw.sound_effect_modal_pop, true, false)
        elements.modal.post {
            elements.modal.visibility = View.VISIBLE

            elements
                .modal
                .animate()
                .setDuration(500)
                .alpha(1f)
        }

        delay(500)
    }

    suspend fun say(title: String, subtitle: String, delayTime: Long) {
        elements.modal.post {
            elements.modalSentence.visibility = View.GONE
            elements.choiceRecycler.visibility = View.GONE
        }

        elements.modalTitle.post {
            elements.modalTitle.text = "* " + title + " * "
            elements.modalSubtitle.text = subtitle
            elements.modalTitle.visibility = View.VISIBLE
            elements.modalSubtitle.visibility = View.VISIBLE
        }

        delay(100)
        show()
        delay(delayTime)
        hide()

        elements.modalTitle.post {
            elements.modalTitle.text = null
            elements.modalSubtitle.text = null
            elements.modalTitle.visibility = View.GONE
            elements.modalSubtitle.visibility = View.GONE
            elements.modalSentence.visibility = View.VISIBLE
            elements.choiceRecycler.visibility = View.VISIBLE
        }
    }

    fun setModalElements(context: Context, elements: ModalElements) {
        this.elements = elements
        val layout = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        choiceItemAdapter= ItemAdapter()
        val fastAdapter = FastAdapter.with(choiceItemAdapter)

        fastAdapter.onClickListener = { _, _, item, _ ->
            GlobalScope.async {
                soundEffectManager.load(R.raw.sound_effect_click_validation, true, false, 1f)
                choiceChannel.send(item.choice)
                mainHandler.post {
                    choiceItemAdapter.clear()
                }
            }
            false
        }

        this.elements.choiceRecycler.layoutManager = layout
        this.elements.choiceRecycler.adapter = fastAdapter

    }

    fun setSentence(text: String) {
            elements.modalSentence.post {
                elements.modalSentence.text = text
            }
    }

    fun setChoices(choices: Array<Choice>) {
        mainHandler.post {
            choiceItemAdapter.add(choices.map { ChoiceItem(it) })
        }
    }
}