package games;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import display.Display;
import games.constants.GameModes;

/**
 * <b>Mastermind is a game where you have to guess a pattern of color in good order.</b><br>
 * <br>
 * In this version, there is no color, only number. The game therefore consists in discover the pattern of number in good order.<br>
 * Each digit composing the mystery pattern is compared to each digit composing the pattern proposed.<br>
 * The player can make a limited number of tries to discover the mystery pattern.<br>
 * <br>
 * Mastermind class represents the Mastermind game. The methods in this class do not perform any direct display<br>
 * The elements that must be displayed are sent to the Display object, wich will then adjust the graphical aspects.<br>
 * 
 * @author BRUCELLA2
 * @version 1.0.2
 */
public class Mastermind extends Game{
	
//***** VARIABLES *****//
	/**
	 * Number of digits/colors in good position regarding the pattern.
	 * 
	 * @see #getGoodPos()
	 * @see #setGoodPos(int)
	 */
	private int goodPos;
	
	/**
	 * Number of digits/colors in bad position regarding the pattern.
	 * 
	 * @see #getBadPos()
	 * @see #setBadPos(int)
	 */
	private int badPos;
	
	
	/**
	 * Log4j2 Logger
	 */
	private static final Logger logger = LogManager.getLogger(Mastermind.class);
	
//***** CONSTRUCTORS *****//
	/**
	 * Constructor of the Mastermind class.<br>
	 * <br>
	 * The constructor initializes the various variables and starts the game with the desired mode.
	 * 
	 * @param pGameMode game mode to be used
	 * @param pDisplay display that will be used to make game displays
	 * 
	 * @see #playChallenger()
	 * @see #playDefender()
	 * @see #playDuel()
	 * 
	 */
	protected Mastermind(GameModes pGameMode, Display pDisplay) {
		
		super(pGameMode, pDisplay);
		
		logger.trace("Mastermind Construction"); //$NON-NLS-1$

	}

//***** GETTERS *****//
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
	
	
//***** SETTERS *****//
	
	/**
	 * Allows to define the number of digits/colors in good position regarding the pattern.
	 * 
	 * @param pGoodPos number of digits/colors in good position regarding the pattern.
	 */
	public void setGoodPos(int pGoodPos) {
		this.goodPos = pGoodPos;
	}
	
	/**
	 * Allows to define the number of digits/colors in bad position regarding the pattern.
	 * 
	 * @param pBadPos number of digits/colors in bad position regarding the pattern.
	 */
	public void setBadPos(int pBadPos) {
		this.badPos = pBadPos;
	}
	
//***** METHODS *****//
	/**
	 * This method allows to compare the number proposed as a parameter with the mystery pattern.<br>
	 * <br>
	 * The comparison is done digit by digit.<br>
	 * The first comparison search for digit in good position. After, there is a second comparison which search for digit present in the pattern.<br>
	 * Number of digits in good and bad position is returned in the form of a String.
	 * 
	 * @param pNumberToCompare The number to be compared to the mystery number. 
	 * @return The result of the comparison in the form of a String
	 * 
	 * @see Mastermind#findNbDigitInMystery(ArrayList, ArrayList)
	 * @see Mastermind#findNbDigitsGoodPos(ArrayList, ArrayList) 
	 */
	@Override
	public String compareNumber(ArrayList<Integer> pNumberToCompare, ArrayList<Integer> pMysteryNumber) {
		
		logger.trace("Start compare number"); //$NON-NLS-1$
		logger.trace("Number to compare : " + pNumberToCompare); //$NON-NLS-1$
		logger.trace("Mystery number : " + pMysteryNumber); //$NON-NLS-1$
		
		String result = ""; //$NON-NLS-1$
		
		int nbDigitInMystery = Mastermind.findNbDigitInMystery(pNumberToCompare, pMysteryNumber);
		this.setGoodPos(Mastermind.findNbDigitsGoodPos(pNumberToCompare, pMysteryNumber));
		this.setBadPos(nbDigitInMystery - this.getGoodPos());
		
		logger.debug("Nb same digit in mystery : " + nbDigitInMystery); //$NON-NLS-1$
		logger.debug("Nb digit in good position : " + this.getGoodPos()); //$NON-NLS-1$
		logger.debug("Nb digit in bad position : " + this.getBadPos()); //$NON-NLS-1$
		
		result = this.getBadPos() + " présent(s), " + this.getGoodPos() + " bien placé(s)"; //$NON-NLS-1$ //$NON-NLS-2$
		
		logger.debug("result comparison : " + result); //$NON-NLS-1$
		return result;
	}
	
