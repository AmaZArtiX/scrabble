// Package
package scrabble;

// Import(s)
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * ***********************************************************************
 * Nom ...........: EchangeControleur.java
 * Description ...: Controleur de la scene permettant les echanges de
 * ...............: tuiles entre le sac et le chevalet du joueur
 * ...............:
 * Auteur(s) .....: SIMON BACQUET, RONAN LAMPE
 * Version .......: 1.0
 * Copyright .....: © 2017 SIMON BACQUET, RONAN LAMPE ALL RIGHTS RESERVED
 ***********************************************************************
 */
public class EchangeControleur extends Jeu {

	// On declare le Chevalet d'Echange qui nous servira a stocker les Tuiles
	Chevalet echange = new Chevalet();
	
	// Lien entre le fichier FXML et le GridPane de Tuiles
	@FXML private GridPane grilleTuiles;
	
	// Tableau de Label des cases de Tuiles
	private Label[] casesLabel = new Label[27];
	
	// Lien entre le fichier FXML et le GridPane de l'Echange
	@FXML private GridPane grilleEchange;
	
	// Tableau d'ImageView des cases du Chevalet
	private ImageView[] casesEchange = new ImageView[Chevalet.TAILLE];
	
	// Lien entre le fichier FXML et le GridPane du Chevalet
	@FXML private GridPane grilleChevalet;
	
	// Tableau d'ImageView des cases du Chevalet
	private ImageView[] casesChevalet = new ImageView[Chevalet.TAILLE];
	
	// Nom du bouton permettant de fermer la fenetre
	@FXML private Button btnFermeture;
	
	/**
	 * Met la valeur du nombre d'occurences de chaque tuile dans les labels a l'initialisation de la fenetre
	 */
	public void initialize() {
				
		// Initialisation de casesLabel avec les Label de grilleTuiles et affichage du nombre de tuile
		for(int i=0;i<27;i++) {

			// On recupere le nb d'occurence de la Tuile en question
			int nbTuiles = sac.getNombreTuiles(new Tuile(((ImageView) ((HBox) grilleTuiles.getChildren().get(i)).getChildren().get(0)).getImage()));
			
			// On affiche le nb d'occurence dans le Label
			casesLabel[i] = (Label) ((HBox) grilleTuiles.getChildren().get(i)).getChildren().get(1);
			casesLabel[i].setText(String.valueOf(nbTuiles));
			
			// On change la couleur du Label en fonction du nb d'occurence
			if(nbTuiles < 1) casesLabel[i].setTextFill(Color.web("FF0000"));
			else casesLabel[i].setTextFill(Color.web("007024"));
		}
		
		// Initialisation de casesChevalet avec les ImageView de grilleChevalet
		for(int i=0;i<Chevalet.TAILLE;i++) {
			casesChevalet[i] = (ImageView) grilleChevalet.getChildren().get(i);
		}
		
		// Initialisation de casesEchange avec les ImageView de grilleEchange
		for(int i=0;i<Chevalet.TAILLE;i++) {
			casesEchange[i] = (ImageView) grilleEchange.getChildren().get(i);
		}
		
		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();
	}
	
	/**
	 * Ferme la fenetre d'echange
	 */
	@FXML private void fermerEchange(ActionEvent event) {
		
	    // Si le Chevalet d'echange n'est pas vide alors on redonne les tuiles au joueur
	    if(!echange.estVide()) {
	    	for (Tuile tuile : echange.getTuiles()) {
				Joueurs.get(joueur).getChevaletTampon().ajouterTuile(tuile);
			}
	    }
	    
	    // On quitte la fen�tre
	    ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
	}
	
