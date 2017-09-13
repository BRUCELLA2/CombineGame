package games;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <b>Main Class which start combineGame</b>
 *
 *
 * @author BRUCELLA2
 * @version 1.0.2
 *
 * @see CombineGame
 */
public class Launcher {

    /**
     * Log4j2 logger
     */
    private static final Logger logger = LogManager.getLogger(Launcher.class);

    /**
     * Main methode of the project.
     *
     * The start of combineGame (combinations games) is done from this method.
     *
     * @author BRUCELLA2
     * @param args
     *            A string array containing command line arguments
     *
     */
    public static void main(String[] args) {

        logger.trace("********************  Launch  *******************************"); //$NON-NLS-1$

        new CombineGame();

        logger.trace("*****************  End  ************************************"); //$NON-NLS-1$
    }

}
