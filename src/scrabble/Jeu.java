package scrabble;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Jeu {
	
	//Fonction de lecture du dictionnaire
	public static void lectureDico() {
		Path path = Paths.get("files/dictionnaire/dico.txt");
		
		try {
			//Ajout des mots du fichier .txt dans la liste "dico"
			List<String> dico = Files.readAllLines(path);
			
			//Affichage du dictionnaire mot par mot
			for (String mot:dico) {
				System.out.println(mot);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	// Ceci est la fonction principale du jeu
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Fonction de lecture du dictionnaire
		lectureDico();
	}

}
