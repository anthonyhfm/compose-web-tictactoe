import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import components.Footer
import components.Header
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.*

@Composable
fun Landing() {
    val showingLanding = remember { mutableStateOf(true) }

    Div({
        classes("landing-page")

        if(!showingLanding.value) {
            this.classes("hidden")
        }
    }) {
        Header()

        Div({
            classes("landing-welcome-container")
        }) {
            Span({
                classes("built-with-label")
            }) {
                Text("This App was built using")
            }

            Span({
                classes("jetpack-compose-label")
            }) {
                Text("Jetpack Compose")
            }

            Button({
                classes("goto-game-button")

                onClick {
                    showingLanding.value = false
                }
            }) {
                Text("Play a game of TicTacToe")

                Img(src = "static/side-panel--open--filled.svg")
            }
        }

        Footer()
    }
}