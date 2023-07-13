package com.artlogic.endoderapp.view.toolbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.endoderapp.R
import com.artlogic.endoderapp.view.presentation.ScreenNames

@Composable
fun DecoderToolBar(
    backAction: () -> Unit,
    currentScreen: String,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        },
        backgroundColor = Color.Green,
        navigationIcon = {
            if (currentScreen != ScreenNames.SELECTION) {
                IconButton(onClick = {
                    backAction()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go Back",
                        tint = Color.White,
                    )
                }
            }
        }
    )
}