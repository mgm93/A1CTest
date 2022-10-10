package com.mgm.a1ctest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mgm.a1ctest.repository.Repository
import javax.inject.Inject

class ManufacturerPagingSource @Inject constructor(private val repository: Repository) : PagingSource<Int, Pair<String,String>>() {
    override fun getRefreshKey(state: PagingState<Int, Pair<String,String>>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<String,String>> {
        return try {
            val currentPage = params.key ?: 0
            val response = repository.getManufacturers(currentPage, 15)
            val data = response.body()?.wkda?.toList() ?: emptyList()
            val responseData = mutableListOf<Pair<String,String>>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 0) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}