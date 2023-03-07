package com.joblogic.todo.features.ui.custom

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.joblogic.todo.R

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit,
    color: Color = colorResource(
        id = R.color.white
    ),
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 2,
    shadow: Shadow? = null,
    allowScale: Boolean = false,
    fontWeight: FontWeight = FontWeight.Bold,
    fontStyle: FontStyle = FontStyle.Normal
) {
    var textSize by remember { mutableStateOf(fontSize) }

    Text(
        text = content,
        modifier = modifier,
        style = TextStyle(
            fontWeight = fontWeight,
            fontSize = textSize,
            color = color,
            shadow = shadow,
            fontStyle = fontStyle
        ),
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult ->
            if (allowScale) {
                val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1

                if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                    textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                }
            }
        },
    )
}