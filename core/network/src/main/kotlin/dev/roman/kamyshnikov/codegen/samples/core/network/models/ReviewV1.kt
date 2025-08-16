package dev.roman.kamyshnikov.codegen.samples.core.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewV1(
    val authorId: String,
    val title: String,
    val description: String,
)
