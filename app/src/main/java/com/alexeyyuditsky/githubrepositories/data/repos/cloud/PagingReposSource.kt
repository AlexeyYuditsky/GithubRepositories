package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alexeyyuditsky.githubrepositories.core.log
import retrofit2.HttpException

class PagingReposSource(
    private val service: ReposService,
    private val query: String,
) : PagingSource<Int, RepoCloud>() {

    init {
        "hel"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoCloud> {
        val position = params.key ?: REPOS_STARTING_PAGE_INDEX

        return try {
            val response = service.fetchRepos2(query, position, params.loadSize)
            val repos = response.items
            val nextKey = if (repos.isEmpty()) null else position + params.loadSize / NETWORK_PAGE_SIZE
            LoadResult.Page(
                data = repos,
                prevKey = if (position == REPOS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoCloud>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val REPOS_STARTING_PAGE_INDEX = 1
        private const val NETWORK_PAGE_SIZE = 15
    }

}