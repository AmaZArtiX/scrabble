/*******************************************************************************
Nom ................. : DictionnaireControlleur.java

Description ......... : Classe comportant les fonctions de contrôle utilisées
						par l'application lors d'un événement (clic sur un bout
						-on, etc...) de la scène Dictionnaire

Auteur(s) ........... : LAMPE Ronan

Dernière modification : 17/10/2017

*******************************************************************************/

// Package
package scrabble;

// Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DictionnaireControleur extends Jeu{

	// Lien entre le fichier FXML et le Label lblResRech
	@FXML private Label lblResRech;
	
	// Lien entre le fichier FXML et le TextView txtMotRech
	@FXML private TextField txtMotRech;

	// Fonction de vérification de l'existance d'un mot dans le Dictionnaire
	public void verifExist(ActionEvent e) {
		
		if (txtMotRech.getText().length() == 0) {
			
		} else {
			if (Dictionnaire.existe(txtMotRech.getText().toUpperCase())) lblResRech.setText("Résultat \"" + txtMotRech.getText().toUpperCase() + "\" : Existe");
			else lblResRech.setText("Résultat \"" + txtMotRech.getText().toUpperCase() + "\" : N'existe pas");
		}
		
		txtMotRech.setText("");
	}
}
