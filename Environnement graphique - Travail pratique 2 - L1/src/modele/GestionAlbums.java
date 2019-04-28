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
	
	private String rqtSelectAll;
	
	public GestionAlbums () {
		url = "jdbc:sqlite:" + databaseDirectory.getPath();
		
		rqtSelectAll = "SELECT * FROM Albums;";
		
		connexion = null;
		statement = null;
		
		listeAlbums = new ArrayList<Album>();
	}
	
	public ArrayList<Album> getAlbums () {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Pilote chargé\n"); //TODO Retirer une fois l'app terminé
			
			// Établir une connexion à la base de données
			connexion = DriverManager.getConnection(url);
			
			// Créer une zone de déclaration de requête
			statement = connexion.createStatement();
			
			/*
			nbrEnregistrement = statement.executeUpdate(rqtInsertion1);
			nbrEnregistrement = statement.executeUpdate(rqtInsertion2);
			*/
			
			// Traitement des résultats : Affichage de la requête
			jeuResultats = statement.executeQuery(rqtSelectAll);
			System.out.println("Contenu de la table artistes"); // TODO Retirer
			
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
			
		}catch (ClassNotFoundException cnfe) {
			System.out.println("ERREUR : Driver manquant.");
		}catch (SQLException se) {
			System.out.println("ERREUR SQL : " + se);
		}finally {
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
		
		return listeAlbums;
	}
}
