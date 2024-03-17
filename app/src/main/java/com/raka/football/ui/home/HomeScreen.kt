package com.raka.football.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.data.CallResult
import com.data.models.TeamItem
import com.raka.football.R
import com.raka.football.ui.components.DisplayError
import com.raka.football.ui.components.LoadingView
import com.raka.football.ui.components.TeamItemView

/**
 *  entry point for All Football Teams List view UI page
 *  @param callResult result of a call for data
 *  @param navigateToDetail handle to open detail team page
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    callResult: CallResult<List<TeamItem>>,
    onSortClicked: () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.title),
                    color = colorResource(id = R.color.white)
                )
            },

            actions = {
                if (callResult.status == CallResult.Status.ERROR) {
                    // remove icon
                } else {
                    IconButton(onClick = onSortClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter_list),
                            contentDescription = stringResource(id = R.string.desc_img),
                            tint = Color.White
                        )
                    }
                }
            },
            colors = TopAppBarDefaults
                .topAppBarColors(containerColor = colorResource(id = R.color.green))
        )
        when (callResult.status) {
            CallResult.Status.SUCCESS -> {
                val list = callResult.data
                if (list.isNullOrEmpty()) {
                    DisplayError(message = stringResource(id = R.string.lbl_data_empty))
                } else {
                    AllTeamsListView(list = list, navigateToDetail = navigateToDetail)
                }
            }

            CallResult.Status.LOADING, CallResult.Status.IDLE -> {
                LoadingView()
            }

            CallResult.Status.ERROR -> {
                DisplayError(message = stringResource(id = R.string.lbl_error))
            }
        }
    }
}

/**
 *  Component that composes All Teams Data
 *  @param list data of All Football Teams
 *  @param navigateToDetail handle for forwarding navigation
 */
@Composable
private fun AllTeamsListView(list: List<TeamItem>, navigateToDetail: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(items = list) { index, team ->
            TeamItemView(item = team, navigateToDetail = navigateToDetail)
            if (index < list.lastIndex) {
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}