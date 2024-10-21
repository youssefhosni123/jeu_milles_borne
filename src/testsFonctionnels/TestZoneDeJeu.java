package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;
import cartes.FinLimite;
import Jeu.Joueur;
import Jeu.ZoneDeJeu;

public class TestZoneDeJeu {
	public static void main(String[] args) {
      /*  // Créer un nouveau joueur
        Joueur joueur = new Joueur("Alice");

        // Déposer des cartes de bornes
        System.out.println("Deposer carte 25 km");
        joueur.deposer(new Borne(25));

        System.out.println("Deposer carte 50 km");
        joueur.deposer(new Borne(50));

        System.out.println("Deposer carte 75 km");
        joueur.deposer(new Borne(75));

        // Calculer le total des bornes déposées
        int totalBornes = joueur.donnerKmParcourus();
        System.out.println("Total des bornes : " + totalBornes); // Doit afficher 150

        // Vérifier la limite de vitesse avec une pile vide (par défaut 200)
        System.out.println("Limite : " + joueur.getZoneDeJeu().donnerLimitationVitesse()); // Doit afficher 200

        // Déposer une carte Limite de vitesse (limite à 50 km/h)
        System.out.println("Deposer Limite de vitesse");
        joueur.deposer(new Limite(null));
        System.out.println("Limite : " + joueur.getZoneDeJeu().donnerLimitationVitesse()); // Doit afficher 50

        // Déposer une carte Fin de Limite de vitesse (retour à 200 km/h)
        System.out.println("Deposer Fin de limite de vitesse");
        joueur.deposer(new FinLimite(null));
        System.out.println("Limite : " + joueur.getZoneDeJeu().donnerLimitationVitesse()); // Doit afficher 200
    }*/
	  // Partie 3 : Tests des dépôts de cartes

		 // Partie 3 : Tests des dépôts de cartes
        ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
        boolean depotOK = false;

        // Feu rouge
        System.out.println("Deposer carte Feu rouge");
        depotOK = zoneDeJeu.estDepotAutorise(Cartes.FEU_ROUGE);
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : false
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : false

        // accident
        System.out.println("Deposer carte attaque - accident");
        depotOK = zoneDeJeu.estDepotAutorise(new Attaque(Cartes.ACCIDENT.getType()));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : false
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : false

        // Feu vert
        System.out.println("Deposer carte Feu vert");
        depotOK = zoneDeJeu.estDepotAutorise(Cartes.FEU_VERT);
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(Cartes.FEU_VERT);
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : true

        // panne d'essence
        System.out.println("Deposer carte attaque - essence");
        depotOK = zoneDeJeu.estDepotAutorise(new Attaque(Cartes.ESSENCE.getType()));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(new Attaque(Cartes.ESSENCE.getType()));
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : false

        // roue de secours
        System.out.println("Deposer carte parade - roue de secours");
        depotOK = zoneDeJeu.estDepotAutorise(new Parade(Cartes.CREVAISON.getType()));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : false
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : false

        // bidon d'essence
        System.out.println("Deposer carte parade - essence");
        depotOK = zoneDeJeu.estDepotAutorise(new Parade(Cartes.ESSENCE.getType()));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(new Parade(Cartes.ESSENCE.getType()));
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : false

        // Feu vert (de nouveau)
        System.out.println("Deposer carte Feu vert");
        depotOK = zoneDeJeu.estDepotAutorise(Cartes.FEU_VERT);
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(Cartes.FEU_VERT);
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : true

        // Dépôt 100 bornes
        System.out.println("Deposer carte borne - 100");
        depotOK = zoneDeJeu.estDepotAutorise(new Borne(100));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(new Borne(100));
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : true

        // Dépôt limitation de vitesse 50 bornes
        System.out.println("Deposer carte limite - 50");
        depotOK = zoneDeJeu.estDepotAutorise(new DebutLimite(null));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(new DebutLimite(null));
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : true

        // Dépôt 100 bornes (limite atteinte)
        System.out.println("Deposer carte borne - 100");
        depotOK = zoneDeJeu.estDepotAutorise(new Borne(100));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : false
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : true

        // Dépôt 25 bornes
        System.out.println("Deposer carte borne - 25");
        depotOK = zoneDeJeu.estDepotAutorise(new Borne(25));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(new Borne(25));
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : true

        // Dépôt fin limitation
        System.out.println("Deposer carte fin limite - 50");
        depotOK = zoneDeJeu.estDepotAutorise(new FinLimite(null));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(new FinLimite(null));
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : true

        // Dépôt 100 bornes (après fin de limite)
        System.out.println("Deposer carte borne - 100");
        depotOK = zoneDeJeu.estDepotAutorise(new Borne(100));
        System.out.println("dépôt ok ? " + depotOK); // Résultat attendu : true
        if (depotOK) {
            zoneDeJeu.deposer(new Borne(100));
        }
        System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer()); // Résultat attendu : true
    }
}

