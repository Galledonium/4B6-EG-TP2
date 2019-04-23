package bd;

import java.sql.SQLException;
import java.util.ArrayList;

public class Executable {

	public static void main(String[] args) throws SQLException {
			
		ArrayList<Artiste> artistes = new ArrayList<>();
		ArrayList<Album> albums = new ArrayList<>();
		
		Artiste artiste = new Artiste("1", "Allo Bibi", "true");
		Album album = new Album("1", "Test", "Jazz");
		
		artistes.add(artiste);
		albums.add(album);
		
		GestionDonnees insertion = new GestionDonnees(artistes, albums);
		
		insertion.afficherResultats();

	}

}
