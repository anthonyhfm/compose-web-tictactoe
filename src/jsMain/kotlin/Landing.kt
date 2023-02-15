import androidx.compose.runtime.Composable
import components.Footer
import components.Header
import org.jetbrains.compose.web.dom.*

@Composable
fun Landing() {
    Div({
        classes("landing-page")
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
            }) {
                Text("Play a game of TicTacToe")

                Img(src = "static/side-panel--open--filled.svg")
            }
        }

        Footer()
    }
}