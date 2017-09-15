package games;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <b>This class represents a human player</b>
 *
 * @author BRUCELLA2
 * @version 1.0.6
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

        LOGGER.trace("HumanPlayer construction"); //$NON-NLS-1$
    }

    // ***** METHODS *****//

    /**
     * This method asks the user to provide a number via the display.<br>
     * <br>
     * This number is retrieved as String and transformed into an ArrayList of
     * integer.
     *
     * @param pMaxValueDigit
     *            The max value digit
     *
     * @return A number in the form of an ArrayList of integer
     */
    @Override
    public List<Integer> giveNumber(final int pMaxValueDigit) {

        LOGGER.trace("Human Give number"); //$NON-NLS-1$
        LOGGER.trace("Max value digit = " + pMaxValueDigit); //$NON-NLS-1$

        ArrayList<Integer> number;

        // TODO to be factorised ?
        do {
            CombineGame.getDisplay().println("Donnez le nombre mystère : "); //$NON-NLS-1$
            number = this.getNumberInput(pMaxValueDigit);

            LOGGER.debug("Number Input : " + number); //$NON-NLS-1$

            if (number.isEmpty()) {
                CombineGame.getDisplay().println("Saisie incorrecte"); //$NON-NLS-1$
            }
        } while (number.isEmpty());

        return number;
    }

    /**
     * This method asks the user to provide a mystery number (i. e. a number to
     * guess).<br>
     * <br>
     * This number obtained as String is transformed into an integer ArrayList.
     *
     * @param pMaxValueDigit
     *            The max value digit
     *
     * @return A number in the form of an ArrayList of integer
     */
    public List<Integer> createMysteryNumber(final int pMaxValueDigit) {

        LOGGER.trace("Create Mystery Number"); //$NON-NLS-1$
        LOGGER.trace("Max value digit = " + pMaxValueDigit); //$NON-NLS-1$

        ArrayList<Integer> number;

        // TODO to be factorised ?
        do {
            CombineGame.getDisplay().println("Donnez la combinaison secrète : "); //$NON-NLS-1$
            number = this.getNumberInput(pMaxValueDigit);

            LOGGER.debug("Number input : " + number); //$NON-NLS-1$

            if (number.isEmpty()) {
                CombineGame.getDisplay().println("Saisie incorrecte"); //$NON-NLS-1$
            }
        } while (number.isEmpty());

        return number;
    }

}
