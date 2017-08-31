package games;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import affichage.Affichage;
import games.constantes.ModesDeJeu;
/**
 * <b>PlusMoins est un jeu o� il faut devenir un nombre myst�re.</b><br>
 * <br> 
 * Chaque chiffre composant le nombre est compar� � chaque chiffre composant le nombre propos�.<br>
 * Le r�sultat de la comparaison est une suite de signe soit + soit - soit = en fonction du r�sultat.<br>
 * Le joueur n'a droit qu'� un nombre limit� d'essai pour d�couvrir le nombre myst�re.<br>
 * <br>
 * La classe PlusMoins repr�sente le jeu PlusMoins. Les m�thodes de cette classe n'effectuent aucun affichage direct.<br>
 * Les �l�ments qui doivent �tre affich�s sont envoy�s � l'objet Affichage qui lui se chargera de r�gler les aspects graphiques.<br>
 * 
 * 
 * @author BRUCELLA2
 * @version 1.0
 *
 */
public class PlusMoins extends Game{
	
	/**
	 * Constructeur de la classe PlusMoins.<br>
	 * <br>
	 * Le constructeur initialise les diff�rentes variables et d�bute le jeu avec le mode souhait�.
	 * 
	 * @param pModeDeJeu mode de jeu qui doit �tre utilis�
	 * @param pAffichage affichage qui sera utilis� pour proc�der aux affichages du jeu
	 * 
	 * @see #playChallenger()
	 * @see #playDefenser()
	 * @see #playDuel()
	 */
	public PlusMoins(ModesDeJeu pModeDeJeu, Affichage pAffichage) {
		super(pModeDeJeu, pAffichage);
	}
	
	/**
	 * Cette m�thode permet de comparer le nombre proposer en param�tre au nombre myst�re.<br>
	 * <br>
	 * La comparaison se fait chiffre � chiffre.<br>
	 * Le r�sultat de la comparaison est renvoy� sous la forme d'une chaine de caract�re indiquant pour chaque chiffre le r�sultat de la comparaison : <br>
	 * <ul>
	 * <li>+ si le chiffre myst�re est plus grand que celui propos�</li>
	 * <li>- si le chiffre myst�re est plus petit que celui propos�</li>
	 * <li>= si le chiffre myst�re est identique � celui propos�</li>
	 * </ul>
	 * 
	 * @param pNombreAComparer Le nombre � comparer au nombre myst�re. 
	 * @return Le r�sultat de la comparaison sous la forme d'une String.
	 */
	@Override
	public String compareNombre(ArrayList<Integer> pNombreAComparer) {
		ListIterator<Integer> itNbProposed = pNombreAComparer.listIterator();
		ListIterator<Integer> itNbMystere = this.getNombreMystere().listIterator();
		String resultat = ""; //$NON-NLS-1$
		
		while(itNbProposed.hasNext()) {
			while(itNbMystere.hasNext()) {
				
				int chiffreProposed = itNbProposed.next().intValue();
				int chiffreMystere = itNbMystere.next().intValue();
				if( chiffreProposed - chiffreMystere < 0) {
					resultat = resultat + "+"; //$NON-NLS-1$
				}
				else if(chiffreProposed - chiffreMystere > 0) {
					resultat = resultat + "-"; //$NON-NLS-1$
				}
				else {
					resultat = resultat + "="; //$NON-NLS-1$
				}
			}	
		}		
		return resultat;
	}
	
	/**
	 * Cette m�thode ex�cute le jeu avec le mode Challenger.<br>
	 * <br>
	 * Dans ce mode, le joueur doit trouver le nombre myst�re en un nombre maximum de coups.<br>
	 * Si le joueur trouve la bonne combinaison, la partie s'arr�te et affiche le message de victoire.<br>
	 * Si le joueur ne trouve pas le bonne combinaison, la partie s'arr�te et affiche le message de d�faite. 
	 * 
	 * @see #genereNombreMystere()
	 * @see #victoire()
	 * @see #defaite()
	 */
	@SuppressWarnings("nls")
	@Override
	public void playChallenger() {

		JoueurHumain joueurHumain = new JoueurHumain("Joueur", this.getAffichage()); //$NON-NLS-1$
		this.genereNombreMystere();
		
		while(!this.isFinPartie()) {
			
			ArrayList<Integer> nombre = joueurHumain.donneNombre(this.getNbChiffresMysteres());
			String comparaison = this.compareNombre(nombre);
			String resultat = " -> R�ponse : " + comparaison; //$NON-NLS-1$
			
			this.getAffichage().affiche(("Proposition : "));
			this.getAffichage().affiche(nombre);
			this.getAffichage().afficheln(resultat);
			
			//TODO Refaire la comparaison directement avec les arraylist pour prendre en compte le nb de chiffres en param�trage du jeu
			if(comparaison.equals("====")) { //$NON-NLS-1$
				this.victoire();	
			}
			
			if(!comparaison.equals("====") && this.getNbCoups() == 1) { //$NON-NLS-1$
				this.defaite();
			}
			
			this.setNbCoups(this.getNbCoups()-1);
		}
	}
	
