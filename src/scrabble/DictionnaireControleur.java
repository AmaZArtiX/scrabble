// Package
package scrabble;

// Import(s)
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/*************************************************************************
 * Nom ...........: DictionnaireControleur.java
 * Description ...: Classe comportant les fonctions de controle utilisees
 * ...............: par l'application lors d'un evenement (clic sur un bout
 * ...............: bouton, etc...) de la scene Dictionnaire
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: Â© 2017 RONAN LAMPE ALL RIGHTS RESERVED
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
			if (dictionnaire.existe(txtMotRech.getText().toUpperCase()))
				lblResRech.setText("Résultat \"" + txtMotRech.getText().toUpperCase() + "\" : Existe");
			else 
				lblResRech.setText("Résultat \"" + txtMotRech.getText().toUpperCase() + "\" : N'existe pas");
		}
		
		// On vide le TextView txtMotRech de son contenu
		txtMotRech.setText("");
	}
}
