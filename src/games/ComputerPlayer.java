package games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import display.Display;

/**
 * <b>This class represents a player simulated by the computer.</b><br>
 *
 * @author BRUCELLA2
 * @version 1.0.7
 *
 */
public class ComputerPlayer extends Player {

    // ***** VARIABLES *****/

    /**
     * List of numbers proposed by the ComputerPlayer in the form of an
     * ArrayList.<br>
     * <br>
     * The items in the list are ArrayList of integer.
     *
     * @see #getListNumberProposed()
     * @see #setListNumberProposed(ArrayList)
     */
    private ArrayList<ArrayList<Integer>> listNumberProposed = new ArrayList<>();

    /**
     * List of results obtained by comparing the proposed numbers with the mystery
     * number in More Less game.<br>
     * <br>
     * The items in the list are Strings (usually a result of the comparison).
     *
     * @see #getDisplay()
     * @see #setDisplay(Display)
     */
    private ArrayList<String> resultsListMoreLess = new ArrayList<>();

    /**
     * Lists the maximums for each number constituting the mystery number<br>
     *
     * @see #getMaxValues()
     * @see #setMaxValues(ArrayList)
     *
     */
    private ArrayList<Integer> maxValues = new ArrayList<>();

    /**
     * Lists the minimums for each number constituting the mystery number<br>
     *
     * @see #getMinValues()
     * @see #setMinValues(ArrayList)
     *
     */
    private ArrayList<Integer> minValues = new ArrayList<>();

    /**
     * This map contains the proposition (key) and number of digits in the right
     * position (value)
     *
     * @see #getResultsGoodPos()
     * @see #setResultsGoodPos(HashMap)
     */
    private HashMap<ArrayList<Integer>, Integer> resultsGoodPos = new HashMap<>();

    /**
     * This map contains the proposition (key) and number of digits in the bad
     * position (value)
     *
     * @see #getResultsBadPos()
     * @see #setResultsBadPos(HashMap)
     */
    private HashMap<ArrayList<Integer>, Integer> resultsBadPos = new HashMap<>();

    /**
     * List of possibles combinations for Mastermind game
     *
     * @see #getPoolMastermind()
     * @see #setPoolMastermind(ArrayList)
     */
    private ArrayList<ArrayList<Integer>> poolMastermind = new ArrayList<>();

    /**
     * Log4j2 Logger
     */
    private static final Logger logger = LogManager.getLogger(HumanPlayer.class);

    // ***** CONSTRUCTORS *****/

    /**
     * Constructor of the ComputerPlayer class
     *
     * @param pPlayerName
     *            ComputerPlayer's name
     * @param pDisplay
     *            The display to used
     */
    // TODO mettre en constante globale le nb de chiffres mystères
    public ComputerPlayer(String pPlayerName) {

        super(pPlayerName);

        logger.trace("Computer construction"); //$NON-NLS-1$

    }

    // ***** GETTERS *****/

    /**
     * Returns the list of proposed numbers.<br>
     * <br>
     * The list items are in the form of ArrayList of integer.
     *
     * @return the list of proposed numbers.
     *
     * @see #setListNumberProposed(ArrayList)
     * @see #addLastProposition(ArrayList)
     */
    public ArrayList<ArrayList<Integer>> getListNumberProposed() {
        return this.listNumberProposed;
    }

    /**
     * Returns the list of proposed results obtained by comparing the proposed and
     * mystery numbers in MoreLess game.<br>
     * <br>
     * The list items are in the form of String.
     *
     * @return the list of proposed results in MoreLess game
     *
     * @see #setListNumberProposed(ArrayList)
     * @see #addResultatMoreLess(String)
     */
    public ArrayList<String> getResultsListMoreLess() {
        return this.resultsListMoreLess;
    }

    /**
     * Returns the list of maximum values of the digits constituting the mystery
     * number
     *
     * @return the list of maximum values of the digits constituting the mystery
     *         number
     *
     * @see #setMaxValues(ArrayList)
     */
    public ArrayList<Integer> getMaxValues() {
        return this.maxValues;
    }

    /**
     * Returns the list of minimum values of the digits constituting the mystery
     * number
     *
     * @return the list of minimum values of the digits constituting the mystery
     *         number
     *
     * @see #setMinValues(ArrayList)
     */
    public ArrayList<Integer> getMinValues() {
        return this.minValues;
    }

