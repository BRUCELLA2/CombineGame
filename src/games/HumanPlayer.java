package games;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import display.Display;

/**
 * <b>This class represents a human player</b>
 * 
 * @author BRUCELLA2
 * @version 1.0.3
 */
public class HumanPlayer extends Player {
	
//***** VARIABLES *****//
	/**
	 * Log4j2 Logger
	 */
	private static final Logger logger = LogManager.getLogger(HumanPlayer.class);
	
//***** CONSTRUCTORS *****//
	
	/**
	 * Constructor of the HumanPlayer class.
	 * 
	 * @param pPlayerName Player's name
	 * @param pDisplay The display to used
	 */
	public HumanPlayer(String pPlayerName, Display pDisplay) {
		super(pPlayerName, pDisplay);
		
		logger.trace("HumanPlayer construction"); //$NON-NLS-1$
	}
	
//***** METHODS *****//
	
	/**
	 * This method asks the user to provide a number via the display.<br>
	 * <br>
	 * This number is retrieved as String and transformed into an ArrayList of integer.  
	 * 
	 * @param pMaxValueDigit The max value digit
	 * 
	 * @return A number in the form of an ArrayList of integer
	 */
	@Override
	public ArrayList<Integer> giveNumber(int pMaxValueDigit) {
		
		logger.trace("Human Give number"); //$NON-NLS-1$
		logger.trace("Max value digit = " + pMaxValueDigit); //$NON-NLS-1$
		
		ArrayList<Integer> number = new ArrayList<>();
		
		//TODO to be factorised ?
		do {
			this.getDisplay().println("Donnez le nombre mystère : "); //$NON-NLS-1$
			number = this.getNumberInput(pMaxValueDigit);
			
			logger.debug("Number Input : " + number); //$NON-NLS-1$
			
			if(number.isEmpty()) {
				this.getDisplay().println("Saisie incorrecte"); //$NON-NLS-1$
			}
		}
		while(number.isEmpty());
		
		return number;
	}
	
	/**
	 * This method asks the user to provide a mystery number (i. e. a number to guess).<br>
	 * <br>
	 * This number obtained as String is transformed into an integer ArrayList.  
	 * 
	 * @param pMaxValueDigit The max value digit
	 * 
	 * @return A number in the form of an ArrayList of integer
	 */
	public ArrayList<Integer> createMysteryNumber(int pMaxValueDigit){
		
		logger.trace("Create Mystery Number"); //$NON-NLS-1$
		logger.trace("Max value digit = " + pMaxValueDigit); //$NON-NLS-1$
		
		ArrayList<Integer> number = new ArrayList<>();
		
		//TODO to be factorised ?
		do {
			this.getDisplay().println("Donnez la combinaison secrète : "); //$NON-NLS-1$
			number = this.getNumberInput(pMaxValueDigit);
			
			logger.debug("Number input : " + number); //$NON-NLS-1$
			
			if(number.isEmpty()) {
				this.getDisplay().println("Saisie incorrecte"); //$NON-NLS-1$
			}
		}while(number.isEmpty());
		
		return number;	
	}
	
}
