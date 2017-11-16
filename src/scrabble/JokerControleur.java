// Package
package scrabble;

// Import(s)
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*************************************************************************
 * Nom ...........: JokerControleur.java
 * Description ...: Classe comportant les fonctions de controle utilisees
 * ...............: par l'application lors d'un evenement (clic sur un bout
 * ...............: bouton, etc...) de la scene Joker
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: Â© 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class JokerControleur extends Jeu {

	// Lien entre le fichier FXML et le GridPane de Tuiles
	@FXML private GridPane grilleTuiles;
	
	// Tableau de Label des cases de Tuiles
	private Label[] casesLabel = new Label[26];
	
	// Lien entre le fichier FXML et le GridPane de Joker
	@FXML private GridPane grilleJoker;
	
	// ImageView de la case Joker
	private ImageView caseJoker = null;
	
	public void initialize() {
		
		// On affecte l'ImageView de grilleJoker a caseJoker 
		caseJoker = (ImageView) grilleJoker.getChildren().get(0);
		
		// On affiche la Tuile Joker dans caseJoker
		caseJoker.setImage(new Image("Joker.png"));
		
		// Initialisation de casesLabel avec les Label de grilleTuiles et affichage du nombre de tuile
		for(int i=0;i<26;i++) {
			
			// On recupere le nb d'occurence de la Tuile en question
			int nbTuiles = sac.getNombreTuiles(new Tuile(((ImageView) ((HBox) grilleTuiles.getChildren().get(i)).getChildren().get(0)).getImage()));
			
			// On affiche le nb d'occurence dans le Label
			casesLabel[i] = (Label) ((HBox) grilleTuiles.getChildren().get(i)).getChildren().get(1);
			casesLabel[i].setText(String.valueOf(nbTuiles));
			
			// On change la couleur du Label en fonction du nb d'occurence
			if(nbTuiles < 1) casesLabel[i].setTextFill(Color.web("FF0000"));
			else casesLabel[i].setTextFill(Color.web("007024"));
		}
	}
	
	// Fonction de choix de Tuile
	@FXML private void choixTuile(MouseEvent event) {
		
		// On change l'image de caseJoker avec l'image de la Tuile choisie
		caseJoker.setImage(((ImageView) event.getSource()).getImage());
	}
	
	// Fonction de confirmation de choix de Tuile
	@FXML private void confirmChoixTuile(ActionEvent event) {
		
		// On verifie si la Tuile de caseJoker n'est pas un Joker
		if(!Lettre.imgEgales(caseJoker.getImage(), Lettre.JOKER.img)) {
			
			/*// On verifie si le nb d'occurence de la Tuile choisie est strictement superieur a 0
			if(sac.getNombreTuiles(new Tuile(caseJoker.getImage())) > 0) {*/
				
				// On affecte la Tuile de caseJoker a joker
				joker = new Tuile(0 ,caseJoker.getImage());
				
				// On quitte la fenêtre
			    ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
			//}
		}
	}
}
