package games;

import java.util.Scanner;

import affichage.Affichage;
import games.constantes.ModesDeJeu;
import games.constantes.NomsDeJeu;

/**
 * <b>CombineGame permet de lancer le jeu souhaité par l'utilisateur.</b><br>
 * <br>
 * Affiche différents menus permettant à l'utilisateur de choisir le jeu et l'option de jeu.<br>
 * Quand le choix du jeu et du mode est effectué par l'utilisateur, le jeu à proprement parler est appelé avec en argument le mode de jeu.<br>
 * 
 * @author BRUCELLA2
 * @version 1.0
 * 
 * 
 */
public class CombineGame {
	
//***** VARIABLES *****/
	
	/**
	 * affichage sert à l'affichage des différents élements du jeu (par exemple le menu)
	 * 
	 * @see #getAffichage()
	 * @see #setAffichage(Affichage)
	 */
	private Affichage affichage;
	
	/**
	 * Scanner qui permet de récupérer les données (chaines de caractères) saisies par l'utilisateur
	 * 
	 * @see #getScanner()
	 * @see #setScanner(Scanner)
	 * 
	 */
	private Scanner scanner;
	
	/**
	 * jeuChoisi représente le jeu choisi par l'utilisateur au travers des menus.
	 * 
	 * @see NomsDeJeu#PLUS_MOINS
	 * @see NomsDeJeu#MASTERMIND
	 * 
	 * @see #getJeuChoisi()
	 * @see #setJeuChoisi(NomsDeJeu)
	 */
	private NomsDeJeu jeuChoisi;
	
	/**
	 * modeChoisi représente le mode de jeu choisi par l'utilisateur au travers des menus.
	 * 
	 * @see ModesDeJeu#CHALLENGER
	 * @see ModesDeJeu#DEFENSEUR
	 * @see ModesDeJeu#DUEL
	 * 
	 * @see #getModeChoisi()
	 * @see #setModeChoisi(ModesDeJeu)
	 */
	private ModesDeJeu modeChoisi;
	
	/**
	 * executeCombineGame sert à indiquer si CombineGame est en cours d'exécution ou si son exécution doit être arretée en changeant sa valeur à false.
	 * 
	 * @see #getExecuteCombineGame()
	 * @see #setExecuteCombineGame(boolean)
	 */
	private boolean executeCombineGame = true;
	
	
	
//***** CONSTRUCTEURS *****/
	
	/**
	 * Constructeur de la classe CombineGame.<br>
	 * <br>
	 * Initialisation des différentes variables et lancement de CombineGame via la méthode {@link #launchCombineGame()}
	 * 
	 * @see #launchCombineGame()
	 * 
	 */
	public CombineGame() {
		
		this.setAffichage(new Affichage());
		this.setScanner(new Scanner(System.in));
		this.setJeuChoisi(null);
		this.setModeChoisi(null);
		this.setExecuteCombineGame(true);
		
		this.launchCombineGame();
		
	}
	
	/**
	 * Cette méthode est au coeur du controle des jeux CombineGame. <br>
	 * <br>
	 * Demande l'affichage du menu de choix du jeu.<br>
	 * Demande l'affichage du menu de choix du mode.<br>
	 * Demande le démarrage du jeu choisi.<br>
	 * Demande l'affichage du menu de fin de partie<br>
	 * 
	 * 
	 * @see #menuChoixJeu()
	 * @see #menuChoixMode()
	 */
	private void launchCombineGame() {
		
		while(this.getExecuteCombineGame()) {

			while ((this.getJeuChoisi() == null || this.getModeChoisi() == null) && this.getExecuteCombineGame() != false) {
				
				char choixJeu = this.menuChoixJeu();
				if(choixJeu == 'Q') {
					break;
				}
				
				this.menuChoixMode();
				
				while(this.getJeuChoisi() != null && this.getModeChoisi() != null){
					if(this.getJeuChoisi() == NomsDeJeu.PLUS_MOINS) {
						
						Game game = new PlusMoins(this.getModeChoisi(), this.getAffichage());
						
						if(game.isFinPartie() == true) {

							char choixFinJeu = this.menuChoixFinJeu();
							if(choixFinJeu == 'Q') {
								break;
							}
						}
					}
				}

			}


		}
		
	}
	
//***** GETTER *****/
	
