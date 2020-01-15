package com.example.tchoutchou

import android.view.View
import android.widget.TextView
import com.example.tchoutchou.logic.story.Choice
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class ChoiceViewHolder(itemView: View): FastAdapter.ViewHolder<ChoiceItem>(itemView) {

    val choice: TextView

    init {
        choice = itemView.findViewById(R.id.row_choice_text)
    }

    override fun bindView(item: ChoiceItem, payloads: MutableList<Any>) {
        choice.text = "* " + item.choice.choice
    }

    override fun unbindView(item: ChoiceItem) {
        choice.text = null
    }
}

class ChoiceItem(val choice: Choice): AbstractItem<ChoiceViewHolder>() {
    override val layoutRes = R.layout.row_modal_choice
    override val type = 0
    override fun getViewHolder(v: View) = ChoiceViewHolder(v)
}