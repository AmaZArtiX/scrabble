/*******************************************************************************
Nom ................. : Jeu.java

Description ......... : Classe principale comportant une it�ration de Dictionnai
						-re, la fonction main ainsi que la sc�ne javafx principa
						-le

Auteur(s) ........... : LAMPE Ronan

Derni�re modification : 17/10/2017

*******************************************************************************/

// Package
package scrabble;

// Import(s)
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Jeu extends Application {

	// D�claration du dictionnaire
	static Dictionnaire Dictionnaire = new Dictionnaire();
	
	// D�claration du nom du joueur
	static String nomJoueur = "";
	
	// D�claration du score du Joueur
	static int scoreJoueur = 0;
	
	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {		
		
		// Initialisation du dictionnaire
		Dictionnaire.initDico();
		
		// Lancement de la fen�tre javafx --> fonction "start"
		launch(args);
	}

	// Fonction permettant le lancement de l'application
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// root de l'Accueil cr�� avec Scene Builder
		Parent rootAccueil = FXMLLoader.load(getClass().getResource("/scrabble/Accueil.fxml"));
		
		// D�claration de la sc�ne et insertion dans le primaryStage
		Scene sceneAccueil = new Scene(rootAccueil, 400, 200);
		
		// Visualisation du primaryStage
		primaryStage.setScene(sceneAccueil);
		primaryStage.setTitle("Scrabble");
		primaryStage.show();
	}
}
