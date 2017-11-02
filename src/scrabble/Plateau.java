// Package
package scrabble;

// Import(s)
import java.util.ArrayList;

/*************************************************************************
 * Nom ...........: Plateau.java
 * Description ...: Plateau de jeu (15x15) contenant les bonus et les tuiles
 * ...............:
 * ...............:
 * Auteur(s) .....: SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: © 2017 SIMON BACQUET ALL RIGHTS RESERVED
 ************************************************************************/

public class Plateau {
	
	// Taille (longueur et largeur) du plateau
	private final int TAILLE = 15;
	
	// Tableau 2D de chaines correspondantes aux bonus
	private final String[][] plateauBonus = {
			{"MT", "", "", "LD", "", "", "", "MT", "", "", "", "LD", "", "", "MT"},
			{"", "MD", "", "", "", "LT", "", "", "", "LT", "", "", "", "MD", ""},
			{"", "", "MD", "", "", "", "LD", "", "LD", "", "", "", "MD", "", ""},
			{"LD", "", "", "MD", "", "", "", "LD", "", "", "", "MD", "", "", "LD"},
			{"", "", "", "", "MD", "", "", "", "", "", "MD", "", "", "", ""},
			{"", "LT", "", "", "", "LT", "", "", "", "LT", "", "", "", "LT", ""},
			{"", "", "LD", "", "", "", "LD", "", "lD", "", "", "", "LD", "", ""},
			{"MT", "", "", "LD", "", "", "", "MD", "", "", "", "LD", "", "", "MT"},
			{"", "", "LD", "", "", "", "LD", "", "LD", "", "", "", "LD", "", ""},
			{"", "LT", "", "", "", "LT", "", "", "", "LT", "", "", "", "LT", ""},
			{"", "", "", "", "MD", "", "", "", "", "", "MD", "", "", "", ""},
			{"LD", "", "", "MD", "", "", "", "LD", "", "", "", "MD", "", "", "LD"},
			{"", "", "MD", "", "", "", "LD", "", "LD", "", "", "", "MD", "", ""},
			{"", "MD", "", "", "", "LT", "", "", "", "LT", "", "", "", "MD", ""},
			{"MT", "", "", "LD", "", "", "", "MT", "", "", "", "LD", "", "", "MT"},	
	};
	
	// Tableau d'entiers pour savoir si un bonus a été attribué 
	private int[][] plateauAttribution;
	// Tableau d'entiers pour savoir si un bonus a été attribué (tampon)
	private int[][] plateauAttributionTampon;
	
	// Tableau 2D contenant les tuiles 
	private Tuile[][] plateauTuiles;
	// Tableau 2D contenant les tuiles (tampon)
	private Tuile[][] plateauTuilesTampon;
	
	// Constructeur 
	Plateau(){
		
		plateauTuiles = new Tuile[TAILLE][TAILLE];
		plateauTuilesTampon = new Tuile[TAILLE][TAILLE];
		plateauAttribution = new int[TAILLE][TAILLE];
		plateauAttributionTampon = new int[TAILLE][TAILLE];
		initialiser();
	}
	
