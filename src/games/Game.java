package games;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import display.Display;
import games.constants.GameModes;
import games.constants.GameNames;


/**
 * <b>Game is the class representing a game.</b><br>
 * <br>
 * The games represented by the Game class are games where it is necessary to discover either a number (MoreLess game) or a combination of number or color (mastermind)<br>
 * <br> 
 * A game is initiated by starting with the game mode provided as a constructor's parameter (call of the play methods {@link #playChallenger()} {@link #playDefender()} {@link #playDuel()})<br>
 * A game can generate a mystery number {@link #mysteryNumberGeneration(int)}<br>
 * A game can compare a proposal provided with the mystery number {@link #compareNumber(ArrayList)}<br>
 * A game can initiate a message of victory or defeat {@link #victory()} {@link #defeat()}<br>
 * 
 * 
 * @author BRUCELLA2
 * @version 1.0.4
 * 
 */
public abstract class Game {
	
//***** VARIABLES *****/
	
	/**
	 * Game's name<br>
	 * <br>
	 * This variable is initialized by the constructor of the class.
	 * 
	 * @see #getGameName()
	 * @see #setGameName(GameNames)
	 * 
	 */
	//TODO a rendre final si possible
	GameNames gameName;
	
	/**
	 * game's mode<br>
	 * <br>
	 * This variable is initialized by a parameter provided to the constructor of the class.<br>
	 * This variable is used to define how the game will run.
	 * 
	 * @see #getGameMode()
	 * @see #setGameMode(GameModes)
	 * 
	 */
	//TODO a rendre final si possible
	GameModes gameMode;
	
	/**
	 * Mystery Number to Discover<br>
	 * <br>
	 * Each digit constituting the number is contained in an ArrayList of  integer.<br>
	 * The mystery number is generated randomly {@link #mysteryNumberGeneration(int)} or from a data entered by the user.
	 * 
	 * @see #getMysteryNumber()
	 * @see #setMysteryNumber(ArrayList)
	 * @see #mysteryNumberGeneration(int)
	 * 
	 */
	//TODO A supprimer ? � transformer en liste d'ArrayList pour g�rer le mode duel ? A dupliquer pour g�rer 2 nombres myst�res ?
	ArrayList<Integer> mysteryNumber = new ArrayList<>();
	
	/**
	 * Number of remaining attempts to complete the game<br>
	 * <br>
	 * This variable is initialized in the constructor.<br>
	 * When nbRemainingTries arrives at 0, the game must end. //TODO � int�grer dans le SETTER
	 * 
	 * @see #getNbRemainingTries()
	 * @see #setNbRemainingTries(int)
	 */
	int nbRemainingTries;
	
	/**
	 * Players list<br>
	 * <br>
	 * Players are stored in ArrayList of player.<br>
	 * This list is empty when the game is created. Players are added when the game starts (play methods)<br>
	 * <br>
	 * (currently not used, players are created and used locally except for duel mode)
	 *
	 * @see #getPlayers()
	 * @see #setPlayers(ArrayList)
	 * 
	 */
	ArrayList<Player> players = new ArrayList<>();
	
	/**
	 * display is used to display the different elements of the game.
	 * 
	 * @see #getDisplay()
	 * @see #setDisplay(Display)
	 * 
	 */
	Display display;
	
	/**
	 * endGame indicates whether the game should end.
	 * 
	 * @see #isEndGame()
	 * @see #setEndGame(boolean)
	 * 
	 */
	boolean endGame;
	
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(Game.class); 
	
//***** CONSTRUCTEURS *****/	
	
	/**
	 * Game's constructor<br>
	 * <br>
	 * The constructor initiates the various variables and starts the game with the desired mode
	 * 
	 * 
	 * @param pGameMode game mode to be used
	 * @param pDisplay display that will be used to make game displays
	 * 
	 * @see #playChallenger()
	 * @see #playDefender()
	 * @see #playDuel()
	 */
	
