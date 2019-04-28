package modele;

public class Album {

	private String id;
	private String titre;
	private String genre;
	
	public Album(String id, String titre, String genre) {
		
		this.id = id;
		this.titre = titre;
		this.genre = genre;
		
	}
	
	public void setTitre(String titre) {
		
		this.titre = titre;
		
	}
	
	public void setGenre(String genre) {
		
		this.genre = genre;
		
	}
	
	public String getID() {
		
		return this.id;
		
	}
	
	public String getTitre() {
		
		return this.titre;
		
	}
	
	public String getGenre() {
		
		return this.genre;
		
	}
	
}
