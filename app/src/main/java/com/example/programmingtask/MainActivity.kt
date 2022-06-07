package com.example.programmingtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.programmingtask.ui.theme.ProgrammingTaskTheme
import com.example.programmingtask.view.NavigatePage
import com.example.programmingtask.viewModel.ListImageViewModel
import com.example.programmingtask.viewModel.GridImageViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : ComponentActivity(){
    private val gridViewModel: GridImageViewModel by viewModels()
    private val listViewModel: ListImageViewModel by viewModels()

    @OptIn(ExperimentalFoundationApi::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrammingTaskTheme {
                NavigatePage(gridViewModel,listViewModel)
            }
        }
    }
}

