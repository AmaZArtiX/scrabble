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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/*************************************************************************
 * Nom ...........: AccueilControleur.java
 * Description ...: Classe comportant les fonctions de controle utilisees
 * ...............: par l'application lors d'un evenement (clic sur un bout
 * ...............: -on, etc...) de la scene Accueil
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class AccueilControleur extends Jeu {

	// Lien entre le fichier FXML et le TextField nomTxtJoueur
	@FXML private TextField nomTxtJoueur;
	
	// Lien entre le fichier FXML et le Button lanJeu
	@FXML private Button lanJeu;
	
	// Fonction permettant d'acceder � l'ecran de jeu
	@FXML private void gotoJeu(ActionEvent event) throws IOException {
		
		// On initialise les donn�es du Joueur
		joueur.setNom(nomTxtJoueur.getText());
		
		// Test root cree avec Scene Builder
		Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Jeu.fxml"));
		
		// Declaration de la scene
		Scene scene = new Scene(root, 1280, 950);
		
		// Changement de la scene d'accueil vers la scene principale
		Stage stageJeu = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stageJeu.setScene(scene);
		stageJeu.setTitle("Plateau - Scrabble");
		stageJeu.setResizable(true);
		stageJeu.setMaxWidth(1280);
		stageJeu.setMaxHeight(950);
		stageJeu.setMinWidth(1024);
		stageJeu.setMinHeight(512);
		stageJeu.show();
		stageJeu.centerOnScreen();
	}
	
	// Fonction de verification de la longueur du nom du joueur
	@FXML private void verifPseudo(KeyEvent event) {
		
		// On verifie la taille du contenu du TextView --> Si > 1 (>=2) alors le bouton
		// de lancement sera active (cliquable) | Sinon, le bouton sera desactive
		if (((TextField) event.getSource()).getLength() > 1) lanJeu.setDisable(false);
		else lanJeu.setDisable(true);
		
		// On termine l'evenement
		event.consume();
	}
	
	// Fonction permettant le lancement de la fonction gotoJeu depuis nomTxtJoueur 
	@FXML private void lancerJeu(ActionEvent event) throws IOException {
		
		// On verifie si le bouton de lancement est active avant de lancer le Jeu
		if(!lanJeu.isDisabled())
			gotoJeu(event);
	}
}
