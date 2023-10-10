package com.ix.artisticimpression.data.art.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDailyArt(art: Art)

    @Query("SELECT * FROM art_table")
    suspend fun loadDailyArt(): Art?
}
