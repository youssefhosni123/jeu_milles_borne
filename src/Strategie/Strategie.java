package Strategie;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import Jeu.Coup;

public interface Strategie {
	default boolean trierCoups(Set<Coup> coups) {
        if (coups == null || coups.isEmpty()) {
            throw new IllegalArgumentException("L'ensemble de coups ne doit pas être vide.");
        }

        // Utilisation d'un comparateur qui compare les coups de manière aléatoire, sauf si identiques
        Comparator<Coup> comparator = new Comparator<Coup>() {
            private final Random random = new Random();

            @Override
            public int compare(Coup coup1, Coup coup2) {
                if (coup1.equals(coup2)) {
                    return 0;
                }
                return random.nextBoolean() ? -1 : 1;
            }
        };

        // Créer un TreeSet avec le comparateur pour trier les coups
        return new TreeSet<>(comparator).addAll(coups);
    }

}
