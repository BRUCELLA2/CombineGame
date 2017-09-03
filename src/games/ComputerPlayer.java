package games;

import java.util.ArrayList;

import display.Display;

/**
 * <b>This class represents a player simulated by the computer.</b><br>
 * 
 * @author BRUCELLA2
 * @version 1.0.2
 *
 */
public class ComputerPlayer extends Player{
	
//***** VARIABLES *****/
	
	/**
	 * List of numbers proposed by the ComputerPlayer in the form of an ArrayList.<br>
	 * <br>
	 * The items in the list are ArrayList of integer.
	 * 
	 * @see #getListNumberProposed()
	 * @see #setListNumberProposed(ArrayList)
	 */
	private ArrayList<ArrayList<Integer>> listNumberProposed = new ArrayList<>();
	
	/**
	 * List of results obtained by comparing the proposed numbers with the mystery number.<br>
	 * <br>
	 * The items in the list are Strings (usually a result of the comparison). 
	 * 
	 * @see #getDisplay()
	 * @see #setDisplay(Display)
	 */
	private ArrayList<String> resultsList = new ArrayList<>();
	
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
	
//***** CONSTRUCTORS *****/
	
	/**
	 * 	Constructor of the ComputerPlayer class
	 * 
	 * @param pPlayerName ComputerPlayer's name
	 * @param pDisplay The display to used
	 */
	//TODO mettre en constante globale le nb de chiffres mystères
	public ComputerPlayer(String pPlayerName, Display pDisplay) {
		
		super(pPlayerName, pDisplay);
		
		for(int i=0; i < CombineGame.NB_DIGITS_MYSTERY; i++) {
			this.getMaxValues().add(CombineGame.MAX_VALUE_DIGIT);
			this.getMinValues().add(0);
		}
	}

//***** GETTERS *****/
	
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
	public ArrayList<ArrayList<Integer>> getListNumberProposed(){
		return this.listNumberProposed;
	}
	
	/**
	 * Returns the list of proposed results obtained by comparing the proposed and mystery numbers.<br>
	 * <br>
	 * The list items are in the form of String.
	 * 
	 * @return the list of proposed results
	 * 
	 * @see #setListNumberProposed(ArrayList)
	 * @see #addResultat(String)
	 */
	public ArrayList<String> getResultsList(){
		return this.resultsList;
	}
	
	/**
	 * Returns the list of maximum values of the digits constituting the mystery number
	 * 
	 * @return the list of maximum values of the digits constituting the mystery number
	 * 
	 * @see #setMaxValues(ArrayList)
	 */
	public ArrayList<Integer> getMaxValues(){
		return this.maxValues;
	}
	
	/**
	 * Returns the list of minimum values of the digits constituting the mystery number
	 * 
	 * @return the list of minimum values of the digits constituting the mystery number
	 * 
	 * @see #setMinValues(ArrayList)
	 */
	public ArrayList<Integer> getMinValues(){
		return this.minValues;
	}
//***** SETTERS *****/
	
	/**
	 * Allows to define the list of numbers already proposed.<br>
	 * <br>
	 * To add an item to the list you must use {@link #addLastProposition(ArrayList)}
	 * 
	 * @param pListNumberProposed the list of numbers already proposed
	 * 
	 * @see #getListNumberProposed()
	 * @see #addLastProposition(ArrayList)
	 */
	public void setListNumberProposed(ArrayList<ArrayList<Integer>> pListNumberProposed) {
		this.listNumberProposed = pListNumberProposed;
	}
	
	/**
	 * Allows to define the list of results obtained by comparing the proposed number with the mystery number.<br>
	 * <br>
	 * To add an item to the list you must use {@link #addResultat(String)}
	 * 
	 * @param pResultsList the list of results obtained
	 * 
	 * @see #getResultsList()
	 * @see #addResultat(String)
	 */
	public void setResultsList(ArrayList<String> pResultsList) {
		this.resultsList = pResultsList;
	}
	