    /**
     * Return a map which contains the proposition (key) and number of digits in the
     * right position (value)
     *
     * @return a map which contains the proposition (key) and number of digits in
     *         the right position (value)
     *
     * @see #setResultsGoodPos(HashMap)
     */
    public Map<ArrayList<Integer>, Integer> getResultsGoodPos() {
        return this.resultsGoodPos;
    }

    /**
     * Return a map which contains the proposition (key) and number of digits in the
     * bad position (value)
     *
     * @return a map which contains the proposition (key) and number of digits in
     *         the bad position (value)
     *
     * @see #setResultsBadPos(HashMap)
     */
    public Map<ArrayList<Integer>, Integer> getResultsBadPos() {
        return this.resultsBadPos;
    }

    /**
     * Returns the list of possibles combinations for Mastermind game
     *
     * @return the list of possibles combinations for Mastermind game
     *
     * @see #setPoolMastermind(ArrayList)
     */
    public ArrayList<ArrayList<Integer>> getPoolMastermind() {
        return this.poolMastermind;
    }

    // ***** SETTERS *****/

    /**
     * Allows to define the list of numbers already proposed.<br>
     * <br>
     * To add an item to the list you must use
     * {@link #addLastProposition(ArrayList)}
     *
     * @param pListNumberProposed
     *            the list of numbers already proposed
     *
     * @see #getListNumberProposed()
     * @see #addLastProposition(ArrayList)
     */
    public void setListNumberProposed(ArrayList<ArrayList<Integer>> pListNumberProposed) {
        this.listNumberProposed = pListNumberProposed;
    }

    /**
     * Allows to define the list of results obtained by comparing the proposed
     * number with the mystery number in MoreLess game .<br>
     * <br>
     * To add an item to the list you must use {@link #addResultatMoreLess(String)}
     *
     * @param pResultsListMoreLess
     *            The list of results obtained in MoreLess game
     *
     * @see #getResultsListMoreLess()
     * @see #addResultatMoreLess(String)
     */
    public void setResultsListMoreLess(ArrayList<String> pResultsListMoreLess) {
        this.resultsListMoreLess = pResultsListMoreLess;
    }

    /**
     * Allows to define the list of maximum values for the digits constituting the
     * mystery number
     *
     * @param pMaxValues
     *            List of maximum values for the digits constituting the mystery
     *            number
     *
     * @see #getMaxValues()
     */
    public void setMaxValues(ArrayList<Integer> pMaxValues) {
        this.maxValues = pMaxValues;
    }

    /**
     * Allows to define the list of minimum values for the digits constituting the
     * mystery number
     *
     * @param pMinValues
     *            List of minimum values for the digits constituting the mystery
     *            number
     *
     * @see #getMinValues()
     */
    public void setMinValues(ArrayList<Integer> pMinValues) {
        this.minValues = pMinValues;
    }

    /**
     * Allows to define the map of the proposition (key) and the number of digits in
     * the right position (value)
     *
     * @param pResultsGoodPos
     *            The map of the proposition (key) and the number of digits in the
     *            right position (value)
     *
     * @see #getResultsGoodPos()
     */
    public void setResultsGoodPos(HashMap<ArrayList<Integer>, Integer> pResultsGoodPos) {
        this.resultsGoodPos = pResultsGoodPos;
    }

    /**
     * Allows to define the map of the proposition (key) and number of digits in the
     * bad position (value)
     *
     * @param pResultsBadPos
     *            The map of the proposition (key) and number of digits in the bad
     *            position (value)
     *
     * @see #getResultsBadPos()
     */
    public void setResultsBadPos(HashMap<ArrayList<Integer>, Integer> pResultsBadPos) {
        this.resultsBadPos = pResultsBadPos;
    }

    /**
     * Allows to define the list of possibles combinations for Mastermind game
     *
     * @param pPoolMastermind
     *            The list of possibles combinations for Mastermind game
     *
     * @see #getPoolMastermind()
     */
    public void setPoolMastermind(ArrayList<ArrayList<Integer>> pPoolMastermind) {
        this.poolMastermind = pPoolMastermind;
    }

    // ***** METHODS *****/

