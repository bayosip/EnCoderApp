package com.artlogic.endoderapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artlogic.endoderapp.view.screens.ui_components.AppButton
import com.artlogic.endoderapp.view.screens.ui_components.AppOutputText
import com.artlogic.endoderapp.view.screens.ui_components.EncodeTextField
import com.artlogic.endoderapp.view_model.EncodeState

@Composable
fun EncodingScreen(
    encodeAction: (String) -> Unit,
    clearAction: () -> Unit,
    screenState: State<EncodeState>,
) {
    val input = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        EncodeTextField(
            hint = "Encode here",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .height(100.dp),
            fieldValue = input
        )

        AppOutputText(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .height(200.dp),
            text = screenState.value.output
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,

            ) {

            AppButton(
                action = {
                   encodeAction(input.value)
                },
                text = "Encode",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .wrapContentSize()
            )

            AppButton(
                action = {
                    input.value = ""
                   clearAction()
                },
                text = "Clear",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .wrapContentSize()
            )
        }
    }
}