// Package
package scrabble;

// Import(s)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*************************************************************************
 * Nom ...........: Sac.java
 * Description ...: Sac dans lequel les joueurs piochent les tuiles (102 
 * ...............: au total) pour les ajouter a leur chevalet
 * ...............:
 * Auteur(s) .....: MAMADOU BAH
 * Version .......: 1.0
 * Copyright .....: © 2017 MAMADOU BAH ALL RIGHTS RESERVED
 ************************************************************************/

public class Sac {
	
	// Liste de tuiles
	protected ArrayList<Tuile> lettres;
	
	// Constructeur sans parametre
	public Sac() {
		
		lettres = new ArrayList<Tuile>();
		remplissage();
	}
	
	// Constructeur avec une liste de tuiles en entree
	public Sac(ArrayList<Tuile> _lettres) {
		
		lettres = _lettres;
	}
	
	// Creation des tuiles et remplissage du sac
	public void remplissage() {
		
		for(int i = 1; i <= Lettre.A.nombre; i++)
			lettres.add(new Tuile(Lettre.A.lettre, Lettre.A.valeur, Lettre.A.img));
		
		for(int i = 1; i <= Lettre.B.nombre; i++)
			lettres.add(new Tuile(Lettre.B.lettre, Lettre.B.valeur, Lettre.B.img));
		
		for(int i = 1; i <= Lettre.C.nombre; i++)
			lettres.add(new Tuile(Lettre.C.lettre, Lettre.C.valeur, Lettre.C.img));
		
		for(int i = 1; i <= Lettre.D.nombre; i++)
			lettres.add(new Tuile(Lettre.D.lettre, Lettre.D.valeur, Lettre.D.img));
		
		for(int i = 1; i <= Lettre.E.nombre; i++)
			lettres.add(new Tuile(Lettre.E.lettre, Lettre.E.valeur, Lettre.E.img));
		
		for(int i = 1; i <= Lettre.F.nombre; i++)
			lettres.add(new Tuile(Lettre.F.lettre, Lettre.F.valeur, Lettre.F.img));
		
		for(int i = 1; i <= Lettre.G.nombre; i++)
			lettres.add(new Tuile(Lettre.G.lettre, Lettre.G.valeur, Lettre.G.img));
		
		for(int i = 1; i <= Lettre.H.nombre; i++)
			lettres.add(new Tuile(Lettre.H.lettre, Lettre.H.valeur, Lettre.H.img));
		
		for(int i = 1; i <= Lettre.I.nombre; i++)
			lettres.add(new Tuile(Lettre.I.lettre, Lettre.I.valeur, Lettre.I.img));
		
		for(int i = 1; i <= Lettre.J.nombre; i++)
			lettres.add(new Tuile(Lettre.J.lettre, Lettre.J.valeur, Lettre.J.img));
		
		for(int i = 1; i <= Lettre.K.nombre; i++)
			lettres.add(new Tuile(Lettre.K.lettre, Lettre.K.valeur, Lettre.K.img));
		
		for(int i = 1; i <= Lettre.L.nombre; i++)
			lettres.add(new Tuile(Lettre.L.lettre, Lettre.L.valeur, Lettre.L.img));
		
		for(int i = 1; i <= Lettre.M.nombre; i++)
			lettres.add(new Tuile(Lettre.M.lettre, Lettre.M.valeur, Lettre.M.img));
		
		for(int i = 1; i <= Lettre.N.nombre; i++)
			lettres.add(new Tuile(Lettre.N.lettre, Lettre.N.valeur, Lettre.N.img));
		
		for(int i = 1; i <= Lettre.O.nombre; i++)
			lettres.add(new Tuile(Lettre.O.lettre, Lettre.O.valeur, Lettre.O.img));
		
		for(int i = 1; i <= Lettre.P.nombre; i++)
			lettres.add(new Tuile(Lettre.P.lettre, Lettre.P.valeur, Lettre.P.img));
		
		for(int i = 1; i <= Lettre.Q.nombre; i++)
			lettres.add(new Tuile(Lettre.Q.lettre, Lettre.Q.valeur, Lettre.Q.img));
		
		for(int i = 1; i <= Lettre.R.nombre; i++)
			lettres.add(new Tuile(Lettre.R.lettre, Lettre.R.valeur, Lettre.R.img));
		
		for(int i = 1; i <= Lettre.S.nombre; i++)
			lettres.add(new Tuile(Lettre.S.lettre, Lettre.S.valeur, Lettre.S.img));
		
		for(int i = 1; i <= Lettre.T.nombre; i++)
			lettres.add(new Tuile(Lettre.T.lettre, Lettre.T.valeur, Lettre.T.img));
		
		for(int i = 1; i <= Lettre.U.nombre; i++)
			lettres.add(new Tuile(Lettre.U.lettre, Lettre.U.valeur, Lettre.U.img));
		
		for(int i = 1; i <= Lettre.V.nombre; i++)
			lettres.add(new Tuile(Lettre.V.lettre, Lettre.V.valeur, Lettre.V.img));
		
		for(int i = 1; i <= Lettre.W.nombre; i++)
			lettres.add(new Tuile(Lettre.W.lettre, Lettre.W.valeur, Lettre.W.img));
		
		for(int i = 1; i <= Lettre.X.nombre; i++)
			lettres.add(new Tuile(Lettre.X.lettre, Lettre.X.valeur, Lettre.X.img));
		
		for(int i = 1; i <= Lettre.Y.nombre; i++)
			lettres.add(new Tuile(Lettre.Y.lettre, Lettre.Y.valeur, Lettre.Y.img));
		
		for(int i = 1; i <= Lettre.Z.nombre; i++)
			lettres.add(new Tuile(Lettre.Z.lettre, Lettre.Z.valeur, Lettre.Z.img));
		
		for(int i = 1; i <= Lettre.JOKER.nombre; i++)
			lettres.add(new Tuile(Lettre.JOKER.lettre, Lettre.JOKER.valeur, Lettre.JOKER.img));
	}
	
