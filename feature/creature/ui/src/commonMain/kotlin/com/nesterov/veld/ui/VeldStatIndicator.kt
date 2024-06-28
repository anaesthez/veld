package com.nesterov.veld.ui

import androidx.compose.foundation.Canvas
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import kotlin.math.abs

private const val PADDING_END = 10f

@Composable
fun VeldStatIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    stat: Int,
    color: Color = ProgressIndicatorDefaults.linearColor,
    trackColor: Color = ProgressIndicatorDefaults.linearTrackColor,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
) {
    val textMeasurer = rememberTextMeasurer()
    val coercedProgress = { progress().coerceIn(0f, 1f) }
    Canvas(
        modifier
            .semantics(mergeDescendants = true) {
                progressBarRangeInfo = ProgressBarRangeInfo(coercedProgress(), 0f..1f)
            }
    ) {
        val strokeWidth = size.height
        drawVeldIndicatorTrack(
            color = trackColor,
            strokeWidth = strokeWidth,
            strokeCap = strokeCap
        )
        drawVeldLinearIndicator(
            textMeasurer = textMeasurer,
            textStyle = textStyle,
            startFraction = 0f,
            endFraction = coercedProgress(),
            color = color,
            strokeWidth = strokeWidth,
            strokeCap = strokeCap,
            stat = stat,
        )
    }
}

private fun DrawScope.drawVeldIndicatorTrack(
    startFraction: Float = 0f,
    endFraction: Float = 1f,
    color: Color,
    strokeWidth: Float,
    strokeCap: StrokeCap,
) {
    val width = size.width
    val height = size.height
    // Start drawing from the vertical center of the stroke
    val yOffset = height / 2

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    // if there isn't enough space to draw the stroke caps, fall back to StrokeCap.Butt
    if (strokeCap == StrokeCap.Butt || height > width) {
        // Progress line
        drawLine(color, Offset(barStart, yOffset), Offset(barEnd, yOffset), strokeWidth)
    } else {
        // need to adjust barStart and barEnd for the stroke caps
        val strokeCapOffset = strokeWidth / 2
        val coerceRange = strokeCapOffset..(width - strokeCapOffset)
        val adjustedBarStart = barStart.coerceIn(coerceRange)
        val adjustedBarEnd = barEnd.coerceIn(coerceRange)

        if (abs(endFraction - startFraction) > 0) {
            // Progress line
            drawLine(
                color,
                Offset(adjustedBarStart, yOffset),
                Offset(adjustedBarEnd, yOffset),
                strokeWidth,
                strokeCap,
            )
        }
    }
}

private fun DrawScope.drawVeldLinearIndicator(
    textMeasurer: TextMeasurer,
    textStyle: TextStyle,
    startFraction: Float,
    endFraction: Float,
    stat: Int,
    color: Color,
    strokeWidth: Float,
    strokeCap: StrokeCap,
) {
    val width = size.width
    val height = size.height
    // Start drawing from the vertical center of the stroke
    val yOffset = height / 2

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    // if there isn't enough space to draw the stroke caps, fall back to StrokeCap.Butt
    if (strokeCap == StrokeCap.Butt || height > width) {
        // Progress line
        drawLine(color, Offset(barStart, yOffset), Offset(barEnd, yOffset), strokeWidth)

        drawStatOverProgress(
            textMeasurer = textMeasurer,
            textStyle = textStyle,
            isLtr = isLtr,
            barEnd = barEnd,
            barStart = barStart,
            yOffset = yOffset,
            stat = stat,
        )
    } else {
        // need to adjust barStart and barEnd for the stroke caps
        val strokeCapOffset = strokeWidth / 2
        val coerceRange = strokeCapOffset..(width - strokeCapOffset)
        val adjustedBarStart = barStart.coerceIn(coerceRange)
        val adjustedBarEnd = barEnd.coerceIn(coerceRange)

        if (abs(endFraction - startFraction) > 0) {
            // Progress line
            drawLine(
                color,
                Offset(adjustedBarStart, yOffset),
                Offset(adjustedBarEnd, yOffset),
                strokeWidth,
                strokeCap,
            )

            drawStatOverProgress(
                textMeasurer = textMeasurer,
                textStyle = textStyle,
                isLtr = isLtr,
                barEnd = adjustedBarEnd,
                barStart = adjustedBarStart,
                yOffset = yOffset,
                stat = stat,
            )
        }
    }
}

private fun DrawScope.drawStatOverProgress(
    textMeasurer: TextMeasurer,
    stat: Int,
    textStyle: TextStyle,
    isLtr: Boolean,
    barEnd: Float,
    barStart: Float,
    yOffset: Float,
) {
    val textLayoutResult = textMeasurer.measure(
        text = stat.toString(),
        style = textStyle,
    )

    val textWidth = textLayoutResult.size.width
    val textHeight = textLayoutResult.size.height
    val textOffsetX = if (isLtr) barEnd - textWidth else barStart
    val textOffsetY = yOffset - textHeight / 2

    drawText(
        textLayoutResult = textLayoutResult,
        topLeft = Offset(x = textOffsetX - PADDING_END, y = textOffsetY),
    )
}