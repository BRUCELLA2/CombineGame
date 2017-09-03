package games;

import java.util.ArrayList;

import java.util.Scanner;

import display.Display;

/**
 * <b>This class represents a player</b><br>
 * <br>
 * 
 * @author BRUCELLA2
 * @version 1.0.1
 *
 */
public abstract class Player {
	
//***** VARIABLES *****//
	
	/**
	 * Player's name
	 * 
	 * @see #getPlayerName()
	 * @see #setPlayerName(String)
	 */
	private String playerName;
	
	/**
	 * display is used to display the different elements of the game.
	 * 
	 * @see #getDisplay()
	 * @see #setDisplay(Display) 
	 */
	private Display display;
	
	
//***** CONSTRUCTORS *****//
	
	/**
	 * Player's class constructor<br>
	 * <br>
	 * The name and display are initialized from the parameters provided to the constructor
	 * 
	 * @param pPlayerGame The player's name
	 * @param pDisplay The display to used
	 */
	public Player(String pPlayerGame, Display pDisplay) {
		this.setPlayerName(pPlayerGame);
		this.setDisplay(pDisplay);
	}
	
//***** GETTERS *****//
	
	/**
	 * Returns the player's name
	 * 
	 * @return the player's name
	 * 
	 * @see #setPlayerName(String)
	 */
	public String getPlayerName() {
		return this.playerName;
	}
	
	/**
	 * Returns the display
	 * 
	 * @return the display
	 */
	public Display getDisplay() {
		return this.display;
	}
	
//***** SETTERS *****//
	
	/**
	 * Allows to define the player's name
	 * 
	 * @param pPlayerName Player's name
	 * 
	 * @see #getPlayerName()
	 */
	public void setPlayerName(String pPlayerName) {
		this.playerName = pPlayerName;
	}
	
	/**
	 * Allows to define the display
	 * 
	 * @param pDisplay Display
	 * 
	 * @see #getDisplay()
	 */
	public void setDisplay(Display pDisplay) {
		this.display = pDisplay;
	}
	
//***** METHODS *****//	
	
	/**
	 * This method is used to obtain a number in the form of an ArrayList of integer.<br>
	 * <br>
	 * This number has a limited number of digits.<br>
	 * This method is redefined in the different daughter's classes.
	 * 
	 * @return A number in the form of an ArrayList of integer
	 * 
	 * @see #getNumberInput(int)
	 */
	public abstract ArrayList<Integer> giveNumber();
	
	/**
	 * This method allows to get the number entered by the user and return is as an ArrayList of integer.<br>
	 * <br>
	 * 
	 * @return A number in the form of an ArrayList of integer
	 */
	//TODO Move this method in HumanPlayer
	protected ArrayList<Integer> getNumberInput(){
		
		String str;
		ArrayList<Integer> number = new ArrayList<>();
		
		//TODO warning à prendre en compte
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		str = scan.nextLine();
			
		if (str.length() == CombineGame.NB_DIGITS_MYSTERY) {
			for(int i = 0; i < CombineGame.NB_DIGITS_MYSTERY; i++) {
				if(Character.isDigit(str.charAt(i))) {
					number.add(new Integer(Character.getNumericValue(str.charAt(i))));
				}
				else {
					number.clear();
					return number;
				}
			}
		}
		else {
			number.clear();
			return number;
		}
		
		return number;
	}
}
