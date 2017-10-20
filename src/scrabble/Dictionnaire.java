/*******************************************************************************
Nom ................. : Dictionnaire.java

Description ......... : Classe présentant tout les éléments relatifs au 
						Dictionnaire

Auteur(s) ........... : LAMPE Ronan

Dernière modification : 13/10/2017

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
		Path path = Paths.get("files/dictionnaire/dico.txt");
		
		try {
			// Ajout des mots du fichier .txt dans la liste "dico"
			Dico = Files.readAllLines(path, StandardCharsets.ISO_8859_1);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// Fonction de recherche d'un mot dans le dictionnaire
	public boolean existe(String motRech) {
		for (String mot : Dico) {
			if(mot.equals(motRech)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Dictionnaire [" + Dico + "]";
	}
}
