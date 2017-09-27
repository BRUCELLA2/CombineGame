package games;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <b>Main Class which start combineGame</b>
 *
 *
 * @author BRUCELLA2
 * @version 1.0.5
 *
 * @see CombineGame
 */
public class Launcher {

    /**
     * Log4j2 LOGGER
     */
    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);


    /**
     * Main method of the project.
     *
     * The start of combineGame (combinations games) is done from this method.
     *
     * @author BRUCELLA2
     * @param args
     *            A string array containing command line arguments
     *
     */
    public static void main(final String[] args) {

        LOGGER.trace("********************  Launch  *******************************"); //$NON-NLS-1$

        CombineGame combineGame = new CombineGame();
        combineGame.launchCombineGame();

        LOGGER.trace("*****************  End  ************************************"); //$NON-NLS-1$
    }

}
