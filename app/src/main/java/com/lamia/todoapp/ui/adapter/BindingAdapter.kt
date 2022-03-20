package com.lamia.todoapp.ui.adapter

import android.view.View
import android.widget.ImageView
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
    status: ListState?
) {

    when (status) {
        ListState.EMPTY-> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.emptylist1)
        }

        ListState.NOT_EMPTY -> {
            statusImageView.visibility = View.INVISIBLE
//            statusImageView.setImageResource(R.drawable.)
        }
    }

}
