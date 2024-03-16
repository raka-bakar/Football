package com.raka.spacex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.data.CallResult
import com.data.models.Launch
import com.raka.spacex.R
import com.valentinilk.shimmer.shimmer

/**
 *  entry point for All Launches List view UI page
 *  @param callResult result of a call for data
 *  @param navigateToDetail handle to open single launch page
 */
@Composable
fun AllLaunchesListScreen(
    callResult: CallResult<List<Launch>>,
    navigateToDetail: (String) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp, 0.dp)) {
        Spacer(modifier = Modifier.height(5.dp))
        TitleText(
            title = R.string.lbl_all_launches_list,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        when (callResult.status) {
            CallResult.Status.SUCCESS -> {
                val list = callResult.data
                if (list.isNullOrEmpty()) {
                    Text(
                        text = stringResource(id = R.string.lbl_data_empty),
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    AllLaunchesListView(list = list, navigateToDetail = navigateToDetail)
                }
            }
            CallResult.Status.LOADING, CallResult.Status.IDLE -> {
                LoadingList()
            }
            CallResult.Status.ERROR -> {
                Text(
                    text = stringResource(id = R.string.lbl_error),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color(R.color.error))
                )
            }
        }
    }
}

/**
 *  Component that composes All Launches Data
 *  @param list data of All Launches
 *  @param navigateToDetail handle for forwarding navigation
 */
@Composable
private fun AllLaunchesListView(list: List<Launch>, navigateToDetail: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(items = list) { index, launch ->
            LaunchItemView(item = launch) {
            }
            if (index < list.lastIndex) {
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

/**
 * Component that shows a skeleton of loading of a list
 */
@Composable
private fun LoadingList() {
    LazyColumn {
        items(count = 5) {
            LoadingItem()
        }
    }
}

/**
 * Component that composes loading view of one item
 */
@Composable
private fun LoadingItem() {
    Card(
        modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surface)
                    .shimmer()
            )
            Box(
                modifier = Modifier
                    .padding(2.dp, 5.dp)
                    .height(12.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .shimmer()
            )
        }
    }
}