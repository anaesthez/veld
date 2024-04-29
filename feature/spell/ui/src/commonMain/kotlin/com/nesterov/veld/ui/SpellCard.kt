package com.nesterov.veld.ui

//
//const val STRIPE_WIDTH = 30f
//const val INDEX_INDENTATION = 4.5f
//
//@Composable
//private fun SpellCard(
//    spellName: String,
//    spellCircle: String,
//    components: String,
//    magicSchool: MagicSchool,
//) {
//    val textMeasurer = rememberTextMeasurer()
//    val spellTypes = listOf("AAA", "BBB")
//    Card(
//        modifier = Modifier
//            .size(200.dp)
//            .background(Color(0xFFE9BC1C), CardDefaults.shape)
//    ) {
//        Box {
//            SpellCircle(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                spellCircle = spellCircle,
//            )
//            SpellType(
//                textMeasurer = textMeasurer,
//                spells = spellTypes,
//                stripePadding = 2f,
//                stripeIndentation = 200f
//            )
//        }
//    }
//}
//
//@Composable
//private fun SpellType(
//    textMeasurer: TextMeasurer,
//    stripesColor: Color = Color.White,
//    spells: List<String>,
//    stripePadding: Float,
//    stripeIndentation: Float,
//) {
//    var padding = STRIPE_WIDTH + stripePadding
//    spells.forEachIndexed { index, spell ->
//        padding += if (index == 0) 0f else stripePadding
//        Canvas(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            val maximumX = size.width
//            val maximumY = size.height
//
//            val sideOfTriangleX = (maximumX - stripeIndentation) + padding
//            val sideOfTriangleY = stripeIndentation - padding
//            val sideOfTriangleXY = pythagorasTheorem(sideOfTriangleX, sideOfTriangleY)
//
//            val topStripeCos = sideOfTriangleY.cosinesTheorem(sideOfTriangleX, sideOfTriangleXY)
//            val botStripeCos = sideOfTriangleX.cosinesTheorem(sideOfTriangleY, sideOfTriangleXY)
//
//            drawStripe(
//                rightTopPadding = sideOfTriangleX,
//                rightBotPadding = sideOfTriangleY,
//            )
//
//            val textLayoutResult = textMeasurer.measure(
//                text = spell,
//                style = TextStyle(
//                    color = Color.Black,
//                    fontSize = 12.sp
//                )
//            )
//            drawTextOverStripe(
//                textLayoutResult = textLayoutResult,
//                rotationAngleInRad = topStripeCos,
//                textPlacement = Offset(
//                    x = sideOfTriangleX + textLayoutResult.size.width + STRIPE_WIDTH,
//                    y = ((sideOfTriangleXY / 2) * botStripeCos) - textLayoutResult.size.height,
//                ),
//            )
//        }
//    }
//}
//
//private fun DrawScope.drawStripe(
//    rightTopPadding: Float,
//    rightBotPadding: Float,
//) {
//    drawPath(
//        path = Path().apply {
//            moveTo(x = rightTopPadding - STRIPE_WIDTH, y = 0f)
//
//            lineTo(x = rightTopPadding + STRIPE_WIDTH, y = 0f)
//            lineTo(x = size.width, y = rightBotPadding - STRIPE_WIDTH)
//            lineTo(x = size.width, y = rightBotPadding + STRIPE_WIDTH)
//            lineTo(x = rightTopPadding - STRIPE_WIDTH, y = 0f)
//
//            close()
//        },
//        color = Color.White,
//        style = Fill
//    )
//}
//
//private fun DrawScope.drawTextOverStripe(
//    textLayoutResult: TextLayoutResult,
//    textPlacement: Offset,
//    rotationAngleInRad: Float,
//) {
//    rotateRad(
//        radians = rotationAngleInRad,
//        pivot = textPlacement,
//    ) {
//        drawText(
//            textLayoutResult = textLayoutResult,
//            topLeft = textPlacement,
//        )
//    }
//}
//
//private fun DrawScope.drawSpellCircle(
//    textMeasurer: TextMeasurer,
//    spellCircle: String,
//    cardX: Float,
//    cardY: Float,
//) {
//    drawRoundRect(
//        size = calculateSpellCircleSize(),
//        color = Color.White,
//        cornerRadius = CornerRadius(x = 16f, y = 16f),
//        topLeft = Offset(x = cardX / 12f,y = cardY / 12f)
//    )
//    drawText(
//        text = spellCircle,
//        textMeasurer = textMeasurer,
//        style = TextStyle(fontSize = 18.sp, color = Color.Black),
//        topLeft = Offset(x = cardX / 12f + 1f,y = cardY / 12f + 1f)
//    )
//}
//
//private fun DrawScope.calculateSpellCircleSize(): Size = size / 8f
//
//
//@Composable
//private fun SpellCircle(
//    modifier: Modifier = Modifier,
//    spellCircle: String
//) {
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.Start,
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        Box(
//            modifier = Modifier
//                .size(25.dp)
//                .background(VeldTheme.colors.textColorPrimary, RoundedCornerShape(8.dp))
//        ) {
//            Text(
//                modifier = Modifier.align(Alignment.Center),
//                color = VeldTheme.colors.textColorSecondary,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center,
//                fontSize = 16.sp,
//                text = spellCircle,
//            )
//        }
//    }
//}