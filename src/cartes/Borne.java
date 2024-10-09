package cartes;

public class Borne extends Carte{
	 private int km;

	    public Borne(int km) {
	        this.km = km;
	    }

	    public int getKm() {
	        return km;
	    }

	    @Override
	    public String toString() {
	        return "Borne de " + km + " km";
	    }
	    @Override
	    public boolean equals(Object obj) {
	        // Vérification si l'objet est lui-même
	        if (this == obj) {
	            return true;
	        }
	        // Vérification si l'objet est null ou s'il n'est pas de la même classe
	        if (obj == null || getClass() != obj.getClass()) {
	            return false;
	        }
	        // Comparer le nombre de kilomètres
	        Borne autreBorne = (Borne) obj;
	        return this.km == autreBorne.km;
	    }

		public int getValeur() {
			
			return km;
		}

	    
	    


}
