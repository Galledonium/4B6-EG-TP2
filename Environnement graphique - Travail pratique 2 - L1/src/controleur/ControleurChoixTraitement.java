package controleur;

import vue.ChoixTraitement;
import vue.GestionArtistes;

public class ControleurChoixTraitement {
	
	private ChoixTraitement choixTraitement;
	
	private GestionArtistes fenetreGestionArtistes;
	
	public ControleurChoixTraitement (ChoixTraitement choixTraitement) {
		this.choixTraitement = choixTraitement;
		
		fenetreGestionArtistes = new GestionArtistes();
	}
	
	public void traiterArtiste () {
		fenetreGestionArtistes.setVisible(true);
	}
	
}
