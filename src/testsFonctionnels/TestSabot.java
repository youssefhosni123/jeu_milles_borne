package testsFonctionnels;
import cartes.*;
import Jeu.Sabot;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class TestSabot {
	public static void main(String[] args) {
        // Créer un tableau de cartes pour initialiser le sabot
        Carte[] cartes = {
            new Borne(25), new Borne(50), new Attaque(Type.FEU), new Parade(Type.ESSENCE)
        };
        
        // Créer le sabot
        Sabot sabot = new Sabot(cartes);
        
        // a) Utilisation de piocher jusqu'à ce que le sabot soit vide
        try {
            while (!sabot.estVide()) {
                Carte carte = sabot.piocher();
                System.out.println("je pioche " + carte);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Le sabot est vide !");
        }

        
        sabot = new Sabot(cartes);

       
        Iterator<Carte> it = sabot.iterator();
        while (it.hasNext()) {
            Carte carte = it.next();
            System.out.println("je pioche " + carte);
            it.remove();  
        }

       
        sabot = new Sabot(cartes); // Recréer le sabot
        it = sabot.iterator();
        
        try {
            Carte premiereCarte = sabot.piocher(); // Piocher une carte avant la boucle
            System.out.println("Première carte piochée: " + premiereCarte);
            
            // Boucle avec itération et suppression
            while (it.hasNext()) {
                Carte carte = it.next();
                System.out.println("je pioche " + carte);
                sabot.ajouterCarte(new Botte(Type.ACCIDENT)); // Tentative d'ajouter As du Volant
                it.remove();
            }
        } catch (IllegalStateException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (ConcurrentModificationException e) {
            System.out.println("Erreur de modification concurrente : " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Erreur : Plus de cartes à piocher !");
        }
    }

}
