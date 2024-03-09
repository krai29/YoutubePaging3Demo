package com.krai29.youtubepaging3demo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.krai29.youtubepaging3demo.api.RetrofitClient
import com.krai29.youtubepaging3demo.data.Items
import okio.IOException
import retrofit2.HttpException

class UserSource : PagingSource<String, Items>() {

    private var prevPageKey: String? = null
    override fun getRefreshKey(state: PagingState<String, Items>): String? {
        return state.anchorPosition.toString()
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Items> {
        return try {
            val nextPage = params.key ?: ""
            val userList = RetrofitClient.apiService.fetchApiData(
                AppConstants.PART,
                AppConstants.CHANNEL_ID,
                AppConstants.KEY,
                AppConstants.ORDER,
                AppConstants.MAXRESULT,
                AppConstants.VIDEO,
                nextPage
            )

            if (userList.prevPageToken!=prevPageKey){
                prevPageKey = userList.prevPageToken
            }
            LoadResult.Page(
                data = userList.items,
                prevKey = prevPageKey,
                nextKey = userList.nextPageToken
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}