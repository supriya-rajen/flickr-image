package com.example.programmingtask.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programmingtask.R
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ImageDetailCompose(url : String, name :String) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
      
        TopHeaderBar(LocalContext.current,true,url)

        GlideImage(modifier = Modifier.fillMaxWidth().height(400.dp).padding(30.dp).clip(
            RoundedCornerShape(10.dp)),
            imageModel = url,
            contentScale = ContentScale.Crop,
            placeHolder = ImageBitmap.imageResource(R.drawable.default_image),
            error = ImageBitmap.imageResource(R.drawable.default_image)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}













