// Package
package scrabble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*************************************************************************
 * Nom ...........: Joueur.java
 * Description ...: Classe comprenant les caracteristiques de celui-ci et
 * ...............: les fonctions qui lui sont associees
 * ...............:
 * Auteur(s) .....: YACINE CHTAIRI, RONAN LAMPE, SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: © 2017 YACINE CHTAIRI, RONAN LAMPE, SIMON BACQUET ALL RIGHTS RESERVED
 ************************************************************************/

public class Joueur {

	// Nom du joueur 
	private String nom;
	// Score du joueur 
	private int score;
	// Nombre d'indices disponibles
	private int nbIndices;
	// Chevalet du joueur 
	private Chevalet chevalet;
	// Chevalet du joueur (tampon)
	private Chevalet chevaletTampon;
	// Coordonnees dans l'ordre des tuiles jouees 
	private ArrayList<Coordonnees> motJoue;
	
	boolean currentPlayer = false;
	
	/**
	 * TODO
	 * Ajouter un historique
	 */
	
	// Constructeur sans parametres
	public Joueur() {
		
		this.nom = "";
		this.score = 0;
		this.nbIndices = 5;
		this.chevalet = new Chevalet();
		this.chevaletTampon = new Chevalet();
		this.motJoue = new ArrayList<>();
	}
	
	// Constructeur avec nom du Joueur
	public Joueur(String nom) {
		this.nom = nom;
		this.score = 0;
		this.chevalet = new Chevalet();
		this.chevaletTampon = new Chevalet();
		this.motJoue = new ArrayList<>();
	}
	
	// Constructeur avec parametres
	public Joueur(String nom, int score, Chevalet chevalet, Chevalet chevaletTampon) {
		
		this.nom = nom;
		this.score = score;
		this.chevalet = chevalet;
		this.chevaletTampon = chevaletTampon;
		this.motJoue = new ArrayList<>();
	}
	
	// Renvoie le nom du joueur 
	public String getNom() {
		
		return nom;
	}

	// Modifie le nom du joueur 
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	// Renvoie le score du joueur
	public int getScore() {
		return score;
	}

	// Modifie le score du joueur
	public void setScore(int score) {
		this.score = score;
	}
	
	// Modifie le chevalet du joueur
	public void setChevalet(Chevalet _chevalet) {
		
		chevalet = _chevalet;
	}
	
	// Renvoie le chevalet du joueur 
	public Chevalet getChevalet() {
		
		return chevalet;
	}
	
	// Modifie le chevalet tampon du joueur
	public void setChevaletTampon(Chevalet _chevaletTampon) {
		
		chevaletTampon = _chevaletTampon;
	}
	
	// Renvoie le chevalet tampon du joueur
	public Chevalet getChevaletTampon() {
		
		return chevaletTampon;
	}

	// Renvoie le nb d'indices disponible du joueur
	public int getNbIndices() {
		return nbIndices;
	}

	// Modifie le nb d'indices disponible pour le joueur
	public void setNbIndices(int nbIndices) {
		this.nbIndices = nbIndices;
	}
	
	/**
	 * Renvoie les coordonnees des tuiles jouees 
	 * @return motJoue La liste des coordonnees des tuiles jouees
	 * @author Simon Bacquet
	 */
	public ArrayList<Coordonnees> getMotJoue() {
		
		return motJoue;
	}
	
	/**
	 * Ajoute les coordonnees d'une tuile jouee 
	 * @param c Coordonnées de la tuile
	 * @author Simon Bacquet
	 */
	public void ajouterCoordonnees(Coordonnees c) {
		
		motJoue.add(c);
	}
	
	/**
	 * Efface les coordonnees des tuiles jouees
	 * @author Simon Bacquet
	 */
	public void effacerMotJoue() {
		
		motJoue.clear();
	}
	
