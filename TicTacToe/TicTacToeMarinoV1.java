//libraries
import java.util.Scanner;

public class TicTacToeMarinoV1 {

    //main
    public static void main(String[] args) {

        //initialize - setup
        CellTTT[][] board = new CellTTT[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = new CellTTT('-');
            }
        }

        //variables
        Scanner input = new Scanner(System.in);
        int r;
        int c;
        boolean playing = true;
        boolean turn = true;
        int turns = 0;
        CellTTT blank = new CellTTT('-');

        //playing
        while (playing) {

            //printing
            printBoard(board);
            
            //input
            
            System.out.println("Row: ");
            r = (Integer.parseInt(input.nextLine()) - 1);
            System.out.println("Col: ");
            c = (Integer.parseInt(input.nextLine()) - 1);

            System.out.println();
            
            if (turn) {
                board[r][c] = new CellTTT('x');
            } else {
                board[r][c] = new CellTTT('o');
            }

            //check for win
            if (scanBoard(board)) {
                printBoard(board);
                playing = false;
                if (turn) {
                    System.out.println("Player X Wins!");
                } else {
                    System.out.println("Player O Wins!");

                }
            }

            //change turn
            if (turn) {
                turn = false;
            } else {
                turn = true;
            }

            turns += 1;

            //check for draw
            if (scanBoard(board) == false && turns == 9) {
                printBoard(board);
                playing = false;
                System.out.println("It's a draw!");
            }
        }
    }

    /* check for column win
    * @param winBoard the board with input
    * @return if there was a win or not
    */
    public static boolean colWin(CellTTT[][] winBoard) {
        for (int c = 0; c < 3; c++) {
            if (winBoard[0][c].getValue() == winBoard[1][c].getValue() && winBoard[0][c].getValue() == winBoard[2][c].getValue() && winBoard[0][c].getValue() != '-') {
                return true;
            }
        }
        return false;
    }

    /* check for row win
    * @param winBoard the board with input
    * @return if there was a win or not
    */
    public static boolean rowWin(CellTTT[][] winBoard) {
        for (int r = 0; r < 3; r++) {
            if (winBoard[r][0].getValue() == winBoard[r][1].getValue() && winBoard[r][2].getValue() == winBoard[r][1].getValue() && winBoard[r][0].getValue() != '-') {
                return true;
            }
        }
        return false;
    }

    /* check for win
    * @param winBoard the board with input
    * @return if there was a win or not
    */
    public static boolean scanBoard(CellTTT[][] winBoard) {
        if (colWin(winBoard) || rowWin(winBoard)) {
            return true;
        } 
        if (winBoard[0][0].getValue() == winBoard[1][1].getValue() && winBoard[0][0].getValue() == winBoard[2][2].getValue() && winBoard[0][0].getValue() != '-') {
            return true;
        }
        if (winBoard[0][2].getValue() == winBoard[1][1].getValue() && winBoard[0][2].getValue() == winBoard[2][0].getValue() && winBoard[1][1].getValue() != '-') {
            return true;
        }
        return false;
    }

    /* print the board
    * @param playBoard the board with input
    */
    public static void printBoard(CellTTT[][] playBoard) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                System.out.print(playBoard[r][c] + " ");
            }
            System.out.println();
        }
    }
}