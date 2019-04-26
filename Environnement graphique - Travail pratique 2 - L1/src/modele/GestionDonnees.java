package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.ControleurGestionArtistes;

public class GestionDonnees {

	private ControleurGestionArtistes controleurArtistes;
	
	private Connection connexion;
	private Statement statement;
	private ResultSet jeuResultats;
	private String url = "jdbc:sqlite:sqlite/db/dbBibliotheque.db";
	
	private Artiste artiste;
	private ArrayList<Artiste> liste;
	
	public GestionDonnees(ControleurGestionArtistes controleur) {
		
		this.controleurArtistes = controleur;
		
		liste = new ArrayList<>();
		
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
	
	
	public void getArtistes() {
		
		String requete = "SELECT * from Artistes;";
		
		try {
			
			System.out.println("1");
			
			jeuResultats = statement.executeQuery(requete);
			
			while(jeuResultats.next()) {
				
				String id = jeuResultats.getString("id");
				String nomArtiste = jeuResultats.getString("nomArtiste");
				int isMembre = Integer.parseInt(jeuResultats.getString("isMembre"));
							
				artiste = new Artiste(id, nomArtiste, isMembre);
				
				liste.add(artiste);	
				
				System.out.println(artiste.getNomArtiste());
			}
			
			controleurArtistes.setListeArtistes(liste);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
}
