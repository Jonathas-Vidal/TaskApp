package com.example.taskapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.data.Status
import com.example.taskapp.data.Task
import com.example.taskapp.databinding.FragmentDoingBinding
import com.example.taskapp.ui.adapter.TaskAdapter

class DoingFragment : Fragment() {
    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTaskRecyclerView()
        getTasks()
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
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Next ${task.desc}", Toast.LENGTH_SHORT).show()
            }

            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Back ${task.desc}", Toast.LENGTH_SHORT).show()
            }

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

        }
    }

    private fun getTasks() {
        val taskList = listOf(
            Task("10", "Pesquisar informações para o projeto", Status.DOING),
            Task("11", "Redigir relatório para reunião", Status.DOING),
            Task("12", "Estudar para o exame de matemática", Status.DOING),
            Task("13", "Atualizar o currículo com as últimas experiências", Status.DOING),
            Task("14", "Editar as fotos do último evento", Status.DOING),
            Task("15", "Revisar o código para resolver bugs", Status.DOING),
            Task("16", "Preparar materiais para a apresentação", Status.DOING),
            Task("17", "Responder aos e-mails pendentes", Status.DOING),
            Task("18", "Aprender a tocar uma música no violão", Status.DOING),
            Task("19", "Desenvolver o roteiro do vídeo tutorial", Status.DOING),
        )

        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}