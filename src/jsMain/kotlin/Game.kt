import androidx.compose.runtime.Composable
import components.Footer
import components.Header
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.css.gridTemplateRows
import org.jetbrains.compose.web.dom.*

const val fieldSize = 3

val field: List<String> = listOf(
    "", "", "",
    "", "", "",
    "", "", "",
)

@Composable
fun Game() {
    Div({
        classes("game-page")
    }) {
        Header()

        Div({
            classes("game-container")
        }) {
            Div({
                classes("player-field")

                style {
                    gridTemplateColumns("repeat(${fieldSize}, 80px)")
                    gridTemplateRows("repeat(${fieldSize}, 80px)")
                }
            }) {
                for (i in 1..fieldSize * fieldSize) {
                    Div({
                        classes("player-button")

                        if(field[i] != "") {
                            classes("unselectable")
                        }

                        onClick {
                            // TODO: Detect Player Move
                        }
                    }) {

                    }
                }
            }
        }

        Footer()
    }
}