	/**
	 * Allows to define the list of maximum values for the digits constituting the mystery number
	 * 
	 * @param pMaxValues list of maximum values for the digits constituting the mystery number
	 * 
	 * @see #getMaxValues()
	 */
	public void setMaxValues(ArrayList<Integer> pMaxValues) {
		this.maxValues = pMaxValues;
	}
	
	/**
	 * Allows to define the list of minimum values for the digits constituting the mystery number
	 * 
	 * @param pMinValues list of minimum values for the digits constituting the mystery number
	 * 
	 * @see #getMinValues()
	 */
	public void setMinValues(ArrayList<Integer> pMinValues) {
		this.minValues = pMinValues;
	}
	
//***** METHODS *****/
	
	/**
	 * This method enables to the ComputerPlayer to provide a number in the form of an ArrayList of integer.<br>
	 * <br>
	 * This number is added to the list of proposed numbers.<br>
	 * 
	 * 
	 */
	@Override
	public ArrayList<Integer> giveNumber() {
		
		ArrayList<Integer> number = new ArrayList<>();
		ArrayList<Integer> lastProposition;
		if(!this.getListNumberProposed().isEmpty()) {
			lastProposition = this.getListNumberProposed().get(this.getListNumberProposed().size()-1);
		}
		else {
			lastProposition = new ArrayList<>();
		}

		/* VERSION IA nulle
		for(int i=0; i < pNbChiffre; i++) {
			nombre.add(new Integer((int) ((9-0)*Math.random())));
		}
		*/
		
		/* VERSION IA MEDIUM
		for(int i=0; i < pNbChiffre; i++) {
			
			if(derniereProposition.isEmpty()) {
				nombre.add(new Integer((int) ((9-0)*Math.random())));
			}
			else {
				
				switch(this.getListResultats().get(this.getListResultats().size()-1).charAt(i)) {
				
				case '+':
					nombre.add(new Integer((int) ((9-derniereProposition.get(i).intValue())*Math.random())));
					break;
				case '-':
					nombre.add(new Integer((int) ((derniereProposition.get(i).intValue()-0)*Math.random())));
					break;
				case '=':
					nombre.add(new Integer(derniereProposition.get(i).intValue()));
					break;
					
				default:
					break;
				}
			}
		}*/
		
		for(int i=0; i < CombineGame.NB_DIGITS_MYSTERY; i++) {
			
			if(lastProposition.isEmpty()) {
				number.add(new Integer((int) ((CombineGame.MAX_VALUE_DIGIT-0)*Math.random())));
			}
			else {
				
				switch(this.getResultsList().get(this.getResultsList().size()-1).charAt(i)) {
				
				case '+':
					this.getMinValues().set(i,lastProposition.get(i));
					number.add(new Integer((((this.getMaxValues().get(i).intValue()+1)-lastProposition.get(i).intValue())/2) + lastProposition.get(i)));
					break;
				case '-':
					this.getMaxValues().set(i,lastProposition.get(i));
					number.add(new Integer(lastProposition.get(i).intValue() - ((lastProposition.get(i).intValue()-(this.getMinValues().get(i).intValue()-1))/2)));
					break;
				case '=':
					number.add(new Integer(lastProposition.get(i).intValue()));
					break;
					
				default:
					break;
				}
			}
		}
		
		this.addLastProposition(number);
		return number;
	}
	
	/**
	 * This method allows to add a result to the results list.<br>
	 * <br>
	 * A result is a string obtained when comparing a proposed number with the mystery number.
	 * 
	 * @param pResult Result of the comparison between a proposed number and the mystery number
	 */
	public void addResultat(String pResult) {
		
		this.getResultsList().add(pResult);
	}
	
	/**
	 * This method allows to add the number proposed in the last proposal to the list of results.<br>
	 * <br>
	 *  A proposal is an ArrayList of the integer. This method is used internally by {@link #giveNumber()}
	 * 
	 * @param pLastProposition number proposed in the last proposal
	 */
	private void addLastProposition(ArrayList<Integer> pLastProposition) {
		this.getListNumberProposed().add(pLastProposition);
	}
	
}
