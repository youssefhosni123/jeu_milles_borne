package testsFonctionnels;
import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class TestCartes {
	  // Méthode générique pour vérifier que deux listes ont les mêmes occurrences de chaque élément
    public static <T> boolean verifierOccurrences(List<T> liste1, List<T> liste2) {
        if (liste1.size() != liste2.size()) {
            return false;
        }

        for (T element : liste1) {
            if (Collections.frequency(liste1, element) != Collections.frequency(liste2, element)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Créer un jeu de cartes
        JeuDeCartes jeu = new JeuDeCartes();
        List<Carte> listeCarteNonMelangee = new LinkedList<>();

        // Ajouter les cartes du jeu à la liste non mélangée
        for (Carte carte : jeu.donnerCartes()) {
            listeCarteNonMelangee.add(carte);
        }

        // Créer une copie de la liste non mélangée
        List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
        System.out.println("Liste originale : ");
        System.out.println(listeCartes);

        // Mélanger la liste
        listeCartes = GestionCartes.melanger(listeCartes);
        System.out.println("Liste mélangée : ");
        System.out.println(listeCartes);

        // Vérifier que le mélange n'a pas changé le nombre d'occurrences des éléments
        boolean melangeSansErreur = verifierOccurrences(listeCarteNonMelangee, listeCartes);
        System.out.println("Liste mélangée sans erreur sur les occurrences ? " + melangeSansErreur);

        // Rassembler la liste mélangée
        listeCartes = GestionCartes.rassembler(listeCartes);
        System.out.println("Liste rassemblée : ");
        System.out.println(listeCartes);

        // Vérifier que le rassemblement est correct
        boolean rassemblementCorrect = GestionCartes.verifierRassemblement(listeCartes);
        System.out.println("Liste rassemblée sans erreur ? " + rassemblementCorrect);

        // Tester avec d'autres listes d'exemples
        List<Integer> liste1 = new ArrayList<>(List.of(1, 1, 2, 1, 3));
        List<Integer> liste2 = new ArrayList<>(List.of(1, 4, 3, 2));
        List<Integer> liste3 = new ArrayList<>(List.of(1, 1, 2, 3, 1));

        System.out.println("\nTest sur d'autres listes :");

        // Mélanger les autres listes
        liste1 = GestionCartes.melanger(liste1);
        System.out.println("Liste 1 mélangée : " + liste1);

        // Vérifier le rassemblement
        boolean rassemblementListe1 = GestionCartes.verifierRassemblement(liste1);
        System.out.println("Rassemblement correct pour Liste 1 ? " + rassemblementListe1);

        liste2 = GestionCartes.melanger(liste2);
        System.out.println("Liste 2 mélangée : " + liste2);

        boolean rassemblementListe2 = GestionCartes.verifierRassemblement(liste2);
        System.out.println("Rassemblement correct pour Liste 2 ? " + rassemblementListe2);

        liste3 = GestionCartes.melanger(liste3);
        System.out.println("Liste 3 mélangée : " + liste3);

        boolean rassemblementListe3 = GestionCartes.verifierRassemblement(liste3);
        System.out.println("Rassemblement correct pour Liste 3 ? " + rassemblementListe3);
    }
}