	//TODO Remove play methods, this methods need to be call in a new method startGame() and not directly in the constructor.
	protected Game(GameModes pGameMode, Display pDisplay) {
		
		logger.trace("Game Construction"); //$NON-NLS-1$
		
		this.setDisplay(pDisplay);
		this.setEndGame(false);
		this.setGameMode(pGameMode);
		this.setNbRemainingTries(CombineGame.NB_MAX_TRIES);
		
		logger.debug("EnGame = " + this.isEndGame()); //$NON-NLS-1$
		logger.debug("GameMode = " + this.getGameMode()); //$NON-NLS-1$
		logger.debug("RemainingTries = " + this.getNbRemainingTries()); //$NON-NLS-1$
		
		if(this.getGameMode() == GameModes.CHALLENGER) {
			this.playChallenger();
		}
		else if(this.getGameMode() == GameModes.DEFENDER) {
			this.playDefender();
		}
		else{
			this.playDuel();
		}
	}
	
	
//***** GETTERS *****/
	
	/**
	 * Returns the game's name
	 * 
	 * @return game's name
	 * 
	 * @see #setGameName(GameNames)
	 */
	public GameNames getGameName() {
		return this.gameName;
	}
	
	/**
	 * Returns the game's mode
	 * 
	 * @return Mode de jeu
	 * 
	 * @see #setGameMode(GameModes)
	 */
	public GameModes getGameMode() {
		return this.gameMode;
	}
	
	
	/**
	 * Returns the mystery number as an ArrayList of integer
	 * 
	 * @return mystery number
	 * 
	 * @see #setMysteryNumber(ArrayList)
	 * @see #mysteryNumberGeneration(int)
	 */
	public ArrayList<Integer> getMysteryNumber(){
		return this.mysteryNumber;
	}
	
	
	/**
	 * Returns the number of remaining tries before the end of the game.<br>
	 * <br>
	 * This number of tries is set when the game is launched.
	 * 
	 * @return the number of remaining tries before the end of the game.
	 * 
	 * @see #setNbRemainingTries(int)
	 */
	public int getNbRemainingTries() {
		return this.nbRemainingTries;
	}
	
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
	 * Returns the list of players as an ArrayList
	 * 
	 * @return list of players
	 * 
	 * @see #setPlayers(ArrayList)
	 */
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * Return true if the game has to end.
	 * 
	 * @return true if the game has to end and false if not
	 * 
	 * @see #setEndGame(boolean)
	 */
	public boolean isEndGame() {
		return this.endGame;
	}
	
//***** SETTERS *****/
	
	/**
	 * Allows to define the game's name.<br>
	 * 
	 * @param pGameName game's name
	 * 
	 * @see #getGameName()
	 */
	public void setGameName(GameNames pGameName) {
		this.gameName = pGameName;
	}
	
	/**
	 * Allows to define the game's mode.
	 * 
	 * @param pGameMode The game's mode
	 * 
	 * @see #getGameMode()
	 */
	public void setGameMode(GameModes pGameMode) {
		this.gameMode = pGameMode;
	}
	
	/**
	 * Allows to define the mystery number by using an ArrayList of integer.
	 * 
	 * @param pMysteryNumber Mystery number
	 * 
	 * @see #getMysteryNumber()
	 */
	public void setMysteryNumber(ArrayList<Integer> pMysteryNumber) {
		this.mysteryNumber = pMysteryNumber;
	}
	
	/**
	 * Allows to define the number of remaining tries before the end of the game.
	 * 
	 * @param pNbRemainingTries number of remaining tries before the end of the game.
	 * 
	 * @see #getNbRemainingTries()
	 */
	public void setNbRemainingTries(int pNbRemainingTries) {
		this.nbRemainingTries = pNbRemainingTries;
	}
	
	/**
	 * Allow to define the display used to make the displays
	 * 
	 * @param pDisplay display used to make the displays
	 * 
	 * @see #getDisplay()
	 */
	public void setDisplay(Display pDisplay) {
		this.display = pDisplay;
	}
	
