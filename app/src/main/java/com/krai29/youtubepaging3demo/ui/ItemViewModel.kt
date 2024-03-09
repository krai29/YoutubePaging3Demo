package com.krai29.youtubepaging3demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.krai29.youtubepaging3demo.data.Items
import com.krai29.youtubepaging3demo.paging.UserSource
import kotlinx.coroutines.flow.Flow

class ItemViewModel : ViewModel(){

    val user: Flow<PagingData<Items>> = Pager(PagingConfig(pageSize = 100)){
        UserSource()
    }.flow.cachedIn(viewModelScope)
}