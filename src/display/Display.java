package display;

import java.util.List;
import java.util.ListIterator;

import games.constants.GameModes;
import games.constants.GameNames;
import games.constants.NavigationMenu;

/**
 * Display allow to manage all the display.<br>
 * <br>
 * Currently the display is made by console output.
 *
 * @author BRUCELLA2
 * @version 1.0.2
 *
 */
public class Display {

    // ***** CONSTRUCTORS *****/

    /**
     * Display class's constructor.<br>
     * <br>
     * At the initialization, display the CombineGame introduction message.
     *
     */
    public Display() {

        System.out.println("Bienvenue dans CombineGame"); //$NON-NLS-1$
    }

    // ***** METHODS *****/

    /**
     * showGamesMenu show the menu to chose the game in CombineGame<br>
     * <br>
     * this method just do a display. There is no recuperation of user input here.
     * <br>
     */
    @SuppressWarnings("static-method")
    public void showGamesMenu() {

        System.out.println("Choisir un jeu : \n"); //$NON-NLS-1$
        System.out.println("1 - " + GameNames.MORE_LESS.getGameName()); //$NON-NLS-1$
        System.out.println("2 - " + GameNames.MASTERMIND.getGameName()); //$NON-NLS-1$
        System.out.println("Q - " + NavigationMenu.EXIT.getNavigationName()); //$NON-NLS-1$
    }

    /**
     * showGameModesMenu show the menu to chose the game mode.<br>
     * <br>
     * this method just do a display. There is no recuperation of user input here.
     */
    @SuppressWarnings("static-method")
    public void showGameModesMenu() {

        System.out.println("Choisir un mode de jeu : \n"); //$NON-NLS-1$
        System.out.println("1 - " + GameModes.CHALLENGER.getModeName()); //$NON-NLS-1$
        System.out.println("2 - " + GameModes.DEFENDER.getModeName()); //$NON-NLS-1$
        System.out.println("3 - " + GameModes.DUEL.getModeName()); //$NON-NLS-1$
        System.out.println("R - " + NavigationMenu.RETURN.getNavigationName()); //$NON-NLS-1$
    }

    /**
     * showEndGameMenu show the end game menu which allows user to give what he
     * wants to do thereadter<br>
     * <br>
     * this method just do a display. There is no recuperation of user input here.
     */
    @SuppressWarnings("static-method")
    public void showEndGameMenu() {

        System.out.println("Jeu terminé. Que souhaitez-vous faire : \n"); //$NON-NLS-1$
        System.out.println("1 - " + NavigationMenu.REPLAY.getNavigationName()); //$NON-NLS-1$
        System.out.println("R - " + NavigationMenu.RETURN.getNavigationName()); //$NON-NLS-1$
        System.out.println("Q - " + NavigationMenu.EXIT.getNavigationName()); //$NON-NLS-1$
    }

    /**
     * Allow to display a String and then terminate the line.
     *
     * @param pString
     *            String to display
     */
    @SuppressWarnings("static-method")
    public void println(final String pString) {

        System.out.println(pString);
    }

    /**
     * Allow to display a String without terminate the line.
     *
     * @param pString
     *            String to display
     */
    @SuppressWarnings("static-method")
    public void print(final String pString) {

    	System.out.print(pString);
    }

    /**
     * Allow to display a ArrayList of Integer without terminate the line.
     *
     * @param pArrayList
     *            ArrayList of Integer to show
     */
    @SuppressWarnings("static-method")
    public void print(final List<Integer> pArrayList) {

        StringBuilder bld = new StringBuilder();
        ListIterator<Integer> li = pArrayList.listIterator();

        while (li.hasNext()) {
            bld.append(li.next().toString());
        }

        System.out.print(bld.toString());
    }

    /**
     * Allow to display a ArrayList of Integer with terminate the line.
     *
     * @param pArrayList
     *            ArrayList of Integer to show
     */
    @SuppressWarnings("static-method")
    public void println(final List<Integer> pArrayList) {

        StringBuilder bld = new StringBuilder();
        ListIterator<Integer> li = pArrayList.listIterator();

        while (li.hasNext()) {
            bld.append(li.next().toString());
        }

        System.out.println(bld.toString());
    }
}
