package modele;

public class Album {

	private int id;
	private String titre;
	private String genre;
	private int anneeSortie;
	private String photo;
	
	public Album(int id, String titre, String genre, int anneeSortie, String photo) {
		this.id = id;
		this.titre = titre;
		this.genre = genre;
		this.anneeSortie = anneeSortie;
		this.photo = photo;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setAnneeSortie(int anneeSortie) {
		this.anneeSortie = anneeSortie;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public int getAnneeSortie() {
		return this.anneeSortie;
	}
	
	public String getPhoto () {
		return this.photo;
	}
}
