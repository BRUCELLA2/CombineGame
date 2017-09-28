package games;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import games.constants.GameModes;
import games.constants.GameNames;
import players.ComputerPlayer;
import players.HumanPlayer;


/**
 * <b>Game is the class representing a game.</b><br>
 * <br>
 * The games represented by the Game class are games where it is necessary to discover either a number (MoreLess game)
 * or a combination of number or color (mastermind)<br>
 * <br>
 * A game is initiated by starting with the game mode provided as a constructor's parameter (call of the play methods
 * {@link #playChallenger()} {@link #playDefender()} {@link #playDuel()})<br>
 * A game can generate a mystery number {@link #mysteryNumberGeneration(int)}<br>
 * A game can compare a proposal provided with the mystery number {@link #compareNumber(int[], int[])}<br>
 * A game can initiate a message of victory or defeat {@link #victory()} {@link #defeat(int[])}<br>
 *
 *
 * @author BRUCELLA2
 * @version 1.1.0
 *
 */
public abstract class Game {

// ***** STATIC VARIABLES *****/

    /**
     * Log4j LOGGER
     */
    private static final Logger LOGGER = LogManager.getLogger(Game.class);

// ***** VARIABLES *****/

    /**
     * endGame indicates whether the game should end.
     *
     * @see #isEndGame()
     * @see #setEndGame(boolean)
     *
     */
    private boolean endGame;

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
    private GameModes gameMode;

    /**
     * Game's name<br>
     * <br>
     * This variable is initialized by the constructor of the class.
     *
     * @see #getGameName()
     * @see #setGameName(GameNames)
     *
     */
    private GameNames gameName;

    /**
     * Number of remaining attempts to complete the game<br>
     * <br>
     * This variable is initialized in the constructor.<br>
     *
     * @see #getNbRemainingTries()
     * @see #setNbRemainingTries(int)
     */
    private int nbRemainingTries;


// ***** CONSTRUCTEURS *****/

    /**
     * Game's constructor<br>
     * <br>
     * The constructor initiates the various variables.
     *
     *
     * @param pGameMode
     *            game mode to be used
     *
     * @see #playChallenger()
     * @see #playDefender()
     * @see #playDuel()
     */

    protected Game(final GameModes pGameMode) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Game Construction"); //$NON-NLS-1$
        }
        this.endGame = false;
        this.gameMode = pGameMode;
        this.setNbRemainingTries(CombineGame.getNbMaxTries());

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("EnGame = " + this.endGame); //$NON-NLS-1$
            LOGGER.trace("GameMode = " + this.gameMode); //$NON-NLS-1$
        }
    }


// ***** GETTERS *****/

    /**
     * Returns the game's mode
     *
     * @return game's mode
     *
     * @see #setGameMode(GameModes)
     */
    public GameModes getGameMode() {

        return this.gameMode;
    }


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
     * Return true if the game has to end.
     *
     * @return true if the game has to end and false if not
     *
     * @see #setEndGame(boolean)
     */
    public boolean isEndGame() {

        return this.endGame;
    }


// ***** SETTERS *****/

    /**
     * Allows to define if the game should end.
     *
     * @param pEndGame
     *            Specifies whether the game should end
     *
     * @see #isEndGame()
     */
    public void setEndGame(final boolean pEndGame) {

        this.endGame = pEndGame;
    }


    /**
     * Allows to define the game's mode.
     *
     * @param pGameMode
     *            The game's mode
     *
     * @see #getGameMode()
     */
    public void setGameMode(final GameModes pGameMode) {

        this.gameMode = pGameMode;
    }


    /**
     * Allows to define the game's name.<br>
     *
     * @param pGameName
     *            game's name
     *
     * @see #getGameName()
     */
    public void setGameName(final GameNames pGameName) {

        this.gameName = pGameName;
    }


    /**
     * Allows to define the number of remaining tries before the end of the game.
     *
     * @param pNbRemainingTries
     *            number of remaining tries before the end of the game.
     *
     * @see #getNbRemainingTries()
     */
    public void setNbRemainingTries(final int pNbRemainingTries) {

        this.nbRemainingTries = pNbRemainingTries;
    }


