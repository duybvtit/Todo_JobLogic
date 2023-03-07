package com.joblogic.todo.domain.entities.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToCallItem(
    val id: Int = 0,
    val name: String = "",
    val number: String = "z"
) : Parcelable
