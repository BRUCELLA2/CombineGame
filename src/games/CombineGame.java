package games;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import display.Display;
import games.constants.GameModes;
import games.constants.GameNames;

/**
 * <b>CombineGame allows to launch the game desired by the user.</b><br>
 * <br>
 * Displays various menus allowing the user to choose the game and the game option.<br>
 * When the choice of game and mode is made by the user, the game itself is called with the game mode as an argument.<br>
 * 
 * @author BRUCELLA2
 * @version 1.0.1
 * 
 */
public class CombineGame {
	
//***** VARIABLES *****/
	/**
	 * Indicates if CombineGame will run in developer mode.<br>
	 * <br>
	 * In this mode, the solution of the games is display at the start.<br>
	 * <br>
	 * This variable is customizable in config.properties file
	 * 
	 * @see #initProperties()
	 */
	public static boolean DEVELOPER_MODE;
	
	/**
	 * Number of digits constituting the mystery number.<br>
	 * <br>
	 * This variable is customizable in config.properties file
	 * 
	 * @see #initProperties()
	 */
	public static int NB_DIGITS_MYSTERY;
	
	/**
	 * Number of tries at the start of a game.<br>
	 * <br>
	 * This variable is customizable in config.properties file
	 * 
	 * @see #initProperties()
	 */
	public static int NB_MAX_TRIES;
	
	/**
	 * Maximum value for a digit.<br>
	 * <br>
	 * This variable is customizable in config.properties file
	 * 
	 * @see #initProperties()
	 */
	public static int MAX_VALUE_DIGIT;
	/**
	 * Number of colors for mastermind game<br>
	 * <br>
	 * This variable is customizable in config.properties file
	 * 
	 * @see #initProperties()
	 */
	public static int NB_COLORS;
	
	/**
	 * display is used to display the different elements of the game (for example the menu)
	 * 
	 * @see #getDisplay()
	 * @see #setDisplay(Display)
	 */
	private Display display;
	
	/**
	 * scanner which allows to retrieve data (Strings) entered by the user
	 * 
	 * @see #getScanner()
	 * @see #setScanner(Scanner)
	 * 
	 */
	private Scanner scanner;
	
	/**
	 * gameChosen represents the game chose by the user through the menus.
	 * 
	 * @see GameNames#MORE_LESS
	 * @see GameNames#MASTERMIND
	 * 
	 * @see #getGameChosen()
	 * @see #setGameChosen(GameNames)
	 */
	private GameNames gameChosen;
	
	/**
	 * modeChosen represents the game mode chose by the user through the menus.
	 * 
	 * @see GameModes#CHALLENGER
	 * @see GameModes#DEFENDER
	 * @see GameModes#DUEL
	 * 
	 * @see #getmodeChosen()
	 * @see #setModeChosen(GameModes)
	 */
	private GameModes modeChosen;
	
	/**
	 * executeCombineGame is used to indicate whether CombineGame is running or whether it should be stopped by changing its value to false.
	 * 
	 * @see #getExecuteCombineGame()
	 * @see #setExecuteCombineGame(boolean)
	 */
	private boolean executeCombineGame = true;
	
	
	
//***** CONSTRUCTORS *****/
	
	/**
	 * CombineGame class's constructor.<br>
	 * <br>
	 * Initialization of global variables using the method {@link #initProperties()}
	 * Initialization of the different variables and launch of CombineGame using the method {@link #launchCombineGame()}
	 * 
	 * @see #launchCombineGame()
	 * 
	 */
	public CombineGame() {
		
		this.setDisplay(new Display());
		this.setScanner(new Scanner(System.in));
		this.setGameChosen(null);
		this.setModeChosen(null);
		this.setExecuteCombineGame(true);
		
		initProperties();
		
		this.launchCombineGame();		
	}
	
	/**
	 * This method asks for menus to be displayed to select the game and mode and then start the game.<br>
	 * <br>
	 * Requests the display of the game selection menu.<br>
	 * Requests the display of the mode selection menu.<br>
	 * Request the start of the selected game.<br>
	 * Requests the display of the end of game menu<br>
	 * 
	 * 
	 * @see #menuGameSelection()
	 * @see #menuModeSelection()
	 */
	private void launchCombineGame() {
		
		while(this.getExecuteCombineGame()) {

			while ((this.getGameChosen() == null || this.getmodeChosen() == null) && this.getExecuteCombineGame() != false) {
				
				char gameChoice = this.menuGameSelection();
				if(gameChoice == 'Q') {
					break;
				}
				
				this.menuModeSelection();
				
				while(this.getGameChosen() != null && this.getmodeChosen() != null){
					if(this.getGameChosen() == GameNames.MORE_LESS) {
						
						Game game = new MoreLess(this.getmodeChosen(), this.getDisplay());
						
						if(game.isEndGame() == true) {

							char endGameChoice = this.menuEndGameSelection();
							if(endGameChoice == 'Q') {
								break;
							}
						}
					}
				}

			}


		}
		
	}
	
//***** GETTER *****/
	
