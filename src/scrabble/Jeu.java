// Package
package scrabble;

import java.nio.file.Paths;
import java.util.Random;

// Import(s)
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*************************************************************************
 * Nom ...........: Jeu.java
 * Description ...: Classe principale comportant une itération de Dictionnai
 * ...............: Dictionnaire, la fonction main ainsi que la scène javafx 
 * ...............: principale
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class Jeu extends Application {
	
	// Declaration du dictionnaire
	static Dictionnaire Dictionnaire = new Dictionnaire();
	
	// Declaration du nom du Joueur
	static String nomJoueur = "";
	
	// Declaration du score du Joueur
	static int scoreJoueur = 0;
		
	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {	
		

		// Initialisation du dictionnaire
		Dictionnaire.initDico();
		
		/*******************************************************
		 * 
		// Lancement de la fenetre javafx --> fonction "start"
		launch(args);
		*
		*******************************************************/
		
		// Sac commun à tous les joueurs 
		Sac s = new Sac();
		// Plateau commun à tous les joueurs
		Plateau p = new Plateau();
		// Chavelet du joueur 1
		Chevalet c1 = new Chevalet();
		// Remplissage du chevalet du joueur 1 avec 7 tuiles
		c1.remplir(s);
		// Joueur 1
		Joueur j = new Joueur(nomJoueur, scoreJoueur, c1);
		// Affichage du plateau 
		p.afficher();
		// Affichage du chevalet du joueur 1
		System.out.println(j.getChevalet());
		// Affichage du sac après après supprimé des tuiles
		s.afficherSac();
	}

	// Fonction permettant le lancement de l'application
	@Override
	public void start(Stage stageAccueil) throws Exception {
		
		// root de l'Accueil cree avec Scene Builder (Accueil.fxml)
		Parent rootAccueil = FXMLLoader.load(getClass().getResource("/scrabble/Accueil.fxml"));
		
		// Declaration de la scene
		Scene sceneAccueil = new Scene(rootAccueil, 400, 200);
		
		// Visualisation de stageAccueil
		stageAccueil.setScene(sceneAccueil);
		stageAccueil.getIcons().add(new Image("S.png"));
		stageAccueil.setTitle("Accueil - Scrabble");
		stageAccueil.setResizable(false);
		stageAccueil.show();
	}
}
