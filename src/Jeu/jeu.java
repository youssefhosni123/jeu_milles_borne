package Jeu;
import cartes.Carte;
import java.util.Iterator;
import cartes.JeuDeCartes;
import utils.GestionCartes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
public class jeu {
    private Sabot sabot;
    private List<Joueur> joueurs;
    private static final int NBCARTES = 6;
    private Iterator<Joueur> joueurIterator;
    // Constructeur
    public jeu() {
        // Récupérer le tableau de cartes de la classe JeuDeCartes
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] tableauDeCartes = jeuDeCartes.donnerCartes();
        joueurs = new ArrayList<>();
        // Transformer le tableau en liste mutable (ArrayList)
        List<Carte> listeCartes = new ArrayList<>(Arrays.asList(tableauDeCartes));

        // Mélanger la liste
        listeCartes = GestionCartes.melanger(listeCartes);

        // Transformer la liste mélangée en tableau
        Carte[] tableauMelange = listeCartes.toArray(new Carte[0]);

        // Créer le sabot avec le tableau mélangé
        this.sabot = new Sabot(tableauMelange);
    }

    // Méthode pour accéder au sabot
    public Sabot getSabot() {
        return sabot;
    }
    public List<Joueur> getJoueurs() {
        return joueurs;
    }
    // Méthode pour inscrire un ou plusieurs joueurs dans l'ordre d'inscription
    public void inscrire(Joueur... nouveauxJoueurs) {
    	 for (Joueur joueur : nouveauxJoueurs) {
    	        joueurs.add(joueur);
    	    }
    	    // Initialiser l'itérateur après avoir inscrit les joueurs
    	    joueurIterator = joueurs.iterator();
    }
    public void distribuerCartes() {
        for (int i = 0; i < NBCARTES; i++) { // Répète l'opération 6 fois
            for (Joueur joueur : joueurs) {
                Carte carte = sabot.piocher(); // Pioche une carte depuis le sabot
                if (carte != null) { // Vérifie que le sabot contient encore des cartes
                    joueur.getMainJoueur().prendre(carte); // Donne la carte au joueur
                } else {
                    System.out.println("Le sabot est vide. Distribution interrompue.");
                    return;
                }
            }
        }
    }
    public String jouerTour(Joueur joueur) {
        StringBuilder actions = new StringBuilder();

        // 1. Le joueur pioche une carte
        Carte cartePiochee = joueur.prendreCarte(sabot);
        actions.append(joueur.getNom()).append(" pioche une carte : ").append(cartePiochee).append("\n");

        // 2. Le joueur choisit un coup
        Set<Joueur> participants = new HashSet<>(joueurs); // Ensemble des participants
        Coup coup = joueur.choisirCoup(participants);
        actions.append(joueur.getNom()).append(" choisit un coup : ").append(coup).append("\n");

        // 3. Retirer la carte de la main du joueur
        joueur.retirerDeLaMain(coup.getCarteJouee());

        // 4. Exécuter le coup
        if (coup.getJoueurCible() == null) {
            // Si le coup n’a pas de cible, la carte est replacée dans le sabot
            sabot.ajouterCarte(coup.getCarteJouee());
            actions.append(joueur.getNom()).append(" défausse la carte ").append(coup.getCarteJouee()).append(" dans le sabot\n");
        } else {
            // Si le coup a une cible, la carte est déposée dans la zone de jeu du joueur cible
            coup.getJoueurCible().deposer(coup.getCarteJouee());
            actions.append(joueur.getNom()).append(" dépose la carte ").append(coup.getCarteJouee())
                    .append(" dans la zone de jeu de ").append(coup.getJoueurCible().getNom()).append("\n");
        }

        return actions.toString();
    }
    public Joueur donnerJoueurSuivant() {
    	 // Vérifier et réinitialiser l'itérateur si nécessaire
        if (joueurIterator == null || !joueurIterator.hasNext()) {
            joueurIterator = joueurs.iterator(); // Recommence depuis le début
        }
        return joueurIterator.next();
    }
    public String lancer() {
        StringBuilder resultat = new StringBuilder();
        int tourCount = 0; // Limite de tours pour éviter une boucle infinie

        while (!sabot.estVide()) {
            Joueur joueur = donnerJoueurSuivant();
            
            resultat.append("\n--- Tour de ").append(joueur.getNom()).append(" ---\n");
            resultat.append(jouerTour(joueur));

            // Vérifier si le joueur a atteint 1000 bornes
            if (joueur.donnerKmParcourus() >= 1000) {
                resultat.append("\nLe joueur ").append(joueur.getNom()).append(" a atteint 1000 bornes et a gagné la partie !");
                return resultat.toString();
            }

            // Limite temporaire de tours pour éviter une boucle infinie (augmentée à 5000)
            if (++tourCount > 5000) { 
                resultat.append("\nLimite de tours atteinte. Fin de la simulation.");
                
                // Ajouter le classement final lorsque la limite de tours est atteinte
                resultat.append("\nClassement final des joueurs :\n");
                List<Joueur> classementFinal = classement();
                for (int i = 0; i < classementFinal.size(); i++) {
                    Joueur joueurClasse = classementFinal.get(i);
                    resultat.append((i + 1)).append(". ").append(joueurClasse.getNom())
                            .append(" - ").append(joueurClasse.donnerKmParcourus()).append(" km\n");
                }
                return resultat.toString();
            }
        }

        // Affichage du classement final lorsque le sabot est vide
        resultat.append("\nLe sabot est vide. Fin de la partie sans qu'un joueur ait atteint 1000 bornes.\n");
        resultat.append("\nClassement final des joueurs :\n");

        // Récupérer le classement final et l'afficher
        List<Joueur> classementFinal = classement();
        for (int i = 0; i < classementFinal.size(); i++) {
            Joueur joueurClasse = classementFinal.get(i);
            resultat.append((i + 1)).append(". ").append(joueurClasse.getNom())
                    .append(" - ").append(joueurClasse.donnerKmParcourus()).append(" km\n");
        }

        // Déterminer le vainqueur
        if (!classementFinal.isEmpty()) {
            Joueur vainqueur = classementFinal.get(0);
            resultat.append("\nLe vainqueur est ").append(vainqueur.getNom())
                    .append(" avec ").append(vainqueur.donnerKmParcourus()).append(" km parcourus.");
        }

        return resultat.toString();
    }

    public List<Joueur> classement (){
    	Comparator <Joueur> comparator = (j1,j2)->Integer.compare(j2.donnerKmParcourus(), j1.donnerKmParcourus());
    	 TreeSet<Joueur> classementSet = new TreeSet<>(comparator);
    	 classementSet.addAll(joueurs);
    	 return new ArrayList<>(classementSet);
    }  


	}

