package com.ix.artisticimpression.navigation

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.ix.artisticimpression.MainActivity
import com.ix.artisticimpression.R
import com.ix.artisticimpression.ui.theme.ArtisticImpressionTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class NavigationTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navController: TestNavHostController
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            ArtisticImpressionTheme {
                NavigationHost(navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.label_screen_quiz))
            .assertIsDisplayed()
    }

    @Test
    fun appNavHost_clickAllTabs_navigateToTabs() {
        with(composeTestRule) {
            onNodeWithText(context.getString(R.string.label_screen_favorites))
                .assertIsDisplayed()
                .assertHasClickAction()
                .performClick()
            onNodeWithContentDescription(context.getString(R.string.label_screen_favorites))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.label_screen_explore))
                .assertIsDisplayed()
                .assertHasClickAction()
                .performClick()
            onNodeWithContentDescription(context.getString(R.string.label_screen_explore))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.label_screen_quiz))
                .assertIsDisplayed()
                .assertHasClickAction()
                .performClick()
            onNodeWithContentDescription(context.getString(R.string.label_screen_quiz))
                .assertIsDisplayed()
        }
    }

    @Test
    fun appNavHost_clickDetails_navigateToDetails() {
        with(composeTestRule) {
            onNodeWithContentDescription(context.getString(R.string.label_screen_quiz))
                .assertIsDisplayed()

            onNodeWithText(context.getString(R.string.label_view_details))
                .assertIsDisplayed()
                .assertHasClickAction()
                .performClick()

            onNodeWithContentDescription(context.getString(R.string.label_screen_details))
                .assertIsDisplayed()

            onNodeWithContentDescription(context.getString(R.string.content_desc_navigate_back))
                .assertIsDisplayed()
                .assertHasClickAction()
                .performClick()

            onNodeWithContentDescription(context.getString(R.string.label_screen_quiz))
                .assertIsDisplayed()
        }
    }
}
