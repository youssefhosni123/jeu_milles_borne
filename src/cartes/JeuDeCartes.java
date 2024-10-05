package cartes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JeuDeCartes {
	 private Configuration[] typesDeCartes;

	    // Constructeur pour initialiser les cartes et leurs configurations
	    public JeuDeCartes() {
	        typesDeCartes = new Configuration[] {
	        		new Configuration(new Borne(25), 10),
	                new Configuration(new Borne(50), 10),
	                new Configuration(new Borne(75), 10),
	                new Configuration(new Borne(100), 12),
	                new Configuration(new Borne(200), 4),
	                new Configuration(new Parade(Type.FEU), 14), // Feu Vert
	                new Configuration(new FinLimite(Type.LIMITE), 6), // Fin de Limite
	                new Configuration(new Parade(Type.ESSENCE), 6), // Bidon d'essence
	                new Configuration(new Parade(Type.CREVAISON), 6), // Roue de secours
	                new Configuration(new Parade(Type.ACCIDENT), 6), // Réparation
	                new Configuration(new Attaque(Type.FEU), 5), // Feu Rouge
	                new Configuration(new Attaque(Type.LIMITE), 4), // Limite 50
	                new Configuration(new Attaque(Type.ESSENCE), 3), // Panne d'essence
	                new Configuration(new Attaque(Type.CREVAISON), 3), // Crevaison
	                new Configuration(new Attaque(Type.ACCIDENT), 3), // Accident
	                new Configuration(new Botte(Type.ESSENCE), 1), // Citerne
	                new Configuration(new Botte(Type.CREVAISON), 1), // Increvable
	                new Configuration(new Botte(Type.ACCIDENT), 1), // As du Volant
	                new Configuration(new Botte(Type.VEHICULE_PRIORITAIRE), 1) // Véhicule Prioritaire
	            };
	    }

	    // Méthode pour afficher les configurations du jeu
	    public String affichageJeuDeCartes() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("JEU :\n");
	        for (Configuration config : typesDeCartes) {
	            sb.append(config.getNbExemplaires())
	              .append(" ")
	              .append(config.getCarte().toString())
	              .append("\n");
	        }
	        return sb.toString();
	    }
	    public Carte[] donnerCartes() {
	        List<Carte> toutesLesCartes = new ArrayList<>();

	        // Parcourir chaque configuration
	        for (Configuration config : typesDeCartes) {
	            // Répliquer chaque carte selon son nombre d'exemplaires
	            for (int i = 0; i < config.getNbExemplaires(); i++) {
	                toutesLesCartes.add(config.getCarte());
	            }
	        }

	        // Convertir la liste en tableau et la renvoyer
	        return toutesLesCartes.toArray(new Carte[0]);
	    }
	    public boolean checkCount() {
	        Carte[] toutesLesCartes = donnerCartes();

	        // Parcourir chaque configuration
	        for (Configuration config : typesDeCartes) {
	            int expectedCount = config.getNbExemplaires();
	            int actualCount = 0;

	            // Compter le nombre d'occurrences de la carte dans toutesLesCartes
	            for (Carte carte : toutesLesCartes) {
	                if (carte.equals(config.getCarte())) {
	                    actualCount++;
	                }
	            }

	            // Vérifier si le nombre d'occurrences correspond au nombre attendu
	            if (actualCount != expectedCount) {
	                System.out.println("Erreur : " + config.getCarte() + " attendue " + expectedCount + " fois, trouvée " + actualCount + " fois.");
	                return false;
	            }
	        }

	        // Tout est conforme
	        return true;
	    }
	    }
	
	
