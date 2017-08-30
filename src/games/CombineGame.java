package games;

import java.util.Scanner;

import affichage.Affichage;
import games.constantes.ModesDeJeu;
import games.constantes.NomsDeJeu;

/**
 * <b>CombineGame permet de lancer le jeu souhait� par l'utilisateur.</b><br>
 * <br>
 * Affiche diff�rents menus permettant � l'utilisateur de choisir le jeu et l'option de jeu.<br>
 * Quand le choix du jeu et du mode est effectu� par l'utilisateur, le jeu � proprement parler est appel� avec en argument le mode de jeu.<br>
 * 
 * @author BRUCELLA2
 * @version 1.0
 * 
 * 
 */
public class CombineGame {
	
//***** VARIABLES *****/
	
	/**
	 * affichage sert � l'affichage des diff�rents �lements du jeu (par exemple le menu)
	 * 
	 * @see #getAffichage()
	 * @see #setAffichage(Affichage)
	 */
	private Affichage affichage;
	
	/**
	 * Scanner qui permet de r�cup�rer les donn�es (chaines de caract�res) saisies par l'utilisateur
	 * 
	 * @see #getScanner()
	 * @see #setScanner(Scanner)
	 * 
	 */
	private Scanner scanner;
	
	/**
	 * jeuChoisi repr�sente le jeu choisi par l'utilisateur au travers des menus.
	 * 
	 * @see NomsDeJeu#PLUS_MOINS
	 * @see NomsDeJeu#MASTERMIND
	 * 
	 * @see #getJeuChoisi()
	 * @see #setJeuChoisi(NomsDeJeu)
	 */
	private NomsDeJeu jeuChoisi;
	
	/**
	 * modeChoisi repr�sente le mode de jeu choisi par l'utilisateur au travers des menus.
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
	 * executeCombineGame sert � indiquer si CombineGame est en cours d'ex�cution ou si son ex�cution doit �tre arret�e en changeant sa valeur � false.
	 * 
	 * @see #getExecuteCombineGame()
	 * @see #setExecuteCombineGame(boolean)
	 */
	private boolean executeCombineGame = true;
	
	
	
//***** CONSTRUCTEURS *****/
	
	/**
	 * Constructeur de la classe CombineGame.<br>
	 * <br>
	 * Initialisation des diff�rentes variables et lancement de CombineGame via la m�thode {@link #launchCombineGame()}
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
	 * Cette m�thode est au coeur du controle des jeux CombineGame. <br>
	 * <br>
	 * Demande l'affichage du menu de choix du jeu.<br>
	 * Demande l'affichage du menu de choix du mode.<br>
	 * Demande le d�marrage du jeu choisi.<br>
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
	 * Retourne l'affichage qui permet de faire les diff�rents affichages du jeu
	 * 
	 * @return L'affichage permettant l'affichage du jeu
	 * 
	 * @see #setAffichage(Affichage)
	 */
	public Affichage getAffichage() {
		return this.affichage;
	}
	
	/**
	 * Retourne le scanner qui permet de r�cup�rer les saisies de l'utilisateur
	 * 
	 * @return le scanner qui permet de r�cup�rer les saisies de l'utilisateur
	 * 
	 * @see #setScanner(Scanner)
	 * 
	 */
	public Scanner getScanner() {
		return this.scanner;
	}
		
	/**
	 * Retourne le status de CombineGame.<br>
	 * True si CombineGame doit s'ex�cuter, False, si CombineGame doit s'arr�ter.
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
	 * Retourne null si aucun jeu n'a �t� choisi
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
	 * Permet de d�finir le scanner utilis� pour r�cup�rer les saisies de l'utilisateur
	 * 
	 * @param pScanner un scanner qui sera utilis� pour r�cup�rer les saisies de l'utilisateur
	 * 
	 * @see #getScanner()
	 */
	private void setScanner(Scanner pScanner) {
		this.scanner = pScanner;
	}
	
	/**
	 * Permet de d�finir l'affichage qui sera utilis� pour effectuer les affichages
	 * 
	 * @param pAffichage Affichage utilis� pour effectuer les affichages
	 * 
	 * @see #getAffichage()
	 */
	private void setAffichage(Affichage pAffichage) {
		this.affichage = pAffichage;
	}
	
	/**
	 * Permet de d�finir le jeu choisi par l'utilisateur
	 *  
	 * @param pJeuChoisi Jeu choisi par l'utilisateur
	 * 
	 * @see #getJeuChoisi()
	 */
	private void setJeuChoisi(NomsDeJeu pJeuChoisi) {
		this.jeuChoisi = pJeuChoisi;
	}
	
	/**
	 * Permet de d�finir le mode de jeu choisi par l'utilisateur
	 * 
	 * @param pModeChoisi Mode de jeu choisi par l'utilisateur
	 * 
	 * @see #getModeChoisi()
	 */
	private void setModeChoisi(ModesDeJeu pModeChoisi) {
		this.modeChoisi = pModeChoisi;
	}
	
	/**
	 * Permet de d�finir si CombineGame doit continuer � s'ex�cuter.
	 * 
	 * @param pExecuteCombineGame true si CombineGame continue ou false si CombineGame doit s'arr�ter
	 * 
	 * @see #getExecuteCombineGame()
	 */
	private void setExecuteCombineGame(boolean pExecuteCombineGame) {
		this.executeCombineGame = pExecuteCombineGame;
	}
	
	
//***** METHODES *****/
	
	/**
	 * Cette m�thode permet � l'utilisateur de choisir son jeu.<br>
	 * 
	 * Le choix du jeu se fait au travers d'un menu. <br>
	 * Le menu actualise la variable {@link #jeuChoisi}. Le choix de l'utilisateur est �galement renvoy� � l'issu de la m�thode sous forme d'un char en majuscule.<br>
	 * Les diff�rents retour possible sont :<br>
	 * <ul>
	 * <li>'1' pour le jeu Plus_Moins</li>
	 * <li>'2' pour le jeu MasterMind</li>
	 * <li>'Q' pour arr�ter CombineGame</li>
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
	 * Cette m�thode permet � l'utilisateur de choisir son mode jeu.<br>
	 * 
	 * Le choix du mode de jeu se fait au travers d'un menu. <br>
	 * Le menu actualise la variable {@link #modeChoisi}. Le choix de l'utilisateur est �galement renvoy� � l'issu de la m�thode sous forme d'un char en majuscule.<br>
	 * Les diff�rents retour possible sont :<br>
	 * <ul>
	 * <li>'1' pour le mode Challenger</li>
	 * <li>'2' pour le mode Defenseur</li>
	 * <li>'3' pour le mode Duel</li>
	 * <li>'R' pour revenir en arri�re</li>
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
	 * Cette m�thode permet � l'utilisateur d'indiquer ce qu'il souhaite faire � la fin de sa partie.<br>
	 * 
	 * Le choix de fin de partie se fait au travers d'un menu. <br>
	 * Le menu r�initialise � null la variable {@link #modeChoisi} et la variable {@link #jeuChoisi}. <br>
	 * Le choix de l'utilisateur est �galement renvoy� � l'issu de la m�thode sous forme d'un char en majuscule.<br>
	 * Les diff�rents retour possible sont :<br>
	 * <ul>
	 * <li>'1' pour rejouer au m�me jeu avec le m�me mode</li>
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
