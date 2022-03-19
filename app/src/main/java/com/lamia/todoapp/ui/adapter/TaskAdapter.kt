package com.lamia.todoapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lamia.todoapp.databinding.TaskBinding
import com.lamia.todoapp.model.Task
import com.lamia.todoapp.ui.TaskFragmentDirections

class TaskAdapter (
    val delete: (task: Task) -> Unit
): ListAdapter<Task, TaskAdapter.TaskViewHolder>(DiffCallback){

    class TaskViewHolder(
        private var binding:
        TaskBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.result = task
           val id =  task.id
            binding.executePendingBindings()
        }
    val task: CardView = binding.taskCard
    val delete: ImageView = binding.delete

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
        val task = getItem(position)
        holder.bind(task)

        holder.task.setOnClickListener {
            val action =
                TaskFragmentDirections.actionTaskFragmentToTaskDetailFragment(task.id!!)
            holder.task.findNavController().navigate(action)
        }

        holder.delete.setOnClickListener {
            delete(task)
//            notifyItemRemoved(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    }
}