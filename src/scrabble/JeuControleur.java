// Package
package scrabble;

// Import(s)
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
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
 * Copyright .....: © 2017 RONAN LAMPE ALL RIGHTS RESERVED
 ************************************************************************/

public class JeuControleur extends Jeu {

	// Lien entre le fichier FXML et le ComboBox de Mode de Jeu
	@FXML private ComboBox<String> cbModeJeu;
	
	// Lien entre le fichier FXML et le Button Dictionnaire
	@FXML private Button btnDico;
	
	// Lien entre le fichier FXML et le Label de Score du Joueur 1
	@FXML private Label lblScoreJ1;
	
	// Lien entre le fichier FXML et le Label de Score du Joueur 2
	@FXML private Label lblScoreJ2;
	
	// Lien entre le fichier FXML et le Label de Nb de Tour
	@FXML private Label lblNbTour;
	
	// Lien entre le fichier FXML et le Button Professeur
	@FXML private Button btnProfesseur;
	
	// Lien entre le fichier FXML et le GridPane du Plateau
	@FXML private GridPane grillePlateau;
	
	// Tableau d'ImageView des cases du Plateau
	private ImageView[] casesPlateau = new ImageView[Plateau.TAILLE*Plateau.TAILLE];
	
	// Lien entre le fichier FXML et le Button Abandon
	@FXML private Button btnAbandon;
	
	// Lien entre le fichier FXML et le Button Sac
	@FXML private Button btnSac;

	// Lien entre le fichier FXML et le GridPane du Chevalet
	@FXML private GridPane grilleChevalet;
	
	// Tableau d'ImageView des cases du Chevalet
	private ImageView[] casesChevalet = new ImageView[Chevalet.TAILLE];
	
	// Lien entre le fichier FXML et le Button Melanger/Recuperer
	@FXML private Button btnMelRec;
	
	// Lien entre le fichier FXML et le Button Jouer
	@FXML private Button btnJouer;
	
	// Fonction d'initialisation de la fenetre de Jeu
	public void initialize() {

		// Initialisation du Plateau de Jeu
		plateau.initialiser();

		// Affichage du Score du Joueur ainsi que son nom
		lblScoreJ1.setText(joueur.getNom() + " : " + Integer.toString(joueur.getScore()));

		// Initialisation de casesPlateau avec les ImageView de grillePlateau
		for(int i=0;i<(Plateau.TAILLE*Plateau.TAILLE);i++) {
			casesPlateau[i] = (ImageView) grillePlateau.getChildren().get(i);
		}

		// Initialisation de casesChevalet avec les ImageView de grilleChevalet
		for(int i=0;i<Chevalet.TAILLE;i++) {
			casesChevalet[i] = (ImageView) grilleChevalet.getChildren().get(i);
		}

		// On rafraichit les ImageView du Plateau
		rafraichissementPlateau();
	}
	
	// Fonction de changement de mode de jeu
	@FXML private void changeModeJeu() {
		
		// On verifie si une partie n'est pas deja en cours
		if(!jeuEnCours) {
			
			// Selon le mode de jeu choisi, on initialise et affiche le chevalet du joueur
			// et on active les boutons
			switch (cbModeJeu.getValue()) {
			case "Entrainement":
				
				// On lance le jeu
				jeuEnCours = true;
				nbTours = 1;
				
				// On active le lbl de Score du Joueur
				lblScoreJ1.setDisable(false);
				
				// On rafraichi le Score du Joueur
				lblScoreJ1.setText(joueur.getNom() + " : " + Integer.toString(joueur.getScore()));
				
				// On active le lbl de Nb de Tour
				lblNbTour.setDisable(false);
				
				// On rafraichi le compteur de tour
				lblNbTour.setText("Tour : " + nbTours);
				
				// Recuperation du Chevalet du Joueur et remplissage avec 7 Tuiles
				joueur.getChevalet().remplir(sac);
				
				// On sauvegarde le chevalet du joueur
				plateau.sauvegarderChevalet(joueur.getChevalet(), joueur.getChevaletTampon());
				
				// On rafraichit les ImageView du Chevalet
				rafraichissementChevalet();
				
				// On active les boutons
				// btnProfesseur.setDisable(false);
				btnAbandon.setDisable(false);
				btnSac.setDisable(false);
				btnMelRec.setDisable(false);
				btnJouer.setDisable(false);
				break;

			default:
				break;
			}
		}
	}
	