    /**
     * This method enables to the ComputerPlayer to provide a number in the form of
     * an ArrayList of integer.<br>
     * <br>
     * This number is added to the list of proposed numbers.<br>
     *
     * @param pMaxValueDigit
     *            The max value digit
     *
     * @return A number in the form of an ArrayList of integer
     */
    @Override
    public ArrayList<Integer> giveNumber(int pMaxValueDigit) {

        logger.trace("Computer Give Number"); //$NON-NLS-1$
        logger.trace("Max Value Digit : " + pMaxValueDigit); //$NON-NLS-1$

        ArrayList<Integer> number = new ArrayList<>();
        ArrayList<Integer> lastProposition;

        logger.trace("List Number Proposed : " + this.getListNumberProposed()); //$NON-NLS-1$

        if (!this.getListNumberProposed().isEmpty()) {
            lastProposition = this.getListNumberProposed().get(this.getListNumberProposed().size() - 1);
        } else {
            lastProposition = new ArrayList<>();
        }
        logger.trace("Last proposition : " + lastProposition); //$NON-NLS-1$

        /*
         * VERSION IA LOW for(int i=0; i < pNbChiffre; i++) { nombre.add(new
         * Integer((int) ((9-0)*Math.random()))); }
         */

        /*
         * VERSION IA MEDIUM for(int i=0; i < pNbChiffre; i++) {
         *
         * if(derniereProposition.isEmpty()) { nombre.add(new Integer((int)
         * ((9-0)*Math.random()))); } else {
         *
         * switch(this.getListResultats().get(this.getListResultats().size()-1).charAt(i
         * )) {
         *
         * case '+': nombre.add(new Integer((int)
         * ((9-derniereProposition.get(i).intValue())*Math.random()))); break; case '-':
         * nombre.add(new Integer((int)
         * ((derniereProposition.get(i).intValue()-0)*Math.random()))); break; case '=':
         * nombre.add(new Integer(derniereProposition.get(i).intValue())); break;
         *
         * default: break; } } }
         */

        for (int i = 0; i < CombineGame.NB_DIGITS_MYSTERY; i++) {

            if (lastProposition.isEmpty()) {
                number.add(new Integer((int) ((pMaxValueDigit - 0) * Math.random())));
                this.getMaxValues().add(new Integer(CombineGame.MAX_VALUE_DIGIT));
                this.getMinValues().add(new Integer(0));

                if (logger.isTraceEnabled()) {
                    logger.trace("Construction of MaxValue for each digit : " + this.getMaxValues()); //$NON-NLS-1$
                    logger.trace("Construction of MinValue for each digit : " + this.getMinValues()); //$NON-NLS-1$
                    logger.trace("Last proposition empty - Number construction : " + number); //$NON-NLS-1$
                }

            } else {

                switch (this.getResultsListMoreLess().get(this.getResultsListMoreLess().size() - 1).charAt(i)) {

                case '+':
                    logger.trace("Case + "); //$NON-NLS-1$
                    this.getMinValues().set(i, lastProposition.get(i));
                    number.add(new Integer(
                            (this.getMaxValues().get(i).intValue() + 1 - lastProposition.get(i).intValue()) / 2
                                    + lastProposition.get(i)));
                    if (logger.isTraceEnabled()) {
                        logger.trace("Update min values : " + this.getMinValues()); //$NON-NLS-1$
                        logger.trace("Number construction : " + number); //$NON-NLS-1$
                    }
                    break;
                case '-':
                    logger.trace("Case - "); //$NON-NLS-1$
                    this.getMaxValues().set(i, lastProposition.get(i));
                    number.add(new Integer(lastProposition.get(i).intValue()
                            - (lastProposition.get(i).intValue() - (this.getMinValues().get(i).intValue() - 1)) / 2));
                    if (logger.isTraceEnabled()) {
                        logger.trace("Update max values : " + this.getMaxValues()); //$NON-NLS-1$
                        logger.trace("Number construction : " + number); //$NON-NLS-1$
                    }
                    break;
                case '=':
                    logger.trace("Case = "); //$NON-NLS-1$
                    number.add(new Integer(lastProposition.get(i).intValue()));
                    if (logger.isTraceEnabled()) {
                        logger.trace("Number construction : " + number); //$NON-NLS-1$
                    }
                    break;

                default:
                    break;
                }
            }
        }
        logger.debug("Number to give : " + number); //$NON-NLS-1$
        this.addLastProposition(number);
        return number;
    }

    /**
     * This method allows to add a result to the results list.<br>
     * <br>
     * A result is a string obtained when comparing a proposed number with the
     * mystery number in MoreLess game.
     *
     * @param pResultMoreLess
     *            Result of the comparison between a proposed number and the mystery
     *            number in MoreLess game
     */
    public void addResultatMoreLess(String pResultMoreLess) {

        this.getResultsListMoreLess().add(pResultMoreLess);
    }

