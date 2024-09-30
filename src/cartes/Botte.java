package cartes;

public class Botte  extends Bataille{
	public Botte(Type type) {
		super(type);
	}
	@Override
    public String toString() {
        return "Carte Botte: " + type.getBotte();
    }

}
