// Package
package scrabble;

// Import(s)
import java.util.ArrayList;
import java.util.Hashtable;

/*************************************************************************
 * Nom ...........: Plateau.java
 * Description ...: Plateau de jeu (15x15) contenant les bonus et les tuiles
 * ...............:
 * ...............:
 * Auteur(s) .....: SIMON BACQUET, RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: Â© 2017 SIMON BACQUET, RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class Plateau extends Jeu{
	
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
	
	// 
	private static ArrayList<ArrayList<String>> permutations = new ArrayList<ArrayList<String>>();
	
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
	 * Restaure les tuiles sauvegardees dans le plateau tampon vers le plateau principal
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
		
		// On vide le chevalet tampon
		chevaletTampon.getTuiles().clear();
		
		// Et on le rempli avec le chevalet principal
		for(int i = 0; i < chevaletPrincipal.getTaille(); i++)
			chevaletTampon.getTuiles().add(chevaletPrincipal.getTuile(i));
	}
	
	/**
	 * Restaure les tuiles du chevalet principal depuis le chevalet tampon
	 * @param chevaletPrincipal
	 * @param chevaletTampon
	 */
	public void restaurerChevalet(Chevalet chevaletPrincipal, Chevalet chevaletTampon) {
		
		// On vide le chevalet principal
		chevaletPrincipal.getTuiles().clear();
		
		// Et on le rempli avec le chevalet tampon
		for(int i = 0; i < chevaletTampon.getTaille(); i++)
			chevaletPrincipal.getTuiles().add(chevaletTampon.getTuile(i));	
	}
	
	/**
	 * Place une tuile sur le plateau tampon
	 * @param x Coordonnee de la ligne
	 * @param y Coordonnee de la colonne
	 * @param direction Direction du mot
	 * @param t Tuile a placer
	 */
	public Coordonnees placerTuile(int x, int y, Tuile t) {
		
		// Depassement de coordonnees
		if(x < 0 || x > (TAILLE-1) || y < 0 || y > (TAILLE-1))
			return new Coordonnees();
		
		// Si case vide, on ajoute
		if(plateauTuilesTampon[x][y] == null)
			plateauTuilesTampon[x][y] = t;
		
		return new Coordonnees(x, y);
	}
	
	/**
	 * Renvoie la liste des tuiles disponibles sur le plateau pour faire un mot
	 * @return liste Liste de tuiles 
	 */
	public Hashtable<Tuile, int[]> getTuilesDisponibles() {
		
		// On declare 
		Hashtable<Tuile, int[]> posTuiles = new Hashtable<Tuile, int[]>();
		
		// Pour chaque ligne
		for(int x = 0; x < TAILLE; x++) {
			
			// Pour chaque colonne
			for(int y = 0; y < TAILLE; y++) {
				
				// On verifie si une tuile est presente
				if(plateauTuiles[x][y] != null) {
					
					// Recherche d'une tuile voisine, si pas, on sort de la boucle
					if(plateauTuiles[x][y+1] != null && plateauTuiles[x+1][y] != null && plateauTuiles[x][y-1] != null && plateauTuiles[x-1][y] != null)
					      break;
					// Tuile voisine a l'horizontale
					if(plateauTuiles[x][y+1] == null && plateauTuiles[x][y-1] == null)
						posTuiles.put(plateauTuiles[x][y], new int[] {x, y});
					// Tuile voisine a la verticale
					else if(plateauTuiles[x+1][y] == null && plateauTuiles[x-1][y] == null)
						posTuiles.put(plateauTuiles[x][y], new int[] {x, y});
				}
			}
		}
		
		// On affiche 
		System.out.println(posTuiles);
		
		// On retourne 
		return posTuiles;
	}
	
	// Fonction permettant le retour du hashtable contenant tout les mots jouables (et les positions de chaque tuile)
	public Hashtable<ArrayList<Tuile>, int[][]> motsJouables(Hashtable<Tuile, int[]> posTuiles) {
		Hashtable<ArrayList<Tuile>, int[][]> motsJouables = new Hashtable<ArrayList<Tuile>, int[][]>();
		
		for (Tuile tuile : posTuiles.keySet()) {
			
			// On declare les ArrayLists ligne et colonne (qui contiendront les mots crees avant verification)
			ArrayList<Tuile> ligne = new ArrayList<Tuile>();
			ArrayList<Tuile> colonne = new ArrayList<Tuile>();
			
			// On initialise la ligne avec la tuile donnee
			ligne.add(plateauTuiles[posTuiles.get(tuile)[0]][posTuiles.get(tuile)[1]]);
			
			// On initialise la colonne avec la tuile donnee
			colonne.add(plateauTuiles[posTuiles.get(tuile)[0]][posTuiles.get(tuile)[1]]);
			
			/////////////////////////////////////////////////////////////////////////////
			
			// On parcours toutes les lignes de la colonne (vers le bas)
			int fc;
			for(fc=posTuiles.get(tuile)[0]; fc<TAILLE-1; fc++) {
				
				// 
				//System.out.println("Fin -> " + fc);
				
				// On verifie si 1 case apres, la bordure du plateau n'est pas atteinte
				if((fc+1) < TAILLE) {
					
					// On verifie la presence d'une tuile sur la case suivante
					if(plateauTuiles[fc+1][posTuiles.get(tuile)[1]] != null) {
						
						// On ajoute la tuile suivante a la colonne
						colonne.add(plateauTuiles[fc+1][posTuiles.get(tuile)[1]]);
					} else break;
				} else break;
			}
			
			// On parcours toutes les lignes de la colonne (vers le haut)
			int dc;
			for(dc=posTuiles.get(tuile)[0]; dc>0; dc--) {
				
				// 
				//System.out.println("Début -> " + dc);
				
				// On verifie si 1 case avant, la bordure du plateau n'est pas atteinte
				if((dc-1) >= 0) {
					
					// On verifie la presence d'une tuile sur la case precedente
					if(plateauTuiles[dc-1][posTuiles.get(tuile)[1]] != null) {
						
						// On ajoute la tuile precedente a la colonne
						colonne.add(0, plateauTuiles[dc-1][posTuiles.get(tuile)[1]]);
					} else break;
				} else break;
			}
			
			// On affiche les indices debut & la fin de la colonne
			//System.out.println("Colonne : " + posTuiles.get(tuile)[1] + " -> Ligne de début : " + dc + " Ligne de fin : " + fc);
			
			// On affiche la colonne (test)
			//System.out.println("Colonne : " + posTuiles.get(tuile)[1] + " " + colonne);
			
			// On declare
			ArrayList<String> lettresPlateau = new ArrayList<String>();
			lettresPlateau.addAll(lettresTuiles(colonne));
			
			// 
			System.out.println("Colonne : " + posTuiles.get(tuile)[1] + " -> Lettre(s) plateau : " + lettresPlateau + " -> Ligne de début : " + dc + " | Ligne de fin : " + fc);
			
			// On declare
			ArrayList<String> lettresColonne = new ArrayList<String>();
			lettresColonne.add(concatArray(lettresPlateau).toLowerCase());
			lettresColonne.addAll(lettresTuiles(Joueurs.get(joueur).getChevalet().getTuiles()));
			
			// 
			//System.out.println("Colonne : " + posTuiles.get(tuile)[1] + " Lettre(s) : " + lettresColonne);

			// On cree les permutations
			subsets(lettresColonne, lettresPlateau);
			
			// On recupere les permutations
			ArrayList<ArrayList<String>> mots = new ArrayList<ArrayList<String>>();
			mots = (ArrayList<ArrayList<String>>) permutations.clone();
			
			// 
			//System.out.println("Liste des permutations : " + mots);
			
			// On recupere les mots valides des permutations
			ArrayList<ArrayList<String>> motsValides = new ArrayList<ArrayList<String>>();
			motsValides = motsValides(mots);
			
			// 
			//System.out.println("Liste des permutations valides : " + motsValides);
			
			// On ajoute les mots jouables dans le hashtable
			motsJouables(motsJouables, motsValides, 'c', posTuiles.get(tuile)[1], dc, fc);
			
			// 
			//System.out.println("Liste des permutations jouables : " + motsJouables);
			System.out.println();
			
			//
			lettresColonne = new ArrayList<String>();
			
			// 
			lettresPlateau = new ArrayList<String>();
			
			// On reinitialise les permutations
			permutations = new ArrayList<ArrayList<String>>();
			
			// On reinitialise les mots
			mots = new ArrayList<ArrayList<String>>();
			
			// On reinitialise les mots valides
			motsValides = new ArrayList<ArrayList<String>>();
			
			///////////////////////////////////////////////////////////////////////////
			
			// On parcours toutes les colonnes de la ligne (vers la droite)
			int fl;
			for(fl=posTuiles.get(tuile)[1]; fl<TAILLE-1; fl++) {

				// 
				//System.out.println("Fin -> " + fl);
				
				// On verifie si 1 case apres, la bordure du plateau n'est pas atteinte
				if((fl+1) < TAILLE) {
					
					// On verifie la presence d'une tuile sur la case suivante
					if(plateauTuiles[posTuiles.get(tuile)[0]][fl+1] != null) {
						
						// On ajoute la tuile suivante a la colonne
						ligne.add(plateauTuiles[posTuiles.get(tuile)[0]][fl+1]);
					} else break;
				} else break;
			}
			
			// On parcours toutes les colonnes de la ligne (vers la gauche)
			int dl;
			for(dl=posTuiles.get(tuile)[1]; dl>0; dl--) {

				// 
				//System.out.println("Début -> " + dl);
				
				// On verifie si 1 case avant, la bordure du plateau n'est pas atteinte
				if((dl-1) >= 0) {
					
					// On verifie la presence d'une tuile sur la case precedente
					if(plateauTuiles[posTuiles.get(tuile)[0]][dl-1] != null) {
						
						// On ajoute la tuile precedente a la colonne
						ligne.add(0, plateauTuiles[posTuiles.get(tuile)[0]][dl-1]);
					} else break;
				} else break;
			}
			
			// On affiche les indices debut & la fin de la ligne
			//System.out.println("Ligne : " + posTuiles.get(tuile)[0] + " -> Colonne de début : " + dl + " Colonne de fin : " + fl);
			
			// On affiche la ligne (test)
			//System.out.println("Ligne : " + posTuiles.get(tuile)[0] + " " + ligne);
			
			// 
			lettresPlateau.addAll(lettresTuiles(ligne));
			
			// 
			System.out.println("Ligne : " + posTuiles.get(tuile)[0] + " -> Lettre(s) plateau : " + lettresPlateau + " -> Colonne de début : " + dl + " | Colonne de fin : " + fl);
			
			// 
			ArrayList<String> lettresLigne = new ArrayList<String>();
			lettresLigne.add(concatArray(lettresPlateau).toLowerCase());
			lettresLigne.addAll(lettresTuiles(Joueurs.get(joueur).getChevalet().getTuiles()));
			
			// 
			//System.out.println("Ligne : " + posTuiles.get(tuile)[0] + " Lettre(s) : " + lettresLigne);
			
			// On cree les permutations
			subsets(lettresLigne, lettresPlateau);
			
			// On recupere les permutations
			mots = (ArrayList<ArrayList<String>>) permutations.clone();
			
			// 
			//System.out.println("Liste des permutations : " + mots);
			
			// On recupere les mots valides des permutations
			motsValides = motsValides(mots);
			
			// 
			//System.out.println("Liste des permutations valides : " + motsValides);
			
			// On ajoute les mots jouables dans le hashtable
			motsJouables(motsJouables, motsValides, 'l', posTuiles.get(tuile)[0], dl, fl);
			
			// 
			//System.out.println("Liste des permutations jouables : " + motsJouables);
			System.out.println();
			
			// 
			lettresLigne = new ArrayList<String>();

			// 
			lettresPlateau = new ArrayList<String>();
			
			// On reinitialise les permutations
			permutations = new ArrayList<ArrayList<String>>();
			
			// On reinitialise les mots
			mots = new ArrayList<ArrayList<String>>();
			
			// On reinitialise les mots valides
			motsValides = new ArrayList<ArrayList<String>>();
		}
		
		System.out.println("Liste des permutations jouables : " + motsJouables);
		System.out.println();
		
		// On retourne le hashtable des mots jouables
		return motsJouables;
	}
	
	// Procedure d'ajout des mots jouables au hashtable
	private void motsJouables(Hashtable<ArrayList<Tuile>, int[][]> motsJouables, ArrayList<ArrayList<String>> motsValides, char orientation, int pos, int debut, int fin) {
		// 
		for (ArrayList<String> mot : motsValides) {
			ArrayList<Tuile> tuiles = getTuiles(mot);
			int[][] coordonnees = new int[tuiles.size()][2];
			int avant = debut, apres = fin;
			boolean ap = false, jouable = true;
			
			// 
			for (String element : mot) {
				String upElement = element.toUpperCase();
				if (!element.equals(upElement)) {
					ap = true;
					element = element.toUpperCase();
				} else {
					if (!ap) avant--;
					else if (ap) apres++;
				}
			}
			
			// 
			int ind = 0;
			for (int j = avant; j <= apres; j++) {
				switch (orientation) {
				case 'l':
					coordonnees[ind][0] = pos;
					coordonnees[ind][1] = j;
					break;
				case 'c':
					coordonnees[ind][0] = j;
					coordonnees[ind][1] = pos;
					break;
				default:
					break;
				}
				ind++;
			}
			
			// Verification de la jouabilite d'un mot
			for(int i=0; i<tuiles.size(); i++) {
				if(coordonnees[i][0] >= 0 & coordonnees[i][0] < TAILLE & coordonnees[i][1] >= 0 & coordonnees[i][1] < TAILLE) {
					if (plateauTuiles[coordonnees[i][0]][coordonnees[i][1]] != null)
						if (!plateauTuiles[coordonnees[i][0]][coordonnees[i][1]].equals(tuiles.get(i)))
							jouable = false;
					if(coordonnees[i][0]+1 < TAILLE)
						if(orientation == 'l' & plateauTuiles[coordonnees[i][0]+1][coordonnees[i][1]] != null)
							if (plateauTuiles[coordonnees[i][0]+1][coordonnees[i][1]] instanceof Tuile)
								jouable = false;
					if(coordonnees[i][0]-1 >= 0)
						if(orientation == 'l' & plateauTuiles[coordonnees[i][0]-1][coordonnees[i][1]] != null)
							if (plateauTuiles[coordonnees[i][0]-1][coordonnees[i][1]] instanceof Tuile)
								jouable = false;
					if(coordonnees[i][1]+1 < TAILLE)
						if(orientation == 'c' & plateauTuiles[coordonnees[i][0]][coordonnees[i][1]+1] != null)
							if (plateauTuiles[coordonnees[i][0]][coordonnees[i][1]+1] instanceof Tuile)
								jouable = false;
					if(coordonnees[i][1]-1 >= 0)
						if(orientation == 'c' & plateauTuiles[coordonnees[i][0]][coordonnees[i][1]-1] != null)
							if (plateauTuiles[coordonnees[i][0]][coordonnees[i][1]-1] instanceof Tuile)
								jouable = false;
				}
			}
			
			// 
			if(jouable)
				if(motsJouables.get(tuiles) != null) {
					if(!motsJouables.get(tuiles).equals(coordonnees))
						motsJouables.put(tuiles, coordonnees);
				} else {
					motsJouables.put(tuiles, coordonnees);
				}
		}
	}
	
	// Fonction de recuperation des tuiles d'une permutation
	private ArrayList<Tuile> getTuiles(ArrayList<String> mot){
		ArrayList<Tuile> tuiles = new ArrayList<Tuile>();
		for (String partie : mot) {
			if(partie.length() == 1) {
				tuiles.add(new Tuile(partie.toUpperCase().charAt(0)));
			} else if(partie.length() > 1) {
				for(int i=0; i<partie.length(); i++) {
					tuiles.add(new Tuile(partie.toUpperCase().charAt(i)));
				}
			}
		}
		return tuiles;
	}
	
	// Fonction de retour des mots valides
	private ArrayList<ArrayList<String>> motsValides(ArrayList<ArrayList<String>> motsAValides) {
		ArrayList<ArrayList<String>> motsValides = new ArrayList<ArrayList<String>>();
		for (ArrayList<String> lettres : motsAValides) {
			// Position eventuelle du tri
			String mot = concatArray(lettres);
			if(dictionnaire.existe(mot.toUpperCase()))
				motsValides.add(lettres);
		}
		return motsValides;
	}
	
	// Fonction de retour des lettres de tuiles
	private ArrayList<String> lettresTuiles(ArrayList<Tuile> tuiles){
		ArrayList<String> lettres = new ArrayList<String>();
		for (Tuile tuile : tuiles) {
			lettres.add(String.valueOf(tuile.getLettre()));
		}
		return lettres;
	}
	
	// print n! permutation of the elements of array a (not in order)    
    private static void perms(ArrayList<String> str) {
    	int n = str.size();
    	perms(str, n);
    }
    
    // 
	private static void perms(ArrayList<String> str, int n) {
    	if (n == 1) {
			permutations.add((ArrayList<String>) str.clone());
		}
    	for(int i=0; i<n; i++) {
    		perm(str, i, n-1);
    		perms(str, n-1);
    		perm(str, i, n-1);
    	}
    }
    
    // swap the characters at indices i and j
    private static void perm(ArrayList<String> str, int i, int j) {
    	String s = str.get(i);
    	str.set(i, str.get(j));
    	str.set(j, s);
    }
    
    // Fonction de recherche des sous-listes
    private static void subsets(ArrayList<String> perms, ArrayList<String> lettresPlateau) {
    	for(int i=2; i<perms.size(); i++) {
    		boolean[] ajoute = new boolean[perms.size()];
    		subsets(perms, ajoute, 0, i, concatArray(lettresPlateau).toLowerCase());
    	}
    }
    
    //Fonction d'aide a la recherche des sous-listes
    private static void subsets(ArrayList<String> perms, boolean[] ajoute, int debut, int reste, String plateau) {
    	if(reste == 0) {
    		ArrayList<String> ssliste = new ArrayList<String>();
    		for(int i=0; i<ajoute.length; i++) {
    			if(ajoute[i]) {
    				ssliste.add(perms.get(i));
    			}
    		}
    		
    		// 
    		boolean trouve = false;			
    		for (String element : ssliste) {
    			
    			// 
				if (element.equals(plateau)) {
					trouve = true;
				}
			}
    		if(trouve) perms(ssliste);
    	} else {
    		for(int i=debut; i<perms.size(); i++) {
    			if(!ajoute[i]) {
    				ajoute[i] = true;
    				subsets(perms, ajoute, i+1, reste-1, plateau);
    				ajoute[i] = false;
    			}
    		}
    	}
    }
    
	// 
	private static String concatArray(ArrayList<String> lettres) {
		String mot = "";
		for (String lettre : lettres) {
			mot += lettre;
		}
		return mot;
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
			
			case "  ":
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
				return 0;
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
	 * @param tuilesJouees Liste des coordonnees ou sont placÃ©es les tuiles qui forment le mot
	 * @param scrabble Bonus de scrabble
	 * @return scoreMot le score effectue 
	 */
	public int calculScoreMot(ArrayList<Coordonnees> tuilesJouees, boolean scrabble) {
	
		// Score total m
		int scoreMot = 0;
		// Score pour une lettre 
		int scoreLettre = 0;
		// Compteur de cases "MD"
		int cptMotDouble = 0;
		// Compteur de case "MT"
		int cptMotTriple = 0;
		
		for(Coordonnees c : tuilesJouees) {
			
			// On rÃ©cupere les coordonnees
			int x = c.getX();
			int y = c.getY();
			
			// Recuperation de la valeur d'une tuile
			scoreLettre = plateauTuilesTampon[x][y].getValeur();
			
			if(plateauAttribution[x][y] == 0) {
				
				// Multiplication de la valeur de la tuile par le bonus de la case
				scoreLettre *= getBonus(plateauBonus[x][y]);
				
				// Incrementation du compteur de cases "MD"
				if(plateauBonus[x][y].equals("MD"))
					cptMotDouble++;
				// IncrÃ©mentation du compteur de cases "MT"
				else if(plateauBonus[x][y].equals("MT"))
					cptMotTriple++;
				
				// Bonus attribuÃ©
				plateauAttribution[x][y] = 1;
			}
			
			// Incrementation du score total
			scoreMot += scoreLettre;
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
		
		if(scrabble)
			scoreMot += 50;
			
		return scoreMot;
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
