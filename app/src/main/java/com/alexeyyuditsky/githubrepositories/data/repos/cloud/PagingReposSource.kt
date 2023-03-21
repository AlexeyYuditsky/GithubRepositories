package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException

class PagingReposSource(
    private val service: ReposService,
    private val query: String,
) : PagingSource<Int, RepoCloud>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoCloud> {
        val position = params.key ?: INITIAL_LOAD_SIZE

        return try {
            val response = service.fetchRepos(query, position, params.loadSize)
            val repos = response.items
            val nextKey = if (repos.isEmpty()) null else position + (params.loadSize / NETWORK_PAGE_SIZE)
            LoadResult.Page(
                data = repos,
                prevKey = if (position == 1) null else position - 1,
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
        private const val INITIAL_LOAD_SIZE = 1
        const val NETWORK_PAGE_SIZE = 15
    }

}