	/**
	 * Permet de vérifier le bon placement d'un mot sur le plateau de jeu
	 * @param p Plateau de jeu
	 * @return resultat Booleen de retour sur le placement des tuiles jouees
	 * @author Simon Bacquet
	 */
	public boolean verifierMotJoue(Plateau p) {
		
		boolean resultat = true;
		// Coordonnee x de la premiere tuile jouee
		int xDebut = motJoue.get(0).getX();
		// Coordonnee y de la premiere tuile jouee
		int yDebut = motJoue.get(0).getY();
		// Coordonnee x de la derniere tuile jouee
		int xFin = motJoue.get(motJoue.size()-1).getX();
		// Coordonnee y de la derniere tuile jouee
		int yFin = motJoue.get(motJoue.size()-1).getY();
		
		// Execution de la vérification si le joueur a placé plus d'une tuile
		if(motJoue.size() > 1) {
			
			// Non correspondance des coordonnées de début et de fin
			if(xDebut != xFin && yDebut != yFin)
				resultat = false;
			else {
				
				// Vérification de l'égalité des x de début et de fin 
				if(xDebut == xFin) {
					
					// Parcours des coordonnées en x pour comparer les égalités
					for(int i = 0; i < motJoue.size(); i++) {
						
						if(motJoue.get(i).getX() != xDebut)
							resultat = false;
					}
					
					// Vérification de l'existence d'un chemin horizontal allant de gauche à droite
					if(resultat && yDebut < yFin) {
												
						for(int y = yDebut; y < yFin; y++) {
							
							// Vérification de l'existence d'une tuile voisine
							if(p.getTuileTampon(y+1, xDebut) == null) {
								resultat = false;
								break;
							}
						}
					}
					// Vérification de l'existence d'un chemin horizontal allant de droite à gauche
					else if(resultat && yDebut > yFin) {
						
						for(int y = yDebut; y > yFin; y--) {
							
							// Vérification de l'existence d'une tuile voisine
							if(p.getTuileTampon(y-1, xDebut) == null) {
								resultat = false;
								break;
							}
						}
					}
				}
				// Vérification de l'égalité des y de début et de fin
				else if(yDebut == yFin){
					
					// Parcours des coordonnées en y pour comparer les égalités
					for(int i = 0; i < motJoue.size(); i++) {
						
						if(motJoue.get(i).getY() != yDebut)
							resultat = false;
					}
					
					// Vérification de l'existence d'un chemin vertical allant de bas en haut
					if(resultat && xDebut < xFin) {
						
						for(int x = xDebut; x < xFin; x++) {
							
							// Vérification de l'existence d'une tuile voisine
							if(p.getTuileTampon(yDebut, x+1) == null) {
								
								resultat = false;
								break;
							}
						}
					}
					// Vérification de l'existence d'un chemin vertical allant de haut en bas
					else if(resultat && xDebut > xFin) {
						
						for(int x = xDebut; x > xFin; x--) {
							
							// Vérification de l'existence d'une tuile voisine
							if(p.getTuileTampon(yDebut, x-1) == null) {
								
								resultat = false;
								break;
							}
						}
					}
				}
			}
		}
		
		return resultat;
	}
	
	/**
	 * Renvoie la direction dans laquelle le mot a été joué
	 * @param p Plateau de jeu
	 * @param x Coordonnee en abscisse
	 * @param y Coordonnee en ordonee
	 * @return diction Chaine de caractère indiquant la direction
	 * @author Simon Bacquet
	 */
	public String getDirection(Plateau p, int x, int y) {
		
		String direction = "";
		
		// Mot placé de gauche à droite
		if(p.getTuileTampon(y+1, x) != null && p.getTuileTampon(y, x+1) == null && p.getTuileTampon(y-1, x) == null && p.getTuileTampon(y, x-1) == null)
			direction = "droite";
		// Mot placé de droite à gauche
		else if(p.getTuileTampon(y-1, x) != null && p.getTuileTampon(y, x-1) == null && p.getTuileTampon(y+1, x) == null && p.getTuileTampon(y, x+1) == null)
			direction = "gauche";
		// Mot placé de haut en bas
		else if(p.getTuileTampon(y, x+1) != null && p.getTuileTampon(y-1, x) == null && p.getTuileTampon(y, x-1) == null && p.getTuileTampon(y+1, x) == null)
			direction = "bas";
		// Mot placé de bas en haut
		else if(p.getTuileTampon(y, x-1) != null && p.getTuileTampon(y+1, x) == null && p.getTuileTampon(y, x+1) == null && p.getTuileTampon(y-1, x) == null)
			direction = "haut";
		
		return direction;
	}
	
