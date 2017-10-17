/*******************************************************************************
Nom ................. : JeuControlleur.java

Description ......... : Classe comportant les fonctions de contrôle utilisées
						par l'application lors d'un événement (clic sur un bout
						-on, etc...) de la scène Jeu

Auteur(s) ........... : LAMPE Ronan

Dernière modification : 13/10/2017

*******************************************************************************/

// Package
package scrabble;

// Imports
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class JeuControlleur extends Jeu {

	// Lien entre le fichier FXML et le Label lbl
	@FXML private Label lblScoreJ;
	
	// Fonction permettant d'accèder au dictionnaire
	public void gotoDictionnaire(ActionEvent e) throws IOException {
		
		// Test root créé avec Scene Builder
		Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Dictionnaire.fxml"));
		
		// Déclaration de la scène et insertion dans le primaryStage
		Scene scene = new Scene(root, 300, 300);
		
		// Changement de la scène d'acceuil vers la scène principale
		Stage stageDictionnaire = new Stage();
		stageDictionnaire.setScene(scene);
		stageDictionnaire.setTitle("Scrabble - Dictionnaire");
		stageDictionnaire.show();
	}
	
	// Fonction permettant de quitter l'application
	public void quitter(ActionEvent e) {
		
		System.exit(0);
	}
	
	public void initialize() {
		
		lblScoreJ.setText("Score du Joueur \"" + nomJoueur + "\" : " + Integer.toString(scoreJoueur));
	}
}
