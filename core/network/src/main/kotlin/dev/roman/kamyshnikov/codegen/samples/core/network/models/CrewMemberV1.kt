package dev.roman.kamyshnikov.codegen.samples.core.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CrewMemberV1(
    val id: String,
    val name: String,
    val job: String?,
    val department: String?,
    val profileUrl: String?,
)
