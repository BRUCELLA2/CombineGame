package games;

import java.util.ArrayList;

import affichage.Affichage;
import games.constantes.ModesDeJeu;
import games.constantes.NomsDeJeu;


/**
 * /**
 * <b>Game est la classe repr�sentant un jeu.</b><br>
 * <br>
 * Les jeux repr�sent�s par la classe Game sont des jeux o� il faut d�couvrir soit un nombre (Jeu du plus moins) soit une combinaison de nombre ou de couleur (mastermind)<br>
 * <br> 
 * Un Jeu est initilis� en d�butant avec le mode de jeu fourni en param�tre du constructeur (appel des m�thodes play {@link #playChallenger()} {@link #playDefenser()} {@link #playDuel()})<br>
 * Un jeu peut g�n�rer un nombre myst�re {@link #genereNombreMystere()}<br>
 * Un jeu peut comparer une proposition fournie avec le nombre myst�re {@link #compareNombre(ArrayList)}<br>
 * Un jeu peut initier un message de victoire ou de d�faite {@link #victoire()} {@link #defaite()}<br>
 * 
 * 
 * @author BRUCELLA2
 * @version 1.0
 * 
 */
public abstract class Game {
	
//***** VARIABLES *****/
	
	/**
	 * Nom du jeu<br>
	 * <br>
	 * Cette variable est initialis�e par le constructeur de la classe.
	 * 
	 * @see #getNomDeJeu()
	 * @see #setNomDeJeu(NomsDeJeu)
	 * 
	 */
	//TODO a rendre final si possible
	NomsDeJeu nomDeJeu;
	
	/**
	 * Mode du jeu<br>
	 * <br>
	 * Cette variable est initialis�e par un param�tre fourni au constructeur de la classe.<br>
	 * Cette variable permet de d�finir comment le jeu se d�roulera. 
	 * 
	 * @see #getModeDeJeu()
	 * @see #setModeDeJeu(ModesDeJeu)
	 * 
	 */
	//TODO a rendre final si possible
	ModesDeJeu modeDeJeu;
	
	/**
	 * Nombre Mystere � d�couvrir<br>
	 * <br>
	 * Chaque chiffre constituant le nombre est contenu dans un ArrayList d'entier.<br>
	 * Le nombre myst�re est g�n�r� al�atoirement {@link #genereNombreMystere()} ou � partir d'une donn�e saisie par l'utilisateur
	 * 
	 * @see #getNombreMystere()
	 * @see #setNombreMystere(ArrayList)
	 * @see #genereNombreMystere()
	 * 
	 */
	//TODO A supprimer ? � transformer en liste d'ArrayList pour g�rer le mode duel ? A dupliquer pour g�rer 2 nombres myst�res ?
	ArrayList<Integer> nombreMystere = new ArrayList<>();
	
	/**
	 * Nombre de chiffres constituant le nombre myst�re<br>
	 *
	 * @see #getNbChiffresMysteres()
	 * @see #setNbChiffresMysteres(int)
	 */
	//TODO a rendre final si possible
	int nbChiffresMysteres = 4;
	
	/**
	 * Nombre de tentatives restant pour finir la partie<br>
	 * <br>
	 * Cette variable est initialis�e au niveau du constructeur.<br>
	 * Quand nbCoups arrive � 0, la partie doit prendre fin. //TODO � int�grer dans le SETTER
	 * 
	 * @see #getNbCoups()
	 * @see #setNbCoups(int)
	 */
	int nbCoups;
	
	/**
	 * Liste des joueurs<br>
	 * <br>
	 * Les joueurs sont stock�s dans un ArrayList de joueur.<br>
	 * Cette liste est vide � la cr�ation du jeu. Les joueurs sont ajout�s lorsque le jeu d�bute (m�thodes play)<br>
	 * <br>
	 * (actuellement pas utilis�, les joueurs sont cr��s et utilis�s localement)
	 *
	 * @see #getJoueurs()
	 * @see #setJoueurs(ArrayList)
	 * 
	 */
	// TODO a voir si utile
	ArrayList<Joueur> joueurs = new ArrayList<>();
	
	/**
	 * affichage sert � l'affichage des diff�rents �lements du jeu
	 * 
	 * @see #getAffichage()
	 * @see #setAffichage(Affichage)
	 * 
	 */
	Affichage affichage;
	
	/**
	 * finPartie indique si la partie doit prendre fin.
	 * 
	 * @see #isFinPartie()
	 * @see #setFinPartie(boolean)
	 * 
	 */
	boolean finPartie;
	
	
//***** CONSTRUCTEURS *****/	
	
