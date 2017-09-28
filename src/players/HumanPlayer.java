package players;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import games.CombineGame;


/**
 * <b>This class represents a human player</b>
 *
 * @author BRUCELLA2
 * @version 1.1.0
 */
public class HumanPlayer extends Player {

// ***** VARIABLES *****//

    /**
     * Log4j2 Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(HumanPlayer.class);


// ***** CONSTRUCTORS *****//

    /**
     * Constructor of the HumanPlayer class.
     *
     * @param pPlayerName
     *            Player's name
     */
    public HumanPlayer(final String pPlayerName) {

        super(pPlayerName);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("HumanPlayer construction"); //$NON-NLS-1$
        }
    }


// ***** METHODS *****//

    /**
     * This method allows to get the number entered by the user and return is as an array of int.<br>
     * <br>
     * Return an empty array if the input is not valid (too long, no digit character, digit not in the game rules)
     *
     * @param pMaxValueDigit
     *            The max value digit
     * @return A number in the form of an array of int.
     */
    protected int[] fetchNumberInput(final int pMaxValueDigit) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Get Number input"); //$NON-NLS-1$
        }
        String str;
        int[] number = new int[CombineGame.getNbDigitsMystery()];

        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("User input : " + str); //$NON-NLS-1$
            LOGGER.trace("User input length : " + str.length()); //$NON-NLS-1$
        }
        if (str.length() == CombineGame.getNbDigitsMystery()) {

            for (int i = 0; i < CombineGame.getNbDigitsMystery(); i++) {

                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("char : " + str.charAt(i)); //$NON-NLS-1$
                }

                if (Character.isDigit(str.charAt(i))) {
                    int digit = Character.getNumericValue(str.charAt(i));
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("digit : " + digit); //$NON-NLS-1$
                    }
                    if (digit <= pMaxValueDigit) {
                        number[i] = digit;
                        if (LOGGER.isTraceEnabled()) {
                            LOGGER.trace("number : " + number); //$NON-NLS-1$
                        }
                    }
                    else {
                        if (LOGGER.isTraceEnabled()) {
                            LOGGER.trace("number returned : " + number); //$NON-NLS-1$
                        }
                        return new int[0];
                    }
                }
                else {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("number returned : " + number); //$NON-NLS-1$
                    }
                    return new int[0];
                }
            }
        }
        else {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("number returned : " + number); //$NON-NLS-1$
            }
            return new int[0];
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("number returned : " + number); //$NON-NLS-1$
        }

        return number;
    }


    /**
     * This method asks the user to provide a number via the display.<br>
     * <br>
     * This number is retrieved as String and transformed into an array of int.
     *
     * @param pMaxValueDigit
     *            The max value digit
     *
     * @return A number in the form of an array of int.
     */
    @Override
    public int[] giveNumber(final int pMaxValueDigit) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Human Give number"); //$NON-NLS-1$
            LOGGER.trace("Max value digit = " + pMaxValueDigit); //$NON-NLS-1$
        }
        int[] number;

        do {
            CombineGame.getDisplay().println("\nQuel est le nombre mystère : "); //$NON-NLS-1$
            number = this.fetchNumberInput(pMaxValueDigit);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Number Input : " + number); //$NON-NLS-1$
            }
            if (number.length == 0) {
                CombineGame.getDisplay().println("Saisie incorrecte"); //$NON-NLS-1$
            }

        } while (number.length == 0);

        return number;
    }
}
