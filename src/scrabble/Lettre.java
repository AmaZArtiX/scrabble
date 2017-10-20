/*******************************************************************************
Name................. : sacALettre.java

Description ......... : Classe Lettre avec ses attributs et son constructer

@uthor... ........... : BAH Mamadou

Version...............: 1.0

Date..................: 20/10/2017

*******************************************************************************/

package scrabble;

public class Lettre {
	protected String lettre;
	protected int point;
	protected int nbFois;
	
	
	//Constructeur 
	public Lettre(String lettre, int point, int nbFois) {
		super();
		this.lettre = lettre;
		this.point = point;
		this.nbFois = nbFois;
	}
	
	//getters and setters
	public String getLettre() {
		return lettre;
	}
	public void setLettre(String lettre) {
		this.lettre = lettre;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getNbFois() {
		return nbFois;
	}
	public void setNbFois(int nbFois) {
		this.nbFois = nbFois;
	}
	
}