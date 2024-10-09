package Jeu;

import cartes.Borne;
import cartes.Carte;

public class Joueur  implements Comparable<Joueur>{
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	 private MainJoueur mainJoueur; 
	public Joueur(String nom) {
		this.nom=nom;
		this.zoneDeJeu= new ZoneDeJeu();
		 this.mainJoueur = new MainJoueur();
	}
	 public String getNom() {
	        return nom;
	    }
	 public ZoneDeJeu getZoneDeJeu() {
	        return zoneDeJeu;
	    }
	 public MainJoueur getMainJoueur() {
	        return mainJoueur;
	    }
	 public void donner(Carte carte) {
	        mainJoueur.prendre(carte);
	    }
	 public Carte prendreCarte(Sabot sabot) {
	        if (sabot == null || sabot.estVide()) {
	            return null; // Retourne null si le sabot est vide
	        }

	        Carte carte = sabot.piocher(); // Prend la première carte du sabot
	        mainJoueur.prendre(carte); // Ajoute la carte à la main du joueur
	        return carte; // Retourne la carte prise
	    }
	 @Override
	 public boolean equals(Object obj) {
	     
	     if (obj instanceof Joueur) {
	         Joueur joueur = (Joueur) obj; 
	         return nom.equals(joueur.nom);
	     }
	     return false; 
	 }
	 @Override
	    public int hashCode() {
	        return nom.hashCode();
	    }
	 @Override
	 public String toString() {
	        return nom;
	    }
	@Override
	 public int compareTo(Joueur autreJoueur) {
        return this.nom.compareTo(autreJoueur.nom);
    }
	

}
