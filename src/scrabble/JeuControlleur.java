/*******************************************************************************
Nom ................. : JeuControlleur.java

Description ......... : Classe comportant les fonctions de contr�le utilis�es
						par l'application lors d'un �v�nement (clic sur un bout
						-on, etc...)

Auteur(s) ........... : LAMPE Ronan

Derni�re modification : 13/10/2017

*******************************************************************************/

// Package
package scrabble;

// Imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class JeuControlleur extends Jeu {

	// Lien entre le fichier FXML et le Label lblResRech
	@FXML private Label lblResRech;
	
	// Lien entre le fichier FXML et le TextView motRech
	@FXML private TextField motRech;
	
	// Fonction de v�rification de l'existance d'un mot dans le Dictionnaire
	public void verifExist(ActionEvent e) {
		
		if  (Dictionnaire.existe(motRech.getText().toUpperCase())) lblResRech.setText("Existe");
		else lblResRech.setText("N'existe pas");
		
		motRech.setText("");
	}
	
	// Fonction permettant de quitter l'application
	public void quitter(ActionEvent e) {
		
		System.exit(0);
	}
}