	// Fonction permettant de quitter l'application
	@FXML private void quitter(ActionEvent e) {
		
		if (jeuEnCours) {
			
			// 
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Une partie est cours, voulez-vous vraiment quitter ?");

			// 
			Optional<ButtonType> resultat = alert.showAndWait();

			if(resultat.get() == ButtonType.OK) {
				
				// On abandonne la partie
				abandonPartie();
				
				// On quitte le jeu
				System.exit(0);
			}
		} else {
			
			// On quitte le jeu
			System.exit(0);
		}
	}
	
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
	
	// Fonction d'aide au placement de tuile
	@FXML private void aideProfesseur() {
		// TODO
	}
	
	// Fonction permettant d'abandonner la partie en cours
	@FXML private void abandonPartie() {
	
		// On reinitialise l'etat de la partie et le nb de tours
		jeuEnCours = false;
		nbTours = 0;

		// On raffraichi le compteur de tour
		lblNbTour.setText("Tour : " + nbTours);

		// Si le bouton Sac est desactive alors des tuiles ont ete jouees et le bouton Melanger a ete modifie
		if(btnSac.isDisable()) {

			// On change le nom et la fonction du bouton Recuperer pour revenir a Melanger
			((ImageView) btnMelRec.getGraphic()).setImage(new Image("melanger.png"));
			btnMelRec.setOnAction(e -> melangeChevalet());

			// On desactive les boutons de jeu
			// btnProfesseur.setDisable(true);
			btnAbandon.setDisable(true);
			btnMelRec.setDisable(true);
			btnJouer.setDisable(true);

			// On desactive les lbl de Score de Joueur et Nb de Tour
			lblScoreJ1.setDisable(true);
			lblNbTour.setDisable(true);
		} else {

			// On desactive les boutons de jeu
			// btnProfesseur.setDisable(true);
			btnAbandon.setDisable(true);
			btnSac.setDisable(true);
			btnMelRec.setDisable(true);
			btnJouer.setDisable(true);

			// On desactive les lbl de Score de Joueur et Nb de Tour
			lblScoreJ1.setDisable(true);
			lblNbTour.setDisable(true);
		}

		// On reinitialise le Plateau
		plateau.initialiser();

		// On reinitialise les donnees du Joueur (sauf le nom)
		joueur = new Joueur(joueur.getNom());

		// On rafraichit le Score du Joueur
		lblScoreJ1.setText(joueur.getNom() + " : " + Integer.toString(joueur.getScore()));

		// On reinitialise le Sac
		sac = new Sac();

		// On rafraichit les ImageView du Plateau
		rafraichissementPlateau();

		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();

		// On reset le ComboBox de choix de Mode de Jeu
		((ComboBoxBase<String>) cbModeJeu).setValue(cbModeJeu.getPromptText());
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
		stageEchange.setTitle("Echanger des lettres - Scrabble");
		stageEchange.setResizable(false);
		stageEchange.initOwner(((Button) e.getSource()).getScene().getWindow());
		stageEchange.initModality(Modality.WINDOW_MODAL);
		stageEchange.setOnHidden(EventHandler -> {
			plateau.restaurerChevalet(joueur.getChevalet(), joueur.getChevaletTampon());
			rafraichissementChevalet();
			finTourJeu();
		});
		stageEchange.show();
	}
	
	// Fonction de melange des Tuiles du Chevalet
	@FXML private void melangeChevalet() {
		
		// Melange des Tuiles du Chevalet du Joueur
		joueur.getChevaletTampon().melanger();
		plateau.restaurerChevalet(joueur.getChevalet(), joueur.getChevaletTampon());
		
		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();
	}
	
	// Fonction de recuperation des tuiles jouees
	private void recupTuilesJouee() {

		// On supprime les Tuiles jouees de plateauTuilesTampon
		plateau.sauvegarderPlateauTuiles();

		// On recupere l'etat du Chevalet au debut du Jeu
		plateau.sauvegarderChevalet(joueur.getChevalet(), joueur.getChevaletTampon());
		
		// On rafraichit les ImageView du Plateau
		rafraichissementPlateau();
		
		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();
	}
	
