package com.ix.artisticimpression.data.di

import com.ix.artisticimpression.data.art.remote.ArtRemoteDataSource
import com.ix.artisticimpression.data.art.remote.ArtRemoteRepository
import com.ix.artisticimpression.data.art.remote.ArtRemoteRepositoryI
import com.ix.artisticimpression.data.art.remote.ArtService
import com.ix.artisticimpression.data.util.DataUtil.Companion.baseUrl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideJsonConverter(): Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        jsonConverter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(jsonConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideArtService(retrofit: Retrofit): ArtService {
        return retrofit.create(ArtService::class.java)
    }

    @Singleton
    @Provides
    fun provideArtRemoteRepository(artRemoteDataSource: ArtRemoteDataSource): ArtRemoteRepositoryI {
        return ArtRemoteRepository(artRemoteDataSource)
    }
}
