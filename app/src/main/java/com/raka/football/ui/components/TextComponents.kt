package com.raka.football.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleText(@StringRes title: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = title),
        textAlign = TextAlign.Center,
        modifier = modifier,
        style = MaterialTheme.typography.displaySmall
    )
}