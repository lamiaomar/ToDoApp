package com.lamia.todoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.lamia.todoapp.databinding.FragmentTaskBinding
import com.lamia.todoapp.ui.adapter.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_task.*

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels()

    lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTaskBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.list.adapter = TaskAdapter {
            viewModel.deleteTask(it)
            //call onResume() to refresh the list
            onResume()
        }

        return binding.root
    }

    /**
     * call getTask() in onResume to refresh the list every time
     */
    override fun onResume() {
        viewModel.getTasks()
        viewModel.taskList
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Navigate to Add Task fragment
         */
        binding.addTask.setOnClickListener {
            val action =
                TaskFragmentDirections.actionTaskFragmentToAddTaskFragment()
            add_task.findNavController().navigate(action)
        }

    }

}