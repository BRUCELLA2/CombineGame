package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import games.constants.GameModes;
import players.ComputerPlayer;
import players.HumanPlayer;


/**
 * <b>Mastermind is a game where you have to guess a pattern of color in good order.</b><br>
 * <br>
 * In this version, there is no color, only number. The game therefore consists in discover the pattern of number in
 * good order.<br>
 * Each digit composing the mystery pattern is compared to each digit composing the pattern proposed.<br>
 * The player can make a limited number of tries to discover the mystery pattern.<br>
 * <br>
 * Mastermind class represents the Mastermind game. The methods in this class do not perform any direct display<br>
 * The elements that must be displayed are sent to the Display object, wich will then adjust the graphical aspects.<br>
 *
 * @author BRUCELLA2
 * @version 1.1.0
 */
public class Mastermind extends Game {

// ***** VARIABLES *****//

    /**
     * Log4j2 Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(Mastermind.class);

    /**
     * Number of digits/colors in bad position regarding the pattern.
     *
     * @see #getBadPos()
     * @see #setBadPos(int)
     */
    private int badPos;

    /**
     * Number of digits/colors in good position regarding the pattern.
     *
     * @see #getGoodPos()
     * @see #setGoodPos(int)
     */
    private int goodPos;


// ***** CONSTRUCTORS *****//

    /**
     * Constructor of the Mastermind class.<br>
     * <br>
     * The constructor initializes the various variables and starts the game with the desired mode.
     *
     * @param pGameMode
     *            game mode to be used
     *
     * @see #playChallenger()
     * @see #playDefender()
     * @see #playDuel()
     *
     */
    protected Mastermind(final GameModes pGameMode) {

        super(pGameMode);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("********** START MASTERMIND GAME ************"); //$NON-NLS-1$
            LOGGER.debug("RemainingTries = " + this.getNbRemainingTries()); //$NON-NLS-1$
        }
    }


// ***** GETTERS *****//

    /**
     * Returns the number of digits/colors in bad position regarding the pattern.
     *
     * @return number of digits/colors in bad position regarding the pattern.
     *
     * @see #setBadPos(int)
     */
    public int getBadPos() {

        return this.badPos;
    }


    /**
     * Returns the number of digits/colors in good position regarding the pattern.
     *
     * @return number of digits/colors in good position regarding the pattern.
     *
     * @see #setGoodPos(int)
     */
    public int getGoodPos() {

        return this.goodPos;
    }


// ***** SETTERS *****//

    /**
     * Allows to define the number of digits/colors in bad position regarding the pattern.
     *
     * @param pBadPos
     *            number of digits/colors in bad position regarding the pattern.
     */
    public void setBadPos(final int pBadPos) {

        this.badPos = pBadPos;
    }


    /**
     * Allows to define the number of digits/colors in good position regarding the pattern.
     *
     * @param pGoodPos
     *            number of digits/colors in good position regarding the pattern.
     */
    public void setGoodPos(final int pGoodPos) {

        this.goodPos = pGoodPos;
    }


