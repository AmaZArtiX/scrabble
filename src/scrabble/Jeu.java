// Package
package scrabble;

// Import(s)
import java.util.ArrayList;
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
		
		/*
		// Affichage du plateau 
<<<<<<< HEAD
		//p.afficher();
=======
		p.afficher();
		
>>>>>>> refs/remotes/origin/branche-Ronan
		// Affichage du chevalet du joueur 1
<<<<<<< HEAD
		//System.out.println(j.getChevalet());
		// Affichage du sac après après supprimé des tuiles
		//s.afficherSac();
=======
		System.out.println(Joueur.getChevalet());
		
		// Affichage du sac apres avoir supprime des tuiles
		s.afficherSac();
>>>>>>> refs/remotes/origin/branche-Ronan
		
<<<<<<< HEAD
		//p.placerTuile(7, 7, 0, j.getChevalet().getTuile(0));
		//j.getChevalet().supprimerTuile(0);
		//p.placerTuile(7, 7, 0, j.getChevalet().getTuile(1));
		//j.getChevalet().supprimerTuile(1);
		//p.placerTuile(7, 7, 1, j.getChevalet().getTuile(2));
		//j.getChevalet().supprimerTuile(2);
		//j.getChevalet().reRemplir(s);
=======
		p.placerTuile(7, 7, 0, Joueur.getChevalet().getTuile(0));
		//Joueur.getChevalet().supprimerTuile(0);
		p.placerTuile(7, 7, 0, Joueur.getChevalet().getTuile(1));
		//Joueur.getChevalet().supprimerTuile(1);
		p.placerTuile(7, 7, 1, Joueur.getChevalet().getTuile(2));
		//Joueur.getChevalet().supprimerTuile(2);
		//Joueur.getChevalet().reRemplir(s);
>>>>>>> refs/remotes/origin/branche-Ronan
		
		
<<<<<<< HEAD
		ArrayList<Tuile> mots = j.getChevalet().getTuiles();
		ArrayList <String> bonus = new ArrayList<>();
		
		bonus.add("");
		bonus.add("");
		bonus.add("MT");
		bonus.add("");
		bonus.add("");
		bonus.add("");
		bonus.add("MT");
		
		System.out.println(mots);
		int score = p.calculScoreMot(mots, bonus);
		System.out.println(score);
=======
		ArrayList<Tuile> l = p.getTuilesDisponibles();
		String mot = p.concatenerChaines(l, Joueur.getChevalet());
		System.out.println(l);
		System.out.println(mot);
		*/

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
