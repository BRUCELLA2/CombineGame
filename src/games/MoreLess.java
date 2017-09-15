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
 * Each digit composing the number is compared to each digit composing the
 * proposed number.<br>
 * The result of the comparison is a sequence of signs either + or - or =
 * depending on the result.<br>
 * The player can make a limited number of tries to discover the mystery
 * number.<br>
 * <br>
 * The MoreLess class represents the MoreLess game. The methods in this class do
 * not perform any direct display<br>
 * The elements that must be displayed are sent to the Display object, which
 * will then adjust the graphical aspects.<br>
 *
 *
 * @author BRUCELLA2
 * @version 1.0.8
 *
 */
public class MoreLess extends Game {

    /**
     * Log4j2 Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(MoreLess.class);

    /**
     * Constructor of the MoreLess class.<br>
     * <br>
     * The constructor initializes the various variables and starts the game with
     * the desired mode.
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
        LOGGER.trace("MoreLess Construction"); //$NON-NLS-1$
    }

    /**
     * This method allows you to compare the number proposed as a parameter with the
     * mystery number.<br>
     * <br>
     * The comparison is done digit by digit.<br>
     * The result of the comparison is returned in the form of a string of
     * characters indicating for each digit the result of the comparison: <br>
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
                + " to mysteryNumber : " + pMysteryNumber); //$NON-NLS-1$ //$NON-NLS-2$

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
     * This method executes the game with the Challenger mode.<br>
     * <br>
     * In this mode, the player must find the mystery number in a maximum number of
     * tries.<br>
     * If the player finds the right combination, the game stops and displays the
     * victory message.<br>
     * If the player does not find the right combination, the game stops and
     * displays the defeat message.
     *
     * @see #mysteryNumberGeneration(int)
     * @see #victory()
     * @see #defeat(List)
     */
    @Override
    public void playChallenger() {

        LOGGER.trace("Start Challenger mode"); //$NON-NLS-1$

        HumanPlayer humanPlayer = new HumanPlayer("Player"); //$NON-NLS-1$
        List<Integer> mysteryNumber = this.mysteryNumberGeneration(CombineGame.getMaxValueDigit());
        if (CombineGame.isDeveloperMode()) {
            CombineGame.getDisplay().print("Le nombre mystère généré est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumber);
        }

        LOGGER.debug("Mystery Number = " + mysteryNumber); //$NON-NLS-1$

        while (!this.isEndGame()) {

            LOGGER.trace("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$

            List<Integer> number = humanPlayer.giveNumber(CombineGame.getMaxValueDigit());
            String comparison = this.compareNumber(number, mysteryNumber);
            String result = " -> Réponse : " + comparison; //$NON-NLS-1$

            CombineGame.getDisplay().print("Proposition : "); //$NON-NLS-1$
            CombineGame.getDisplay().print(number);
            CombineGame.getDisplay().println(result);

            if (number.equals(mysteryNumber)) {
                this.victory();
            }

            if (!number.equals(mysteryNumber) && this.getNbRemainingTries() == 1) {
                this.defeat(mysteryNumber);
            }

            this.setNbRemainingTries(this.getNbRemainingTries() - 1);

            LOGGER.debug("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
        }
    }

    /**
     * This method executes the game with the defender mode.<br>
     * <br>
     * In this mode, the player gives the mystery number and the computer has to
     * discover it.<br>
     * Each time the computer makes a proposal, the result is displayed.<br>
     * The human player must press "enter" to trigger the next computer proposal.
     *
     * If the computer finds the right combination, the game stops and displays the
     * defeat message.<br>
     * If the computer does not find the right combination, the game stops and
     * displays the victory message.
     *
     */
    @Override
    public void playDefender() {

        LOGGER.trace("Start Defender mode"); //$NON-NLS-1$

        HumanPlayer humanPlayer = new HumanPlayer("Player"); //$NON-NLS-1$
        ComputerPlayer computerPlayer = new ComputerPlayer("Ordinateur"); //$NON-NLS-1$
        List<Integer> mysteryNumber = humanPlayer.createMysteryNumber(CombineGame.getMaxValueDigit());

        LOGGER.debug("Mystery Number = " + mysteryNumber); //$NON-NLS-1$

        // TODO warning a prendre en compte
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);

        while (!this.isEndGame()) {

            LOGGER.trace("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$

            List<Integer> number = computerPlayer.giveNumber(CombineGame.getMaxValueDigit());
            String comparison = this.compareNumber(number, mysteryNumber);
            computerPlayer.addResultatMoreLess(comparison);
            String resultat = " -> Réponse : " + comparison; //$NON-NLS-1$

            CombineGame.getDisplay().print(computerPlayer.getPlayerName() + " propose : "); //$NON-NLS-1$
            CombineGame.getDisplay().print(number);
            CombineGame.getDisplay().print(resultat);
            CombineGame.getDisplay().print("   (Appuyez sur entrée pour continuer)"); //$NON-NLS-1$
            scan.nextLine();

            if (number.equals(mysteryNumber)) {
                this.defeat(mysteryNumber);
            }

            if (!number.equals(mysteryNumber) && this.getNbRemainingTries() == 1) {
                this.victory();
            }

            this.setNbRemainingTries(this.getNbRemainingTries() - 1);

            LOGGER.debug("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
        }
    }

    /**
     * This method executes the game in duel mode<br>
     * <br>
     * In this mode, the player and computer give a mystery number<br>
     * In each turn, player and computer make proposals to find the mystery number
     * create either by computer or player.<br>
     * If player find the mystery number before the computer, player wins.<br>
     * If computer find the mystery number before the player, player loose. <br>
     * If computer and player don't find the other mystery number, it's an
     * equality.<br>
     */
    @Override
    public void playDuel() {

        LOGGER.trace("Start Duel mode"); //$NON-NLS-1$

        HumanPlayer humanPlayer = new HumanPlayer("Player"); //$NON-NLS-1$
        ComputerPlayer computerPlayer = new ComputerPlayer("Ordinateur"); //$NON-NLS-1$

        List<Integer> mysteryNumberComputer = this.mysteryNumberGeneration(CombineGame.getMaxValueDigit());
        List<Integer> mysteryNumberHuman = humanPlayer.createMysteryNumber(CombineGame.getMaxValueDigit());

        LOGGER.debug("Mystery Number computer = " + mysteryNumberComputer); //$NON-NLS-1$
        LOGGER.debug("Mystery Number human = " + mysteryNumberHuman); //$NON-NLS-1$

        CombineGame.getDisplay().println("Les nombres mystères ont été créés. Que le meilleur gagne"); //$NON-NLS-1$

        if (CombineGame.isDeveloperMode()) {
            CombineGame.getDisplay().print("Le nombre mystère généré par l'ordinateur est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumberComputer);
            CombineGame.getDisplay().print("Le nombre mystère généré par le jouer est : "); //$NON-NLS-1$
            CombineGame.getDisplay().println(mysteryNumberHuman);
        }

        while (!this.isEndGame()) {

            LOGGER.trace("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$

            // Human turn
            List<Integer> numberHuman = humanPlayer.giveNumber(CombineGame.getMaxValueDigit());

            LOGGER.debug("Number proposed  by player: " + numberHuman); //$NON-NLS-1$

            String compHumanNb = this.compareNumber(numberHuman, mysteryNumberComputer);
            String resultatHuman = " -> Réponse : " + compHumanNb; //$NON-NLS-1$

            CombineGame.getDisplay().print("Votre proposition : "); //$NON-NLS-1$
            CombineGame.getDisplay().print(numberHuman);
            CombineGame.getDisplay().println(resultatHuman);

            if (numberHuman.equals(mysteryNumberComputer)) {
                this.victory();
            }

            // Computer turn
            if (!this.isEndGame()) {
                List<Integer> numberComputer = computerPlayer.giveNumber(CombineGame.getMaxValueDigit());

                LOGGER.debug("Number proposed by computer: " + numberComputer); //$NON-NLS-1$

                String compComputerNb = this.compareNumber(numberComputer, mysteryNumberHuman);
                computerPlayer.addResultatMoreLess(compComputerNb);
                String resultatComputer = " -> Réponse : " + compComputerNb; //$NON-NLS-1$

                CombineGame.getDisplay().print("L'ordinateur a proposé un nombre. "); //$NON-NLS-1$
                CombineGame.getDisplay().println(resultatComputer);

                if (numberComputer.equals(mysteryNumberHuman)) {
                    CombineGame.getDisplay().println("L'ordinateur a proposé les valeurs suivantes :"); //$NON-NLS-1$
                    for (List<Integer> proposition : computerPlayer.getListNumberProposed()) {
                        CombineGame.getDisplay().print(proposition);
                        CombineGame.getDisplay().print("\n"); //$NON-NLS-1$
                    }

                    this.defeat(mysteryNumberComputer);
                }

                if (!numberHuman.equals(mysteryNumberComputer) && !numberComputer.equals(mysteryNumberHuman)
                        && this.getNbRemainingTries() == 1) {
                    this.equality();
                }
                this.setNbRemainingTries(this.getNbRemainingTries() - 1);

                LOGGER.debug("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
            }
        }

    }

}
