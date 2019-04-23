package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionDonnees {
	
	private ArrayList<Artiste> artistes;
	private ArrayList<Album> albums;
	private Connection connexion;
	private Statement statement;
	private ResultSet jeuResultats;
	private String url = "jdbc:sqlite:sqlite/db/dbBibliotheque.db";
	
	public GestionDonnees() {
		
		connexion = null;
		statement = null;
		
	}
	
	public GestionDonnees(ArrayList<Artiste> artistes, ArrayList<Album> albums) {
		
		this.artistes = artistes;
		this.albums = albums;
		connexion = null;
		statement = null;
		
		try {
			this.insererDonneesArtiste();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			this.insererDonneesAlbums();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setTabArtiste(ArrayList<Artiste> artistes) {
		
		this.artistes = artistes;
		
		try {
			this.insererDonneesArtiste();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void setTabAlbum(ArrayList<Album> albums) {
		
		this.albums = albums;
		
		try {
			this.insererDonneesAlbums();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void insererDonneesArtiste() throws SQLException {
		
		String sqlInsertStatement;
		
		for(Artiste artiste : this.artistes) {
			
			sqlInsertStatement = "INSERT INTO Artistes Values('" + artiste.getID() + "', '" + artiste.getNomArtiste() + "', '" + artiste.getMembre() + "')";
			
			try {
				
				Class.forName("org.sqlite.JDBC");
				
				// Établir une connexion à la base de données
				connexion = DriverManager.getConnection(url);
				
				// Créer une zone de déclaration de requête
				statement = connexion.createStatement();
				
				statement.executeUpdate(sqlInsertStatement);
							
				
			}catch (ClassNotFoundException cnfe) {
				System.out.println("ERREUR : Driver manquant.");
			}catch (SQLException se) {
				System.out.println("ERREUR SQL : " + se);
			}
			
		}
		
		
	}
	
	private void insererDonneesAlbums() throws SQLException{
		
		String sqlInsertStatement;
		
		for(Album album : this.albums) {
			
			sqlInsertStatement = "INSERT INTO Albums Values('" + album.getID() + "', '" + album.getTitre() + "', '" + album.getGenre() + "')";
			
			try {
				
				Class.forName("org.sqlite.JDBC");
				
				// Établir une connexion à la base de données
				connexion = DriverManager.getConnection(url);
				
				// Créer une zone de déclaration de requête
				statement = connexion.createStatement();
				
				statement.executeUpdate(sqlInsertStatement);
							
				
			}catch (ClassNotFoundException cnfe) {
				System.out.println("ERREUR : Driver manquant.");
			}catch (SQLException se) {
				System.out.println("ERREUR SQL : " + se);
			}
			
		}
	}

	public void afficherResultats() throws SQLException {
		
		String rqtAffichage1 = "SELECT * FROM Artistes";
		String rqtAffichage2 = "SELECT * FROM Albums";
		
		// Traitement des résultats : Affichage de la requête
		jeuResultats = statement.executeQuery(rqtAffichage1);
		System.out.println("Contenu de la table artistes");
		while (jeuResultats.next()) {
			String id = jeuResultats.getString("id");
			String nomArtiste = jeuResultats.getString("nomArtiste");
			String isMembre = jeuResultats.getString("isMembre");
			
			System.out.println("Num\u00E9ro: " + id + "\nNom de l'artiste : " + nomArtiste + "\nMembre? : " + (isMembre == "true" ? "Oui\n" : "Non\n"));
		}
		
		jeuResultats = statement.executeQuery(rqtAffichage2);
		System.out.println("Contenu de la table albums");
		while (jeuResultats.next()) {
			String id = jeuResultats.getString("id");
			String titre = jeuResultats.getString("titre");
			String genre = jeuResultats.getString("genre");
			
			System.out.println("Num\u00E9ro: " + id + "\nTitre de l'album : " + titre + "\nGenre : " + genre + "\n\n");
		}
		
	}
	
	
}
