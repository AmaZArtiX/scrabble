// Package
package scrabble;

// Import(s)
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
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
	
	// Lien entre le fichier FXML et le GridPane du Plateau
	@FXML private GridPane grillePlateau;
	
	// Tableau d'ImageView des cases du Plateau
	private ImageView[] casesPlateau = new ImageView[Plateau.TAILLE*Plateau.TAILLE];

	// Lien entre le fichier FXML et le GridPane du Chevalet
	@FXML private GridPane grilleChevalet;
	
	// Tableau d'ImageView des cases du Chevalet
	private ImageView[] casesChevalet = new ImageView[Chevalet.TAILLE];
	
	// Lien entre le fichier FXML et le Button melrec
	@FXML private Button melrec;
	
	// Fonction permettant d'acceder au dictionnaire
	@FXML private void gotoDictionnaire(ActionEvent e) throws IOException {
		
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
	@FXML private void quitter(ActionEvent e) {
		
		System.exit(0);
	}
	
	// Fonction d'initialisation de la fenetre de Jeu
	public void initialize() {
		
		// Initialisation du Plateau de Jeu
		plateau.initialiser();
		
		// Affichage du Score du Joueur ainsi que son nom
		lblScoreJ.setText(joueur.getNom() + " : " + Integer.toString(joueur.getScore()));
		
		// Initialisation de casesPlateau avec les ImageView de grillePlateau
		for(int i=0;i<(Plateau.TAILLE*Plateau.TAILLE);i++) {
			casesPlateau[i] = (ImageView) grillePlateau.getChildren().get(i);
		}
		
		// Initialisation de casesChevalet avec les ImageView de grilleChevalet
		for(int i=0;i<Chevalet.TAILLE;i++) {
			casesChevalet[i] = (ImageView) grilleChevalet.getChildren().get(i);
		}
		
		// Recuperation du Chevalet du Joueur et remplissage avec 7 Tuiles
		joueur.getChevalet().remplir(sac);
		joueur.setChevaletTampon(joueur.getChevalet());
		
		// On raffraichit les ImageView du Chevalet
		raffraichissementChevalet();
	}
	
	// Fonction permettant d'acceder a l'echange de tuiles
	@FXML private void gotoEchange(ActionEvent e) throws IOException {
		
		// Test root cree avec Scene Builder
		Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Echange.fxml"));
		
		// Declaration de la scene
		Scene scene = new Scene(root, 600, 550);
		
		// Changement de la scene d'accueil vers la scene principale
		Stage stageEchange = new Stage();
		stageEchange.setScene(scene);
		stageEchange.getIcons().add(new Image("S.png"));
		stageEchange.setTitle("Echanger des lettres");
		stageEchange.setResizable(false);
		stageEchange.initOwner(((Button) e.getSource()).getScene().getWindow());
		stageEchange.initModality(Modality.WINDOW_MODAL);
		stageEchange.setOnHidden(EventHandler -> raffraichissementChevalet());
		stageEchange.show();
	}
	
	// Fonction de melange des Tuiles du Chevalet
	@FXML private void melangeChevalet() {
		
		// Melange des Tuiles du Chevalet du Joueur
		joueur.getChevalet().melanger();

		// On raffraichit les ImageView du Chevalet
		raffraichissementChevalet();
	}
	
	// Fonction de detection d'un drag'n'drop
	@FXML private void dragDetected(MouseEvent event) {
		
		Dragboard dragboard = ((Node) event.getSource()).startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent clipboardContent = new ClipboardContent();
		clipboardContent.putImage(((ImageView) event.getSource()).getImage());
		
		dragboard.setContent(clipboardContent);
		
		event.consume();
	}
	
	// Fonction de detection d'un drag over
	@FXML private void dragOver(DragEvent event) {
		
		// On recupere les coordonnees de jeu de la Tuile (Plateau)
		int col = GridPane.getColumnIndex((Node) event.getSource());
		int lig = GridPane.getRowIndex((Node) event.getSource());

		// On verifie si le drag contient une Image
		if(event.getDragboard().hasImage()) {

			// On verifie si aucune Tuile n'est deja presente sur la case souhaitee
			if(!plateau.existeTuile(col, lig)) {

				// On verifie si la case cible est la case de depart
				if(col == 7 & lig == 7) {

					// On autorise le drop
					event.acceptTransferModes(TransferMode.ANY);

				} else if (!plateau.tuileSeule(col, lig)) {

					// On autorise le drop
					event.acceptTransferModes(TransferMode.ANY);
				}
			}
		}
	}
	
	// Fonction de detection d'un drag dropped
	@FXML private void dragDropped(DragEvent event) {
		
		// On recupere l'indice de la Tuile jouee (Chevalet)
		int index = GridPane.getColumnIndex((Node) event.getGestureSource());
		
		// On recupere les coordonnees de jeu de la Tuile (Plateau)
		int col = GridPane.getColumnIndex((Node) event.getSource());
		int lig = GridPane.getRowIndex((Node) event.getSource());
		
		// On ajoute la Tuile jouee a plateauTuilesTampon
		plateau.placerTuile(lig, col, joueur.getChevalet().getTuile(index));
		
		// On supprime la Tuile jouee du Chevalet Tampon du Joueur
		joueur.getChevaletTampon().supprimerTuile(index);
		
		// On change le nom et la fonction du bouton Melanger
		melrec.setText("Récupérer");
		melrec.setOnAction(EventHandler -> {
			
			// 
			recupTuilesJouee();			
			
			// On change le nom et la fonction du bouton Récupérer pour revenir à Melanger
			melrec.setText("Mélanger");
			melrec.setOnAction(e -> melangeChevalet());
		});
	}
	
	// Fonction de detection d'un drag done
	@FXML private void dragDone(DragEvent event) {
		
		// On raffraichit les ImageView du Chevalet
		raffraichissementChevalet();
		
		// On raffraichit les ImageView du Plateau
		raffraichissementPlateau();
	}
	
	// Fonction de recuperation des tuiles jouees
	private void recupTuilesJouee() {
		
		// On supprime les Tuiles jouees de plateauTuilesTampon
		plateau.sauvegarderPlateauTuiles();

		// On recupere l'etat du Chevalet au debut du Jeu
		joueur.setChevaletTampon(joueur.getChevalet());
		
		/* PROBLEME DE RAFFRAICHISSEMENT */
		// On raffraichit les ImageView du Chevalet
		raffraichissementChevalet();

		// On raffraichit les ImageView du Plateau
		raffraichissementPlateau();
	}
	
	// Fonction de raffraichissement des ImageView du Chevalet en fonction du Chevalet du Joueur
	private void raffraichissementChevalet() {
		
		// Le Chevalet du Joueur n'est pas vide
		if(!joueur.getChevaletTampon().estVide()) {
			
			int i; // On met a jour les ImageView du Chevalet en fonction du Chevalet Tampon du Joueur
			for(i=0;i<joueur.getChevaletTampon().getTaille();i++) {
				
				// 
				casesChevalet[i].setImage(joueur.getChevaletTampon().getTuile(i).getImg());
			}
			
			// Si le nombre de Tuile present dans le Chevalet du Joueur est inferieur a la taille
			// MAX du Chevalet alors on vide les ImageView restants
			if(i<Chevalet.TAILLE) {
				
				// 
				while (i<Chevalet.TAILLE) {
					
					// 
					casesChevalet[i].setImage(null); i++;
				}
			}
		} else { // Le Chevalet du Joueur est vide
			
			// On vide les ImageView du Chevalet
			for(int i=0;i<Chevalet.TAILLE;i++) {
				
				// 
				casesChevalet[i].setImage(null);
			}
		}
	}
	
	// Fonction de raffraichissement des ImageView du Plateau en fonction du tableau plateauTuiles
	private void raffraichissementPlateau() {
		
		// Le tableau plateauTuiles n'est pas vide
		if(!plateau.getPlateauTuilesTampon().equals(null)) {
			
			// On parcours toutes les lignes
			for(int i=0;i<Plateau.TAILLE;i++) {
				
				// On parcours toutes les colonnes
				for(int j=0;j<Plateau.TAILLE;j++) {
					
					// Si un bonus est present mais pas de Tuile alors on entre dans le if
					if((plateau.getStringBonus(i, j) != "") & !(plateau.getTuileTampon(i, j) != null)) {
						
						// On recupere le String du bonus (de plateauBonus)
						switch (plateau.getStringBonus(i, j)) {
							case "LD":
								// On met l'Image de la lettre double dans l'ImageView du Plateau
								casesPlateau[j*Plateau.TAILLE+i].setImage(new Image("LD.png"));
								break;
							case "LT":
								// On met l'Image de la lettre triple dans l'ImageView du Plateau
								casesPlateau[j*Plateau.TAILLE+i].setImage(new Image("LT.png"));
								break;
							case "MD":
								// On met l'Image du mot double dans l'ImageView du Plateau
								if(i == 7 & j == 7) casesPlateau[j*Plateau.TAILLE+i].setImage(new Image("CD.png"));
								else casesPlateau[j*Plateau.TAILLE+i].setImage(new Image("MD.png"));
								break;
							case "MT":
								// On met l'Image du mot triple dans l'ImageView du Plateau
								casesPlateau[j*Plateau.TAILLE+i].setImage(new Image("MT.png"));
								break;
							default:
								break;
						}
					} else 
					
					// Si une Tuile est presente alors on entre dans le if
					if (plateau.getTuileTampon(i, j) != null) {
						
						// On affiche son Image dans l'ImageView du Plateau
						casesPlateau[j*Plateau.TAILLE+i].setImage(plateau.getTuileTampon(i, j).getImg());
					} else {
						
						// La case est vide donc on met l'Image de l'ImageView a vide
						casesPlateau[j*Plateau.TAILLE+i].setImage(null);
					}
				}
			}
		}
	}
}
