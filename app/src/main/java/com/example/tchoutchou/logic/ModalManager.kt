package com.example.tchoutchou.logic

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tchoutchou.ChoiceItem
import com.example.tchoutchou.logic.story.Choice
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay


class ModalManager (val choiceChannel: Channel<Choice>) {
    private lateinit var elements: ModalElements
    lateinit var choiceItemAdapter: ItemAdapter<ChoiceItem>

    fun hide() {
        elements.modal.post {
            elements.modal.visibility = View.GONE
        }
    }

    fun show() {
        elements.modal.post {
            elements.modal.visibility = View.VISIBLE
        }
    }

    suspend fun say(text: String, delayTime: Long) {
        elements.modalSentence.post {
            elements.modalSentence.text = text
        }
        show()
        delay(delayTime)
        hide()
    }

    fun setModalElements(context: Context, elements: ModalElements) {
        this.elements = elements
        val layout = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        choiceItemAdapter= ItemAdapter<ChoiceItem>()
        val fastAdapter = FastAdapter.with(choiceItemAdapter)

        fastAdapter.onClickListener = { _, _, item, _ ->
            GlobalScope.async {
                choiceChannel.send(item.choice)
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
        println("> ModalManager: Before clear")
        choiceItemAdapter.clear()
        println("> ModalManager: After clean")
        println("> ModalManager: Before add")
        choiceItemAdapter.add(choices.map { ChoiceItem(it) })
        println("> ModalManager: After add")
    }
}