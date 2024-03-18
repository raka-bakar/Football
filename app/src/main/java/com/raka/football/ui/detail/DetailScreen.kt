package com.raka.football.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.data.CallResult
import com.data.models.TeamItem
import com.raka.football.R
import com.raka.football.ui.components.DisplayError
import com.raka.football.ui.components.LoadingView
import com.raka.football.util.getAnnotatedStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    callResult: CallResult<TeamItem>,
    onBackClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.desc_img),
                        tint = Color.White
                    )
                }
            },
            title = {
                if (callResult.status == CallResult.Status.SUCCESS) {
                    Text(
                        text = callResult.data?.name ?: "",
                        color = colorResource(id = R.color.white)
                    )
                }
            },
            colors = TopAppBarDefaults
                .topAppBarColors(containerColor = colorResource(id = R.color.green))
        )

        when (callResult.status) {
            CallResult.Status.SUCCESS -> {
                val footballTeam = callResult.data
                if (footballTeam != null) {
                    FootballTeamDetail(item = footballTeam)
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FootballTeamDetail(item: TeamItem) {
    Column {
        ConstraintLayout(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.detail_container_size))
                .fillMaxWidth()
                .background(color = Color.DarkGray),
        ) {
            val (logoRef, nameRef) = createRefs()

            GlideImage(
                model = item.image,
                contentDescription = stringResource(id = R.string.desc_img),
                modifier = Modifier
                    .constrainAs(logoRef) {
                        centerTo(parent)
                    }
                    .size(
                        width = dimensionResource(id = R.dimen.detail_image_size),
                        height = dimensionResource(id = R.dimen.detail_image_size)
                    )
            )
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(nameRef) {
                        start.linkTo(parent.start)
                        top.linkTo(logoRef.bottom)
                    }
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_extra_large),
                        start = dimensionResource(id = R.dimen.padding_medium)
                    )
            )
        }

        Text(
            text = getAnnotatedStrings(
                sentence = stringResource(
                    id = R.string.detail_team_format,
                    item.name,
                    item.country,
                    item.value,
                    item.name,
                    item.europeanTitles
                ),
                markedWord = item.name
            ),
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    dimensionResource(id = R.dimen.padding_medium)
                )
        )
    }
}