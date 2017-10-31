package scrabble;

import java.nio.file.Paths;

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
	
	// Taille en longueur et largeur du plateau
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
			{"", "", "LD", "", "", "", "LD", "", "lD", "", "", "", "LD", "", ""},
			{"", "LT", "", "", "", "LT", "", "", "", "LT", "", "", "", "LT", ""},
			{"", "", "", "", "MD", "", "", "", "", "", "MD", "", "", "", ""},
			{"LD", "", "", "MD", "", "", "", "LD", "", "", "", "MD", "", "", "LD"},
			{"", "", "MD", "", "", "", "LD", "", "LD", "", "", "", "MD", "", ""},
			{"", "MD", "", "", "", "LT", "", "", "", "LT", "", "", "", "MD", ""},
			{"MT", "", "", "LD", "", "", "", "MT", "", "", "", "LD", "", "", "MT"},	
	};
	
	// Tableau 2D contenant les tuiles 
	private Tuile[][] plateauTuiles;
	
	// Constructeur 
	Plateau(){
		
		plateauTuiles = new Tuile[TAILLE][TAILLE];
		initialiser();
	}
	
	// Initialisation du plateau avec des objets nuls
	public void initialiser() {
		
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++)
				plateauTuiles[x][y] = null;
		}
	}
	
	// Affichage du plateau en console 
	public void afficher() {
		
		int x, y, z, w;  
	    
		System.out.print("   _________________________________________________________________________________________ \n");
		System.out.print("  |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |\n");
	   
		for(x = 14; x >= 0; x--) {
			
			System.out.print("  ");
					
			for(y = 0; y < 15; y++) {
				
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
}
