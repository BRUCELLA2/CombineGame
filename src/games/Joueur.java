package games;

import java.util.ArrayList;
import java.util.Scanner;

import affichage.Affichage;
/**
 * <b>Cette classe repr�sente un joueur</b><br>
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
	 * affichage sert � l'affichage de diff�rents �lements li� au joueur
	 * 
	 * @see #getAffichage()
	 * @see #setAffichage(Affichage) 
	 */
	private Affichage affichage;
	
	
//***** CONSTRUCTEURS *****//
	
	/**
	 * Constructeur de la classe Joueur.<br>
	 * <br>
	 * Le nom et l'affichage sont initialis�s � partir des param�tres fournis au constructeur
	 * 
	 * @param pNomJoueur Le nom du joueur
	 * @param pAffichage L'affichage utilis� par le joueur
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
	 * Permet de d�finir le nom du joueur
	 * 
	 * @param pNomJoueur Nom du joueur
	 * 
	 * @see #getNomJoueur()
	 */
	public void setNomJoueur(String pNomJoueur) {
		this.nomJoueur = pNomJoueur;
	}
	
	/**
	 * Permet de d�finir l'affichage
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
	 * Cette m�thode permet d'obtenir un nombre sous forme d'ArrayList d'entier.<br>
	 * <br>
	 * Ce nomnbre un nombre limit� de chiffre (fourni en param�tres.<br>
	 * Cette m�thode est red�finie dans les diff�rentes classes filles.
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre retourn�
	 * @return Un nombre sous forme d'ArrayList d'entier
	 * 
	 * @see #recuperationNombre(int)
	 */
	public abstract ArrayList<Integer> donneNombre(int pNbChiffre);
	
	/**
	 * Cette m�thode permet de r�cup�rer un nombre saisi par l'utilisateur et le renvoi sous forme d'ArrayList d'entier.<br>
	 * <br>
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre retourn�
	 * @return Un nombre sous forme d'ArrayList d'entier
	 */
	protected ArrayList<Integer> recuperationNombre(int pNbChiffre){
		
		String str;
		ArrayList<Integer> nombre = new ArrayList<>();
		
		//TODO warning � prendre en compte
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
