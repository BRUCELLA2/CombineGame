package games;

import java.util.ArrayList;

import affichage.Affichage;
import games.constantes.ModesDeJeu;
import games.constantes.NomsDeJeu;


/**
 * /**
 * <b>Game est la classe représentant un jeu.</b><br>
 * <br>
 * Les jeux représentés par la classe Game sont des jeux où il faut découvrir soit un nombre (Jeu du plus moins) soit une combinaison de nombre ou de couleur (mastermind)<br>
 * <br> 
 * Un Jeu est initilisé en débutant avec le mode de jeu fourni en paramètre du constructeur (appel des méthodes play {@link #playChallenger()} {@link #playDefenser()} {@link #playDuel()})<br>
 * Un jeu peut générer un nombre mystère {@link #genereNombreMystere()}<br>
 * Un jeu peut comparer une proposition fournie avec le nombre mystère {@link #compareNombre(ArrayList)}<br>
 * Un jeu peut initier un message de victoire ou de défaite {@link #victoire()} {@link #defaite()}<br>
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
	 * Cette variable est initialisée par le constructeur de la classe.
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
	 * Cette variable est initialisée par un paramètre fourni au constructeur de la classe.<br>
	 * Cette variable permet de définir comment le jeu se déroulera. 
	 * 
	 * @see #getModeDeJeu()
	 * @see #setModeDeJeu(ModesDeJeu)
	 * 
	 */
	//TODO a rendre final si possible
	ModesDeJeu modeDeJeu;
	
	/**
	 * Nombre Mystere à découvrir<br>
	 * <br>
	 * Chaque chiffre constituant le nombre est contenu dans un ArrayList d'entier.<br>
	 * Le nombre mystère est généré aléatoirement {@link #genereNombreMystere()} ou à partir d'une donnée saisie par l'utilisateur
	 * 
	 * @see #getNombreMystere()
	 * @see #setNombreMystere(ArrayList)
	 * @see #genereNombreMystere()
	 * 
	 */
	//TODO A supprimer ? à transformer en liste d'ArrayList pour gérer le mode duel ? A dupliquer pour gérer 2 nombres mystères ?
	ArrayList<Integer> nombreMystere = new ArrayList<>();
	
	/**
	 * Nombre de chiffres constituant le nombre mystère<br>
	 *
	 * @see #getNbChiffresMysteres()
	 * @see #setNbChiffresMysteres(int)
	 */
	//TODO a rendre final si possible
	int nbChiffresMysteres = 4;
	
	/**
	 * Nombre de tentatives restant pour finir la partie<br>
	 * <br>
	 * Cette variable est initialisée au niveau du constructeur.<br>
	 * Quand nbCoups arrive à 0, la partie doit prendre fin. //TODO à intégrer dans le SETTER
	 * 
	 * @see #getNbCoups()
	 * @see #setNbCoups(int)
	 */
	int nbCoups;
	
	/**
	 * Liste des joueurs<br>
	 * <br>
	 * Les joueurs sont stockés dans un ArrayList de joueur.<br>
	 * Cette liste est vide à la création du jeu. Les joueurs sont ajoutés lorsque le jeu débute (méthodes play)<br>
	 * <br>
	 * (actuellement pas utilisé, les joueurs sont créés et utilisés localement)
	 *
	 * @see #getJoueurs()
	 * @see #setJoueurs(ArrayList)
	 * 
	 */
	// TODO a voir si utile
	ArrayList<Joueur> joueurs = new ArrayList<>();
	
	/**
	 * affichage sert à l'affichage des différents élements du jeu
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
	 * Le constructeur initialise les différentes variables et débute le jeu avec le mode souhaité.
	 * 
	 * 
	 * @param pModeDeJeu mode de jeu qui doit être utilisé
	 * @param pAffichage affichage qui sera utilisé pour procéder aux affichages du jeu
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
	 * Retourne le nombre mystère sous forme d'ArrayList d'entiers
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
	 * Retourne le nombre de chiffres mystères à découvrir.<br>
	 * <br>
	 * Ce nombre de chiffres est paramétré au lancement du jeu.
	 * 
	 * @return Le nombre de chiffres mystères à découvrir.
	 * 
	 * @see #setNbChiffresMysteres(int)
	 */
	public int getNbChiffresMysteres() {
		return this.nbChiffresMysteres;
	}
	
	/**
	 * Retourne le nombre de coups possible avant la fin de partie<br>
	 * <br>
	 * Ce nombre de coups est paramétré au lancement du jeu.
	 * 
	 * @return Le nombre de coups avant la fin de partie
	 * 
	 * @see #setNbCoups(int)
	 */
	public int getNbCoups() {
		return this.nbCoups;
	}
	
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
	 * Permet de définir le nom du jeu.<br>
	 * 
	 * @param pNomDeJeu Le nom du jeu
	 * 
	 * @see #getNomDeJeu()
	 */
	public void setNomDeJeu(NomsDeJeu pNomDeJeu) {
		this.nomDeJeu = pNomDeJeu;
	}
	
	/**
	 * Permet de définir le mode du jeu.
	 * 
	 * @param pModeDeJeu Le mode du jeu
	 * 
	 * @see #getModeDeJeu()
	 */
	public void setModeDeJeu(ModesDeJeu pModeDeJeu) {
		this.modeDeJeu = pModeDeJeu;
	}
	
	/**
	 * Permet de définir le nombre mystère au moyen d'un ArrayList d'entiers.
	 * 
	 * @param pNombreMystere Nombre mystère
	 * 
	 * @see #getNombreMystere()
	 */
	public void setNombreMystere(ArrayList<Integer> pNombreMystere) {
		this.nombreMystere = pNombreMystere;
	}
	
	/**
	 * Permet de définir le nombre de chiffres constituant le nombre mystère.
	 * 
	 * @param pNbChiffresMysteres Nombre de chiffres constituant le nombre mystère.
	 * 
	 * @see #getNbChiffresMysteres()
	 */
	public void setNbChiffresMysteres(int pNbChiffresMysteres) {
		this.nbChiffresMysteres = pNbChiffresMysteres;
	}
	
	/**
	 * Permet de définir le nombre de coups possible (ou restant) avant la fin de la partie
	 * 
	 * @param pNbCoups Nombre de coups avant la fin de partie
	 * 
	 * @see #getNbCoups()
	 */
	public void setNbCoups(int pNbCoups) {
		this.nbCoups = pNbCoups;
	}
	
	/**
	 * Permet de définir l'affichage qui sera utilisé pour effectuer les affichages
	 * 
	 * @param pAffichage Affichage utilisé pour effectuer les affichages
	 * 
	 * @see #getAffichage()
	 */
	public void setAffichage(Affichage pAffichage) {
		this.affichage = pAffichage;
	}
	
	/**
	 * Permet de définir la liste des joueurs à partir d'un ArrayList de joueurs
	 * 
	 * @param pJoueurs liste des joueurs
	 * 
	 * @see #getJoueurs()
	 */
	public void setJoueurs(ArrayList<Joueur> pJoueurs) {
		this.joueurs = pJoueurs;
	}
	
	/**
	 * Permet de définir si la partie doit se terminer.
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
	 * Cette méthode permet de générer un nombre mystère.<br>
	 * <br>
	 * Cette méthode prend en compte le nombre de chiffres mystère constituant le nombre mystère.<br>
	 * Les chiffres générés sont des entiers compris entre 0 et 9.<br>
	 * 
	 */
	public void genereNombreMystere(){
		
		for(int i=0; i < this.getNbChiffresMysteres(); i++) {
			this.getNombreMystere().add(new Integer((int) ((9-0)*Math.random())));
		}
	}
	
	/**
	 * Cette méthode permet de mettre un terme à la partie en indiquant que le joueur a perdu.<br>
	 * Le message est "Dommage ! Vous avez perdu" suivi de la bonne réponse sauf si le mode de jeu est le duel.
	 * 
	 */
	protected void defaite() {
		
		this.getAffichage().afficheln("\n Dommage ! Vous avez perdu"); //$NON-NLS-1$

		if(this.getModeDeJeu() != ModesDeJeu.DEFENSEUR) {
			this.getAffichage().affiche("La bonne réponse était : "); //$NON-NLS-1$
			// TODO ajouter la fonction afficheln(ArrayList<Integer>)
			this.getAffichage().affiche(this.getNombreMystere());
			this.getAffichage().afficheln(""); //$NON-NLS-1$
		}

		this.setFinPartie(true);
	}
	
	/**
	 * Cette méthode permet de mettre un terme à la partie en indiquant que le joueur a gagné.<br>
	 * Le message est "Bravo ! Vous avez gagné"
	 * 
	 */
	protected void victoire() {

		this.getAffichage().afficheln("\n Bravo ! Vous avez gagné"); //$NON-NLS-1$
		this.setFinPartie(true);
	}
	
	/**
	 * Cette méthode permet de mettre un terme à la partie en indiquant une égalité entre le joueur et l'ordinateur.<br>
	 * Le message est "Personne n'a gagné"
	 * 
	 */
	protected void egalite() {
		
		this.getAffichage().afficheln("\n Personne n'a gagné"); //$NON-NLS-1$
		this.setFinPartie(true);
	}
	
	/**
	 * Cette méthode permet de comparer le nombre proposer en paramètre au nombre mystère.<br>
	 * <br>
	 * Cette méthode est redéfinie dans les différentes classes filles.
	 * 
	 * @param pNombreAComparer Le nombre à comparer au nombre mystère. 
	 * @return Le résultat de la comparaison sous la forme d'une String.
	 */
	public abstract String compareNombre(ArrayList<Integer> pNombreAComparer);
	
	/**
	 * Cette méthode démarre le jeu en mode Challenger.<br>
	 * <br>
	 * Cette méthode est redéfinie dans les classes filles
	 */
	public abstract void playChallenger();
	
	/**
	 * Cette méthode démarre le jeu en mode Défenseur<br>
	 * <br>
	 * Cette méthode est redéfinie dans les classes filles
	 */
	public abstract void playDefenser();
	
	/**
	 * Cette méthode démarre le jeu en mode Duel<br>
	 * <br>
	 * Cette méthode est redéfinie dans les classes filles
	 */
	public abstract void playDuel();
	
}
