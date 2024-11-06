package Jeu;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import cartes.Borne;
import cartes.Carte;
import cartes.*;
public class Joueur  implements Comparable<Joueur>{
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	 private MainJoueur mainJoueur; 
	public Joueur(String nom) {
		this.nom=nom;
		this.zoneDeJeu= new ZoneDeJeu();
		 this.mainJoueur = new MainJoueur();
	}
	 public String getNom() {
	        return nom;
	    }
	 public ZoneDeJeu getZoneDeJeu() {
	        return zoneDeJeu;
	    }
	 public MainJoueur getMainJoueur() {
	        return mainJoueur;
	    }
	 public void donner(Carte carte) {
	        mainJoueur.prendre(carte);
	    }
	 public Carte prendreCarte(Sabot sabot) {
	        if (sabot == null || sabot.estVide()) {
	            return null; // Retourne null si le sabot est vide
	        }

	        Carte carte = sabot.piocher(); // Prend la première carte du sabot
	        mainJoueur.prendre(carte); // Ajoute la carte à la main du joueur
	        return carte; // Retourne la carte prise
	    }
	 @Override
	 public boolean equals(Object obj) {
	     
	     if (obj instanceof Joueur) {
	         Joueur joueur = (Joueur) obj; 
	         return nom.equals(joueur.nom);
	     }
	     return false; 
	 }
	 @Override
	    public int hashCode() {
	        return nom.hashCode();
	    }
	 @Override
	 public String toString() {
	        return nom;
	    }
	@Override
	 public int compareTo(Joueur autreJoueur) {
        return this.nom.compareTo(autreJoueur.nom);
    }
	// Méthode pour déposer une carte dans la zone de jeu du joueur
    public void deposer(Carte carte) {
        zoneDeJeu.deposer(carte); // Appelle la méthode deposer de ZoneDeJeu
    }
 // Méthode pour retourner le nombre total de km parcourus
    public int donnerKmParcourus() {
        return zoneDeJeu.donnerKmParcourus();
    }
    public boolean estDepotAutorise(Carte carte) {
        return zoneDeJeu.estDepotAutorise(carte);
    }
    public Set<Coup> coupsPossibles(Set<Joueur> participants) {
        Set<Coup> coupsValides = new HashSet<>();

        // Parcourt chaque carte dans la main du joueur
        for (Carte carte : mainJoueur.getMain()) {
            // Pour chaque participant
            for (Joueur joueurCible : participants) {
                // Créé un coup avec cette carte et le joueur cible
                Coup coup = new Coup(this, carte, joueurCible.equals(this) ? null : joueurCible);
                
                // Vérifie si le coup est valide
                if (coup.estValide()) {
                    coupsValides.add(coup);
                }
            }
        }

        return coupsValides;
    }
    public Set<Coup> coupsDefausse() {
        Set<Coup> coupsDefausse = new HashSet<>();

        // Parcourt chaque carte dans la main du joueur
        for (Carte carte : mainJoueur.getMain()) {
            // Crée un coup avec la carte et une cible nulle
            Coup coup = new Coup(this, carte, null);
            coupsDefausse.add(coup);
        }

        return coupsDefausse;
    }
 // Méthode pour retirer une carte de la main du joueur
    public boolean retirerDeLaMain(Carte carte) {
        return mainJoueur.jouer(carte); // Utilise la méthode jouer() de MainJoueur pour retirer la carte
    }
    public Coup choisirCoup(Set<Joueur> participants) {
        Set<Coup> coupsPossibles = coupsPossibles(participants); // Génère les coups possibles

        // Si des coups sont possibles, en choisir un aléatoirement
        if (!coupsPossibles.isEmpty()) {
            return choisirCoupAleatoire(coupsPossibles);
        }

        // Sinon, utiliser les coups de défausse et en choisir un aléatoirement
        Set<Coup> coupsDefausse = coupsDefausse();
        return choisirCoupAleatoire(coupsDefausse);
    }
    private Coup choisirCoupAleatoire(Set<Coup> coups) {
        int index = new Random().nextInt(coups.size());
        return coups.stream().skip(index).findFirst().orElse(null);
    }
    public String afficherEtatJoueur() {
        // 1. Ensemble des bottes
        String bottes = zoneDeJeu.getBottes().stream()
                                 .map(Carte::toString)
                                 .collect(Collectors.joining(", "));

        // 2. Limitation de vitesse
        boolean limitationVitesse = zoneDeJeu.donnerLimitationVitesse() == 50;

        // 3. Sommet de la pile de bataille
        String sommetBataille = zoneDeJeu.getSommetPileBataille() != null 
                                ? zoneDeJeu.getSommetPileBataille().toString() 
                                : "null";

        // 4. Contenu de la main
        String main = mainJoueur.getMain().stream()
                                .map(Carte::toString)
                                .collect(Collectors.joining(", "));

        return "État du joueur " + nom + " :\n" +
               "- Bottes : " + bottes + "\n" +
               "- Limitation de vitesse active : " + limitationVitesse + "\n" +
               "- Sommet de la pile de bataille : " + sommetBataille + "\n" +
               "- Cartes en main : " + main;
    }
}
