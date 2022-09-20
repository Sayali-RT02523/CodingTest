package com.example.codingtest.model

data class config(
    val settings: Settings
) {
    data class Settings(
        val workHours: String
    )
}