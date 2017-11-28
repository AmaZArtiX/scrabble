// Package
package scrabble;

// Import(s)
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

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

	// 
	@FXML private AnchorPane anchorPane;
	
	// Lien entre le fichier FXML et le TextField nomTxtJoueur
	@FXML private TextField nomTxtJoueur1;
	
	// Lien entre le fichier FXML et le TextField nomTxtJoueur
	@FXML private TextField nomTxtJoueur2;
	
	// Lien entre le fichier FXML et le Button lanJeu
	@FXML private Button lanJeu;
	
	@FXML private ComboBox<String> cbNombreJoueurs;
	
	public void initialize() {
		
		// 
		cbNombreJoueurs.getSelectionModel().selectFirst();
	}
	
	// Fonction permettant d'acceder a l'ecran de jeu
	@FXML private void gotoJeu(ActionEvent event) {
		
		if(cbNombreJoueurs.getValue().equals("1 Joueur")) {
			
			// On initialise les donnees du Joueur
			Joueurs.add(new Joueur(nomTxtJoueur1.getText()));
			
		} else if(cbNombreJoueurs.getValue().equals("2 Joueurs")) {
			
			// On initialise les donnees du Joueur
			Joueurs.add(new Joueur(nomTxtJoueur1.getText()));
			
			// On initialise les donnees du Joueur
			Joueurs.add(new Joueur(nomTxtJoueur2.getText()));
		}
		
		disparitionScene();
	}
	
	// Fonction de verification de la longueur du nom du joueur
	@FXML private void verifPseudo() {
		
		// On verifie la taille du contenu du TextView --> Si > 1 (>=2) alors le bouton
		// de lancement sera active (cliquable) | Sinon, le bouton sera desactive
		
		switch(cbNombreJoueurs.getValue()) {
		
		case "1 Joueur":
			
			if (nomTxtJoueur1.getLength() > 1) {
				lanJeu.setDisable(false);
			}
			else lanJeu.setDisable(true);
			break;
		case "2 Joueurs":
			
			if (nomTxtJoueur1.getLength() > 1 & nomTxtJoueur2.getLength() > 1) {
				lanJeu.setDisable(false);
			}
			else lanJeu.setDisable(true);
			break;
		
		}
	}
	
	// Fonction permettant le lancement de la fonction gotoJeu depuis nomTxtJoueur 
	@FXML private void lancerJeu(ActionEvent event) throws IOException {
		
		// On verifie si le bouton de lancement est active avant de lancer le Jeu
		if(!lanJeu.isDisabled())
			gotoJeu(event);
	}
	
	@FXML private void nbrJoueurs() {
		
		// 
		switch(cbNombreJoueurs.getValue()) {
		case "1 Joueur":
			
			// 
			nomTxtJoueur2.setDisable(true);
			nomTxtJoueur2.clear();
			verifPseudo();
			break;
		case "2 Joueurs":
			
			// 
			nomTxtJoueur2.setDisable(false);
			verifPseudo();
			break;
		}
	}
	
	// 
	private void disparitionScene() {
		
		// 
		FadeTransition transition = new FadeTransition();
		transition.setDuration(Duration.millis(500));
		transition.setNode(anchorPane);
		transition.setFromValue(1);
		transition.setToValue(0);
		transition.setOnFinished(e -> chargementJeu());
		transition.play();
	}
	
	// 
	private void chargementJeu() {
		 
		try {
			// Test root cree avec Scene Builder
			Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Jeu.fxml"));
			
			// Declaration de la scene
			Scene scene = new Scene(root);

			// Changement de la scene d'accueil vers la scene principale
			Stage stageJeu = (Stage) anchorPane.getScene().getWindow();
			stageJeu.setScene(scene);
			stageJeu.setTitle("Plateau - Scrabble");
			stageJeu.getIcons().add(new Image("S.png"));
			stageJeu.setResizable(false);
			stageJeu.show();
			//stageJeu.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
