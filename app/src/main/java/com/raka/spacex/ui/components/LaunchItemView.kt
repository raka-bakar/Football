package com.raka.spacex.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.data.models.Launch
import com.raka.spacex.R
import com.raka.spacex.util.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchItemView(item: Launch, navigateToDetail: () -> Unit) {
    Card(
        onClick = navigateToDetail,
        modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (imageRef, titleRef, dateRef) = createRefs()
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imgUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.desc_img_feed),
                modifier = Modifier
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 10.dp, start = 5.dp)
            )
            Text(
                text = item.name,
                modifier = Modifier
                    .constrainAs(titleRef) {
                        start.linkTo(imageRef.end)
                        top.linkTo(parent.top)
                    }
                    .padding(
                        top = 10.dp,
                        start = 3.dp
                    )
            )
            Text(
                text = Utils.dateFormatter.format(item.date) ?: "",
                modifier = Modifier
                    .constrainAs(dateRef) {
                        start.linkTo(imageRef.end)
                        top.linkTo(titleRef.bottom)
                    }
                    .padding(
                        top = 3.dp,
                        start = 3.dp
                    )
            )
        }
    }
}