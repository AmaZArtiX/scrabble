// Package
package scrabble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

 /**
  * ***************************************************************************
  * Nom ...........: IA.java
  * Description ...: Intelligence artificielle possèdant 1 niveau de difficulté
  * ...............: intermédiaire et contre laquelle l'utilisateur pourra jouer
  * ...............:
  * Auteur(s) .....: YACINE CHTAIRI, MAMADOU BAH
  * Version .......: 1.0
  * Copyright .....: © 2017 YACINE CHTAIRI, MAMADOU BAH ALL RIGHTS RESERVED
  *****************************************************************************
  */

public class IA extends Joueur{ 

	
	
	//Recuperer indice jusqu'a case vide
	//Stocker indice dans Tableau1
	
	//Recuperer contenu de l'indice
	//Stocker contenu dans Tableau2
	//Lire tableau2
	//Verifier dans dico mots commençant par contenu
	//Choisir aleatoirement un mot
	//Verifier si lettre dispo dans chevalet
	//Placer lettre a lettre ou echanger lettre
		
		Hashtable<Character, Integer> letterValues;


		public IA(Hashtable<Character, Integer> letterValues) {
			super();
			setNom("IA");
		}

		@Override
		public void addScore(int newScore) {
			setScore(getScore() + newScore);
		}
	
		public int getNumberOfLetters() {
			return getChevalet().getTaille();
		}

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
		public ArrayList<String> findAnagrams(char lettre) {

			char[] characters;
			if (lettre != ' ') {
				characters = new char[getChevalet().getTaille() + 1];
			} else {
				characters = new char[getChevalet().getTaille()];
			}

			ArrayList<String> anagrams = new ArrayList<String>();

			/* Remplit les caractères avec les lettres du Chevalet */
			for (int i = 0; i < getChevalet().getTaille(); i++) {
				characters[i] = getChevalet().getTuile(i).getLettre();
			}
			if (lettre != ' ') {
				characters[getChevalet().getTaille()] = lettre;
			}
			/* Pour chaque mot du dictionnaire */
			for (int i = 0; i < Jeu.dictionnaire.getTaille(); i++) {

				char[] temp = characters.clone();
				char[] word = Jeu.dictionnaire.getMot(i).toCharArray();

				/* Pour chaque caractère en mot */
				for (int j = 0; j < word.length; j++) {
					int index = contains(temp, word[j]);
					if (index != -1) {
						temp[index] = '.';
					} else {
						break;
					}
					if (j == word.length - 1) {
						String newWord = new String(word);
						if (lettre != ' ' && newWord.contains("" + lettre)){
							anagrams.add(newWord);						
						} else if (lettre == ' '){
							anagrams.add(newWord);						
						}
					}
				}
			}
			Collections.sort(anagrams);
			return anagrams;
		}

		/** Trouve le score du mot
		 * @author Mamadou BAH
		 **/
		public int findScore(String word) {
			int score = 0;
			for (Character c : word.toCharArray()) {
				score += letterValues.get(c);
			}
			return score;
		}

		/** Trouve l'anagramme qui a le plus de score 
		 * @author Mamadou BAH
		 **/
		public String findBestWord(ArrayList<String> anagrams) {
			String word = "";
			int score = 0;
			for (String s : anagrams) {
				int temp = findScore(s);
				if (temp > score) {
					score = temp;
					word = s;
				}
			}
			return word;
		}
	}
