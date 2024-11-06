package Jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cartes.*;
public class MainJoueur implements Iterable<Carte> {
	 private List<Carte> main;

	    
	    public MainJoueur() {
	        this.main = new ArrayList<>();
	    }

	    // Méthode pour ajouter une carte à la main du joueur
	    public void prendre(Carte carte) {
	        main.add(carte);
	    }

	    // Méthode pour jouer une carte (supprimer de la main)
	    public boolean jouer(Carte carte) {
	       return main.remove(carte);
	    }

	    // Méthode pour obtenir l'état actuel de la main du joueur
	    public List<Carte> getMain() {
	        return main;
	    }
	    @Override
	    public String toString() {
	        return "Main du joueur : " + main;
	    }

		 @Override
		    public Iterator<Carte> iterator() {
		        return main.iterator();
		    }
		
}
