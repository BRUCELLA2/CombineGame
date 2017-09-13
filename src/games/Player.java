package games;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import display.Display;

/**
 * <b>This class represents a player</b><br>
 * <br>
 *
 * @author BRUCELLA2
 * @version 1.0.4
 *
 */
public abstract class Player {

    // ***** VARIABLES *****//

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

    /**
     * Log4j2 Logger
     */
    private static final Logger logger = LogManager.getLogger(Player.class);

    // ***** CONSTRUCTORS *****//

    /**
     * Player's class constructor<br>
     * <br>
     * The name and display are initialized from the parameters provided to the
     * constructor
     *
     * @param pPlayerGame
     *            The player's name
     * @param pDisplay
     *            The display to used
     */
    public Player(String pPlayerGame, Display pDisplay) {

        logger.trace("Player construction"); //$NON-NLS-1$

        this.setPlayerName(pPlayerGame);
        this.setDisplay(pDisplay);

        logger.trace("Player name : " + this.getPlayerName()); //$NON-NLS-1$
    }

    // ***** GETTERS *****//

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

    // ***** SETTERS *****//

    /**
     * Allows to define the player's name
     *
     * @param pPlayerName
     *            Player's name
     *
     * @see #getPlayerName()
     */
    public void setPlayerName(String pPlayerName) {
        this.playerName = pPlayerName;
    }

    /**
     * Allows to define the display
     *
     * @param pDisplay
     *            Display
     *
     * @see #getDisplay()
     */
    public void setDisplay(Display pDisplay) {
        this.display = pDisplay;
    }

    // ***** METHODS *****//

    /**
     * This method is used to obtain a number in the form of an ArrayList of
     * integer.<br>
     * <br>
     * This number has a limited number of digits.<br>
     * This method is redefined in the different daughter's classes.
     *
     * @param pMaxValueDigit
     *            The max value digit
     * @return A number in the form of an ArrayList of integer
     *
     * @see #getNumberInput(int)
     */
    public abstract ArrayList<Integer> giveNumber(int pMaxValueDigit);

    /**
     * This method allows to get the number entered by the user and return is as an
     * ArrayList of integer.<br>
     * <br>
     * Return an empty ArrayList if the input is not valide (too long, no digit
     * character, digit not in the game rules)
     *
     * @param pMaxValueDigit
     *            The max value digit
     * @return A number in the form of an ArrayList of integer
     */
    // TODO Move this method in HumanPlayer
    protected ArrayList<Integer> getNumberInput(int pMaxValueDigit) {

        logger.trace("Get Number input"); //$NON-NLS-1$

        String str;
        ArrayList<Integer> number = new ArrayList<>();

        // TODO warning à prendre en compte
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();

        logger.trace("User input : " + str); //$NON-NLS-1$
        logger.trace("User input length : " + str.length()); //$NON-NLS-1$
        if (str.length() == CombineGame.NB_DIGITS_MYSTERY) {
            for (int i = 0; i < CombineGame.NB_DIGITS_MYSTERY; i++) {
                logger.trace("char : " + str.charAt(i)); //$NON-NLS-1$
                if (Character.isDigit(str.charAt(i))) {
                    int digit = Character.getNumericValue(str.charAt(i));
                    logger.trace("digit : " + digit); //$NON-NLS-1$
                    if (digit <= pMaxValueDigit) {
                        number.add(new Integer(digit));
                        logger.trace("number : " + number); //$NON-NLS-1$
                    } else {
                        number.clear();
                        logger.trace("number returned : " + number); //$NON-NLS-1$
                        return number;
                    }
                } else {
                    number.clear();
                    logger.trace("number returned : " + number); //$NON-NLS-1$
                    return number;
                }
            }
        } else {
            number.clear();
            logger.trace("number returned : " + number); //$NON-NLS-1$
            return number;
        }
        logger.trace("number returned : " + number); //$NON-NLS-1$
        return number;
    }
}
