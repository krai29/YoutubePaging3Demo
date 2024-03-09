package com.krai29.youtubepaging3demo.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.krai29.youtubepaging3demo.data.Items
import kotlinx.coroutines.flow.Flow

@Composable
fun AppComposable(){
    val itemViewModel = ItemViewModel()
    val context = LocalContext.current
    MaterialTheme {
        UserList( viewModel = itemViewModel, context = context)
    }
}

@Composable
fun UserList(modifier: Modifier = Modifier, viewModel: ItemViewModel,context: Context){
    UserInfoList(modifier,userList = viewModel.user, context)
}

@Composable
fun UserInfoList(modifier: Modifier, userList: Flow<PagingData<Items>>, context: Context) {
    val userListItems: LazyPagingItems<Items> = userList.collectAsLazyPagingItems()

    LazyColumn{
        items(userListItems.itemCount){ item ->
            ListViewItem(item = userListItems[item]!!)
        }

        userListItems.apply {
            when{
                loadState.refresh is LoadState.Loading -> {
                }
                loadState.append is LoadState.Loading -> {

                }
                loadState.append is LoadState.Error -> {
                    
                }
            }
        }
    }
}

@Composable
fun ListViewItem(item: Items) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(context)
                .data("${item.snippet.thumbnails.high.url}")
                .crossfade(true)
                .build(),
            contentDescription = "${item.snippet.description}",
            modifier = Modifier.size(480.dp, 360.dp),
            contentScale = ContentScale.Crop,
            error = painterResource(id = android.R.drawable.stat_notify_error),
            placeholder = painterResource(id = android.R.drawable.presence_video_online)
        )
        Text(
            text = "${item.snippet.title}",
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${item.snippet.description}",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Blue,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${item.snippet.channelTitle}",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Green,
            fontWeight = FontWeight.Bold
        )
    }
}
