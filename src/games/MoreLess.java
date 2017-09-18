package games;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import games.constants.GameModes;


/**
 * <b>MoreLess is a game where you have to guess a mystery number.</b><br>
 * <br>
 * Each digit composing the number is compared to each digit composing the proposed number.<br>
 * The result of the comparison is a sequence of signs either + or - or = depending on the result.<br>
 * The player can make a limited number of tries to discover the mystery number.<br>
 * <br>
 * The MoreLess class represents the MoreLess game. The methods in this class do not perform any direct display<br>
 * The elements that must be displayed are sent to the Display object, which will then adjust the graphical aspects.<br>
 *
 *
 * @author BRUCELLA2
 * @version 1.0.10
 *
 */
public class MoreLess extends Game {

// ***** VARIABLES *****/

    /**
     * Log4j2 Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(MoreLess.class);


// ***** CONSTRUCTORS *****/

    /**
     * Constructor of the MoreLess class.<br>
     * <br>
     * The constructor initializes the various variables and starts the game with the desired mode.
     *
     * @param pGameMode
     *            game mode to be used
     *
     * @see #playChallenger()
     * @see #playDefender()
     * @see #playDuel()
     */
    public MoreLess(final GameModes pGameMode) {

        super(pGameMode);
        
        LOGGER.debug("********** START MORE LESS GAME ************"); //$NON-NLS-1$
        LOGGER.debug("RemainingTries = " + this.getNbRemainingTries()); //$NON-NLS-1$
    }


// ***** METHODS *****/

    /**
     * This method allows you to compare the number proposed as a parameter with the mystery number.<br>
     * <br>
     * The comparison is done digit by digit.<br>
     * The result of the comparison is returned in the form of a string of characters indicating for each digit the
     * result of the comparison: <br>
     * <ul>
     * <li>+ if the mystery number is higher than the proposed one</li>
     * <li>- if the mystery number is lower than the proposed one</li>
     * <li>= if the mystery number is identical to the proposed one</li>
     * </ul>
     *
     * @param pNumberToCompare
     *            The number to be compared to the mystery number.
     * @return The result of the comparison in the form of a String
     */
    @Override
    public String compareNumber(final List<Integer> pNumberToCompare, final List<Integer> pMysteryNumber) {

        LOGGER.debug("Compare " + pNumberToCompare //$NON-NLS-1$
                + " to mysteryNumber : " + pMysteryNumber); //$NON-NLS-1$

        ListIterator<Integer> itProposedNumber = pNumberToCompare.listIterator();
        ListIterator<Integer> itMysteryNumber = pMysteryNumber.listIterator();
        StringBuilder bld = new StringBuilder();

        while (itProposedNumber.hasNext()) {
            while (itMysteryNumber.hasNext()) {

                int proposedDigit = itProposedNumber.next().intValue();
                int mysteryDigit = itMysteryNumber.next().intValue();

                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("proposedDigit = " + proposedDigit); //$NON-NLS-1$
                    LOGGER.trace("mysteryDigit = " + mysteryDigit); //$NON-NLS-1$
                }

                if (proposedDigit - mysteryDigit < 0) {
                    bld.append("+"); //$NON-NLS-1$
                }
                else if (proposedDigit - mysteryDigit > 0) {
                    bld.append("-"); //$NON-NLS-1$
                }
                else {
                    bld.append("="); //$NON-NLS-1$
                }
                LOGGER.trace("result = " + bld.toString()); //$NON-NLS-1$
            }
        }