    /**
     * This method allows to add the number proposed in the last proposal to the
     * list of results.<br>
     * <br>
     * A proposal is an ArrayList of the integer. This method is used internally by
     * {@link #giveNumber(int)}
     *
     * @param pLastProposition
     *            number proposed in the last proposal
     */
    private void addLastProposition(ArrayList<Integer> pLastProposition) {

        this.getListNumberProposed().add(pLastProposition);
    }

    /**
     * This method enables to the ComputerPlayer to provide a number pattern in the
     * form of an ArrayList of integer.<br>
     * <br>
     * This number is chose in a pool of possible combinations. This pool is updated
     * before the number is chose based on the precedent proposition and result
     * (good position / bad position)
     *
     * @return A number in the form of an ArrayList of integer
     *
     * @see #createPool(ArrayList, int)
     * @see #optimizedPool(ArrayList)
     */
    public ArrayList<Integer> giveNumberPattern() {

        logger.trace("Computer give number pattern"); //$NON-NLS-1$

        if (logger.isTraceEnabled()) {
            logger.trace("Pool at start : " + this.getPoolMastermind()); //$NON-NLS-1$
        }

        // Create a pool of all combinations if the pool is empty
        if (this.getPoolMastermind().isEmpty()) {

            logger.debug("Pool at start empty"); //$NON-NLS-1$

            ArrayList<Integer> patternInit = new ArrayList<>();
            for (int i = 0; i < CombineGame.NB_DIGITS_MYSTERY; i++) {
                patternInit.add(0);
            }
            this.getPoolMastermind().add(patternInit);
            logger.trace("Pool init : " + this.getPoolMastermind()); //$NON-NLS-1$
            logger.debug("Pool size after init : " + this.getPoolMastermind().size()); //$NON-NLS-1$
            this.createPool(this.getPoolMastermind(), CombineGame.NB_DIGITS_MYSTERY);
            logger.debug("Pool size after creation : " + this.getPoolMastermind().size()); //$NON-NLS-1$
        }

        logger.debug("Pool size before optimization : " + this.getPoolMastermind().size()); //$NON-NLS-1$
        this.optimizedPool(this.getPoolMastermind());
        logger.debug("Pool size after optimization : " + this.getPoolMastermind().size()); //$NON-NLS-1$

        int randomIndex = (int) ((this.getPoolMastermind().size() - 1 - 0) * Math.random());

        return this.getPoolMastermind().get(randomIndex);
    }

    /**
     * This method creates a pool of combinations.<br>
     * <br>
     * The creation need a pool with at min one element. All permutation based on
     * this element(s) will populate the pool given in pattern.<br>
     * Max value for each digit is defined in the config.properties
     * {@link CombineGame#NB_COLORS}
     *
     * @param pPool
     *            Initial pool with one element minimum and that will be populate
     *            with all the combinations.
     * @param nbDigitCombination
     *            Number of digit constituting a combination
     *
     */
    private void createPool(ArrayList<ArrayList<Integer>> pPool, int nbDigitCombination) {

        logger.trace("CreatePool"); //$NON-NLS-1$
        logger.trace("pPool : " + pPool); //$NON-NLS-1$
        logger.trace("NbDigitCombination = " + nbDigitCombination); //$NON-NLS-1$

        ArrayList<ArrayList<Integer>> newPool = new ArrayList<>(pPool);
        ListIterator<ArrayList<Integer>> liPool = newPool.listIterator();
        pPool.clear();

        while (liPool.hasNext()) {

            ArrayList<Integer> numberPool = liPool.next();

            for (int i = 0; i <= CombineGame.NB_COLORS; i++) {
                ArrayList<Integer> number = new ArrayList<>(numberPool);
                number.set(nbDigitCombination - 1, i);
                pPool.add(number);

                if (logger.isTraceEnabled()) {
                    logger.trace("Number combination : " + number); //$NON-NLS-1$
                    logger.trace("Pool : "); //$NON-NLS-1$
                    logger.trace(pPool);
                }
            }

        }
        if (logger.isTraceEnabled()) {
            logger.trace("pPool size : " + pPool.size()); //$NON-NLS-1$
        }

        if (nbDigitCombination - 1 > 0) {
            this.createPool(pPool, nbDigitCombination - 1);
        }
    }

