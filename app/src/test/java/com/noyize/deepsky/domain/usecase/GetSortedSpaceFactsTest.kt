package com.noyize.deepsky.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.noyize.deepsky.data.repository.FakeSpaceFactRepository
import com.noyize.deepsky.domain.repository.SpaceFactRepository
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat


class GetSortedSpaceFactsTest {

    private lateinit var spaceFactRepository: SpaceFactRepository
    private lateinit var getSortedSpaceFacts: GetSortedSpaceFacts

    @Before
    fun setup() {
        spaceFactRepository = FakeSpaceFactRepository()
        getSortedSpaceFacts = GetSortedSpaceFacts(spaceFactRepository)
    }

    @Test
    fun `sort spaceFacts by descending order date`() {
        val spaceFacts = spaceFactRepository.getSpaceFacts().shuffled()
        val sortedSpaceFacts = getSortedSpaceFacts()
        val dateFormat = SimpleDateFormat("dd MMM yyy")
        for (i in 0..spaceFacts.size - 2) {
            val firstItemDate = dateFormat.parse(sortedSpaceFacts[i].date)
            val secondItemDate = dateFormat.parse(sortedSpaceFacts[i + 1].date)
            assertThat(firstItemDate).isGreaterThan(secondItemDate)
        }
    }
}