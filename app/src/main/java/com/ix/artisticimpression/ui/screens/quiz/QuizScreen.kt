package com.ix.artisticimpression.ui.screens.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ix.artisticimpression.ui.theme.ArtisticImpressionTheme
import com.ix.artisticimpression.ui.theme.spacing

@Composable
fun QuizScreen(onNavigateToDetails: () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Artistic Impression")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
            Text("Can you guess today's art piece?")

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
            Button(onClick = { onNavigateToDetails() }) {
                Text("View Details ->")
            }
        }
    }
}

@Preview
@Composable
private fun QuizPreview() {
    ArtisticImpressionTheme {
        QuizScreen()
    }
}
