package games;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

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
 * @version 1.0.0
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
	 * @see #playDefenser()
	 * @see #playDuel()
	 * 
	 */
	protected Mastermind(GameModes pGameMode, Display pDisplay) {
		
		super(pGameMode, pDisplay);

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
	public String compareNumber(ArrayList<Integer> pNumberToCompare) {
		
		String result = ""; //$NON-NLS-1$
		
		int nbDigitInMystery = Mastermind.findNbDigitInMystery(pNumberToCompare, this.getMysteryNumber());
		this.setGoodPos(Mastermind.findNbDigitsGoodPos(pNumberToCompare, this.getMysteryNumber()));
		this.setBadPos(nbDigitInMystery - this.getGoodPos());
		
		result = this.getBadPos() + " présent(s), " + this.getGoodPos() + " bien placé(s)"; //$NON-NLS-1$ //$NON-NLS-2$
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
		
		int NbDigitsInMystery = 0;
		
		ArrayList<Integer> mysteryNumberCopy = new ArrayList<>(pMysteryNumber);
		ListIterator<Integer> itNumberToCompare = pNumberToCompare.listIterator();
		
		while(itNumberToCompare.hasNext()) {
			
			int proposedDigit = itNumberToCompare.next().intValue();
			
			ListIterator<Integer> itMysteryNumberCopy = mysteryNumberCopy.listIterator();
			
			while(itMysteryNumberCopy.hasNext()) {
				
				int mysteryDigit = itMysteryNumberCopy.next().intValue();

				if(proposedDigit == mysteryDigit) {

					NbDigitsInMystery++;						
					itMysteryNumberCopy.remove();
					break;
				}
			}
		}
		
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
		
		int goodPosFind = 0;
		
		ArrayList<Integer >mysteryNumberCopy = new ArrayList<>(pMysteryNumber);
		
		ListIterator<Integer> itMysteryNumberCopy = mysteryNumberCopy.listIterator();
		ListIterator<Integer> itNumberProposed = pNumberToCompare.listIterator();
		
		while(itNumberProposed.hasNext()) {
			
			int proposedDigit = itNumberProposed.next().intValue();
			
			if(itMysteryNumberCopy.hasNext()) {
				
				int mysteryDigit = itMysteryNumberCopy.next().intValue();
				
				if(proposedDigit == mysteryDigit ) {
					goodPosFind++;
				}
			}
		}
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
	 * @see #defeat()
	 */
	@Override
	public void playChallenger() {

		HumanPlayer humanPlayer = new HumanPlayer("Joueur", this.getDisplay()); //$NON-NLS-1$
		this.mysteryNumberGeneration(CombineGame.NB_COLORS);
		
		if(CombineGame.DEVELOPER_MODE) {
			this.getDisplay().print("Le nombre mystère généré est : "); //$NON-NLS-1$
			this.getDisplay().println(this.getMysteryNumber());
		}
		
		while(!this.isEndGame()) {
			
			ArrayList<Integer> proposedNumber = humanPlayer.giveNumber(CombineGame.NB_COLORS);
			String resultat = this.compareNumber(proposedNumber);
			
			this.getDisplay().println(resultat);

			if(this.getGoodPos() == CombineGame.NB_DIGITS_MYSTERY) {
				this.victory();
			}
			else if(this.getNbRemainingTries() == 1) {
				this.defeat();
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
	public void playDefenser() {
		
		HumanPlayer human = new HumanPlayer("Joueur", this.getDisplay()); //$NON-NLS-1$
		ComputerPlayer computer = new ComputerPlayer("ordinateur", this.getDisplay()); //$NON-NLS-1$
		
		this.setMysteryNumber(human.createMysteryNumber(CombineGame.NB_COLORS));
		Scanner scan = new Scanner(System.in);
		
		while(!this.isEndGame()) {
			
			// Ask computer to give a number
			ArrayList<Integer> proposition = computer.giveNumberPattern();
			
			// Compare computer's proposition to mystery pattern
			String result = compareNumber(proposition);
		
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
				this.defeat();
			}
			else if(!(this.getGoodPos() == CombineGame.NB_DIGITS_MYSTERY) && this.getNbRemainingTries() == 1){
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
