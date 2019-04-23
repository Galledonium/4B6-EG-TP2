package vue;

import javax.swing.JFrame;

public class GestionArtistes extends JFrame {
	private static final long serialVersionUID = 1L;

	public GestionArtistes() {
		setTitle("Gestion des Artistes");
		setSize(1000, 500);
	}
	
	public static void main(String[] args) {
		GestionArtistes fenetre = new GestionArtistes();
		fenetre.setVisible(true);
	}

}
