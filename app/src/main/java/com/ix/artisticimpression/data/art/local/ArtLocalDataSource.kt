package com.ix.artisticimpression.data.art.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtLocalDataSource @Inject constructor(
    private val artDao: ArtDao
) {
    fun loadDailyArt(): Flow<Art> {
        return artDao.loadDailyArt()
    }

    fun saveDailyArt(art: Art) {
        artDao.saveDailyArt(art)
    }
}
