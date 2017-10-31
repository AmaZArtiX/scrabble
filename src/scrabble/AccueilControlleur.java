
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

/*************************************************************************
 * Nom ...........: AccueilControlleur.java
 * Description ...: Classe comportant les fonctions de contr�le utilis�es
 * ...............: par l'application lors d'un �v�nement (clic sur un bout
 * ...............: -on, etc...) de la sc�ne Accueil
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class AccueilControlleur extends Jeu {

	// Lien entre le fichier FXML et le TextField nomTxtJoueur
	@FXML private TextField nomTxtJoueur;
	
	// Lien entre le fichier FXML et le Button lanJeu
	@FXML private Button lanJeu;
	
	// Fonction permettant d'acc�der � l'�cran de jeu
	public void gotoJeu(ActionEvent e) throws IOException {
		
		// On r�cup�re le nom du joueur
		nomJoueur = nomTxtJoueur.getText();
		
		// Test root cr�� avec Scene Builder
		Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Jeu.fxml"));
		
		// D�claration de la sc�ne et insertion dans le primaryStage
		Scene scene = new Scene(root, 1280, 980);
		
		// Changement de la sc�ne d'acceuil vers la sc�ne principale
		Stage stageJeu = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stageJeu.setScene(scene);
		stageJeu.setTitle("Plateau - Scrabble");
		stageJeu.show();
	}
	
	// Fonction de v�rification de la longueur du nom du joueur
	public void lettreEntree(ActionEvent e) {
		
		if (nomTxtJoueur.getLength() > 1) lanJeu.setDisable(false);
	}
}
