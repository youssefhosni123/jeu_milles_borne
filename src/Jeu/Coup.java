package Jeu;
import java.util.Objects;

import cartes.*;
public class Coup {
	 private final Joueur joueurCourant;
	    private final Carte carteJouee;
	    private final Joueur joueurCible;

	    // Constructeur
	    public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
	        this.joueurCourant = joueurCourant;
	        this.carteJouee = carteJouee;
	        this.joueurCible = joueurCible; // Peut être null si la carte est remise dans le sabot
	    }

	    // Accesseur pour le joueur courant
	    public Joueur getJoueurCourant() {
	        return joueurCourant;
	    }

	    // Accesseur pour la carte jouée
	    public Carte getCarteJouee() {
	        return carteJouee;
	    }

	    // Accesseur pour le joueur cible
	    public Joueur getJoueurCible() {
	        return joueurCible;
	    }
	    // Méthode estValide pour vérifier si le coup est valide
	    public boolean estValide() {
	        // Si la carte est une Attaque ou une Limite
	        if (carteJouee instanceof Attaque || carteJouee instanceof Limite) {
	            // Elle doit être jouée sur un autre joueur (joueurCible ne doit pas être null et ne doit pas être joueurCourant)
	            return joueurCible != null && !joueurCible.equals(joueurCourant);
	        } else {
	            // Sinon, elle ne peut être jouée que dans sa propre zone
	            return joueurCible == null || joueurCible.equals(joueurCourant);
	        }
	    }
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Coup coup = (Coup) obj;
	        return Objects.equals(joueurCourant, coup.joueurCourant) &&
	               Objects.equals(carteJouee, coup.carteJouee) &&
	               Objects.equals(joueurCible, coup.joueurCible);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(joueurCourant, carteJouee, joueurCible);
	    }
	    @Override
	    public String toString() {
	        String action = (joueurCible == null) ? "défausse" : "dépose";
	        return action + " la carte " + carteJouee.toString() + (joueurCible != null ? " dans la zone de jeu de " + joueurCible.getNom() : "");
	    }
	    
}

