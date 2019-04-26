package controleur;

import vue.GestionArtistes;

public class ControleurChoixTraitement {
	
	
	private GestionArtistes fenetreGestionArtistes;
	
	public ControleurChoixTraitement () {
		
		fenetreGestionArtistes = new GestionArtistes();
	}
	
	public void traiterArtiste () {
		fenetreGestionArtistes.setVisible(true);
	}
	
}
