package com.lamia.todoapp.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lamia.todoapp.model.Task


@BindingAdapter("list")
fun bindList(
    recyclerView: RecyclerView,
    data: List<Task?>?,
) {
    val adapter = recyclerView.adapter as TaskAdapter
    adapter.submitList(data)
}

//@BindingAdapter("list")
//fun bindList(
//    recyclerView: RecyclerView,
//    data: List<Task>?,
//) {
//    val adapter = recyclerView.adapter as TaskAdapter
//    adapter.submitList(data)
//}