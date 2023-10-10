package com.ix.artisticimpression.data.di

import android.content.Context
import androidx.room.Room
import com.ix.artisticimpression.data.art.local.ArtDb
import com.ix.artisticimpression.testDbName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named(testDbName)
    fun injectInMemoryRoom(@ApplicationContext context: Context) = Room
        .inMemoryDatabaseBuilder(
            context = context,
            klass = ArtDb::class.java,
        ).build()
}
