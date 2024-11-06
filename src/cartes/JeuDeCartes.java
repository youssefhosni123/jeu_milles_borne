package cartes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JeuDeCartes {
    private Map<Carte, Integer> typesDeCartes;

    // Constructeur
    public JeuDeCartes() {
        typesDeCartes = new HashMap<>();

        // Initialiser la map avec les cartes et leurs nombres d'exemplaires
        typesDeCartes.put(new Borne(25), 10);
        typesDeCartes.put(new Borne(50), 10);
        typesDeCartes.put(new Borne(75), 10);
        typesDeCartes.put(new Borne(100), 12);
        typesDeCartes.put(new Borne(200), 4);
        typesDeCartes.put(new Parade(Type.FEU), 14);
        typesDeCartes.put(new FinLimite(), 6);
        typesDeCartes.put(new Parade(Type.ESSENCE), 6);
        typesDeCartes.put(new Parade(Type.CREVAISON), 6);
        typesDeCartes.put(new Parade(Type.ACCIDENT), 6);
        typesDeCartes.put(new Attaque(Type.FEU), 5);
        typesDeCartes.put(new Attaque(Type.ESSENCE), 3);
        typesDeCartes.put(new Attaque(Type.CREVAISON), 3);
        typesDeCartes.put(new Attaque(Type.ACCIDENT), 3);
        typesDeCartes.put(new Botte(Type.ESSENCE), 1);
        typesDeCartes.put(new Botte(Type.CREVAISON), 1);
        typesDeCartes.put(new Botte(Type.ACCIDENT), 1);
        typesDeCartes.put(new Botte(Type.FEU), 1);
    }

    // Méthode pour afficher les configurations du jeu
    public String affichageJeuDeCartes() {
        StringBuilder sb = new StringBuilder();
        sb.append("JEU :\n");
        for (Map.Entry<Carte, Integer> entry : typesDeCartes.entrySet()) {
            sb.append(entry.getValue()) // Nombre d'exemplaires
              .append(" ")
              .append(entry.getKey().toString()) // Nom de la carte
              .append("\n");
        }
        return sb.toString();
    }

    // Méthode pour donner toutes les cartes en fonction de leur nombre d'exemplaires
    public Carte[] donnerCartes() {
        List<Carte> toutesLesCartes = new ArrayList<>();

        // Parcourir chaque entrée de la map
        for (Map.Entry<Carte, Integer> entry : typesDeCartes.entrySet()) {
            Carte carte = entry.getKey();
            int nbExemplaires = entry.getValue();

            // Répliquer chaque carte selon son nombre d'exemplaires
            for (int i = 0; i < nbExemplaires; i++) {
                toutesLesCartes.add(carte);
            }
        }

        // Convertir la liste en tableau et la renvoyer
        return toutesLesCartes.toArray(new Carte[0]);
    }

    // Méthode pour vérifier que le nombre de chaque carte est conforme
    public boolean checkCount() {
        Carte[] toutesLesCartes = donnerCartes();

        // Parcourir chaque entrée de la map
        for (Map.Entry<Carte, Integer> entry : typesDeCartes.entrySet()) {
            Carte carte = entry.getKey();
            int expectedCount = entry.getValue();
            int actualCount = 0;

            // Compter le nombre d'occurrences de chaque carte
            for (Carte c : toutesLesCartes) {
                if (c.equals(carte)) {
                    actualCount++;
                }
            }

            // Vérifier si le nombre d'occurrences correspond au nombre attendu
            if (actualCount != expectedCount) {
                System.out.println("Erreur : " + carte + " attendue " + expectedCount + " fois, trouvée " + actualCount + " fois.");
                return false;
            } 
        }

        // Tout est conforme
        return true;
    }

	    }
	
	
