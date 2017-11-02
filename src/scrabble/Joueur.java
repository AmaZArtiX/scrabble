// Package
package scrabble;

/*************************************************************************
 * Nom ...........: Joueur.java
 * Description ...: Classe comprenant les caracteristiques de celui-ci et
 * ...............: les fonctions qui lui sont associees
 * ...............:
 * Auteur(s) .....: YACINE CHTAIRI, RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 YACINE CHTAIRI, RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class Joueur {

	// Nom du joueur 
	private String nom;
	// Score du joueur 
	private int score;
	// Chevalet du joueur 
	private Chevalet chevalet;
	// Chevalet du joueur (tampon)
	private Chevalet chevaletTampon;
	
	/**
	 * TODO
	 * Ajouter un nombre limité d'indice 
	 * Ajouter un historique
	 */
					 
	// Constructeur sans parametres
	public Joueur() {
		
		this.nom =  "";
		this.score = 0;
		this.chevalet = new Chevalet();
		this.chevaletTampon = new Chevalet();
	}
	
	// Constructeur avec nom du Joueur
	public Joueur(String nom) {
		this.nom = nom;
		this.score = 0;
		this.chevalet = new Chevalet();
		this.chevaletTampon = new Chevalet();
	}
	
	// Constructeur avec parametres
	public Joueur(String nom, int score, Chevalet chevalet, Chevalet chevaletTampon) {
		
		this.nom = nom;
		this.score = score;
		this.chevalet = chevalet;
		this.chevaletTampon = chevaletTampon;
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
}
