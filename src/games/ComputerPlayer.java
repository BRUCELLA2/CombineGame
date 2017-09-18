package games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <b>This class represents a player simulated by the computer.</b><br>
 *
 * @author BRUCELLA2
 * @version 1.0.9
 *
 */
public class ComputerPlayer extends Player {

// ***** VARIABLES *****/

    /**
     * Log4j2 Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(ComputerPlayer.class);

    /**
     * List of numbers proposed by the ComputerPlayer in the form of an ArrayList.<br>
     * <br>
     * The items in the list are ArrayList of integer.
     *
     * @see #getListNumberProposed()
     * @see #setListNumberProposed(List)
     */
    private List<List<Integer>> listNumberProposed = new ArrayList<>();

    /**
     * Lists the maximums for each number constituting the mystery number<br>
     *
     * @see #getMaxValues()
     * @see #setMaxValues(List)
     *
     */
    private List<Integer> maxValues = new ArrayList<>();

    /**
     * Lists the minimums for each number constituting the mystery number<br>
     *
     * @see #getMinValues()
     * @see #setMinValues(List)
     *
     */
    private List<Integer> minValues = new ArrayList<>();

    /**
     * List of possibles combinations for Mastermind game
     *
     * @see #getPoolMastermind()
     * @see #setPoolMastermind(List)
     */
    private List<List<Integer>> poolMastermind = new ArrayList<>();

    /**
     * This map contains the proposition (key) and number of digits in the bad position (value)
     *
     * @see #getResultsBadPos()
     * @see #setResultsBadPos(Map)
     */
    private Map<List<Integer>, Integer> resultsBadPos = new HashMap<>();

    /**
     * This map contains the proposition (key) and number of digits in the right position (value)
     *
     * @see #getResultsGoodPos()
     * @see #setResultsGoodPos(Map)
     */
    private Map<List<Integer>, Integer> resultsGoodPos = new HashMap<>();

    /**
     * List of results obtained by comparing the proposed numbers with the mystery number in More Less game.<br>
     * <br>
     * The items in the list are Strings (usually a result of the comparison).
     *
     * @see #getResultsListMoreLess()
     * @see #setResultsListMoreLess(List)
     */
    private List<String> resultsListMoreLess = new ArrayList<>();


    // ***** CONSTRUCTORS *****/

    /**
     * Constructor of the ComputerPlayer class
     *
     * @param pPlayerName
     *            ComputerPlayer's name
     */
    public ComputerPlayer(final String pPlayerName) {

        super(pPlayerName);

        LOGGER.trace("Computer construction"); //$NON-NLS-1$

    }


    // ***** GETTERS *****/

    /**
     * Returns the list of proposed numbers.<br>
     * <br>
     * The list items are in the form of ArrayList of integer.
     *
     * @return the list of proposed numbers.
     *
     * @see #setListNumberProposed(List)
     * @see #addLastProposition(List)
     */
    public List<List<Integer>> getListNumberProposed() {

        return this.listNumberProposed;
    }


    /**
     * Returns the list of maximum values of the digits constituting the mystery number
     *
     * @return the list of maximum values of the digits constituting the mystery number
     *
     * @see #setMaxValues(List)
     */
    public List<Integer> getMaxValues() {

        return this.maxValues;
    }


    /**
     * Returns the list of minimum values of the digits constituting the mystery number
     *
     * @return the list of minimum values of the digits constituting the mystery number
     *
     * @see #setMinValues(List)
     */
    public List<Integer> getMinValues() {

        return this.minValues;
    }


    /**
     * Returns the list of possibles combinations for Mastermind game
     *
     * @return the list of possibles combinations for Mastermind game
     *
     * @see #setPoolMastermind(List)
     */
    public List<List<Integer>> getPoolMastermind() {

        return this.poolMastermind;
    }


    /**
     * Return a map which contains the proposition (key) and number of digits in the bad position (value)
     *
     * @return a map which contains the proposition (key) and number of digits in the bad position (value)
     *
     * @see #setResultsBadPos(Map)
     */
    public Map<List<Integer>, Integer> getResultsBadPos() {

        return this.resultsBadPos;
    }


