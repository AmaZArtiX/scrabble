/*******************************************************************************
Nom ................. : AccueilControlleur.java

Description ......... : Classe comportant les fonctions de contrôle utilisées
						par l'application lors d'un événement (clic sur un bout
						-on, etc...)

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccueilControlleur extends Jeu {

	// Lien entre le fichier FXML et le TextField nomTxtJoueur
	@FXML private TextField nomTxtJoueur;
	
	// Lien entre le fichier FXML et le Button lanJeu
	@FXML private Button lanJeu;
	
	// Fonction permettant d'accèder à l'écran de jeu
	public void gotoScenePrincipale(ActionEvent e) throws IOException {
		
		// On récupère le nom du joueur
		nomJoueur = nomTxtJoueur.getText();
		
		// Test root créé avec Scene Builder
		Parent rootPrincipal = FXMLLoader.load(getClass().getResource("/scrabble/Jeu.fxml"));
		
		// Déclaration de la scène et insertion dans le primaryStage
		Scene scenePrincipale = new Scene(rootPrincipal, 1000, 600);
		
		// Changement de la scène d'acceuil vers la scène principale
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(scenePrincipale);
		primaryStage.show();
	}
	
	// Fonction de vérification de la longueur du nom du joueur
	public void lettreEntree(ActionEvent e) {
		
		if (nomTxtJoueur.getLength() > 1) lanJeu.setDisable(false);
	}
}
