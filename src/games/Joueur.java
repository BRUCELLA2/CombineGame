package games;

import java.util.ArrayList;
import java.util.Scanner;

import affichage.Affichage;
/**
 * <b>Cette classe représente un joueur</b><br>
 * <br>
 * 
 * @author BRUCELLA2
 * @version 1.0
 *
 */
public abstract class Joueur {
	
//***** VARIABLES *****//
	
	/**
	 * Nom du joueur
	 * 
	 * @see #getNomJoueur()
	 * @see #setNomJoueur(String)
	 */
	private String nomJoueur;
	
	/**
	 * affichage sert à l'affichage de différents élements lié au joueur
	 * 
	 * @see #getAffichage()
	 * @see #setAffichage(Affichage) 
	 */
	private Affichage affichage;
	
	
//***** CONSTRUCTEURS *****//
	
	/**
	 * Constructeur de la classe Joueur.<br>
	 * <br>
	 * Le nom et l'affichage sont initialisés à partir des paramètres fournis au constructeur
	 * 
	 * @param pNomJoueur Le nom du joueur
	 * @param pAffichage L'affichage utilisé par le joueur
	 */
	public Joueur(String pNomJoueur, Affichage pAffichage) {
		this.setNomJoueur(pNomJoueur);
		this.setAffichage(pAffichage);
	}
	
//***** GETTER *****//
	
	/**
	 * Retourne le nom du joueur sous forme de String
	 * 
	 * @return Le nom du joueur
	 * 
	 * @see #setNomJoueur(String)
	 */
	public String getNomJoueur() {
		return this.nomJoueur;
	}
	
	/**
	 * Retourne l'affichage
	 * 
	 * @return L'affichage
	 */
	public Affichage getAffichage() {
		return this.affichage;
	}
	
//***** SETTER *****//
	
	/**
	 * Permet de définir le nom du joueur
	 * 
	 * @param pNomJoueur Nom du joueur
	 * 
	 * @see #getNomJoueur()
	 */
	public void setNomJoueur(String pNomJoueur) {
		this.nomJoueur = pNomJoueur;
	}
	
	/**
	 * Permet de définir l'affichage
	 * 
	 * @param pAffichage Affichage
	 * 
	 * @see #getAffichage()
	 */
	public void setAffichage(Affichage pAffichage) {
		this.affichage = pAffichage;
	}
	
//***** METHODES *****//	
	
	/**
	 * Cette méthode permet d'obtenir un nombre sous forme d'ArrayList d'entier.<br>
	 * <br>
	 * Ce nomnbre un nombre limité de chiffre (fourni en paramètres.<br>
	 * Cette méthode est redéfinie dans les différentes classes filles.
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre retourné
	 * @return Un nombre sous forme d'ArrayList d'entier
	 * 
	 * @see #recuperationNombre(int)
	 */
	public abstract ArrayList<Integer> donneNombre(int pNbChiffre);
	
	/**
	 * Cette méthode permet de récupérer un nombre saisi par l'utilisateur et le renvoi sous forme d'ArrayList d'entier.<br>
	 * <br>
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre retourné
	 * @return Un nombre sous forme d'ArrayList d'entier
	 */
	protected ArrayList<Integer> recuperationNombre(int pNbChiffre){
		
		String str;
		ArrayList<Integer> nombre = new ArrayList<>();
		
		//TODO warning à prendre en compte
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		str = scan.nextLine();
			
		if (str.length() == pNbChiffre) {
			for(int i = 0; i < pNbChiffre; i++) {
				if(Character.isDigit(str.charAt(i))) {
					nombre.add(new Integer(Character.getNumericValue(str.charAt(i))));
				}
				else {
					nombre.clear();
					return nombre;
				}
			}
		}
		else {
			nombre.clear();
			return nombre;
		}
		
		return nombre;
	}
}
