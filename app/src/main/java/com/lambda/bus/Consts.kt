package com.lambda.bus

import android.graphics.Color
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat

const val TAG = "Event"

fun append(container: LinearLayoutCompat, text: String) {
    val itemView = LayoutInflater.from(container.context).inflate(
        android.R.layout.simple_list_item_1, container, false
    )
    (itemView as TextView).apply {
        this.setTextColor(Color.BLACK)
        this.text = text
    }
    container.addView(itemView)
}