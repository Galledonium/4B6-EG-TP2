package controleur;

import java.util.ArrayList;

import modele.Album;
import modele.Artiste;
import modele.GestionAlbums;
import modele.GestionArtistes;

public class ControleurGestionArtistes {
	
	private GestionArtistes gestionnaireArtistes;
	private GestionAlbums gestionnaireAlbums;
	
	public ControleurGestionArtistes() {
		gestionnaireArtistes = new GestionArtistes();
		gestionnaireAlbums = new GestionAlbums();
	}
	
	public ArrayList<Artiste> getListeArtistes () {
		return gestionnaireArtistes.getArtistes();
	}
	
	public ArrayList<Album> getListeAlbums(){
		
		return gestionnaireAlbums.getAlbums();
		
	}
}
