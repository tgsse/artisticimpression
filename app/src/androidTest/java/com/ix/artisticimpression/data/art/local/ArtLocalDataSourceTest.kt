package com.ix.artisticimpression.data.art.local

import androidx.test.filters.SmallTest
import com.ix.artisticimpression.artDummy
import com.ix.artisticimpression.testDbName
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtLocalDataSourceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named(testDbName)
    lateinit var artDb: ArtDb

    private lateinit var artDao: ArtDao

    @Before
    fun setup() {
        hiltRule.inject()

        artDao = artDb.artDao()
    }

    @After
    fun tearDown() {
        artDb.close()
    }

    @Test
    fun saveAndLoadArt() = runTest {
        // GIVEN
        assertNull(artDao.loadDailyArt())

        // WHEN
        artDao.saveDailyArt(artDummy)

        // THEN
        val storedValue = artDao.loadDailyArt()
        assertNotNull(storedValue)
        assertEquals(artDummy.title, storedValue!!.title)
    }
}