	// Fonction de fin de tour de jeu
	@FXML private void finTourJeu() {
		
		// On calcul le score du mot joue par le Joueur
		// ------------------------------------------------
		// |                    TODO                      |
		// ------------------------------------------------
		// joueur.setScore(joueur.getScore() + plateau.calculScoreMot(listeTuiles, listeBonus));
		// 
		
		/**************************************************/
		//System.out.println(plateau.checkBoard());
		System.out.println(joueur.getMotJoue());
		System.out.println(joueur.verifierMotJoue(plateau));
		joueur.effacerMotJoue();
		/**************************************************/
		
		// On rafraichit le Score du Joueur
		lblScoreJ1.setText(joueur.getNom() + " : " + Integer.toString(joueur.getScore()));
		
		// On applique les ajouts de tuiles au Plateau
		plateau.restaurerPlateauTuiles();
		
		// On remplit le Chevalet du Joueur
		joueur.getChevaletTampon().reRemplir(sac);
		
		// On applique les suppressions de tuiles au Chevalet du Joueur
		plateau.restaurerChevalet(joueur.getChevalet(), joueur.getChevaletTampon());
		
		// On ajoute 1 tour au compteur
		nbTours++;
		
		// On rafraichi le compteur de tour avec +1 tour
		lblNbTour.setText("Tour : " + nbTours);
		
		// On reactive l'acces a l'echange de tuiles
		btnSac.setDisable(false);
		
		// On change l'image et la fonction du bouton Recuperer pour revenir a Melanger
		((ImageView) btnMelRec.getGraphic()).setImage(new Image("melanger.png"));
		btnMelRec.setOnAction(e -> melangeChevalet());
		
		// On rafraichit les ImageView du Plateau
		rafraichissementPlateau();
		
		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();
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
		joueur.ajouterCoordonnees(plateau.placerTuile(lig, col, joueur.getChevaletTampon().getTuile(index)));
		
		// On supprime la Tuile jouee du Chevalet Tampon du Joueur
		joueur.getChevaletTampon().supprimerTuile(index);
		
		// On desactive l'acces a l'echange de tuiles
		btnSac.setDisable(true);
		
		// On change le nom et la fonction du bouton Melanger
		((ImageView) btnMelRec.getGraphic()).setImage(new Image("recuperer.png"));
		btnMelRec.setOnAction(EventHandler -> {
			
			// On recupere les tuiles jouees
			recupTuilesJouee();		
			
			// On reactive l'acces a l'echange de tuiles
			btnSac.setDisable(false);
			
			// On change l'image et la fonction du bouton Recuperer pour revenir a Melanger
			((ImageView) btnMelRec.getGraphic()).setImage(new Image("melanger.png"));
			btnMelRec.setOnAction(e -> melangeChevalet());
		});
	}
	
	// Fonction de detection d'un drag done
	@FXML private void dragDone(DragEvent event) {
		
		// On rafraichit les ImageView du Plateau
		rafraichissementPlateau();
		
		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();
	}
	
	// Fonction de raffraichissement des ImageView du Chevalet en fonction du Chevalet du Joueur
	private void rafraichissementChevalet() {
		
		// Le Chevalet du Joueur n'est pas vide
		if(!joueur.getChevaletTampon().estVide()) {
			
			int i; // En fonction de la taille du Chevalet Tampon
			for(i=0;i<joueur.getChevaletTampon().getTaille();i++) {
				
				// On met a jour les ImageView du Chevalet
				casesChevalet[i].setImage(joueur.getChevaletTampon().getTuile(i).getImg());
			}
			
			// Si le nombre de Tuile present dans le Chevalet du Joueur est inferieur a la taille
			// MAX du Chevalet alors on vide les ImageView restants
			if(i<Chevalet.TAILLE) {
				
				while (i<Chevalet.TAILLE) {
					
					casesChevalet[i].setImage(null); i++;
				}
			}
		} else { // Le Chevalet du Joueur est vide
			
			// On vide les ImageView du Chevalet
			for(int i=0;i<Chevalet.TAILLE;i++) {
				
				casesChevalet[i].setImage(null);
			}
		}
	}
	
	// Fonction de raffraichissement des ImageView du Plateau en fonction du tableau plateauTuiles
	private void rafraichissementPlateau() {
		
		// Le tableau plateauTuiles n'est pas vide
		if(!plateau.getPlateauTuilesTampon().equals(null)) {
			
			// On parcours toutes les lignes
			for(int i=0;i<Plateau.TAILLE;i++) {
				
				// On parcours toutes les colonnes
				for(int j=0;j<Plateau.TAILLE;j++) {
					
					// Si l'emplacement indique contient une tuile, un bonus ou rien alors on affiche l'image appropriee
					if(plateau.getTuileTampon(i, j) instanceof Tuile) {

						// On affiche son Image dans l'ImageView du Plateau
						casesPlateau[j*Plateau.TAILLE+i].setImage(plateau.getTuileTampon(i, j).getImg());
					} else if(plateau.getStringBonus(i, j) != "  ") {

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
					} else {
						
						// La case est vide donc on met l'Image de l'ImageView a vide
						casesPlateau[j*Plateau.TAILLE+i].setImage(new Image("CV.png"));
					}
				}
			}
		}
	}
}
