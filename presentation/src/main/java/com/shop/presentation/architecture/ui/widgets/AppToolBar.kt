package com.shop.presentation.architecture.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.shop.presentation.R
import com.shop.presentation.architecture.ui.theme.DEFAULT_PADDING_SIZE
import com.shop.presentation.architecture.ui.theme.SIZE_30_DP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
    title: String,
    image: Any?,
    content: @Composable (PaddingValues) -> Unit,
    onBackIconPressed: (() -> Unit)? = null
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row {
                        ImageLoader(
                            imageData = image,
                            Modifier
                                .size(SIZE_30_DP)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(DEFAULT_PADDING_SIZE))
                        Text(text = title, style = MaterialTheme.typography.displaySmall)
                    }
                },
                navigationIcon = {
                    if (onBackIconPressed != null) {
                        IconButton(onClick = { onBackIconPressed() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(
                                    id = R.string.back_to_previous_screen
                                )
                            )
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        content(innerPadding)
    }
}