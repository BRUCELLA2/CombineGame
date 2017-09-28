package players;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <b>This class represents a player</b><br>
 * <br>
 *
 * @author BRUCELLA2
 * @version 1.1.0
 *
 */
public abstract class Player {

// ***** VARIABLES *****//

    /**
     * Log4j2 Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(Player.class);

    /**
     * Player's name
     *
     * @see #getPlayerName()
     * @see #setPlayerName(String)
     */
    private String playerName;


// ***** CONSTRUCTORS *****//

    /**
     * Player's class constructor<br>
     * <br>
     * The name and display are initialized from the parameters provided to the constructor
     *
     * @param pPlayerName
     *            The player's name
     */
    public Player(final String pPlayerName) {

        LOGGER.trace("Player construction"); //$NON-NLS-1$

        this.playerName = pPlayerName;

        LOGGER.trace("Player name : " + this.playerName); //$NON-NLS-1$
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
     * This method is used to obtain a number in the form of an array of int.<br>
     * <br>
     * This number has a limited number of digits.<br>
     * This method is redefined in the different daughter's classes.
     *
     * @param pMaxValueDigit
     *            The max value digit
     * @return A number in the form of an array of int.
     *
     */
    public abstract int[] giveNumber(int pMaxValueDigit);

}
