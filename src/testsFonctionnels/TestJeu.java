package testsFonctionnels;
import java.util.HashSet;
import java.util.Set;
import Jeu.*;
public class TestJeu {
	 public static void main(String[] args) {
		   // 1. Créer une instance de Jeu
	        jeu jeu = new jeu();

	        // 2. Créer et inscrire trois joueurs
	        Joueur joueur1 = new Joueur("Jack");
	        Joueur joueur2 = new Joueur("Bill");
	        Joueur joueur3 = new Joueur("Luffy");
	        

	        jeu.inscrire(joueur1, joueur2, joueur3);

	        // 3. Distribuer les cartes aux joueurs
	        jeu.distribuerCartes();

	        // 4. Utiliser la méthode lancer() pour faire jouer tous les tours automatiquement
	        String resultatPartie = jeu.lancer();
	        
	        // 5. Afficher le déroulement complet de la partie
	        System.out.println(resultatPartie);
	    }
}


