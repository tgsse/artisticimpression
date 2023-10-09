package com.ix.artisticimpression.data.art.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtResponse(
    @SerialName("data")
    val data: ArtResponseData?
)

@Serializable
data class ArtResponseData(

    @SerialName("title")
    val title: String,

    @SerialName("artist_title")
    val artist: String,

    @SerialName("date_start")
    val dateStart: Int,

    @SerialName("date_end")
    val dateEnd: Int,

    @SerialName("image_id")
    val imageId: String
)
