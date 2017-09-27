package games.constants;

/**
 * <b>Enumeration of the game's name of CombineGame</b>
 *
 * @author BRUCELLA2
 * @version 1.0.3
 */
public enum GameNames {

    /**
     * MoreLess game
     */
    MORE_LESS("Jeu du plus et du moins"), //$NON-NLS-1$
    /**
     * Mastermind game
     */
    MASTERMIND("Jeu du Mastermind"); //$NON-NLS-1$

    // ***** VARIABLES *****/

    /**
     * Game's name
     */
    private String gameName;


    // ***** CONSTRUCTORS *****/

    /**
     * Constructor
     *
     * @param pGameName
     *            Name of the game
     */
    GameNames(final String pGameName) {

        this.gameName = pGameName;
    }


    // ***** GETTERS *****/

    /**
     * Returns the name of the game
     *
     * @return Game's name
     */
    public String getGameName() {

        return this.gameName;
    }

}