	// Renvoie la tuile stockee a l'indice i
	public Tuile getTuile(int i) {
		
		return lettres.get(i);
	}
	
	// Supprime la tuile stockee a l'indice i
	public void supprimerTuile(int i) {
		
		lettres.remove(i);
	}
	 
	// Melange le contenu du sac 
	public void melanger() {
		
		Collections.shuffle(lettres);
	}
	
	// Verifie si une tuile est dans le sac
	public boolean contientLettre(Tuile t) {
		
		return lettres.contains(t);
	}

	// Retourne la taille du sac
	public int getTaille() {
		
		return lettres.size();
	}
	
	// Retourne true si le sac est vide, false sinon
	public boolean estVide() {
		
		return lettres.isEmpty();
	}
	
	// Affiche le contenu du sac
	public void afficherSac() {
		
		for(Tuile t: lettres)
		{
			System.out.println(t);
		}
	}
	
	// Tire une lettre dans le Sac
	public Tuile tirerUneLettre() {
		int taille = getTaille();
		melanger(); // melange le contenu avant de tirer la lettre
		Random rand = new Random();
		int lettre = rand.nextInt(taille);
		return lettres.remove(lettre);
	}
	
	/**
	 * Renvoie le nombre d'occurences pour une tuiles donnee
	 * @param t Tuile a rechercher
	 * @return compteur Nombre de tuiles trouvees
	 */
	public int getNombreTuiles(Tuile t) {
		
		int compteur = 0;
		
		for(int i = 0; i < lettres.size(); i++) {
			
			if(lettres.get(i).equals(t))
				compteur++;
		}
		
		return compteur;
	}
	
	// Fonction d'ajout d'une tuile au sac
	public void ajoutTuile(Tuile t) {
		lettres.add(t);
	}
}
