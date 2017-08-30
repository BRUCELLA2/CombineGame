package games;

import java.util.ArrayList;

import affichage.Affichage;

/**
 * <b>Cette classe repr�sente un joueur humain</b>
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
	 * @param pAffichage Affichage utilis� par l'objet JoueurHumain
	 */
	public JoueurHumain(String pNomJoueur, Affichage pAffichage) {
		super(pNomJoueur, pAffichage);
	}
	
//***** METHODES *****//
	
	/**
	 * Cette m�thode demande � l'utilisateur de fournir un nombre via l'affichage.<br>
	 * <br>
	 * Ce nombre r�cup�r� sous forme de String est transform� en ArrayList d'entier. 
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre retourn�
	 * @return Un nombre sous forme d'ArrayList d'entier
	 */
	@Override
	public ArrayList<Integer> donneNombre(int pNbChiffre) {
		
		ArrayList<Integer> nombre = new ArrayList<>();
		
		do {
			this.getAffichage().afficheln("Donnez le nombre myst�re : "); //$NON-NLS-1$
			nombre = this.recuperationNombre(pNbChiffre);
		}
		while(nombre.isEmpty());
		
		return nombre;
	}
	
	/**
	 * Cette permet demande � l'utilisateur de fournir un nombre myst�re (c'est � dire un nombre � deviner).<br>
	 * <br>
	 * Ce nombre r�cup�r� sous forme de String est transform� en ArrayList d'entier. 
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre retourn�
	 * @return Un nombre sous forme d'ArrayList d'entier
	 */
	public ArrayList<Integer> creerNombreMystere(int pNbChiffre){
		
		ArrayList<Integer> nombre = new ArrayList<>();
		
		do {
			this.getAffichage().afficheln("Donnez le nombre myst�re : "); //$NON-NLS-1$
			nombre = this.recuperationNombre(pNbChiffre);
		}while(nombre.isEmpty());
		
		return nombre;	
	}
	
}
