package games.constants;

/**
 * <b>Enumeration of the game's name of CombineGame</b>
 *
 * @author BRUCELLA2
 * @version 1.0.2
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

        this.setGameName(pGameName);
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


    // ***** SETTERS *****/

    /**
     * Allow to define the name of the game
     *
     * @param pGameName
     *            Game's name
     */
    private void setGameName(final String pGameName) {

        this.gameName = pGameName;
    }

}
