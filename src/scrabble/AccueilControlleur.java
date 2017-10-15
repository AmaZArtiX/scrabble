/*******************************************************************************
Nom ................. : AccueilControlleur.java

Description ......... : Classe comportant les fonctions de contr�le utilis�es
						par l'application lors d'un �v�nement (clic sur un bout
						-on, etc...)

Auteur(s) ........... : LAMPE Ronan

Derni�re modification : 13/10/2017

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
	
	// Fonction permettant d'acc�der � l'�cran de jeu
	public void gotoScenePrincipale(ActionEvent e) throws IOException {
		
		// On r�cup�re le nom du joueur
		nomJoueur = nomTxtJoueur.getText();
		
		// Test root cr�� avec Scene Builder
		Parent rootPrincipal = FXMLLoader.load(getClass().getResource("/scrabble/Jeu.fxml"));
		
		// D�claration de la sc�ne et insertion dans le primaryStage
		Scene scenePrincipale = new Scene(rootPrincipal, 1000, 600);
		
		// Changement de la sc�ne d'acceuil vers la sc�ne principale
		Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(scenePrincipale);
		primaryStage.show();
	}
	
	// Fonction de v�rification de la longueur du nom du joueur
	public void lettreEntree(ActionEvent e) {
		
		if (nomTxtJoueur.getLength() > 1) lanJeu.setDisable(false);
	}
}
