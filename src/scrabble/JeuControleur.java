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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*************************************************************************
 * Nom ...........: JeuControleur.java
 * Description ...: Classe comportant les fonctions de controle utilisees
 * ...............: par l'application lors d'un evenement (clic sur un
 * ...............: bouton, etc...) de la scene Jeu
 * Auteur(s) .....: RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class JeuControleur extends Jeu {

	// Lien entre le fichier FXML et le Label de Score du Joueur
	@FXML private Label lblScoreJ;
	
	// Lien entre le fichier FXML et le GridPane du Plateau
	@FXML private GridPane grillePlateau;
	
	// Tableau d'ImageView des cases du Plateau
	private ImageView[][] casesPlateau = new ImageView[Plateau.TAILLE][Plateau.TAILLE];

	// Lien entre le fichier FXML et le GridPane du Chevalet
	@FXML private GridPane grilleChevalet;
	
	// Tableau d'ImageView des cases du Chevalet
	private ImageView[] casesChevalet = new ImageView[Chevalet.TAILLE];
	
	// Fonction permettant d'acc�der au dictionnaire
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
		p.initialiser();
		
		// Affichage du Score du Joueur ainsi que son nom
		lblScoreJ.setText("Score du Joueur \"" + Joueur.getNom() + "\" : " + Integer.toString(Joueur.getScore()));
		
		// Initialisation de casesPlateau avec les ImageView de grillePlateau
		for(int i=0;i<Plateau.TAILLE;i++) {
			for(int j=0;j<Plateau.TAILLE;j++) {
				casesPlateau[i][j] = (ImageView) grillePlateau.getChildren().get(i);
			}
		}
		
		// Initialisation de casesChevalet avec les ImageView de grilleChevalet
		for(int i=0;i<Chevalet.TAILLE;i++) {
			casesChevalet[i] = (ImageView) grilleChevalet.getChildren().get(i);
		}
	}
	
	// Fonction de remplissage du Chevalet
	@FXML private void remplissageChevalet() {
		
		// Recuperation du Chevalet du Joueur et remplissage avec 7 Tuiles
		Joueur.getChevalet().remplir(s);

		// On raffraichit les ImageView du Chevalet
		raffraichissementChevalet();
		
		// Affichage du Chevalet du Joueur (Test Terminal)
		System.out.println("\n" + Joueur.getChevalet() + "\n");
	}
	
	// Fonction de m�lange des Tuiles du Chevalet
	@FXML private void melangeChevalet() {
		
		// Melange des Tuiles du Chevalet du Joueur
		Joueur.getChevalet().melanger();

		// On raffraichit les ImageView du Chevalet
		raffraichissementChevalet();
		
		// Affichage du Chevalet du Joueur (Test Terminal)
		System.out.println("\n" + Joueur.getChevalet() + "\n");
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
		
		try {
			// On r�cup�re les coordonnees de jeu de la Tuile (Plateau)
			int col = GridPane.getColumnIndex((Node) event.getSource());
			int lig = GridPane.getRowIndex((Node) event.getSource());
			
			// On verifie si le drag contient une Image
			if(event.getDragboard().hasImage()) {
				
				// On verifie si aucune Tuile n'est deja presente sur la case souhaitee
				if(!p.existeTuile(col, lig)) {
					
					// On verifie si la case cible est la case de depart
					if(col == lig & col == 7) {
						
						// On autorise le drop
						event.acceptTransferModes(TransferMode.ANY);
						
					/*} else if (!p.tuileSeule(col, lig)) {
						
						// On autorise le drop
						event.acceptTransferModes(TransferMode.ANY);*/
					} else if (p.existeTuile(col, lig-1)) {
						
						// On autorise le drop
						event.acceptTransferModes(TransferMode.ANY);
					} else if (p.existeTuile(col+1, lig)) {
						
						// On autorise le drop
						event.acceptTransferModes(TransferMode.ANY);						
					} else if (p.existeTuile(col, lig+1)) {
						
						// On autorise le drop
						event.acceptTransferModes(TransferMode.ANY);
					} else if (p.existeTuile(col-1, lig)) {
						
						// On autorise le drop
						event.acceptTransferModes(TransferMode.ANY);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Fonction de detection d'un drag dropped
	@FXML private void dragDropped(DragEvent event) {
		
		// On r�cup�re l'indice de la Tuile jou�e (Chevalet)
		int index = GridPane.getColumnIndex((Node) event.getGestureSource());
		
		// On r�cup�re les coordonnees de jeu de la Tuile (Plateau)
		int col = GridPane.getColumnIndex((Node) event.getSource());
		int lig = GridPane.getRowIndex((Node) event.getSource());
		
		// On affiche le Tuile jou�e (Test Terminal)
		System.out.println(Joueur.getChevalet().getTuile(index) + " Jou�e");
		
		// On ajoute la Tuile jou�e a plateauTuilesTampon
		p.placerTuile(lig, col, Joueur.getChevalet().getTuile(index));
		
		// On copie plateauTuilesTampon dans plateauTuiles
		p.restaurerPlateauTuiles();
		
		// Affichage de l'image de l'ImageView source dans l'ImageView destination
		((ImageView) event.getTarget()).setImage(event.getDragboard().getImage());
		
		// On affiche le Plateau (Test Terminal)
		p.afficher();
		
		// On supprime la Tuile jou�e du Chevalet du Joueur
		Joueur.getChevalet().supprimerTuile(index);
		
		// Affichage du Chevalet du Joueur (Test Terminal)
		System.out.println("\n" + Joueur.getChevalet() + "\n");
	}
	
	// Fonction de detection d'un drag done
	@FXML private void dragDone(DragEvent event) {
		
		// On raffraichit les ImageView du Chevalet
		raffraichissementChevalet();
		
		// On raffraichit les ImageView du Plateau
		raffraichissementPlateau();
	}
	
	// Fonction de raffraichissement des ImageView du Chevalet en fonction du Chevalet du Joueur
	private void raffraichissementChevalet() {
		
		if(!Joueur.getChevalet().estVide()) {
			// Le Chevalet du Joueur est vide
			
			int i; // On met � jour les ImageView du Chevalet en fonction du Chevalet du Joueur
			for(i=0;i<Joueur.getChevalet().getTaille();i++) {
				if(Joueur.getChevalet().getTuile(i).getImg() == null) {
					casesChevalet[i].setImage(null);
				} else {
					casesChevalet[i].setImage(Joueur.getChevalet().getTuile(i).getImg());
				}
			}
			
			// Si le nombre de Tuile pr�sent dans le Chevalet du Joueur est inf�rieur � la taille
			// MAX du Chevalet alors on vide les ImageView restants
			if(i<Chevalet.TAILLE) {
				while (i<Chevalet.TAILLE) {
					casesChevalet[i].setImage(null);
					i++;
				}
			}
		} else {
			// Le Chevalet du Joueur est vide 
			
			// On vide les ImageView du Chevalet
			for(int i=0;i<Chevalet.TAILLE;i++) {
				casesChevalet[i].setImage(null);
			}
		}
	}
	
	// Fonction de raffraichissement des ImageView du Plateau en fonction du tableau plateauTuiles
	private void raffraichissementPlateau() {
		// TO-DO
	}
}
