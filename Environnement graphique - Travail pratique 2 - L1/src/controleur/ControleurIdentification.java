package controleur;

import vue.ChoixTraitement;
import vue.Identification;

public class ControleurIdentification {
	
	private final String username = "SorakaIs";
	private final String password = "TheWorstSupport";
	
	private Identification identification;
	
	private ChoixTraitement fenetreChoixTraitement;
	
	public ControleurIdentification (Identification identification) {
		this.identification = identification;
		fenetreChoixTraitement = new ChoixTraitement();
	}
	
	public void valider () {
		String username = identification.getFormFieldInputsList().get(0).getText();
		String password = identification.getFormFieldInputsList().get(1).getText();
		
		if (username.equals(this.username) && password.equals(this.password)) {
			fenetreChoixTraitement.setVisible(true);
		}
	}
	
	public void quitter() {
		System.exit(0);
	}
}
