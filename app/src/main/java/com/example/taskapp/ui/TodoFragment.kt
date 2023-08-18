package com.example.taskapp.ui

import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.R
import com.example.taskapp.data.Status
import com.example.taskapp.data.Task
import com.example.taskapp.databinding.FragmentTodoBinding
import com.example.taskapp.ui.adapter.TaskAdapter

class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initTaskRecyclerView()
        getTasks()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }
    }

    private fun initTaskRecyclerView() {
        taskAdapter = TaskAdapter(requireContext()) { task, option ->
            optionSelected(task, option)
        }

        with(binding.rvTasks) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVE -> {
                Toast.makeText(
                    requireContext(),
                    "Removendo tarefa ${task.desc}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "editando tarefa ${task.desc}", Toast.LENGTH_SHORT)
                    .show()
            }

            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(
                    requireContext(),
                    "detalhando tarefa ${task.desc}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Next ${task.desc}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTasks() {
        val taskList = listOf(
            Task("0", "Comprar mantimentos", Status.TODO),
            Task("1", "Ler um capítulo do livro", Status.TODO),
            Task("2", "Fazer exercícios por 30 minutos", Status.TODO),
            Task("3", "Ligar para o médico e marcar consulta", Status.TODO),
            Task("4", "Limpar o armário da cozinha", Status.TODO),
            Task("5", "Escrever um e-mail para o colega de trabalho", Status.TODO),
            Task("6", "Assistir a um tutorial de programação", Status.TODO),
            Task("7", "Fazer uma caminhada de 1 hora", Status.TODO),
            Task("8", "Organizar as contas mensais", Status.TODO),
            Task("9", "Experimentar uma nova receita de culinária", Status.TODO),
        )

        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}