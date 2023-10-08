package com.ix.artisticimpression.data.art

import com.ix.artisticimpression.data.art.local.Art
import com.ix.artisticimpression.data.art.remote.ArtResponseData
import com.ix.artisticimpression.data.util.DataUtil

fun ArtResponseData.toArt(): Art {
    val formattedYear = if (dateStart == dateEnd) {
        dateStart.toString()
    } else {
        "$dateStart - $dateEnd"
    }
    val imageUrl = "${DataUtil.imagesUrl}/iiif/2/$imageId/full/843,/0/default.jpg"

    return Art(
        title = title,
        artist = artist,
        year = formattedYear,
        imageUrl = imageUrl
    )
}
