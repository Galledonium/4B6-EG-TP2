package controleur;

import vue.ChoixTraitement;
import vue.Identification;

public class ControleurIdentification {
	
	private final String username = "username";
	private final String password = "password";
	
	private Identification identification;
	
	private ChoixTraitement fenetreChoixTraitement;
	
	public ControleurIdentification (Identification identification) {
		this.identification = identification;
		fenetreChoixTraitement = new ChoixTraitement();
	}
	
	public void valider () {
		String username = identification.getUsername();
		String password = "";
		
		for (int indice = 0; indice < identification.getPassword().length; indice++) {
			password += identification.getPassword()[indice];
		}
		
		if (username.equals(this.username) && password.equals(this.password)) {
			fenetreChoixTraitement.setVisible(true);
		}
	}
	
	public void quitter() {
		System.exit(0);
	}
}
