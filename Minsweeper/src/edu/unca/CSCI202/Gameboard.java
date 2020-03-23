package edu.unca.CSCI202;

import java.util.Random;
import java.util.Scanner;

/**
 * @author 		Johnny Remein
 * @version		2/1/19
 * Assignment:	Project 1 - Minesweeper
 * Description: The Gameboard contains the methods that create and populate the gameboard, 
 * 				handles all of the game logic, and manages inputs from the user. 
 * 				Descriptions of methods and data handling can be found below.
 */
public class Gameboard {

	private int columns = 10;
	private int rows = 10;
	private int firstRowElement = 1;
	private int lastRowElement = rows - 2;
	private int firstColElement = 1;
	private int lastColElement = columns - 2;
	private int mineCount = 10;
	boolean playTime = true;	
	private Cell [][] gameBoard = new Cell [rows][columns];
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	

	
	
	
	
	/**
	 * Description: run carries out the high level functioning of the game.
	 * 				It houses the main while loop that keeps the cycles
	 * 				of play running. It also receives the inputs from the 
	 * 				user and determines what happens based on the inputs.
	 */
	public void run() {
		
		int rowGuess;
		int columnGuess;
		String contentsGuess;
		
		buildBoard();
		displayBoard(true);

		System.out.println("\nWelcome to Minesweeper! You will be prompted for your row, column, and cell contents guesses"
				+ " individually.\nRow and column guesses should be entered as a single integer (1-8), and cell contents should "
				+ "be entered\nas 'mine' or 'clean' accordingly. Type in 'peek' when prompted for your cell contents"
				+ " guess to view\nhidden mines, and type in 'return' to go back to normal play. Enjoy!\n");
		
		while(playTime) {
			
			System.out.println("\nGuess cell contents ('mine' or 'clean')\n('peek' to view hidden mines, or 'return' to go back to normal play): ");
			contentsGuess = scan.next();
			
			if(contentsGuess.equals("peek")) {
				System.out.println();
				displayBoard(false);
				continue;
			}
			
			else if(contentsGuess.equals("return")) {
				System.out.println();
				displayBoard(true);
				continue;
			}
			
			System.out.print("\nGuess a row (1-8): " );
			rowGuess = scan.nextInt();
			
			System.out.print("\nGuess a column (1-8): ");
			columnGuess = scan.nextInt();
			
			checkGuess(rowGuess, columnGuess, contentsGuess);
			
			if(playTime) displayBoard(true);
			
			if(gameComplete()) {
				System.out.println("\nHoly Cow! Good job, you won!!");
				playTime = false;
			}
			
		}
		
		System.out.println("\nThank you! Come again!");
		
	}
	
	
	
	
	
	
	/**
	 * Description: buildBoard fully creates a 2d array of Cell objects
	 * 				and populates the board with mines.
	 */
	private void buildBoard() {
		
		int mineRow;
		int mineColumn;
		boolean mineNotSet;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++)
				gameBoard[i][j] = new Cell();
		}
		
		for(int i = mineCount; i > 0; i--) {
			mineNotSet = true;
			
			while(mineNotSet) {
				mineRow = ran.nextInt(lastRowElement) + 1;
				mineColumn = ran.nextInt(lastColElement) + 1;
				
				if(!gameBoard[mineRow][mineColumn].isMineHere()) {
					mineNotSet = false;
					gameBoard[mineRow][mineColumn].setMineHere(true);
				}
			}
			
		}
	}
	
	
	
	
	
	
	/**
	 * @param displayStyle - true for regular game-play display, false to reveal hidden mines
	 * 
	 * Description: displayBoard prints the board out to the user.
	 */
	private void displayBoard(boolean displayStyle) {
		
		if(displayStyle) {
			
			for(int i = firstRowElement; i <= lastRowElement; i++) {
				for(int j = firstColElement; j <= lastColElement; j++) {
					if(gameBoard[i][j].getCellState() != 's') {
						System.out.print(" " + gameBoard[i][j].getCellState());
					}
					else System.out.print(" " + gameBoard[i][j].getNumAdjMines());
				}
				System.out.println();
			}
			
		}
		
		else {
			
			for(int i = firstRowElement; i <= lastRowElement; i++) {
				for(int j = firstColElement; j <= lastColElement; j++) {
					if(gameBoard[i][j].isMineHere()) {
						System.out.print(" M");
					}
					else if(gameBoard[i][j].getCellState() == 's') {
						System.out.print(" " + gameBoard[i][j].getNumAdjMines());
					}
					else System.out.print(" " + gameBoard[i][j].getCellState());
				}
				System.out.println();
			}
		}
		
	}
	
	
	
	
	
	
	/**
	 * @param rowGuess		- The row guessed by the user
	 * @param columnGuess	- The column guessed by the user
	 * @param contentsGuess	- The contents guessed by the user
	 * 
	 * Description: checkGuess determines if the users' guess was correct
	 * 				or not, and it changes cell states or 
	 * 				contents accordingly.
	 */
	private void checkGuess(int rowGuess, int columnGuess, String contentsGuess) {
			
		boolean mineHere = gameBoard[rowGuess][columnGuess].isMineHere();
		
		if(contentsGuess.equals("mine")) {
			if(mineHere) {
				gameBoard[rowGuess][columnGuess].setCellState('M');
				System.out.println("\nGood guess!\n");
			}
			else {
				playTime = false;
				System.out.println("\nSorry, you guessed wrong. Game Over.\n");
			}
		}
		
		else {
			if(mineHere) {
				playTime = false;
				System.out.println("\nSorry, you guessed wrong. Game Over.\n");
			}
			else {
				gameBoard[rowGuess][columnGuess].setCellState('s');
				gameBoard[rowGuess][columnGuess].setNumAdjMines(countMines(rowGuess, columnGuess));
				System.out.println("\nGood guess!\n");
			}
		}
	}
	
	
	
	
	
		
	/**
	 * @param rowGuess		- The row guessed by the user
	 * @param columnGuess	- The column guessed by the user
	 * @return				- Returns a integer, the number of adjacent mines around the cell
	 * 
	 * Description: countMines calculates the number of adjacent mines to
	 * 				the guessed cell.
	 */
	private int countMines(int rowGuess, int columnGuess) {
		
		int adjMines = 0;
		int rowPerimStart = rowGuess - 1;
		int rowPerimEnd = rowGuess + 1;
		int columnPerimStart = columnGuess - 1;
		int columnPerimEnd = columnGuess + 1;
		
		for(int i = rowPerimStart; i <= rowPerimEnd; i++) {
			for(int j = columnPerimStart; j <= columnPerimEnd; j++) {
				if(gameBoard[i][j].isMineHere()) adjMines++;
			}
		}
		
		return adjMines;
	}
	
	
	
	
	
	
	/**
	 * @return - Returns true if the game has been completed, false if not
	 * 
	 * Description: gameComplete checks to see if every cell has been 
	 * 				identified correctly.
	 */
	private boolean gameComplete() {
		
		int ideedCells = 0;
		char checkCell;
		
		for(int i = firstRowElement; i <= lastRowElement; i++) {
			for(int j = firstColElement; j <= lastColElement; j++) {
				checkCell = gameBoard[i][j].getCellState();
				if(checkCell != '-') ideedCells++;
			}
		}
		
		return ideedCells == (lastRowElement*lastColElement);
	}

}

