package scrabble;

/**
 * ***********************************************************************
 * Nom ...........: Coordonees.java
 * Description ...: Contient les coordonnes d'une tuile jouee 
 * ...............:
 * ...............:
 * Auteur(s) .....: SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: © 2017 SIMON BACQUET ALL RIGHTS RESERVED
 ***********************************************************************
 */
public class Coordonnees {
	
	int x;
	int y;
	
	Coordonnees(){
		
		x = 0;
		y = 0;
	}
	
	Coordonnees(int _x, int _y){
		
		x = _x;
		y = _y;
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public String toString() {
		
		return "[Coordonnees : "+ x +", "+ y +"]";
	}
}