// ***** METHODS *****//

    /**
     * This method allows to compare the number proposed as a parameter with the mystery pattern.<br>
     * <br>
     * The comparison is done digit by digit.<br>
     * The first comparison search for digit in good position. After, there is a second comparison which search for
     * digit present in the pattern.<br>
     * Number of digits in good and bad position is returned in the form of a String.
     *
     * @param pNumberToCompare
     *            The number to be compared to the mystery number.
     * @return The result of the comparison in the form of a String
     *
     * @see Mastermind#findNbDigitInMystery(int[], int[])
     * @see Mastermind#findNbDigitsGoodPos(int[], int[])
     */
    @Override
    public String compareNumber(final int[] pNumberToCompare, final int[] pMysteryNumber) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Start compare number"); //$NON-NLS-1$
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Number to compare : " + pNumberToCompare); //$NON-NLS-1$
            LOGGER.debug("Mystery number : " + pMysteryNumber); //$NON-NLS-1$
        }

        int nbDigitInMystery = Mastermind.findNbDigitInMystery(pNumberToCompare, pMysteryNumber);
        this.setGoodPos(Mastermind.findNbDigitsGoodPos(pNumberToCompare, pMysteryNumber));
        this.setBadPos(nbDigitInMystery - this.getGoodPos());

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Nb same digit in mystery : " + nbDigitInMystery); //$NON-NLS-1$
            LOGGER.trace("Nb digit in good position : " + this.getGoodPos()); //$NON-NLS-1$
            LOGGER.trace("Nb digit in bad position : " + this.getBadPos()); //$NON-NLS-1$
        }
        String result = this.getBadPos() + " pr�sent(s), " + this.getGoodPos() + " bien plac�(s)"; //$NON-NLS-1$ //$NON-NLS-2$

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("result comparison : " + result); //$NON-NLS-1$
        }
        return result;
    }


    /**
     * This method ask to computer player to give a number, compare this number with the mystery pattern, save the good
     * position and bad position for this proposition, and display the result of the comparison and return the
     * proposition.
     *
     * @param pComputerPlayer
     *            The computer player
     * @param pMysteryNumber
     *            the mystery number to discover
     *
     * @return the proposition number of the computer.
     */
    @Override
    public int[] computerTurn(final ComputerPlayer pComputerPlayer, final int[] pMysteryNumber) {

        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);

        // Ask computer to give a number
        int[] computerProposition = pComputerPlayer.giveNumberPattern();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Computer proposition :"); //$NON-NLS-1$
            LOGGER.debug(computerProposition);
        }
        // Compare computer's proposition to mystery pattern
        String comparison = this.compareNumber(computerProposition, pMysteryNumber);

        // Store the comparison results
        pComputerPlayer.addGoodResultMastermind(computerProposition, this.getGoodPos());
        pComputerPlayer.addBadResultMastermind(computerProposition, this.getBadPos());

        // Show the computer proposition and the result
        CombineGame.getDisplay().print("\nProposition de l'ordinateur: "); //$NON-NLS-1$
        CombineGame.getDisplay().print(computerProposition);
        CombineGame.getDisplay().print(" -> R�ponse : " + comparison //$NON-NLS-1$
                + "   (Appuyez sur entr�e pour continuer)"); //$NON-NLS-1$

        // Wait user input to continue
        scan.nextLine();

        return computerProposition;
    }


    /**
     * This methods search and count the number of digit at the good position compare to the mystery pattern.<br>
     *
     * @param pNumberToCompare
     *            The number to be compared to the mystery number.
     * @param pMysteryNumber
     *            The mystery pattern to discover.
     *
     * @return the number of digit at the good position compare to the mystery pattern
     */
    public static int findNbDigitsGoodPos(final int[] pNumberToCompare, final int[] pMysteryNumber) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Start search for nb digits in good pos"); //$NON-NLS-1$
            LOGGER.trace("Number to compare : " + pNumberToCompare); //$NON-NLS-1$
            LOGGER.trace("Mystery number : " + pMysteryNumber); //$NON-NLS-1$
        }
        int goodPosFind = 0;

        for (int i = 0; i < CombineGame.getNbDigitsMystery(); i++) {
            if (pNumberToCompare[i] == pMysteryNumber[i]) {
                goodPosFind++;

                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("Nb digits in good pos mystery : " + goodPosFind); //$NON-NLS-1$
                }
            }
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Nb digits in good pos : " + goodPosFind); //$NON-NLS-1$
        }
        return goodPosFind;
    }


    /**
     * This methods search and count the number of digit present in the mystery pattern.<br>
     *
     * @param pNumberToCompare
     *            The number to be compared to the mystery number.
     * @param pMysteryNumber
     *            The mystery pattern to discover.
     *
     * @return the number of digit present in the mystery pattern
     */
    public static int findNbDigitInMystery(final int[] pNumberToCompare, final int[] pMysteryNumber) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Start search nb same digit in mystery"); //$NON-NLS-1$
            LOGGER.trace("Number to compare : " + pNumberToCompare); //$NON-NLS-1$
            LOGGER.trace("Mystery number : " + pMysteryNumber); //$NON-NLS-1$
        }
        int nbDigitsInMystery = 0;

        List<Integer> mysteryNumberCopy = new ArrayList<>();
        List<Integer> pNumberToCompareCopy = new ArrayList<>();

        for (int i = 0; i < CombineGame.getNbDigitsMystery(); i++) {
            mysteryNumberCopy.add(Integer.valueOf(pNumberToCompare[i]));
            pNumberToCompareCopy.add(Integer.valueOf(pMysteryNumber[i]));
        }

        ListIterator<Integer> itNumberToCompare = pNumberToCompareCopy.listIterator();

        while (itNumberToCompare.hasNext()) {

            int proposedDigit = itNumberToCompare.next().intValue();

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("Proposed digit : " + proposedDigit); //$NON-NLS-1$
            }
            ListIterator<Integer> itMysteryNumberCopy = mysteryNumberCopy.listIterator();

            while (itMysteryNumberCopy.hasNext()) {

                int mysteryDigit = itMysteryNumberCopy.next().intValue();

                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("Mystery digit : " + mysteryDigit); //$NON-NLS-1$
                }
                if (proposedDigit == mysteryDigit) {

                    nbDigitsInMystery++;
                    itMysteryNumberCopy.remove();
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("Nb same digits in mystery : " + nbDigitsInMystery); //$NON-NLS-1$
                        LOGGER.trace("Digit remaining in mystery : " + mysteryNumberCopy); //$NON-NLS-1$
                    }
                    break;
                }
            }
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Number same digit in mystery after searching : " + nbDigitsInMystery); //$NON-NLS-1$
        }
        return nbDigitsInMystery;
    }


    /**
     * This method ask to the player to give a number, compare this number with the mystery number, display the result
     * of the comparison and return the proposition.
     *
     * @param pHumanPlayer
     *            The human player
     * @param pMysteryNumber
     *            the mystery number to discover
     *
     * @return the proposition number of the player.
     */
    @Override
    public int[] humanTurn(final HumanPlayer pHumanPlayer, final int[] pMysteryNumber) {

        // Ask human player to give a number
        int[] humanProposition = pHumanPlayer.giveNumber(CombineGame.getNbColors());

        // Compare human player's proposition to mystery pattern
        String comparison = this.compareNumber(humanProposition, pMysteryNumber);

        // Show the human player proposition and the result
        CombineGame.getDisplay().println(comparison);

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Proposed number by player : " + humanProposition); //$NON-NLS-1$
            LOGGER.trace("Result of comparison : " + comparison); //$NON-NLS-1$
        }

        return humanProposition;
    }


    /**
     * This method executes the game with the Challenger mode.<br>
     * <br>
     * In this mode, the player must find the good pattern of number in a maximum number of tries.<br>
     * If the player finds the right combination, the game stops and displays the victory message.<br>
     * If the player does not find the right combination, the game stops and displays the defeat message.
     *
     * @see #mysteryNumberGeneration(int)
     * @see #victory()
     * @see #defeat(int[])
     */
    @Override
    public void playChallenger() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start Challenger mode"); //$NON-NLS-1$
        }
        HumanPlayer humanPlayer = new HumanPlayer("Joueur"); //$NON-NLS-1$
        int[] mysteryNumber = this.mysteryNumberGeneration(CombineGame.getNbColors());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Mystery number generated : " + mysteryNumber); //$NON-NLS-1$
        }
        if (CombineGame.isDeveloperMode()) {
            CombineGame.getDisplay().print("Developpeur Mode : le nombre myst�re g�n�r� est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumber);
        }

        while (!this.isEndGame()) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
            }
            // Decrease number of remaining tries at the start of each turn
            this.setNbRemainingTries(this.getNbRemainingTries() - 1);

            int[] proposedNumber = this.humanTurn(humanPlayer, mysteryNumber);

            if (Arrays.equals(proposedNumber, mysteryNumber)) {
                this.victory();
            }
            else if (this.getNbRemainingTries() == 0) {
                this.defeat(mysteryNumber);
            }
        }

    }


    /**
     * This method executes the game with the defender mode.<br>
     * <br>
     * In this mode, the player gives the mystery digits pattern and the computer has to discover it.<br>
     * Each time the computer makes a proposal, the result is displayed.<br>
     * The human player must press "enter" to trigger the next computer proposal.<br>
     * <br>
     * If the computer find the right pattern, the game stops and displays the defeat message.<br>
     * If the computer does not find the right combination, the game stops and displays the victory message.
     *
     */
    @Override
    public void playDefender() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start Defender mode"); //$NON-NLS-1$
        }
        HumanPlayer human = new HumanPlayer("Joueur"); //$NON-NLS-1$
        ComputerPlayer computer = new ComputerPlayer("ordinateur"); //$NON-NLS-1$

        int[] mysteryNumber = human.giveNumber(CombineGame.getNbColors());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Mystery number : " + Arrays.toString(mysteryNumber)); //$NON-NLS-1$
        }
        if (CombineGame.isDeveloperMode()) {
            CombineGame.getDisplay().print("Developpeur Mode : le nombre myst�re est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumber);
            CombineGame.getDisplay().println(""); //$NON-NLS-1$
        }

        while (!this.isEndGame()) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
            }
            // Decrease number of remaining tries at the start of each turn
            this.setNbRemainingTries(this.getNbRemainingTries() - 1);

            int[] proposition = this.computerTurn(computer, mysteryNumber);

            // Check if it's the end of game
            if (Arrays.equals(proposition, mysteryNumber)) {
                this.defeat(mysteryNumber);
            }
            else if (this.getNbRemainingTries() == 0) {
                this.victory();
            }
        }

    }


    /**
     * This method executes the game in duel mode<br>
     * <br>
     * In this mode, the player and the computer give the mystery digits pattern<br>
     * In each turn, player and computer make proposals to find the mystery digit pattern make either by computer or
     * player<br>
     * If player find the pattern before the computer, player wins.<br>
     * If computer find the pattern before the player, player loose. <br>
     * If computer and player don't find the other pattern, it's an equality.<br>
     *
     */
    @Override
    public void playDuel() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start Duel mode"); //$NON-NLS-1$
        }
        HumanPlayer human = new HumanPlayer("Joueur"); //$NON-NLS-1$
        ComputerPlayer computer = new ComputerPlayer("Ordinateur"); //$NON-NLS-1$

        int[] humanMysteryNumber = human.giveNumber(CombineGame.getNbColors());
        int[] computerMysteryNumber = this.mysteryNumberGeneration(CombineGame.getNbColors());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Mystery Number computer = " + computerMysteryNumber); //$NON-NLS-1$
            LOGGER.debug("Mystery Number human = " + humanMysteryNumber); //$NON-NLS-1$
        }
        if (CombineGame.isDeveloperMode()) {
            CombineGame.getDisplay().print("Developpeur Mode : " //$NON-NLS-1$
                    + "le nombre myst�re g�n�r� par le joueur est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(humanMysteryNumber);
            CombineGame.getDisplay().print("Developpeur Mode : " //$NON-NLS-1$
                    + "le nombre myst�re g�n�r� par l'ordinateur est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(computerMysteryNumber);
        }

        while (!this.isEndGame()) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
            }
            // Decrease number of remaining tries at the start of each turn
            this.setNbRemainingTries(this.getNbRemainingTries() - 1);

            // Human turn

            int[] humanProposition = this.humanTurn(human, computerMysteryNumber);

            if (Arrays.equals(humanProposition, computerMysteryNumber)) {
                this.victory();
                break;
            }

            // Computer turn

            int[] computerProposition = this.computerTurn(computer, humanMysteryNumber);

            if (Arrays.equals(computerProposition, humanMysteryNumber)) {
                this.defeat(computerMysteryNumber);
            }
            else if (this.getNbRemainingTries() == 0) {
                this.equality();
            }

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
            }

        }
    }
}
