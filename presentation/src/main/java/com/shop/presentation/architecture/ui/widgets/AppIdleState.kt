package com.shop.presentation.architecture.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.shop.presentation.R

@Composable
fun AppIdleState(modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Text(
            text = stringResource(R.string.idle_state_message),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}