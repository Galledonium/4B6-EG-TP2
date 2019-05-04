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
			e.printStackTrace();
		}
		catch (SQLException e) {
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
		listeArtistes.clear();
		
		try {
			open();
			
			request = "SELECT * FROM Artistes;";
			
			/*
			nbrEnregistrement = statement.executeUpdate(rqtInsertion1);
			nbrEnregistrement = statement.executeUpdate(rqtInsertion2);
			*/
			
			// Traitement des résultats : Affichage de la requête
			jeuResultats = statement.executeQuery(request);
			
			while (jeuResultats.next()) {
				int id = jeuResultats.getInt("id");
				String nom = jeuResultats.getString("nom");
				boolean isMembre = jeuResultats.getBoolean("isMembre");
				String photo = jeuResultats.getString("photo");
				
				Artiste artiste = new Artiste(id, nom, isMembre, photo);
				
				listeArtistes.add(artiste);
			}
		}catch (SQLException se) {
			System.out.println("ERREUR SQL : " + se);
		}finally {
			close();
		}
		
		return listeArtistes;
	}
	
	public void saveImage (Artiste artiste, String path) {
		listeArtistes.clear();
		
		request = "UPDATE Artistes SET photo = \"" + path + "\" WHERE id = " + artiste.getID() + ";";
		
		try {
			open();
			
			statement.executeUpdate(request);
			
			request = "SELECT * FROM Artistes;";
			
			jeuResultats = statement.executeQuery(request);
			
			while(jeuResultats.next()) {
				int id = jeuResultats.getInt("id");
				String nom = jeuResultats.getString("nom");
				boolean isMembre = jeuResultats.getBoolean("isMembre");
				String photo = jeuResultats.getString("photo");
				
				Artiste artisteTemp = new Artiste(id, nom, isMembre, photo);
				
				listeArtistes.add(artisteTemp);
			}
		}catch (SQLException se) {
				System.out.println("ERREUR SQL : " + se);
		}finally {
			close();
		}
	}
	
	public String getImage (Artiste artiste) {
		String chemin = null;
		
		try {
			open();
			
			request = "SELECT photo FROM Artistes WHERE id = " + artiste.getID() + ";";
			
			// Traitement des résultats : Affichage de la requête
			jeuResultats = statement.executeQuery(request);
			
			chemin = jeuResultats.getString("photo");
		}catch (SQLException ex) {
			System.out.println("ERREUR SQL : " + ex);
		}finally {
			close();
		}
		
		return chemin;
	}
	
	public ArrayList<Artiste> rechercherArtiste(String terme){
		listeArtistes.clear();
		
		try {
			open();
			
			request = "SELECT * from Artistes WHERE UPPER(nom) LIKE UPPER('%" + terme + "%');";
			
			jeuResultats = statement.executeQuery(request);
			
			while(jeuResultats.next()) {
				
				int id = jeuResultats.getInt("id");
				String nom = jeuResultats.getString("nom");
				boolean isMembre = jeuResultats.getBoolean("isMembre");
				String photo = jeuResultats.getString("photo");
				
				Artiste artiste = new Artiste(id, nom, isMembre, photo);
				
				listeArtistes.add(artiste);
			}
			
			statement.executeUpdate(request);
			
		} catch (SQLException se) {
			System.out.println("ERREUR SQL : " + se);
		}finally {
			close();
		}
		
		return listeArtistes;
	}
	
	public int getLastIndex() {
		try {
			open();
			
			request = "SELECT * from Artistes WHERE id > " + nbrEnregistrement + ";";
			
			jeuResultats = statement.executeQuery(request);
			
			while(jeuResultats.next()) {
				nbrEnregistrement = jeuResultats.getInt("id");
			}
			
		} catch (SQLException se) {
			System.out.println("ERREUR SQL : " + se);
		}finally {
			close();
		}
		
		return nbrEnregistrement + 1;
	}

	public void addArtiste(Artiste artiste) {
		
		listeArtistes.clear();
		
		request = "INSERT INTO Artistes Values('" 
									+ artiste.getID() + "', '" 
									+ artiste.getNom() + "', '"
									+ (artiste.getMembre() ? 1 : 0) + "', '"
									+ artiste.getPhoto() + "')";
			
		try {
			open();
			
			statement.executeUpdate(request);
			
			listeArtistes.add(artiste);
									
		}catch (SQLException se) {
				System.out.println("ERREUR SQL : " + se);
		}finally {
			close();
		}
	}

	public void modifierArtiste(Artiste artiste) {
		listeArtistes.clear();
		
		request = "UPDATE Artistes SET nom = \"" + artiste.getNom() + "\", isMembre = \"" + (artiste.getMembre() ? 1 : 0) + "\" WHERE id = " + artiste.getID() + ";";
		
		
		
		try {
			open();
			
			statement.executeUpdate(request);
			
			request = "SELECT * FROM Artistes;";
			
			jeuResultats = statement.executeQuery(request);
			
			while(jeuResultats.next()) {
				int id = jeuResultats.getInt("id");
				String nom = jeuResultats.getString("nom");
				boolean isMembre = jeuResultats.getBoolean("isMembre");
				String photo = jeuResultats.getString("photo");
				
				Artiste artisteTemp = new Artiste(id, nom, isMembre, photo);
				
				listeArtistes.add(artisteTemp);
			}
									
		}catch (SQLException se) {
				System.out.println("ERREUR SQL : " + se);
		}finally {
			close();
		}
	}

	public void deleteArtiste(int id) {
		
		request = "DELETE FROM Artistes WHERE id = " + id + ";";

		try {
			open();
			
			statement.executeUpdate(request);
			
		}catch (SQLException se) {
			System.out.println("ERREUR SQL : " + se);
		}finally {
			close();
		}
	}
}
