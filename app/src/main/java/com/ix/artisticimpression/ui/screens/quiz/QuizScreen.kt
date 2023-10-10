package com.ix.artisticimpression.ui.screens.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import com.ix.artisticimpression.R
import com.ix.artisticimpression.ui.components.ProgressIndicator
import com.ix.artisticimpression.ui.components.ViewDetailsButton
import com.ix.artisticimpression.ui.theme.spacing
import com.ix.artisticimpression.viewmodel.ArtEvent
import com.ix.artisticimpression.viewmodel.ArtViewModel

@Composable
fun QuizScreen(
    onNavigateToDetails: () -> Unit = {},
    viewModel: ArtViewModel = hiltViewModel(),
) {
    val screenContentDesc = stringResource(id = R.string.label_screen_quiz)
    val lifecycleOwner = LocalLifecycleOwner.current
//    val scope = rememberCoroutineScope()
//    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsState()

    DisposableEffect(lifecycleOwner) {
        val reloadWhenResumed = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.onEvent(event = ArtEvent.Init)
            }
        }
        lifecycleOwner.lifecycle.addObserver(reloadWhenResumed)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(reloadWhenResumed)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.m)
                .verticalScroll(rememberScrollState())
                .semantics {
                    contentDescription = screenContentDesc
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
            Text("Can you guess today's art piece?")
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))

            if (state.isLoading) {
                ProgressIndicator()
            } else if (state.dailyArt == null) {
                Text(
                    text = "No daily art loaded :(",
                )
            } else {
                Text(
                    text = state.dailyArt!!.artist,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
                Text(
                    text = state.dailyArt!!.title,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
                Text(
                    text = state.dailyArt!!.year,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
                AsyncImage(
                    model = state.dailyArt!!.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    error = painterResource(id = R.drawable.image_placeholder),
                    modifier = Modifier
                        .fillMaxSize(),
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))
            ViewDetailsButton(onNavigateToDetails)
        }
    }
}

// @Preview
// @Composable
// private fun QuizPreview() {
//    ArtisticImpressionTheme {
//        QuizScreen()
//    }
// }
