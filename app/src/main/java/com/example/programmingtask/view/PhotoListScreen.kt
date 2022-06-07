package com.example.programmingtask.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

import com.example.programmingtask.R
import com.example.programmingtask.model.ImageData
import com.example.programmingtask.navigation.Destination

import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.ExperimentalCoroutinesApi


import kotlinx.coroutines.flow.Flow
import retrofit2.Response.error
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoListCompose(imageList: Flow<PagingData<ImageData>>,itemClick : (String) -> Unit) {

    val userListItems: LazyPagingItems<ImageData> = imageList.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {

        TopHeaderBar(LocalContext.current,false,"")

        LazyColumn {
            items(userListItems) { item ->
                item?.let {
                    ImageGridItem(imageData = it) {
                        val encodedUrl =
                            URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                        itemClick.invoke(Destination.Detail.route + "/$encodedUrl" + "/${it.name}")
                    }
                }
            }

            userListItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        //You can add modifier to manage load state when first time response page is loading
                    }
                    loadState.append is LoadState.Loading -> {
                        //You can add modifier to manage load state when next response page is loading
                        item{
                            Box(modifier = Modifier.fillMaxSize()){
                                CircularProgressIndicator(modifier = Modifier
                                    .size(30.dp)
                                    .align(Center))
                            }
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        //You can use modifier to show error message``
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ImageGridItem(imageData: ImageData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .background(Color.White)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White),
        ) {
            Surface(
                modifier = Modifier.size(130.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.surface.copy(
                    alpha = 0.2f
                )
            ) {
                GlideImage(
                    imageModel = imageData.url,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows a placeholder while loading the image.
                    placeHolder = ImageBitmap.imageResource(R.drawable.default_image),
                    // shows an error ImageBitmap when the request failed.
                    error = ImageBitmap.imageResource(R.drawable.default_image)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = imageData.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )

            }

        }
    }

}






