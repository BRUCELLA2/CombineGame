package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <b>This class represents a human player</b>
 *
 * @author BRUCELLA2
 * @version 1.0.7
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
    protected ArrayList<Integer> getNumberInput(final int pMaxValueDigit) {

        LOGGER.trace("Get Number input"); //$NON-NLS-1$

        String str;
        ArrayList<Integer> number = new ArrayList<>();

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
