package com.ix.artisticimpression.data.util

import com.ix.artisticimpression.BuildConfig

sealed class DataUtil {
    companion object {
        const val artDatabase = "art_database"
        const val artTable = "art_table"
        const val baseUrl = BuildConfig.baseUrl
        const val imagesUrl = BuildConfig.imagesUrl
    }
}
