package com.alexeyyuditsky.githubrepositories.domain.issues

import com.alexeyyuditsky.githubrepositories.core.ErrorType
import com.alexeyyuditsky.githubrepositories.data.issues.IssuesData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class IssuesInteractorTest {

    @Test
    fun test_success() {
        val data = IssuesData.Base(listOf())

        val actual = data.map()
        val expected = IssuesDomain.Base(listOf())

        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val data = IssuesData.Fail(UnknownHostException())

        val actual = data.map()
        val expected = IssuesDomain.Fail(ErrorType.NO_CONNECTION)

        assertEquals(expected, actual)
    }

}