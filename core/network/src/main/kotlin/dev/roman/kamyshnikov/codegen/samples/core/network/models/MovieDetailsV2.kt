package dev.roman.kamyshnikov.codegen.samples.core.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsV2(
    val id: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val runtime: Int,
    val rating: Double?,
    val genres: List<GenreV1>,
    val posterUrl: String?,
    val credits: CreditsV1,
    val reviews: List<ReviewV1>,
    val trailers: List<TrailerV1>?,
)
