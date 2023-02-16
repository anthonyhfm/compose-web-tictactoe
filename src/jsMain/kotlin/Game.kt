import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import components.Circle
import components.Cross
import components.Footer
import components.Header
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

const val fieldSize: Int = 3
var isPlayable: Boolean = true

val konamiCode: List<String> = listOf("arrowup", "arrowup", "arrowdown", "arrowdown", "arrowleft", "arrowright", "arrowleft", "arrowright", "b", "a")
var konamiIndex: Int = 0

var reverseMode: Boolean = false

fun checkForWinner(field: SnapshotStateList<String>): String? {
    val winningPaths: List<List<Int>> = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6),
    )

    for (path in winningPaths) {
        val checkPlayer: String? = if(field[path[0]] != "") field[path[0]] else null

        var playerHasRow = true;

        for(block in path) {
            if(field[block] != checkPlayer) {
                playerHasRow = false;
            }
        }

        if(playerHasRow) {
            for(i in 0 until 9) {
                val playerField = document.getElementById("player-button-$i")

                playerField?.classList?.add("unselectable")

                if (i in path) {
                    if (reverseMode) {
                        playerField?.classList?.add("loosing-field")
                    }
                    else {
                        playerField?.classList?.add("winning-field")
                    }
                }
            }

            return checkPlayer
        }
    }

    return null
}

fun konamiHandler(inputKey: String) {
    if(konamiCode[konamiIndex] == inputKey) {
        konamiIndex++

        if (konamiIndex == konamiCode.size) {
            reverseMode = !reverseMode

            document.getElementById("game-page")?.classList?.toggle("reverse-mode")

            konamiIndex = 0
        }
    }
    else {
        konamiIndex = 0
    }
}

@Composable
fun Game() {
    val field = remember {
        mutableStateListOf(
            "", "", "",
            "", "", "",
            "", "", ""
        )
    }

    val playerTurn = remember { mutableStateOf("X") }
    val gameIsWon = remember { mutableStateOf(false) }
    val gameTitle = remember { mutableStateOf("Tic Tac Toe 2-Player") }

    Div({
        id("game-page")
        classes("game-page")

        window.onkeydown = {
            konamiHandler(it.key.lowercase())
        }
    }) {
        Header()

        Div({
            classes("game-container")
        }) {
            Span({
                id("game-title")
                classes("game-title")
            }) {
                Text(gameTitle.value)
            }

            Div({
                classes("player-field")

                style {
                    gridTemplateColumns("repeat(${fieldSize}, 80px)")
                    gridTemplateRows("repeat(${fieldSize}, 80px)")
                }
            }) {
                for (i in 0 until 9) {
                    Div({
                        id("player-button-$i")
                        classes("player-button")

                        onClick {
                            if (field[i] == "" && isPlayable) {
                                field[i] = playerTurn.value

                                playerTurn.value = if (playerTurn.value == "X") "O" else "X"

                                val winner = checkForWinner(field)

                                if (winner != null) {
                                    gameTitle.value = "Player $winner has won the Game!"

                                    document.getElementById("game-title")?.classList?.add("expand")

                                    isPlayable = false
                                    gameIsWon.value = true
                                }
                            }
                        }
                    }) {
                        if (field[i] == "X") {
                            Cross()
                        }
                        else if (field[i] == "O") {
                            Circle()
                        }
                    }
                }
            }

            if (gameIsWon.value) {
                Button({
                    classes("retry-button")

                    onClick {
                        for (i in 0 until 9) {
                            field[i] = ""

                            val playerField = document.getElementById("player-button-$i")

                            playerField?.classList?.remove("winning-field")
                            playerField?.classList?.remove("loosing-field")
                            playerField?.classList?.remove("unselectable")
                        }

                        document.getElementById("game-title")?.classList?.remove("expand")

                        gameTitle.value = "Tic Tac Toe 2-Player"

                        gameIsWon.value = false;
                        isPlayable = true;
                    }
                }) {
                    Text("Retry")
                }
            }
        }

        Footer()
    }
}