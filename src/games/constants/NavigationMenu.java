package games.constants;

/**
 * <b>Enumeration of the different navigations in the CombineGame menus</b>
 *
 * @author BRUCELLA2
 * @version 1.0.3
 */
public enum NavigationMenu {

    /**
     * Leave CombineGame<br>
     * <br>
     */
    EXIT("Quitter CombineGame"), //$NON-NLS-1$

    /**
     * Back to game selection
     */
    RETURN("Retour choix du jeu"), //$NON-NLS-1$
    /**
     * Replay the same game again and in the same mode
     */
    REPLAY("Rejouer au même jeu et au même mode"); //$NON-NLS-1$

    // ***** VARIABLES *****//
    /**
     * Navigation's name
     */
    private String navigationName;


    // ***** CONSTRUCTORS *****//
    /**
     * Constructor
     *
     * @param pNavigationName
     *            Navigation's name
     */
    NavigationMenu(final String pNavigationName) {

        this.setNavigationName(pNavigationName);
    }


    // ***** GETTERS *****//

    /**
     * Returns the navigation's name
     *
     * @return the navigation's name
     */
    public String getNavigationName() {

        return this.navigationName;
    }


    // ***** SETTERS *****//

    /**
     * Allows to define the name of the navigation
     *
     * @param pNavigationName
     *            the name of the navigation
     */
    private void setNavigationName(final String pNavigationName) {

        this.navigationName = pNavigationName;
    }
}
