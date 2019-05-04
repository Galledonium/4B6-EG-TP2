package controleur;

import modele.Artiste;
import modele.GestionArtistes;

public class ControleurModificationArtiste {
	
	private GestionArtistes gestionnaire;
	
	public ControleurModificationArtiste() {
		gestionnaire = new GestionArtistes();
	}
	
	public void modifierArtiste(Artiste artiste) {
		gestionnaire.modifierArtiste(artiste);
	}
}
