package scrabble;

/*************************************************************************
 * Nom ...........: Joueur.java
 * Description ...: Classe "Joueur" comprenant les caract�ristiques de celui ci
 * ...............: et les fonctions qui lui sont associ�es
 * ...............:
 * Auteur(s) .....: YACINE CHTAIRI
 * Version .......: 1.0
 * Copyright .....: © 2017 YACINE CHTAIRI ALL RIGHTS RESERVED
 ************************************************************************/

public class Joueur {

	//Propri�t�s du joueur
	private String nom;
	private int score;
	//Ajouter :    "Un joueur a un chevalet"
				 //"Un joueur a un nombre d'aides limit�"
				 //"Un joueur a un historique"
					 
		
	//Constructeur sans param�tres
	public Joueur() {
		this.nom =  "";
		this.score = 0;
	}
	
	//Constructeur avec param�tres
	public Joueur(String nom, int score) {
		this.nom = nom;
		this.score = score;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
