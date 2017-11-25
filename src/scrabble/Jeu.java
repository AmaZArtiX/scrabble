// Package
package scrabble;

import java.util.ArrayList;

// Import(s)
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*************************************************************************
 * Nom ...........: Jeu.java
 * Description ...: Classe principale comportant une iteration de Diction
 * ...............: -naire, la fonction main ainsi que la scene javafx 
 * ...............: principale
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class Jeu extends Application {
	
	// Declaration du Dictionnaire
	static Dictionnaire dictionnaire = new Dictionnaire();
	
	// Declaration du Joueur
	static ArrayList<Joueur> Joueurs = new ArrayList<Joueur>();
	
	// 
	static int joueur = 0;
	
	// Declaration du Sac et remplissage avec les 102 Tuiles
	static Sac sac = new Sac();
	
	// Declaration du Plateau
	static Plateau plateau = new Plateau();
	
	// Declaration du booleen de Jeu
	static boolean jeuEnCours = false;
	
	// Declaration du nombre de tours
	static int nbTours = 0;
	
	// Declaration de la Tuile Joker
	static Tuile joker = new Tuile();
	
	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {	
	
		// Lancement de la fenetre javafx --> fonction "start"
		launch(args);
	}

	// Fonction permettant le lancement de l'application
	@Override
	public void start(Stage stageAccueil) throws Exception {
		
		// Interface d'Accueil cree avec Scene Builder (Accueil.fxml)
		Parent rootAccueil = FXMLLoader.load(getClass().getResource("/scrabble/Accueil.fxml"));
		
		// Declaration de la scene javafx
		Scene sceneAccueil = new Scene(rootAccueil, 1080, 720);
		
		// Visualisation de stageAccueil
		stageAccueil.setScene(sceneAccueil);
		stageAccueil.getIcons().add(new Image("S.png"));
		stageAccueil.setTitle("Accueil - Scrabble");
		stageAccueil.setResizable(false);
		stageAccueil.show();
	}
}
