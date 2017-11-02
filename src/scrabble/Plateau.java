package scrabble;

import java.awt.List;
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
	 * Crée un mot (chaine de caractères) à partir d'un tableau de tuiles
	 * @param listeTuiles Tuiles à concatener pour former un mot
	 * @return le mot formé
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
	
	// Restaure les tuiles sauvegardées dans le plateau dans tampon vers le plateau principal
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
	 * @param x Coordonnée de la ligne
	 * @param y Coordonnée de la colonne
	 * @param direction Direction du mot
	 * @param t Tuile à placer
	 */
	public void placerTuile(int x, int y, int direction, Tuile t) {
		
		// Dépassement de coordonnées
		if(x < 0 || x > (TAILLE-1) || y < 0 || y > (TAILLE-1))
			return;
		
		// Si case vide, on ajoute
		if(plateauTuilesTampon[x][y] == null)
			plateauTuilesTampon[x][y] = t;
		else {
			
			// Appel de la fonction pour placer la lettre à l'horizontal
			if(direction == 0)
				placerTuile(x, y+1, direction, t);
			// Appel de la fonction pour placer la lettre à la verticale
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
					// Tuile voisine à l'horizontale
					if(plateauTuiles[x][y+1] == null && plateauTuiles[x][y-1] == null)
						liste.add(plateauTuiles[x][y]);
					// Tuile voisine à la verticale
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
	 * @return mot Le mot formé suite à la concaténation
	 */
	public String concatenerChaines(ArrayList<Tuile> tuilesDisponibles, Chevalet chevalet) {
		
		String mot;
		StringBuilder sb = new StringBuilder();
		
		mot = sb.append(creerMot(tuilesDisponibles)).append(creerMot(chevalet.getTuiles())).toString();
		
		return mot;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getMotsAJouer(){
		
		ArrayList<String> motsAJouer = new ArrayList();
		
		
		
		return motsAJouer;
	}
}
