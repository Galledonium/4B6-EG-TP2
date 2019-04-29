package controleur;

import modele.GestionArtistes;

public class ControleurModificationArtiste {
	
	private GestionArtistes gestionnaire;
	
	public ControleurModificationArtiste() {
		
		gestionnaire = new GestionArtistes();
		
	}
	
	public void modifierArtiste(String nom, boolean isMembre) {
		
		gestionnaire.modifierArtiste(nom, isMembre);
		
	}
	
}
