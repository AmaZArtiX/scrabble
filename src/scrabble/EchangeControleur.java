// Package
package scrabble;

// Imports
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * ***********************************************************************
 * Nom ...........: EchangeControleur.java
 * Description ...: Controleur de la scene permettant les echanges de tuiles
 * ...............: entre le sac et le chevalet du joueur
 * ...............:
 * Auteur(s) .....: SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: Â© 2017 SIMON BACQUET ALL RIGHTS RESERVED
 ***********************************************************************
 */
public class EchangeControleur extends Jeu {

	// On declare le Chevalet d'Echange qui nous servira a stocker les Tuiles
	Chevalet echange = new Chevalet();
	
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
	
	// Nom des labels contenant le nombre d'occurences de chaque tuile
	@FXML private Label lblNbA;
	@FXML private Label lblNbB;
	@FXML private Label lblNbC;
	@FXML private Label lblNbD;
	@FXML private Label lblNbE;
	@FXML private Label lblNbF;
	@FXML private Label lblNbG;
	@FXML private Label lblNbH;
	@FXML private Label lblNbI;
	@FXML private Label lblNbJ;
	@FXML private Label lblNbK;
	@FXML private Label lblNbL;
	@FXML private Label lblNbM;
	@FXML private Label lblNbN;
	@FXML private Label lblNbO;
	@FXML private Label lblNbP;
	@FXML private Label lblNbQ;
	@FXML private Label lblNbR;
	@FXML private Label lblNbS;
	@FXML private Label lblNbT;
	@FXML private Label lblNbU;
	@FXML private Label lblNbV;
	@FXML private Label lblNbW;
	@FXML private Label lblNbX;
	@FXML private Label lblNbY;
	@FXML private Label lblNbZ;
	@FXML private Label lblNbJocker;
	
	/**
	 * Met la valeur du nombre d'occurences de chaque tuile dans les labels a l'initialisation de la fenetre
	 */
	public void initialize() {
		
		lblNbA.setText(String.valueOf(sac.getNombreTuiles(new Tuile('A', Lettre.A.valeur))));
		lblNbB.setText(String.valueOf(sac.getNombreTuiles(new Tuile('B', Lettre.B.valeur))));
		lblNbC.setText(String.valueOf(sac.getNombreTuiles(new Tuile('C', Lettre.C.valeur))));
		lblNbD.setText(String.valueOf(sac.getNombreTuiles(new Tuile('D', Lettre.D.valeur))));
		lblNbE.setText(String.valueOf(sac.getNombreTuiles(new Tuile('E', Lettre.E.valeur))));
		lblNbF.setText(String.valueOf(sac.getNombreTuiles(new Tuile('F', Lettre.F.valeur))));
		lblNbG.setText(String.valueOf(sac.getNombreTuiles(new Tuile('G', Lettre.G.valeur))));
		lblNbH.setText(String.valueOf(sac.getNombreTuiles(new Tuile('H', Lettre.H.valeur))));
		lblNbI.setText(String.valueOf(sac.getNombreTuiles(new Tuile('I', Lettre.I.valeur))));
		lblNbJ.setText(String.valueOf(sac.getNombreTuiles(new Tuile('J', Lettre.J.valeur))));
		lblNbK.setText(String.valueOf(sac.getNombreTuiles(new Tuile('K', Lettre.K.valeur))));
		lblNbL.setText(String.valueOf(sac.getNombreTuiles(new Tuile('L', Lettre.L.valeur))));
		lblNbM.setText(String.valueOf(sac.getNombreTuiles(new Tuile('M', Lettre.M.valeur))));
		lblNbN.setText(String.valueOf(sac.getNombreTuiles(new Tuile('N', Lettre.N.valeur))));
		lblNbO.setText(String.valueOf(sac.getNombreTuiles(new Tuile('O', Lettre.O.valeur))));
		lblNbP.setText(String.valueOf(sac.getNombreTuiles(new Tuile('P', Lettre.P.valeur))));
		lblNbQ.setText(String.valueOf(sac.getNombreTuiles(new Tuile('Q', Lettre.Q.valeur))));
		lblNbR.setText(String.valueOf(sac.getNombreTuiles(new Tuile('R', Lettre.R.valeur))));
		lblNbS.setText(String.valueOf(sac.getNombreTuiles(new Tuile('S', Lettre.S.valeur))));
		lblNbT.setText(String.valueOf(sac.getNombreTuiles(new Tuile('T', Lettre.T.valeur))));
		lblNbU.setText(String.valueOf(sac.getNombreTuiles(new Tuile('U', Lettre.U.valeur))));
		lblNbV.setText(String.valueOf(sac.getNombreTuiles(new Tuile('V', Lettre.V.valeur))));
		lblNbW.setText(String.valueOf(sac.getNombreTuiles(new Tuile('W', Lettre.W.valeur))));
		lblNbX.setText(String.valueOf(sac.getNombreTuiles(new Tuile('X', Lettre.X.valeur))));
		lblNbY.setText(String.valueOf(sac.getNombreTuiles(new Tuile('Y', Lettre.Y.valeur))));
		lblNbZ.setText(String.valueOf(sac.getNombreTuiles(new Tuile('Z', Lettre.Z.valeur))));
		lblNbJocker.setText(String.valueOf(sac.getNombreTuiles(new Tuile('*', Lettre.JOCKER.valeur))));
		
		// Initialisation de casesChevalet avec les ImageView de grilleChevalet
		for(int i=0;i<Chevalet.TAILLE;i++) {
			casesChevalet[i] = (ImageView) grilleChevalet.getChildren().get(i);
		}
		
		// Initialisation de casesEchange avec les ImageView de grilleEchange
		for(int i=0;i<Chevalet.TAILLE;i++) {
			casesEchange[i] = (ImageView) grilleEchange.getChildren().get(i);
		}
		
		raffraichissementChevalet();
	}
	
