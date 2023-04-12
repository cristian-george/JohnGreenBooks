package com.book.models

data class Book(
    val title: String,
    val year: Int,
    val pages: Int,
    val genre: String,
    val description: String,
    val image: String,
    val url: String

) : java.io.Serializable
