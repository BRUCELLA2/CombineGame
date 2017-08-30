package affichage;

import java.util.ArrayList;
import java.util.ListIterator;

import games.constantes.ModesDeJeu;
import games.constantes.NavigationMenu;
import games.constantes.NomsDeJeu;
/**
 * La classe Affichage permet de gérer les affichages.<br>
 * <br>
 * Actuellement l'affichage se fait par des sorties sur console.
 * 
 * @author BRUCELLA2
 * @version 1.0
 *
 */
public class Affichage {
	
//***** CONSTRUCTEURS *****/
	
	/**
	 * Constructeur de la classe Affichage.<br>
	 * <br>
	 * A l'initialisation, affiche un message d'introduction de CombineGame ("Bienvenue dans CombineGame")
	 * 
	 */
	public Affichage() {
		
		System.out.println("Bienvenue dans CombineGame"); //$NON-NLS-1$
	}
	

//***** METHODES *****/
	
	/**
	 * AfficheMenuChoixJeu affiche le menu permettant le choix d'un jeu dans CombineGame<br>
	 * <br>
	 * Cette méthode ne fait que l'affichage, aucune récupération des données saisies par l'utilisateur n'est récupéré ici.
	 * <br>
	 * Note : Cette méthode pourrait être déclaré static mais afin de permettre plus plus de souplesse en cas de reworking du module d'affichage, ce ne sera pas le cas.
	 * 
	 */
	@SuppressWarnings("static-method")
	public void afficheMenuChoixJeu(){
		
		System.out.println("Choisir un jeu : \n"); //$NON-NLS-1$
		System.out.println("1 - " + NomsDeJeu.PLUS_MOINS.getNomJeu()); //$NON-NLS-1$
		System.out.println("2 - " + NomsDeJeu.MASTERMIND.getNomJeu()); //$NON-NLS-1$
		System.out.println("Q - " + NavigationMenu.QUITTER.getNomNavigation()); //$NON-NLS-1$
	}
	
	/**
	 * AfficheMenuModeJeu affiche le menu permettant le choix du mode de jeu<br>
	 * <br>
	 * Cette méthode ne fait que l'affichage, aucune récupération des données saisies par l'utilisateur n'est récupéré ici.
	 * <br>
	 * Note : Cette méthode pourrait être déclaré static mais afin de permettre plus plus de souplesse en cas de reworking du module d'affichage, ce ne sera pas le cas.
	 * 
	 */
	@SuppressWarnings("static-method")
	public void afficheMenuModeJeu() {
		System.out.println("Choisir un mode de jeu : \n"); //$NON-NLS-1$
		System.out.println("1 - " + ModesDeJeu.CHALLENGER.getNomMode()); //$NON-NLS-1$
		System.out.println("2 - " + ModesDeJeu.DEFENSEUR.getNomMode()); //$NON-NLS-1$
		System.out.println("3 - " + ModesDeJeu.DUEL.getNomMode()); //$NON-NLS-1$
		System.out.println("R - " + NavigationMenu.RETOUR.getNomNavigation()); //$NON-NLS-1$
	}
	
	/**
	 * afficheMenuFinJeu affiche le menu à la fin de la partie permettan à l'utilisateur d'indiquer ce qu'il souhaite faire ensuite<br>
	 * <br>
	 * Cette méthode ne fait que l'affichage, aucune récupération des données saisies par l'utilisateur n'est récupéré ici.
	 * <br>
	 * Note : Cette méthode pourrait être déclaré static mais afin de permettre plus plus de souplesse en cas de reworking du module d'affichage, ce ne sera pas le cas.
	 * 
	 */
	@SuppressWarnings("static-method")
	public void afficheMenuFinJeu() {
		System.out.println("Jeu terminé. Que souhaitez-vous faire : \n"); //$NON-NLS-1$
		System.out.println("1 - " + NavigationMenu.REJOUER.getNomNavigation()); //$NON-NLS-1$
		System.out.println("R - " + NavigationMenu.RETOUR.getNomNavigation()); //$NON-NLS-1$
		System.out.println("Q - " + NavigationMenu.QUITTER.getNomNavigation()); //$NON-NLS-1$
	}
	
	/**
	 * Permet d'afficher une String avec saut de ligne dans l'affichage.<br>
	 * <br>
	 * Note : Cette méthode pourrait être déclaré static mais afin de permettre plus plus de souplesse en cas de reworking du module d'affichage, ce ne sera pas le cas.
	 * 
	 * @param pString String à afficher
	 */
	@SuppressWarnings("static-method")
	public void afficheln(String pString) {
		System.out.println(pString);
	}
	
	/**
	 * Permet d'afficher une String sans saut de ligne dans l'affichage.<br>
	 * <br>
	 * Note : Cette méthode pourrait être déclaré static mais afin de permettre plus plus de souplesse en cas de reworking du module d'affichage, ce ne sera pas le cas.
	 *  
	 * @param pString String à afficher
	 */
	@SuppressWarnings("static-method")
	public void affiche(String pString) {
		System.out.print(pString);
	}
	
	/**
	 * Permet d'afficher le contenu d'un ArrayList d'entier sans saut de ligne dans l'affichage.<br>
	 * <br>
	 * Note : Cette méthode pourrait être déclaré static mais afin de permettre plus plus de souplesse en cas de reworking du module d'affichage, ce ne sera pas le cas.
	 * 
	 * @param pArrayList ArrayList d'entier à afficher
	 */
	@SuppressWarnings("static-method")
	public void affiche(ArrayList<Integer> pArrayList) {
		
		String str = ""; //$NON-NLS-1$
		ListIterator<Integer> li = pArrayList.listIterator();
		
		while(li.hasNext()) {
			str = str + li.next().toString();
		}
		
		System.out.print(str);
	}
	
	/**
	 * Permet d'afficher le contenu d'un ArrayList d'entier avec un saut de ligne dans l'affichage.<br>
	 * <br>
	 * Note : Cette méthode pourrait être déclaré static mais afin de permettre plus plus de souplesse en cas de reworking du module d'affichage, ce ne sera pas le cas.
	 * 
	 * @param pArrayList ArrayList d'entier à afficher
	 */
	@SuppressWarnings("static-method")
	public void Afficheln(ArrayList<Integer> pArrayList) {
		
		String str = ""; //$NON-NLS-1$
		ListIterator<Integer> li = pArrayList.listIterator();
		
		while(li.hasNext()) {
			str = str + li.next().toString();
		}
		
		System.out.println(str);
	}
}
