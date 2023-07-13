package com.artlogic.endoderapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.endoderapp.R
import com.artlogic.endoderapp.view.screens.ui_components.AppButton

@Composable
fun SelectionScreen(
    navigateToDecoder: () -> Unit,
    navigateToEncoder: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.selection_msg),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f)
                .wrapContentHeight(),
            color = Color.Black,
        )

        AppButton(
            action = {
                navigateToEncoder()
            },
            text = "Encoder",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        )

        AppButton(
            action = {
                navigateToDecoder()
            },
            text = "Decoder",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}