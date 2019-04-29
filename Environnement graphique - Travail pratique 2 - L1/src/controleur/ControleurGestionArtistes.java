package controleur;

import java.util.ArrayList;

import modele.Album;
import modele.Artiste;
import modele.GestionAlbums;
import modele.GestionArtistes;
import vue.ModificationArtiste;

public class ControleurGestionArtistes {
	
	private GestionArtistes gestionnaireArtistes;
	private GestionAlbums gestionnaireAlbums;
	private ModificationArtiste modification;
	
	public ControleurGestionArtistes() {
		gestionnaireArtistes = new GestionArtistes();
		gestionnaireAlbums = new GestionAlbums();
	}
	
	public void addArtiste(Artiste artiste) {
		
		gestionnaireArtistes.addArtiste(artiste);
		
	}
	
	public ArrayList<Artiste> getListeArtistes () {
		return gestionnaireArtistes.getArtistes();
	}
	
	public ArrayList<Album> getListeAlbums (int idArtiste) {
		return gestionnaireAlbums.getAlbums(idArtiste);
	}
	
	public int getLastIndex() {
		
		return gestionnaireArtistes.getLastIndex();
		
	}
	
	public void modifier(vue.GestionArtistes vue){
		modification = new ModificationArtiste(vue);
		modification.setVisible(true);
	}
}
