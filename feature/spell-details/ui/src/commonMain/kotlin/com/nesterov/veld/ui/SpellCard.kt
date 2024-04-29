package com.nesterov.veld.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.rotateRad
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesterov.veld.helpers.cosinesTheorem
import com.nesterov.veld.helpers.pythagorasTheorem
import com.nesterov.veld.presentation.model.utils.MagicSchool

const val STRIPE_WIDTH = 30f
const val INDEX_INDENTATION = 4.5f

@Composable
private fun SpellCard(
    spellName: String,
    spellCircle: String,
    components: String,
    magicSchool: MagicSchool,
) {
    val textMeasurer = rememberTextMeasurer()
    val spellTypes = listOf("AAA", "BBB")
    Card(
        modifier = Modifier
            .size(200.dp)
            .background(Color(0xFFE9BC1C), CardDefaults.shape)
    ) {
        Box {
            SpellType(
                textMeasurer = textMeasurer,
                spells = spellTypes,
                stripePadding = 2f,
                stripeIndentation = 200f
            )
        }
    }
}

@Composable
private fun SpellType(
    textMeasurer: TextMeasurer,
    stripesColor: Color = Color.White,
    spells: List<String>,
    stripePadding: Float,
    stripeIndentation: Float,
) {
    var padding = STRIPE_WIDTH + stripePadding
    spells.forEachIndexed { index, spell ->
        padding += if (index == 0) 0f else stripePadding
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val maximumX = size.width
            val maximumY = size.height

            val sideOfTriangleX = (maximumX - stripeIndentation) + padding
            val sideOfTriangleY = stripeIndentation - padding
            val sideOfTriangleXY = pythagorasTheorem(sideOfTriangleX, sideOfTriangleY)

            val topStripeCos = sideOfTriangleY.cosinesTheorem(sideOfTriangleX, sideOfTriangleXY)
            val botStripeCos = sideOfTriangleX.cosinesTheorem(sideOfTriangleY, sideOfTriangleXY)

            drawStripe(
                rightTopPadding = sideOfTriangleX,
                rightBotPadding = sideOfTriangleY,
            )

            val textLayoutResult = textMeasurer.measure(
                text = spell,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp
                )
            )
            drawTextOverStripe(
                textLayoutResult = textLayoutResult,
                rotationAngleInRad = topStripeCos,
                textPlacement = Offset(
                    x = sideOfTriangleX + textLayoutResult.size.width + STRIPE_WIDTH,
                    y = ((sideOfTriangleXY / 2) * botStripeCos) - textLayoutResult.size.height,
                ),
            )
        }
    }
}

private fun DrawScope.drawStripe(
    rightTopPadding: Float,
    rightBotPadding: Float,
) {
    drawPath(
        path = Path().apply {
            moveTo(x = rightTopPadding - STRIPE_WIDTH, y = 0f)

            lineTo(x = rightTopPadding + STRIPE_WIDTH, y = 0f)
            lineTo(x = size.width, y = rightBotPadding - STRIPE_WIDTH)
            lineTo(x = size.width, y = rightBotPadding + STRIPE_WIDTH)
            lineTo(x = rightTopPadding - STRIPE_WIDTH, y = 0f)

            close()
        },
        color = Color.White,
        style = Fill
    )
}

private fun DrawScope.drawTextOverStripe(
    textLayoutResult: TextLayoutResult,
    textPlacement: Offset,
    rotationAngleInRad: Float,
) {
    rotateRad(
        radians = rotationAngleInRad,
        pivot = textPlacement,
    ) {
        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = textPlacement,
        )
    }
}

private fun DrawScope.drawSpellCircle(
    textMeasurer: TextMeasurer,
    spellCircle: String,
    cardX: Float,
    cardY: Float,
) {
    drawRoundRect(
        size = calculateSpellCircleSize(),
        color = Color.White,
        cornerRadius = CornerRadius(x = 16f, y = 16f),
        topLeft = Offset(x = cardX / 12f,y = cardY / 12f)
    )
    drawText(
        text = spellCircle,
        textMeasurer = textMeasurer,
        style = TextStyle(fontSize = 18.sp, color = Color.Black),
        topLeft = Offset(x = cardX / 12f + 1f,y = cardY / 12f + 1f)
    )
}

private fun DrawScope.calculateSpellCircleSize(): Size = size / 8f