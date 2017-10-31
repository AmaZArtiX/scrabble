// Package
package scrabble;

// Import(s)
import java.nio.file.Path;
import java.nio.file.Paths;

/*************************************************************************
 * Nom ...........: Tuile.java
 * Description ...: Classe presentant tout les elements relatifs aux Tuiles
 * ...............:
 * ...............:
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class Tuile {

	// Proprietes de Tuile
	private char lettre;
	private int valeur;
	private Path img;
	
	// Constructeur vide
	public Tuile() {
		this.lettre = ' ';
		this.valeur = 0;
		this.img = Paths.get("Jocker.png");
	}
	
	// Constructeur par parametres
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

	// Fonction retournant le chemin de l'image
	public Path getImg() {
		return img;
	}

	@Override
	public String toString() {
		return "Tuile [lettre=" + lettre + ", valeur=" + valeur + ", img=" + img + "]";
	}
}
