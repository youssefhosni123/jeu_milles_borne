package Jeu;
import cartes.*;
public class Main {
	
	    public static void main(String[] args) {
	    	/*
	        // Créer un tableau de cartes pour initialiser le sabot
	        Carte[] cartes = {
	            new Borne(25), new Borne(50), new Attaque(Type.FEU), new Parade(Type.ESSENCE)
	        };
	        
	        Sabot sabot = new Sabot(cartes);

	        System.out.println("Sabot vide ? " + sabot.estVide());

	        System.out.println("Première carte piochée : " + sabot.piocher());

	        System.out.println("Ajout d'une carte Borne 100 km.");
	        sabot.ajouterCarte(new Borne(100));

	        // Itérer sur les cartes restantes
	        System.out.println("Cartes restantes dans le sabot :");
	        for (Carte carte : sabot) {
	            System.out.println(carte);
	        }
	    }*/
	    	 JeuDeCartes jeu = new JeuDeCartes();

	         // Tester la méthode donnerCartes()
	         Carte[] toutesLesCartes = jeu.donnerCartes();

	         // Afficher toutes les cartes répliquées
	         for (Carte carte : toutesLesCartes) {
	             System.out.println(carte);
	         }
	         
	    }
	    
}