    /**
     * Return a map which contains the proposition (key) and number of digits in the right position (value)
     *
     * @return a map which contains the proposition (key) and number of digits in the right position (value)
     *
     * @see #setResultsGoodPos(Map)
     */
    public Map<List<Integer>, Integer> getResultsGoodPos() {

        return this.resultsGoodPos;
    }


    /**
     * Returns the list of proposed results obtained by comparing the proposed and mystery numbers in MoreLess game.<br>
     * <br>
     * The list items are in the form of String.
     *
     * @return the list of proposed results in MoreLess game
     *
     * @see #setListNumberProposed(List)
     * @see #addResultatMoreLess(String)
     */
    public List<String> getResultsListMoreLess() {

        return this.resultsListMoreLess;
    }


    // ***** SETTERS *****/

    /**
     * Allows to define the list of numbers already proposed.<br>
     * <br>
     * To add an item to the list you must use {@link #addLastProposition(List)}
     *
     * @param pListNumberProposed
     *            the list of numbers already proposed
     *
     * @see #getListNumberProposed()
     * @see #addLastProposition(List)
     */
    public void setListNumberProposed(final List<List<Integer>> pListNumberProposed) {

        this.listNumberProposed = pListNumberProposed;
    }


    /**
     * Allows to define the list of maximum values for the digits constituting the mystery number
     *
     * @param pMaxValues
     *            List of maximum values for the digits constituting the mystery number
     *
     * @see #getMaxValues()
     */
    public void setMaxValues(final List<Integer> pMaxValues) {

        this.maxValues = pMaxValues;
    }


    /**
     * Allows to define the list of minimum values for the digits constituting the mystery number
     *
     * @param pMinValues
     *            List of minimum values for the digits constituting the mystery number
     *
     * @see #getMinValues()
     */
    public void setMinValues(final List<Integer> pMinValues) {

        this.minValues = pMinValues;
    }


    /**
     * Allows to define the list of possibles combinations for Mastermind game
     *
     * @param pPoolMastermind
     *            The list of possibles combinations for Mastermind game
     *
     * @see #getPoolMastermind()
     */
    public void setPoolMastermind(final List<List<Integer>> pPoolMastermind) {

        this.poolMastermind = pPoolMastermind;
    }


    /**
     * Allows to define the map of the proposition (key) and number of digits in the bad position (value)
     *
     * @param pResultsBadPos
     *            The map of the proposition (key) and number of digits in the bad position (value)
     *
     * @see #getResultsBadPos()
     */
    public void setResultsBadPos(final Map<List<Integer>, Integer> pResultsBadPos) {

        this.resultsBadPos = pResultsBadPos;
    }


    /**
     * Allows to define the map of the proposition (key) and the number of digits in the right position (value)
     *
     * @param pResultsGoodPos
     *            The map of the proposition (key) and the number of digits in the right position (value)
     *
     * @see #getResultsGoodPos()
     */
    public void setResultsGoodPos(final Map<List<Integer>, Integer> pResultsGoodPos) {

        this.resultsGoodPos = pResultsGoodPos;
    }


    /**
     * Allows to define the list of results obtained by comparing the proposed number with the mystery number in
     * MoreLess game .<br>
     * <br>
     * To add an item to the list you must use {@link #addResultatMoreLess(String)}
     *
     * @param pResultsListMoreLess
     *            The list of results obtained in MoreLess game
     *
     * @see #getResultsListMoreLess()
     * @see #addResultatMoreLess(String)
     */
    public void setResultsListMoreLess(final List<String> pResultsListMoreLess) {

        this.resultsListMoreLess = pResultsListMoreLess;
    }


    // ***** METHODS *****/

    /**
     * Add a proposition and the number of digits in bad position to the map {@link #resultsBadPos}
     *
     * @param pProposition
     *            The number proposed
     * @param pBadPos
     *            The number of digits in bad position
     */
    public void addBadResultMastermind(final List<Integer> pProposition, final int pBadPos) {

        this.resultsBadPos.put(pProposition, Integer.valueOf(pBadPos));
    }


    /**
     * Add a proposition and the number of digits in good position to the map {@link #resultsGoodPos}
     *
     * @param pProposition
     *            The number proposed
     * @param pGoodPos
     *            The number of digits in good position
     */
    public void addGoodResultMastermind(final List<Integer> pProposition, final int pGoodPos) {

        this.resultsGoodPos.put(pProposition, Integer.valueOf(pGoodPos));
    }


