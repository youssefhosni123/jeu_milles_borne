package cartes;

public class Probleme  extends Carte{
	public Probleme(Type type) {
		this.type=type;

	}
	
	 @Override
	    public String toString() {
	        return "Probleme de type: " + type;
	    }

}
