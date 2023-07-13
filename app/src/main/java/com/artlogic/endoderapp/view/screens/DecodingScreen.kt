package com.artlogic.endoderapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artlogic.endoderapp.view.screens.ui_components.AppButton
import com.artlogic.endoderapp.view.screens.ui_components.AppOutputText
import com.artlogic.endoderapp.view.screens.ui_components.DecodeTextField
import com.artlogic.endoderapp.view_model.DecodeState

@Composable
fun DecodingScreen(
    decodeAction: (List<String>) -> Unit,
    clearAction: () -> Unit,
    screenState: State<DecodeState>,
) {
    val inputs = remember {
        mutableStateMapOf(0 to mutableStateOf(""))
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        AppButton(
            action = {
                if (inputs.size < 10) inputs[inputs.size] = mutableStateOf("")
            }, text = "Add Field To Decode",
            modifier = Modifier
                .padding(
                    bottom = 16.dp,
                )
                .fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
        ) {
            items(inputs.size) {
                DecodeBox(
                    index = it,
                    decodeValues = screenState.value.decodedList.split(","),
                    input = inputs[it]
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,

            ) {

            AppButton(
                action = {
                    decodeAction(inputs.map {
                        it.value.value
                    })
                },
                text = "Decode",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .wrapContentSize()
            )

            AppButton(
                action = {
                    inputs.forEach {
                        it.value.value = ""
                    }
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

@Composable
fun DecodeBox(
    index:Int,
    decodeValues: List<String>?,
    input: MutableState<String>?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        if (input != null) {
            DecodeTextField(
                hint = "Decode here",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                fieldValue = input,
            )
            if (decodeValues != null) {
                if (index < decodeValues.size) {
                    AppOutputText(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = decodeValues[index],
                    )
                }
            }
        }
    }
}
