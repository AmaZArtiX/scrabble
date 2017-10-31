package scrabble;

/*************************************************************************
 * Nom ...........: TuileBis.java
 * Description ...: Classe pr�sentant tout les �l�ments relatifs aux Tuiles
 * ...............:
 * ...............:
 * Auteur(s) .....: MAMADOU BAH
 * Version .......: 1.0
 * Copyright .....: © 2017 MAMADOU BAH ALL RIGHTS RESERVED
 ************************************************************************/

public class TuileBis {
	protected String lettre;
	protected int point;
	protected int nbFois;
	
	
	//Constructeur 
	public TuileBis(String lettre, int point, int nbFois) {
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