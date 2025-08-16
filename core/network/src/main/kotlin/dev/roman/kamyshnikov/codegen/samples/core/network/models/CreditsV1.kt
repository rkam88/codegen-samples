package dev.roman.kamyshnikov.codegen.samples.core.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditsV1(
    val cast: List<CastMemberV1>,
    val crew: List<CrewMemberV1>,
)
