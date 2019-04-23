package bd;

public class Artiste {
	
	private String id;
	private String nomArtiste;
	private String isMembre;
	
	public Artiste(String id, String nomArtiste, String isMembre) {
		
		this.id = id;
		this.nomArtiste = nomArtiste;
		this.isMembre = isMembre;
		
	}
	
	public String getID() {
		
		return this.id;
		
	}
	
	public String getNomArtiste() {
		
		return this.nomArtiste;
		
	}
	
	public String getMembre() {
		
		return this.isMembre;
		
	}

}
