package Jeu;
import cartes.Carte;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Sabot implements Iterable<Carte> {
    private Carte[] cartes;
    private int nbCartes;
    private int modCount; // Compteur pour gérer les modifications concurrentes

    // Constructeur
    public Sabot(Carte[] cartes) {
        this.cartes = cartes;
        this.nbCartes = cartes.length;
        this.modCount = 0;
    }

    // Méthode estVide : indique si le sabot est vide
    public boolean estVide() {
        return nbCartes == 0;
    }

    // Méthode ajouterCarte : ajoute une carte au sabot
    public void ajouterCarte(Carte carte) {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Capacité maximale du sabot atteinte");
        }
        cartes[nbCartes] = carte;
        nbCartes++;
        modCount++; // Incrémenter pour suivre la modification
    }

    // Méthode piocher : renvoie et supprime la première carte du sabot
    public Carte piocher() {
        if (estVide()) {
            throw new NoSuchElementException("Le sabot est vide");
        }
        Carte carte = cartes[0];
        // Décaler les cartes restantes
        System.arraycopy(cartes, 1, cartes, 0, nbCartes - 1);
        nbCartes--;
        modCount++; // Incrémenter pour suivre la modification
        return carte;
    }

    // Méthode iterator pour rendre la classe itérable
    @Override
    public Iterator<Carte> iterator() {
        return new SabotIterator();
    }

    // Classe interne pour gérer l'itération et la suppression de cartes
    private class SabotIterator implements Iterator<Carte> {
        private int cursor; // Position actuelle de l'itérateur
        private int expectedModCount; // Pour gérer les modifications concurrentes

        public SabotIterator() {
            this.cursor = 0;
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return cursor < nbCartes;
        }

        @Override
        public Carte next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Le sabot a été modifié");
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return cartes[cursor++];
        }

        @Override
        public void remove() {
            if (cursor == 0) {
                throw new IllegalStateException("Aucune carte à supprimer");
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Le sabot a été modifié");
            }
            // Décaler les cartes restantes pour supprimer la carte courante
            System.arraycopy(cartes, cursor, cartes, cursor - 1, nbCartes - cursor);
            nbCartes--;
            cursor--;
            expectedModCount++;
            modCount++;
        }
    }
}