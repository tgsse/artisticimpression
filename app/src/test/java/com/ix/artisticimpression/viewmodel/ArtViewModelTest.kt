package com.ix.artisticimpression.viewmodel

import com.ix.artisticimpression.artDummy
import com.ix.artisticimpression.data.art.FakeArtRepository
import com.ix.artisticimpression.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArtViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup() {
        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `loading state should be false after init event`() = runTest {
        // GIVEN
        assertTrue(viewModel.state.value.isLoading)

        // WHEN
        viewModel.onEvent(ArtEvent.Init)

        // THEN
        advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)
    }

    @Test
    fun `init event should load dailyArt`() = runTest {
        // GIVEN
        assertNull(viewModel.state.value.dailyArt)

        // WHEN
        viewModel.onEvent(ArtEvent.Init)

        // THEN
        advanceUntilIdle()
        assertEquals(artDummy, viewModel.state.value.dailyArt)
    }
}