	/**
	 * Ferme la fenetre d'echange
	 */
	@FXML private void fermerEchange(ActionEvent event) {
		
	    // Si le Chevalet d'echange n'est pas vide alors on redonne les tuiles au joueur
	    if(!echange.estVide()) {
	    	for (Tuile tuile : echange.getTuiles()) {
				joueur.getChevalet().ajouterTuile(tuile);
			}
	    }
	    
	    // On quitte la fenêtre
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
			joueur.getChevalet().ajouterTuile(sac.tirerUneLettre());
		}
		
		// On quitte la fenêtre
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

		// On recupere l'indice de la Tuile a echanger (Chevalet)
		int index = GridPane.getColumnIndex((Node) event.getGestureSource());
		
		// On ajoute la Tuile a echanger dans le Chevalet d'echange
		echange.ajouterTuile(joueur.getChevalet().getTuile(index));
		
		// On supprime la Tuile jouee du Chevalet du Joueur
		joueur.getChevalet().supprimerTuile(index);
	}
	
	// Fonction de detection d'un drag dropped
	@FXML private void dragDroppedOnChevaletJoueur(DragEvent event) {

		// On recupere l'indice de la Tuile a echanger (Chevalet)
		int index = GridPane.getColumnIndex((Node) event.getGestureSource());

		// On ajoute la Tuile a echanger dans le Chevalet d'echange
		joueur.getChevalet().ajouterTuile(echange.getTuile(index));

		// On supprime la Tuile jouee du Chevalet du Joueur
		echange.supprimerTuile(index);
	}
	
	// Fonction de detection d'un drag done
	@FXML private void dragDone(DragEvent event) {

		// On raffraichit les ImageView du Chevalet
		raffraichissementChevalet();

		// On raffraichit les ImageView du Plateau
		raffraichissementEchange();
	}
	
	// Fonction de raffraichissement des ImageView du Chevalet en fonction du Chevalet du Joueur
	private void raffraichissementChevalet() {

		// Le Chevalet du Joueur n'est pas vide
		if(!joueur.getChevalet().estVide()) {

			int i; // On met a jour les ImageView du Chevalet en fonction du Chevalet du Joueur
			for(i=0;i<joueur.getChevalet().getTaille();i++) {
				if(joueur.getChevalet().getTuile(i).getImg() == null) {
					casesChevalet[i].setImage(null);
				} else {
					casesChevalet[i].setImage(joueur.getChevalet().getTuile(i).getImg());
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
	
	private void raffraichissementEchange() {
		
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
