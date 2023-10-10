package com.ix.artisticimpression.viewmodel

import com.ix.artisticimpression.artDummy
import com.ix.artisticimpression.data.art.ArtRepository
import com.ix.artisticimpression.data.art.local.ArtLocalRepositoryI
import com.ix.artisticimpression.data.art.remote.ArtRemoteRepositoryI
import com.ix.artisticimpression.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
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
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var artRemoteRepo: ArtRemoteRepositoryI

    @MockK
    private lateinit var artLocalRepo: ArtLocalRepositoryI

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup() {
        viewModel = ArtViewModel(ArtRepository(artRemoteRepo, artLocalRepo))
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `loading state should be false after init event`() = runTest {
        // GIVEN
        assertTrue(viewModel.state.value.isLoading)
        coEvery {
            artLocalRepo.loadDailyArt()
        } returns null
        coEvery {
            artRemoteRepo.fetchDailyArt()
        } returns null

        // WHEN
        viewModel.onEvent(ArtEvent.Init)

        // THEN
        advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)
    }

    @Test
    fun `local art exists then init event should load art from local`() = runTest {
        // GIVEN
        assertNull(viewModel.state.value.dailyArt)
        coEvery {
            artLocalRepo.loadDailyArt()
        } returns artDummy

        // WHEN
        viewModel.onEvent(ArtEvent.Init)

        // THEN
        advanceUntilIdle()
        coVerify(exactly = 1) {
            artLocalRepo.loadDailyArt()
        }
        assertEquals(artDummy, viewModel.state.value.dailyArt)
    }

    @Test
    fun `no local art exists then init event should load art from remote`() = runTest {
        // GIVEN
        assertNull(viewModel.state.value.dailyArt)
        coEvery {
            artLocalRepo.loadDailyArt()
        } returns null
        coEvery {
            artRemoteRepo.fetchDailyArt()
        } returns artDummy
        coEvery {
            artLocalRepo.saveDailyArt(artDummy)
        } returns Unit

        // WHEN
        viewModel.onEvent(ArtEvent.Init)

        // THEN
        advanceUntilIdle()
        coVerify(exactly = 1) {
            artRemoteRepo.fetchDailyArt()
        }
        coVerify(exactly = 1) {
            artLocalRepo.loadDailyArt()
        }
        coVerify(exactly = 1) {
            artLocalRepo.saveDailyArt(artDummy)
        }
        assertEquals(artDummy, viewModel.state.value.dailyArt)
    }
}
