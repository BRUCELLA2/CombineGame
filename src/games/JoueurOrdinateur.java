package games;

import java.util.ArrayList;

import affichage.Affichage;

/**
 * <b>Cette classe représente un joueur simulé par l'ordinateur.</b><br>
 * 
 * @author BRUCELLA2
 * @version 1.0
 *
 */
public class JoueurOrdinateur extends Joueur{
	
//***** VARIABLES *****/
	
	/**
	 * Liste des nombres proposés par le JoueurOrdinateur sous la forme d'une ArrayList.<br>
	 * <br>
	 * Les éléments de la liste sont des ArrayList d'entier.
	 * 
	 * @see #getListNumberProposed()
	 * @see #setListNumberProposed(ArrayList)
	 */
	private ArrayList<ArrayList<Integer>> listNumberProposed = new ArrayList<>();
	
	/**
	 * Liste des résultats obtenus par comparaison des nombres proposés et des nombres mystères.<br>
	 * <br>
	 * Les éléments de la liste sont des String (issue normalement de la comparaison. 
	 * 
	 * @see #getAffichage()
	 * @see #setAffichage(Affichage)
	 */
	private ArrayList<String> listResultats = new ArrayList<>();
	
	/**
	 * Liste les maximums pour chaque chiffre constituant le nombre mystère<br>
	 * 
	 * @see #getValeurMax()
	 * @see #setValeurMax(ArrayList)
	 * 
	 */
	private ArrayList<Integer> valeurMax = new ArrayList<>();
	
	/**
	 * Liste les minimums pour chaque chiffre constituant le nombre mystère<br>
	 * 
	 * @see #getValeurMin()
	 * @see #setValeurMin(ArrayList)
	 * 
	 */
	private ArrayList<Integer> valeurMin = new ArrayList<>();
	
//***** CONSTRUCTEURS *****/
	
	/**
	 * 	Constructeur de la classe JoueurOrdinateur.
	 * 
	 * @param pNomJoueur Nom du JoueurOrdinateur
	 * @param pAffichage Affichage utilisé par l'objet JoueurOrdinateur
	 */
	//TODO mettre en constante globale le nb de chiffres mystères
	public JoueurOrdinateur(String pNomJoueur, Affichage pAffichage, int pNbChiffresMysteres) {
		
		super(pNomJoueur, pAffichage);
		
		for(int i=0; i < pNbChiffresMysteres; i++) {
			this.getValeurMax().add(9);
			this.getValeurMin().add(0);
		}
	}

//***** GETTERS *****/
	
	/**
	 * Retourne la liste des nombres proposés.
	 * 
	 * @return La liste des nombres proposés.
	 * 
	 * @see #setListNumberProposed(ArrayList)
	 * @see #addDerniereProposition(ArrayList)
	 */
	public ArrayList<ArrayList<Integer>> getListNumberProposed(){
		return this.listNumberProposed;
	}
	
	/**
	 * Retourne la liste des résultats proposés obtenus par comparaison des nombres proposés et des nombres mystères.<br>
	 * <br>
	 * Les éléments de la liste sont sous la forme d'ArrayList d'entier.
	 * 
	 * @return La liste des nombres proposés.
	 * 
	 * @see #setListNumberProposed(ArrayList)
	 * @see #addResultat(String)
	 */
	public ArrayList<String> getListResultats(){
		return this.listResultats;
	}
	
	/**
	 * Retourne la liste des valeurs maximum des chiffres constituants le nombre mystère
	 * 
	 * @return la liste des valeurs maximum des chiffres constituants le nombre mystère
	 * 
	 * @see #setValeurMax(ArrayList)
	 */
	public ArrayList<Integer> getValeurMax(){
		return this.valeurMax;
	}
	
	/**
	 * Retourne la liste des valeurs minimums des chiffres constituants le nombre mystère
	 * 
	 * @return la liste des valeurs minimums des chiffres constituants le nombre mystère
	 * 
	 * @see #setValeurMin(ArrayList)
	 */
	public ArrayList<Integer> getValeurMin(){
		return this.valeurMin;
	}
//***** SETTERS *****/
	
	/**
	 * Permet de définir la liste des nombres déjà proposés.<br>
	 * <br>
	 * Pour ajouter un élément à la liste il faut utiliser {@link #addDerniereProposition(ArrayList)}
	 * 
	 * @param pListNumberProposed Liste des nombres déjà proposés
	 * 
	 * @see #getListNumberProposed()
	 * @see #addDerniereProposition(ArrayList)
	 */
	public void setListNumberProposed(ArrayList<ArrayList<Integer>> pListNumberProposed) {
		this.listNumberProposed = pListNumberProposed;
	}
	
