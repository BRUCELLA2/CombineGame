package games;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import affichage.Affichage;
import games.constantes.ModesDeJeu;
/**
 * <b>PlusMoins est un jeu où il faut devenir un nombre mystère.</b><br>
 * <br> 
 * Chaque chiffre composant le nombre est comparé à chaque chiffre composant le nombre proposé.<br>
 * Le résultat de la comparaison est une suite de signe soit + soit - soit = en fonction du résultat.<br>
 * Le joueur n'a droit qu'à un nombre limité d'essai pour découvrir le nombre mystère.<br>
 * <br>
 * La classe PlusMoins représente le jeu PlusMoins. Les méthodes de cette classe n'effectuent aucun affichage direct.<br>
 * Les éléments qui doivent être affichés sont envoyés à l'objet Affichage qui lui se chargera de régler les aspects graphiques.<br>
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
	 * Le constructeur initialise les différentes variables et débute le jeu avec le mode souhaité.
	 * 
	 * @param pModeDeJeu mode de jeu qui doit être utilisé
	 * @param pAffichage affichage qui sera utilisé pour procéder aux affichages du jeu
	 * 
	 * @see #playChallenger()
	 * @see #playDefenser()
	 * @see #playDuel()
	 */
	public PlusMoins(ModesDeJeu pModeDeJeu, Affichage pAffichage) {
		super(pModeDeJeu, pAffichage);
	}
	
	/**
	 * Cette méthode permet de comparer le nombre proposer en paramètre au nombre mystère.<br>
	 * <br>
	 * La comparaison se fait chiffre à chiffre.<br>
	 * Le résultat de la comparaison est renvoyé sous la forme d'une chaine de caractère indiquant pour chaque chiffre le résultat de la comparaison : <br>
	 * <ul>
	 * <li>+ si le chiffre mystère est plus grand que celui proposé</li>
	 * <li>- si le chiffre mystère est plus petit que celui proposé</li>
	 * <li>= si le chiffre mystère est identique à celui proposé</li>
	 * </ul>
	 * 
	 * @param pNombreAComparer Le nombre à comparer au nombre mystère. 
	 * @return Le résultat de la comparaison sous la forme d'une String.
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
	 * Cette méthode exécute le jeu avec le mode Challenger.<br>
	 * <br>
	 * Dans ce mode, le joueur doit trouver le nombre mystère en un nombre maximum de coups.<br>
	 * Si le joueur trouve la bonne combinaison, la partie s'arrête et affiche le message de victoire.<br>
	 * Si le joueur ne trouve pas le bonne combinaison, la partie s'arrête et affiche le message de défaite. 
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
			String resultat = " -> Réponse : " + comparaison; //$NON-NLS-1$
			
			this.getAffichage().affiche(("Proposition : "));
			this.getAffichage().affiche(nombre);
			this.getAffichage().afficheln(resultat);
			
			//TODO Refaire la comparaison directement avec les arraylist pour prendre en compte le nb de chiffres en paramètrage du jeu
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
	 * Cette méthode éxécute le jeu avec le mode défenseur.<br>
	 * <br>
	 * Dans ce mode, le joueur donne le nombre mystère et c'est l'ordinateur qui doit le découvrir.<br>
	 * A chaque proposition de l'ordinateur, le résultat est affiché.<br>
	 * Le joueur humain doit presser "entrée" pour déclencher la prochaine proposition de l'ordinateur.
	 * 
	 * Si l'ordinateur trouve la bonne combinaison, la partie s'arrête et affiche le message de défaite.<br>
	 * Si l'ordinateur ne trouve pas le bonne combinaison, la partie s'arrête et affiche le message de victoire.  
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
			String resultat = " -> Réponse : " + comparaison; //$NON-NLS-1$
			
			this.getAffichage().affiche(joueurOrdinateur.getNomJoueur() + " propose : "); //$NON-NLS-1$
			this.getAffichage().affiche(nombre);
			this.getAffichage().affiche(resultat);
			this.getAffichage().affiche("   (Appuyez sur entrée pour continuer)"); //$NON-NLS-1$
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
	 * Cette méthode exécute le jeu en mode Duel<br>
	 * <br>
	 * Dans ce mode, le joueur et l'ordinateur doivent découvrir un nombre mystère généré aléatoirement.<br>
	 * A tour de role, joueur et ordinateur effectuent des propositions. La proposition de l'ordinateur n'est pas visible,
	 * seule le résultat de sa proposition est visible.<br>
	 *
	 */
	@Override
	//TODO A refaire, ne correspond pas à l'énoncé.
	public void playDuel() {
		
		JoueurHumain joueurHumain= new JoueurHumain("Joueur", this.getAffichage()); //$NON-NLS-1$
		this.joueurs.add(joueurHumain);
		JoueurOrdinateur joueurOrdinateur = new JoueurOrdinateur("Ordinateur", this.getAffichage(), this.getNbChiffresMysteres()); //$NON-NLS-1$
		this.joueurs.add(joueurOrdinateur);
		String compNbHumain = new String();
		String compNbOrdinateur= new String();
		
		this.genereNombreMystere();
		this.getAffichage().afficheln("Un nombre a été généré. A vous de le deviner. Que le meilleur gagne"); //$NON-NLS-1$
		
		while(!this.isFinPartie()) {
			
			for(Joueur joueur : this.getJoueurs()) {
				
				ArrayList<Integer> nombre = new ArrayList<>();			
				nombre = joueur.donneNombre(this.getNbChiffresMysteres());
				
				
				if(joueur instanceof JoueurHumain) {
					compNbHumain = this.compareNombre(nombre);
					String resultat = " -> Réponse : " + compNbHumain; //$NON-NLS-1$
					
					this.getAffichage().affiche(("Votre proposition : ")); //$NON-NLS-1$
					this.getAffichage().affiche(nombre);
					this.getAffichage().afficheln(resultat);
					
					if(compNbHumain.equals("====")) { //$NON-NLS-1$
						this.victoire();	
					}
					
					//TODO Mettre nb de coups comme variable du joueur ? 1 coup = 1 tour donc pas nécessaire ?
					/*if(!comparaison.equals("====") && this.getNbCoups() == 1) { //$NON-NLS-1$
						this.defaite();
					}*/
				}
				else if (!this.isFinPartie()) {
					compNbOrdinateur = this.compareNombre(nombre);
					joueurOrdinateur.addResultat(compNbOrdinateur);
					String resultat = " -> Réponse : " + compNbOrdinateur; //$NON-NLS-1$
					
					this.getAffichage().affiche(("L'ordinateur a proposé un nombre. "));//$NON-NLS-1$
					this.getAffichage().afficheln(resultat);
					
					if(compNbOrdinateur.equals("====")) { //$NON-NLS-1$
						this.getAffichage().afficheln("L'ordinateur a proposé les valeurs suivantes :"); //$NON-NLS-1$
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
