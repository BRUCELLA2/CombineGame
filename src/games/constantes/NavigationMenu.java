package games.constantes;

/**
 * Enumeration des diff�rentes navigations dans les menus de CombineGame
 * 
 * @author BRUCELLA2
 * @version 1.0
 */
public enum NavigationMenu {
	
	/**
	 * Navigation Quitter CombineGame<br>
	 * <br>
	 */
	QUITTER("Quitter CombineGame"), //$NON-NLS-1$
	
	/**
	 * Navigation Retour au choix du jeu
	 */
	RETOUR("Retour choix du jeu"), //$NON-NLS-1$
	/**
	 * Navigation Rejouer au m�me jeu et au m�me mode
	 */
	REJOUER("Rejouer au m�me jeu et au m�me mode"); //$NON-NLS-1$
	
//***** VARIABLES *****//
	/**
	 * Nom de la navigation
	 */
	String nomNavigation;

//***** CONSTRUCTEURS *****//
	/**
	 * Constructeur
	 * 
	 * @param pNomNavigation Nom de la navigation
	 */
	NavigationMenu(String pNomNavigation){
		this.setNomNavigation(pNomNavigation);
	}

//***** GETTERS *****//
	
	/**
	 * Retourne le nom de la navigation
	 * 
	 * @return Le nom de la navigation
	 */
	public String getNomNavigation() {
		return this.nomNavigation;
	}
	
//***** SETTERS *****//
	
	/**
	 * Permet de d�finir le nom de la navigation
	 * 
	 * @param pNomNavigation Le nom de la navigation
	 */
	private void setNomNavigation(String pNomNavigation) {
		this.nomNavigation = pNomNavigation;
	}
}
