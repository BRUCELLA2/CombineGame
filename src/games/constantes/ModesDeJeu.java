package games.constantes;

/**
 * Enum�ration des diff�rents mode de jeu disponible pour CombineGame
 * 
 * @author BRUCELLA2
 * @version 1.0
 */
public enum ModesDeJeu {
	/**
	 * Mode Challenger<br>
	 * <br>
	 * Dans ce mode le joueur fait des propositions seule.
	 */
	CHALLENGER("Mode Challenger"), //$NON-NLS-1$
	
	/**
	 * Mode d�fenseur<br>
	 * <br>
	 * Dans ce mode, l'ordinateur fait des propositions
	 */
	DEFENSEUR("Mode D�fenseur"), //$NON-NLS-1$
	
	/**
	 * Mode Duel<br>
	 * <br>
	 * Dans ce mode, le joueur affronte l'ordinateur.
	 */
	DUEL("Mode Duel"); //$NON-NLS-1$
	
//***** VARIABLES *****/
	
	/**
	 * Nom du mode de jeu
	 */
	private String nomMode;
	
	
//***** CONSTRUCTEURS *****/
	
	/**
	 * Constructeur
	 * 
	 * @param pNomMode Nom du mode de jeu
	 */
	ModesDeJeu(String pNomMode){
		this.setNomMode(pNomMode);
	}
	
//***** GETTER *****/
	
	/**
	 * Renvoie le nom du mode de jeu
	 * 
	 * @return le nom du mode
	 */
	public String getNomMode() {
		return this.nomMode;
	}
	
//***** SETTERS *****/
	
	/**
	 * Permet de d�finir le nom du mode de jeu
	 * 
	 * @param pNomMode Le nom du mode de jeu
	 */
	private void setNomMode(String pNomMode) {
		this.nomMode = pNomMode;
	}
	
}