	/**
	 * This methods search and count the number of digit present in the mystery pattern.<br>
	 * 
	 * @param pNumberToCompare The number to be compared to the mystery number.
	 * @param pMysteryNumber The mystery pattern to discover.
	 * 
	 * @return the number of digit present in the mystery pattern
	 */
	public static int findNbDigitInMystery(ArrayList<Integer> pNumberToCompare, ArrayList<Integer> pMysteryNumber) {
		
		logger.trace("Start search nb same digit in mystery"); //$NON-NLS-1$
		logger.trace("Number to compare : " + pNumberToCompare); //$NON-NLS-1$
		logger.trace("Mystery number : " + pMysteryNumber); //$NON-NLS-1$
		
		int NbDigitsInMystery = 0;
		
		ArrayList<Integer> mysteryNumberCopy = new ArrayList<>(pMysteryNumber);
		ListIterator<Integer> itNumberToCompare = pNumberToCompare.listIterator();
		
		while(itNumberToCompare.hasNext()) {
			
			int proposedDigit = itNumberToCompare.next().intValue();
			
			logger.trace("Proposed digit : " + proposedDigit); //$NON-NLS-1$
			
			ListIterator<Integer> itMysteryNumberCopy = mysteryNumberCopy.listIterator();
			
			while(itMysteryNumberCopy.hasNext()) {
				
				int mysteryDigit = itMysteryNumberCopy.next().intValue();
				
				logger.trace("Mystery digit : " + mysteryDigit); //$NON-NLS-1$
				
				if(proposedDigit == mysteryDigit) {
					
					NbDigitsInMystery++;						
					itMysteryNumberCopy.remove();
					if(logger.isTraceEnabled()) {
						logger.trace("Nb same digits in mystery : " + NbDigitsInMystery); //$NON-NLS-1$
						logger.trace("Digit remaining in mystery : " + mysteryNumberCopy); //$NON-NLS-1$
					}					
					break;
				}
			}
		}
		
		logger.debug("Number same digit in mystery after searching : " + NbDigitsInMystery); //$NON-NLS-1$
		
		return NbDigitsInMystery;
	}
	