	// Effectue l'echange des tuiles entre le chevalet du joueur et le sac
	@FXML private void effectEchange(ActionEvent event) {
		
		// On ajoute les tuiles a echanger dans le sac
		for (Tuile tuile : echange.getTuiles()) {
			sac.ajoutTuile(tuile);
		}
		
		// On recupere autant de tuiles depuis la sac que le nombre de tuile a echanger
		for (int i = 0; i < echange.getTaille(); i++) {
			Joueurs.get(joueur).getChevaletTampon().ajouterTuile(sac.tirerUneLettre());
		}
		
		// On quitte la fen�tre
		((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
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
		
		// On verifie si le drag contient une Image
		if(event.getDragboard().hasImage()) {

			// On autorise le drop
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	// Fonction de detection d'un drag dropped
	@FXML private void dragDroppedOnChevaletEchange(DragEvent event) {

		// 
		int taille = 0;
		
		// 
		for(int i=0;i<Chevalet.TAILLE;i++) {
			if(casesChevalet[i].getImage() != null) taille++;
		}
		
		// 
		if(taille < sac.getTaille()) {

			// On recupere l'indice de la Tuile a echanger (Chevalet)
			int index = GridPane.getColumnIndex((Node) event.getGestureSource());
			
			// On ajoute la Tuile a echanger dans le Chevalet d'echange
			echange.ajouterTuile(Joueurs.get(joueur).getChevaletTampon().getTuile(index));
			
			// On supprime la Tuile jouee du Chevalet du Joueur
			Joueurs.get(joueur).getChevaletTampon().supprimerTuile(index);
		}
	}
	
	// Fonction de detection d'un drag dropped
	@FXML private void dragDroppedOnChevaletJoueur(DragEvent event) {

		// On recupere l'indice de la Tuile a echanger (Chevalet)
		int index = GridPane.getColumnIndex((Node) event.getGestureSource());

		// On ajoute la Tuile a echanger dans le Chevalet d'echange
		Joueurs.get(joueur).getChevaletTampon().ajouterTuile(echange.getTuile(index));

		// On supprime la Tuile jouee du Chevalet du Joueur
		echange.supprimerTuile(index);
	}
	
	// Fonction de detection d'un drag done
	@FXML private void dragDone(DragEvent event) {

		// On rafraichit les ImageView du Chevalet
		rafraichissementChevalet();

		// On rafraichit les ImageView du Plateau
		rafraichissementEchange();
	}
	
	// Fonction de rafraichissement des ImageView du Chevalet en fonction du Chevalet du Joueur
	private void rafraichissementChevalet() {

		// Le Chevalet du Joueur n'est pas vide
		if(!Joueurs.get(joueur).getChevaletTampon().estVide()) {

			int i; // On met a jour les ImageView du Chevalet en fonction du Chevalet du Joueur
			for(i=0;i<Joueurs.get(joueur).getChevaletTampon().getTaille();i++) {
				if(Joueurs.get(joueur).getChevaletTampon().getTuile(i).getImg() == null) {
					casesChevalet[i].setImage(null);
				} else {
					casesChevalet[i].setImage(Joueurs.get(joueur).getChevaletTampon().getTuile(i).getImg());
				}
			}

			// Si le nombre de Tuile present dans le Chevalet du Joueur est inferieur a la taille
			// MAX du Chevalet alors on vide les ImageView restants
			if(i<Chevalet.TAILLE) {
				while (i<Chevalet.TAILLE) {
					casesChevalet[i].setImage(null);
					i++;
				}
			}
		} else { // Le Chevalet du Joueur est vide

			// On vide les ImageView du Chevalet
			for(int i=0;i<Chevalet.TAILLE;i++) {
				casesChevalet[i].setImage(null);
			}
		}
	}
	
	private void rafraichissementEchange() {
		
		// Le Chevalet d'Echange n'est pas vide
		if(!echange.estVide()) {

			int i; // On met a jour les ImageView du Chevalet en fonction du Chevalet du Joueur
			for(i=0;i<echange.getTaille();i++) {
				if(echange.getTuile(i).getImg() == null) {
					casesEchange[i].setImage(null);
				} else {
					casesEchange[i].setImage(echange.getTuile(i).getImg());
				}
			}

			// Si le nombre de Tuile present dans le Chevalet du Joueur est inferieur a la taille
			// MAX du Chevalet alors on vide les ImageView restants
			if(i<Chevalet.TAILLE) {
				while (i<Chevalet.TAILLE) {
					casesEchange[i].setImage(null);
					i++;
				}
			}
		} else { // Le Chevalet du Joueur est vide

			// On vide les ImageView du Chevalet
			for(int i=0;i<Chevalet.TAILLE;i++) {
				casesEchange[i].setImage(null);
			}
		}
	}
}