    /**
     * This method optimized the pool of combinations.<br>
     * <br>
     * Based on previous propositions and results, keep only combinations that are
     * compatible with the results previously obtained.
     *
     * @param pPool
     *            The pool of combinations that will be optimized
     */
    public void optimizedPool(ArrayList<ArrayList<Integer>> pPool) {

        if (logger.isTraceEnabled()) {
            logger.trace("Optimized pool"); //$NON-NLS-1$
            logger.trace("Pool to optimized : " + pPool); //$NON-NLS-1$
        }

        // TODO Think to add a score to each pool element during the optimization to
        // chose the best proposition

        ListIterator<ArrayList<Integer>> liPool = pPool.listIterator();

        Set<Entry<ArrayList<Integer>, Integer>> setResultsGoodPos = this.getResultsGoodPos().entrySet();
        Iterator<Entry<ArrayList<Integer>, Integer>> liResultsGoodPos = setResultsGoodPos.iterator();

        Set<Entry<ArrayList<Integer>, Integer>> setResultsBadPos = this.getResultsBadPos().entrySet();
        Iterator<Entry<ArrayList<Integer>, Integer>> liResultsBadPos = setResultsBadPos.iterator();

        while (liResultsGoodPos.hasNext() && liResultsBadPos.hasNext()) {

            Map.Entry<ArrayList<Integer>, Integer> mapResultGoodPos = liResultsGoodPos.next();
            Map.Entry<ArrayList<Integer>, Integer> mapResultBadPos = liResultsBadPos.next();
            liPool = pPool.listIterator();

            while (liPool.hasNext()) {
                int goodPos = 0;
                int badPos = 0;
                ArrayList<Integer> numberPool = liPool.next();

                goodPos = Mastermind.findNbDigitsGoodPos(numberPool, mapResultGoodPos.getKey());
                badPos = Mastermind.findNbDigitInMystery(numberPool, mapResultBadPos.getKey()) - goodPos;

                if (logger.isTraceEnabled()) {
                    logger.trace("number pool : " + numberPool); //$NON-NLS-1$
                    logger.trace("compare with Result : " + mapResultGoodPos.getKey()); //$NON-NLS-1$
                    logger.trace("goodPos with old result : " + goodPos); //$NON-NLS-1$
                    logger.trace("baddPos with old result : " + badPos); //$NON-NLS-1$
                }

                // TODO add some comments to give more details for each remove
                if (goodPos < mapResultGoodPos.getValue().intValue()) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("goodPos < result nb good pos"); //$NON-NLS-1$
                        logger.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } else if (badPos + goodPos != mapResultGoodPos.getValue().intValue()
                        + mapResultBadPos.getValue().intValue()) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("(good pos + bad pos) != (result good pos + result bad pos)"); //$NON-NLS-1$
                        logger.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } else if (goodPos == 0 && badPos < mapResultBadPos.getValue().intValue()) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("goodPos = 0 and (badPos < result bad pos"); //$NON-NLS-1$
                        logger.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } else if (goodPos > 0 && mapResultGoodPos.getValue().intValue() == 0) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("good pos > 0 and result good pos = 0"); //$NON-NLS-1$
                        logger.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } else if (numberPool.equals(mapResultGoodPos.getKey())) {
                    if (logger.isTraceEnabled()) {
                        logger.trace("number is the number result"); //$NON-NLS-1$
                        logger.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } else if (mapResultBadPos.getValue().intValue() == 0 && mapResultGoodPos.getValue().intValue() == 0
                        && goodPos + badPos == CombineGame.NB_DIGITS_MYSTERY) {
                    if (logger.isTraceEnabled()) {
                        logger.trace(
                                "result bad pos = 0 and result good pos = 0 and (bad pos + good pos) = nb mystery digit"); //$NON-NLS-1$
                        logger.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                }
            }
        }

        logger.trace("Pool optimized : " + pPool); //$NON-NLS-1$
    }

    /**
     * Add a proposition and the number of digits in good position to the map
     * {@link #resultsGoodPos}
     *
     * @param pProposition
     *            The number proposed
     * @param pGoodPos
     *            The number of digits in good position
     */
    public void addGoodResultMastermind(ArrayList<Integer> pProposition, int pGoodPos) {
        this.resultsGoodPos.put(pProposition, new Integer(pGoodPos));
    }

    /**
     * Add a proposition and the number of digits in bad position to the map
     * {@link #resultsBadPos}
     *
     * @param pProposition
     *            The number proposed
     * @param pBadPos
     *            The number of digits in bad position
     */
    public void addBadResultMastermind(ArrayList<Integer> pProposition, int pBadPos) {
        this.resultsBadPos.put(pProposition, new Integer(pBadPos));
    }
}
