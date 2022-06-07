package com.example.programmingtask.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.searchdebounce.networking.WebClient
import com.example.programmingtask.model.ImageData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class GridImageViewModel : ViewModel() {
    val isSuccessLoading = mutableStateOf(value = false)
    var photosList = emptyList<ImageData>()
    private val API_KEY :String = "ebde3aba8362977b0f6967ba0bae97ec"

    init{
        getImageList()
    }

   private fun getImageList(){
        // using coroutine doing api call
        viewModelScope.launch(IO){
            val searchResponse = WebClient.client.fetchImages(method = "flickr.photos.search", format ="json", nojsoncallback = 1, text = "electrolux", page = 1, per_page = 21, api_key = API_KEY)
            photosList = searchResponse.photos.photo.map { photo ->
                ImageData(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    name = photo.title
                )
            }
            // adding empty data as last item in the list to show view more option
            (photosList as MutableList<ImageData>).add(
                photosList.lastIndex.plus(1),
                ImageData("", "", ""))

            isSuccessLoading.value = true
        }
    }
}