	/**
	 * Retourne l'affichage qui permet de faire les différents affichages du jeu
	 * 
	 * @return L'affichage permettant l'affichage du jeu
	 * 
	 * @see #setAffichage(Affichage)
	 */
	public Affichage getAffichage() {
		return this.affichage;
	}
	
	/**
	 * Retourne le scanner qui permet de récupérer les saisies de l'utilisateur
	 * 
	 * @return le scanner qui permet de récupérer les saisies de l'utilisateur
	 * 
	 * @see #setScanner(Scanner)
	 * 
	 */
	public Scanner getScanner() {
		return this.scanner;
	}
		
	/**
	 * Retourne le status de CombineGame.<br>
	 * True si CombineGame doit s'exécuter, False, si CombineGame doit s'arrêter.
	 * 
	 * @return le status de CombineGame. 
	 * 
	 * @see #setExecuteCombineGame(boolean)
	 */
	public boolean getExecuteCombineGame() {
		return this.executeCombineGame;
	}
	
	/**
	 * Retourne le jeu choisi par l'utilisateur sous la forme d'un {@link NomsDeJeu}<br>
	 * Retourne null si aucun jeu n'a été choisi
	 * 
	 * @return Le jeu choisi par l'utilisateur.
	 * 
	 * @see #setJeuChoisi(NomsDeJeu)
	 */
	public NomsDeJeu getJeuChoisi() {
		return this.jeuChoisi;
	}
	
	/**
	 * Retourne le mode du jeu choisi par l'utilisateur sous la forme d'un {@link ModesDeJeu}<br>
	 * Retourne null si aucun mode n'est choisi
	 * 
	 * @return Le mode de jeu choisi par l'utilisateur
	 * 
	 * @see #setModeChoisi(ModesDeJeu)
	 */
	public ModesDeJeu getModeChoisi() {
		return this.modeChoisi;
	}
	
//***** SETTER *****/
	
	/**
	 * Permet de définir le scanner utilisé pour récupérer les saisies de l'utilisateur
	 * 
	 * @param pScanner un scanner qui sera utilisé pour récupérer les saisies de l'utilisateur
	 * 
	 * @see #getScanner()
	 */
	private void setScanner(Scanner pScanner) {
		this.scanner = pScanner;
	}
	
	/**
	 * Permet de définir l'affichage qui sera utilisé pour effectuer les affichages
	 * 
	 * @param pAffichage Affichage utilisé pour effectuer les affichages
	 * 
	 * @see #getAffichage()
	 */
	private void setAffichage(Affichage pAffichage) {
		this.affichage = pAffichage;
	}
	
	/**
	 * Permet de définir le jeu choisi par l'utilisateur
	 *  
	 * @param pJeuChoisi Jeu choisi par l'utilisateur
	 * 
	 * @see #getJeuChoisi()
	 */
	private void setJeuChoisi(NomsDeJeu pJeuChoisi) {
		this.jeuChoisi = pJeuChoisi;
	}
	
	/**
	 * Permet de définir le mode de jeu choisi par l'utilisateur
	 * 
	 * @param pModeChoisi Mode de jeu choisi par l'utilisateur
	 * 
	 * @see #getModeChoisi()
	 */
	private void setModeChoisi(ModesDeJeu pModeChoisi) {
		this.modeChoisi = pModeChoisi;
	}
	
	/**
	 * Permet de définir si CombineGame doit continuer à s'exécuter.
	 * 
	 * @param pExecuteCombineGame true si CombineGame continue ou false si CombineGame doit s'arrêter
	 * 
	 * @see #getExecuteCombineGame()
	 */
	private void setExecuteCombineGame(boolean pExecuteCombineGame) {
		this.executeCombineGame = pExecuteCombineGame;
	}
	
	
//***** METHODES *****/
	
