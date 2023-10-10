package com.ix.artisticimpression.data.art.local

import javax.inject.Inject

interface ArtLocalRepositoryI {
    suspend fun loadDailyArt(): Art?
    suspend fun saveDailyArt(art: Art)
}

class ArtLocalRepository @Inject constructor(
    private val artLocalDataSource: ArtLocalDataSource,
) : ArtLocalRepositoryI {

    override suspend fun loadDailyArt(): Art? {
        return artLocalDataSource.loadDailyArt()
    }

    override suspend fun saveDailyArt(art: Art) {
        artLocalDataSource.saveDailyArt(art)
    }
}
