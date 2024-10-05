package utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	// a. Version travaillant directement sur la liste
    public static <T> T extraire(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne peut pas être vide.");
        }

        // Choisir un index aléatoire dans la liste
        Random random = new Random();
        int index = random.nextInt(liste.size());

        // Supprimer et retourner l'élément à cet index
        return liste.remove(index);
    }

    // b. Version utilisant un ListIterator
    public static <T> T extraireAvecIterator(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne peut pas être vide.");
        }

        // Choisir un index aléatoire dans la liste
        Random random = new Random();
        int index = random.nextInt(liste.size());

        // Utiliser un ListIterator pour parcourir la liste
        ListIterator<T> iterator = liste.listIterator();
        T element = null;

        for (int i = 0; i <= index; i++) {
            element = iterator.next();
        }

        // Supprimer l'élément
        iterator.remove();

        return element;
    }
    public static <T> List<T> melanger(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne peut pas être vide.");
        }

        // Créer une nouvelle liste pour stocker les éléments mélangés
        List<T> listeMelangee = new ArrayList<>();

        // Extraire des éléments de la liste passée en argument jusqu'à ce qu'elle soit vide
        while (!liste.isEmpty()) {
            // Utiliser la méthode extraire pour choisir un élément aléatoire et l'ajouter à la nouvelle liste
            T element = extraire(liste);
            listeMelangee.add(element);
        }

        return listeMelangee;
    }
    public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
        // Vérifier si les listes sont de même taille
        if (liste1.size() != liste2.size()) {
            return false;
        }

        // Vérifier que chaque élément de liste1 a le même nombre d'occurrences dans liste2
        for (T element : liste1) {
            int frequencyListe1 = Collections.frequency(liste1, element);
            int frequencyListe2 = Collections.frequency(liste2, element);

            if (frequencyListe1 != frequencyListe2) {
                return false;
            }
        }

        // Si tous les éléments ont le même nombre d'occurrences, les listes sont équivalentes
        return true;
    }
    public static <T> List<T> rassembler(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne peut pas être vide.");
        }

        // Nouvelle liste pour stocker les éléments rassemblés
        List<T> listeRassemblee = new ArrayList<>();
        List<T> elementsDejaTraites = new ArrayList<>(); // Pour garder une trace des éléments déjà rassemblés

        // Parcourir chaque élément de la liste d'origine
        for (T element : liste) {
            if (!elementsDejaTraites.contains(element)) {
                // Ajouter tous les éléments identiques à la nouvelle liste
                for (T elem : liste) {
                    if (elem.equals(element)) {
                        listeRassemblee.add(elem);
                    }
                }
                // Marquer l'élément comme traité
                elementsDejaTraites.add(element);
            }
        }

        return listeRassemblee;
    }
    public static <T> boolean verifierRassemblement(List<T> liste) {
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne peut pas être vide.");
        }

        // Utiliser un premier ListIterator pour parcourir la liste
        ListIterator<T> it1 = liste.listIterator();

        while (it1.hasNext()) {
            T currentValue = it1.next();

            // Utiliser un second ListIterator pour parcourir le reste de la liste
            ListIterator<T> it2 = liste.listIterator(it1.nextIndex());

            while (it2.hasNext()) {
                T nextValue = it2.next();

                // Si une occurrence de l'ancienne valeur est trouvée, les éléments ne sont pas consécutifs
                if (nextValue.equals(currentValue)) {
                    return false;
                }
            }
        }

        // Si aucune duplication n'est trouvée après un changement d'élément, les éléments identiques sont consécutifs
        return true;
    }
}
