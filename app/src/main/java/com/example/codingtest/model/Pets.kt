package com.example.codingtest.model

data class Pets(
    val pets: List<Pet>
) {
    data class Pet(
        val content_url: String,
        val date_added: String,
        val image_url: String,
        val title: String
    )
}