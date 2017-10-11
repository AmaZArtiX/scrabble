// Classe principale du Scrabble : Jeu
//
// Auteur(s): LAMPE Ronan
//
//
//
//

// Package
package scrabble;

// Import(s)
import java.util.Scanner;

public class Jeu {

	//Fonction comprenant l'entr�e d'un mot et la v�rif. de son existence dans le dictionnaire
	public static void recherche() {
		//D�claration du dictionnaire
		Dictionnaire Dictionnaire = new Dictionnaire();
		
		//Fonction d'initialisation du dictionnaire
		Dictionnaire.initDico();
		
		//Affichage de la proposition d'entr�e
		System.out.println("Veuillez entrer le mot recherch�");
		
		//Lecture du mot entr� (non s�curis�)
		Scanner sc = new Scanner(System.in);
		String mot = sc.nextLine().toUpperCase();
		
		//Affichage du r�sultat de la recherche
		System.out.print("R�sultat: ");
		if(Dictionnaire.existe(mot)) System.out.println("Existe.\n");
		else System.out.print("N'existe pas.");
	}
	
	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Affichage du jeu
		System.out.println("\nBienvenue dans le jeu Scrabble\n");
		
		//Test de la fonction "recherche"
		recherche();
	}

}
