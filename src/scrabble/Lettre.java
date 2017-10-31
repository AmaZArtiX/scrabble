// Package
package scrabble;

// Import(s)
import java.nio.file.Path;
import java.nio.file.Paths;

/*************************************************************************
 * Nom ...........: Lettre.java
 * Description ...: Enumeration de toutes les lettres presentes dans le 
 * ...............: jeu de scrabble, avec leur valeur et leur nombre 
 * ...............: respectifs. Cette classe facilite l'ajout de tuiles
 * ...............: dans le sac.
 * Auteur(s) .....: SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: © 2017 SIMON BACQUET ALL RIGHTS RESERVED
 ************************************************************************/

public enum Lettre {
	
	A(1, 9, Paths.get("A.png")),
	B(3, 2, Paths.get("B.png")),
	C(3, 2, Paths.get("C.png")),
	D(2, 3, Paths.get("D.png")),
	E(1, 15, Paths.get("E.png")),
	F(4, 2, Paths.get("F.png")),
	G(2, 2, Paths.get("G.png")),
	H(4, 2, Paths.get("H.png")),
	I(1, 8, Paths.get("I.png")),
	J(8, 1, Paths.get("J.png")),
	K(10, 1, Paths.get("K.png")),
	L(1, 5, Paths.get("L.png")),
	M(2, 3, Paths.get("M.png")),
	N(1, 6, Paths.get("N.png")),
	O(1, 6, Paths.get("O.png")),
	P(3, 2, Paths.get("P.png")),
	Q(8, 1, Paths.get("Q.png")),
	R(1, 6, Paths.get("R.png")),
	S(1, 6, Paths.get("S.png")),
	T(1, 6, Paths.get("T.png")),
	U(1, 6, Paths.get("U.png")),
	V(4, 2, Paths.get("V.png")),
	W(10, 1, Paths.get("W.png")),
	X(10, 1, Paths.get("X.png")),
	Y(10, 1, Paths.get("Y.png")),
	Z(10, 1, Paths.get("Z.png")),
	JOCKER(0, 2, Paths.get("Jocker.png"));
	
	int valeur;
	int nombre;
	Path img;
	
	Lettre(int _valeur, int _nombre, Path _img){
		
		valeur = _valeur;
		nombre = _nombre;
		img = _img;	
	}
}
