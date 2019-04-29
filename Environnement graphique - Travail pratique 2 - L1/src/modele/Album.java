package modele;

public class Album {

	private int id;
	private String titre;
	private String genre;
	private int anneeSortie;
	
	public Album(int id, String titre, String genre, int anneeSortie) {
		
		this.id = id;
		this.titre = titre;
		this.genre = genre;
		this.anneeSortie = anneeSortie;
		
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
	
}
