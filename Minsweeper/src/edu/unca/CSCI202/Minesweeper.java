package edu.unca.CSCI202;

/**
 * @author 		Johnny Remein
 * @version		2/1/19
 * Assignment:	Project 1 - Minesweeper
 * Description: The Minesweeper class holds the main method which creates a Gameboard instance and 
 * 				initiates the game.
 */
public class Minesweeper {

	/**
	 * @param args
	 * Description: The main method creates a Gameboard object and starts the game.
	 */
	public static void main(String[] args) {
		
		Gameboard thisGame = new Gameboard();
				
		thisGame.run();

	}

}
