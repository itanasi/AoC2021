import java.io.File
import kotlin.system.exitProcess

/*
--- Day 4: Giant Squid ---

--- Part Two ---

On the other hand, it might be wise to try a different strategy: let the giant squid win.

You aren't sure how many bingo boards a giant squid could play at once, so rather than waste time counting its arms, the safe thing to do is to figure out which board will win last and choose that one. That way, no matter which boards it picks, it will win for sure.

In the above example, the second board is the last to win, which happens after 13 is eventually called and its middle column is completely marked. If you were to keep playing until this point, the second board would have a sum of unmarked numbers equal to 148 for a final score of 148 * 13 = 1924.

Figure out which board will win last. Once it wins, what would its final score be?

 */
fun printBoard(board: Array<Array<String>>)
{
    for (row in board) {
        for (entry in row) {
            print(" $entry,")
        }
        print("\n")
    }
}
fun printBoards(boards: Array<Array<Array<String>>>)
{
    var index = 0
    for (board in boards){
        println("board $index")
        printBoard(board)
        println()
        index++
    }
}

fun bingo(board: Array<Array<String>> ): Boolean {
    val boardRows = board.size
    val boardCols = board.first().size
    // do check if BINGO!
    var bingo = 1
    // Same ROW, check all columns
    for (row in 0 until boardCols) {
        for (newCol in 0 until boardCols) {
            if (board[row][newCol] != "") {
                bingo = 0
                break
            }
        }
    }
    // Same COL, check all rows
    if (bingo == 0) {
        bingo = 1
        for (col in 0 until boardCols) {
            for (newRow in 0 until boardRows) {
                if (board[newRow][col] != "") {
                    bingo = 0
                    break
                }
            }
        }
    }
    return (bingo == 1)
}

fun main() {
    println("Hello World")
    val filename = "input"
    val lines = File(filename).readLines().toTypedArray()  // returns List<String>

    val boardRows = 5
    val boardCols = 5

    val iDraws = lines.first().split(",")
    val boardLines = lines.drop(2) // drop first line
    // [board][line (y)][col (x)]
    // Note line order doesn't matter. Input is top-to-bottom, so index 0 = top
    var boards = arrayOf<Array<Array<String>>>()
    var boardRow = 0
    var board = arrayOf<Array<String>>()
    for (line in boardLines)
    {
        // should happen on blank line, so can conveniently continue
        if (boardRow == boardRows) {
            boardRow = 0
            continue
        }
        val rowLine = line.split(" ")
        var row = arrayOf<String>()
        for (i in rowLine.filter { it.isNotBlank() }) {
            row += i
        }
        board += row
        boardRow++
        if (boardRow == boardRows) {
            boards += board
            board = arrayOf<Array<String>>()
        }
    }
    printBoards(boards)  // debug check

    // now start removing BINGO spots
    var lastWinningBoard = arrayOf<Array<String>>()
    var lastWinningDraw = -1
    var lastWinningScore = 0
    for (draw in iDraws)
    {
        println("draw:$draw")
        for (board in boards)
        {
            for (row in 0 until boardRows){
                for (col in 0 until boardCols)
                {
                    if (board[row][col] == draw){
                        board[row][col] = ""  // blank it out!
                    }
                }
            }
            if( bingo(board)){
                lastWinningBoard = board
                lastWinningDraw = draw.toInt()
                lastWinningScore = board.sumOf { it.filter{it.isNotBlank()}.sumOf { it.toInt() } } * lastWinningDraw
                println(lastWinningScore)
            }
        }
        boards = boards.filter { !bingo(it) }.toTypedArray()
        //printBoards(boards)
    }
    printBoard(lastWinningBoard)
    println("last score=$lastWinningScore")
}