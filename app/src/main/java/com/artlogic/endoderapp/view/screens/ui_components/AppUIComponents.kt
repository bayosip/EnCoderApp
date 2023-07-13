package com.artlogic.endoderapp.view.screens.ui_components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(
    action: () -> Unit,
    text: String,
    modifier: Modifier,
) {
    Button(
        onClick = { action() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Green,
            contentColor = Color.White),
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
        )
    }
}

@Composable
fun DecodeTextField(
    hint: String,
    modifier: Modifier,
    fieldValue: MutableState<String>
) {
    TextField(
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
        value = fieldValue.value,
        onValueChange = {
            fieldValue.value = it
        },
        label = {
            Text(
                text = hint,
                fontSize = 16.sp
            )
        }
    )
}

@Composable
fun EncodeTextField(
    hint: String,
    modifier: Modifier,
    fieldValue: MutableState<String>
) {
    TextField(
        modifier = modifier,
        value = fieldValue.value,
        onValueChange = {
            fieldValue.value = it
        },
        label = {
            Text(
                text = hint,
                fontSize = 16.sp
            )
        }
    )
}


@Composable
fun AppOutputText(
    modifier: Modifier,
    text: String = ""
) {
    Text(
        text = text,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = Color.DarkGray,
        modifier = modifier
    )
}