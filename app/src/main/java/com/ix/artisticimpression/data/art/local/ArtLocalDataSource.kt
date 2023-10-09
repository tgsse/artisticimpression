package com.ix.artisticimpression.data.art.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArtLocalDataSource @Inject constructor(
    private val artDao: ArtDao,
//    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun loadDailyArt(): Art? {
        return artDao.loadDailyArt()
//        return withContext(ioDispatcher) {
//            artDao.loadDailyArt()
//        }
    }

    fun saveDailyArt(art: Art) {
        artDao.saveDailyArt(art)
    }
}