	/**
	 * Constructeur de Game<br>
	 * <br>
	 * Le constructeur initialise les diff�rentes variables et d�bute le jeu avec le mode souhait�.
	 * 
	 * 
	 * @param pModeDeJeu mode de jeu qui doit �tre utilis�
	 * @param pAffichage affichage qui sera utilis� pour proc�der aux affichages du jeu
	 * 
	 * @see #playChallenger()
	 * @see #playDefenser()
	 * @see #playDuel()
	 */
	protected Game(ModesDeJeu pModeDeJeu, Affichage pAffichage) {
		this.setAffichage(pAffichage);
		this.setFinPartie(false);
		this.setModeDeJeu(pModeDeJeu);
		this.setNbCoups(4);
		
		if(this.getModeDeJeu() == ModesDeJeu.CHALLENGER) {
			this.playChallenger();
		}
		else if(this.getModeDeJeu() == ModesDeJeu.DEFENSEUR) {
			this.playDefenser();
		}
		else{
			this.playDuel();
		}
	}
	
	
//***** GETTERS *****/
	
	/**
	 * Retourne le nom du jeu
	 * 
	 * @return Nom du jeu
	 * 
	 * @see #setNomDeJeu(NomsDeJeu)
	 */
	public NomsDeJeu getNomDeJeu() {
		return this.nomDeJeu;
	}
	
	/**
	 * Retourne le mode du jeu
	 * 
	 * @return Mode de jeu
	 * 
	 * @see #setModeDeJeu(ModesDeJeu)
	 */
	public ModesDeJeu getModeDeJeu() {
		return this.modeDeJeu;
	}
	
	
	/**
	 * Retourne le nombre myst�re sous forme d'ArrayList d'entiers
	 * 
	 * @return nombre mystere
	 * 
	 * @see #setNombreMystere(ArrayList)
	 * @see #genereNombreMystere()
	 */
	public ArrayList<Integer> getNombreMystere(){
		return this.nombreMystere;
	}
	
	/**
	 * Retourne le nombre de chiffres myst�res � d�couvrir.<br>
	 * <br>
	 * Ce nombre de chiffres est param�tr� au lancement du jeu.
	 * 
	 * @return Le nombre de chiffres myst�res � d�couvrir.
	 * 
	 * @see #setNbChiffresMysteres(int)
	 */
	public int getNbChiffresMysteres() {
		return this.nbChiffresMysteres;
	}
	
	/**
	 * Retourne le nombre de coups possible avant la fin de partie<br>
	 * <br>
	 * Ce nombre de coups est param�tr� au lancement du jeu.
	 * 
	 * @return Le nombre de coups avant la fin de partie
	 * 
	 * @see #setNbCoups(int)
	 */
	public int getNbCoups() {
		return this.nbCoups;
	}
	
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
	 * Retourne la liste des joueurs sous forme d'ArrayList
	 * 
	 * @return La liste des joueurs
	 * 
	 * @see #setJoueurs(ArrayList)
	 */
	public ArrayList<Joueur> getJoueurs(){
		return this.joueurs;
	}
	
	/**
	 * Retourne si la partie doit se terminer.
	 * 
	 * @return Si la partie doit se terminer
	 * 
	 * @see #setFinPartie(boolean)
	 */
	public boolean isFinPartie() {
		return this.finPartie;
	}
	
//***** SETTERS *****/
	
	/**
	 * Permet de d�finir le nom du jeu.<br>
	 * 
	 * @param pNomDeJeu Le nom du jeu
	 * 
	 * @see #getNomDeJeu()
	 */
	public void setNomDeJeu(NomsDeJeu pNomDeJeu) {
		this.nomDeJeu = pNomDeJeu;
	}
	
	/**
	 * Permet de d�finir le mode du jeu.
	 * 
	 * @param pModeDeJeu Le mode du jeu
	 * 
	 * @see #getModeDeJeu()
	 */
	public void setModeDeJeu(ModesDeJeu pModeDeJeu) {
		this.modeDeJeu = pModeDeJeu;
	}
	
	/**
	 * Permet de d�finir le nombre myst�re au moyen d'un ArrayList d'entiers.
	 * 
	 * @param pNombreMystere Nombre myst�re
	 * 
	 * @see #getNombreMystere()
	 */
	public void setNombreMystere(ArrayList<Integer> pNombreMystere) {
		this.nombreMystere = pNombreMystere;
	}
	
	/**
	 * Permet de d�finir le nombre de chiffres constituant le nombre myst�re.
	 * 
	 * @param pNbChiffresMysteres Nombre de chiffres constituant le nombre myst�re.
	 * 
	 * @see #getNbChiffresMysteres()
	 */
	public void setNbChiffresMysteres(int pNbChiffresMysteres) {
		this.nbChiffresMysteres = pNbChiffresMysteres;
	}
	
