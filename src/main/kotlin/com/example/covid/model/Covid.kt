package com.example.covid.model

data class Covid(
    val id: Int,
    val country: String,
    val active: Int,
    val death: Int,
    val recovered: Int
)