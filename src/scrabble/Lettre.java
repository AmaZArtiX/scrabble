// Package
package scrabble;

// Import(s)
import javafx.scene.image.Image;

/*************************************************************************
 * Nom ...........: Lettre.java
 * Description ...: Enumeration de toutes les lettres presentes dans le 
 * ...............: jeu de scrabble, avec leur valeur et leur nombre 
 * ...............: respectifs. Cette classe facilite l'ajout de tuiles
 * ...............: dans le sac.
 * Auteur(s) .....: MAMADOU BAH
 * Version .......: 1.0
 * Copyright .....: © 2017 MAMADOU BAH ALL RIGHTS RESERVED
 ************************************************************************/

public enum Lettre {
	
	A(1, 9, new Image("A.png")),
	B(3, 2, new Image("B.png")),
	C(3, 2, new Image("C.png")),
	D(2, 3, new Image("D.png")),
	E(1, 15, new Image("E.png")),
	F(4, 2, new Image("F.png")),
	G(2, 2, new Image("G.png")),
	H(4, 2, new Image("H.png")),
	I(1, 8, new Image("I.png")),
	J(8, 1, new Image("J.png")),
	K(10, 1, new Image("K.png")),
	L(1, 5, new Image("L.png")),
	M(2, 3, new Image("M.png")),
	N(1, 6, new Image("N.png")),
	O(1, 6, new Image("O.png")),
	P(3, 2, new Image("P.png")),
	Q(8, 1, new Image("Q.png")),
	R(1, 6, new Image("R.png")),
	S(1, 6, new Image("S.png")),
	T(1, 6, new Image("T.png")),
	U(1, 6, new Image("U.png")),
	V(4, 2, new Image("V.png")),
	W(10, 1, new Image("W.png")),
	X(10, 1, new Image("X.png")),
	Y(10, 1, new Image("Y.png")),
	Z(10, 1, new Image("Z.png")),
	JOCKER(0, 2, new Image("Jocker.png"));
	
	int valeur;
	int nombre;
	Image img;
	
	Lettre(int _valeur, int _nombre, Image _img){
		
		valeur = _valeur;
		nombre = _nombre;
		img = _img;	
	}
}
