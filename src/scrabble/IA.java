// Package
package scrabble;

// Imports
import java.util.ArrayList;

/**
  * ***********************************************************************
  * Nom ...........: IA.java
  * Description ...: Intelligence artificielle possï¿½dant 1 niveau de difficultï¿½
  * ...............: intermï¿½diaire et contre laquelle l'utilisateur pourra jouer
  * ...............:
  * Auteur(s) .....: YACINE CHTAIRI
  * Version .......: 1.0
  * Copyright .....: ï¿½ 2017 YACINE CHTAIRI ALL RIGHTS RESERVED
  ***********************************************************************
  */

public class IA extends Joueur{ 

	public IA() { //Constructeur sans paramï¿½tres
		super();
		setNom("IA");
	}
	
	//Recuperer indice jusqu'a case vide
	//Stocker indice dans Tableau1
	//Recuperer contenu de l'indice
	//Stocker contenu dans Tableau2
	//Lire tableau2
	//Verifier dans dico mots commenï¿½ant par contenu
	//Choisir aleatoirement un mot
	//Verifier si lettre dispo dans chevalet
	//Placer lettre a lettre ou echanger lettre
	
	
	/**
	 * Vérifie si un mot contient un caractère spécifique et renvoie l'index de
	 *  la première instance. Sinon renvoie -1.
	 *  @author Mamadou BAH
	 **/
	private int contains(char[] word, char lettre) {
		for (int i = 0; i < word.length; i++) {
			if (lettre == word[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Trouve tous les anagrammes des lettres du chevalet et un sur le plateau dans le
	 * Dico
	 * @author Mamadou BAH
	 **/
	public ArrayList<String> findAnagrams(char[] lettres) {

		char[] characters = new char[getChevalet().getTaille() + lettres.length]; 

		ArrayList<String> anagrams = new ArrayList<String>();

		/* Remplit les caractères avec les lettres du Chevalet */
		for (int i = 0; i < getChevalet().getTaille(); i++) {
			characters[i] = getChevalet().getTuile(i).getLettre();
		}
		
		if (lettres.length > 0) {
			for(int i = 0; i < lettres.length; i++) {
				characters[getChevalet().getTaille() + i] = lettres[i];
			}
		}
		
		System.out.println("Liste des caracteres :");
		System.out.println(characters);
		System.out.println("");
		
		/* Pour chaque mot du dictionnaire */
		for (int i = 0; i < Jeu.dictionnaire.getTaille(); i++) {

			char[] word = Jeu.dictionnaire.getMot(i).toCharArray();
			char[] temp = word.clone();
			
			// 
			if(characters.length >= word.length) {

				/* Pour chaque caractère en mot */
				for (int j = 0; j < characters.length; j++) {
					if (contains(temp, characters[j]) != -1) {
						temp[contains(temp, characters[j])] = '.';
					}
				}
				
				// 
				if (motFaisable(temp)) {
					String newWord = new String(word);
					anagrams.add(newWord);
				}
			}
		}
		
		System.out.println("Nombre de mots jouables :");
		System.out.println(anagrams.size());
		System.out.println("");
		
		return anagrams;
	}
	
	// 
	private boolean motFaisable(char[] mot) {
		for(int i=0; i < mot.length; i++) {
			if(mot[i] != '.') {
				return false;
			}
		}
		return true;
	}
	
	
	
	// 
	public void jouerMotIntermediaire(Plateau plateau, Chevalet chevalet) {
		
		// 
		
	}
}