	/**
	 * Allows to define the players list from an ArrayList of players.
	 * 
	 * @param pPlayers list of players
	 * 
	 * @see #getPlayers()
	 */
	public void setPlayers(ArrayList<Player> pPlayers) {
		this.players = pPlayers;
	}
	
	/**
	 * Allows to define if the game should end.
	 * 
	 * @param pEndGame Specifies whether the game should end
	 * 
	 * @see #isEndGame()
	 */
	public void setEndGame(boolean pEndGame) {
		this.endGame = pEndGame;
	}
	
//***** METHODS *****/
	
	/**
	 * This method generates a mystery number.<br>
	 * <br>
	 * This method is based on the number of mystery digits that constitue the mystery number.<br>
	 * The generated digits are integers between 0 and 9.<br>
	 * 
	 * @param pMaxValueDigit The max value of each digit
	 */
	public void mysteryNumberGeneration(int pMaxValueDigit){
		
		logger.trace("Number Generation"); //$NON-NLS-1$
		
		for(int i=0; i < CombineGame.NB_DIGITS_MYSTERY; i++) {
			this.getMysteryNumber().add(new Integer((int) (((pMaxValueDigit+1)-0)*Math.random())));
			
			if(logger.isTraceEnabled()) {
				logger.trace(this.getMysteryNumber());
			}
		}
	}
	
	/**
	 * This method is used to end the game by indicating that the player has lost.<br>
	 * The message is "Dommage ! Vous avez perdu" followed by the correct answer unless the game mode is the defender.
	 * 
	 */
	protected void defeat() {
		
		logger.trace("Defeat"); //$NON-NLS-1$
		
		this.getDisplay().println("\n Dommage ! Vous avez perdu"); //$NON-NLS-1$

		if(this.getGameMode() != GameModes.DEFENDER) {
			this.getDisplay().print("La bonne r�ponse �tait : "); //$NON-NLS-1$
			// TODO ajouter la fonction afficheln(ArrayList<Integer>)
			this.getDisplay().print(this.getMysteryNumber());
			this.getDisplay().println(""); //$NON-NLS-1$
		}

		this.setEndGame(true);
		logger.trace("End game : " + this.isEndGame()); //$NON-NLS-1$
	}
	
	/**
	 * This method is used to end the game by indicating that the player has won.<br>
	 * The message is "Bravo ! Vous avez gagn�"
	 * 
	 */
	protected void victory() {
		
		logger.trace("Victory"); //$NON-NLS-1$
		
		this.getDisplay().println("\n Bravo ! Vous avez gagn�"); //$NON-NLS-1$
		this.setEndGame(true);
		
		logger.trace("End game : " + this.isEndGame()); //$NON-NLS-1$
	}
	
	/**
	 * This method is used to end the game by indicating an equality between the player and the computer.<br>
	 * This message is "Personne n'a gagn�"
	 * 
	 */
	protected void equality() {
		
		logger.trace("Equality"); //$NON-NLS-1$
		
		this.getDisplay().println("\n Personne n'a gagn�"); //$NON-NLS-1$
		this.setEndGame(true);
		
		logger.trace("End game : " + this.isEndGame()); //$NON-NLS-1$
	}
	
	/**
	 * This method allows to compare the number proposed in the parameter with the mystery number<br>
	 * <br>
	 * This method is redefined in the different girls' classes.
	 * 
	 * @param pNumberToCompare The number to be compared to the mystery number. 
	 * @return The result of the comparison in the form of a String
	 */
	public abstract String compareNumber(ArrayList<Integer> pNumberToCompare);
	
	/**
	 * This method starts the game in Challenger mode.<br>
	 * <br>
	 * This method is redefined in the different daughter's classes.
	 */
	public abstract void playChallenger();
	
	/**
	 * This method starts the game in Defender mode<br>
	 * <br>
	 * This method is redefined in the different daughter's classes.
	 */
	public abstract void playDefender();
	
	/**
	 * This method starts the game in Duel mode<br>
	 * <br>
	 * This method is redefined in the different daughter's classes.
	 */
	public abstract void playDuel();
	
}
