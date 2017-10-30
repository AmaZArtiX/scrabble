/*******************************************************************************
Nom ................. : Jeu.java

Description ......... : Classe principale comportant une itération de Dictionnai
						-re, la fonction main ainsi que la scène javafx principa
						-le

Auteur(s) ........... : LAMPE Ronan

Dernière modification : 17/10/2017

*******************************************************************************/

// Package
package scrabble;

// Import(s)
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Jeu extends Application {
	
	// Déclaration du dictionnaire
	static Dictionnaire Dictionnaire = new Dictionnaire();
	
	// Déclaration du nom du Joueur
	static String nomJoueur = "";
	
	// Déclaration du score du Joueur
	static int scoreJoueur = 0;
	
	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {		
		
		// Initialisation du dictionnaire
		Dictionnaire.initDico();
		
		// Lancement de la fenêtre javafx --> fonction "start"
		launch(args);
	}

	// Fonction permettant le lancement de l'application
	@Override
	public void start(Stage stageAccueil) throws Exception {
		
		// root de l'Accueil créé avec Scene Builder
		Parent rootAccueil = FXMLLoader.load(getClass().getResource("/scrabble/Accueil.fxml"));
		
		// Déclaration de la scène et insertion dans le primaryStage
		Scene sceneAccueil = new Scene(rootAccueil, 400, 200);
		
		// Visualisation de stageAccueil
		stageAccueil.setScene(sceneAccueil);
		stageAccueil.getIcons().add(new Image("S.png"));
		stageAccueil.setTitle("Accueil - Scrabble");
		stageAccueil.show();
	}
}
