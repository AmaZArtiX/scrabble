/*******************************************************************************
Nom ................. : Jeu

Description ......... : Classe principale comportant une itération de Dictionnai
						-re, la fonction main ainsi que la scène javafx principa
						-le

Auteur(s) ........... : LAMPE Ronan

Dernière modification : 12/10/2017

*******************************************************************************/

// Package
package scrabble;

// Import(s)
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Jeu extends Application {
	
	//Déclaration du dictionnaire
	static Dictionnaire Dictionnaire = new Dictionnaire();
	
	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {		
		
		//Initialisation du dictionnaire
		Dictionnaire.initDico();
		
		//Lancement de la fenêtre javafx
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Déclaration des Labels
		Label lblRech = new Label("Entrez un mot à rechercher :");
		Label lblResRech = new Label();
		
		//Déclaration du TextField
		TextField motRech = new TextField();
		
		//Déclaration des Buttons
		Button verifExist = new Button("Vérifier l'existance");
		verifExist.setOnAction(e -> {
			//Récupération du mot à rechercher
			String mot = motRech.getText().toUpperCase();
			
			//Affichage du résultat de la recherche dans motRech
			if(Dictionnaire.existe(mot)) lblResRech.setText("Existe.\n");
			else lblResRech.setText("N'existe pas.");
		});
		
		Button quitter = new Button("Quitter");
		quitter.setOnAction(e -> {
			System.out.println("Arrêt en cours...");
			System.exit(0);
			//Rien ne s'éxécutera après un System.exit(0);
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(lblRech, motRech, verifExist, lblResRech, quitter);
		
		//Déclaration de la scène et insertion dans le primaryStage
		Scene scene = new Scene(root, 200, 150);
		primaryStage.setScene(scene);
		
		//Visualisation du primaryStage
		primaryStage.setTitle("Scrabble");
		primaryStage.show();
	}

}
