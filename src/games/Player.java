package games;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <b>This class represents a player</b><br>
 * <br>
 *
 * @author BRUCELLA2
 * @version 1.0.8
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
     * This method is used to obtain a number in the form of an ArrayList of integer.<br>
     * <br>
     * This number has a limited number of digits.<br>
     * This method is redefined in the different daughter's classes.
     *
     * @param pMaxValueDigit
     *            The max value digit
     * @return A number in the form of an ArrayList of integer
     *
     */
    public abstract List<Integer> giveNumber(int pMaxValueDigit);

}
