package modele;

public class Artiste {
	
	private int id;
	private String nom;
	private boolean isMembre;
	private String photo;
	
	public Artiste() {
		
	}
	
	public Artiste(int id, String nom, boolean isMembre, String photo) {
		
		this.id = id;
		this.nom = nom;
		this.isMembre = isMembre;
		this.photo = photo;
		
	}
	
	// Vérificateurs
	public void isMembre(boolean valeur) {
		
		this.isMembre = valeur;
		
	}
	
	public void isMembre(int valeur) {
		
		this.isMembre = valeur == 1 ? true : false;
		
	}
	
	// Accesseurs
	public int getID() {
		
		return this.id;
		
	}
	
	public String getNom() {
		
		return this.nom;
		
	}
	
	public boolean getMembre() {
		
		return this.isMembre;
		
	}
	
	public String getPhoto (){
		
		return this.photo;
		
	}
	
	// Mutateurs
	public void setNom(String nomArtiste) {
		
		this.nom = nomArtiste;
		
	}
}
