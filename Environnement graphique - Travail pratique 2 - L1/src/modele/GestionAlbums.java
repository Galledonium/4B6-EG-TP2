package modele;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionAlbums {
private String url;
	
	private File databaseDirectory = new File("sqlite/db/bibliotheque.db");
	
	private Connection connexion;
	private Statement statement;
	private ResultSet jeuResultats;
	
	private int nbrEnregistrement;
	
	private ArrayList<Album> listeAlbums;
	
	private String request;
	
	public GestionAlbums () {
		url = "jdbc:sqlite:" + databaseDirectory.getPath();
		
		request = "";
		
		connexion = null;
		statement = null;
		
		listeAlbums = new ArrayList<Album>();
		
		open();
	}
	
	private void open() {
			
		try {
			
			Class.forName("org.sqlite.JDBC");
			connexion = DriverManager.getConnection(url);
			
			statement = connexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void close() {
		
		try {
			if (statement != null) {
				statement.close();
			}
				
			if (connexion != null) {
				connexion.close();
			}
		}catch (SQLException se) {
			System.out.println("ERREUR : " + se);
		}
	
	}
	
	public ArrayList<Album> getAlbums (int idArtiste) {
		try {
			
			open();
			
			request = "SELECT * FROM Albums WHERE idArtiste = " + idArtiste + ";";
			
			jeuResultats = statement.executeQuery(request);
			
			while (jeuResultats.next()) {
				int id = jeuResultats.getInt("id");
				String titre = jeuResultats.getString("titre");
				String genre = jeuResultats.getString("genre");
				int anneeSortie = jeuResultats.getInt("anneeSortie");
				
				Album album = new Album(id, titre, genre, anneeSortie);
				
				listeAlbums.add(album);
				
				System.out.println(id + titre + genre + anneeSortie); // TODO
			}
			
			// System.out.println("\n" + nbrEnregistrement);
			System.out.println("\nConnexion établie"); // TODO Retirer
			
		}catch (SQLException se) {
			System.out.println("ERREUR SQL : " + se);
		}finally {
			
			close();
			
		}
		
		return listeAlbums;
	}
}
