package com.lamia.todoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lamia.todoapp.databinding.FragmentTaskDetailBinding
import com.lamia.todoapp.model.Task
import dagger.hilt.android.AndroidEntryPoint

private const val ID = "id"

@AndroidEntryPoint
class TaskDetailFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels()

    private var taskId = 0

    lateinit var binding: FragmentTaskDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            taskId = it.getInt(ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTaskDetailBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.getTaskDetail(taskId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * when the task updated use insertTask() function and navigate to home screen
         */
        binding.done.setOnClickListener {
            val editTask = Task(
                id = taskId,
                title = binding.titlee.text.toString(),
                description = binding.descriptionn.text.toString(),
                timestamp = System.currentTimeMillis().toString()
            )
            viewModel.insertTask(editTask)
            val action =
                TaskDetailFragmentDirections.actionTaskDetailFragmentToTaskFragment()
            findNavController().navigate(action)
        }


    }

}