/*******************************************************************************
Nom ................. : Dictionnaire.java

Description ......... : Classe pr�sentant tout les �l�ments relatifs au 
						Dictionnaire

Auteur(s) ........... : LAMPE Ronan

Derni�re modification : 13/10/2017

*******************************************************************************/

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
	 * 
	// Fonction de recherche d'un mot dans le dictionnaire
	public boolean existe(String motRech) {
		for (String mot : Dico) {
			if(mot.equals(motRech)) return true;
		}
		return false;
	}
	*/
	
	public boolean existe(String mot) {
		
		boolean trouve;
		int debut;
		int milieu;
		int fin;
		
		trouve = false;
		debut = 0;
		fin = Dico.size();
		
		while(!trouve && ((fin - debut) > 1)) {
			
			milieu = (debut+fin)/2;
			trouve = (Dico.get(milieu).equals(mot));
			
			if(Dico.get(milieu).compareTo(mot) > 0)
				fin = milieu;
			else
				debut = milieu;
		}
		
		return trouve;
	}
	
	

	@Override
	public String toString() {
		return "Dictionnaire [" + Dico + "]";
	}
}
