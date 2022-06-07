package com.example.programmingtask.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

@SuppressLint("CoroutineCreationDuringComposition", "ShowToast")
fun DownloadImage(context : Context, imageUrl :String)
{
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    coroutineScope.launch {
        val remoteImageDeferred = coroutineScope.async(Dispatchers.IO) {
            getImageFromUrl(imageUrl)
        }
        val imageBitmap = remoteImageDeferred.await()
        // Saveing image in device
        FileUtil().saveImage(imageBitmap, context,"FlickrImage")
    }

}

private fun getImageFromUrl(IMAGE_URL: String): Bitmap =
    URL(IMAGE_URL).openStream().use {
        BitmapFactory.decodeStream(it)
    }


