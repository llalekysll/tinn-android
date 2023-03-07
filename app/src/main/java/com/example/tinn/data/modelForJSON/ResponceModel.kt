package com.example.tinn.data.modelForJSON

data class ResponceModel <T>(
    val success: Boolean = false,
    val errors: List<Any> = emptyList(),
    val data: List<T> = emptyList()
)