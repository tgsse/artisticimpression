package com.ix.artisticimpression.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ix.artisticimpression.R
import com.ix.artisticimpression.ui.theme.ArtisticImpressionTheme

@Composable
fun ProgressIndicator() {
    val contentDesc = stringResource(id = R.string.content_desc_loading)
    CircularProgressIndicator(
        modifier = Modifier
            .width(32.dp)
            .semantics {
                contentDescription = contentDesc
            },
        color = MaterialTheme.colorScheme.surfaceVariant,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}

@Preview
@Composable
fun ProgressIndicatorPreview() {
    ArtisticImpressionTheme {
        ProgressIndicator()
    }
}
