// Package
package scrabble;

import java.util.ArrayList;

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
}