	/**
	 * Returns the display that allows to make the different displays of the game.
	 * 
	 * @return The display allowing the display in the game
	 * 
	 * @see #setDisplay(Display)
	 */
	public Display getDisplay() {
		return this.display;
	}
	
	/**
	 * Returns the scanner that allow to get the user's input.
	 * 
	 * @return the scanner that allow to get the user's input.
	 * 
	 * @see #setScanner(Scanner)
	 * 
	 */
	public Scanner getScanner() {
		return this.scanner;
	}
		
	/**
	 * Returns combineGame's status<br>
	 * True if combineGame has to run, false if combineGame has to stop.
	 * 
	 * @return the combineGame's status
	 * 
	 * @see #setExecuteCombineGame(boolean)
	 */
	public boolean getExecuteCombineGame() {
		return this.executeCombineGame;
	}
	
	/**
	 * Returns the game chosen by the user in the form of a {@link GameNames}<br>
	 * <br>
	 * Returns null if no game has been chosen
	 * 
	 * @return game chosen by the user
	 * 
	 * @see #setGameChosen(GameNames)
	 */
	public GameNames getGameChosen() {
		return this.gameChosen;
	}
	
	/**
	 * Returns the game mode chosen by the user in the form of a {@link GameModes}<br>
	 * <br>
	 * Returns null if no mode has been chosen
	 * 
	 * @return game mode chosen by the user
	 * 
	 * @see #setModeChosen(GameModes)
	 */
	public GameModes getmodeChosen() {
		return this.modeChosen;
	}
	
//***** SETTER *****/
	
	/**
	 * Allow to define the scanner used to get user input
	 * 
	 * @param pScanner a scanner that will be used to get user input
	 * 
	 * @see #getScanner()
	 */
	private void setScanner(Scanner pScanner) {
		this.scanner = pScanner;
	}
	
	/**
	 * Allow to define the display used to make the displays
	 * 
	 * @param pDisplay display used to make the displays
	 * 
	 * @see #getDisplay()
	 */
	private void setDisplay(Display pDisplay) {
		this.display = pDisplay;
	}
	
	/**
	 * Allow to define the game chosen by the user
	 *  
	 * @param pGameChosen game chosen by the user
	 * 
	 * @see #getGameChosen()
	 */
	private void setGameChosen(GameNames pGameChosen) {
		this.gameChosen = pGameChosen;
	}
	
	/**
	 * Allow to define the game mode chosen by the user
	 * 
	 * @param pModeChosen game mode chosen by the user
	 * 
	 * @see #getmodeChosen()
	 */
	private void setModeChosen(GameModes pModeChosen) {
		this.modeChosen = pModeChosen;
	}
	
	/**
	 * Set whether CombineGame should continue to run.
	 * 
	 * @param pExecuteCombineGame true if CombineGame has to continue or false if CombineGame has to stop
	 * 
	 * @see #getExecuteCombineGame()
	 */
	private void setExecuteCombineGame(boolean pExecuteCombineGame) {
		this.executeCombineGame = pExecuteCombineGame;
	}
	
	
//***** METHODES *****/
	
	/**
	 * This method allows the user to choose his game.<br>
	 * 
	 * The choice of the game is made through a menu.<br>
	 * The menu updates the {@link #gameChosen} variable. The user's choice is also returned at the end of the method in the form of a uppercase char.<br>
	 * The different possible returns are :<br>
	 * <ul>
	 * <li>'1' for MoreLess game</li>
	 * <li>'2' for Mastermind game</li>
	 * <li>'Q' to stop CombineGame</li>
	 * </ul>
	 * 
	 * 
	 * @return the user's choice in the form of a uppercas char
	 */
	public char menuGameSelection() {
		
		boolean gameChoice = false;
		char choice;
		
		do {
			this.getDisplay().showGamesMenu();
			choice = this.getScanner().nextLine().charAt(0);
			choice = Character.toUpperCase(choice);
			
			switch(choice)
			{
			case '1':
				gameChoice = true;
				this.setGameChosen(GameNames.MORE_LESS);
				break;
				
			case '2':
				gameChoice = true;
				this.setGameChosen(GameNames.MASTERMIND);
				break;
			
			case 'Q':
				gameChoice = true;
				this.setGameChosen(null);
				this.setExecuteCombineGame(false);
				break;
				
			default:
				break;	
			}
		}while(!gameChoice);
		
		return choice;
	}
	
