package com.ix.artisticimpression.data.art

import com.ix.artisticimpression.data.art.local.ArtLocalRepositoryI
import com.ix.artisticimpression.data.art.remote.ArtRemoteRepositoryI
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface ArtRepositoryI {
    val remote: ArtRemoteRepositoryI
    val local: ArtLocalRepositoryI
}

@ViewModelScoped
class ArtRepository @Inject constructor(
    artRemoteRepository: ArtRemoteRepositoryI,
    artLocalRepository: ArtLocalRepositoryI,
) : ArtRepositoryI {
    override val remote = artRemoteRepository
    override val local = artLocalRepository
}
