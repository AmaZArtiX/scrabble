/*******************************************************************************
Nom ................. : Jeu

Description ......... : Classe principale comportant une it�ration de Dictionnai
						-re, la fonction main ainsi que la sc�ne javafx principa
						-le

Auteur(s) ........... : LAMPE Ronan

Derni�re modification : 12/10/2017

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
	
	//D�claration du dictionnaire
	static Dictionnaire Dictionnaire = new Dictionnaire();
	
	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {		
		
		//Initialisation du dictionnaire
		Dictionnaire.initDico();
		
		//Lancement de la fen�tre javafx
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//D�claration des Labels
		Label lblRech = new Label("Entrez un mot � rechercher :");
		Label lblResRech = new Label();
		
		//D�claration du TextField
		TextField motRech = new TextField();
		
		//D�claration des Buttons
		Button verifExist = new Button("V�rifier l'existance");
		verifExist.setOnAction(e -> {
			//R�cup�ration du mot � rechercher
			String mot = motRech.getText().toUpperCase();
			
			//Affichage du r�sultat de la recherche dans motRech
			if(Dictionnaire.existe(mot)) lblResRech.setText("Existe.\n");
			else lblResRech.setText("N'existe pas.");
		});
		
		Button quitter = new Button("Quitter");
		quitter.setOnAction(e -> {
			System.out.println("Arr�t en cours...");
			System.exit(0);
			//Rien ne s'�x�cutera apr�s un System.exit(0);
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(lblRech, motRech, verifExist, lblResRech, quitter);
		
		//D�claration de la sc�ne et insertion dans le primaryStage
		Scene scene = new Scene(root, 200, 150);
		primaryStage.setScene(scene);
		
		//Visualisation du primaryStage
		primaryStage.setTitle("Scrabble");
		primaryStage.show();
	}

}
