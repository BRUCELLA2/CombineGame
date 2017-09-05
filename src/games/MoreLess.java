package games;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

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
 * @version 1.0.3
 *
 */
public class MoreLess extends Game{
	
	/**
	 * Constructor of the MoreLess class.<br>
	 * <br>
	 * The constructor initializes the various variables and starts the game with the desired mode.
	 * 
	 * @param pGameMode game mode to be used
	 * @param pDisplay display that will be used to make game displays
	 * 
	 * @see #playChallenger()
	 * @see #playDefenser()
	 * @see #playDuel()
	 */
	public MoreLess(GameModes pGameMode, Display pDisplay) {
		super(pGameMode, pDisplay);
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
	public String compareNumber(ArrayList<Integer> pNumberToCompare) {
		ListIterator<Integer> itProposedNumber = pNumberToCompare.listIterator();
		ListIterator<Integer> itMysteryNumber = this.getMysteryNumber().listIterator();
		String result = ""; //$NON-NLS-1$
		
		while(itProposedNumber.hasNext()) {
			while(itMysteryNumber.hasNext()) {
				
				int proposedDigit = itProposedNumber.next().intValue();
				int mysteryDigit = itMysteryNumber.next().intValue();
				if( proposedDigit - mysteryDigit < 0) {
					result = result + "+"; //$NON-NLS-1$
				}
				else if(proposedDigit - mysteryDigit > 0) {
					result = result + "-"; //$NON-NLS-1$
				}
				else {
					result = result + "="; //$NON-NLS-1$
				}
			}	
		}		
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
	 * @see #defeat()
	 */
	@Override
	public void playChallenger() {

		HumanPlayer humanPlayer = new HumanPlayer("Player", this.getDisplay()); //$NON-NLS-1$
		this.mysteryNumberGeneration(CombineGame.MAX_VALUE_DIGIT);
		if(CombineGame.DEVELOPER_MODE) {
			this.getDisplay().print("Le nombre mystère généré est : "); //$NON-NLS-1$
			this.getDisplay().println(this.getMysteryNumber());
		}
		
		while(!this.isEndGame()) {
			
			ArrayList<Integer> number = humanPlayer.giveNumber(CombineGame.MAX_VALUE_DIGIT);
			String comparison = this.compareNumber(number);
			String result = " -> Réponse : " + comparison; //$NON-NLS-1$
			
			this.getDisplay().print(("Proposition : ")); //$NON-NLS-1$
			this.getDisplay().print(number);
			this.getDisplay().println(result);
			
			//TODO Refaire la comparaison directement avec les arraylist pour prendre en compte le nb de chiffres en paramètrage du jeu
			if(comparison.equals("====")) { //$NON-NLS-1$
				this.victory();	
			}
			
			if(!comparison.equals("====") && this.getNbRemainingTries() == 1) { //$NON-NLS-1$
				this.defeat();
			}
			
			this.setNbRemainingTries(this.getNbRemainingTries()-1);
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
	public void playDefenser() {

		HumanPlayer humanPlayer = new HumanPlayer("Player", this.getDisplay()); //$NON-NLS-1$
		ComputerPlayer computerPlayer = new ComputerPlayer("Ordinateur", this.getDisplay()); //$NON-NLS-1$
		this.setMysteryNumber(humanPlayer.createMysteryNumber(CombineGame.MAX_VALUE_DIGIT));
		//TODO warning a prendre en compte
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		while(!this.isEndGame()) {
			
			ArrayList<Integer> number = computerPlayer.giveNumber(CombineGame.MAX_VALUE_DIGIT);
			String comparison = this.compareNumber(number);
			computerPlayer.addResultatMoreLess(comparison);
			String resultat = " -> Réponse : " + comparison; //$NON-NLS-1$
			
			this.getDisplay().print(computerPlayer.getPlayerName() + " propose : "); //$NON-NLS-1$
			this.getDisplay().print(number);
			this.getDisplay().print(resultat);
			this.getDisplay().print("   (Appuyez sur entrée pour continuer)"); //$NON-NLS-1$
			scan.nextLine();
			
			if(comparison.equals("====")) { //$NON-NLS-1$
				this.defeat();	
			}
			
			if(!comparison.equals("====") && this.getNbRemainingTries() == 1) { //$NON-NLS-1$
				this.victory();
			}
			
			this.setNbRemainingTries(this.getNbRemainingTries()-1);	
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
	//TODO A refaire, ne correspond pas à l'énoncé.
	public void playDuel() {
		
		HumanPlayer humanPlayer= new HumanPlayer("Player", this.getDisplay()); //$NON-NLS-1$
		this.players.add(humanPlayer);
		ComputerPlayer computerPlayer = new ComputerPlayer("Ordinateur", this.getDisplay()); //$NON-NLS-1$
		this.players.add(computerPlayer);
		String compHumanNb = new String();
		String compComputerNb= new String();
		
		this.mysteryNumberGeneration(CombineGame.MAX_VALUE_DIGIT);
		this.getDisplay().println("Un nombre a été généré. A vous de le deviner. Que le meilleur gagne"); //$NON-NLS-1$
		
		if(CombineGame.DEVELOPER_MODE) {
			this.getDisplay().print("Le nombre mystère généré est : "); //$NON-NLS-1$
			this.getDisplay().println(this.getMysteryNumber());
		}
		
		while(!this.isEndGame()) {
			
			for(Player player : this.getPlayers()) {
				
				ArrayList<Integer> number = new ArrayList<>();			
				number = player.giveNumber(CombineGame.MAX_VALUE_DIGIT);
				
				
				if(player instanceof HumanPlayer) {
					compHumanNb = this.compareNumber(number);
					String resultat = " -> Réponse : " + compHumanNb; //$NON-NLS-1$
					
					this.getDisplay().print(("Votre proposition : ")); //$NON-NLS-1$
					this.getDisplay().print(number);
					this.getDisplay().println(resultat);
					
					if(compHumanNb.equals("====")) { //$NON-NLS-1$
						this.victory();	
					}
					
					//TODO Mettre nb de coups comme variable du joueur ? 1 coup = 1 tour donc pas nécessaire ?
					/*if(!comparaison.equals("====") && this.getNbCoups() == 1) { //$NON-NLS-1$
						this.defaite();
					}*/
				}
				else if (!this.isEndGame()) {
					compComputerNb = this.compareNumber(number);
					computerPlayer.addResultatMoreLess(compComputerNb);
					String resultat = " -> Réponse : " + compComputerNb; //$NON-NLS-1$
					
					this.getDisplay().print(("L'ordinateur a proposé un nombre. "));//$NON-NLS-1$
					this.getDisplay().println(resultat);
					
					if(compComputerNb.equals("====")) { //$NON-NLS-1$
						this.getDisplay().println("L'ordinateur a proposé les valeurs suivantes :"); //$NON-NLS-1$
						for(ArrayList<Integer> proposition : computerPlayer.getListNumberProposed()) {
							this.getDisplay().print(proposition);
							this.getDisplay().print("\n"); //$NON-NLS-1$
						}

						this.defeat();	
					}
				}
			}
			if(!compHumanNb.equals("====") && !compComputerNb.equals("====") && this.getNbRemainingTries() == 1) { //$NON-NLS-1$ //$NON-NLS-2$
				this.equality();
			}
			this.setNbRemainingTries(this.getNbRemainingTries()-1);
		}
	}
	
}
