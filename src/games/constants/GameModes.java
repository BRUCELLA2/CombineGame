package games.constants;

/**
 * <b>Enumeration of the different game modes available for CombineGame</b>
 *
 * @author BRUCELLA2
 * @version 1.0.3
 */
public enum GameModes {
    /**
     * Challenger Mode<br>
     * <br>
     * In this mode the player makes proposals alone.
     */
    CHALLENGER("Mode Challenger"), //$NON-NLS-1$

    /**
     * Defender Mode<br>
     * <br>
     * In this mode, the computer makes proposals
     */
    DEFENDER("Mode Défenseur"), //$NON-NLS-1$

    /**
     * Duel Mode<br>
     * <br>
     * In this mode, the player fights the computer.
     */
    DUEL("Mode Duel"); //$NON-NLS-1$

    // ***** VARIABLES *****/

    /**
     * Name of the game mode
     */
    private String modeName;


    // ***** CONSTRUCTORS *****/

    /**
     * Constructor
     *
     * @param pModeName
     *            Name of the game mode
     */
    GameModes(final String pModeName) {

        this.setModeName(pModeName);
    }


    // ***** GETTERS *****/

    /**
     * Returns the name of the game mode
     *
     * @return the name of the game mode
     */
    public String getModeName() {

        return this.modeName;
    }


    // ***** SETTERS *****/

    /**
     * Allows to define the name of the game mode
     *
     * @param pModeName
     *            the name of the game mode
     */
    private void setModeName(final String pModeName) {

        this.modeName = pModeName;
    }

}
