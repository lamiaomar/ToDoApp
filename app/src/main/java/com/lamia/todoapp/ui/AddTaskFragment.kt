package com.lamia.todoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.lamia.todoapp.R
import com.lamia.todoapp.databinding.FragmentAddTaskBinding
import com.lamia.todoapp.model.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_task.*

@AndroidEntryPoint
class AddTaskFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels()

    lateinit var binding: FragmentAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddTaskBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.done.setOnClickListener {
            if (titlee.text.toString().isEmpty()) {
                Toast.makeText(this.context, "You have to fill the title", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val task = Task(
                    title = titlee.text.toString(),
                    description = descriptionn.text.toString(),
                    timestamp = System.currentTimeMillis().toString()
                )
                viewModel.insertTask(task)
                val action =
                    AddTaskFragmentDirections.actionAddTaskFragmentToTaskFragment()
                done.findNavController().navigate(action)
            }

        }

    }
}