package components

import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

@Composable
fun Header() {
    Div({
        classes("header")
    }) {
        Img(src = "static/github-mark.svg", attrs = {
            onClick {
                window.location.href = "https://github.com/anthonyhfm/compose-web-tictactoe"
            }
        })
    }
}