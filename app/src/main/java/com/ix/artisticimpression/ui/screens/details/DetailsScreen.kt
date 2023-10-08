package com.ix.artisticimpression.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ix.artisticimpression.R
import com.ix.artisticimpression.ui.components.BackIconButton
import com.ix.artisticimpression.ui.components.TopBar
import com.ix.artisticimpression.ui.theme.ArtisticImpressionTheme

@Composable
fun DetailsScreen(
    onNavigateBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.label_screen_details),
                navigationIcon = { BackIconButton(onClick = onNavigateBack) }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Details")
            }
        }
    }
}

@Preview
@Composable
private fun DetailsPreview() {
    ArtisticImpressionTheme {
        DetailsScreen()
    }
}
