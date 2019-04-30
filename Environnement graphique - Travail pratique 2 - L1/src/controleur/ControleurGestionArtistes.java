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
	
	public void deleteArtiste(int idArtiste) {
		
		gestionnaireArtistes.deleteArtiste(idArtiste);
		
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
	
	public void modifier(vue.GestionArtistes vue, Artiste artiste){
		modification = new ModificationArtiste(vue, artiste);
		modification.setVisible(true);
	}
	
	public ArrayList<Artiste> rechercherArtiste(String terme){
		
		return gestionnaireArtistes.rechercherArtiste(terme);
		
	}
}
