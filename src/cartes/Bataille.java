package cartes;

public class Bataille extends Probleme  {
	 public Bataille(Type type) {
	        super(type);
	    }

	    @Override
	    public String toString() {
	        return "Carte Bataille: " + type;
	    }

}
