// Package
package scrabble;

/*************************************************************************
 * Nom ...........: Joueur.java
 * Description ...: Classe "Joueur" comprenant les caracteristiques de celui ci
 * ...............: et les fonctions qui lui sont associees
 * ...............:
 * Auteur(s) .....: YACINE CHTAIRI
 * Version .......: 1.0
 * Copyright .....: © 2017 YACINE CHTAIRI ALL RIGHTS RESERVED
 ************************************************************************/

public class Joueur {

	// Nom du joueur 
	private String nom;
	// Score du joueur 
	private int score;
	// Chevalet du joueur 
	private Chevalet chevalet;
	
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
	}
	
	// Constructeur avec parametres
	public Joueur(String nom, int score, Chevalet chevalet) {
		this.nom = nom;
		this.score = score;
		this.chevalet = chevalet;
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
}
