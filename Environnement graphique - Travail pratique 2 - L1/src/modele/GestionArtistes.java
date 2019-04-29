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
	
	private String request;
	
	public GestionArtistes () {
		url = "jdbc:sqlite:" + databaseDirectory.getPath();
		
		request = "";
		
		connexion = null;
		statement = null;
		
		nbrEnregistrement = 0;
		
		listeArtistes = new ArrayList<Artiste>();
		
		open();
	}
	
	private void open() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			connexion = DriverManager.getConnection(url);
			
			statement = connexion.createStatement();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Pilote chargé\n"); //TODO Retirer une fois l'app terminé
		catch (SQLException e) {
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

	public ArrayList<Artiste> getArtistes () {
		try {
			
			this.open();
			
			request = "SELECT * FROM Artistes;";
			
			/*
			nbrEnregistrement = statement.executeUpdate(rqtInsertion1);
			nbrEnregistrement = statement.executeUpdate(rqtInsertion2);
			*/
			
			// Traitement des résultats : Affichage de la requête
			jeuResultats = statement.executeQuery(request);
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
			System.out.println("\nConnexion établie"); // TODO Retirer
			
		}catch (SQLException se) {
			System.out.println("ERREUR SQL : " + se);
		}finally {
			
			this.close();
			
		}
		
		return listeArtistes;
	}
	
	public int getLastIndex() {
		
		
		
		try {
			
			this.open();
			
			request = "SELECT * from Artistes WHERE id > " + nbrEnregistrement + ";";
			
			jeuResultats = statement.executeQuery(request);
			
			while(jeuResultats.next()) {
				
				nbrEnregistrement = jeuResultats.getInt("id");
				
			}
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR SQL : " + se);
		}finally {
			
			this.close();
			
		}
		
		return nbrEnregistrement + 1;
			
	}

	public void addArtiste(Artiste artiste) {
		
		String sqlInsertStatement = "INSERT INTO Artistes Values('" 
									+ artiste.getID() + "', '" 
									+ artiste.getNom() + "', '"
									+ (artiste.getMembre() ? 1 : 0) + "', '"
									+ artiste.getPhoto() + "')";
			
		try {
			
			this.open();
				
			statement = connexion.createStatement();
			
			statement.executeUpdate(sqlInsertStatement);
			
			listeArtistes.add(artiste);
									
		}catch (SQLException se) {
				System.out.println("ERREUR SQL : " + se);
		}finally {
			
			this.close();
			
		}
			
	}
}
