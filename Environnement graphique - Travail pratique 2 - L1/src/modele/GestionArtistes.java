package modele;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionArtistes {
	private String url;
	
	private File databaseDirectory = new File("sqlite/db/bibliotheque.db");
	
	private Connection connexion;
	private Statement statement;
	private ResultSet jeuResultats;
	
	private int nbrEnregistrement;
	
	private ArrayList<Artiste> listeArtistes;
	
	private String rqtSelectAll;
	
	public GestionArtistes () {
		url = "jdbc:sqlite:" + databaseDirectory.getPath();
		
		rqtSelectAll = "SELECT * FROM Artiste;";
		
		connexion = null;
		statement = null;
		
		listeArtistes = new ArrayList<Artiste>();
	}
	
	public ArrayList<Artiste> getArtistes () {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Pilote charg�\n"); //TODO Retirer une fois l'app termin�
			
			// �tablir une connexion � la base de donn�es
			connexion = DriverManager.getConnection(url);
			
			// Cr�er une zone de d�claration de requ�te
			statement = connexion.createStatement();
			
			/*
			nbrEnregistrement = statement.executeUpdate(rqtInsertion1);
			nbrEnregistrement = statement.executeUpdate(rqtInsertion2);
			*/
			
			// Traitement des r�sultats : Affichage de la requ�te
			jeuResultats = statement.executeQuery(rqtSelectAll);
			System.out.println("Contenu de la table artistes"); // TODO Retirer
			
			while (jeuResultats.next()) {
				int id = jeuResultats.getInt("id");
				String nom = jeuResultats.getString("nom");
				boolean isMembre = jeuResultats.getBoolean("isMembre");
				String photo = jeuResultats.getString("photo");
				
				Artiste artiste = new Artiste(id, nom, isMembre, photo);
				
				listeArtistes.add(artiste);
				
				System.out.println("ID : " + id + "\tNom : " + nom + "\tEstMembre : " + isMembre + "\tPhoto : " + photo); // TODO
			}
			
			// System.out.println("\n" + nbrEnregistrement);
			System.out.println("\nConnexion �tablie"); // TODO Retirer
			
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
		
		return listeArtistes;
	}
}
