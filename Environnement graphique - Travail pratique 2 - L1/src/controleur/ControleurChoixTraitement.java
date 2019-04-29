package controleur;

import vue.ChoixTraitement;
import vue.GestionArtistes;

public class ControleurChoixTraitement {
	private GestionArtistes fenetreGestionArtistes;
	private ChoixTraitement vue;
	
	public ControleurChoixTraitement (ChoixTraitement vue) {
		fenetreGestionArtistes = new GestionArtistes();
		this.vue = vue;
	}
	
	public void traiterArtiste () {
		fenetreGestionArtistes.setParent(vue);
		fenetreGestionArtistes.setVisible(true);
		vue.setVisible(false);
	}
}