	/**
	 * This methods search and count the number of digit at the good position compare to the mystery pattern.<br>
	 * 
	 * @param pNumberToCompare The number to be compared to the mystery number.
	 * @param pMysteryNumber The mystery pattern to discover.
	 * 
	 * @return the number of digit at the good position compare to the mystery pattern
	 */
	public static int findNbDigitsGoodPos(ArrayList<Integer> pNumberToCompare, ArrayList<Integer> pMysteryNumber) {
		
		logger.trace("Start search for nb digits in good pos"); //$NON-NLS-1$
		logger.trace("Number to compare : " + pNumberToCompare); //$NON-NLS-1$
		logger.trace("Mystery number : " + pMysteryNumber); //$NON-NLS-1$
		
		int goodPosFind = 0;
		
		ArrayList<Integer >mysteryNumberCopy = new ArrayList<>(pMysteryNumber);
		
		ListIterator<Integer> itMysteryNumberCopy = mysteryNumberCopy.listIterator();
		ListIterator<Integer> itNumberProposed = pNumberToCompare.listIterator();
		
		while(itNumberProposed.hasNext()) {
			
			int proposedDigit = itNumberProposed.next().intValue();
			
			logger.trace("Proposed digit : " + proposedDigit); //$NON-NLS-1$
			
			if(itMysteryNumberCopy.hasNext()) {
				
				int mysteryDigit = itMysteryNumberCopy.next().intValue();
				
				logger.trace("mystery digit : " + mysteryDigit); //$NON-NLS-1$
				
				if(proposedDigit == mysteryDigit ) {
					goodPosFind++;
					logger.trace("Nb digits in good pos mystery : " + goodPosFind); //$NON-NLS-1$
				}
			}
		}
		
		logger.debug("Nb digits in good pos : " + goodPosFind); //$NON-NLS-1$
		
		return goodPosFind;
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
	 * @see #defeat(ArrayList)
	 */
	@Override
	public void playChallenger() {
		
		logger.trace("Start Challenger mode"); //$NON-NLS-1$
		logger.trace("Remaining tries at start of turn: " + this.nbRemainingTries); //$NON-NLS-1$
		
		HumanPlayer humanPlayer = new HumanPlayer("Joueur", this.getDisplay()); //$NON-NLS-1$
		ArrayList<Integer> mysteryNumber = this.mysteryNumberGeneration(CombineGame.NB_COLORS);
		
		logger.debug("Mystery number : " + mysteryNumber); //$NON-NLS-1$
		
		if(CombineGame.DEVELOPER_MODE) {
			this.getDisplay().print("Le nombre mystère généré est : "); //$NON-NLS-1$
			this.getDisplay().println(mysteryNumber);
		}
		
		while(!this.isEndGame()) {
			
			ArrayList<Integer> proposedNumber = humanPlayer.giveNumber(CombineGame.NB_COLORS);
			String result = this.compareNumber(proposedNumber, mysteryNumber);
			
			if(logger.isTraceEnabled()) {
				logger.trace("Proposed number by player : " + proposedNumber); //$NON-NLS-1$
				logger.trace("Result of comparison : " + result); //$NON-NLS-1$
			}
			
			this.getDisplay().println(result);

			if(this.getGoodPos() == CombineGame.NB_DIGITS_MYSTERY) {
				logger.debug("Victory : good position = nb digits mystery"); //$NON-NLS-1$
				this.victory();
			}
			else if(this.getNbRemainingTries() == 1) {
				logger.debug("Defeat : no remaining tries "); //$NON-NLS-1$
				this.defeat(mysteryNumber);
			}
			else {
				this.setNbRemainingTries(this.getNbRemainingTries() - 1);
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
		
		logger.trace("Start Challenger mode"); //$NON-NLS-1$
		logger.trace("Remaining tries at start of turn: " + this.nbRemainingTries); //$NON-NLS-1$
		
		HumanPlayer human = new HumanPlayer("Joueur", this.getDisplay()); //$NON-NLS-1$
		ComputerPlayer computer = new ComputerPlayer("ordinateur", this.getDisplay()); //$NON-NLS-1$
		
		ArrayList<Integer> mysteryNumber = human.createMysteryNumber(CombineGame.NB_COLORS);
		
		logger.debug("Mystery number : " + mysteryNumber); //$NON-NLS-1$
		
		if(CombineGame.DEVELOPER_MODE) {
			this.getDisplay().print("Le nombre mystère est : "); //$NON-NLS-1$
			this.getDisplay().println(mysteryNumber);
		}
		
		//TODO don't create a new scan each time if possible
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		while(!this.isEndGame()) {
			
			// Ask computer to give a number
			ArrayList<Integer> proposition = computer.giveNumberPattern();
			
			// Compare computer's proposition to mystery pattern
			String result = compareNumber(proposition, mysteryNumber);
		
			// Store the comparison results
			computer.addGoodResultMastermind(proposition, this.getGoodPos());
			computer.addBadResultMastermind(proposition, this.getBadPos());
			
			// Show the computer proposition and the result
			this.getDisplay().print("Proposition : "); //$NON-NLS-1$
			this.getDisplay().print(proposition);
			this.getDisplay().print(" -> Réponse : " + result + "   (Appuyez sur entrée pour continuer)"); //$NON-NLS-1$ //$NON-NLS-2$
			scan.nextLine();
			
			//Check if it's the end of game
			if(this.getGoodPos() == CombineGame.NB_DIGITS_MYSTERY) {
				logger.debug("Defeat : good position = nb digits mystery"); //$NON-NLS-1$
				this.defeat(mysteryNumber);
			}
			else if(!(this.getGoodPos() == CombineGame.NB_DIGITS_MYSTERY) && this.getNbRemainingTries() == 1){
				logger.debug("Victory : no remaining tries for computer"); //$NON-NLS-1$
				this.victory();
			}
			
			// Decrease the remaining tries by 1
			this.setNbRemainingTries(this.getNbRemainingTries()-1);
		}
		
	}

	@Override
	public void playDuel() {
		// TODO Auto-generated method stub	
		
	}

}
