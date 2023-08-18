package com.example.taskapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.data.Status
import com.example.taskapp.data.Task
import com.example.taskapp.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
    private val taskSelected: (Task, Int) -> Unit
) : ListAdapter<Task, TaskAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val SELECT_BACK: Int = 1
        val SELECT_REMOVE: Int = 2
        val SELECT_EDIT: Int = 3
        val SELECT_DETAILS: Int = 4
        val SELECT_NEXT: Int = 5
    }

    object DIFF_CALLBACK : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(
            oldItem: Task,
            newItem: Task
        )
                : Boolean {
            return oldItem.id == newItem.id && oldItem.desc == newItem.desc
        }

        override fun areContentsTheSame(
            oldItem: Task,
            newItem: Task
        )
                : Boolean {
            return oldItem.id == newItem.id && oldItem.desc == newItem.desc
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = getItem(position)

        holder.binding.btnDeleteTask.setOnClickListener { taskSelected(task, SELECT_REMOVE) }
        holder.binding.btnEditTask.setOnClickListener { taskSelected(task, SELECT_EDIT) }
        holder.binding.btnDetailTask.setOnClickListener { taskSelected(task, SELECT_DETAILS) }

        holder.binding.textDescription.text = task.desc

        setIndicators(task, holder)

    }

    private fun setIndicators(task: Task, holder: MyViewHolder) {
        when (task.status) {
            Status.TODO -> {
                holder.binding.btnBack.isVisible = false

                holder.binding.btnNext.setOnClickListener { taskSelected(task, SELECT_NEXT) }
            }

            Status.DOING -> {

                holder.binding.btnNext.setOnClickListener { taskSelected(task, SELECT_NEXT) }
                holder.binding.btnBack.setOnClickListener { taskSelected(task, SELECT_BACK) }

                holder.binding.btnBack.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_status_todo
                    )
                )
                holder.binding.btnNext.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_status_done
                    )
                )
            }

            Status.DONE -> {
                holder.binding.btnNext.isVisible = false

                holder.binding.btnBack.setOnClickListener { taskSelected(task, SELECT_BACK) }
            }

        }

    }

    inner class MyViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)
}

