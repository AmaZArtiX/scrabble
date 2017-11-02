// Package
package scrabble;

// Import(s)
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
 * Nom ...........: AccueilControleur.java
 * Description ...: Classe comportant les fonctions de controle utilisees
 * ...............: par l'application lors d'un evenement (clic sur un bout
 * ...............: -on, etc...) de la scene Accueil
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: Â© 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class AccueilControleur extends Jeu {

	// Lien entre le fichier FXML et le TextField nomTxtJoueur
	@FXML private TextField nomTxtJoueur;
	
	// Lien entre le fichier FXML et le Button lanJeu
	@FXML private Button lanJeu;
	
	// Fonction permettant d'acceder à l'ecran de jeu
	public void gotoJeu(ActionEvent e) throws IOException {
		
		// On initialise les données du Joueur
		Joueur = new Joueur(nomTxtJoueur.getText());
		
		// Test root cree avec Scene Builder
		Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Jeu.fxml"));
		
		// Declaration de la scene
		Scene scene = new Scene(root, 1280, 950);
		
		// Changement de la scene d'acceuil vers la scene principale
		Stage stageJeu = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stageJeu.setScene(scene);
		stageJeu.setTitle("Plateau - Scrabble");
		stageJeu.setResizable(true);
		stageJeu.setMaxWidth(1280);
		stageJeu.setMaxHeight(950);
		stageJeu.setMinWidth(1024);
		stageJeu.setMinHeight(512);
		stageJeu.show();
	}
	
	// Fonction de verification de la longueur du nom du joueur
	public void lettreEntree(ActionEvent e) {
		
		if (nomTxtJoueur.getLength() > 1) lanJeu.setDisable(false);
	}
}
