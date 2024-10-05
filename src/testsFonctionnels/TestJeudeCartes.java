package testsFonctionnels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cartes.*;
import utils.GestionCartes;
public class TestJeudeCartes {
    public static void main(String[] args) {
       /* // Créer une instance de JeuDeCartes
        JeuDeCartes jeu = new JeuDeCartes();

        // Tester la méthode checkCount()
        boolean resultat = jeu.checkCount();
        if (resultat) {
            System.out.println("Toutes les cartes respectent la configuration !");
        } else {
            System.out.println("Certaines cartes ne respectent pas la configuration.");
        }
    }*/
    	        // Créer une liste d'exemple
    	        List<String> cartes = new ArrayList<>(Arrays.asList("As", "Roi", "Dame", "Valet", "10", "9"));

    	        // Afficher la liste originale
    	        System.out.println("Liste originale : " + cartes);

    	        // Mélanger la liste
    	        List<String> cartesMelangees = GestionCartes.melanger(cartes);

    	        // Afficher la liste mélangée et la liste originale (qui devrait être vide)
    	        System.out.println("Liste mélangée : " + cartesMelangees);
    	        System.out.println("Liste originale après mélange (devrait être vide) : " + cartes);

    }
}
