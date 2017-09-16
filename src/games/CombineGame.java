package games;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import display.Display;
import games.constants.GameModes;
import games.constants.GameNames;

/**
 * <b>CombineGame allows to launch the game desired by the user.</b><br>
 * <br>
 * Displays various menus allowing the user to choose the game and the game
 * option.<br>
 * When the choice of game and mode is made by the user, the game itself is
 * called with the game mode as an argument.<br>
 *
 * @author BRUCELLA2
 * @version 1.0.8
 *
 */
public class CombineGame {

    // ***** VARIABLES *****/
    /**
     * Indicates if CombineGame will run in developer mode.<br>
     * <br>
     * In this mode, the solution of the games is display at the start.<br>
     * <br>
     * This variable is customizable in config.properties file
     *
     * @see #isDeveloperMode()
     */
    private static final boolean DEVELOPER_MODE;

    /**
     * Number of digits constituting the mystery number.<br>
     * <br>
     * This variable is customizable in config.properties file
     *
     */
    private static final int NB_DIGITS_MYSTERY;

    /**
     * Number of tries at the start of a game.<br>
     * <br>
     * This variable is customizable in config.properties file
     *
     */
    private static final int NB_MAX_TRIES;

    /**
     * Maximum value for a digit.<br>
     * <br>
     * This variable is customizable in config.properties file
     *
     */
    private static final int MAX_VALUE_DIGIT;
    /**
     * Number of colors for mastermind game<br>
     * <br>
     * This variable is customizable in config.properties file
     *
     */
    private static final int NB_COLORS;

    /**
     * display is used to display the different elements of the game (for example
     * the menu)
     *
     * @see #getDisplay()
     */
    private static final Display DISPLAY;

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
     * executeCombineGame is used to indicate whether CombineGame is running or
     * whether it should be stopped by changing its value to false.
     *
     * @see #getExecuteCombineGame()
     * @see #setExecuteCombineGame(boolean)
     */
    private boolean executeCombineGame = true;

    /**
     * Log4j2 LOGGER
     */
    private static final Logger LOGGER = LogManager.getLogger(CombineGame.class);

    // ***** CONSTRUCTORS *****/

    /**
     * CombineGame class's constructor.<br>
     * <br>
     * Initialization of the different variables and launch of CombineGame using the
     * method {@link #launchCombineGame()}
     *
     * @see #launchCombineGame()
     *
     */
    public CombineGame() {

        LOGGER.trace("CombineGame Construction"); //$NON-NLS-1$

        this.setScanner(new Scanner(System.in));
        this.setGameChosen(null);
        this.setModeChosen(null);
        this.setExecuteCombineGame(true);

        this.launchCombineGame();
    }

    /**
     * This method asks for menus to be displayed to select the game and mode and
     * then start the game.<br>
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

        LOGGER.trace("Start CombineGame"); //$NON-NLS-1$

        while (this.getExecuteCombineGame() && (this.getGameChosen() == null || this.getmodeChosen() == null)) {

            LOGGER.trace("CombineGame is running"); //$NON-NLS-1$

            this.menuGameSelection();

            if (this.getExecuteCombineGame()) {
                this.menuModeSelection();
            }

            while (this.getGameChosen() != null && this.getmodeChosen() != null && this.executeCombineGame) {

                if (this.getGameChosen() == GameNames.MORE_LESS) {

                    Game game = new MoreLess(this.getmodeChosen());
                    game.startGame();

                    if (game.isEndGame()) {

                        this.menuEndGameSelection();
                    }
                }
                else if (this.getGameChosen() == GameNames.MASTERMIND) {

                    Game game = new Mastermind(this.getmodeChosen());
                    game.startGame();

                    if (game.isEndGame()) {

                        this.menuEndGameSelection();
                    }
                }
            }

        }

        LOGGER.trace("CombineGame stop"); //$NON-NLS-1$
    }

    // ***** GETTER *****/
    /**
     * Returns if combine game is launch with Developper mode.
     *
     * @return true if Combine game is in developper mode and false if not.
     */
    public static final boolean isDeveloperMode() {

        return CombineGame.DEVELOPER_MODE;
    }

