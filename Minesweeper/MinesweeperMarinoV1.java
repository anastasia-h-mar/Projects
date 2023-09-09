import java.util.Scanner;
import java.util.Random;

public class MinesweeperMarinoV1 { 

    private static final int GAME_WON = 1;
    private static final int GAME_LOST = -1;
    private static final int GAME_NOTOVER = 0;
    
    // Add other static variables and constants you might need
    private static Cell[][] grid;


    public static void main(String[] args) {

        //initialize
        Scanner input = new Scanner(System.in);

        //VARS
        //board setup
        System.out.print("Rows? ");
        int rows = Integer.parseInt(input.nextLine());
        System.out.println();
        System.out.print("Columns? ");
        int cols = Integer.parseInt(input.nextLine());
        System.out.println();
        System.out.print("Mines? ");
        int mines = Integer.parseInt(input.nextLine());

        //vars set up
        int row;
        int col;
        boolean playing = true;
        int wl = 0;
        String reveal;

        //making field
        initGrid(rows, cols);
        disperseMines(mines);
        adjacentMines();
        printGrid();

        System.out.println();

        //playing
        while (playing) {

            //reveal grid check
            System.out.print("Reveal? ");
            reveal = input.nextLine();
            if (reveal.equals("yes") || reveal.equals("Yes")) {
                printAnswerKey();
            }

            //reveal cell check
            System.out.print("Row to reveal? ");
            row = Integer.parseInt(input.nextLine());
            System.out.println();
            System.out.print("Col to reveal? ");
            col = Integer.parseInt(input.nextLine());
            System.out.println();
            wl = revealCell(row, col);

            //check for win/loss
            if (wl != 0) {
                playing = false;
            }
            if (checkGameOver()) {
                playing = false;
            }
        }
        
        //print out win/loss
        if (wl < 0) {
            System.out.println();
            System.out.println("You lost!");
            System.out.println();
            printAnswerKey();
        } else {
            System.out.println("You won!");
        }
    }


    /* 
     * Create the grid.
     *
     * @param rows     The number of rows in the Minesweeper grid
     * @param columns  The number of columns in the Minesweeper grid
     *
     * Tip: Create Minesweeper grid with 2 extra rows and 2 extra columns
     *      This will make it easy to go around the grid eliminating
     *      the need for ArrayOutOfBounds checking at the edges.
     */
    public static void initGrid(int rows, int columns) {
        grid = new Cell[rows + 2][columns + 2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Cell();
            }
        }
    }
    
    /*
     * Places mines in random locations in the grid.
     *
     * @param amountMines   The number of mines to be set in the grid.
     */
    public static void disperseMines(int amountMines) {
        Random rand = new Random();
        int randRow;
        int randCol;
        for (int i = 0; i < amountMines; i++) {
            randRow = rand.nextInt(grid.length - 2) + 1;
            randCol = rand.nextInt(grid[0].length - 2) +1;
            while (grid[randRow][randCol].isMine()) {
                randRow = rand.nextInt(grid.length - 2) + 1;
                randCol = rand.nextInt(grid[0].length - 2) +1;
            }
            grid[randRow][randCol].setMine();            
        }
    }

    /*
     * Updates each cell with the number of adjacent cells with mines
     */
    public static void adjacentMines() {
        int num;
        for (int r = 1; r < grid.length - 1; r++) {
            for (int c = 1; c < grid[0].length - 1; c++) {
                num = 0;
                if (grid[r - 1][c - 1].isMine()) {
                    num++;
                }
                if (grid[r - 1][c].isMine()) {
                    num++;
                }
                if (grid[r - 1][c + 1].isMine()) {
                    num++;
                }
                if (grid[r][c - 1].isMine()) {
                    num++;
                }
                if (grid[r][c + 1].isMine()) {
                    num++;
                }
                if (grid[r + 1][c - 1].isMine()) {
                    num++;
                }
                if (grid[r + 1][c].isMine()) {
                    num++;
                }
                if (grid[r + 1][c + 1].isMine()) {
                    num++;
                }
                grid[r][c].setAdjacentMines(num);
            }
        }
    }
 
    /*
     * Method to print Minesweeper grid
     */
    private static void printGrid() {
        for (int r = 1; r < grid.length - 1; r++) {
            for (int c = 1; c < grid[0].length - 1; c++) {
                System.out.print(grid[r][c].getVal());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
     * Method to show the "answer key"
     * If the cell is a mine, print out a mine.
     * Otherwise, print out the number of adjacent mines. 
     * For 0, print '.'.
     */
    public static void printAnswerKey() {
        int mines;
        for (int r = 1; r < grid.length - 1; r++) {
            for (int c = 1; c < grid[0].length - 1; c++) {
                if (grid[r][c].isMine()) {
                    System.out.print("* ");
                } else {
                    mines = grid[r][c].getAdjacentMines();
                    if (mines == 0) {
                        System.out.print(". ");
                    } else {
                        System.out.print(mines + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    /* 
     * Reveals the selected cell. So the cell now displays a '.' if no
     * adjacent cells have mines, or, a number representing the number 
     * of adjacent cells with mines.
     *
     * Extra Credit: Reveal surrounding cells until encountering a cell 
     *               with non-zero adjacent mines 
     *
     * @param   row    Row of the user selected cell
     * @param   column Column of the user selected cell
     * @return  an integer indicating if the game is won, lost or not-over
     */
    public static int revealCell(int row, int column) {
        /*
         * Handle user's cell selection specified by row and column 
         * There are three different cases:
         * 1. user chooses already explored cell - do nothing
         * 2. user chooses cell which has a mine - game lost
         * 3. user chooses a mine-free cell - reveal the cell
         * Print Minesweeper grid after handling user input
         *
         */
        if (grid[row][column].isRevealed()) {
            System.out.print("You have already revealed that spot.");
            System.out.println();
            printGrid();
            return GAME_NOTOVER;
        } 
        grid[row][column].reveal();
        if (grid[row][column].getVal() == '*') {
            printGrid();
            return GAME_LOST;
        } else {
            printGrid();
            return GAME_NOTOVER;
        }
    }

    /*
     * Check if all the mine-free cells are revealed
     * @return  true if game over, false if not
     */
    public static boolean checkGameOver() {
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (!grid[i][j].isMine() && !grid[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Add other static methods as necessary */
    
}

