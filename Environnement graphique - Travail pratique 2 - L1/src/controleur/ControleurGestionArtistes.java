package controleur;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import modele.Album;
import modele.Artiste;
import modele.GestionDonnees;
import vue.GestionArtistes;

public class ControleurGestionArtistes {

	private GestionArtistes gestionArtistes;
	private GestionDonnees gestionDonnees;
	
	private Artiste artiste;
	private Album album;
	
	private ArrayList<Artiste> listeArtistes;
	private ArrayList<Album> listeAlbums;
	
	
	public ControleurGestionArtistes(GestionArtistes gestionArtistes) {
		
		this.gestionArtistes = gestionArtistes;
		gestionDonnees = new GestionDonnees(this);
		
	}
	
	public void setArtiste() {
		
		String id = ((JTextField) gestionArtistes.getElementList().get(0)).getText();
		String nomArtiste = ((JTextField) gestionArtistes.getElementList().get(1)).getText();
		int isMembre = ((JCheckBox) gestionArtistes.getElementList().get(2)).isSelected() ? 1 : 0;
		
		artiste = new Artiste(id, nomArtiste, isMembre);
	}
	
	public ArrayList<Artiste> getListeArtistes() {
		
		gestionDonnees.getArtistes();
		
		return this.listeArtistes;
		
	}
	
	public void setListeArtistes(ArrayList<Artiste> artistes) {
		
		this.listeArtistes = artistes;
		
	}
	
}
