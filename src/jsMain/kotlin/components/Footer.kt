package components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.href
import org.jetbrains.compose.web.css.selectors.CSSSelector
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun Footer() {
    Div({
        classes("footer")
    }) {
        Span {
            Text("Made with 💙 by ")

            A(attrs = {
                href("https://github.com/anthonyhfm")
            }) {
                Text("Anthony Hofmeister")
            }
        }
    }
}