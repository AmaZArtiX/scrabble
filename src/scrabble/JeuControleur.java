// Package
package scrabble;

// Import(s)
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*************************************************************************
 * Nom ...........: JeuControleur.java
 * Description ...: Classe comportant les fonctions de controle utilisees
 * ...............: par l'application lors d'un evenement (clic sur un
 * ...............: bouton, etc...) de la scene Jeu
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: Â© 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class JeuControleur extends Jeu {

	// Lien entre le fichier FXML et le Label lbl
	@FXML private Label lblScoreJ;
	
	// Fonction permettant d'accèder au dictionnaire
	public void gotoDictionnaire(ActionEvent e) throws IOException {
		
		// Test root cree avec Scene Builder
		Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Dictionnaire.fxml"));
		
		// Declaration de la scene
		Scene scene = new Scene(root, 300, 300);
		
		// Changement de la scene d'accueil vers la scene principale
		Stage stageDictionnaire = new Stage();
		stageDictionnaire.setScene(scene);
		stageDictionnaire.getIcons().add(new Image("S.png"));
		stageDictionnaire.setTitle("Dictionnaire - Scrabble");
		stageDictionnaire.setResizable(false);
		stageDictionnaire.show();
	}
	
	// Fonction permettant de quitter l'application
	public void quitter(ActionEvent e) {
		
		System.exit(0);
	}
	
	// Fonction d'initialisation de la fenetre de Jeu
	public void initialize() {
		
		lblScoreJ.setText("Score du Joueur \"" + nomJoueur + "\" : " + Integer.toString(scoreJoueur));
	}
}
