package com.lamia.todoapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lamia.todoapp.databinding.TaskBinding
import com.lamia.todoapp.model.Task

class TaskAdapter (

): ListAdapter<Task, TaskAdapter.TaskViewHolder>(DiffCallback){

//    val delete: (task: Task) -> Unit
    class TaskViewHolder(
        private var binding:
        TaskBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.result = task
//            binding.title.text = task.title
//            binding.description.text = task.description
            binding.executePendingBindings()
        }

//        val addbutton : Button = binding.
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(
            oldItem: Task,
            newItem: Task
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: Task,
            newItem: Task
        ): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val bookPhoto = getItem(position)
        holder.bind(bookPhoto)

//        holder

//        holder.bookThumb.setOnClickListener {
//            val action =
//                BookShelfFragmentDirections.actionBookShelfFragmentToDetailsUserBookFragment(
//                    position
//                )
//            holder.bookThumb.findNavController().navigate(action)
//        }
//
//        holder.deleteBook.setOnClickListener {
//            delete(bookPhoto)
//            notifyItemRemoved(position)
//        }
//
//        holder.shareBook.setOnClickListener {
//            share(bookPhoto)
//
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    }
}