package testsFonctionnels;
import Jeu.jeu;
import Jeu.Sabot;
public class Tests {
	  public static void main(String[] args) {
	        // Créer un nouveau jeu
	        jeu jeu = new jeu();

	        // Récupérer le sabot du jeu
	        Sabot sabot = jeu.getSabot();

	        // Afficher le contenu du sabot (optionnel)
	        System.out.println("Sabot créé avec succès !");
	    }
}
