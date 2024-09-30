package cartes;

public enum Type {
	FEU("Feu Rouge", "Feu Vert", "Véhicule Prioritaire"),
    ESSENCE("Panne d'Essence", "Essence", "Citerne d'Essence"),
    CREVAISON("Crevaison", "Roue de Secours", "Increvable"),
    ACCIDENT("Accident", "Réparations", "As du Volant"),
    LIMITE("Limite de Vitesse", "Fin de Limite", null), // Limite de Vitesse, pas de botte
    VEHICULE_PRIORITAIRE(null, null, "Véhicule Prioritaire"); // Botte spécifique
	private final String attaque;
    private final String parade;
    private final String botte;

    // Constructeur avec 3 paramètres
    Type(String attaque, String parade, String botte) {
        this.attaque = attaque;
        this.parade = parade;
        this.botte = botte;
    }
    // Méthodes pour obtenir les différents affichages
    public String getAttaque() {
        return attaque;
    }

    public String getParade() {
        return parade;
    }

    public String getBotte() {
        return botte;
    }

}
