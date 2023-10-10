package com.ix.artisticimpression.data.art.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArtLocalDataSource @Inject constructor(
    private val artDao: ArtDao,
) {
    suspend fun loadDailyArt(): Art? = withContext(Dispatchers.IO) {
        artDao.loadDailyArt()
    }

    suspend fun saveDailyArt(art: Art) = withContext(Dispatchers.IO) {
        artDao.saveDailyArt(art)
    }
}
