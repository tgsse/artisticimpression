package com.ix.artisticimpression.ui.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ix.artisticimpression.ui.theme.ArtisticImpressionTheme
import com.ix.artisticimpression.ui.theme.spacing

@Composable
fun ExploreScreen(onNavigateToDetails: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Explore")
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
        Button(onClick = { onNavigateToDetails() }) {
            Text("View Details ->")
        }
    }
}

@Preview
@Composable
private fun ExplorePreview() {
    ArtisticImpressionTheme {
        ExploreScreen()
    }
}
