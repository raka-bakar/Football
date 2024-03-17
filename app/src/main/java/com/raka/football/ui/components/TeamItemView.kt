package com.raka.football.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.data.models.TeamItem
import com.raka.football.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TeamItemView(item: TeamItem, navigateToDetail: (String) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .clickable { navigateToDetail(item.id) }
    ) {
        val (imageRef, nameRef, countryRef) = createRefs()
        val valueRef = createRef()
        GlideImage(
            model = item.image,
            contentDescription = stringResource(id = R.string.desc_img),
            modifier = Modifier
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .size(
                    width = dimensionResource(id = R.dimen.image_size),
                    height = dimensionResource(id = R.dimen.image_size)
                )
                .padding(dimensionResource(id = R.dimen.padding_large))
        )

        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .constrainAs(nameRef) {
                    start.linkTo(imageRef.end)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(
                    top = dimensionResource(id = R.dimen.padding_extra_large),
                    start = dimensionResource(id = R.dimen.padding_small)
                )
        )
        Text(
            text = item.country,
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(countryRef) {
                    start.linkTo(imageRef.end)
                    top.linkTo(nameRef.bottom)
                }
                .padding(
                    top = dimensionResource(id = R.dimen.padding_small),
                    start = dimensionResource(id = R.dimen.padding_small)
                )
        )

        Text(
            text = stringResource(id = R.string.value_format, item.value),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(valueRef) {
                    top.linkTo(countryRef.bottom)
                    end.linkTo(parent.end)
                }
                .padding(
                    end = dimensionResource(id = R.dimen.padding_extra_large)
                )
        )
    }
}