    /**
     * This method allows to add the number proposed in the last proposal to the list of results.<br>
     * <br>
     * A proposal is an ArrayList of the integer. This method is used internally by {@link #giveNumber(int)}
     *
     * @param pLastProposition
     *            number proposed in the last proposal
     */
    private void addLastProposition(final List<Integer> pLastProposition) {

        this.getListNumberProposed().add(pLastProposition);
    }


    /**
     * This method allows to add a result to the results list.<br>
     * <br>
     * A result is a string obtained when comparing a proposed number with the mystery number in MoreLess game.
     *
     * @param pResultMoreLess
     *            Result of the comparison between a proposed number and the mystery number in MoreLess game
     */
    public void addResultatMoreLess(final String pResultMoreLess) {

        this.getResultsListMoreLess().add(pResultMoreLess);
    }


    /**
     * This method creates a pool of combinations.<br>
     * <br>
     * The creation need a pool with at min one element. All permutation based on this element(s) will populate the pool
     * given in pattern.<br>
     * Max value for each digit is defined in the config.properties {@link CombineGame#getNbColors()}
     *
     * @param pPool
     *            Initial pool with one element minimum and that will be populate with all the combinations.
     * @param nbDigitCombination
     *            Number of digit constituting a combination
     *
     */
    private void createPool(final List<List<Integer>> pPool, final int nbDigitCombination) {

        LOGGER.trace("CreatePool"); //$NON-NLS-1$
        LOGGER.trace("pPool : " + pPool); //$NON-NLS-1$
        LOGGER.trace("NbDigitCombination = " + nbDigitCombination); //$NON-NLS-1$

        List<List<Integer>> newPool = new ArrayList<>(pPool);
        ListIterator<List<Integer>> liPool = newPool.listIterator();
        pPool.clear();

        while (liPool.hasNext()) {

            List<Integer> numberPool = liPool.next();

            for (int i = 0; i <= CombineGame.getNbColors(); i++) {
                ArrayList<Integer> number = new ArrayList<>(numberPool);
                number.set(nbDigitCombination - 1, Integer.valueOf(i));
                pPool.add(number);

                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("Number combination : " + number); //$NON-NLS-1$
                    LOGGER.trace("Pool : "); //$NON-NLS-1$
                    LOGGER.trace(pPool);
                }
            }

        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("pPool size : " + pPool.size()); //$NON-NLS-1$
        }