// ***** METHODS *****/

    /**
     * This method allows to compare the number proposed in the parameter with the mystery number<br>
     * <br>
     * This method is redefined in the different girls' classes.
     *
     * @param pNumberToCompare
     *            The number to be compared to the mystery number.
     * @param pMysteryNumber
     *            The mystery Number.
     *
     * @return The result of the comparison in the form of a String
     */
    public abstract String compareNumber(int[] pNumberToCompare, int[] pMysteryNumber);


    /**
     * When it's the computer turn in a game, this method need to be called to performed the operation needed. All game
     * in CombineGame need to give a proposition. This proposition is returned.
     *
     * @param pComputerPlayer
     *            The computer player
     * @param pMysteryNumber
     *            The mystery number to discover
     *
     * @return The proposition of the computer player
     */
    protected abstract int[] computerTurn(ComputerPlayer pComputerPlayer, int[] pMysteryNumber);


    /**
     * This method is used to end the game by indicating that the player has lost.<br>
     * The message is "Dommage ! Vous avez perdu" followed by the correct answer unless the game mode is the defender.
     *
     * @param pMysteryNumber
     *            The mystery Number not discovered during the game
     *
     */
    protected void defeat(final int[] pMysteryNumber) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Defeat"); //$NON-NLS-1$
        }
        CombineGame.getDisplay().println("\n*** Dommage ! Vous avez perdu ***\n"); //$NON-NLS-1$

        if (this.getGameMode() != GameModes.DEFENDER) {
            CombineGame.getDisplay().print("\nLa bonne réponse était : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(pMysteryNumber);
            CombineGame.getDisplay().println(""); //$NON-NLS-1$
        }

        this.setEndGame(true);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("End game : " + this.isEndGame()); //$NON-NLS-1$
        }
    }


    /**
     * This method is used to end the game by indicating an equality between the player and the computer.<br>
     * This message is "Personne n'a gagné"
     *
     */
    protected void equality() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Equality"); //$NON-NLS-1$
        }
        CombineGame.getDisplay().println("\n*** Egalité - Personne n'a gagné ***\n"); //$NON-NLS-1$
        this.setEndGame(true);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("End game : " + this.isEndGame()); //$NON-NLS-1$
        }
    }


    /**
     * When it's the human turn in a game, this method need to be called to performed the operation needed. All game in
     * CombineGame need to give a proposition. This proposition is returned.
     *
     * @param pHumanPlayer
     *            The human player
     * @param pMysteryNumber
     *            The mystery number to discover
     *
     * @return The proposition of the human player
     */
    protected abstract int[] humanTurn(HumanPlayer pHumanPlayer, int[] pMysteryNumber);


    /**
     * This method generates a mystery number.<br>
     * <br>
     * This method is based on the number of mystery digits that constitue the mystery number.<br>
     * The generated digits are integers between 0 and 9.<br>
     *
     * @param pMaxValueDigit
     *            The max value of each digit
     * @return The mystery Number generated
     */
    public int[] mysteryNumberGeneration(final int pMaxValueDigit) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Number Generation"); //$NON-NLS-1$
        }
        int[] mysteryNumber = new int[CombineGame.getNbDigitsMystery()];
        Random r = new Random();

        for (int i = 0; i < CombineGame.getNbDigitsMystery(); i++) {

            mysteryNumber[i] = r.nextInt(pMaxValueDigit + 1);

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(mysteryNumber);
            }
        }

        return mysteryNumber;
    }


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


    /**
     * This method starts the game with the desired mode
     */
    public void startGame() {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Start game"); //$NON-NLS-1$
        }
        if (this.getGameMode() == GameModes.CHALLENGER) {
            this.playChallenger();
        }
        else if (this.getGameMode() == GameModes.DEFENDER) {
            this.playDefender();
        }
        else {
            this.playDuel();
        }
    }


    /**
     * This method is used to end the game by indicating that the player has won.<br>
     * The message is "Bravo ! Vous avez gagné"
     *
     */
    protected void victory() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Victory"); //$NON-NLS-1$
        }
        CombineGame.getDisplay().println("\n*** Bravo ! Vous avez gagné ! ***\n"); //$NON-NLS-1$
        this.setEndGame(true);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("End game : " + this.isEndGame()); //$NON-NLS-1$
        }
    }
}
