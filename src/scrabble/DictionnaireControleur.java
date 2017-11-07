// Package
package scrabble;

// Import(s)
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/*************************************************************************
 * Nom ...........: DictionnaireControleur.java
 * Description ...: Classe comportant les fonctions de controle utilisees
 * ...............: par l'application lors d'un evenement (clic sur un bout
 * ...............: bouton, etc...) de la scene Dictionnaire
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class DictionnaireControleur extends Jeu{

	// Lien entre le fichier FXML et le Label lblResRech
	@FXML private Label lblResRech;
	
	// Lien entre le fichier FXML et le TextView txtMotRech
	@FXML private TextField txtMotRech;

	// Fonction de verification de l'existance d'un mot dans le Dictionnaire
	@FXML private void verifExist(ActionEvent e) {
		
		// Si le mot recherche est null alors rien n'est fait
		if (txtMotRech.getText().length() == 0) {
			
		} else {
			
			// Si le mot recherche n'est pas null alors le recherche dans le dictionnaire et on affiche
			// le resultat
			if (dictionnaire.existe(txtMotRech.getText().toUpperCase())) {
				lblResRech.setText(txtMotRech.getText().substring(0, 1).toUpperCase() +txtMotRech.getText().substring(1).toLowerCase() + " est un mot valide dans le dictionnaire francais");
				lblResRech.setTextFill(Color.web("007024"));
			}
			else {
				lblResRech.setText(txtMotRech.getText().substring(0, 1).toUpperCase() +txtMotRech.getText().substring(1).toLowerCase() + " n'est pas un mot valide dans le dictionnaire francais");
				lblResRech.setTextFill(Color.web("FF0000"));
			}
		}
		
		// On vide le TextView txtMotRech de son contenu
		txtMotRech.setText("");
	}
}
