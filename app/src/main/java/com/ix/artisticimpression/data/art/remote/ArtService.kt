package com.ix.artisticimpression.data.art.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ArtService {

    @GET("/api/v1/artworks/{id}")
    suspend fun getArtwork(@Path("id") id: String): ArtResponse
}
