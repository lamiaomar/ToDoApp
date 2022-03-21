package com.lamia.todoapp.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lamia.todoapp.R
import com.lamia.todoapp.model.Task
import com.lamia.todoapp.ui.ListState


@BindingAdapter("list")
fun bindList(
    recyclerView: RecyclerView,
    data: List<Task?>?,
) {
    val adapter = recyclerView.adapter as TaskAdapter
    adapter.submitList(data)
}


@BindingAdapter("listStatus")
fun bindStatus(
    statusImageView: ImageView,
//  statusTextView: TextView,
    status: ListState?
) {
    when (status) {
        ListState.EMPTY -> {
            statusImageView.visibility = View.VISIBLE
        //  statusTextView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.emplist)
        }

        ListState.NOT_EMPTY -> {
            statusImageView.visibility = View.INVISIBLE
        }
    }
}

@BindingAdapter("listStatusText")
fun bindStatus(
    statusTextView: TextView,
    status: ListState?
) {
    when (status) {
        ListState.EMPTY -> {
            statusTextView.visibility = View.VISIBLE
        }
        ListState.NOT_EMPTY -> {
            statusTextView.visibility = View.INVISIBLE
        }
    }

}
