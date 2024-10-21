package cartes;

public abstract class Carte {
	
	protected Type type;
  

	public Type getType() {
		return type;
	}
	
	
	 
  
	public void setType(Type type) {
		this.type = type;
	}
	@Override 
	public boolean equals(Object obj) {
	    // Vérification si l'objet est lui-même
	    if (this == obj) {
	        return true;
	    }
	    // Vérification si l'objet est une instance de la classe Carte
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    // Comparaison des types de cartes
	    Carte autreCarte = (Carte) obj;

	    // Vérifier si le type est null avant d'appeler equals
	    if (this.type == null && autreCarte.type == null) {
	        return true; // Les deux types sont null, donc égaux
	    }
	    if (this.type == null || autreCarte.type == null) {
	        return false; // Un seul des deux est null, donc pas égaux
	    }
	    
	    // Comparer les deux types
	    return this.type.equals(autreCarte.type);
	}
	  @Override
	    public int hashCode() {
	        return (type != null) ? type.hashCode() : 0;
	    }

	    @Override
	    public String toString() {
	        return "Carte de type : " + type;
	    }


}
