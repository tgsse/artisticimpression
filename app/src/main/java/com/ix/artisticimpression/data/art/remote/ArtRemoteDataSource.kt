package com.ix.artisticimpression.data.art.remote

import javax.inject.Inject

class ArtRemoteDataSource @Inject constructor(
    private val artService: ArtService
) {
    suspend fun fetchDailyArt(): ArtResponseData? {
        val resp = artService.getArtwork("24645")
        return resp.data
    }
}
