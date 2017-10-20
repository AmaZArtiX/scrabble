package scrabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class sacALettres {
	protected List<Lettre> lettres;

	public sacALettres(List<Lettre> lettres) {
		super();
		this.lettres = new ArrayList<>();
	}


// creation des lettres et remplissage du sac à lettre (102 lettres)
	public void remplissageSac() {
        //initialisation des lettres
		Lettre JOKER = new Lettre("*",0,2);
		Lettre A = new Lettre("A",1,9);
		Lettre B = new Lettre("B",3,2);
		Lettre C = new Lettre("C",3,2);
		Lettre D = new Lettre("D",2,3);
		Lettre E = new Lettre("E",1,51);
        Lettre F = new Lettre("F",4,2);
        Lettre G = new Lettre("G",2,2);
        Lettre H = new Lettre("H",4,2);
		Lettre I = new Lettre("I",1,8);
		Lettre J = new Lettre("J",8,1);
		Lettre K = new Lettre("K",10,1);
		Lettre L = new Lettre("L",1,5);
		Lettre M = new Lettre("M",2,3);
		Lettre N = new Lettre("N",1,6);
		Lettre O = new Lettre("O",1,6);
		Lettre P = new Lettre("P",3,2);
		Lettre Q = new Lettre("Q",8,1);
		Lettre R = new Lettre("R",1,6);
		Lettre S = new Lettre("S",1,6);
		Lettre T = new Lettre("T",1,6);
		Lettre U = new Lettre("U",1,6);
		Lettre V = new Lettre("V",4,2);
	    Lettre W = new Lettre("W",10,1);
	    Lettre X = new Lettre("X",10,1);
		Lettre Y = new Lettre("Y",10,1);
		Lettre Z = new Lettre("Z",10,1);
        
		//Remplissage
        for(int i=0; i < JOKER.nbFois; i++){
			lettres.add(JOKER);			
			}
        int i;
        for(i = 0; i < A.nbFois; i++)
        {
        	lettres.add(A);
        }
        
        for(i = 0; i < B.nbFois; i++)
        {
        	lettres.add(B);
        }
        
        for(i = 0; i < C.nbFois; i++)
        {
        	lettres.add(C);
        }
        
        for(i = 0; i < D.nbFois; i++)
        {
        	lettres.add(D);
        }
        
        for(i = 0; i < E.nbFois; i++)
        {
        	lettres.add(E);
        }
        
        for(i = 0; i < F.nbFois; i++)
        {
        	lettres.add(F);
        }
        
        for(i = 0; i < G.nbFois; i++)
        {
        	lettres.add(G);
        }
        
        for(i = 0; i < H.nbFois; i++)
        {
        	lettres.add(H);
        }
        
        for(i = 0; i < I.nbFois; i++)
        {
        	lettres.add(I);
        }
        
        for(i = 0; i < J.nbFois; i++)
        {
        	lettres.add(J);
        }
        
        for(i = 0; i < K.nbFois; i++)
        {
        	lettres.add(K);
        }
        
        for(i = 0; i < L.nbFois; i++)
        {
        	lettres.add(L);
        }
        
        for(i = 0; i < M.nbFois; i++)
        {
        	lettres.add(M);
        }
        
        for(i = 0; i < N.nbFois; i++)
        {
        	lettres.add(N);
        }
        
        for(i = 0; i < O.nbFois; i++)
        {
        	lettres.add(O);
        }
        
        for(i = 0; i < P.nbFois; i++)
        {
        	lettres.add(P);
        }
        
        for(i = 0; i < Q.nbFois; i++)
        {
        	lettres.add(Q);
        }
        
        for(i = 0; i < R.nbFois; i++)
        {
        	lettres.add(R);
        }
        
        for(i = 0; i < S.nbFois; i++)
        {
        	lettres.add(S);
        }
        
        for(i = 0; i < T.nbFois; i++)
        {
        	lettres.add(T);
        }
        
        for(i = 0; i < U.nbFois; i++)
        {
        	lettres.add(U);
        }
        
        for(i = 0; i < V.nbFois; i++)
        {
        	lettres.add(V);
        }
        
        for(i = 0; i < W.nbFois; i++)
        {
        	lettres.add(W);
        }
        
        for(i = 0; i < X.nbFois; i++)
        {
        	lettres.add(X);
        }
        
        for(i = 0; i < Y.nbFois; i++)
        {
        	lettres.add(Y);
        }
        
        for(i = 0; i < Z.nbFois; i++)
        {
        	lettres.add(Z);
        }
    }
	
	//Melange le contenu du sac pour bien piocher apres
	public void melangerSac() {
		Collections.shuffle(lettres);
	}
	
	//verification si une lettre est dans le sac
	public boolean contientLettre(Lettre lettre) {
		return lettres.contains(lettre);
	}

	//retourne le nombre de lettre dans le sac (taille du sac)
	public int nombreDeJetons() {
		return lettres.size();
	}
	
	//retourne true si le sac est vide, false sinon
	public boolean estVide() {
		if(lettres == null) return true;
		return false;
	}
	
	// Afficher le contenu du sac
	public void afficherSac() {
		for(Lettre L: lettres)
		{
			System.out.println(L.lettre);
		}
	}
	
	//tirer une lettre dans le sacAlettre
	public Lettre tirerUneLettre() {
		int taille = nombreDeJetons();
		melangerSac(); // melange le contenu avant de tirer la lettre
		Random rand = new Random();
		int lettre = rand.nextInt(taille) + 1;
		return lettres.remove(lettre);
	}
	
}
