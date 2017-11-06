// Package
package scrabble;

// Import(s)
import java.util.ArrayList;

/*************************************************************************
 * Nom ...........: Plateau.java
 * Description ...: Plateau de jeu (15x15) contenant les bonus et les tuiles
 * ...............:
 * ...............:
 * Auteur(s) .....: SIMON BACQUET, RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 SIMON BACQUET, RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class Plateau {
	
	// Taille (longueur et largeur) du plateau
	public static final int TAILLE = 15;
	
	// Booleens HORIZONTAL et VERTICAL
	static final boolean HORIZONTAL = false;
	static final boolean VERTICAL = true;
	
	// Tableau 2D de chaines correspondantes aux bonus
	private final String[][] plateauBonus = {
			{"MT", "  ", "  ", "LD", "  ", "  ", "  ", "MT", "  ", "  ", "  ", "LD", "  ", "  ", "MT"},
			{"  ", "MD", "  ", "  ", "  ", "LT", "  ", "  ", "  ", "LT", "  ", "  ", "  ", "MD", "  "},
			{"  ", "  ", "MD", "  ", "  ", "  ", "LD", "  ", "LD", "  ", "  ", "  ", "MD", "  ", "  "},
			{"LD", "  ", "  ", "MD", "  ", "  ", "  ", "LD", "  ", "  ", "  ", "MD", "  ", "  ", "LD"},
			{"  ", "  ", "  ", "  ", "MD", "  ", "  ", "  ", "  ", "  ", "MD", "  ", "  ", "  ", "  "},
			{"  ", "LT", "  ", "  ", "  ", "LT", "  ", "  ", "  ", "LT", "  ", "  ", "  ", "LT", "  "},
			{"  ", "  ", "LD", "  ", "  ", "  ", "LD", "  ", "LD", "  ", "  ", "  ", "LD", "  ", "  "},
			{"MT", "  ", "  ", "LD", "  ", "  ", "  ", "MD", "  ", "  ", "  ", "LD", "  ", "  ", "MT"},
			{"  ", "  ", "LD", "  ", "  ", "  ", "LD", "  ", "LD", "  ", "  ", "  ", "LD", "  ", "  "},
			{"  ", "LT", "  ", "  ", "  ", "LT", "  ", "  ", "  ", "LT", "  ", "  ", "  ", "LT", "  "},
			{"  ", "  ", "  ", "  ", "MD", "  ", "  ", "  ", "  ", "  ", "MD", "  ", "  ", "  ", "  "},
			{"LD", "  ", "  ", "MD", "  ", "  ", "  ", "LD", "  ", "  ", "  ", "MD", "  ", "  ", "LD"},
			{"  ", "  ", "MD", "  ", "  ", "  ", "LD", "  ", "LD", "  ", "  ", "  ", "MD", "  ", "  "},
			{"  ", "MD", "  ", "  ", "  ", "LT", "  ", "  ", "  ", "LT", "  ", "  ", "  ", "MD", "  "},
			{"MT", "  ", "  ", "LD", "  ", "  ", "  ", "MT", "  ", "  ", "  ", "LD", "  ", "  ", "MT"},	
	};
	
	// Tableau d'entiers pour savoir si un bonus a ete attribue 
	private int[][] plateauAttribution;
	// Tableau d'entiers pour savoir si un bonus a ete attribue (tampon)
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
	
	/**
	 * Initialise le plateau de scrabble avec des objets nuls
	 */
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
	
	/**
	 * Affiche le plateau de scrabble en console 
	 */
	public void afficher() {
		
		int x, y; 
	    
		System.out.print("   _________________________________________________________________________________________ \n");
		System.out.print("  |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |\n");
	   
		for(x = 0; x < TAILLE; x++) {
			
			System.out.print("  ");
					
			for(y = 0; y < TAILLE; y++) {
				
				System.out.print("|  ");
				
				if(plateauTuilesTampon[x][y] instanceof Tuile)
					System.out.print(plateauTuilesTampon[x][y].getLettre());
				else
					System.out.print(" ");
				
				System.out.print("  ");
			}   
			
			System.out.print("|\n");
	        System.out.print("  |_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|_____|");
			
	        if (x < 14)
	          System.out.print("\n  |     |     |     |     |     |     |     |     |     |     |     |     |     |     |     |\n");   
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
	
	/**
	 * Sauvegarde les tuiles du plateau principal dans le plateau tampon
	 */
	public void sauvegarderPlateauTuiles() {
		
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++)
				plateauTuilesTampon[x][y] = plateauTuiles[x][y];
		}
	}
	
	/**
	 * Restaure les tuiles sauvegardees dans le plateau dans tampon vers le plateau principal
	 */
	public void restaurerPlateauTuiles() {
		
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++)
				plateauTuiles[x][y] = plateauTuilesTampon[x][y];
		}
	}
	
	/**
	 * Sauvegarde les tuiles du chevalet principal dans un chevalet tampon
	 * @param chevaletPrincipal
	 * @param chevaletTampon
	 */
	public void sauvegarderChevalet(Chevalet chevaletPrincipal, Chevalet chevaletTampon) {
		
		for(int i = 0; i < chevaletPrincipal.getTaille(); i++)
			chevaletTampon.ajouterTuileIndex(i, chevaletPrincipal.getTuile(i));	
	}
	
	/**
	 * Restaure les tuiles du chevalet principal depuis le chevalet tampon
	 * @param chevaletPrincipal
	 * @param chevaletTampon
	 */
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
	public void placerTuile(int x, int y, Tuile t) {
		
		// Depassement de coordonnees
		if(x < 0 || x > (TAILLE-1) || y < 0 || y > (TAILLE-1))
			return;
		
		// Si case vide, on ajoute
		if(plateauTuilesTampon[x][y] == null)
			plateauTuilesTampon[x][y] = t;
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
	
	public String[][] getPlateauBonus() {
		return plateauBonus;
	}
	
	public String getStringBonus(int col, int lig) {
		return plateauBonus[lig][col];
	}
	
	public boolean existeTuile(int col, int lig) {
		if(plateauTuilesTampon[lig][col] == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Calcule le score effectue pour une liste de tuiles et une liste de bonus correspondants
	 * la premiere tuile de la liste correspond a la premiere lettre du mot place, idem pour le bonus 
	 * qui corresppond a la case ou a ete jouee la tuile
	 * @param listeTuiles Tuiles qui forment le mot
	 * @param listeBonus Bonus de chaque case 
	 * @return scoreMot le score effectue 
	 */
	public int calculScoreMot(ArrayList<Tuile> listeTuiles, ArrayList<String> listeBonus) {

		// ArrayList<String> motsAJouer = new ArrayList<String>();
		
		
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
				// Incrementation du score total
				scoreMot += scoreLettre;
				
				// Incrementation du compteur de cases "MD"
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
	
	/**
	 * Verifie si une tuile est isolee sur le plateau
	 * @return boolean true si une tuile a ete trouvee
	 */
	public boolean existeTuileSeule() {
	
		for(int x = 0; x < TAILLE; x++) {
			
			for(int y = 0; y < TAILLE; y++) {
				
				// Existence d'une tuile en x, y
				if(plateauTuilesTampon[x][y] != null) {
					
					// Tuile isolee dans le coin superieur gauche
					if((x == 0 && y == 0) && (plateauTuilesTampon[x][y+1] == null && plateauTuilesTampon[x+1][y] == null))
						return true;
					
					// Tuile isolee dans le coin superieur droit
					if((x == 0 && y == 14) && (plateauTuilesTampon[x][y-1] == null && plateauTuilesTampon[x+1][y] == null))
						return true;
					
					// Tuile isolee dans le coin inferieur gauche
					if((x == 14 && y == 0) &&  (plateauTuilesTampon[x][y+1] == null && plateauTuilesTampon[x-1][y] == null))
						return true;
					
					// Tuile isolee dans le coin inferieur droit
					if((x == 14 && y == 14) &&  (plateauTuilesTampon[x-1][y] == null && plateauTuilesTampon[x][y-1] == null))
						return true;
					
					// Tuile isolee sur la premiere ligne 
					if((x == 0 && (y > 0 && y < 14)) && (plateauTuilesTampon[x][y-1] == null && plateauTuilesTampon[x+1][y] == null && plateauTuilesTampon[x][y+1] == null))
						return true;
					
					// Tuile isolee sur la derniere ligne
					if((x == 14 && (y > 0 && y < 14)) && (plateauTuilesTampon[x][y-1] == null && plateauTuilesTampon[x-1][y] == null && plateauTuilesTampon[x][y+1] == null))
						return true;
					
					// Tuile isolee sur la premiere colonne
					if((y == 0 && (x > 0 && x < 14)) && (plateauTuilesTampon[x-1][y] == null && plateauTuilesTampon[x][y+1] == null && plateauTuilesTampon[x+1][y] == null))
						return true;
					
					// Tuile isolee sur la derniere colonne
					if((y == 14 && (x > 0 && x < 14)) && (plateauTuilesTampon[x-1][y] == null && plateauTuilesTampon[x][y-1] == null && plateauTuilesTampon[x+1][y] == null))
						return true;
					
					// Tuile isolee sur le plateau quand x > 0, x < 14, y > 0 et y < 14
					if((x > 0 && x < 14) && (y > 0 && y < 14) && (plateauTuilesTampon[x-1][y] == null  && plateauTuilesTampon[x][y+1] == null && plateauTuilesTampon[x+1][y] == null && plateauTuilesTampon[x][y-1] == null))
						return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean tuileSeule(int col, int lig) {
		
		// On verifie si la case cible ne touche pas le bord du plateau
		if((col > 0 && col < 14) && (lig > 0 && lig < 14)) {

			// On verifie les 4 cotes
			if (existeTuile(col, lig-1)) return false;
			else if (existeTuile(col+1, lig)) return false;
			else if (existeTuile(col, lig+1)) return false;
			else if (existeTuile(col-1, lig)) return false;
		} else 

			// On verifie si la case cible est sur la premiere colonne
			if (col == 0 && (lig > 0 && lig < 14)) {

				// On verifie au dessus, a droite et en dessous
				if (existeTuile(col, lig-1)) return false;
				else if (existeTuile(col+1, lig)) return false;
				else if (existeTuile(col, lig+1)) return false;
			} else 

				// On verifie si la case cible est sur la premiere ligne
				if (lig == 0 && (col > 0 && col < 14)) {

					// On verifie a droite, en dessous et a gauche
					if (existeTuile(col+1, lig)) return false;
					else if (existeTuile(col, lig+1)) return false;
					else if (existeTuile(col-1, lig)) return false;
				} else 

					// On verifie si la case cible est sur la derniere colonne
					if (col == 14 && (lig > 0 && lig < 14)) {

						// On verifie au dessus, a gauche et en dessous
						if (existeTuile(col, lig-1)) return false;
						else if (existeTuile(col-1, lig)) return false;
						else if (existeTuile(col, lig+1)) return false;
					} else 

						// On verifie si la case cible est sur la derniere ligne
						if (lig == 14 && (col > 0 && col < 14)) {

							// On verifie au dessus, a droite et a gauche
							if (existeTuile(col, lig-1)) return false;
							else if (existeTuile(col+1, lig)) return false;
							else if (existeTuile(col-1, lig)) return false;
						} else

							// On verifie si la case cible est le coin superieur gauche
							if(col == 0 && lig == 0) {

								// On verifie a droite et en dessous
								if (existeTuile(col+1, lig)) return false;
								else if (existeTuile(col, lig+1)) return false;
							} else

								// On verifie si la case cible est le coin superieur droit
								if(col == 14 && lig == 0) {

									// On verifie en dessous et a gauche
									if (existeTuile(col, lig+1)) return false;
									else if (existeTuile(col-1, lig)) return false;
								} else

									// On verifie si la case cible est le coin inferieur gauche
									if(col == 0 && lig == 14) {

										// On verifie au dessus et a droite
										if (existeTuile(col, lig-1)) return false;
										else if (existeTuile(col+1, lig)) return false;
									} else

										// On verifie si la case cible est le coin inferieur droit
										if(col == 14 && lig == 14) {

											// On verifie au dessus et a gauche
											if (existeTuile(col, lig-1)) return false;
											else if (existeTuile(col-1, lig)) return false;
										}

		return true;
	}
	
	public boolean checkBoard() 
	{
	   String word = "";
	   int xStartIndex = 0, yStartIndex = 0, xEndIndex = 0, yEndIndex = 0;
	   Dictionnaire dict = new Dictionnaire();
	   
	   for(int x=0;x<15;x++)
	   {
	   
	      for(int y=0;y<15;y++)
		  {
	    	  
	    	  	if(x > 0 && x < 14 && y > 0 && y < 14 && plateauTuilesTampon[x][y+1] != null) {
	    	  		if(plateauTuilesTampon[x][y+1] == null && plateauTuilesTampon[x+1][y] == null && plateauTuilesTampon[x][y-1] == null && plateauTuilesTampon[x-1][y] ==  null)
	    	  		return false;
	    	  	}
		     
			 if(plateauTuilesTampon[x][y] != null)
			 {
				 
			    word = "";
			    int tempX = x;
				int tempY = y;
				//System.out.println(x + " " + y);
				
			    while(plateauTuilesTampon[tempX][tempY] != null)
				{
				   //System.out.println(1);
				   xStartIndex = tempX;
				   yStartIndex = tempY;
				   tempY--;
				   if(tempY<0)
					  break;
				}
				
				tempY = y;
				
				while(plateauTuilesTampon[tempX][tempY] != null)
				{
				   //System.out.println(2);
				   xEndIndex = tempX;
				   yEndIndex = tempY;
				   tempY++;
				   if(tempY>14)
					  break;
				}
				
				for(int start = yStartIndex; start<=yEndIndex; start++)
				{
				   //System.out.println(3);
				   word += plateauTuilesTampon[x][start].getLettre() + "";
				}
				
				System.out.println("Ceci est mon mot "+word);
				
				if(dict.existe(word)==false)
				{
				   
				   if(word.length()>1)
					  {
					        return false;
					  }
				        
				   if(x>0&&x<14)
				   {
				      
				      if(word.length()==1&&(plateauTuilesTampon[x-1][y]!= null&&plateauTuilesTampon[x+1][y]!= null))
				      {
				        return false;
				      }
					  
				   } 
				   else if(x==0)
				   {
				   
				      if(word.length()==1&&plateauTuilesTampon[x+1][y] == null)
				      {
				        return false;
				      }
					  
				   }
				   else if(x==14)
				   {
				      if(word.length()==1&&plateauTuilesTampon[x-1][y] == null)
				      {
				        return false;
				      }
				   }
				}//end confirm if block
				
				word = "";
				
				while(plateauTuilesTampon[tempX][y]!= null)
				{
				   //System.out.println(4);
				   xStartIndex = tempX;
				   yStartIndex = tempY;
				   tempX--;
				   if(tempX<0)
					  break;
				}
				
				tempX = x;
				
				while(plateauTuilesTampon[tempX][y]!= null)
				{
				
				   //System.out.println(5);
				   xEndIndex = tempX+1;
				   yEndIndex = tempY;
				   tempX++;
				   if(tempX>14)
					  break;
				   
				}
				
				for(int end = xStartIndex; end < xEndIndex; end++)
				{
				   //System.out.println(6);
				   word += plateauTuilesTampon[end][y].getLettre() + "";
				   System.out.println("JE SUIS DANS LA BOUCLE "+word);
				}
				
				/*
				for(int end = xEndIndex-1; end>=xStartIndex; end--)
				{
				   //System.out.println(6);
				   word += plateauTuilesTampon[end][y].getLettre() + "";
				   System.out.println("JE SUIS DANS LA BOUCLE"+word);
				}
				*/
				
				System.out.println("Ceci est mon mot2 "+word);
				
				if(dict.existe(word)==false)
				{
				   
				   if(word.length()>1)
					  {
					        return false;
					  }
				        
				   if(y>0&&y<15)
				   {
				      
				      if(word.length()==1&&plateauTuilesTampon[x][y-1]==null&&plateauTuilesTampon[x][y+1]==null)
				      {
				        return false;
				      }
				   } 
				   else if(y==0)
				   {
				   
				      if(word.length()==1&&plateauTuilesTampon[x][y+1]==null)
				      {
				        return false;
				      }
					  
				   }
				   else if(y==15)
				   {
				      if(word.length()==1&&plateauTuilesTampon[x][y-1] == null)
				      {
				        return false;
				      }
				   }
				}//end confirm if block
				
				word = "";
				
			 }
			 
		  }
	   
	   }
	   //System.out.println("True");
	   return true;
	}//end checkBoard()

	public int[][] getPlateauAttribution() {
		return plateauAttribution;
	}

	public void setPlateauAttribution(int[][] plateauAttribution) {
		this.plateauAttribution = plateauAttribution;
	}

	public int[][] getPlateauAttributionTampon() {
		return plateauAttributionTampon;
	}

	public void setPlateauAttributionTampon(int[][] plateauAttributionTampon) {
		this.plateauAttributionTampon = plateauAttributionTampon;
	}

	public Tuile[][] getPlateauTuiles() {
		return plateauTuiles;
	}
	
	public Tuile getTuile(int col, int lig) {
		return plateauTuiles[lig][col];
	}

	public void setPlateauTuiles(Tuile[][] plateauTuiles) {
		this.plateauTuiles = plateauTuiles;
	}

	public Tuile[][] getPlateauTuilesTampon() {
		return plateauTuilesTampon;
	}

	public void setPlateauTuilesTampon(Tuile[][] plateauTuilesTampon) {
		this.plateauTuilesTampon = plateauTuilesTampon;
	}
	
	public Tuile getTuileTampon(int col, int lig) {
		return plateauTuilesTampon[lig][col];
	}
}
