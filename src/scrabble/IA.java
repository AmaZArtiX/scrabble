// Package
package scrabble;

import java.util.ArrayList;

/**
  * ***********************************************************************
  * Nom ...........: IA.java
  * Description ...: Intelligence artificielle poss�dant 1 niveau de difficult�
  * ...............: interm�diaire et contre laquelle l'utilisateur pourra jouer
  * ...............:
  * Auteur(s) .....: YACINE CHTAIRI
  * Version .......: 1.0
  * Copyright .....: � 2017 YACINE CHTAIRI ALL RIGHTS RESERVED
  ***********************************************************************
  */

public class IA extends Joueur{ 

	public IA() { //Constructeur sans param�tres
		super();
		setNom("IA");
	}
	
	//Recuperer indice jusqu'a case vide
	//Stocker indice dans Tableau1
	//Recuperer contenu de l'indice
	//Stocker contenu dans Tableau2
	//Lire tableau2
	//Verifier dans dico mots commen�ant par contenu
	//Choisir aleatoirement un mot
	//Verifier si lettre dispo dans chevalet
	//Placer lettre a lettre ou echanger lettre
	
	
	// 
	public void jouerMotIntermediaire(Plateau plateau, Chevalet chevalet) {
		// On recupere la liste des tuiles dispo. sur le plateau
		ArrayList<Tuile> tuilesPlateau = new ArrayList<Tuile>(plateau.getTuilesDisponibles());
	
		
	}
}
