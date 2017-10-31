/*******************************************************************************
Nom ................. : AccueilControlleur.java

Description ......... : Classe comportant les fonctions de contrôle utilisées
						par l'application lors d'un événement (clic sur un bout
						-on, etc...) de la scène Accueil

Auteur(s) ........... : LAMPE Ronan

Dernière modification : 17/10/2017

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

public class AccueilControleur extends Jeu {

	// Lien entre le fichier FXML et le TextField nomTxtJoueur
	@FXML private TextField nomTxtJoueur;
	
	// Lien entre le fichier FXML et le Button lanJeu
	@FXML private Button lanJeu;
	
	// Fonction permettant d'accèder à l'écran de jeu
	public void gotoJeu(ActionEvent e) throws IOException {
		
		// On récupère le nom du joueur
		nomJoueur = nomTxtJoueur.getText();
		
		// Test root créé avec Scene Builder
		Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Jeu.fxml"));
		
		// Déclaration de la scène et insertion dans le primaryStage
		Scene scene = new Scene(root, 1280, 980);
		
		// Changement de la scène d'acceuil vers la scène principale
		Stage stageJeu = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stageJeu.setScene(scene);
		stageJeu.setTitle("Plateau - Scrabble");
		stageJeu.setResizable(true);
		stageJeu.setMaxWidth(1280);
		stageJeu.setMaxHeight(980);
		stageJeu.setMinWidth(1024);
		stageJeu.setMinHeight(512);
		stageJeu.show();
	}
	
	// Fonction de vérification de la longueur du nom du joueur
	public void lettreEntree(ActionEvent e) {
		
		if (nomTxtJoueur.getLength() > 1) lanJeu.setDisable(false);
	}
}
