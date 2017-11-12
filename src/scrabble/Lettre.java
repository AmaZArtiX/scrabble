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
	
	A('A', 1, 9, new Image("A.png")),
	B('B', 3, 2, new Image("B.png")),
	C('C', 3, 2, new Image("C.png")),
	D('D', 2, 3, new Image("D.png")),
	E('E', 1, 15, new Image("E.png")),
	F('F', 4, 2, new Image("F.png")),
	G('G', 2, 2, new Image("G.png")),
	H('H', 4, 2, new Image("H.png")),
	I('I', 1, 8, new Image("I.png")),
	J('J', 8, 1, new Image("J.png")),
	K('K', 10, 1, new Image("K.png")),
	L('L', 1, 5, new Image("L.png")),
	M('M', 2, 3, new Image("M.png")),
	N('N', 1, 6, new Image("N.png")),
	O('O', 1, 6, new Image("O.png")),
	P('P', 3, 2, new Image("P.png")),
	Q('Q', 8, 1, new Image("Q.png")),
	R('R', 1, 6, new Image("R.png")),
	S('S', 1, 6, new Image("S.png")),
	T('T', 1, 6, new Image("T.png")),
	U('U', 1, 6, new Image("U.png")),
	V('V', 4, 2, new Image("V.png")),
	W('W', 10, 1, new Image("W.png")),
	X('X', 10, 1, new Image("X.png")),
	Y('Y', 10, 1, new Image("Y.png")),
	Z('Z', 10, 1, new Image("Z.png")),
	JOKER('*', 0, 2, new Image("Joker.png"));
	
	char lettre;
	int valeur;
	int nombre;
	Image img;
	
	Lettre(char _lettre, int _valeur, int _nombre, Image _img){
		
		lettre = _lettre;
		valeur = _valeur;
		nombre = _nombre;
		img = _img;	
	}
	
	// Fonction de recherche d'une lettre en fonction de l'image
	public static char ltr(Image img) {

		// On declare la lettre a retourner
		char lettre = ' ';

		// On cherche la lettre en fonction de l'image
		if (imgEgales(img, A.img)) {
			lettre = A.lettre;
		} else if (imgEgales(img, B.img)) {
			lettre = B.lettre;
		} else if (imgEgales(img, C.img)) {
			lettre = C.lettre;
		} else if (imgEgales(img, D.img)) {
			lettre = D.lettre;
		} else if (imgEgales(img, E.img)) {
			lettre = E.lettre;
		} else if (imgEgales(img, F.img)) {
			lettre = F.lettre;
		} else if (imgEgales(img, G.img)) {
			lettre = G.lettre;
		} else if (imgEgales(img, H.img)) {
			lettre = H.lettre;
		} else if (imgEgales(img, I.img)) {
			lettre = I.lettre;
		} else if (imgEgales(img, J.img)) {
			lettre = J.lettre;
		} else if (imgEgales(img, K.img)) {
			lettre = K.lettre;
		} else if (imgEgales(img, L.img)) {
			lettre = L.lettre;
		} else if (imgEgales(img, M.img)) {
			lettre = M.lettre;
		} else if (imgEgales(img, N.img)) {
			lettre = N.lettre;
		} else if (imgEgales(img, O.img)) {
			lettre = O.lettre;
		} else if (imgEgales(img, P.img)) {
			lettre = P.lettre;
		} else if (imgEgales(img, Q.img)) {
			lettre = Q.lettre;
		} else if (imgEgales(img, R.img)) {
			lettre = R.lettre;
		} else if (imgEgales(img, S.img)) {
			lettre = S.lettre;
		} else if (imgEgales(img, T.img)) {
			lettre = T.lettre;
		} else if (imgEgales(img, U.img)) {
			lettre = U.lettre;
		} else if (imgEgales(img, V.img)) {
			lettre = V.lettre;
		} else if (imgEgales(img, W.img)) {
			lettre = W.lettre;
		} else if (imgEgales(img, X.img)) {
			lettre = X.lettre;
		} else if (imgEgales(img, Y.img)) {
			lettre = Y.lettre;
		} else if (imgEgales(img, Z.img)) {
			lettre = Z.lettre;
		} else if (imgEgales(img, JOKER.img)) {
			lettre = JOKER.lettre;
		}

		// On retourne la lettre recherchee
		return lettre;
	}
	
	// Fonction de recherche d'une valeur en fonction de l'image
	public static int vlr(Image img) {
		
		// On declare la valeur a retourner
		int valeur = 0;
		
		// On cherche la valeur en fonction de l'image
		if (imgEgales(img, A.img)) {
			valeur = A.valeur;
		} else if (imgEgales(img, B.img)) {
			valeur = B.valeur;
		} else if (imgEgales(img, C.img)) {
			valeur = C.valeur;
		} else if (imgEgales(img, D.img)) {
			valeur = D.valeur;
		} else if (imgEgales(img, E.img)) {
			valeur = E.valeur;
		} else if (imgEgales(img, F.img)) {
			valeur = F.valeur;
		} else if (imgEgales(img, G.img)) {
			valeur = G.valeur;
		} else if (imgEgales(img, H.img)) {
			valeur = H.valeur;
		} else if (imgEgales(img, I.img)) {
			valeur = I.valeur;
		} else if (imgEgales(img, J.img)) {
			valeur = J.valeur;
		} else if (imgEgales(img, K.img)) {
			valeur = K.valeur;
		} else if (imgEgales(img, L.img)) {
			valeur = L.valeur;
		} else if (imgEgales(img, M.img)) {
			valeur = M.valeur;
		} else if (imgEgales(img, N.img)) {
			valeur = N.valeur;
		} else if (imgEgales(img, O.img)) {
			valeur = O.valeur;
		} else if (imgEgales(img, P.img)) {
			valeur = P.valeur;
		} else if (imgEgales(img, Q.img)) {
			valeur = Q.valeur;
		} else if (imgEgales(img, R.img)) {
			valeur = R.valeur;
		} else if (imgEgales(img, S.img)) {
			valeur = S.valeur;
		} else if (imgEgales(img, T.img)) {
			valeur = T.valeur;
		} else if (imgEgales(img, U.img)) {
			valeur = U.valeur;
		} else if (imgEgales(img, V.img)) {
			valeur = V.valeur;
		} else if (imgEgales(img, W.img)) {
			valeur = W.valeur;
		} else if (imgEgales(img, X.img)) {
			valeur = X.valeur;
		} else if (imgEgales(img, Y.img)) {
			valeur = Y.valeur;
		} else if (imgEgales(img, Z.img)) {
			valeur = Z.valeur;
		} else if (imgEgales(img, JOKER.img)) {
			valeur = JOKER.valeur;
		}
		
		// On retourne la valeur recherchee
		return valeur;
	}

	// Fonction de recherche d'une image en fonction de la lettre
	public static Image img(char lettre) {
		
		// On declare l'image a retourner
		Image image = null;
		
		// On cherche l'image en fonction de la lettre
		switch (lettre) {
		case 'A':
			image = A.img;
			break;
		case 'B':
			image = B.img;
			break;
		case 'C':
			image = C.img;
			break;
		case 'D':
			image = D.img;
			break;
		case 'E':
			image = E.img;
			break;
		case 'F':
			image = F.img;
			break;
		case 'G':
			image = G.img;
			break;
		case 'H':
			image = H.img;
			break;
		case 'I':
			image = I.img;
			break;
		case 'J':
			image = J.img;
			break;
		case 'K':
			image = K.img;
			break;
		case 'L':
			image = L.img;
			break;
		case 'M':
			image = M.img;
			break;
		case 'N':
			image = N.img;
			break;
		case 'O':
			image = O.img;
			break;
		case 'P':
			image = P.img;
			break;
		case 'Q':
			image = Q.img;
			break;
		case 'R':
			image = R.img;
			break;
		case 'S':
			image = S.img;
			break;
		case 'T':
			image = T.img;
			break;
		case 'U':
			image = U.img;
			break;
		case 'V':
			image = V.img;
			break;
		case 'W':
			image = W.img;
			break;
		case 'X':
			image = X.img;
			break;
		case 'Y':
			image = Y.img;
			break;
		case 'Z':
			image = Z.img;
			break;
		case '*':
			image = JOKER.img;
			break;
		default:
			break;
		}
		
		// On retourne l'image recherchee
		return image;
	}
	
	public static boolean imgEgales(Image img1, Image img2) {
		for (int i = 0; i < img1.getWidth(); i++)
		{
		  for (int j = 0; j < img1.getHeight(); j++)
		  {
			  if (img1.getPixelReader().getArgb(i, j) != img2.getPixelReader().getArgb(i, j)) return false;
		  }
		}
		return true;
	}
}
