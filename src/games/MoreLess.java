package games;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import display.Display;
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
 * @version 1.0.5
 *
 */
public class MoreLess extends Game{
	
	/**
	 * Log4j2 Logger
	 */
	private static final Logger logger = LogManager.getLogger(MoreLess.class);
	
	/**
	 * Constructor of the MoreLess class.<br>
	 * <br>
	 * The constructor initializes the various variables and starts the game with the desired mode.
	 * 
	 * @param pGameMode game mode to be used
	 * @param pDisplay display that will be used to make game displays
	 * 
	 * @see #playChallenger()
	 * @see #playDefender()
	 * @see #playDuel()
	 */
	public MoreLess(GameModes pGameMode, Display pDisplay) {
		
		super(pGameMode, pDisplay);
		logger.trace("MoreLess Construction"); //$NON-NLS-1$
	}
	
	/**
	 * This method allows you to compare the number proposed as a parameter with the mystery number.<br>
	 * <br>
	 * The comparison is done digit by digit.<br>
	 * The result of the comparison is returned in the form of a string of characters indicating for each digit the result of the comparison: <br>
	 * <ul>
	 * <li>+ if the mystery number is higher than the proposed one</li>
	 * <li>- if the mystery number is lower than the proposed one</li>
	 * <li>= if the mystery number is identical to the proposed one</li>
	 * </ul>
	 * 
	 * @param pNumberToCompare The number to be compared to the mystery number. 
	 * @return The result of the comparison in the form of a String
	 */
	@Override
	public String compareNumber(ArrayList<Integer> pNumberToCompare, ArrayList<Integer> pMysteryNumber) {
		
		logger.debug("Compare " + pNumberToCompare + " to mysteryNumber : " + pMysteryNumber); //$NON-NLS-1$ //$NON-NLS-2$
		
		ListIterator<Integer> itProposedNumber = pNumberToCompare.listIterator();
		ListIterator<Integer> itMysteryNumber = pMysteryNumber.listIterator();
		String result = ""; //$NON-NLS-1$
		
		while(itProposedNumber.hasNext()) {
			while(itMysteryNumber.hasNext()) {
				
				int proposedDigit = itProposedNumber.next().intValue();
				int mysteryDigit = itMysteryNumber.next().intValue();
				
				if(logger.isTraceEnabled()) {
					logger.trace("proposedDigit = " + proposedDigit); //$NON-NLS-1$
					logger.trace("mysteryDigit = " + mysteryDigit); //$NON-NLS-1$
				}
				
				if( proposedDigit - mysteryDigit < 0) {
					result = result + "+"; //$NON-NLS-1$
				}
				else if(proposedDigit - mysteryDigit > 0) {
					result = result + "-"; //$NON-NLS-1$
				}
				else {
					result = result + "="; //$NON-NLS-1$
				}
				logger.trace("result = " + result); //$NON-NLS-1$
			}	
		}
		logger.debug("Result comparison = " + result); //$NON-NLS-1$
		return result;
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
	 * @see #defeat(ArrayList)
	 */
	@Override
	public void playChallenger() {
		
		logger.trace("Start Challenger mode"); //$NON-NLS-1$
		
		HumanPlayer humanPlayer = new HumanPlayer("Player", this.getDisplay()); //$NON-NLS-1$
		ArrayList<Integer> mysteryNumber = this.mysteryNumberGeneration(CombineGame.MAX_VALUE_DIGIT);
		if(CombineGame.DEVELOPER_MODE) {
			this.getDisplay().print("Le nombre mystère généré est : "); //$NON-NLS-1$
			this.getDisplay().println(mysteryNumber);
		}
		
		logger.debug("Mystery Number = " + mysteryNumber); //$NON-NLS-1$
		
		while(!this.isEndGame()) {
			
			logger.trace("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
			
			ArrayList<Integer> number = humanPlayer.giveNumber(CombineGame.MAX_VALUE_DIGIT);
			String comparison = this.compareNumber(number, mysteryNumber);
			String result = " -> Réponse : " + comparison; //$NON-NLS-1$
			
			this.getDisplay().print(("Proposition : ")); //$NON-NLS-1$
			this.getDisplay().print(number);
			this.getDisplay().println(result);
			
			if(number.equals(mysteryNumber)) { 
				this.victory();	
			}
			
			if(!number.equals(mysteryNumber) && this.getNbRemainingTries() == 1) { 
				this.defeat(mysteryNumber);
			}
			
			this.setNbRemainingTries(this.getNbRemainingTries()-1);
			
			logger.debug("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
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
		
		logger.trace("Start Defender mode"); //$NON-NLS-1$
		
		HumanPlayer humanPlayer = new HumanPlayer("Player", this.getDisplay()); //$NON-NLS-1$
		ComputerPlayer computerPlayer = new ComputerPlayer("Ordinateur", this.getDisplay()); //$NON-NLS-1$
		ArrayList<Integer> mysteryNumber = humanPlayer.createMysteryNumber(CombineGame.MAX_VALUE_DIGIT);
		
		logger.debug("Mystery Number = " + mysteryNumber); //$NON-NLS-1$
		
		//TODO warning a prendre en compte
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		while(!this.isEndGame()) {
			
			logger.trace("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
			
			ArrayList<Integer> number = computerPlayer.giveNumber(CombineGame.MAX_VALUE_DIGIT);
			String comparison = this.compareNumber(number, mysteryNumber);
			computerPlayer.addResultatMoreLess(comparison);
			String resultat = " -> Réponse : " + comparison; //$NON-NLS-1$
			
			this.getDisplay().print(computerPlayer.getPlayerName() + " propose : "); //$NON-NLS-1$
			this.getDisplay().print(number);
			this.getDisplay().print(resultat);
			this.getDisplay().print("   (Appuyez sur entrée pour continuer)"); //$NON-NLS-1$
			scan.nextLine();
			
			if(number.equals(mysteryNumber)) { 
				this.defeat(mysteryNumber);	
			}
			
			if(!number.equals(mysteryNumber) && this.getNbRemainingTries() == 1) { 
				this.victory();
			}
			
			this.setNbRemainingTries(this.getNbRemainingTries()-1);	
			
			logger.debug("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
		}		
	}
	

	/**
	 * This method executes the game in duel mode<br>
	 * <br>
	 * In this mode, the player and computer must discover a randomly generated mystery number.<br>
	 * In each turn, player and computer make proposals. The computer's proposal is not visible, only the result of its proposal is visible.<br>
	 *
	 */
	@Override
	public void playDuel() {
		
		logger.trace("Start Duel mode"); //$NON-NLS-1$
		
		HumanPlayer humanPlayer= new HumanPlayer("Player", this.getDisplay()); //$NON-NLS-1$
		//this.players.add(humanPlayer);
		ComputerPlayer computerPlayer = new ComputerPlayer("Ordinateur", this.getDisplay()); //$NON-NLS-1$
		//this.players.add(computerPlayer);
		String compHumanNb = new String();
		String compComputerNb= new String();
		
		ArrayList<Integer> mysteryNumberComputer = this.mysteryNumberGeneration(CombineGame.MAX_VALUE_DIGIT);
		//this.mysteryNumberGeneration(CombineGame.MAX_VALUE_DIGIT);
		ArrayList<Integer> mysteryNumberHuman = humanPlayer.createMysteryNumber(CombineGame.MAX_VALUE_DIGIT);
		
		logger.debug("Mystery Number computer = " + mysteryNumberComputer); //$NON-NLS-1$
		logger.debug("Mystery Number human = " + mysteryNumberHuman); //$NON-NLS-1$
		
		this.getDisplay().println("Les nombres mystères ont été créés. Que le meilleur gagne"); //$NON-NLS-1$
		
		if(CombineGame.DEVELOPER_MODE) {
			this.getDisplay().print("Le nombre mystère généré par l'ordinateur est : "); //$NON-NLS-1$
			this.getDisplay().println(mysteryNumberComputer);
			this.getDisplay().print("Le nombre mystère généré par le jouer est : "); //$NON-NLS-1$
			this.getDisplay().println(mysteryNumberHuman);
		}
		
		while(!this.isEndGame()) {
			
			logger.trace("Start round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
			
			
			// Human turn
			ArrayList<Integer> numberHuman = new ArrayList<>();	
			numberHuman = humanPlayer.giveNumber(CombineGame.MAX_VALUE_DIGIT);
			
			logger.debug("Number proposed  by player: " + numberHuman); //$NON-NLS-1$
			
			compHumanNb = this.compareNumber(numberHuman, mysteryNumberComputer);
			String resultatHuman = " -> Réponse : " + compHumanNb; //$NON-NLS-1$
			
			this.getDisplay().print(("Votre proposition : ")); //$NON-NLS-1$
			this.getDisplay().print(numberHuman);
			this.getDisplay().println(resultatHuman);
			
			if(numberHuman.equals(mysteryNumberComputer)) { 
				this.victory();	
			}
			
			// Computer turn
			if(!this.isEndGame()) {
				ArrayList<Integer> numberComputer = new ArrayList<>();	
				numberComputer = computerPlayer.giveNumber(CombineGame.MAX_VALUE_DIGIT);
				
				logger.debug("Number proposed by computer: " + numberComputer); //$NON-NLS-1$
				
				compComputerNb = this.compareNumber(numberComputer, mysteryNumberHuman);
				computerPlayer.addResultatMoreLess(compComputerNb);
				String resultatComputer = " -> Réponse : " + compComputerNb; //$NON-NLS-1$
				
				this.getDisplay().print(("L'ordinateur a proposé un nombre. "));//$NON-NLS-1$
				this.getDisplay().println(resultatComputer);
				
				if(numberComputer.equals(mysteryNumberHuman)) { 
					this.getDisplay().println("L'ordinateur a proposé les valeurs suivantes :"); //$NON-NLS-1$
					for(ArrayList<Integer> proposition : computerPlayer.getListNumberProposed()) {
						this.getDisplay().print(proposition);
						this.getDisplay().print("\n"); //$NON-NLS-1$
					}

					this.defeat(mysteryNumberComputer);	
				}
				
				if(!numberHuman.equals(mysteryNumberComputer) && !numberComputer.equals(mysteryNumberHuman) && this.getNbRemainingTries() == 1) { 
					this.equality();
				}
				this.setNbRemainingTries(this.getNbRemainingTries()-1);
				
				logger.debug("End round - Remaining Tries : " + this.getNbRemainingTries()); //$NON-NLS-1$
			}
		}
			
	}
	
}
