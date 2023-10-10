package com.ix.artisticimpression.ui.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.ix.artisticimpression.R
import com.ix.artisticimpression.ui.components.ViewDetailsButton
import com.ix.artisticimpression.ui.theme.ArtisticImpressionTheme
import com.ix.artisticimpression.ui.theme.spacing

@Composable
fun ExploreScreen(onNavigateToDetails: () -> Unit = {}) {
    val screenContentDesc = stringResource(id = R.string.label_screen_explore)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .semantics {
                contentDescription = screenContentDesc
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Explore")
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))

        ViewDetailsButton(onNavigateToDetails)
    }
}

@Preview
@Composable
private fun ExplorePreview() {
    ArtisticImpressionTheme {
        ExploreScreen()
    }
}
