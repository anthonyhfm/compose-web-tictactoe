package components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

@Composable
fun Header() {
    Div({
        classes("header")
    }) {
        Img(src = "static/github-mark.svg")
    }
}