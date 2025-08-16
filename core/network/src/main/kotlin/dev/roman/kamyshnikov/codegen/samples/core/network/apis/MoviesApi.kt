package dev.roman.kamyshnikov.codegen.samples.core.network.apis

import dev.roman.kamyshnikov.codegen.samples.core.network.models.MovieDetailsV1
import dev.roman.kamyshnikov.codegen.samples.core.network.models.MovieDetailsV2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    @GET("movies/v2/{id}")
    suspend fun getMovieDetailsV1(
        @Path("id") id: String
    ): Response<MovieDetailsV1>

    @GET("movies/v2/{id}")
    suspend fun getMovieDetailsV2(
        @Path("id") id: String,
    ): Response<MovieDetailsV2>
}