	/**
	 * This method allows the user to choose his game mode.<br>
	 * 
	 * The choice of the game mode is made through a menu.<br>
	 * 
	 * The menu updates the {@link #modeChosen} variable. The user's choice is also returned at the end of the method in the form of a uppercase char.<br>
	 * The different possible returns are :<br>
	 * <ul>
	 * <li>'1' for Challenger mode</li>
	 * <li>'2' for Defender mode</li>
	 * <li>'3' for Duel mode</li>
	 * <li>'R' to go back to the game selection</li>
	 * </ul>
	 * 
	 * 
	 * @return The user's choice in the form of an uppercase char.
	 */
	public char menuModeSelection() {
		
		boolean modeChoice = false;
		char choice;
		
		do {
			this.getDisplay().showGameModesMenu();
			choice = this.getScanner().nextLine().charAt(0);
			choice = Character.toUpperCase(choice);
			
			switch(choice)
			{
			case '1':
				modeChoice = true;
				this.setModeChosen(GameModes.CHALLENGER);
				break;
				
			case '2':
				modeChoice = true;
				this.setModeChosen(GameModes.DEFENDER);
				break;
				
			case '3':
				modeChoice = true;
				this.setModeChosen(GameModes.DUEL);
				break;
				
			case 'R':
				modeChoice = true;
				this.setModeChosen(null);
				this.setGameChosen(null);
				break;
				
			default :
				break;
			}
			
		}while(!modeChoice);
		
		return choice;
	}
	
	/**
	 * This method allows the user to indicate what he wants to do after the end of his game.<br>
	 * 
	 * The choice of what to do at the end of game is made through a menu.<br>
	 * The menu updates at null the variable {@link #modeChosen} and the variable {@link #gameChosen}.<br>
	 * The user's choice is also returned at the end of the method in the form of an uppercase char.<br>
	 * The different possible returns are :<br>
	 * <ul>
	 * <li>'1' to replay the same game with the same mode</li>
	 * <li>'R' to go back to the game selection</li>
	 * <li>'Q' to stop CombineGame</li>
	 * </ul>
	 * 
	 * 
	 * @return The user's choice in the form of an uppercase char.
	 */
	public char menuEndGameSelection() {
		boolean endGameChoice = false;
		char choice;
		
		do {
			this.getDisplay().showEndGameMenu();
			choice = this.getScanner().nextLine().charAt(0);
			choice = Character.toUpperCase(choice);
			
			switch(choice) 
			{
			case '1':
				endGameChoice = true;
				break;
			
			case 'R':
				endGameChoice = true;
				this.setGameChosen(null);
				this.setModeChosen(null);
				break;
			
			case 'Q':
				endGameChoice = true;
				this.setExecuteCombineGame(false);
				break;
			
			default :
				break;
			}		
		}while(!endGameChoice);
		
		return choice;
	}
	
	/**
	 * This method read the properties file (config.properties) and initialize the global variables with the properties values.
	 * 
	 */
	private void initProperties() {
		
		Properties prop = new Properties();
		
		try(InputStream input = getClass().getClassLoader().getResourceAsStream("resources/config.properties")){ //$NON-NLS-1$
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DEVELOPER_MODE = Boolean.valueOf(prop.getProperty("DEVELOPER_MODE")).booleanValue(); //$NON-NLS-1$
		NB_DIGITS_MYSTERY = Integer.valueOf(prop.getProperty("NB_DIGITS_MYSTERY")).intValue(); //$NON-NLS-1$
		NB_MAX_TRIES = Integer.valueOf(prop.getProperty("NB_MAX_TRIES")).intValue(); //$NON-NLS-1$
		MAX_VALUE_DIGIT = Integer.valueOf(prop.getProperty("MAX_VALUE_DIGIT")).intValue(); //$NON-NLS-1$
		NB_COLORS = Integer.valueOf(prop.getProperty("NB_COLORS")).intValue(); //$NON-NLS-1$
		
	}
}
