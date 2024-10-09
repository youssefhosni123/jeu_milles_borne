package testsFonctionnels;
import utils.GestionCartes;
import Jeu.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TestGestiondeCarte {
	public class Main {
	    public static void main(String[] args) {
	        // Créer une liste d'exemple
	        List<String> cartes = new ArrayList<>(Arrays.asList("As", "Roi", "Dame", "Valet", "10", "9"));

	        // Tester la première version de extraire
	        String carteExtraite = GestionCartes.extraire(cartes);
	        System.out.println("Carte extraite : " + carteExtraite);
	        System.out.println("Liste après extraction : " + cartes);

	        // Réinitialiser la liste
	        cartes = new ArrayList<>(Arrays.asList("As", "Roi", "Dame", "Valet", "10", "9"));

	        // Tester la deuxième version avec ListIterator
	        String carteExtraiteAvecIterator = GestionCartes.extraireAvecIterator(cartes);
	        System.out.println("Carte extraite avec iterator : " + carteExtraiteAvecIterator);
	        System.out.println("Liste après extraction avec iterator : " + cartes);
	        
	        
	        // Créer deux listes d'exemple
	        List<String> liste1 = new ArrayList<>(Arrays.asList("As", "Roi", "Dame", "Valet", "10", "9"));
	        List<String> liste2 = new ArrayList<>(Arrays.asList("Valet", "As", "Roi", "Dame", "9", "10"));

	        // Tester si les deux listes sont des mélanges équivalents
	        boolean estEquivalent = GestionCartes.verifierMelange(liste1, liste2);
	        System.out.println("Les deux listes sont-elles des mélanges équivalents ? " + estEquivalent);

	        // Tester avec des listes différentes
	        List<String> liste3 = new ArrayList<>(Arrays.asList("As", "Roi", "Dame", "Valet", "9"));
	        boolean estEquivalentDiff = GestionCartes.verifierMelange(liste1, liste3);
	        System.out.println("Les deux listes sont-elles des mélanges équivalents ? " + estEquivalentDiff);
	    }
}
}
