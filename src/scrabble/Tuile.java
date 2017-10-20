/*******************************************************************************
Nom ................. : Tuile.java

Description ......... : Classe présentant tout les éléments relatifs aux Tuiles

Auteur(s) ........... : LAMPE Ronan

Dernière modification : 18/10/2017

*******************************************************************************/

// Package
package scrabble;

// Imports
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tuile {

	// Propriétés de Tuile
	private char lettre;
	private int valeur;
	private Path img;
	
	// Constructeur vide
	public Tuile() {
		this.lettre = ' ';
		this.valeur = 0;
		this.img = Paths.get("files/tuiles/*.png");
	}
	
	// Constructeur par paramètres
	public Tuile(char Lettre, int Valeur, Path Img) {
		this.lettre = Lettre;
		this.valeur = Valeur;
		this.img = Img;
	}

	// Fonction retournant la lettre
	public char getLettre() {
		return lettre;
	}

	// Fonction retournant la valeur
	public int getValeur() {
		return valeur;
	}

	// Fonction retournant le chemain de l'image
	public Path getImg() {
		return img;
	}
}
