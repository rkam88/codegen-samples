package dev.roman.kamyshnikov.codegen.samples.core.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrailerV1(
    val id: String,
    val site: String,
    val key: String,
    val type: String,
)
