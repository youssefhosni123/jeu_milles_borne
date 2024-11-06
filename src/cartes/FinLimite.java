package cartes;

public class FinLimite extends Limite {
	 public FinLimite() {
		 super(Type.LIMITE);
	    }

	   
		@Override
	    public String toString() {
	    	 return "Fin de Limite de type: " + (type != null ? type.name() : "Aucun type");
	    }
		  public FinLimite(Type type) {
		        super(type);
		    }
}
