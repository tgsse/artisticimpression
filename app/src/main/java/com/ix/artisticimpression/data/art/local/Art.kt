package com.ix.artisticimpression.data.art.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ix.artisticimpression.data.util.DataUtil.Companion.artTable

@Entity(tableName = artTable)
class Art(
    val title: String,
    val artist: String,
    val year: String,
    val imageUrl: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
