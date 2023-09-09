import java.util.Scanner;

public class CellMS { 
    public static final char MINE = '*';
    public static final char NO_ADJ_MINE_CELL = '.';
    public static final char HIDDEN_CELL = '#';
    public static final char[] NUM_ADJ_MINES = {'.','1','2','3','4','5','6','7','8'};
    
    /* Add or remove constants, instance variables and instance methods 
     * as necessary. 
     */
    private boolean mine;
    private boolean revealed;
    private int numAdjacentMines;

    /* Constructor - sets up a default Cell. It is hidden, not a mine,
     *               and has 0 adjacent mines.
     */
    public CellMS() {
        mine = false;
        revealed = false;
        numAdjacentMines = 0;
    }

    /* Mark this cell as containing a mine
     */
    public void setMine() {
        mine = true;
    }
     
    /* Checks if the cell is a mine or not
     * @return   true if it is a mine, false if it is not a mine
     */
    public boolean isMine() {
        if (mine) {
            return true;
        }
        return false;
    }

    /* Mark this cell as revealed.
     */
    public void reveal() {
        revealed = true;
    }

	/* Checks if the cell is revealed or not
	 * @return   true if the cell is revealed, false if it is not revealed
	 */ 
	public boolean isRevealed() {
        if (revealed) {
            return true;
        }
		return false;
	}
    /* Helper method which returns a char value corresponding to the integer parameter
     * numMines = 0 --> returns '.'
     * numMines = 1 --> returns '1', numMines = 2 --> returns '2' etc
     */
    private char getNumMines(int numMines) {
        return NUM_ADJ_MINES[numMines];
    }

    /* If the Cell is not revealed, returns HIDDEN_CELL.
	 * If the Cell is a mine, returns MINE.
     * Calls getNumMines() to get the char corresponding to number of mines if the Cell is revealed.
     *
     */
    public char getVal() {
        if (!revealed) {
            return HIDDEN_CELL;
        } else if (mine) {
            return MINE;
        } else {
            return getNumMines(numAdjacentMines);
        }
    } 


	/* Store the number of neighboring mines there are to this cell.
	 */
	public void setAdjacentMines(int numMines) {
        numAdjacentMines = numMines;
    }

	/* Returns the number of neighboring mines there are to this cell.
	 */
	public int getAdjacentMines() {
		return numAdjacentMines;
    }

}

