package com.artlogic.endoderapp.view.screens.ui_components

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun MatrixBackgroundAnimation() {
    val paint = remember { Paint() }
    val matrixChars = remember {
        ('0'..'9') +
                ('A'..'Z') +
                ('a'..'z') + listOf(
            '<',
            '>',
            '/',
            '?',
            '.',
            ',',
            ';',
            ':',
            '[',
            ']',
            '{',
            '}',
            '|',
            '-',
            '_',
            '+',
            '=',
            '(',
            ')',
            '*',
            '&',
            '^',
            '%',
            '$',
            '#',
            '@',
            '!',
            '~'
        )
    }

    var animationTime by remember { mutableStateOf(0L) }

    val columnWidth = paint.asFrameworkPaint().measureText("0")
    val columns = (LocalConfiguration.current.screenWidthDp / columnWidth).toInt()
    val height = LocalConfiguration.current.screenHeightDp
    val matrixColumn = remember {
        Array(columns) {
            MatrixColumn(
                height,
                columnWidth,
                paint.asFrameworkPaint(),
                matrixChars
            )
        }
    }

    val canvasBmp = Bitmap.createBitmap(columnWidth.toInt(), height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(canvasBmp)

    Canvas(modifier = Modifier.fillMaxSize()) {
        matrixColumn.forEachIndexed { index, it ->
            it.drawColumn(
                canvas,
                animationTime + index * 50
            )
        }
    }

    LaunchedEffect(key1 = true) {
        animationTime = withFrameNanos { it }
    }
}
