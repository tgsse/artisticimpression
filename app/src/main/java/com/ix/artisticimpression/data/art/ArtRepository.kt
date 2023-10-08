package com.ix.artisticimpression.data.art

import com.ix.artisticimpression.data.art.local.ArtLocalRepositoryI
import com.ix.artisticimpression.data.art.remote.ArtRemoteRepositoryI
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ArtRepository @Inject constructor(
    artRemoteRepository: ArtRemoteRepositoryI,
    artLocalRepository: ArtLocalRepositoryI
) {
    val remote = artRemoteRepository
    val local = artLocalRepository
}
