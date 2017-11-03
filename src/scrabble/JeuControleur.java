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
import javafx.scene.image.ImageView;
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

	// Lien entre le fichier FXML et le Label de Score du Joueur
	@FXML private Label lblScoreJ;
	
	// Lien entre le fichier FXML et les ImageView des cases du Chevalet
	@FXML private ImageView caseChevalet0;
	@FXML private ImageView caseChevalet1;
	@FXML private ImageView caseChevalet2;
	@FXML private ImageView caseChevalet3;
	@FXML private ImageView caseChevalet4;
	@FXML private ImageView caseChevalet5;
	@FXML private ImageView caseChevalet6;
	
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
		
		lblScoreJ.setText("Score du Joueur \"" + Joueur.getNom() + "\" : " + Integer.toString(Joueur.getScore()));
	}
	
	// Fonction de remplissage du Chevalet
	public void remplissageChevalet() {
		
		// Recuperation du Chevalet du Joueur et remplissage avec 7 Tuiles
		Joueur.getChevalet().remplir(s);
		
		// Mise à jour du Chevalet Tampon du Joueur
		Joueur.setChevaletTampon(Joueur.getChevalet());
		
		// Initialisation du Chevalet Graphique avec le Chevalet remplit
		caseChevalet0.setImage(Joueur.getChevalet().getTuile(0).getImg());
		caseChevalet1.setImage(Joueur.getChevalet().getTuile(1).getImg());
		caseChevalet2.setImage(Joueur.getChevalet().getTuile(2).getImg());
		caseChevalet3.setImage(Joueur.getChevalet().getTuile(3).getImg());
		caseChevalet4.setImage(Joueur.getChevalet().getTuile(4).getImg());
		caseChevalet5.setImage(Joueur.getChevalet().getTuile(5).getImg());
		caseChevalet6.setImage(Joueur.getChevalet().getTuile(6).getImg());
	}
	
	// Fonction de mélange des Tuiles du Chevalet
	public void melangeChevalet() {
		
		// Melange des Tuiles du Chevalet du Joueur
		Joueur.getChevalet().melanger();

		// Mise à jour du Chevalet Tampon du Joueur
		Joueur.setChevaletTampon(Joueur.getChevalet());
		
		// Initialisation du Chevalet Graphique avec le Chevalet remplit
		caseChevalet0.setImage(Joueur.getChevalet().getTuile(0).getImg());
		caseChevalet1.setImage(Joueur.getChevalet().getTuile(1).getImg());
		caseChevalet2.setImage(Joueur.getChevalet().getTuile(2).getImg());
		caseChevalet3.setImage(Joueur.getChevalet().getTuile(3).getImg());
		caseChevalet4.setImage(Joueur.getChevalet().getTuile(4).getImg());
		caseChevalet5.setImage(Joueur.getChevalet().getTuile(5).getImg());
		caseChevalet6.setImage(Joueur.getChevalet().getTuile(6).getImg());
	}
}