	/**
	 * Permet de d�finir le nombre de coups possible (ou restant) avant la fin de la partie
	 * 
	 * @param pNbCoups Nombre de coups avant la fin de partie
	 * 
	 * @see #getNbCoups()
	 */
	public void setNbCoups(int pNbCoups) {
		this.nbCoups = pNbCoups;
	}
	
	/**
	 * Permet de d�finir l'affichage qui sera utilis� pour effectuer les affichages
	 * 
	 * @param pAffichage Affichage utilis� pour effectuer les affichages
	 * 
	 * @see #getAffichage()
	 */
	public void setAffichage(Affichage pAffichage) {
		this.affichage = pAffichage;
	}
	
	/**
	 * Permet de d�finir la liste des joueurs � partir d'un ArrayList de joueurs
	 * 
	 * @param pJoueurs liste des joueurs
	 * 
	 * @see #getJoueurs()
	 */
	public void setJoueurs(ArrayList<Joueur> pJoueurs) {
		this.joueurs = pJoueurs;
	}
	
	/**
	 * Permet de d�finir si la partie doit se terminer.
	 * 
	 * @param pFinPartie Indique si la partie doit se terminer.
	 * 
	 * @see #isFinPartie()
	 */
	public void setFinPartie(boolean pFinPartie) {
		this.finPartie = pFinPartie;
	}
	
//***** METHODES *****/
	
	/**
	 * Cette m�thode permet de g�n�rer un nombre myst�re.<br>
	 * <br>
	 * Cette m�thode prend en compte le nombre de chiffres myst�re constituant le nombre myst�re.<br>
	 * Les chiffres g�n�r�s sont des entiers compris entre 0 et 9.<br>
	 * 
	 */
	public void genereNombreMystere(){
		
		for(int i=0; i < this.getNbChiffresMysteres(); i++) {
			this.getNombreMystere().add(new Integer((int) ((9-0)*Math.random())));
		}
	}
	
	/**
	 * Cette m�thode permet de mettre un terme � la partie en indiquant que le joueur a perdu.<br>
	 * Le message est "Dommage ! Vous avez perdu" suivi de la bonne r�ponse sauf si le mode de jeu est le duel.
	 * 
	 */
	protected void defaite() {
		
		this.getAffichage().afficheln("\n Dommage ! Vous avez perdu"); //$NON-NLS-1$

		if(this.getModeDeJeu() != ModesDeJeu.DEFENSEUR) {
			this.getAffichage().affiche("La bonne r�ponse �tait : "); //$NON-NLS-1$
			// TODO ajouter la fonction afficheln(ArrayList<Integer>)
			this.getAffichage().affiche(this.getNombreMystere());
			this.getAffichage().afficheln(""); //$NON-NLS-1$
		}

		this.setFinPartie(true);
	}
	
	/**
	 * Cette m�thode permet de mettre un terme � la partie en indiquant que le joueur a gagn�.<br>
	 * Le message est "Bravo ! Vous avez gagn�"
	 * 
	 */
	protected void victoire() {

		this.getAffichage().afficheln("\n Bravo ! Vous avez gagn�"); //$NON-NLS-1$
		this.setFinPartie(true);
	}
	
	/**
	 * Cette m�thode permet de mettre un terme � la partie en indiquant une �galit� entre le joueur et l'ordinateur.<br>
	 * Le message est "Personne n'a gagn�"
	 * 
	 */
	protected void egalite() {
		
		this.getAffichage().afficheln("\n Personne n'a gagn�"); //$NON-NLS-1$
		this.setFinPartie(true);
	}
	
	/**
	 * Cette m�thode permet de comparer le nombre proposer en param�tre au nombre myst�re.<br>
	 * <br>
	 * Cette m�thode est red�finie dans les diff�rentes classes filles.
	 * 
	 * @param pNombreAComparer Le nombre � comparer au nombre myst�re. 
	 * @return Le r�sultat de la comparaison sous la forme d'une String.
	 */
	public abstract String compareNombre(ArrayList<Integer> pNombreAComparer);
	
	/**
	 * Cette m�thode d�marre le jeu en mode Challenger.<br>
	 * <br>
	 * Cette m�thode est red�finie dans les classes filles
	 */
	public abstract void playChallenger();
	
	/**
	 * Cette m�thode d�marre le jeu en mode D�fenseur<br>
	 * <br>
	 * Cette m�thode est red�finie dans les classes filles
	 */
	public abstract void playDefenser();
	
	/**
	 * Cette m�thode d�marre le jeu en mode Duel<br>
	 * <br>
	 * Cette m�thode est red�finie dans les classes filles
	 */
	public abstract void playDuel();
	
}
