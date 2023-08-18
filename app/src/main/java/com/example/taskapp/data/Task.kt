package com.example.taskapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: String,
    val desc: String,
    val status: Status
) : Parcelable
