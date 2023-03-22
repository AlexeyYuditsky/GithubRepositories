package com.alexeyyuditsky.githubrepositories.presentation.issues

import com.alexeyyuditsky.githubrepositories.core.ResourceProvider
import org.junit.Test
import com.alexeyyuditsky.githubrepositories.R
import org.junit.Assert.*

class ResourceProviderTest {

    @Test
    fun test() {
        val resourceProviderTest = ResourceProviderTest()

        val actual = resourceProviderTest.getString(R.string.no_connection_message)
        val expected = "noConnection"

        assertEquals(expected, actual)
    }

    private class ResourceProviderTest : ResourceProvider {
        override fun getString(id: Int): String {
            return when (id) {
                R.string.no_connection_message -> "noConnection"
                R.string.service_unavailable_message -> "serviceUnavailable"
                else -> "somethingWentWrong"
            }
        }
    }

}