package games.constantes;

/**
 * Enumération des noms de jeu de CombineGame
 * 
 * @author BRUCELLA2
 * @version 1.0
 */
public enum NomsDeJeu {
	
	/**
	 * Jeu du plus et du moins
	 */
	PLUS_MOINS("Jeu du plus et du moins"), //$NON-NLS-1$
	/**
	 * Jeu du mastermind
	 */
	MASTERMIND("Jeu du Mastermind"); //$NON-NLS-1$
	
//***** VARIABLES *****/
	
	/**
	 * Nom du jeu
	 */
	private String nomJeu;
	
//***** CONSTRUCTEURS *****/
	
	/**
	 * Constructeur
	 * 
	 * @param pNomJeu Le nom du jeu
	 */
	NomsDeJeu(String pNomJeu){
		this.setNomJeu(pNomJeu);
	}
	
//***** GETTER *****/
	
	/**
	 * Retourne le nom du jeu
	 * 
	 * @return Nom du jeu
	 */
	public String getNomJeu() {
		return this.nomJeu;
	}
	
	
//***** SETTER *****/
	
	/**
	 * Permet de définir le nom du jeu
	 * 
	 * @param pNomJeu Le nom du jeu
	 */
	private void setNomJeu(String pNomJeu) {
		this.nomJeu = pNomJeu;
	}
	
}
