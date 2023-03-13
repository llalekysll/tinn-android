package com.example.tinn.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tinn.ui.theme.Blue

@Composable
fun TextWithUnderLine(
    text: String,
    textColor: Color = MaterialTheme.colors.onSurface,
    lineColor: Color = MaterialTheme.colors.primary,
    style: TextStyle = TextStyle(),
    width: Float = 5f,
    lineIsVisible: Boolean = true,
    paddingBottomOffset: Float = 10f,
    modifier: Modifier = Modifier
) {
    var layout by remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(
        text = text,
        style = style,
        color = textColor,
        onTextLayout = {
            layout = it
        },
        modifier = modifier
            .drawBehind {
                if (lineIsVisible) {
                    layout?.let {
                        drawPath(
                            path = Path().apply {
                                moveTo(it.getLineLeft(0), it.getLineBottom(0) + paddingBottomOffset)
                                lineTo(
                                    it.getLineRight(0),
                                    it.getLineBottom(0) + paddingBottomOffset
                                )
                            },
                            lineColor,
                            style = Stroke(width = width)
                        )
                    }
                }
            }
            .padding(bottom = (paddingBottomOffset + 10f).dp)
    )
}