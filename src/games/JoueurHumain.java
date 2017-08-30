package games;

import java.util.ArrayList;

import affichage.Affichage;

/**
 * <b>Cette classe représente un joueur humain</b>
 * 
 * @author BRUCELLA2
 *@version 1.0
 */
public class JoueurHumain extends Joueur {
	
//***** CONSTRUCTEURS *****//
	
	/**
	 * Constructeur de la classe JoueurHumain.
	 * 
	 * @param pNomJoueur Nom du joueur
	 * @param pAffichage Affichage utilisé par l'objet JoueurHumain
	 */
	public JoueurHumain(String pNomJoueur, Affichage pAffichage) {
		super(pNomJoueur, pAffichage);
	}
	
//***** METHODES *****//
	
	/**
	 * Cette méthode demande à l'utilisateur de fournir un nombre via l'affichage.<br>
	 * <br>
	 * Ce nombre récupéré sous forme de String est transformé en ArrayList d'entier. 
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre retourné
	 * @return Un nombre sous forme d'ArrayList d'entier
	 */
	@Override
	public ArrayList<Integer> donneNombre(int pNbChiffre) {
		
		ArrayList<Integer> nombre = new ArrayList<>();
		
		do {
			this.getAffichage().afficheln("Donnez le nombre mystère : "); //$NON-NLS-1$
			nombre = this.recuperationNombre(pNbChiffre);
		}
		while(nombre.isEmpty());
		
		return nombre;
	}
	
	/**
	 * Cette permet demande à l'utilisateur de fournir un nombre mystère (c'est à dire un nombre à deviner).<br>
	 * <br>
	 * Ce nombre récupéré sous forme de String est transformé en ArrayList d'entier. 
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre retourné
	 * @return Un nombre sous forme d'ArrayList d'entier
	 */
	public ArrayList<Integer> creerNombreMystere(int pNbChiffre){
		
		ArrayList<Integer> nombre = new ArrayList<>();
		
		do {
			this.getAffichage().afficheln("Donnez le nombre mystère : "); //$NON-NLS-1$
			nombre = this.recuperationNombre(pNbChiffre);
		}while(nombre.isEmpty());
		
		return nombre;	
	}
	
}
