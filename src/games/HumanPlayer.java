package games;

import java.util.ArrayList;

import display.Display;

/**
 * <b>This class represents a human player</b>
 * 
 * @author BRUCELLA2
 * @version 1.0
 */
public class HumanPlayer extends Player {
	
//***** CONSTRUCTORS *****//
	
	/**
	 * Constructor of the HumanPlayer class.
	 * 
	 * @param pPlayerName Player's name
	 * @param pDisplay The display to used
	 */
	public HumanPlayer(String pPlayerName, Display pDisplay) {
		super(pPlayerName, pDisplay);
	}
	
//***** METHODS *****//
	
	/**
	 * This method asks the user to provide a number via the display.<br>
	 * <br>
	 * This number is retrieved as String and transformed into an ArrayList of integer.  
	 * 
	 * @param pDigitsNumber Number of digits constituting the number returned
	 * @return A number in the form of an ArrayList of integer
	 */
	@Override
	public ArrayList<Integer> giveNumber(int pDigitsNumber) {
		
		ArrayList<Integer> number = new ArrayList<>();
		
		do {
			this.getDisplay().println("Donnez le nombre mystère : "); //$NON-NLS-1$
			number = this.getNumberInput(pDigitsNumber);
		}
		while(number.isEmpty());
		
		return number;
	}
	
	/**
	 * This method asks the user to provide a mystery number (i. e. a number to guess).<br>
	 * <br>
	 * This number obtained as String is transformed into an integer ArrayList.  
	 * 
	 * @param pDigitsNumber Number of digits constituting the number returned
	 * @return A number in the form of an ArrayList of integer
	 */
	public ArrayList<Integer> createMysteryNumber(int pDigitsNumber){
		
		ArrayList<Integer> number = new ArrayList<>();
		
		do {
			this.getDisplay().println("Donnez le nombre mystère : "); //$NON-NLS-1$
			number = this.getNumberInput(pDigitsNumber);
		}while(number.isEmpty());
		
		return number;	
	}
	
}
