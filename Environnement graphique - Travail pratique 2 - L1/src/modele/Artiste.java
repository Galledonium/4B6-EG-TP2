package modele;

public class Artiste {
	
	private String id;
	private String nomArtiste;
	private Boolean isMembre;
	
	public Artiste() {
		
		
		
	}
	
	//Puisque SQLite n'a pas de valeur booleene, le constructeur recoit une valeur INTEGER en parametre et la convertie en booleen. (0 pour false et 1 pour true)
	
	public Artiste(String id, String nomArtiste, int isMembre) {
		
		this.id = id;
		this.nomArtiste = nomArtiste;
		this.isMembre = isMembre == 1 ? true : false;
		
	}
	
	public void setNomArtiste(String nomArtiste) {
		
		this.nomArtiste = nomArtiste;
		
	}
	
	public void isMembre(boolean valeur) {
		
		this.isMembre = valeur;
		
	}
	
	public void isMembre(int valeur) {
		
		this.isMembre = valeur == 1 ? true : false;
		
	}
	
	public String getID() {
		
		return this.id;
		
	}
	
	public String getNomArtiste() {
		
		return this.nomArtiste;
		
	}
	
	public boolean getMembre() {
		
		return this.isMembre;
		
	}
	

}
