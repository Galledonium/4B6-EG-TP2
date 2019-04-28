package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import controleur.ControleurGestionArtistes;

public class GestionDonnees {
	
	private Connection connexion;
	private Statement statement;
	private ResultSet jeuResultats;
	private String url = "jdbc:sqlite:sqlite/bibliotheque.db";
	
	private Artiste artiste;
	private Vector<Artiste> listeArtistes;
	private ArrayList<Album> listeAlbums;
	
	public GestionDonnees() {
		
		listeArtistes = new Vector<>();
		listeAlbums = new ArrayList<>();
		
		connexion = null;
		statement = null;
		
		try {
			connexionBD();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void connexionBD() throws SQLException {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			
			connexion = DriverManager.getConnection(url);
			
			statement = connexion.createStatement();
			
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
				System.out.println("ERREUR : Driver manquant.");
			}catch (SQLException se) {
				System.out.println("ERREUR SQL : " + se);
			}	
		
	}
	
	public void ajouterArtiste() {
		
		
		
	}
	
	public Vector<Artiste> getArtistes() {
		
		String requete = "SELECT * from Artistes;";
		
		listeArtistes.clear();
		
		try {
			
			jeuResultats = statement.executeQuery(requete);
			
			while(jeuResultats.next()) {
				
				int id = Integer.parseInt(jeuResultats.getString("id"));
				String nomArtiste = jeuResultats.getString("nom");
				boolean isMembre = jeuResultats.getBoolean("isMembre");
				String url = "images/default.png";
							
				artiste = new Artiste(id, nomArtiste, isMembre, url);
				
				listeArtistes.add(artiste);	
				
				System.out.println(artiste.getNom());
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeArtistes;
		
	}
	
	public ArrayList<Album> getAlbums(int idArtiste){
		
		String requete = "SELECT * from Albums WHERE idArtiste = " + Integer.toString(idArtiste) + ";";
		
		listeAlbums.clear();
		
		try {
			
			jeuResultats = statement.executeQuery(requete);
			
			while(jeuResultats.next()) {
				
				/*int id = jeuResultats.getInt("id");
				String nomArtiste = jeuResultats.getString("nomArtiste");
				boolean isMembre = jeuResultats.getBoolean("isMembre");
				String url = "images/default.png";
							
				artiste = new Artiste(id, nomArtiste, isMembre, url);
				
				listeAlbums.add(artiste); TODO*/	
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeAlbums;
		
	}
	
	public ArrayList<Artiste> rechercher(){
		
		return null; //TODO A completer
		
	}
	
}
