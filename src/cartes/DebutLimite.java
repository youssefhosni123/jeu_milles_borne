package cartes;

public class DebutLimite extends Limite{
	public DebutLimite(Type type) {
        super(type);
    }

    @Override
    public String toString() {
        return "Début de Limite de type: " + type;
    }

}
