package com.example.programmingtask

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.searchdebounce.networking.WebClient
import com.example.programmingtask.model.ImageData
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class ListSourcePagination : PagingSource<Int, ImageData>() {
    private val API_KEY :String = "ebde3aba8362977b0f6967ba0bae97ec"

    override fun getRefreshKey(state: PagingState<Int, ImageData>): Int?
    {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>):LoadResult<Int, ImageData> {
        return try {
            val nextPage = params.key ?: 1
            delay(500)
            val userList = WebClient.client.fetchImages(method = "flickr.photos.search", format ="json", nojsoncallback = 1, text = "electrolux",
                page = nextPage, per_page = 20, api_key = API_KEY)

            LoadResult.Page(
                data = userList.photos.photo.map{ it ->
                    ImageData(
                        id = it.id,
                        url = "https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg",
                        name = it.title
                    )
                },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.photos.photo.isEmpty()) null else userList.photos.page + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}