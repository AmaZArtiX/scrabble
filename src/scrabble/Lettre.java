package scrabble;

import java.nio.file.Path;
import java.nio.file.Paths;

/*************************************************************************
 * Nom ...........: Lettre.java
 * Description ...: Enumeration de toutes les lettres présentes dans le 
 * ...............: jeu de scrabble, avec leur valeur et leur nombre 
 * ...............: respectifs. Cette classe facilite l'ajout de tuiles
 * ...............: dans le sac.
 * Auteur(s) .....: SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: © 2017 SIMON BACQUET ALL RIGHTS RESERVED
 ************************************************************************/

public enum Lettre {
	
	A(1, 9, Paths.get("files/tuiles/A.png")),
	B(3, 2, Paths.get("files/tuiles/B.png")),
	C(3, 2, Paths.get("files/tuiles/C.png")),
	D(2, 3, Paths.get("files/tuiles/D.png")),
	E(1, 15, Paths.get("files/tuiles/E.png")),
	F(4, 2, Paths.get("files/tuiles/F.png")),
	G(2, 2, Paths.get("files/tuiles/G.png")),
	H(4, 2, Paths.get("files/tuiles/H.png")),
	I(1, 8, Paths.get("files/tuiles/I.png")),
	J(8, 1, Paths.get("files/tuiles/J.png")),
	K(10, 1, Paths.get("files/tuiles/K.png")),
	L(1, 5, Paths.get("files/tuiles/L.png")),
	M(2, 3, Paths.get("files/tuiles/M.png")),
	N(1, 6, Paths.get("files/tuiles/N.png")),
	O(1, 6, Paths.get("files/tuiles/O.png")),
	P(3, 2, Paths.get("files/tuiles/P.png")),
	Q(8, 1, Paths.get("files/tuiles/Q.png")),
	R(1, 6, Paths.get("files/tuiles/R.png")),
	S(1, 6, Paths.get("files/tuiles/S.png")),
	T(1, 6, Paths.get("files/tuiles/T.png")),
	U(1, 6, Paths.get("files/tuiles/U.png")),
	V(4, 2, Paths.get("files/tuiles/V.png")),
	W(10, 1, Paths.get("files/tuiles/W.png")),
	X(10, 1, Paths.get("files/tuiles/X.png")),
	Y(10, 1, Paths.get("files/tuiles/Y.png")),
	Z(10, 1, Paths.get("files/tuiles/Z.png")),
	JOCKER(0, 2, Paths.get("files/tuiles/Jocker.png"));
	
	int valeur;
	int nombre;
	Path img;
	
	
	Lettre(int _valeur, int _nombre, Path _img){
		
		valeur = _valeur;
		nombre = _nombre;
		img = _img;	
	}
}
