package com.ix.artisticimpression.data.art.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ArtLocalRepositoryI {
    suspend fun loadDailyArt(): Flow<Art>
    suspend fun saveDailyArt(art: Art)
}

class ArtLocalRepository @Inject constructor(
    private val artLocalDataSource: ArtLocalDataSource
) : ArtLocalRepositoryI {

    override suspend fun loadDailyArt(): Flow<Art> {
        return artLocalDataSource.loadDailyArt()
    }

    override suspend fun saveDailyArt(art: Art) {
        artLocalDataSource.saveDailyArt(art)
    }
}
