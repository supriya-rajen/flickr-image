package com.example.programmingtask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.programmingtask.ListSourcePagination

import com.example.programmingtask.model.ImageData
import kotlinx.coroutines.flow.Flow


class ListImageViewModel : ViewModel() {
    val user: Flow<PagingData<ImageData>> = Pager(PagingConfig(pageSize = 6)) {
        ListSourcePagination()
    }.flow.cachedIn(viewModelScope)
}