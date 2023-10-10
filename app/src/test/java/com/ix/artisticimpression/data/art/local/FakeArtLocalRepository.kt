package com.ix.artisticimpression.data.art.local

import kotlinx.coroutines.delay

object FakeArtLocalRepository : ArtLocalRepositoryI {

    private var dailyArt: Art? = null

    override suspend fun loadDailyArt(): Art? {
        delay(10L)
        return dailyArt
    }

    override suspend fun saveDailyArt(art: Art) {
        delay(10L)
        dailyArt = art
    }
}
