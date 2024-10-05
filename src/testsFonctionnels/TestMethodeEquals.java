package testsFonctionnels;
import cartes.*;
public class TestMethodeEquals {

public static void main(String[] args) {
    // a) Comparaison de deux objets de la carte borne 25 km
    Borne borne1 = new Borne(25);
    Borne borne2 = new Borne(25);
    System.out.println("Deux cartes de 25km sont identiques ? " + borne1.equals(borne2));

    // b) Comparaison de deux objets de la carte feu rouge (Attaque)
    Attaque feuRouge1 = new Attaque(Type.FEU);  // Type Feu Rouge
    Attaque feuRouge2 = new Attaque(Type.FEU);  // Type Feu Rouge
    System.out.println("Deux cartes de feux rouge sont identiques ? " + feuRouge1.equals(feuRouge2));

    // c) Comparaison d'un objet feu rouge et un objet feu vert (Feu Rouge = Attaque, Feu Vert = Parade)
    Attaque feuRouge = new Attaque(Type.FEU);
    Parade feuVert = new Parade(Type.FEU);  // Feu Vert
    System.out.println("La carte feu rouge et la carte feu vert sont identiques ? " + feuRouge.equals(feuVert));
}
}