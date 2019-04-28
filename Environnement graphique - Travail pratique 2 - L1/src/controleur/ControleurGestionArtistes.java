package controleur;

import java.util.ArrayList;
import modele.Artiste;
import modele.GestionArtistes;

public class ControleurGestionArtistes {
	
	private GestionArtistes gestionnaire;
	
	public ControleurGestionArtistes() {
		gestionnaire = new GestionArtistes();
	}
	
	public ArrayList<Artiste> getListeArtistes () {
		return gestionnaire.getArtistes();
	}
}
