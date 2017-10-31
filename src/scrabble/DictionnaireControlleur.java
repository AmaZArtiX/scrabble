// Package
package scrabble;

// Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/*************************************************************************
 * Nom ...........: DictionnaireControlleur.java
 * Description ...: Classe comportant les fonctions de contr�le utilis�es
 * ...............: par l'application lors d'un �v�nement (clic sur un bout
 * ...............: -on, etc...) de la sc�ne Dictionnaire
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class DictionnaireControlleur extends Jeu{

	// Lien entre le fichier FXML et le Label lblResRech
	@FXML private Label lblResRech;
	
	// Lien entre le fichier FXML et le TextView txtMotRech
	@FXML private TextField txtMotRech;

	// Fonction de v�rification de l'existance d'un mot dans le Dictionnaire
	public void verifExist(ActionEvent e) {
		
		if (txtMotRech.getText().length() == 0) {
			
		} else {
			if (Dictionnaire.existe(txtMotRech.getText().toUpperCase())) lblResRech.setText("R�sultat \"" + txtMotRech.getText().toUpperCase() + "\" : Existe");
			else lblResRech.setText("R�sultat \"" + txtMotRech.getText().toUpperCase() + "\" : N'existe pas");
		}
		
		txtMotRech.setText("");
	}
}
