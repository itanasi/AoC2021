import java.io.File
import kotlin.system.exitProcess

/*
--- Day 4: Giant Squid ---

You're already almost 1.5km (almost a mile) below the surface of the ocean, already so deep that you can't see any sunlight. What you can see, however, is a giant squid that has attached itself to the outside of your submarine.

Maybe it wants to play bingo?

Bingo is played on a set of boards each consisting of a 5x5 grid of numbers. Numbers are chosen at random, and the chosen number is marked on all boards on which it appears. (Numbers may not appear on all boards.) If all numbers in any row or any column of a board are marked, that board wins. (Diagonals don't count.)

The submarine has a bingo subsystem to help passengers (currently, you and the giant squid) pass the time. It automatically generates a random order in which to draw numbers and a random set of boards (your puzzle input). For example:

7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7

After the first five numbers are drawn (7, 4, 9, 5, and 11), there are no winners, but the boards are marked as follows (shown here adjacent to each other to save space):

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7

After the next six numbers are drawn (17, 23, 2, 0, 14, and 21), there are still no winners:

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7

Finally, 24 is drawn:

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7

At this point, the third board wins because it has at least one complete row or column of marked numbers (in this case, the entire top row is marked: 14 21 17 24 4).

The score of the winning board can now be calculated. Start by finding the sum of all unmarked numbers on that board; in this case, the sum is 188. Then, multiply that sum by the number that was just called when the board won, 24, to get the final score, 188 * 24 = 4512.

To guarantee victory against the giant squid, figure out which board will win first. What will your final score be if you choose that board?

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

                        // do check if BINGO!
                        var bingo = 1
                        // Same ROW, check all columns
                        for (newCol in 0 until boardCols){
                            if (board[row][newCol] != ""){
                                bingo = 0
                                break
                            }
                        }
                        // Same COL, check all rows
                        if(bingo == 0) {
                            bingo = 1
                            for (newRow in 0 until boardRows) {
                                if (board[newRow][col] != "") {
                                    bingo = 0
                                    break
                                }
                            }
                        }
                        if (bingo == 1)
                        {
                            printBoard(board)
                            val boardVal = board.sumOf { it.filter{it.isNotBlank()}.sumOf { it.toInt() } }
                            val score = boardVal * draw.toInt()
                            println("boardVal=$boardVal draw=$draw score=$score")
                            return
                        }
                    }
                }
            }
        }

        printBoards(boards)
    }
}