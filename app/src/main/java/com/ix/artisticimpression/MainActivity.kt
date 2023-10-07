package com.ix.artisticimpression

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ix.artisticimpression.navigation.NavigationHost
import com.ix.artisticimpression.ui.theme.ArtisticImpressionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ArtisticImpressionTheme {
                // A surface container using the 'background' color from the theme
                NavigationHost(navController)
            }
        }
    }
}
