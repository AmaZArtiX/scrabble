/*******************************************************************************
Nom ................. : Lettre.java

Description ......... : Classe présentant tout les éléments relatifs aux Lettres

Auteur(s) ........... : LAMPE Ronan

Dernière modification : 18/10/2017

*******************************************************************************/

// Package
package scrabble;

public class Lettre {

	private char alphabet[] = {
								'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
								'O','P','Q','R','S','T','U','V','W','X','Y','Z','*'
							  };
	
	private int valNbLet[][] = {
								{1,3,3,2,1,4,2,4,1,8,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10,0},
								{9,2,2,3,15,2,2,2,8,1,1,5,3,6,6,2,1,6,6,6,6,2,1,1,1,1,2}
							   };
	
	
}
