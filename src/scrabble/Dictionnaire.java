// Package
package scrabble;

// Import(s)
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * Nom ...........: Dictionnaire.java
 * Description ...: Classe presentant tout les elements relatifs au 
 * ...............: Dictionnaire
 * ...............:
 * Auteur(s) .....: RONAN LAMPE, SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: © 2017 RONAN LAMPE, SIMON BACQUET ALL RIGHTS RESERVED
 ************************************************************************/

public class Dictionnaire {

	// Dictionnaire vide
	private List<String> Dico = new ArrayList<String>();
	
	// Fonction "set" du dictionnaire
	public void setDico(List<String> dico) {
		Dico = dico;
	}
	
	// Fonction "get" du dictionnaire
	public List<String> getDico() {
		return Dico;
	}
	
	// Fonction d'initialisation du dictionnaire
	public void initDico() {
		Path path = Paths.get("dictionnaire/dico.txt");
		
		try {
			// Ajout des mots du fichier .txt dans la liste "dico"
			Dico = Files.readAllLines(path, StandardCharsets.ISO_8859_1);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/*
	// Fonction de recherche d'un mot dans le dictionnaire
	public boolean existe(String motRech) {
		for (String mot : Dico) {
			if(mot.equals(motRech)) return true;
		}
		return false;
	}
	*/
	
	/*
	 * Verifier l'existence d'un mot dans le dictionnaire (recherche dichotomique)
	 * @param mot Le mot a rechercher
	 * @return trouve Le booleen utile a la recherhce 
	 */
	public boolean existe(String mot) {
		
		// Booleen de recherche
		boolean trouve;
		// Indice de debut de liste 
		int debut;
		// Indice de milieu de liste 
		int milieu;
		// Indice de fin de liste 
		int fin;
		
		// Initialisation du booleen 
		trouve = false;
		// Debut de liste a l'indice 0
		debut = 0;
		// Fin de liste a l'indice nombre de valeurs 
		fin = Dico.size();
		
		while(!trouve && ((fin - debut) > 1)) {
			
			// Indice de mileu de liste 
			milieu = (debut+fin)/2;
			// Mot recherche a l'indice courant
			trouve = (Dico.get(milieu).equals(mot));
			
			// Si mot courant est superieur au mot donne 
			if(Dico.get(milieu).compareTo(mot) > 0)
				fin = milieu;
			else
				debut = milieu;
		}
		
		// Renvoie du booleen
		return trouve;
	}
	
	@Override
	public String toString() {
		return "Dictionnaire [" + Dico + "]";
	}
}
