package com.ix.artisticimpression.data.art.remote

import com.ix.artisticimpression.artDummy
import com.ix.artisticimpression.data.art.local.Art
import kotlinx.coroutines.delay

object FakeArtRemoteRepository : ArtRemoteRepositoryI {

    override suspend fun fetchDailyArt(): Art {
        delay(10L)
        return artDummy
    }
}
