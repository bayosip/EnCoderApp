package com.artlogic.endoderapp.view.screens.ui_components

import android.graphics.Paint
import android.graphics.Canvas
import kotlin.random.Random

class MatrixColumn(
    private val height: Int,
    private val width: Float,
    private val paint: Paint,
    private val matrixChars: List<Char>
) {
    private var lastY: Float = 0f
    private val lines = (0..(height / width).toInt()).map { it * width }

    private val chars = (0..Random.nextInt(10, 20)).map { Random.nextInt(matrixChars.size) }

    fun drawColumn(canvas: Canvas, time: Long) {
        paint.alpha = Random.nextInt(10, 255)

        chars.forEachIndexed { index, char ->
            val x = index * width

            canvas.drawText(
                matrixChars[char].toString(),
                x,
                lastY - lines.last() + lines[index % lines.size] + ((time * 0.0000005f * Random.nextInt(
                    50,
                    150
                ))),
                paint,
            )
        }

        lastY += width

        if (lastY > height) {
            lastY -= (lines.size * width)
            val mChars = chars.toMutableList()
            mChars.forEachIndexed { index, _ ->
                mChars[index] = Random.nextInt(matrixChars.size)
            }
        }
    }
}