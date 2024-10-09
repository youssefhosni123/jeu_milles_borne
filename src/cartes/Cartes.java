package cartes;

public interface Cartes {
	// Constantes représentant des cartes de type FEU
    Carte PRIORITAIRE = new Botte("Prioritaire", Type.FEUX);
    Carte FEU_ROUGE = new Attaque("Feu Rouge", Type.FEUX);
    Carte FEU_VERT = new Parade("Feu Vert", Type.FEUX);

}
