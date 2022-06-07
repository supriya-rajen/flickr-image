package com.example.programmingtask.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programmingtask.R
import com.example.programmingtask.ui.theme.BLUE

@Composable
fun  TopHeaderBar(context : Context, showSave : Boolean, url : String) {

    val openDialog = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(BLUE)
            .padding(6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    )

    {
        Text(
            text = stringResource(id = R.string.title ),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,

            modifier = Modifier
                .padding(20.dp, 0.dp, 0.dp, 0.dp)
                .align(CenterVertically)
        )
        if (showSave) {
            Text(
                text =  stringResource(id = R.string.save ),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier
                    .padding(5.dp)
                    .align(CenterVertically)
                    .clickable {
                        openDialog.value = true
                    }
            )
        }
    }

    if(openDialog.value){
        CustomAlertDialog(openDialog,context,url)
    }
}





