package scrabble;

import javafx.beans.property.SimpleStringProperty;
/**
 * ***********************************************************************
 * Nom ...........: Historique.java
 * Description ...: Permet de sauvegarder à chaque tour, le nom du joueur, 
 * ...............: l'action réalisée et les points marqués
 * ...............:
 * Auteur(s) .....: SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: © 2017 SIMON BACQUET ALL RIGHTS RESERVED
 ***********************************************************************
 */
public class Historique {
	
	private SimpleStringProperty joueur;
	private SimpleStringProperty action;
	private SimpleStringProperty points;
	
	Historique(String _joueur, String _action, String _points){
		
		joueur = new SimpleStringProperty(_joueur);
		action = new SimpleStringProperty(_action);
		points = new SimpleStringProperty(_points);
	}
	
	public String getJoueur() {
		
		return joueur.get();
	}
	
	public String getAction() {
		
		return action.get();
	}
	
	public String getPoints() {
		
		return points.get();
	}
}
