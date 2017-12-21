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

	/* Global variables used for the AI findBestMove methods. */
	/* Global variables used for the AI findBestMove methods. */
	private String bestString = "";
	private int bestScore = 0;
	private char direction;
    private Square start = null;
    private Plateau plateau;
	/* Pass booleans. The game ends if both are true. */
	private boolean humanPass;
	private boolean aiPass;

	
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
	private boolean HORIZONTAL;


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
		 * Trouve tous les anagrammes des lettres du chevalet et sur le plateau dans le
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
	


///////////////////////////////////////////////////////////////////////////////
		
		
		/**
		 * Returns true if any of the squares in a word are neighbored by another
		 * filled square not also in the word.
		 **/
		/*private boolean hasUniqueNeighbors(ArrayList<Square> word) {
			for (Square s : word) {
				if (s.hasUniqueNeighbor(word)) {
					return true;
				}
			}
			return false;
		}
*/
		/*
		* Retourne la somme des scores de chaque mot dans allWords et supprime le
		* marquer les modificateurs de leurs carrés si permanent est vrai.
		* @author Mamadou BAH
		*/
		
		private int findScore(ArrayList<ArrayList<Square>> allWords, boolean permanent) {
			int score = 0;
			int wordMult = 1;
			for (ArrayList<Square> word : allWords) {
				int tempScore = 0;
				for (Square s : word) {
					if (s.getLetterMult() > 1) {
						tempScore += s.getLetter().valeur * s.getLetterMult();
					} else {
						tempScore += s.getLetter().valeur;
					}
					wordMult *= s.getWordMult();
				}
				score += tempScore * wordMult;
				wordMult = 1;
			}

			if (permanent) {
				for (ArrayList<Square> word : allWords) {
					for (Square s : word) {
						s.setLetterMult(1);
						s.setWordMult(1);
					}
				}
			}

			return score;
		}

		/**
		 * Place les lettres du plateau d'AI sur le tableau en fonction des champs
		 * bestString, direction et début.
		 * @author Mamadou BAH
		 **/
		private void placeBestMove() {

			String prefix = "";
			String suffix = "";
			Square tempSquare = start;
			ArrayList<Square> word = new ArrayList<Square>();
			ArrayList<Square> tempCrossword = new ArrayList<Square>();
			ArrayList<ArrayList<Square>> allWords = new ArrayList<ArrayList<Square>>();
			Joueur ia;
			if (bestString.equals("")) {
				aiPass = true;
				return;
			}

			word.add(start);

			int location = bestString.indexOf(start.getLetter().lettre);
			if (location > 0) {
				prefix = bestString.substring(0, location);
			}
			if (location < bestString.length()) {
				suffix = bestString.substring(location + 1, bestString.length());
			}
			if (direction == 'h') {
				//plateau.HORIZONTAL = true;
				/* Places each char in prefix to the left of start, if able */
				while (tempSquare.getLeft() != null && tempSquare.getLeft().getLetter() == null && !prefix.equals("")) {
					char c = prefix.charAt(prefix.length() - 1);
					prefix = prefix.substring(0, prefix.length() - 1);
					tempSquare = tempSquare.getLeft();
					tempSquare.setLetter(findLetterInTray(c, ia));
					word.add(tempSquare);
					ia.getChevalet().supprimerTuiles();
					/* If the newly placed tile forms a crossword */
					tempCrossword = plateau.buildWord(tempSquare, 'h');
					if (!tempCrossword.isEmpty()) {
						allWords.add(tempCrossword);
					}
				}
				tempSquare = start;
				/* Places each char in suffix to the right of start, if able */
				while (tempSquare.getRight() != null && tempSquare.getRight().getLetter() == null && !suffix.equals("")) {
					char c = suffix.charAt(0);
					suffix = suffix.substring(1);
					tempSquare = tempSquare.getRight();
					tempSquare.setLetter(findLetterInTray(c, ia));
					word.add(tempSquare);
					
					ia.getChevalet().supprimerTuile(tempSquare.);
					/* If the newly placed tile forms a crossword */
					tempCrossword = plateau.buildWord(tempSquare, 'h');
					if (!tempCrossword.isEmpty()) {
						allWords.add(tempCrossword);
					}
				}
			} else if (direction == 'v') {
				/* Places each char in prefix to the top of start, if able */
				while (tempSquare.getTop() != null && tempSquare.getTop().getLetter() == null && !prefix.equals("")) {
					char c = prefix.charAt(prefix.length() - 1);
					prefix = prefix.substring(0, prefix.length() - 1);
					tempSquare = tempSquare.getTop();
					tempSquare.setLetter(findLetterInTray(c, ia));
					word.add(tempSquare);
					ia.getChevalet().supprimerTuile(tempSquare.getLetter().).remove(tempSquare.getLetter());
					/* If the newly placed tile forms a crossword */
					tempCrossword = plateau.buildWord(tempSquare, 'v');
					if (!tempCrossword.isEmpty()) {
						allWords.add(tempCrossword);
					}
				}
				tempSquare = start;
				/* Places each char in suffix to the bottom of start, if able */
				while (tempSquare.getBottom() != null && tempSquare.getBottom().getLetter() == null && !suffix.equals("")) {
					char c = suffix.charAt(0);
					suffix = suffix.substring(1);
					tempSquare = tempSquare.getBottom();
					tempSquare.setLetter(findLetterInTray(c, ia));
					word.add(tempSquare);
					ia.getChevalet().supprimerTuile(tempSquare.getLetter());
					/* If the newly placed tile forms a crossword */
					tempCrossword = plateau.buildWord(tempSquare, 'v');
					if (!tempCrossword.isEmpty()) {
						allWords.add(tempCrossword);
					}
				}
			}

			allWords.add(word);
			ia.addScore(findScore(allWords, true));
			start = null;
			direction = ' ';
			bestScore = 0;
			bestString = "";

			blink(allWords);
		}

		private void blink(ArrayList<ArrayList<Square>> allWords) {
			// TODO Auto-generated method stub
			
		}

		/**
		* Trouve tous les anagrammes de la lettre en premier et les lettres dans le bac d'AI
		* et trouve le score de chacun lorsqu'il est placé sur le plateau, si possible. le
		* Le mot de notation le plus élevé est ensuite sauvegardé dans les champs bestString, bestScore,
		* direction, et commencez.
		* @author Mamadou BAH
		**/
		private void findBestMove(Square first, ArrayList<String> anagrams,Joueur ia) {
			/* If both horizontal neighbors exist and do not have a letter */
			if (first.getRight() != null && first.getRight().getLetter() == null && first.getLeft() != null && first.getLeft().getLetter() == null) {
				/* Check each anagram to see if it fits horizontally */
				for (String s : anagrams) {
					String prefix = "";
					String suffix = "";
					Square tempSquare = first;
					ArrayList<Square> word = new ArrayList<Square>();
					ArrayList<Square> tempCrossword = new ArrayList<Square>();
					ArrayList<ArrayList<Square>> allWords = new ArrayList<ArrayList<Square>>();

					int location = s.indexOf(first.getLetter().lettre);
					if (location > 0) {
						prefix = s.substring(0, location);
					}
					if (location < s.length()) {
						suffix = s.substring(location + 1, s.length());
					}
					/* Places each char in prefix to the left of first, if able */
					while (tempSquare.getLeft() != null && tempSquare.getLeft().getLetter() == null && !prefix.equals("")) {
						char c = prefix.charAt(prefix.length() - 1);
						tempSquare = tempSquare.getLeft();
						tempSquare.setLetter(findLetterInTray(c, ia));
						word.add(tempSquare);
						/* If the newly placed tile forms a crossword */
						tempCrossword = plateau.buildWord(tempSquare, 'h');
						if (tempCrossword == null) {
							for (Square square : word) {
								square.setLetter(null);
							}
							break;
							/* Else add it to the list of words spelled */
						} else if (!tempCrossword.isEmpty()) {
							allWords.add(tempCrossword);
						}
						prefix = prefix.substring(0, prefix.length() - 1);
					}
					/* If not all the letters in prefix were placed, continue */
					if (!prefix.equals("")) {
						for (Square square : word) {
							square.setLetter(null);
						}
						continue;
					}

					/* Resets tempSquare to first */
					tempSquare = first;

					/* Places each char in suffix to the right of first, if able */
					while (tempSquare.getRight() != null && tempSquare.getRight().getLetter() == null && !suffix.equals("")) {
						char c = suffix.charAt(0);
						tempSquare = tempSquare.getRight();
						tempSquare.setLetter(findLetterInTray(c, ia));
						word.add(tempSquare);
						/* If the newly placed tile forms an illegal crossword */
						tempCrossword = plateau.buildWord(tempSquare, 'h');
						if (tempCrossword == null) {
							for (Square square : word) {
								square.setLetter(null);
							}
							break;
							/* Else add it to the list of words spelled */
						} else if (!tempCrossword.isEmpty()) {
							allWords.add(tempCrossword);
						}
						suffix = suffix.substring(1);
					}
					/* If not all the letters in prefix were placed, continue */
					if (!suffix.equals("")) {
						for (Square square : word) {
							square.setLetter(null);
						}
						continue;
					}

					/* If the word is spelled, save the score */
					ArrayList<Square> finalWord = plateau.buildWord(first, 'v');
					if (finalWord != null) {
						allWords.add(finalWord);
						int score = findScore(allWords, false);
						if (score > bestScore) {
							bestScore = score;
							bestString = s;
							direction = 'h';
							start = first;
						}
					}
					for (Square square : word) {
						square.setLetter(null);
					}
				}
			}

			/* If both vertical neighbors exist and do not have a letter */
			if (first.getTop() != null && first.getTop().getLetter() == null && first.getBottom() != null && first.getBottom().getLetter() == null) {
				/* Check each anagram to see if it fits vertically */
				for (String s : anagrams) {
					String prefix = "";
					String suffix = "";
					Square tempSquare = first;
					ArrayList<Square> tempCrossword = new ArrayList<Square>();
					ArrayList<ArrayList<Square>> allWords = new ArrayList<ArrayList<Square>>();
					ArrayList<Square> word = new ArrayList<Square>();

					int location = s.indexOf(first.getLetter().lettre);
					if (location > 0) {
						prefix = s.substring(0, location);
					}
					if (location < s.length()) {
						suffix = s.substring(location + 1, s.length());
					}
					/* Places each char in prefix to the top of first, if able */
					while (tempSquare.getTop() != null && tempSquare.getTop().getLetter() == null && !prefix.equals("")) {
						char c = prefix.charAt(prefix.length() - 1);
						tempSquare = tempSquare.getTop();
						tempSquare.setLetter(findLetterInTray(c, ia));
						word.add(tempSquare);
						/* If the newly placed tile forms an illegal crossword */
						tempCrossword = plateau.buildWord(tempSquare, 'v');
						if (tempCrossword == null) {
							for (Square square : word) {
								square.setLetter(null);
							}
							break;
							/* Else add it to the list of words spelled */
						} else if (!tempCrossword.isEmpty()) {
							allWords.add(tempCrossword);
						}
						prefix = prefix.substring(0, prefix.length() - 1);
					}
					/* If not all the letters in prefix were placed, continue */
					if (!prefix.equals("")) {
						for (Square square : word) {
							square.setLetter(null);
						}
						continue;
					}

					/* Resets tempSquare to first */
					tempSquare = first;

					/* Places each char in suffix to the bottom of first, if able */
					while (tempSquare.getBottom() != null && tempSquare.getBottom().getLetter() == null && !suffix.equals("")) {
						char c = suffix.charAt(0);
						tempSquare = tempSquare.getBottom();
						tempSquare.setLetter(findLetterInTray(c, ia));
						word.add(tempSquare);
						/* If the newly placed tile forms an illegal crossword */
						tempCrossword = plateau.buildWord(tempSquare, 'v');
						if (tempCrossword == null) {
							for (Square square : word) {
								square.setLetter(null);
							}
							break;
							/* Else add it to the list of words spelled */
						} else if (!tempCrossword.isEmpty()) {
							allWords.add(tempCrossword);
						}
						suffix = suffix.substring(1);
					}
					/* If not all the letters in prefix were placed, continue */
					if (!suffix.equals("")) {
						for (Square square : word) {
							square.setLetter(null);
						}
						continue;
					}

					/* If the word is spelled, save the score */
					ArrayList<Square> finalWord = plateau.buildWord(first, 'h');
					if (finalWord != null) {
						allWords.add(finalWord);
						int score = 0;
						score = findScore(allWords, false);
						if (score > bestScore) {
							bestScore = score;
							bestString = s;
							direction = 'v';
							start = first;
						}
					}
					
	for (Square square : word) {
		square.setLetter(null);
					}
				}
			}
		}

		private Lettre findLetterInTray(char c, Joueur ia) {
			// TODO Auto-generated method stub
			return null;
		}

		
		private void makeMove(Joueur ai) {

			aiPass = false;

			/* If it is the first play of the game */
			if (plateau.estVide()) {
				char[] characters = ((IA) ai).findBestWord(((IA) ai).findAnagrams(' ')).toCharArray();
				ArrayList<Square> word = new ArrayList<Square>();
				/* For each character in word */
				for (int i = 0; i < characters.length; i++) {
					/* For each letter in tray */
					for (Lettre letter : ai.getChevalet().ge) {
						if (letter.lettre == characters[i]) {
							plateau.putLetter(ai.getChevalet().remove(ai.getChevalet().getIndex(letter)), 7, 7 + i);
							word.add(plateau.getSquare(7, 7 + i));
							break;
						}
					}
				}
				ArrayList<ArrayList<Square>> allWords = new ArrayList<ArrayList<Square>>();
				allWords.add(word);
				ai.addScore(findScore(allWords, true));
			} else {
				/* For each square in board, find the best play */
				for (int r = 0; r < 15; r++) {
					for (int c = 0; c < 15; c++) {
						Square tempSquare = plateau.getSquare(r, c);
						if (tempSquare.getLetter() != null) {
							findBestMove(tempSquare, ((IA) ai).findAnagrams(tempSquare.getLetter().lettre), ai);
						}
					}
				}
				placeBestMove();
			}
		}



}


