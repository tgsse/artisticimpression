package com.ix.artisticimpression.data.art.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Art::class],
    version = 1,
    exportSchema = false,
)
abstract class ArtDb : RoomDatabase() {
    abstract fun artDao(): ArtDao
}