	/**
	 * Cette méthode permet à l'utilisateur de choisir son jeu.<br>
	 * 
	 * Le choix du jeu se fait au travers d'un menu. <br>
	 * Le menu actualise la variable {@link #jeuChoisi}. Le choix de l'utilisateur est également renvoyé à l'issu de la méthode sous forme d'un char en majuscule.<br>
	 * Les différents retour possible sont :<br>
	 * <ul>
	 * <li>'1' pour le jeu Plus_Moins</li>
	 * <li>'2' pour le jeu MasterMind</li>
	 * <li>'Q' pour arrêter CombineGame</li>
	 * </ul>
	 * 
	 * 
	 * @return Le choix de l'utilisateur sous forme d'un char en majuscule.
	 */
	public char menuChoixJeu() {
		
		boolean choixJeu = false;
		char choix;
		
		do {
			this.getAffichage().afficheMenuChoixJeu();
			choix = this.getScanner().nextLine().charAt(0);
			choix = Character.toUpperCase(choix);
			
			switch(choix)
			{
			case '1':
				choixJeu = true;
				this.setJeuChoisi(NomsDeJeu.PLUS_MOINS);
				break;
				
			case '2':
				choixJeu = true;
				this.setJeuChoisi(NomsDeJeu.MASTERMIND);
				break;
			
			case 'Q':
				choixJeu = true;
				this.setJeuChoisi(null);
				this.setExecuteCombineGame(false);
				break;
				
			default:
				break;	
			}
		}while(!choixJeu);
		
		return choix;
	}
	
	/**
	 * Cette méthode permet à l'utilisateur de choisir son mode jeu.<br>
	 * 
	 * Le choix du mode de jeu se fait au travers d'un menu. <br>
	 * Le menu actualise la variable {@link #modeChoisi}. Le choix de l'utilisateur est également renvoyé à l'issu de la méthode sous forme d'un char en majuscule.<br>
	 * Les différents retour possible sont :<br>
	 * <ul>
	 * <li>'1' pour le mode Challenger</li>
	 * <li>'2' pour le mode Defenseur</li>
	 * <li>'3' pour le mode Duel</li>
	 * <li>'R' pour revenir en arrière</li>
	 * </ul>
	 * 
	 * 
	 * @return Le choix de l'utilisateur sous forme d'un char en majuscule.
	 */
	public char menuChoixMode() {
		
		boolean choixMode = false;
		char choix;
		
		do {
			this.getAffichage().afficheMenuModeJeu();
			choix = this.getScanner().nextLine().charAt(0);
			choix = Character.toUpperCase(choix);
			
			switch(choix)
			{
			case '1':
				choixMode = true;
				this.setModeChoisi(ModesDeJeu.CHALLENGER);
				break;
				
			case '2':
				choixMode = true;
				this.setModeChoisi(ModesDeJeu.DEFENSEUR);
				break;
				
			case '3':
				choixMode = true;
				this.setModeChoisi(ModesDeJeu.DUEL);
				break;
				
			case 'R':
				choixMode = true;
				this.setModeChoisi(null);
				this.setJeuChoisi(null);
				break;
				
			default :
				break;
			}
			
		}while(!choixMode);
		
		return choix;
	}
	
	/**
	 * Cette méthode permet à l'utilisateur d'indiquer ce qu'il souhaite faire à la fin de sa partie.<br>
	 * 
	 * Le choix de fin de partie se fait au travers d'un menu. <br>
	 * Le menu réinitialise à null la variable {@link #modeChoisi} et la variable {@link #jeuChoisi}. <br>
	 * Le choix de l'utilisateur est également renvoyé à l'issu de la méthode sous forme d'un char en majuscule.<br>
	 * Les différents retour possible sont :<br>
	 * <ul>
	 * <li>'1' pour rejouer au même jeu avec le même mode</li>
	 * <li>'R' pour revenir au menu de choix du jeu</li>
	 * <li>'Q' Pour quitter CombineGame</li>
	 * </ul>
	 * 
	 * 
	 * @return Le choix de l'utilisateur sous forme d'un char en majuscule.
	 */
	public char menuChoixFinJeu() {
		boolean choixFinJeu = false;
		char choix;
		
		do {
			this.getAffichage().afficheMenuFinJeu();
			choix = this.getScanner().nextLine().charAt(0);
			choix = Character.toUpperCase(choix);
			
			switch(choix) 
			{
			case '1':
				choixFinJeu = true;
				break;
			
			case 'R':
				choixFinJeu = true;
				this.setJeuChoisi(null);
				this.setModeChoisi(null);
				break;
			
			case 'Q':
				choixFinJeu = true;
				this.setExecuteCombineGame(false);
				break;
			
			default :
				break;
			}		
		}while(!choixFinJeu);
		
		return choix;
	}
}
