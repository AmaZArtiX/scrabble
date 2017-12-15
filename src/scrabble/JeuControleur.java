// Package
package scrabble;

// Import(s)
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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

	// 
	@FXML private AnchorPane anchorPane;
	
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
	
	@FXML private TableView<Historique> tableHistorique = new TableView<>();
	@FXML private TableColumn<Historique, String> colonneJoueur;
	@FXML private TableColumn<Historique, String> colonneAction;
	@FXML private TableColumn<Historique, String> colonnePoints;
	
	private ObservableList<Historique> historique = FXCollections.observableArrayList();
	
	/**
	 * Raffraichit l'historique
	 */
	public void raffraichirHistorique() {
		
		colonneJoueur.setCellValueFactory(new PropertyValueFactory<Historique, String>("joueur"));
		colonneAction.setCellValueFactory(new PropertyValueFactory<Historique, String>("action"));
		colonnePoints.setCellValueFactory(new PropertyValueFactory<Historique, String>("points"));
		tableHistorique.setItems(historique);
	}
	
	// Fonction d'initialisation de la fenetre de Jeu
	public void initialize() {
		// 
		apparitionScene();
		
		// Initialisation du Plateau de Jeu
		plateau.initialiser();

		if(Joueurs.size() == 1) {
			
			// Affichage du Score du Joueur ainsi que son nom
			lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));
			
			// Affichage du Score de l'IA
			lblScoreJ2.setText(Joueurs.get(1).getNom() + " : " + Integer.toString(Joueurs.get(1).getScore()));

		} else if(Joueurs.size() == 2) {
			
			// Affichage du Score du Joueur ainsi que son nom
			lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));

			// Affichage du Score du Joueur ainsi que son nom
			lblScoreJ2.setText(Joueurs.get(1).getNom() + " : " + Integer.toString(Joueurs.get(1).getScore()));

		}
		
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
		
		// Non possibilité de modification manuelle de l'historique
		tableHistorique.setEditable(false);
	}
	
	// 
	private void apparitionScene() {
		
		// 
		anchorPane.setOpacity(0);
		
		// 
		FadeTransition transition = new FadeTransition();
		transition.setDuration(Duration.millis(500));
		transition.setNode(anchorPane);
		transition.setFromValue(0);
		transition.setToValue(1);
		transition.play();
	}
	
	// Fonction de changement de mode de jeu
	@FXML private void changeModeJeu() {
		
		// On verifie si une partie n'est pas deja en cours
		if(!jeuEnCours & !cbModeJeu.getSelectionModel().isEmpty()) {
			
			// Selon le mode de jeu choisi, on initialise et affiche le chevalet du joueur
			// et on active les boutons
			switch (cbModeJeu.getValue()) {
			case "Entrainement":
				
				// On lance le jeu
				jeuEnCours = true;
				nbTours = 1;
				
				historique.add(new Historique("Tour "+nbTours, "", ""));
				raffraichirHistorique();
				
				if(Joueurs.size() == 1) {
					
					// On active le lbl de Score du Joueur
					lblScoreJ1.setDisable(false);
					
					// On rafraichi le Score du Joueur
					lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));
					
					// On active le lbl de Score du Joueur
					lblScoreJ2.setDisable(false);
					
					// On rafraichi le Score du Joueur
					lblScoreJ2.setText(Joueurs.get(1).getNom() + " : " + Integer.toString(Joueurs.get(1).getScore()));
					
				} else if(Joueurs.size() == 2) {
				
					// On active le lbl de Score du Joueur
					lblScoreJ1.setDisable(false);
					
					// On rafraichi le Score du Joueur
					lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));
					
					// On active le lbl de Score du Joueur
					lblScoreJ2.setDisable(false);
					
					// On rafraichi le Score du Joueur
					lblScoreJ2.setText(Joueurs.get(1).getNom() + " : " + Integer.toString(Joueurs.get(1).getScore()));
					
				}
				
				// 
				joueurEnCours(joueur);
				
				// On active le lbl de Nb de Tour
				lblNbTour.setDisable(false);
				
				// On rafraichi le compteur de tour
				lblNbTour.setText("Tour : " + nbTours);
				
				// Recuperation du Chevalet du Joueur et remplissage avec 7 Tuiles
				for (Joueur joueur : Joueurs) {
					joueur.getChevalet().remplir(sac);
				}
				
				// On sauvegarde le chevalet du joueur
				for (Joueur joueur : Joueurs) {
					plateau.sauvegarderChevalet(joueur.getChevalet(), joueur.getChevaletTampon());
				}
				
				// On rafraichit les ImageView du Chevalet
				rafraichissementChevalet();
				
				// On active les boutons
				// btnProfesseur.setDisable(false);
				btnAbandon.setDisable(false);
				btnSac.setDisable(false);
				btnMelRec.setDisable(false);
				btnJouer.setDisable(false);
				
				// On rafraichit le nb de tuile affiche sur le sac
				btnSac.setText(Integer.toString(sac.getTaille()));
				break;

			default:
				break;
			}
		}
		
		ArrayList<String> motJouables = ((IA) Joueurs.get(1)).findAnagrams(getChars(plateau.getTuilesDisponibles()));

		System.out.println("Liste des mots jouables :");
		for (String string : motJouables) {
			System.out.println(string);
		}
	}
	
	// Fonction permettant de quitter l'application
	@FXML private void quitter(ActionEvent e) {
		
		// On verifie si une partie est en cours
		if (jeuEnCours) {
			
			// On declare une nouvelle Alert de type CONFIRMATION
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Une partie est cours, voulez-vous vraiment quitter ?");

			// On recupere le booleen resultat de cette Alert
			Optional<ButtonType> resultat = alert.showAndWait();

			// Si la reponse est OK alors on continue
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
	@FXML private void gotoDictionnaire(){
		
		try {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		// 
		nbToursPasses = 0;
		
		// 
		historique.clear();
		raffraichirHistorique();

		// On raffraichi le compteur de tour
		lblNbTour.setText("Tour : " + nbTours);
		
		// 
		joueur = 0;
		
		// 
		lblScoreJ1.setTextFill(Color.BLACK);
		lblScoreJ1.setFont(new Font("Arial", 18));
		lblScoreJ2.setTextFill(Color.BLACK);
		lblScoreJ2.setFont(new Font("Arial", 18));
		
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

			// 
			lblNbTour.setDisable(true);
				
			// On desactive les lbl de Score de Joueur
			lblScoreJ1.setDisable(true);
			
			// On desactive les lbl de Score de Joueur
			lblScoreJ2.setDisable(true);
		} else {

			// On desactive les boutons de jeu
			// btnProfesseur.setDisable(true);
			btnAbandon.setDisable(true);
			btnSac.setDisable(true);
			btnMelRec.setDisable(true);
			btnJouer.setDisable(true);

			// 
			lblNbTour.setDisable(true);
			
			// On desactive les lbl de Score de Joueur
			lblScoreJ1.setDisable(true);
			
			// On desactive les lbl de Score de Joueur
			lblScoreJ2.setDisable(true);
		}

		// On reinitialise le Plateau
		plateau.initialiser();

		// On reinitialise les donnees du Joueur (sauf le nom)
		for (int i=0;i<Joueurs.size();i++) {
		
			Joueurs.get(i).reinitialisation();
		}

		if(Joueurs.size() == 1) {
		
			// On rafraichit le Score du Joueur 1
			lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));
		
			// On rafraichit le Score de l'IA
			lblScoreJ2.setText(Joueurs.get(1).getNom() + " : " + Integer.toString(Joueurs.get(1).getScore()));
		} else if (Joueurs.size() == 2) {
			
			// On rafraichit le Score du Joueur 1
			lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));
			
			// On rafraichit le Score du Joueur 2
			lblScoreJ2.setText(Joueurs.get(1).getNom() + " : " + Integer.toString(Joueurs.get(1).getScore()));
		}
		
		// On reinitialise le Sac
		sac = new Sac();
		
		// On vide le nb de tuile affiche sur le sac
		btnSac.setText("0");

		// On rafraichit les ImageView du Plateau
		rafraichissementPlateau();

		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();

		// On reset le ComboBox de choix de Mode de Jeu		
		cbModeJeu.getSelectionModel().clearSelection();
	}
	
	// Fonction permettant d'acceder a l'echange de tuiles
	@FXML private void gotoEchange(){
		
		try {
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
			stageEchange.initOwner(btnSac.getScene().getWindow());
			stageEchange.initModality(Modality.WINDOW_MODAL);
			stageEchange.setOnHidden(EventHandler -> {
				plateau.restaurerChevalet(Joueurs.get(joueur).getChevalet(), Joueurs.get(joueur).getChevaletTampon());
				rafraichissementChevalet();
				btnJouer.fire();
				
				// On rafraichit le nb de tuile affiche sur le sac
				btnSac.setText(Integer.toString(sac.getTaille()));
			});
			stageEchange.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Fonction de choix d'une tuile pour le placement d'un Joker
	@FXML private void choixTuileJoker(DragEvent event) {
		
		try{
		// Test root cree avec Scene Builder
		Parent root = FXMLLoader.load(getClass().getResource("/scrabble/Joker.fxml"));
		
		// Declaration de la scene
		Scene scene = new Scene(root, 600, 500);
		
		// Changement de la scene d'accueil vers la scene principale
		Stage stageJoker = new Stage();
		stageJoker.setScene(scene);
		stageJoker.getIcons().add(new Image("S.png"));
		stageJoker.setTitle("Choix d'une Tuile - Scrabble");
		stageJoker.setResizable(false);
		stageJoker.initOwner(((ImageView) event.getSource()).getScene().getWindow());
		stageJoker.initModality(Modality.WINDOW_MODAL);
		stageJoker.initStyle(StageStyle.UNDECORATED);
		stageJoker.showAndWait();
		}catch(IOException e){
		
		e.printStackTrace();
		}
	}
	
	// Fonction de melange des Tuiles du Chevalet
	@FXML private void melangeChevalet() {
		
		// Melange des Tuiles du Chevalet du Joueur
		Joueurs.get(joueur).getChevaletTampon().melanger();
		plateau.restaurerChevalet(Joueurs.get(joueur).getChevalet(), Joueurs.get(joueur).getChevaletTampon());
		
		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();
	}
	
	// Fonction de recuperation des tuiles jouees
	private void recupTuilesJouee() {

		// On supprime les Tuiles jouees de plateauTuilesTampon
		plateau.sauvegarderPlateauTuiles();

		// On recupere l'etat du Chevalet au debut du Jeu
		plateau.sauvegarderChevalet(Joueurs.get(joueur).getChevalet(), Joueurs.get(joueur).getChevaletTampon());
		
		// On rafraichit les ImageView du Plateau
		rafraichissementPlateau();
		
		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();
	}
	
	// Fonction de passage de tour de jeu
	@FXML private void passerTourJeu() {

		nbToursPasses++;

		if(nbToursPasses < 6) {
			
			/************************************/
			historique.add(new Historique(Joueurs.get(joueur).getNom(), "Tour passé", "0"));
			raffraichirHistorique();
			/************************************/

			// 
			if(Joueurs.size() == 2) {

				// 
				if(joueur == 0) {
					joueur = 1;
				} else {
					
					// On ajoute 1 tour au compteur
					nbTours++;
					
					// On rafraichi le compteur de tour avec +1 tour
					lblNbTour.setText("Tour : " + nbTours);
					
					historique.add(new Historique("Tour "+nbTours, "", ""));
					raffraichirHistorique();
					
					joueur = 0;
				}

				// 
				joueurEnCours(joueur);
			}

			rafraichissementChevalet();
		} else if (nbToursPasses == 6) {
			
			// arret de la partie
			abandonPartie();
		}
	}
	
	/**
	 * Vérifie le plateau selon le nouveau mot joué, et balaye tous les autres 
	 * mots qui le touchent directement 
	 * @param listeCoordonnees Coordonnees des tuiles jouees  
	 * @return Le bon placement d'un mot
	 */
	public boolean verifierPlateau(ArrayList<Coordonnees> listeCoordonnees) {
		
		// Bon placement d'un mot 
		boolean resultat = true;
		// Liste de tuiles qui forment le mot
		ArrayList<Tuile> liste = new ArrayList<Tuile>();
		// Direction du mot
		String direction = "";
		// Mot en lien avec le mot joue
		String mot;
		
		// Coordonnees de debut et de fin 
		int xDebut = listeCoordonnees.get(0).getX();
		int xFin = listeCoordonnees.get(listeCoordonnees.size()-1).getX();
		int yDebut = listeCoordonnees.get(0).getY();
		int yFin = listeCoordonnees.get(listeCoordonnees.size()-1).getY();
		
		// Parcours de toutes les tuiles qui forment le mot joué
		for(Coordonnees c: listeCoordonnees) {
			
			// Coordonnees de la tuile courante
			int x = c.getX();
			int y = c.getY();
			
			// Tuile sur la premiere ligne
			if(x == 0 && (y > 0 && y < 14)) {
				
				// Possibilités de parcours
				if((xDebut != xFin) && plateau.getTuileTampon(y-1, x) != null)
					direction = "gauche";
				else if((xDebut != xFin) && plateau.getTuileTampon(y+1, x) != null)
					direction = "droite";
				else if((xDebut == xFin) && plateau.getTuileTampon(y, x+1) != null)
					direction = "bas";		
			}
			// Tuile sur la derniere colonne
			else if(y == 14 && (x > 0 && x < 14)) {
				
				// Possibilites de parcours
				if((yDebut != yFin) && plateau.getTuileTampon(y, x-1) != null)
					direction = "haut";
				else if((yDebut != yFin) && plateau.getTuileTampon(y, x+1) != null)
					direction = "bas";
				else if((yDebut == yFin) && plateau.getTuileTampon(y-1, x) != null)
					direction = "gauche";
			}
			// Tuile sur la derniere ligne
			else if(x == 14 && (y > 0 && y < 14)) {
				
				// Possibilites de parcours
				if((xDebut != xFin) && plateau.getTuileTampon(y-1, x) != null)
					direction = "gauche";
				else if((xDebut != xFin) && plateau.getTuileTampon(y+1, x) != null)
					direction = "droite";
				else if((xDebut == xFin) && plateau.getTuileTampon(y, x-1) != null)
					direction = "haut";
				
			}
			// Tuile sur la premiere colonne
			else if(y == 0 && (x > 0 && x < 14)) {
				
				
				if((yDebut != yFin) && plateau.getTuileTampon(y, x+1) != null)
					direction = "bas";
				else if((yDebut != yFin) && plateau.getTuileTampon(y, x-1) != null)
					direction = "haut";
				else if((yDebut == yFin) && plateau.getTuileTampon(y+1, x) != null)
					direction = "droite";
			}
			// Tuile sur la case (0, 0)
			else if(x == 0 && y == 0) {
				
				// Possibilites de parcours
				if((xDebut == xFin) && plateau.getTuileTampon(y, x+1) != null)
					direction = "bas";
				else if((yDebut == yFin) && plateau.getTuileTampon(y+1, x) != null)
					direction = "droite";
			}
			// Tuile sur la case (0, 14)
			else if(x == 0 && y == 14) {
				
				// Possibilites de parcours
				if((xDebut == xFin) && plateau.getTuileTampon(y, x+1) != null)
					direction = "bas";
				else if((yDebut == yFin) && plateau.getTuileTampon(y-1, x) != null)
					direction = "gauche";
			}
			// Tuile sur la case (14, 14)
			else if(x == 14 && y == 14) {
				
				// Possibilites de parcours
				if((xDebut == xFin) && plateau.getTuileTampon(y, x-1) != null)
					direction = "haut";
				else if((yDebut == yFin) && plateau.getTuileTampon(y-1, x) != null)
					direction = "gauche";
			}
			// Tuile sur la case (14, 0)
			else if(x == 14 && y == 0) {
				
				// Possibilites de parcours
				if((xDebut == xFin) && plateau.getTuileTampon(y, x-1) != null)
					direction = "haut";
				else if((yDebut == yFin) && plateau.getTuileTampon(y+1, x) != null)
					direction = "droite";
			}
			// Tuile hors limites du plateau
			else if (x > 0 && x < 14  && y > 0 && y < 14) {
				
				// Coordonnees du mot courant
				ArrayList<Coordonnees> coordonnees = new ArrayList<Coordonnees>();
				
				// Mot horizontal, balayage vertical
				if(xDebut == xFin) {
					
					int xDebutBis = x;
					
					// Recherche de tuiles vosines sur les lignes inferieures a la tuile de depart
					while(x >= 1 && plateau.getTuileTampon(y, x-1) != null) {
						
						// Ajout des coordonnees
						coordonnees.add(new Coordonnees(x, y));
						x--;
					}
					
					if(plateau.getTuileTampon(y, xDebutBis-1) != null || plateau.getTuileTampon(y, xDebutBis+1) != null)
						coordonnees.add(new Coordonnees(x, y));
					
					// Recherche de tuiles voisines sur les lignes superieures a la tuile de depart
					if(xDebutBis < 14) {
						if(plateau.getTuileTampon(y, xDebutBis+1) != null) {
							
							x = xDebutBis+1;
							
							while(x <= 13 && plateau.getTuileTampon(y, x+1) != null) {
								
								// Ajout des coordonnees
								coordonnees.add(new Coordonnees(x, y));
								x++;
							}
							
							if(plateau.getTuileTampon(y, xDebutBis+1) != null)
								coordonnees.add(new Coordonnees(x, y));
						}
					}
					
					// Tri des coordonnees en x pour mettre le mot dans le bon sens
					Collections.sort(coordonnees, new Comparator<Coordonnees>() {

						@Override
						public int compare(Coordonnees c1, Coordonnees c2) {
							return c1.getX() - c2.getX();
						}
					});
					
					if(coordonnees.size() > 1) {

						liste = Joueurs.get(joueur).getTuilesJouees(plateau, coordonnees);
						mot = plateau.creerMot(liste);
						System.out.println("Je balaye les mots verticaux "+mot);
						if(!dictionnaire.existe(mot))
							resultat = false; break;
					}
				}
				// Mot vertical, balayage horizontal
				else if(yDebut == yFin) {
					
					int yDebutBis = y;
					
					// Recherche de tuiles vosines sur les colonnes inferieures a la tuile de depart
					while(y >= 1 && plateau.getTuileTampon(y-1, x) != null) {
						
						// Ajout des coordonnees
						coordonnees.add(new Coordonnees(x, y));
						y--;
					}
					
					if(plateau.getTuileTampon(yDebutBis-1, x) != null || plateau.getTuileTampon(yDebutBis+1, x) != null)
						coordonnees.add(new Coordonnees(x, y));
					
					// Recherche de tuiles vosines sur les colonnes superieures a la tuile de depart
					if(yDebutBis < 14) {
						if(plateau.getTuileTampon(yDebutBis+1, x) != null) {
							
							y = yDebutBis+1;
							
							// Recherche d'une tuile vosine sur les colonnes superieures a la tuile de depart
							while(y <= 13 && plateau.getTuileTampon(y+1, x) != null) {
								
								// Ajout des coordonnees
								coordonnees.add(new Coordonnees(x, y));
								y++;
							}
							
							if(plateau.getTuileTampon(yDebutBis+1, x) != null)
								coordonnees.add(new Coordonnees(x, y));
						}
					}
					
					// Tri des coordonnees en y pour mettre le mot dans le bon sens
					Collections.sort(coordonnees, new Comparator<Coordonnees>() {

						@Override
						public int compare(Coordonnees c1, Coordonnees c2) {
							return c1.getY() - c2.getY();
						}
					});
					
					if(coordonnees.size() > 1) {

						liste = Joueurs.get(joueur).getTuilesJouees(plateau, coordonnees);
						mot = plateau.creerMot(liste);
						System.out.println("Je balaye les mots horizontaux "+mot);
						if(!dictionnaire.existe(mot))
							resultat = false; break;
					}
				}
			}
			
			// Recherche des coordonnees de gauche à droite
			if(direction.equals("droite")) {
			
				// Vérification de l'existence d'une tuile voisine
				while(y <= 13 && plateau.getTuileTampon(y+1, x) != null) {
					
					// Ajout des coordonnes
					liste.add(plateau.getTuileTampon(y, x));
					y++;
				}
				
				liste.add(plateau.getTuileTampon(y, x));
				
				mot = plateau.creerMot(liste);
				System.out.println("Je verifie à droite "+mot);
				if(!dictionnaire.existe(mot))
					resultat = false; break;		
			}
			// Recherche des coordonnees de droite à gauche
			else if(direction.equals("gauche")) {
								
				// Vérification de l'existence d'une tuile voisine
				while(y >= 1 && plateau.getTuileTampon(y-1, x) != null) {
					
					// Ajout des coordonnees
					liste.add(plateau.getTuileTampon(y, x));
					y--;
				}
				
				liste.add(plateau.getTuileTampon(y, x));
				
				// Inversion des coordonnees, pour former le mot dans le bon sens
				Collections.reverse(liste);
				
				mot = plateau.creerMot(liste);
				System.out.println("Je verifie à gauche "+mot);
				if(!dictionnaire.existe(mot))
					resultat = false; break;	
			}
			// Recherche des coordonnees de haut en bas 
			else if(direction.equals("bas")) {
				
				// Vérification de l'existence d'une tuile voisine
				while(x <= 13 && plateau.getTuileTampon(y, x+1) != null) {
					
					// Ajout des coordonnees
					liste.add(plateau.getTuileTampon(y, x));
					x++;
				}
				
				liste.add(plateau.getTuileTampon(y, x));
				
				mot = plateau.creerMot(liste);
				System.out.println("Je verifie en bas "+mot);
				if(!dictionnaire.existe(mot))
					resultat = false; break;	
			}
			// Recherche des coordonnees de bas en haut
			else if(direction.equals("haut")) {
								
				// Vérification de l'existence d'une tuile voisine
				while(x >= 1 && plateau.getTuileTampon(y, x-1) != null){
					
					// Ajout des coordonnees
					liste.add(plateau.getTuileTampon(y, x));
					x--;
				}
				
				liste.add(plateau.getTuileTampon(y, x));
				
				// Inversion des coordonnees, pour former le mot dans le bon sens
				Collections.reverse(liste);
				
				mot = plateau.creerMot(liste);
				System.out.println("Je verifie en haut "+mot);
				if(!dictionnaire.existe(mot))
					resultat = false; break;	
			}
		}
		
		return resultat;
	}
	
	// Fonction de fin de tour de jeu
	@FXML private void finTourJeu() {
		
		// On verifie le bon placement des tuiles placees
		if(Joueurs.get(joueur).verifierMotJoue(plateau)) {
			
			System.out.println("Les tuiles sont bien placees !");

			// On récupère toutes les coordonnees des tuiles qui forment le mot 
			ArrayList <Coordonnees> listeCoordonnees = Joueurs.get(joueur).getMotJoueComplet(plateau);
			// On récupere toutes les tuiles qui forment le mot
			ArrayList<Tuile> liste = Joueurs.get(joueur).getTuilesJouees(plateau, listeCoordonnees);
			// On initialise le bonus scrabble à faux
			boolean scrabble = false;
			// Mot formé
			String mot = plateau.creerMot(liste);

			// On verifie que le mot joue existe dans le dico
			if(dictionnaire.existe(mot.toUpperCase()) && verifierPlateau(listeCoordonnees)) {
				
				System.out.println("Le mot existe !");

				// Si le joueur à placer toutes ses tuiles, on lui attribue le bonus scrabble
				if(Joueurs.get(joueur).getChevaletTampon().getTaille() == 0)
					scrabble = true;
				
				// On récupère le score du mot joué
				int score = plateau.calculScoreMot(listeCoordonnees, scrabble);
				Joueurs.get(joueur).setScore(Joueurs.get(joueur).getScore() + score);
				// Affichage en console du mot joué et du score obtenu
				System.out.println("Mot joué : "+mot);
				System.out.println("Score du mot joué : "+ score);
				
				/************************************/
				historique.add(new Historique(Joueurs.get(joueur).getNom(), mot, String.valueOf(score)));
				raffraichirHistorique();
				/************************************/
				

				// On efface le mot joue 
				Joueurs.get(joueur).effacerMotJoue();
				
				if(Joueurs.size() == 1) {
					
					System.out.println("Il n'y a qu'un seul joueur");

					// On rafraichi le Score du Joueur
					lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));

					// 

				} else if(Joueurs.size() == 2) {
					
					System.out.println("Il y a deux joueurs");

					// On rafraichi le Score du Joueur
					lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));

					// On rafraichi le Score du Joueur
					lblScoreJ2.setText(Joueurs.get(1).getNom() + " : " + Integer.toString(Joueurs.get(1).getScore()));
				}
				
				// On applique les ajouts de tuiles au Plateau
				plateau.restaurerPlateauTuiles();
				
				// 
				if(sac.estVide() & Joueurs.get(joueur).getChevaletTampon().estVide()) {
					
					System.out.println("Le sac est vide & le chevalet du joueur est vide");
					
					// 
					if(joueur == 0) {
						
						System.out.println("pour le joueur 0");
						
						// 
						Joueurs.get(0).setScore(Joueurs.get(0).getScore()+scoreChevalet(Joueurs.get(1)));
						
						// 
						Joueurs.get(1).setScore(Joueurs.get(1).getScore()-scoreChevalet(Joueurs.get(1)));
					} else if(joueur == 1) {
						
						System.out.println("pour le joueur 1");
						
						// 
						Joueurs.get(1).setScore(Joueurs.get(1).getScore()+scoreChevalet(Joueurs.get(0)));
						
						// 
						Joueurs.get(0).setScore(Joueurs.get(0).getScore()-scoreChevalet(Joueurs.get(0)));
					}
					
					// On rafraichi le Score du Joueur
					lblScoreJ1.setText(Joueurs.get(0).getNom() + " : " + Integer.toString(Joueurs.get(0).getScore()));

					// On rafraichi le Score du Joueur
					lblScoreJ2.setText(Joueurs.get(1).getNom() + " : " + Integer.toString(Joueurs.get(1).getScore()));

					// 
					//abandonPartie();
				} else if(Joueurs.get(joueur).getChevaletTampon().getTaille() >= 0) {
					
					System.out.println("Le chevalet du joueur n'est pas vide");
					
					// On remplit le Chevalet du Joueur
					Joueurs.get(joueur).getChevaletTampon().reRemplir(sac);

					// On rafraichit le nb de tuile affiche sur le sac
					btnSac.setText(Integer.toString(sac.getTaille()));

					// On applique les suppressions de tuiles au Chevalet du Joueur
					plateau.restaurerChevalet(Joueurs.get(joueur).getChevalet(), Joueurs.get(joueur).getChevaletTampon());
					
					// 
					nbToursPasses = 0;

					// 
					if(Joueurs.size() == 2) {

						// 
						if(joueur == 0) {
							joueur = 1;
						} else {
							// On ajoute 1 tour au compteur
							nbTours++;
							
							// On rafraichi le compteur de tour
							lblNbTour.setText("Tour : " + nbTours);
							
							historique.add(new Historique("Tour "+nbTours, "", ""));
							raffraichirHistorique();
							
							joueur = 0;
						}

						// 
						joueurEnCours(joueur);
					}
				}
			}
			else
				recupTuilesJouee(); Joueurs.get(joueur).effacerMotJoue();
				
		} else 
			recupTuilesJouee(); Joueurs.get(joueur).effacerMotJoue();


		// On reactive l'acces a l'echange de tuiles
		btnSac.setDisable(false);

		// On change l'image et la fonction du bouton Recuperer pour revenir a Melanger
		((ImageView) btnMelRec.getGraphic()).setImage(new Image("melanger.png"));
		btnMelRec.setOnAction(e -> melangeChevalet());

		// On change l'image et la fonction du bouton Jouer pour revenir a Passer
		((ImageView) btnJouer.getGraphic()).setImage(new Image("passer.png"));
		btnJouer.setOnAction(e -> passerTourJeu());

		// On rafraichit les ImageView du Plateau
		rafraichissementPlateau();

		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();
	}
	
	private char[] getChars(ArrayList<Tuile> tuiles) {
		char[] lettres = new char[tuiles.size()];
		for (int i = 0; i < lettres.length; i++) {
			lettres[i] = tuiles.get(i).getLettre();
		}
		return lettres;
	}
	
	private int scoreChevalet(Joueur joueur) {
		
		// On initialise le score du chevalet
		int score = 0;
		
		// On compose le score du chevalet
		for(Tuile tuile : joueur.getChevaletTampon().getTuiles()) {
			score = score + tuile.getValeur();
		}
		
		// On retourne le score du chevalet
		return score;
	}
	
	private void joueurEnCours(int i) {
		
		// 
		if(i == 0) {
			lblScoreJ1.setTextFill(Color.web("007024"));
			lblScoreJ1.setFont(new Font("Arial", 22));
			lblScoreJ2.setTextFill(Color.web("000000"));
			lblScoreJ2.setFont(new Font("Arial", 18));
		} else if(i == 1) {
			lblScoreJ1.setTextFill(Color.web("000000"));
			lblScoreJ1.setFont(new Font("Arial", 18));
			lblScoreJ2.setTextFill(Color.web("007024"));
			lblScoreJ2.setFont(new Font("Arial", 22));
		}
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
	@FXML private void dragDropped(DragEvent event) throws IOException {
		
		// On recupere l'indice de la Tuile jouee (Chevalet)
		int index = GridPane.getColumnIndex((Node) event.getGestureSource());
		
		// On recupere les coordonnees de jeu de la Tuile (Plateau)
		int col = GridPane.getColumnIndex((Node) event.getSource());
		int lig = GridPane.getRowIndex((Node) event.getSource());
		
		// On affecte le Tuile jouee a tuile
		Tuile tuile = Joueurs.get(joueur).getChevaletTampon().getTuile(index);
		
		// On verifie si la Tuile posee est un Joker
		if (tuile.getLettre() == '*') {
			
			// On affiche la fen�tre de choix de Tuile
			choixTuileJoker(event);
			
			// On affecte la Tuile Joker choisie a tuile
			tuile = joker;
		}
		
		//System.out.println(tuile);
		
		// On ajoute la Tuile jouee a plateauTuilesTampon
		Joueurs.get(joueur).ajouterCoordonnees(plateau.placerTuile(lig, col, tuile));
		
		// On supprime la Tuile jouee du Chevalet Tampon du Joueur
		Joueurs.get(joueur).getChevaletTampon().supprimerTuile(index);
		
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
		
		// On change le nom et la fonction du bouton Passer
		((ImageView) btnJouer.getGraphic()).setImage(new Image("jouer.png"));
		btnJouer.setOnAction(e -> finTourJeu());
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
		if(!Joueurs.get(joueur).getChevaletTampon().estVide()) {
			
			int i; // En fonction de la taille du Chevalet Tampon
			for(i=0;i<Joueurs.get(joueur).getChevaletTampon().getTaille();i++) {
				
				// On met a jour les ImageView du Chevalet
				casesChevalet[i].setImage(Joueurs.get(joueur).getChevaletTampon().getTuile(i).getImg());
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
