package com.example.programmingtask.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.programmingtask.R
import com.example.programmingtask.utils.DownloadImage

@Composable
fun CustomAlertDialog(openDialog: MutableState<Boolean>, context : Context, url : String) {
    MaterialTheme {
        Column(modifier = Modifier.background(Color.White)) {
                AlertDialog(
                    onDismissRequest = { // Dismiss the dialog when the user clicks outside the dialog or on the back button
                        openDialog.value = false
                    },
                    title = {
                        Text( stringResource(id = R.string.confirm ), color = Color.Black)
                    },
                    text = {
                        Text(
                            stringResource(id = R.string.want_to_save ),
                            color = Color.Black
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                                DownloadImage(context, url)
                            }) {
                            Text( stringResource(id = R.string.yes ), color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text( stringResource(id = R.string.no ), color = Color.White)
                        }
                    }
                )
        }
    }
}