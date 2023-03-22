package com.alexeyyuditsky.githubrepositories.data.issues

import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssueCloud
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssueOwner
import com.alexeyyuditsky.githubrepositories.data.issues.cloud.IssuesCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

class IssuesRepositoryTest {

    private val unknownHostException = UnknownHostException()

    @Test
    fun test_have_connection() = runBlocking {
        val cloudDataSourceTest = IssuesCloudDataSourceTest(true)
        val repository = IssuesRepository.Base(cloudDataSourceTest)

        val actual = repository.fetchIssues("title", "user")
        val expected = IssuesData.Base(
            listOf(
                IssueCloud(0, "title0", IssueOwner("user0"), "created0", "updated0"),
                IssueCloud(1, "title1", IssueOwner("user1"), "created1", "updated1")
            )
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection() = runBlocking {
        val cloudDataSourceTest = IssuesCloudDataSourceTest(false)
        val repository = IssuesRepository.Base(cloudDataSourceTest)

        val actual = repository.fetchIssues("title", "user")
        val expected = IssuesData.Fail(unknownHostException)

        Assert.assertEquals(expected, actual)
    }

    private inner class IssuesCloudDataSourceTest(
        private val returnSuccess: Boolean,
    ) : IssuesCloudDataSource {
        override suspend fun fetchIssues(user: String, repo: String): List<IssueCloud> {
            if (returnSuccess) {
                return listOf(
                    IssueCloud(0, "title0", IssueOwner("user0"), "created0", "updated0"),
                    IssueCloud(1, "title1", IssueOwner("user1"), "created1", "updated1")
                )
            } else {
                throw unknownHostException
            }
        }
    }

}