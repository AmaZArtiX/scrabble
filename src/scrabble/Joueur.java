/*
Nom de la classe : Joueur

Classe "Joueur" comprenant les caractéristiques de celui ci
et les fonctions qui lui sont associées

Auteur : Chtairi Yacine

Dernière modification : 20/10/2017
 */ 

package scrabble;

public class Joueur {

	//Propriétés du joueur
	private String nom;
	private int score;
	//Ajouter :    "Un joueur a un chevalet"
				 //"Un joueur a un nombre d'aides limité"
				 //"Un joueur a un historique"
					 
		
	//Constructeur sans paramètres
	public Joueur() {
		this.nom =  "";
		this.score = 0;
	}
	
	//Constructeur avec paramètres
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
