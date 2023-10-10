package com.ix.artisticimpression.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ix.artisticimpression.R
import com.ix.artisticimpression.ui.theme.ArtisticImpressionTheme

@Composable
fun ViewDetailsButton(onNavigateToDetails: () -> Unit) {
    Button(onClick = { onNavigateToDetails() }) {
        Text(stringResource(R.string.label_view_details))
    }
}

@Preview
@Composable
fun ViewDetailsButtonPreview() {
    ArtisticImpressionTheme {
        ViewDetailsButton(onNavigateToDetails = {})
    }
}
