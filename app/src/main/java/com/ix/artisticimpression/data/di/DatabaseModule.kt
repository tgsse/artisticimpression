package com.ix.artisticimpression.data.di

import android.content.Context
import androidx.room.Room
import com.ix.artisticimpression.data.art.local.ArtDb
import com.ix.artisticimpression.data.art.local.ArtLocalDataSource
import com.ix.artisticimpression.data.art.local.ArtLocalRepository
import com.ix.artisticimpression.data.art.local.ArtLocalRepositoryI
import com.ix.artisticimpression.data.util.DataUtil.Companion.artDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideArtDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = ArtDb::class.java,
        name = artDatabase,
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ArtDb) = database.artDao()

    @Singleton
    @Provides
    fun provideArtLocalRepository(artLocalDataSource: ArtLocalDataSource): ArtLocalRepositoryI {
        return ArtLocalRepository(artLocalDataSource)
    }
}
