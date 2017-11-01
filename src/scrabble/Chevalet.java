// Package
package scrabble;

// Import(s)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*************************************************************************
 * Nom ...........: Chevalet.java
 * Description ...: Ensemble de tuiles dont dispose le joueur durant la 
 * ...............: partie, il en possede 7 constamment
 * ...............:
 * Auteur(s) .....: SIMON BACQUET
 * Version .......: 1.0
 * Copyright .....: © 2017 SIMON BACQUET ALL RIGHTS RESERVED
 ************************************************************************/

public class Chevalet {
	
	// ArrayList contenant les tuiles du chevalet
	private ArrayList<Tuile> chevalet;
	// Taille constante du chevalet tout au long du jeu 
	private final int TAILLE = 7;
	
	// Constructeur sans parametre
	Chevalet(){
		
		this.chevalet = new ArrayList<Tuile>();
	}
	
	// Constructeur avec un chevalet en parametre 
	Chevalet(ArrayList<Tuile> chevalet){
		
		this.chevalet = chevalet;
	}
	
	// Ajouter une tuile en queue de liste 
	public void ajouterTuile(Tuile t) {
		
		if(this.chevalet.size() < TAILLE)
			this.chevalet.add(t);
	}
	
	// Ajoute la tuile t en indice i 
	public void ajouterTuileIndex(Tuile t, int i) {
		
		if(this.chevalet.size() < TAILLE)
			this.chevalet.add(i, t);
	}
	
	// Obtenir la taille de la liste
	public int getTaille() {
		
		return this.chevalet.size();
	}
	
	// Retourne la tuile correspondante a l'indice donne 
	public Tuile getTuile(int i) {
		
		return this.chevalet.get(i);
	}
	
	// Retourne l'indice de la tuile donnee 
	public int getIndex(Tuile t) {
		
		return this.chevalet.indexOf(t);
	}
	
	// Retourne un boolean selon l'etat de la liste (#t = vide)
	public boolean estVide(){
		
		return this.chevalet.isEmpty();
	}
	
	// Supprime toutes les tuiles du chevalet
	public void supprimerTuiles() {
		
		this.chevalet.clear();
	}
	
	// Supprime une truile a l'indice i
	public void supprimerTuile(int i) {
		
		this.chevalet.remove(i);
	}
	
	
	// Melange les tuiles de la liste 
	public void melanger() {
		
		Collections.shuffle(chevalet);
	}
	
	// Renvoie un nombre aléatoire compris entre 0 et la taille courante du sac
	public int getRandom(Sac s) {
		
		Random r = new Random();
		int valeur = 0 + r.nextInt(s.getTaille() - 0);
		
		return valeur;
	}
	
	// Remplit le sac passé en parametre de 7 tuiles
	public void remplir(Sac s) {
		
		for(int i = 1; i <= 7; i++) {
			
			int r = getRandom(s);
			// Mélange du sac pour un tirage correct
			s.melanger();
			// Ajout de la tuile dans le chevalet
			ajouterTuile(s.getTuile(r));
			// Suppression de la tuile dans le sac
			s.supprimerTuile(r);
		}
	}

	@Override
	public String toString() {
		return "Chevalet [chevalet=" + chevalet + "]";
	}
}
