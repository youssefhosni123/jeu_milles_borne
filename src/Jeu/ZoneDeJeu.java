package Jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;

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
	
}
