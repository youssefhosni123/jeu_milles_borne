package Jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private List<Carte> PileLimiteVitesse;
	private List<Carte> PileDeBataille;
	private Set<Carte>collectiondeBornes;
	
	public ZoneDeJeu() {
		this.PileLimiteVitesse=new ArrayList<>();
		this.PileDeBataille=new ArrayList<>();
		this.collectiondeBornes= new HashSet<>();
		
	}
	public void AjouterLimiteVitesse (Carte carte) {
		PileLimiteVitesse.add(carte);
		}
	public void AjouterBataille(Carte carte ) {
		PileDeBataille.add( carte);
		
	}
	public void AjouterBorne(Carte carte ) {
		collectiondeBornes.add(carte);
	}
	 public int donnerLimitationVitesse() {
	        if (PileLimiteVitesse.isEmpty()) {
	            return 200; // Pas de limitation de vitesse
	        }

	        // Vérifie le sommet de la pile de limites de vitesse
	        Carte sommet = PileLimiteVitesse.get(PileLimiteVitesse.size() - 1);
	        if (sommet instanceof FinLimite) {
	            return 200; // Fin de limitation, donc limite de vitesse à 200
	        } else {
	            return 50; // Limite de vitesse en cours à 50
	        }
	    }
	 public int donnerKmParcourus() {
	        int totalKm = 0;
	        for (Carte carte : collectiondeBornes) {
	            if (carte instanceof Borne) {
	                Borne borne = (Borne) carte;
	                totalKm += borne.getValeur();
	            }
	        }
	        return totalKm;
	    }
	 public void deposer(Carte carte) {
	        if (carte instanceof Borne) {
	            collectiondeBornes.add(carte); // Ajoute la carte à la collection des bornes
	        } else if (carte instanceof DebutLimite || carte instanceof FinLimite) {
	            PileLimiteVitesse.add(carte); // Ajoute la carte à la pile des limites de vitesse
	        } else if (carte instanceof Bataille) {
	            PileDeBataille.add(carte); // Ajoute la carte à la pile de bataille
	        }
	    }
	 
	 public boolean peutAvancer() {
		    // Vérifie que la pile de bataille n'est pas vide
		    if (PileDeBataille.isEmpty()) {
		        return false;
		    }

		    // Récupère le sommet de la pile de bataille
		    Carte sommet = PileDeBataille.get(PileDeBataille.size() - 1);

		    // Vérifie si le sommet est un Feu Vert en utilisant la constante de l'interface Cartes
		    if (sommet.equals(Cartes.FEU_VERT)) {
		        return true;
		    }

		    return false;
		}
	  public boolean estDepotFeuVertAutorise() {
	        // Vérifie que la pile de bataille est vide
		  if (PileDeBataille.isEmpty()) {
		        return true;
		    }

		    // Récupère le sommet de la pile de bataille
		    Carte sommet = PileDeBataille.get(PileDeBataille.size() - 1);

		    // Vérifie si le sommet est une Attaque ou une Parade qui n'est pas un Feu Vert
		    if (sommet instanceof Attaque || (sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT))) {
		        return true;
		    }

		    return false;
	    }
	  public boolean estDepotBorneAutorise(Borne borne) {
		    // Vérifier si le joueur est bloqué (pile de bataille non vide et le sommet n'est pas un feu vert)
		    if (!peutAvancer()) {
		        return false;
		    }

		    // Vérifier que la vitesse de la borne ne dépasse pas la limite actuelle
		    if (borne.getValeur() > donnerLimitationVitesse()) {
		        return false;
		    }

		    // Vérifier que la somme des bornes ne dépasse pas 1000 km
		    if (donnerKmParcourus() + borne.getValeur() > 1000) {
		        return false;
		    }

		    return true;
		}
	  public boolean estDepotLimiteAutorise(Limite limite) {
		    // Si c'est un début de limite, on vérifie si la pile est vide ou si son sommet est une fin de limite
		    if (limite instanceof DebutLimite) {
		        return PileLimiteVitesse.isEmpty() || PileLimiteVitesse.get(PileLimiteVitesse.size() - 1) instanceof FinLimite;
		    }
		    
		    // Si c'est une fin de limite, on vérifie que le sommet de la pile est un début de limite
		    if (limite instanceof FinLimite) {
		        return !PileLimiteVitesse.isEmpty() && PileLimiteVitesse.get(PileLimiteVitesse.size() - 1) instanceof DebutLimite;
		    }

		    return false;
		}
	  public boolean estDepotBatailleAutorise(Bataille bataille) {
		  // Si c'est une attaque, on vérifie que le joueur n'est pas bloqué
		    if (bataille instanceof Attaque) {
		        return !peutAvancer();
		    }

		    // Si c'est une parade
		    if (bataille instanceof Parade) {
		        // Cas 1 : Feu Vert
		        if (bataille.equals(Cartes.FEU_VERT)) {
		            // On peut déposer un Feu Vert si la pile de bataille est vide,
		            // ou si le sommet est un Feu Rouge ou une parade autre que Feu Vert
		            Carte sommet = PileDeBataille.isEmpty() ? null : PileDeBataille.get(PileDeBataille.size() - 1);
		            return PileDeBataille.isEmpty() || 
		                   (sommet instanceof Attaque && sommet.equals(Cartes.FEU_ROUGE)) ||
		                   (sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT));
		        }

		        // Cas 2 : Autre parade
		        // On peut déposer si la pile n'est pas vide et que son sommet est une attaque du même type
		        return !PileDeBataille.isEmpty() && PileDeBataille.get(PileDeBataille.size() - 1) instanceof Attaque &&
		               PileDeBataille.get(PileDeBataille.size() - 1).getClass().equals(bataille.getClass());
		    }

		    return false;
		}

	  public boolean estDepotAutorise(Carte carte) {
		    // Si la carte est une borne
		    if (carte instanceof Borne) {
		        return estDepotBorneAutorise((Borne) carte);
		    }

		    // Si la carte est une limite
		    if (carte instanceof Limite) {
		        return estDepotLimiteAutorise((Limite) carte);
		    }

		    // Si la carte est une bataille
		    if (carte instanceof Bataille) {
		        return estDepotBatailleAutorise((Bataille) carte);
		    }

		    // Par défaut, renvoie false si la carte ne correspond à aucun type attendu
		    return false;
		}

}
