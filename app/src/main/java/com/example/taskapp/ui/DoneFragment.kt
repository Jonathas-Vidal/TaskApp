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
import com.example.taskapp.databinding.FragmentDoneBinding
import com.example.taskapp.ui.adapter.TaskAdapter

class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
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
            Task("20", "Entregar o relatório final", Status.DONE),
            Task("21", "Completar a leitura do livro", Status.DONE),
            Task("22", "Finalizar a apresentação para a conferência", Status.DONE),
            Task("23", "Enviar convites para o evento", Status.DONE),
            Task("24", "Concluir o treino de corrida de 10 km", Status.DONE),
            Task("25", "Submeter a inscrição para o curso online", Status.DONE),
            Task("26", "Montar o móvel novo", Status.DONE),
            Task("27", "Enviar os documentos assinados", Status.DONE),
            Task("28", "Terminar a pintura do quadro", Status.DONE),
            Task("29", "Realizar a limpeza profunda da casa", Status.DONE),
        )

        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}