        LOGGER.debug("Result comparison = " + bld.toString()); //$NON-NLS-1$
        return bld.toString();
    }


    /**
     * This method ask to the computer to give a number, compare this number with the mystery number, save the
     * proposition and display the result of the comparison and return the proposition.
     *
     * @param pComputerPlayer
     *            The computer player
     * @param pMysteryNumber
     *            The mystery number to discover
     * @return the proposition number of the computer
     */
    @Override
    public List<Integer> computerTurn(final ComputerPlayer pComputerPlayer, final List<Integer> pMysteryNumber) {

        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);

        // Ask computer to give a number
        List<Integer> computerProposition = pComputerPlayer.giveNumber(CombineGame.getMaxValueDigit());

        // Compare computer's proposition to mystery number
        String comparison = this.compareNumber(computerProposition, pMysteryNumber);

        // Save the result and add it to the previous results
        pComputerPlayer.addResultatMoreLess(comparison);

        // Show the computer's proposition and the result
        String resultatComputer = " -> Réponse : " + comparison; //$NON-NLS-1$
        CombineGame.getDisplay().print("L'ordinateur a proposé un nombre : "); //$NON-NLS-1$
        CombineGame.getDisplay().print(computerProposition);
        CombineGame.getDisplay().print(resultatComputer);
        CombineGame.getDisplay().println(" (Appuyez sur entrée pour continuer)"); //$NON-NLS-1$

        // Wait user input to continue
        scan.nextLine();

        return computerProposition;
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
    public List<Integer> humanTurn(final HumanPlayer pHumanPlayer, final List<Integer> pMysteryNumber) {

        // Ask human player to give a number
        List<Integer> humanProposition = pHumanPlayer.giveNumber(CombineGame.getMaxValueDigit());

        // Compare human player's proposition to mystery number
        String comparison = this.compareNumber(humanProposition, pMysteryNumber);

        // Show the human player's proposition and the result
        String result = " -> Réponse : " + comparison; //$NON-NLS-1$
        CombineGame.getDisplay().print("Proposition : "); //$NON-NLS-1$
        CombineGame.getDisplay().print(humanProposition);
        CombineGame.getDisplay().println(result);
        CombineGame.getDisplay().println("");

        return humanProposition;
    }


    /**
     * This method executes the game with the Challenger mode.<br>
     * <br>
     * In this mode, the player must find the mystery number in a maximum number of tries.<br>
     * If the player finds the right combination, the game stops and displays the victory message.<br>
     * If the player does not find the right combination, the game stops and displays the defeat message.
     *
     * @see #mysteryNumberGeneration(int)
     * @see #victory()
     * @see #defeat(List)
     */
    @Override
    public void playChallenger() {

        LOGGER.debug("Start Challenger mode"); //$NON-NLS-1$

        HumanPlayer humanPlayer = new HumanPlayer("Player"); //$NON-NLS-1$
        List<Integer> mysteryNumber = this.mysteryNumberGeneration(CombineGame.getMaxValueDigit());
        
        if (CombineGame.isDeveloperMode()) {
            CombineGame.getDisplay().print("Developpeur Mode : Le nombre mystère généré est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumber);
            CombineGame.getDisplay().println(""); //$NON-NLS-1$
        }

        LOGGER.debug("Mystery Number generated = " + mysteryNumber); //$NON-NLS-1$

        while (!this.isEndGame()) {

            LOGGER.debug("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$

            // Decrease number of remaining tries at the start of each turn
            this.setNbRemainingTries(this.getNbRemainingTries() - 1);

            List<Integer> proposition = this.humanTurn(humanPlayer, mysteryNumber);

            if (proposition.equals(mysteryNumber)) {
                this.victory();
            }
            else if (this.getNbRemainingTries() == 0) {
                this.defeat(mysteryNumber);
            }

            LOGGER.trace("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
        }
    }


    /**
     * This method executes the game with the defender mode.<br>
     * <br>
     * In this mode, the player gives the mystery number and the computer has to discover it.<br>
     * Each time the computer makes a proposal, the result is displayed.<br>
     * The human player must press "enter" to trigger the next computer proposal.
     *
     * If the computer finds the right combination, the game stops and displays the defeat message.<br>
     * If the computer does not find the right combination, the game stops and displays the victory message.
     *
     */
    @Override
    public void playDefender() {

        LOGGER.debug("Start Defender mode"); //$NON-NLS-1$

        HumanPlayer humanPlayer = new HumanPlayer("Player"); //$NON-NLS-1$
        ComputerPlayer computerPlayer = new ComputerPlayer("Ordinateur"); //$NON-NLS-1$
        List<Integer> mysteryNumber = humanPlayer.giveNumber(CombineGame.getMaxValueDigit());
        
        if (CombineGame.isDeveloperMode()) {
            CombineGame.getDisplay().print("Developpeur Mode : Le nombre mystère choisi par le joueur est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumber);
            CombineGame.getDisplay().println(""); //$NON-NLS-1$
        }
        
        LOGGER.debug("Mystery Number proposed by player = " + mysteryNumber); //$NON-NLS-1$

        while (!this.isEndGame()) {

            LOGGER.debug("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$

            // Decrease number of remaining tries at the start of each turn
            this.setNbRemainingTries(this.getNbRemainingTries() - 1);

            List<Integer> number = this.computerTurn(computerPlayer, mysteryNumber);

            if (number.equals(mysteryNumber)) {
                this.defeat(mysteryNumber);
            }

            if (!number.equals(mysteryNumber) && this.getNbRemainingTries() == 0) {
                this.victory();
            }

            LOGGER.trace("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
        }
    }


    /**
     * This method executes the game in duel mode<br>
     * <br>
     * In this mode, the player and computer give a mystery number<br>
     * In each turn, player and computer make proposals to find the mystery number create either by computer or
     * player.<br>
     * If player find the mystery number before the computer, player wins.<br>
     * If computer find the mystery number before the player, player loose. <br>
     * If computer and player don't find the other mystery number, it's an equality.<br>
     */
    @Override
    public void playDuel() {

        LOGGER.debug("Start Duel mode"); //$NON-NLS-1$

        HumanPlayer humanPlayer = new HumanPlayer("Player"); //$NON-NLS-1$
        ComputerPlayer computerPlayer = new ComputerPlayer("Ordinateur"); //$NON-NLS-1$

        List<Integer> mysteryNumberComputer = this.mysteryNumberGeneration(CombineGame.getMaxValueDigit());
        List<Integer> mysteryNumberHuman = humanPlayer.giveNumber(CombineGame.getMaxValueDigit());

        LOGGER.debug("Mystery Number computer = " + mysteryNumberComputer); //$NON-NLS-1$
        LOGGER.debug("Mystery Number human = " + mysteryNumberHuman); //$NON-NLS-1$

        CombineGame.getDisplay().println("Les nombres mystères ont été créés. Que le meilleur gagne !\n"); //$NON-NLS-1$

        if (CombineGame.isDeveloperMode()) {
            CombineGame.getDisplay().print("Developpeur Mode : Le nombre mystère généré par l'ordinateur est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumberComputer);
            CombineGame.getDisplay().print("Developpeur Mode : Le nombre mystère généré par le jouer est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumberHuman);
        }

        while (!this.isEndGame()) {

            LOGGER.trace("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$

            // Decrease number of remaining tries at the start of each turn
            this.setNbRemainingTries(this.getNbRemainingTries() - 1);

            // Human turn
            List<Integer> humanProposition = this.humanTurn(humanPlayer, mysteryNumberComputer);

            if (humanProposition.equals(mysteryNumberComputer)) {
                this.victory();
                break;
            }

            // Computer turn
            List<Integer> numberComputer = this.computerTurn(computerPlayer, mysteryNumberHuman);

            if (numberComputer.equals(mysteryNumberHuman)) {
                CombineGame.getDisplay().println("L'ordinateur a proposé les valeurs suivantes :"); //$NON-NLS-1$
                for (List<Integer> proposition : computerPlayer.getListNumberProposed()) {
                    CombineGame.getDisplay().print(proposition);
                    CombineGame.getDisplay().print("\n"); //$NON-NLS-1$
                }
                this.defeat(mysteryNumberComputer);
            }
            else if (this.getNbRemainingTries() == 0) {
                this.equality();
            }

            LOGGER.debug("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$

        }

    }
}