	/**
	 * Permet de définir la liste des résultats obtenu par comparaison du nombre proposé et le nombre mystère.<br>
	 * <br>
	 * Pour ajouter un élément à la liste il faut utiliser {@link #addResultat(String)}
	 * 
	 * @param pListResultats Liste des résultats obtenus
	 * 
	 * @see #getListResultats()
	 * @see #addResultat(String)
	 */
	public void setListResultats(ArrayList<String> pListResultats) {
		this.listResultats = pListResultats;
	}
	
	/**
	 * Permet de définir la liste des valeurs maximales pour les chiffres constituant le nombre mystère
	 * 
	 * @param pValeurMax Liste des valeurs maximales pour les chiffres constituant le nombre mystère
	 * 
	 * @see #getValeurMax()
	 */
	public void setValeurMax(ArrayList<Integer> pValeurMax) {
		this.valeurMax = pValeurMax;
	}
	
	/**
	 * Permet de définir la liste des valeurs minimales pour les chiffres constituant le nombre mystère
	 * 
	 * @param pValeurMin Liste des valeurs minimales pour les chiffres constituant le nombre mystère
	 * 
	 * @see #getValeurMin()
	 */
	public void setValeurMin(ArrayList<Integer> pValeurMin) {
		this.valeurMin = pValeurMin;
	}
	
//***** METHODES *****/
	
	/**
	 * Cette méthode permet au JoueurOrdinateur de fournir un nombre sous forme d'ArrayList d'entier.<br>
	 * <br>
	 * Ce nombre est ajouté à la liste des nombres proposés.<br>
	 * 
	 * @param pNbChiffre Nombre de chiffre constituant le nombre qui sera renvoyé.
	 * 
	 */
	@Override
	public ArrayList<Integer> donneNombre(int pNbChiffre) {
		
		ArrayList<Integer> nombre = new ArrayList<>();
		ArrayList<Integer> derniereProposition;
		if(!this.getListNumberProposed().isEmpty()) {
			derniereProposition = this.getListNumberProposed().get(this.getListNumberProposed().size()-1);
		}
		else {
			derniereProposition = new ArrayList<>();
		}

		/* VERSION IA nulle
		for(int i=0; i < pNbChiffre; i++) {
			nombre.add(new Integer((int) ((9-0)*Math.random())));
		}
		*/
		
		/* VERSION IA MEDIUM
		for(int i=0; i < pNbChiffre; i++) {
			
			if(derniereProposition.isEmpty()) {
				nombre.add(new Integer((int) ((9-0)*Math.random())));
			}
			else {
				
				switch(this.getListResultats().get(this.getListResultats().size()-1).charAt(i)) {
				
				case '+':
					nombre.add(new Integer((int) ((9-derniereProposition.get(i).intValue())*Math.random())));
					break;
				case '-':
					nombre.add(new Integer((int) ((derniereProposition.get(i).intValue()-0)*Math.random())));
					break;
				case '=':
					nombre.add(new Integer(derniereProposition.get(i).intValue()));
					break;
					
				default:
					break;
				}
			}
		}*/
		
		for(int i=0; i < pNbChiffre; i++) {
			
			if(derniereProposition.isEmpty()) {
				nombre.add(new Integer((int) ((9-0)*Math.random())));
			}
			else {
				
				switch(this.getListResultats().get(this.getListResultats().size()-1).charAt(i)) {
				
				case '+':
					this.getValeurMin().set(i,derniereProposition.get(i));
					nombre.add(new Integer((((this.getValeurMax().get(i).intValue()+1)-derniereProposition.get(i).intValue())/2) + derniereProposition.get(i)));
					break;
				case '-':
					this.getValeurMax().set(i,derniereProposition.get(i));
					nombre.add(new Integer(derniereProposition.get(i).intValue() - ((derniereProposition.get(i).intValue()-(this.getValeurMin().get(i).intValue()-1))/2)));
					break;
				case '=':
					nombre.add(new Integer(derniereProposition.get(i).intValue()));
					break;
					
				default:
					break;
				}
			}
		}
		
		this.addDerniereProposition(nombre);
		return nombre;
	}
	
	/**
	 * Cette méthode permet d'ajouter un résultat à la liste des résultats.<br>
	 * <br>
	 * Un résultat est une String obtenue lors de la comparaison entre un nombre proposé et le nombre mystère.
	 * 
	 * @param pResultat Résultat de la comparaison entre un nombre proposé et le nombre mystère
	 */
	public void addResultat(String pResultat) {
		
		this.getListResultats().add(pResultat);
	}
	
	/**
	 * Cette méthode permet d'ajouter le nombre proposé en dernière proposition à la liste des résultats.<br>
	 * <br>
	 * Une proposition est un ArrayList d'entier. Cette méthode est utilisé en interne par {@link #donneNombre(int)}
	 * 
	 * @param pProposition nombre proposé en dernière proposition
	 */
	private void addDerniereProposition(ArrayList<Integer> pProposition) {
		this.getListNumberProposed().add(pProposition);
	}
	
}
