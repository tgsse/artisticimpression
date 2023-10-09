package com.ix.artisticimpression.data.art.remote

import com.ix.artisticimpression.data.art.local.Art
import com.ix.artisticimpression.data.art.toArt
import javax.inject.Inject

interface ArtRemoteRepositoryI {
    suspend fun fetchDailyArt(): Art?
}

class ArtRemoteRepository @Inject constructor(
    private val artRemoteDataSource: ArtRemoteDataSource
) : ArtRemoteRepositoryI {

    override suspend fun fetchDailyArt(): Art? {
        val response = artRemoteDataSource.fetchDailyArt()
        return response?.toArt()
    }
}
