package cartes;

public class Main {
	 public static void main(String[] args) {
	       /* JeuDeCartes jeu = new JeuDeCartes();
	        System.out.println(jeu.affichageJeuDeCartes());*/
		 Carte borne1 = new Borne(50);
	        Carte borne2 = new Borne(50);
	        Carte borne3 = new Borne(75);
	        Carte attaque1 = new Attaque(Type.FEU);
	        Carte attaque2 = new Attaque(Type.FEU);
	        Carte parade = new Parade(Type.ESSENCE);

	        System.out.println("Borne 1 égale à Borne 2 ? " + borne1.equals(borne2)); // true
	        System.out.println("Borne 1 égale à Borne 3 ? " + borne1.equals(borne3)); // false
	        System.out.println("Attaque 1 égale à Attaque 2 ? " + attaque1.equals(attaque2)); // true
	        System.out.println("Attaque 1 égale à Parade ? " + attaque1.equals(parade)); // false
	    }
	    }

