package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <b>This class represents a player</b><br>
 * <br>
 *
 * @author BRUCELLA2
 * @version 1.0.6
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
     * Log4j2 Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(Player.class);

    // ***** CONSTRUCTORS *****//

    /**
     * Player's class constructor<br>
     * <br>
     * The name and display are initialized from the parameters provided to the
     * constructor
     *
     * @param pPlayerGame
     *            The player's name
     */
    public Player(final String pPlayerGame) {

        LOGGER.trace("Player construction"); //$NON-NLS-1$

        this.setPlayerName(pPlayerGame);

        LOGGER.trace("Player name : " + this.getPlayerName()); //$NON-NLS-1$
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

    // ***** SETTERS *****//

    /**
     * Allows to define the player's name
     *
     * @param pPlayerName
     *            Player's name
     *
     * @see #getPlayerName()
     */
    public void setPlayerName(final String pPlayerName) {

        this.playerName = pPlayerName;
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
    public abstract List<Integer> giveNumber(int pMaxValueDigit);

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
    protected ArrayList<Integer> getNumberInput(final int pMaxValueDigit) {

        LOGGER.trace("Get Number input"); //$NON-NLS-1$

        String str;
        ArrayList<Integer> number = new ArrayList<>();

        // TODO warning à prendre en compte
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();

        LOGGER.trace("User input : " + str); //$NON-NLS-1$
        LOGGER.trace("User input length : " + str.length()); //$NON-NLS-1$
        if (str.length() == CombineGame.getNbDigitsMystery()) {
            for (int i = 0; i < CombineGame.getNbDigitsMystery(); i++) {
                LOGGER.trace("char : " + str.charAt(i)); //$NON-NLS-1$
                if (Character.isDigit(str.charAt(i))) {
                    int digit = Character.getNumericValue(str.charAt(i));
                    LOGGER.trace("digit : " + digit); //$NON-NLS-1$
                    if (digit <= pMaxValueDigit) {
                        number.add(Integer.valueOf(digit));
                        LOGGER.trace("number : " + number); //$NON-NLS-1$
                    }
                    else {
                        number.clear();
                        LOGGER.trace("number returned : " + number); //$NON-NLS-1$
                        return number;
                    }
                }
                else {
                    number.clear();
                    LOGGER.trace("number returned : " + number); //$NON-NLS-1$
                    return number;
                }
            }
        }
        else {
            number.clear();
            LOGGER.trace("number returned : " + number); //$NON-NLS-1$
            return number;
        }
        LOGGER.trace("number returned : " + number); //$NON-NLS-1$
        return number;
    }
}