        if (nbDigitCombination - 1 > 0) {
            this.createPool(pPool, nbDigitCombination - 1);
        }
    }


    /**
     * This method enables to the ComputerPlayer to provide a number in the form of an ArrayList of integer.<br>
     * <br>
     * This number is added to the list of proposed numbers.<br>
     *
     * @param pMaxValueDigit
     *            The max value digit
     *
     * @return A number in the form of an ArrayList of integer
     */
    @Override
    public List<Integer> giveNumber(final int pMaxValueDigit) {

        LOGGER.trace("Computer Give Number"); //$NON-NLS-1$
        LOGGER.trace("Max Value Digit : " + pMaxValueDigit); //$NON-NLS-1$

        ArrayList<Integer> number = new ArrayList<>();
        List<Integer> lastProposition;

        LOGGER.trace("List Number Proposed : " + this.getListNumberProposed()); //$NON-NLS-1$

        if (!this.getListNumberProposed().isEmpty()) {
            lastProposition = this.getListNumberProposed().get(this.getListNumberProposed().size() - 1);
        }
        else {
            lastProposition = new ArrayList<>();
        }
        LOGGER.trace("Last proposition : " + lastProposition); //$NON-NLS-1$

        for (int i = 0; i < CombineGame.getNbDigitsMystery(); i++) {

            Random r = new Random();

            if (lastProposition.isEmpty()) {
                number.add(Integer.valueOf(r.nextInt(pMaxValueDigit)));

                this.getMaxValues().add(Integer.valueOf(CombineGame.getMaxValueDigit()));
                this.getMinValues().add(Integer.valueOf(0));

                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("Construction of MaxValue for each digit : " + this.getMaxValues()); //$NON-NLS-1$
                    LOGGER.trace("Construction of MinValue for each digit : " + this.getMinValues()); //$NON-NLS-1$
                    LOGGER.trace("Last proposition empty - Number construction : " + number); //$NON-NLS-1$
                }

            }
            else {

                switch (this.getResultsListMoreLess().get(this.getResultsListMoreLess().size() - 1).charAt(i)) {

                    case '+':
                        LOGGER.trace("Case + "); //$NON-NLS-1$
                        this.getMinValues().set(i, lastProposition.get(i));
                        number.add(Integer.valueOf(
                                (this.getMaxValues().get(i).intValue() + 1 - lastProposition.get(i).intValue()) / 2
                                        + lastProposition.get(i).intValue()));
                        if (LOGGER.isTraceEnabled()) {
                            LOGGER.trace("Update min values : " + this.getMinValues()); //$NON-NLS-1$
                            LOGGER.trace("Number construction : " + number); //$NON-NLS-1$
                        }
                        break;
                    case '-':
                        LOGGER.trace("Case - "); //$NON-NLS-1$
                        this.getMaxValues().set(i, lastProposition.get(i));
                        number.add(Integer.valueOf(lastProposition.get(i).intValue()
                                - (lastProposition.get(i).intValue() - (this.getMinValues().get(i).intValue() - 1))
                                        / 2));
                        if (LOGGER.isTraceEnabled()) {
                            LOGGER.trace("Update max values : " + this.getMaxValues()); //$NON-NLS-1$
                            LOGGER.trace("Number construction : " + number); //$NON-NLS-1$
                        }
                        break;
                    case '=':
                        LOGGER.trace("Case = "); //$NON-NLS-1$
                        number.add(lastProposition.get(i));
                        if (LOGGER.isTraceEnabled()) {
                            LOGGER.trace("Number construction : " + number); //$NON-NLS-1$
                        }
                        break;

                    default:
                        break;
                }
            }
        }
        LOGGER.trace("Number to give : " + number); //$NON-NLS-1$
        this.addLastProposition(number);
        return number;
    }


    /**
     * This method enables to the ComputerPlayer to provide a number pattern in the form of an ArrayList of integer.<br>
     * <br>
     * This number is chose in a pool of possible combinations. This pool is updated before the number is chose based on
     * the precedent proposition and result (good position / bad position)
     *
     * @return A number in the form of an ArrayList of integer
     *
     * @see #createPool(List, int)
     * @see #optimizedPool(List)
     */
    public List<Integer> giveNumberPattern() {

        LOGGER.trace("Computer give number pattern"); //$NON-NLS-1$

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Pool at start : " + this.getPoolMastermind()); //$NON-NLS-1$
        }

        // Create a pool of all combinations if the pool is empty
        if (this.getPoolMastermind().isEmpty()) {

            LOGGER.debug("Pool at start empty"); //$NON-NLS-1$

            ArrayList<Integer> patternInit = new ArrayList<>();
            for (int i = 0; i < CombineGame.getNbDigitsMystery(); i++) {
                patternInit.add(Integer.valueOf(0));
            }
            this.getPoolMastermind().add(patternInit);
            LOGGER.trace("Pool init : " + this.getPoolMastermind()); //$NON-NLS-1$
            LOGGER.trace("Pool size after init : " + this.getPoolMastermind().size()); //$NON-NLS-1$
            this.createPool(this.getPoolMastermind(), CombineGame.getNbDigitsMystery());
            LOGGER.trace("Pool size after creation : " + this.getPoolMastermind().size()); //$NON-NLS-1$
        }

        LOGGER.trace("Pool size before optimization : " + this.getPoolMastermind().size()); //$NON-NLS-1$
        this.optimizedPool(this.getPoolMastermind());
        LOGGER.trace("Pool size after optimization : " + this.getPoolMastermind().size()); //$NON-NLS-1$

        Random r = new Random();
        int randomIndex;

        try {
            randomIndex = r.nextInt(this.getPoolMastermind().size() - 1);
        } catch (@SuppressWarnings("unused") IllegalArgumentException e) {
            randomIndex = 0;
        }

        return this.getPoolMastermind().get(randomIndex);
    }


    /**
     * This method optimized the pool of combinations.<br>
     * <br>
     * Based on previous propositions and results, keep only combinations that are compatible with the results
     * previously obtained.
     *
     * @param pPool
     *            The pool of combinations that will be optimized
     */
    private void optimizedPool(final List<List<Integer>> pPool) {

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Optimized pool"); //$NON-NLS-1$
            LOGGER.trace("Pool to optimized : " + pPool); //$NON-NLS-1$
        }

        // TODO Think to add a score to each pool element during the optimization to
        // chose the best proposition

        ListIterator<List<Integer>> liPool;

        Set<Entry<List<Integer>, Integer>> setResultsGoodPos = this.getResultsGoodPos().entrySet();
        Iterator<Entry<List<Integer>, Integer>> liResultsGoodPos = setResultsGoodPos.iterator();

        Set<Entry<List<Integer>, Integer>> setResultsBadPos = this.getResultsBadPos().entrySet();
        Iterator<Entry<List<Integer>, Integer>> liResultsBadPos = setResultsBadPos.iterator();

        while (liResultsGoodPos.hasNext() && liResultsBadPos.hasNext()) {

            Map.Entry<List<Integer>, Integer> mapResultGoodPos = liResultsGoodPos.next();
            Map.Entry<List<Integer>, Integer> mapResultBadPos = liResultsBadPos.next();
            liPool = pPool.listIterator();

            while (liPool.hasNext()) {
                int goodPos = 0;
                int badPos = 0;
                List<Integer> numberPool = liPool.next();

                goodPos = Mastermind.findNbDigitsGoodPos(numberPool, mapResultGoodPos.getKey());
                badPos = Mastermind.findNbDigitInMystery(numberPool, mapResultBadPos.getKey()) - goodPos;

                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("number pool : " + numberPool); //$NON-NLS-1$
                    LOGGER.trace("compare with Result : " + mapResultGoodPos.getKey()); //$NON-NLS-1$
                    LOGGER.trace("goodPos with old result : " + goodPos); //$NON-NLS-1$
                    LOGGER.trace("baddPos with old result : " + badPos); //$NON-NLS-1$
                }

                // New proposition need to have at least same number digits in good position
                // than old propositions have with mystery pattern.
                if (goodPos < mapResultGoodPos.getValue().intValue()) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("goodPos < result nb good pos"); //$NON-NLS-1$
                        LOGGER.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } // New proposition need to have same number of digit in good or bad position
                  // with old propositions than old propositions have with mystery pattern.
                  // If more, it means there is some digit which are not the good one. If less, it
                  // means there is good digits missing
                else if (badPos + goodPos != mapResultGoodPos.getValue().intValue()
                        + mapResultBadPos.getValue().intValue()) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("(good pos + bad pos) != (result good pos + result bad pos)"); //$NON-NLS-1$
                        LOGGER.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } // If goodPos is 0 for the proposition with old propositions and for the old
                  // propositions with mystery pattern,
                  // new proposition need to have at least same number of bad pos than old
                  // propositions have with mystery pattern.
                else if (goodPos == 0 && badPos < mapResultBadPos.getValue().intValue()) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("goodPos = 0 and (badPos < result bad pos"); //$NON-NLS-1$
                        LOGGER.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } // New proposition need to have 0 good pos with old propositions if old
                  // proposition have 0 good position with mystery pattern
                else if (goodPos > 0 && mapResultGoodPos.getValue().intValue() == 0) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("good pos > 0 and result good pos = 0"); //$NON-NLS-1$
                        LOGGER.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } // New proposition need to be different than old propositions
                else if (numberPool.equals(mapResultGoodPos.getKey())) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("number is the number result"); //$NON-NLS-1$
                        LOGGER.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                } // Proposition needs to be totally different (only new digits) if old
                  // proposition have 0 good and bad position with mystery pattern.
                else if (mapResultBadPos.getValue().intValue() == 0 && mapResultGoodPos.getValue().intValue() == 0
                        && goodPos + badPos == CombineGame.getNbDigitsMystery()) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("result bad pos = 0 and result good pos = 0 " //$NON-NLS-1$
                                + "and (bad pos + good pos) = nb mystery digit"); //$NON-NLS-1$
                        LOGGER.trace("remove : " + numberPool); //$NON-NLS-1$
                    }
                    liPool.remove();
                }
            }
        }

        LOGGER.trace("Pool optimized : " + pPool); //$NON-NLS-1$
    }
}