    /**
     * Returns the display that allows to make the different displays of the game.
     *
     * @return The display allowing the display in the game
     *
     */
    public static Display getDisplay() {

        return CombineGame.DISPLAY;
    }

    /**
     * Returns the numbers of digits in mystery number.
     *
     * @return the number of digits in mystery number.
     */
    public static final int getNbDigitsMystery() {

        return CombineGame.NB_DIGITS_MYSTERY;
    }

    /**
     * Returns the max numbers of tries at the start of a game.
     *
     * @return the max numbers of tries
     */
    public static final int getNbMaxTries() {

        return CombineGame.NB_MAX_TRIES;
    }

    /**
     * Returns the max value for one digit.
     *
     * @return the max value for one digit
     */
    public static final int getMaxValueDigit() {

        return CombineGame.MAX_VALUE_DIGIT;
    }

    /**
     * Returns the number of color for Mastermind game
     *
     * @return the number of color for Mastermind game
     */
    public static final int getNbColors() {

        return CombineGame.NB_COLORS;
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
     * Returns the game mode chosen by the user in the form of a
     * {@link GameModes}<br>
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

    // ***** SETTER *****/

    /**
     * Allow to define the scanner used to get user input
     *
     * @param pScanner
     *            a scanner that will be used to get user input
     *
     * @see #getScanner()
     */
    private void setScanner(final Scanner pScanner) {

        this.scanner = pScanner;
    }

    /**
     * Allow to define the game chosen by the user
     *
     * @param pGameChosen
     *            game chosen by the user
     *
     * @see #getGameChosen()
     */
    private void setGameChosen(final GameNames pGameChosen) {

        this.gameChosen = pGameChosen;
    }

    /**
     * Allow to define the game mode chosen by the user
     *
     * @param pModeChosen
     *            game mode chosen by the user
     *
     * @see #getmodeChosen()
     */
    private void setModeChosen(final GameModes pModeChosen) {

        this.modeChosen = pModeChosen;
    }

    /**
     * Set whether CombineGame should continue to run.
     *
     * @param pExecuteCombineGame
     *            true if CombineGame has to continue or false if CombineGame has to
     *            stop
     *
     * @see #getExecuteCombineGame()
     */
    private void setExecuteCombineGame(final boolean pExecuteCombineGame) {

        this.executeCombineGame = pExecuteCombineGame;
    }

    // ***** METHODES *****/

    /**
     * This method allows the user to choose his game.<br>
     *
     * The choice of the game is made through a menu.<br>
     * The menu updates the {@link #gameChosen} variable. The user's choice is also
     * returned at the end of the method in the form of a uppercase char.<br>
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

        LOGGER.trace("Display Game selection menu"); //$NON-NLS-1$

        boolean gameChoice = false;
        char choice;

        do {
            CombineGame.getDisplay().showGamesMenu();
            String line = this.getScanner().nextLine();
            if (line.length() > 1) {
                choice = 'x';
            }
            else {
                choice = line.charAt(0);
                choice = Character.toUpperCase(choice);
            }

            LOGGER.debug("User input : " + line); //$NON-NLS-1$
            LOGGER.debug("User choice : " + choice); //$NON-NLS-1$

            switch (choice) {
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
                    CombineGame.getDisplay().println("Saisie incorrecte"); //$NON-NLS-1$
                    break;
            }
        } while (!gameChoice);

        LOGGER.debug("Game Chosen : " + this.getGameChosen()); //$NON-NLS-1$

        return choice;
    }

    /**
     * This method allows the user to choose his game mode.<br>
     *
     * The choice of the game mode is made through a menu.<br>
     *
     * The menu updates the {@link #modeChosen} variable. The user's choice is also
     * returned at the end of the method in the form of a uppercase char.<br>
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

        LOGGER.trace("Display Mode selection menu"); //$NON-NLS-1$

        boolean modeChoice = false;
        char choice;

        do {
            CombineGame.getDisplay().showGameModesMenu();
            String line = this.getScanner().nextLine();

            if (line.length() > 1) {
                choice = 'x';
            }
            else {
                choice = line.charAt(0);
                choice = Character.toUpperCase(choice);
            }

            LOGGER.debug("User input : " + line); //$NON-NLS-1$
            LOGGER.debug("User choice : " + choice); //$NON-NLS-1$

            switch (choice) {
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

                default:
                    CombineGame.getDisplay().println("Saisie incorrecte"); //$NON-NLS-1$
                    break;
            }

        } while (!modeChoice);

        LOGGER.debug("Mode Chosen : " + this.getmodeChosen()); //$NON-NLS-1$

        return choice;
    }

    /**
     * This method allows the user to indicate what he wants to do after the end of
     * his game.<br>
     *
     * The choice of what to do at the end of game is made through a menu.<br>
     * The menu updates at null the variable {@link #modeChosen} and the variable
     * {@link #gameChosen}.<br>
     * The user's choice is also returned at the end of the method in the form of an
     * uppercase char.<br>
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

        LOGGER.trace("Display End Game selection menu"); //$NON-NLS-1$

        do {
            CombineGame.getDisplay().showEndGameMenu();
            String line = this.getScanner().nextLine();
            if (line.length() > 1) {
                choice = 'x';
            }
            else {
                choice = line.charAt(0);
                choice = Character.toUpperCase(choice);
            }

            LOGGER.debug("User input : " + line); //$NON-NLS-1$
            LOGGER.debug("User choice : " + choice); //$NON-NLS-1$

            switch (choice) {
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

                default:
                    CombineGame.getDisplay().println("Saisie incorrecte"); //$NON-NLS-1$
                    break;
            }
        } while (!endGameChoice);

        LOGGER.debug("Combine game continue : " + this.getExecuteCombineGame()); //$NON-NLS-1$

        return choice;
    }

    /**
     * Read the properties file (config.properties) and initialize the global
     * variables with the properties values.<br>
     * <br>
     * Initialize Display wich will be used for all display.
     *
     */
    static {

        Properties prop = new Properties();

        try (InputStream input = CombineGame.class.getClassLoader()
                .getResourceAsStream("resources/config.properties")) { //$NON-NLS-1$
            prop.load(input);
        } catch (IOException e) {
            LOGGER.error("Loading config properties Error"); //$NON-NLS-1$
            LOGGER.error(e.getStackTrace());
        }

        DEVELOPER_MODE = Boolean.parseBoolean(prop.getProperty("DEVELOPER_MODE")); //$NON-NLS-1$
        NB_DIGITS_MYSTERY = Integer.parseInt(prop.getProperty("NB_DIGITS_MYSTERY")); //$NON-NLS-1$
        NB_MAX_TRIES = Integer.parseInt(prop.getProperty("NB_MAX_TRIES")); //$NON-NLS-1$
        MAX_VALUE_DIGIT = Integer.parseInt(prop.getProperty("MAX_VALUE_DIGIT")); //$NON-NLS-1$
        NB_COLORS = Integer.parseInt(prop.getProperty("NB_COLORS")); //$NON-NLS-1$

        DISPLAY = new Display();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEVELOPPER_MODE = " + DEVELOPER_MODE); //$NON-NLS-1$
            LOGGER.debug("NB_DIGITS_MYSTERY = " + NB_DIGITS_MYSTERY); //$NON-NLS-1$
            LOGGER.debug("NB_MAX_TRIES = " + NB_MAX_TRIES); //$NON-NLS-1$
            LOGGER.debug("MAX_VALUE_DIGIT = " + MAX_VALUE_DIGIT); //$NON-NLS-1$
            LOGGER.debug("NB_COLORS = " + NB_COLORS); //$NON-NLS-1$
        }
    }
}