	/**
	 * Cette m�thode �x�cute le jeu avec le mode d�fenseur.<br>
	 * <br>
	 * Dans ce mode, le joueur donne le nombre myst�re et c'est l'ordinateur qui doit le d�couvrir.<br>
	 * A chaque proposition de l'ordinateur, le r�sultat est affich�.<br>
	 * Le joueur humain doit presser "entr�e" pour d�clencher la prochaine proposition de l'ordinateur.
	 * 
	 * Si l'ordinateur trouve la bonne combinaison, la partie s'arr�te et affiche le message de d�faite.<br>
	 * Si l'ordinateur ne trouve pas le bonne combinaison, la partie s'arr�te et affiche le message de victoire.  
	 * 
	 */
	@Override
	public void playDefenser() {

		JoueurHumain joueurHumain = new JoueurHumain("Joueur", this.getAffichage()); //$NON-NLS-1$
		JoueurOrdinateur joueurOrdinateur = new JoueurOrdinateur("Ordinateur", this.getAffichage(), this.getNbChiffresMysteres()); //$NON-NLS-1$
		this.setNombreMystere(joueurHumain.creerNombreMystere(this.getNbChiffresMysteres()));
		//TODO warning a prendre en compte
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		while(!this.isFinPartie()) {
			
			ArrayList<Integer> nombre = joueurOrdinateur.donneNombre(this.getNbChiffresMysteres());
			String comparaison = this.compareNombre(nombre);
			joueurOrdinateur.addResultat(comparaison);
			String resultat = " -> R�ponse : " + comparaison; //$NON-NLS-1$
			
			this.getAffichage().affiche(joueurOrdinateur.getNomJoueur() + " propose : "); //$NON-NLS-1$
			this.getAffichage().affiche(nombre);
			this.getAffichage().affiche(resultat);
			this.getAffichage().affiche("   (Appuyez sur entr�e pour continuer)"); //$NON-NLS-1$
			scan.nextLine();
			
			if(comparaison.equals("====")) { //$NON-NLS-1$
				this.defaite();	
			}
			
			if(!comparaison.equals("====") && this.getNbCoups() == 1) { //$NON-NLS-1$
				this.victoire();
			}
			
			this.setNbCoups(this.getNbCoups()-1);	
		}		
	}
	
	//TODO A faire
	/**
	 * Cette m�thode ex�cute le jeu en mode Duel<br>
	 * <br>
	 * Dans ce mode, le joueur et l'ordinateur doivent d�couvrir un nombre myst�re g�n�r� al�atoirement.<br>
	 * A tour de role, joueur et ordinateur effectuent des propositions. La proposition de l'ordinateur n'est pas visible,
	 * seule le r�sultat de sa proposition est visible.<br>
	 *
	 */
	@Override
	//TODO A refaire, ne correspond pas � l'�nonc�.
	public void playDuel() {
		
		JoueurHumain joueurHumain= new JoueurHumain("Joueur", this.getAffichage()); //$NON-NLS-1$
		this.joueurs.add(joueurHumain);
		JoueurOrdinateur joueurOrdinateur = new JoueurOrdinateur("Ordinateur", this.getAffichage(), this.getNbChiffresMysteres()); //$NON-NLS-1$
		this.joueurs.add(joueurOrdinateur);
		String compNbHumain = new String();
		String compNbOrdinateur= new String();
		
		this.genereNombreMystere();
		this.getAffichage().afficheln("Un nombre a �t� g�n�r�. A vous de le deviner. Que le meilleur gagne"); //$NON-NLS-1$
		
		while(!this.isFinPartie()) {
			
			for(Joueur joueur : this.getJoueurs()) {
				
				ArrayList<Integer> nombre = new ArrayList<>();			
				nombre = joueur.donneNombre(this.getNbChiffresMysteres());
				
				
				if(joueur instanceof JoueurHumain) {
					compNbHumain = this.compareNombre(nombre);
					String resultat = " -> R�ponse : " + compNbHumain; //$NON-NLS-1$
					
					this.getAffichage().affiche(("Votre proposition : ")); //$NON-NLS-1$
					this.getAffichage().affiche(nombre);
					this.getAffichage().afficheln(resultat);
					
					if(compNbHumain.equals("====")) { //$NON-NLS-1$
						this.victoire();	
					}
					
					//TODO Mettre nb de coups comme variable du joueur ? 1 coup = 1 tour donc pas n�cessaire ?
					/*if(!comparaison.equals("====") && this.getNbCoups() == 1) { //$NON-NLS-1$
						this.defaite();
					}*/
				}
				else if (!this.isFinPartie()) {
					compNbOrdinateur = this.compareNombre(nombre);
					joueurOrdinateur.addResultat(compNbOrdinateur);
					String resultat = " -> R�ponse : " + compNbOrdinateur; //$NON-NLS-1$
					
					this.getAffichage().affiche(("L'ordinateur a propos� un nombre. "));//$NON-NLS-1$
					this.getAffichage().afficheln(resultat);
					
					if(compNbOrdinateur.equals("====")) { //$NON-NLS-1$
						this.getAffichage().afficheln("L'ordinateur a propos� les valeurs suivantes :"); //$NON-NLS-1$
						for(ArrayList<Integer> proposition : joueurOrdinateur.getListNumberProposed()) {
							this.getAffichage().affiche(proposition);
							this.getAffichage().affiche("\n"); //$NON-NLS-1$
						}

						this.defaite();	
					}
				}
			}
			if(!compNbHumain.equals("====") && !compNbOrdinateur.equals("====") && this.getNbCoups() == 1) { //$NON-NLS-1$ //$NON-NLS-2$
				this.egalite();
			}
			this.setNbCoups(this.getNbCoups()-1);
		}
	}
	
}
