package dev.roman.kamyshnikov.codegen.samples.core.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastMemberV1(
    val id: String,
    val name: String,
    val character: String?,
    val profileUrl: String?,
)
