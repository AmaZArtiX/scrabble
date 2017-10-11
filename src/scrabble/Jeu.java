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

	//Fonction comprenant l'entrée d'un mot et la vérif. de son existence dans le dictionnaire
	public static void recherche() {
		//Déclaration du dictionnaire
		Dictionnaire Dictionnaire = new Dictionnaire();
		
		//Fonction d'initialisation du dictionnaire
		Dictionnaire.initDico();
		
		//Affichage de la proposition d'entrée
		System.out.println("Veuillez entrer le mot recherché");
		
		//Lecture du mot entré (non sécurisé)
		Scanner sc = new Scanner(System.in);
		String mot = sc.nextLine().toUpperCase();
		
		//Affichage du résultat de la recherche
		System.out.print("Résultat: ");
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