	// Initialisation du plateau avec des objets nuls
	public void initialiser() {
		
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++) {
				
				plateauTuiles[x][y] = null;
				plateauTuilesTampon[x][y] = null;
				plateauAttribution[x][y] = 0;
				plateauAttributionTampon[x][y] = 0;	
			}
		}
	}
	
	// Affichage du plateau en console 
	public void afficher() {
		
		int x, y; 
	    
		System.out.print("   _________________________________________________________________________________________ \n");
		System.out.print("  |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |\n");
	   
		for(x = (TAILLE-1); x >= 0; x--) {
			
			System.out.print("  ");
					
			for(y = 0; y < TAILLE; y++) {
				
				System.out.print("|  ");
				
				if(plateauTuiles[x][y] instanceof Tuile)
					System.out.print(plateauTuiles[x][y].getLettre());
				else
					System.out.print(" ");
				
				System.out.print("  ");
			}   
			
			System.out.print("|\n");
	        System.out.print("  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
			
	        if(x != 0)
	          System.out.print("\n  |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |\n");
	       
			if(x == 0)
	         System.out.print("\n");    
	     }
	}
	
	/**
	 * Cree un mot (chaine de caracteres) a partir d'un tableau de tuiles
	 * @param listeTuiles Tuiles a concatener pour former un mot
	 * @return le mot forme
	 */
	public String creerMot(ArrayList<Tuile> listeTuiles) {
		
		String mot;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < listeTuiles.size(); i++)
			sb.append(listeTuiles.get(i).getLettre());
		
		mot = sb.toString();
		
		return mot;
	}
	
	// Sauvegarde les tuiles du plateau principal dans le plateau tampon
	public void sauvegarderPlateauTuiles() {
		
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++)
				plateauTuilesTampon[x][y] = plateauTuiles[x][y];
		}
	}
	
	// Restaure les tuiles sauvegardees dans le plateau dans tampon vers le plateau principal
	public void restaurerPlateauTuiles() {
		
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++)
				plateauTuiles[x][y] = plateauTuilesTampon[x][y];
		}
	}
	
	// Sauvegarde les tuiles du chevalet principal dans un chevalet tampon
	public void sauvegarderChevalet(Chevalet chevaletPrincipal, Chevalet chevaletTampon) {
		
		for(int i = 0; i < chevaletPrincipal.getTaille(); i++)
			chevaletTampon.ajouterTuileIndex(i, chevaletPrincipal.getTuile(i));	
	}
	
	// Restaure les tuiles du chevalet principal depuis le chevalet tampon
	public void restaurerChevalet(Chevalet chevaletPrincipal, Chevalet chevaletTampon) {
		
		for(int i = 0; i < chevaletTampon.getTaille(); i++)
			chevaletPrincipal.ajouterTuileIndex(i, chevaletTampon.getTuile(i));	
	}
	
	/**
	 * Place une tuile sur le plateau tampon
	 * @param x Coordonnee de la ligne
	 * @param y Coordonnee de la colonne
	 * @param direction Direction du mot
	 * @param t Tuile a placer
	 */
	public void placerTuile(int x, int y, int direction, Tuile t) {
		
		// Depassement de coordonnees
		if(x < 0 || x > (TAILLE-1) || y < 0 || y > (TAILLE-1))
			return;
		
		// Si case vide, on ajoute
		if(plateauTuilesTampon[x][y] == null)
			plateauTuilesTampon[x][y] = t;
		else {
			
			// Appel de la fonction pour placer la lettre a l'horizontal
			if(direction == 0)
				placerTuile(x, y+1, direction, t);
			// Appel de la fonction pour placer la lettre a la verticale
			else if(direction == 1)
				placerTuile(x-1, y, direction, t);
		}
	}
	
	/**
	 * Renvoie la liste des tuiles disponibles sur le plateau pour faire un mot
	 * @return liste Liste de tuiles 
	 */
	public ArrayList<Tuile> getTuilesDisponibles(){
		
		ArrayList<Tuile> liste = new ArrayList<>();
		
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++) {
				
				if(plateauTuiles[x][y] != null) {
					
					// Recherche d'une tuile voisine, si pas, on sort de la boucle
					if(plateauTuiles[x][y+1] != null && plateauTuiles[x+1][y] != null && plateauTuiles[x][y-1] != null && plateauTuiles[x-1][y] != null)
					      break;
					// Tuile voisine a l'horizontale
					if(plateauTuiles[x][y+1] == null && plateauTuiles[x][y-1] == null)
						liste.add(plateauTuiles[x][y]);
					// Tuile voisine a la verticale
					else if(plateauTuiles[x+1][y] == null && plateauTuiles[x-1][y] == null)
						liste.add(plateauTuiles[x][y]);
				}
			}
		}
		
		return liste;
	}
	
	/**
	 * Concatene les tuiles disponibles sur le plateau et les tuiles du chevalet pour former un mot
	 * @param tuilesDisponibles Tuiles disponibles sur le pleateau
	 * @param chevalet Chevalet du joueur
	 * @return mot Le mot forme suite a la concatenation
	 */
	public String concatenerChaines(ArrayList<Tuile> tuilesDisponibles, Chevalet chevalet) {
		
		String mot;
		StringBuilder sb = new StringBuilder();
		
		mot = sb.append(creerMot(tuilesDisponibles)).append(creerMot(chevalet.getTuiles())).toString();
		
		return mot;
	}
	
	/**
	 * Retourne le bonus numerique correspondant au bonus litteral
	 * @param bonus Chaine symbolisant le bonus
	 * @return entier correspondant au bonus 
	 */
	public int getBonus(String bonus) {
		
		switch(bonus) {
			
			case "":
				return 1;
			case "MD":
				return 1;
			case "MT":
				return 1;
			case "LD":
				return 2;
			case "LT":
				return 3;
			default:
				return -1;
		}
	}
	
	/**
	 * Calcule le score effectue pour une liste de tuiles et une liste de bonus correspondants
	 * la premiere tuile de la liste correspond à la premiere lettre du mot place, idem pour le bonus 
	 * qui corresppond à la case ou a ete jouee la tuile
	 * @param listeTuiles Tuiles qui forment le mot
	 * @param listeBonus Bonus de chaque case 
	 * @return scoreMot le score effectue 
	 */
	public int calculScoreMot(ArrayList<Tuile> listeTuiles, ArrayList<String> listeBonus) {

		ArrayList<String> motsAJouer = new ArrayList<String>();

		
		// Score total m
		int scoreMot = 0;
		// Score pour une lettre 
		int scoreLettre = 0;
		// Compteur de cases "MD"
		int cptMotDouble = 0;
		// Compteur de case "MT"
		int cptMotTriple = 0;
		
		// Verification de la coherence des listes
		if(listeTuiles.size() != listeBonus.size())
			return -1;
		else {
			
			for(int i = 0; i < listeTuiles.size(); i++) {
				
				// Recuperation de la valeur d'une tuile
				scoreLettre = listeTuiles.get(i).getValeur(); 
				// Recuperation du bonus de la case
				String bonus = listeBonus.get(i);
				// Multiplication de la valeur de la tuile par le bonus de la case
				scoreLettre *= getBonus(bonus);
				// Incrémentation du score total
				scoreMot += scoreLettre;
				
				// Incrémentation du compteur de cases "MD"
				if(bonus.equals("MD"))
					cptMotDouble++;
				// Incrémentation du compteur de cases "MT"
				else if(bonus.equals("MT"))
					cptMotTriple++;
			}
			
			// Mutliplication du score par le bonus 
			if(cptMotDouble == 1)
				scoreMot *= 2;
			else if(cptMotDouble == 2)
				scoreMot *= 4;
			else if(cptMotTriple == 1)
				scoreMot *= 3;
			else if(cptMotTriple == 2)
				scoreMot *= 9;
			
			return scoreMot;
		}
	}
}
