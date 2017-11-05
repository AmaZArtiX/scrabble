// Package
package scrabble;

// Import(s)
import javafx.scene.image.Image;

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
	private Image img;
	
	// Constructeur vide
	public Tuile() {
		this.lettre = '*';
		this.valeur = 0;
		this.img = new Image("Jocker.png");
	}
	
	public Tuile(char _lettre, int _valeur) {
		
		lettre = _lettre;
		valeur = _valeur;
		this.img = new Image("A.png");
	}
	
	// Constructeur par parametres
	public Tuile(char Lettre, int Valeur, Image Img) {
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
	public Image getImg() {
		return img;
	}
	
	/**
	 * Compare l'egalite de deux tuiles
	 */
	public boolean equals(Object o) {
		
		boolean rep;
		Tuile t = (Tuile)o;
		
		rep = lettre == t.lettre && 
				valeur == t.valeur;
		
		return rep;
	}

	@Override
	public String toString() {
		return "Tuile [" + lettre + "]";
	}
}
