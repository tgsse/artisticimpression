package com.ix.artisticimpression.data.art

import com.ix.artisticimpression.data.art.local.ArtLocalRepositoryI
import com.ix.artisticimpression.data.art.local.FakeArtLocalRepository
import com.ix.artisticimpression.data.art.remote.ArtRemoteRepositoryI
import com.ix.artisticimpression.data.art.remote.FakeArtRemoteRepository

class FakeArtRepository: ArtRepositoryI {
    override val remote: ArtRemoteRepositoryI
        get() = FakeArtRemoteRepository
    override val local: ArtLocalRepositoryI
        get() = FakeArtLocalRepository
}
