package com.example.programmingtask.view

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.programmingtask.R
import com.example.programmingtask.model.ImageData
import com.example.programmingtask.navigation.Destination
import com.example.programmingtask.viewModel.GridImageViewModel
import com.skydoves.landscapist.glide.GlideImage
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@SuppressLint("UnrememberedMutableState")
@ExperimentalFoundationApi
@Composable
fun  PhotoGridCompose(
    photosViewModel: GridImageViewModel,itemClick: (String) -> Unit){

    var imageData: List<ImageData>

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
      TopHeaderBar(LocalContext.current,false,""  )
        val textState =  mutableStateOf(TextFieldValue("Electrolux"))

        SearchView(state = textState)

        if (!photosViewModel.isSuccessLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .align(CenterHorizontally)
                    .wrapContentSize(align = Alignment.Center)
            )
        }else {
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(10.dp)) {
                imageData = photosViewModel.photosList as MutableList<ImageData>
                items(imageData) { data ->
                    ImageDataGridItem(data.url.isEmpty(),data) {
                        if(data.url.isNotEmpty()){
                            val encodedUrl = URLEncoder.encode(data.url, StandardCharsets.UTF_8.toString())
                            itemClick.invoke(Destination.Detail.route + "/$encodedUrl" + "/${data.name}")
                        }else{
                            itemClick.invoke(Destination.List.route)
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun ImageDataGridItem(isLastItem : Boolean,data: ImageData,itemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable {
                itemClick.invoke()
            }
            .padding(10.dp)
            .height(150.dp)
            .width(150.dp),
        elevation = 5.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(5.dp)
    ) {
        if(isLastItem){
            Image(
                painterResource(id = R.drawable.view_more),
                contentDescription = "Grid Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp)), alignment = Alignment.Center
            )
        }else{
//            val image = loadPicture(url = data.url, defaultImage = DEFAULT_RECIPE_IMAGE).value
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(image)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "Photo",
//                contentScale = ContentScale.Crop,
//            )
            GlideImage(
                imageModel = data.url,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                // shows a placeholder while loading the image.
                placeHolder = ImageBitmap.imageResource(R.drawable.default_image),
                // shows an error ImageBitmap when the request failed.
                error = ImageBitmap.imageResource(R.drawable.default_image)
            )

        }
    }
}

