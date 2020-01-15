package com.example.tchoutchou.logic.elements

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

data class ModalElements(val modal: ConstraintLayout, val modalSentence: TextView, val choiceRecycler: RecyclerView, val modalTitle: TextView, val modalSubtitle: TextView)