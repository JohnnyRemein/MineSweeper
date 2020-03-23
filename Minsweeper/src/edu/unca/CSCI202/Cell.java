package edu.unca.CSCI202;

/**
 * @author 		Johnny Remein
 * @version		2/1/19
 * Assignment:	Project 1 - Minesweeper
 * Description: The Cell class holds the contents of each cell created on the gameboard. Instance 
 * 				variables keep track of the state of the cell (guessed correct as clean or mine, or 
 * 				default) and if there is a mine in the cell or the number of adjacent mines around
 * 				the cell if clean.
 */
public class Cell {
	
	private char cellState = '-';
	private boolean mineHere;					
	private int numAdjMines;					
	

	
	/**
	 * @return - Returns the cell's state
	 * 
	 * Description: The cells states could be: 	'-' = default
	 * 											'M' = correctly guessed mine
	 * 											's' = correctly guessed clean
	 */
	public char getCellState() {
		return cellState;
	}

	/**
	 * @param cellState - State of cell
	 */
	public void setCellState(char cellState) {
		this.cellState = cellState;
	}
	
	/**
	 * @return - Returns true if there is a mine, false if not
	 */
	public boolean isMineHere() {
		return mineHere;
	}

	/**
	 * @param mineHere - True if a mine has been set
	 */
	public void setMineHere(boolean mineHere) {
		this.mineHere = mineHere;
	}

	/**
	 * @return - Returns an integer: the number of mines adjacent to the cell
	 */
	public int getNumAdjMines() {
		return numAdjMines;
	}

	/**
	 * @param numAdjMines - Sets the number of mines adjacent to the cell
	 */
	public void setNumAdjMines(int numAdjMines) {
		this.numAdjMines = numAdjMines;
	}

	
	
	
}