	/**
	 * Renvoie les coordonnees du mot complet formé par le joueur
	 * @param p Plateau de jeu
	 * @return motJoueComplet les coordonnees dans l'ordre d'un mot formé
	 * @author Simon Bacquet
	 */
	public ArrayList<Coordonnees> getMotJoueComplet(Plateau p){
		
		// Liste des coordonnees des tuiles
		ArrayList <Coordonnees> motJoueComplet = new ArrayList<>();
		// x de la premiere tuile jouee
		int xDebut = motJoue.get(0).getX();
		// y de la premiere tuile jouee 
		int yDebut = motJoue.get(0).getY();
		// x de la derniere tuile jouee
		int xFin = motJoue.get(motJoue.size()-1).getX();
		// y de la derniere tuile jouee
		int yFin = motJoue.get(motJoue.size()-1).getY();
		// direction dans laquelle le mot a ete joue
		String direction = "";
		
		// Une seule tuile placée par le joueur
		if(motJoue.size() == 1) {
			
			int xBis = motJoue.get(0).getX(), yBis = motJoue.get(0).getY();
			
			// On recupere la direction si x et y ne sont pas les bords du plateau
			if(xBis > 0  && xBis < 14 && yBis > 0 && yBis < 14)
					direction = getDirection(p, xBis, yBis);
			else {
				
				// Tuile unique placée dans sur la colonne 0
				if((xBis > 0 && xBis < 14) && yBis == 0) {
					
					if(p.getTuileTampon(yBis, xBis-1) == null && p.getTuileTampon(yBis+1, xBis) == null)
						direction = "bas";
					else if(p.getTuileTampon(yBis, xBis-1) == null && p.getTuileTampon(yBis, xBis+1) == null)
						direction = "droite";
					else if(p.getTuileTampon(yBis, xBis+1) == null && p.getTuileTampon(yBis+1, xBis) == null)
						direction = "haut";
				}
				// Tuile unique placée sur la ligne 0
				else if((yBis > 0 && yBis < 14) && xBis == 0) {
					
					if(p.getTuileTampon(yBis+1, xBis) == null && p.getTuileTampon(yBis, xBis+1) == null)
						direction = "gauche";
					else if(p.getTuileTampon(yBis-1, xBis) == null && p.getTuileTampon(yBis, xBis+1) == null)
						direction = "droite";
					else if(p.getTuileTampon(yBis-1, xBis) == null && p.getTuileTampon(yBis+1, xBis) == null)
						direction = "bas";
				}
				// Tuile unique placée sur la colonne 14
				else if((xBis > 0 && xBis < 14) && yBis == 14) {
					
					if(p.getTuileTampon(yBis-1, xBis) == null && p.getTuileTampon(yBis, xBis+1) == null)
						direction = "haut";
					else if(p.getTuileTampon(yBis-1, xBis) == null && p.getTuileTampon(yBis, xBis-1) == null)
						direction = "bas";
					else if(p.getTuileTampon(yBis, xBis-1) == null && p.getTuileTampon(yBis, xBis+1) == null)
						direction = "gauche";
				}
				// Tuile unique placéee sur la ligne 14
				else if((yBis > 0 && yBis < 14) && xBis == 14) {
					
					if(p.getTuileTampon(yBis, xBis-1) == null && p.getTuileTampon(yBis+1, xBis) == null)
						direction = "gauche";
					else if(p.getTuileTampon(yBis, xBis-1) == null && p.getTuileTampon(yBis-1, xBis) == null)
						direction = "droite";
					else if(p.getTuileTampon(yBis-1, xBis) == null && p.getTuileTampon(yBis+1, xBis) == null)
						direction = "haut";
				}
				// Tuile unique placée sur la case (0,0)
				else if(xBis == 0 && yBis == 0) {
					
					if(p.getTuileTampon(yBis+1, xBis) == null)
						direction = "bas";
					else if(p.getTuileTampon(yBis, xBis+1) == null)
						direction = "droite";
				}
				// Tuile unique placée sur la case (0, 14)
				else if(xBis == 0 && yBis == 14) {
					
					if(p.getTuileTampon(yBis-1, xBis) == null)
						direction = "bas";
					else if(p.getTuileTampon(yBis, xBis+1) == null)
						direction = "gauche";
				}
				// Tuile unique placée sur la case (14, 14)
				else if(xBis == 14 && yBis == 14) {
					
					if(p.getTuileTampon(yBis-1, xBis) == null)
						direction = "haut";
					else if(p.getTuileTampon(yBis, xBis-1) == null)
						direction = "gauche";
				}
				// Tuile unique placée sur la case (14, 0)
				else if(xBis == 14 && yBis == 0) {
					
					if(p.getTuileTampon(yBis+1, xBis) == null)
						direction = "haut";
					else if(p.getTuileTampon(yBis, xBis-1) == null)
						direction = "droite";
				}
			}
			
			// Recherche des coordonnées pour une unique tuile posée
			// Recherche des coordonnees de gauche à droite
			if(direction.equals("droite")) {
				
				int y = yDebut;
				
				// Vérification de l'existence d'une tuile voisine
				while(y <= 13 && p.getTuileTampon(y+1, xDebut) != null) {
					
					// Ajout des coordonnes
					motJoueComplet.add(new Coordonnees(xDebut, y));
					y++;
				}
				
				motJoueComplet.add(new Coordonnees(xDebut, y));
			}
			// Recherche des coordonnees de droite à gauche
			else if(direction.equals("gauche")) {
				
				int y = yDebut;
				
				// Vérification de l'existence d'une tuile voisine
				while(y >= 1 && p.getTuileTampon(y-1, xDebut) != null) {
					
					// Ajout des coordonnees
					motJoueComplet.add(new Coordonnees(xDebut, y));
					y--;
				}
				
				motJoueComplet.add(new Coordonnees(xDebut, y));
				
				// Inversion des coordonnees, pour former le mot dans le bon sens
				Collections.reverse(motJoueComplet);
			}
			// Recherche des coordonnees de haut en bas 
			else if(direction.equals("bas")) {
				
				int x = xDebut;
				
				// Vérification de l'existence d'une tuile voisine
				while(x <= 13 && p.getTuileTampon(yDebut, x+1) != null) {
					
					// Ajout des coordonnees
					motJoueComplet.add(new Coordonnees(x, yDebut));
					x++;
				}
				
				motJoueComplet.add(new Coordonnees(x, yDebut));
			}
			// Recherche des coordonnees de bas en haut
			else if(direction.equals("haut")) {
				
				int x = xDebut;
				
				// Vérification de l'existence d'une tuile voisine
				while(x >= 1 && p.getTuileTampon(yDebut, x-1) != null){
					
					// Ajout des coordonnees
					motJoueComplet.add(new Coordonnees(x, yDebut));
					x--;
				}
				
				motJoueComplet.add(new Coordonnees(x, yDebut));
				
				// Inversion des coordonnees, pour former le mot dans le bon sens
				Collections.reverse(motJoueComplet);
			}
		}
		// Tuiles placees > 1
		else {
			
			// Recherche de coordonnees en x (chemin horizontal)
			if(xDebut == xFin) {
				
				int y = yDebut;
				
				// Recherche d'une tuile vosine sur les colonnes inferieures a la tuile de depart
				while(y >= 1 && p.getTuileTampon(y-1, xDebut) != null) {
					
					// Ajout des coordonnees
					motJoueComplet.add(new Coordonnees(xDebut, y));
					y--;
				}
				
				motJoueComplet.add(new Coordonnees(xDebut, y));
				
				if(yDebut < 14) {
					if(p.getTuileTampon(yDebut+1, xDebut) != null) {
						
						y = yDebut+1;
						
						// Recherche d'une tuile vosine sur les colonnes superieures a la tuile de depart
						while(y <= 13 && p.getTuileTampon(y+1, xDebut) != null) {
							
							// Ajout des coordonnees
							motJoueComplet.add(new Coordonnees(xDebut, y));
							y++;
						}
						
						motJoueComplet.add(new Coordonnees(xDebut, y));
					}
				}
				
				// Tri des coordonnees en y pour mettre le mot dans le bon sens
				Collections.sort(motJoueComplet, new Comparator<Coordonnees>() {

					@Override
					public int compare(Coordonnees c1, Coordonnees c2) {
						return c1.getY() - c2.getY();
					}
				});
			}
			// Recherche de coordonnees en y (chemin vertical)
			else if(yDebut == yFin) {
				
				int x = xDebut;
				
				// Recherche d'une tuile vosine sur les lignes inferieures a la tuile de depart
				while(x >= 1 && p.getTuileTampon(yDebut, x-1) != null) {
					
					// Ajout des coordonnees
					motJoueComplet.add(new Coordonnees(x, yDebut));
					x--;
				}
				
				motJoueComplet.add(new Coordonnees(x, yDebut));
				
				if(xDebut < 14) {
					if(p.getTuileTampon(yDebut, xDebut+1) != null) {
					
						x = xDebut+1;
						
						// Recherche d'une tuile vosine sur les lignes superieures a la tuile de depart
						while(x <= 13 && p.getTuileTampon(yDebut, x+1) != null) {
							
							// Ajout des coordonnees
							motJoueComplet.add(new Coordonnees(x, yDebut));
							x++;
						}
						
						motJoueComplet.add(new Coordonnees(x, yDebut));
					}
				}
				
				// Tri des coordonnees en x pour mettre le mot dans le bon sens
				Collections.sort(motJoueComplet, new Comparator<Coordonnees>() {
					
					@Override
					public int compare(Coordonnees c1, Coordonnees c2) {
						return c1.getX() - c2.getX();
					}
				});
			}
		}
			
		return motJoueComplet;
	}
	
	/**
	 * Renvoie la liste des tuiles jouees selon leurs coordonnees
	 * @param p Plateau de jeu
	 * @param motJoueComplet Coordonnees des tuiles placees 
	 * @return Tuiles placées
	 */
	public ArrayList<Tuile> getTuilesJouees(Plateau p, ArrayList<Coordonnees> motJoueComplet){
		
		ArrayList<Tuile> mot = new ArrayList<>();
		
		for(Coordonnees c : motJoueComplet) {
			
			mot.add(p.getTuileTampon(c.getY(),c.getX()));
		}
			
		return mot;
	}

	//ajoute le nouveau score a l'encien
	//@param nouveau score
	public void addScore(int newScore) {
		// TODO Auto-generated method stub
		score += newScore;
	}

	public void setCurrentPlayer(boolean value) {
		// TODO Auto-generated method stub
		currentPlayer = value;
	}
}
