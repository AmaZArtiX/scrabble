// Package
package scrabble;

// Import(s)
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*************************************************************************
 * Nom ...........: Jeu.java
 * Description ...: Classe principale comportant une iteration de Diction
 * ...............: -naire, la fonction main ainsi que la scene javafx 
 * ...............: principale
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class Jeu extends Application {
	
	// Declaration du Dictionnaire
	static Dictionnaire Dictionnaire = new Dictionnaire();
	
	// Declaration du Joueur
	static Joueur Joueur;
	
	// Declaration du Sac et remplissage avec les 102 Tuiles
	static Sac s = new Sac();
	
	// Declaration du Plateau
	static Plateau p = new Plateau();
	
	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {	
	
		// Lancement de la fenetre javafx --> fonction "start"
		launch(args);
		
	
		
		// Affichage du plateau 
		p.afficher();

		// Affichage du chevalet du joueur 1
		//System.out.println("\n" + Joueur.getChevalet() + "\n");
		
		// Affichage du sac après après supprimé des tuiles
		//s.afficherSac();
		//Tuile t = new Tuile('*', 0, new Image("Jocker.png"));
		//int i = s.getNombreTuile(t);
		//System.out.println(i);
	}

	// Fonction permettant le lancement de l'application
	@Override
	public void start(Stage stageAccueil) throws Exception {
		
		// Interface d'Accueil cree avec Scene Builder (Accueil.fxml)
		Parent rootAccueil = FXMLLoader.load(getClass().getResource("/scrabble/Accueil.fxml"));
		
		// Declaration de la scene javafx
		Scene sceneAccueil = new Scene(rootAccueil, 400, 200);
		
		// Visualisation de stageAccueil
		stageAccueil.setScene(sceneAccueil);
		stageAccueil.getIcons().add(new Image("S.png"));
		stageAccueil.setTitle("Accueil - Scrabble");
		stageAccueil.setResizable(false);
		stageAccueil.show();
	}
}
