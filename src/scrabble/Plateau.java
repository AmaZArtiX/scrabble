package scrabble;

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
			{"MT", "", "", "LD", "", "", "", "", "", "", "", "LD", "", "", "MT"},
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
	
	// Tableau 2D contenant les tuiles 
	private Tuile[][] plateauTuiles;
	
	// Constructeur 
	Plateau(){
		
		plateauTuiles = new Tuile[TAILLE][TAILLE];
		plateauAttribution = new int[TAILLE][TAILLE];
		initialiser();
	}
	
	// Initialisation du plateau avec des objets nuls
	public void initialiser() {
		
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++) {
				
				plateauTuiles[x][y] = null;
				plateauAttribution[x][y] = 0;
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
	       
			if(x==0)
	         System.out.print("\n   ");    
	     }
	}
	
	// Crée un mot (chaine de caractères) à partir d'un tableau de tuiles
	public String creerMot(Tuile[] tab) {
		
		String mot;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < tab.length; i++)
			sb.append(tab[i].getLettre());
		
		mot = sb.toString();
		
		return mot;
	}
}
