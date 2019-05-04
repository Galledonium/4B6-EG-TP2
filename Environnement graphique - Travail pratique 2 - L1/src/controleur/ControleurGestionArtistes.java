package controleur;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modele.Album;
import modele.Artiste;
import modele.GestionAlbums;
import modele.GestionArtistes;
import vue.ModificationArtiste;

public class ControleurGestionArtistes {
	
	private GestionArtistes gestionnaireArtistes;
	private GestionAlbums gestionnaireAlbums;
	
	private JFileChooser file;
	File chosenFile;
	
	Path sourceDirectory;
	Path targetDirectory;
	
	private String chemin;
	
	public ControleurGestionArtistes() {
		gestionnaireArtistes = new GestionArtistes();
		gestionnaireAlbums = new GestionAlbums();
		
		chemin = "";
		
		chosenFile = null;
		
		sourceDirectory = null;
		targetDirectory = null;
		
		file = new JFileChooser();
	}
	
	public void addArtiste(Artiste artiste) {
		gestionnaireArtistes.addArtiste(artiste);
	}
	
	public void deleteArtiste(int idArtiste) {
		gestionnaireArtistes.deleteArtiste(idArtiste);
		gestionnaireAlbums.deleteAlbum(idArtiste);
	}
	
	public ArrayList<Artiste> getListeArtistes () {
		return gestionnaireArtistes.getArtistes();
	}
	
	public ArrayList<Album> getListeAlbums (int idArtiste) {
		return gestionnaireAlbums.getAlbums(idArtiste);
	}
	
	public int getLastIndex() {
		return gestionnaireArtistes.getLastIndex();
	}
	
	public void modifier(vue.GestionArtistes vue, Artiste artiste){
		if (vue.getChildModificationArtiste() != null) {
			vue.getChildModificationArtiste().dispose();
		}
		vue.setChildModificationArtiste(new ModificationArtiste(vue, artiste));
		vue.getChildModificationArtiste().setVisible(true);
	}
	
	public ArrayList<Artiste> rechercherArtiste(String terme){
		return gestionnaireArtistes.rechercherArtiste(terme);
	}
	
	public void afficherImageArtiste (Artiste artiste, JLabel label) {
		String path = gestionnaireArtistes.getImage(artiste);
		
		ImageIcon imageIcon = new ImageIcon(path);
		
		Image img = imageIcon.getImage();
		Image imageResized = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		
		ImageIcon image = new ImageIcon(imageResized);
		
		label.setIcon(image);
	}
	
	public boolean changerImage (JLabel label) {
		boolean isChosen = false;
		
		// Sélection du fichier
		file.setCurrentDirectory(new File (System.getProperty("user.home")));
		
		// Filtreur de fichier : Seulement les fichiers images
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image", "jpg", "png");
		file.setFileFilter(filter);
		file.addChoosableFileFilter(filter);
		
		int result = file.showSaveDialog(null); // Retourne 0 lors d'un success
		
		chosenFile = file.getSelectedFile();
		String chosenFileName = result == 0 ? chosenFile.getName() : "";
		
		if (chosenFileName.endsWith(".jpg") || chosenFileName.endsWith(".JPG") || chosenFileName.endsWith(".png") || chosenFileName.endsWith(".PNG")) {
			if (result == JFileChooser.APPROVE_OPTION) { // 0 == 0 => JFileChooser.APPROVE_OPTION retourne 0
				
				// Code pour ouvrir une image
				String path = chosenFile.getAbsolutePath();
				
				chemin = path;
				
				ImageIcon chosenImage = new ImageIcon(path);
				
				Image img = chosenImage.getImage();
				Image imageResized = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
				
				ImageIcon image = new ImageIcon(imageResized);
				
				label.setIcon(image); // Afficher l'image dans le label
				
				isChosen = true;
			}
		}else{
			JOptionPane.showMessageDialog(null, "Choisissez une image SVP", "Erreur de sélection", 1);
		}
		
		return isChosen;
		
	}
	
	public void saveImage(Artiste artiste) {
		System.out.println(chemin + " " + artiste.getNom());
		
		sourceDirectory = Paths.get(chemin);
        targetDirectory = Paths.get("src", "images", "artistes", chosenFile.getName());
        
        System.out.println("Source : " + sourceDirectory);
        System.out.println("Destination : " + targetDirectory);
        
        // Copie un fichier à un emplacement et le colle à un autre
        try {
			Files.copy(sourceDirectory, targetDirectory, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // Supprimer ancien fichier
        try {
			Files.deleteIfExists((Path) Paths.get("src\\images\\artistes\\" + Integer.toString(artiste.getID()) + "." + getFileExtension(targetDirectory.toString())));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        String savePath = null;
        
        // Renommer le fichier
        try {
			File oldFile = new File(targetDirectory.toString());
        	File newFile = new File("src\\images\\artistes\\" + Integer.toString(artiste.getID()) + "." + getFileExtension(targetDirectory.toString()));
        	
        	if (newFile.exists()) {
        		throw new java.io.IOException("File exists");
        	}
        	
        	boolean success = oldFile.renameTo(newFile);
        	
        	if (!success) {
        		System.out.println("File was not successfully renamed");
        	}
        	
        	savePath = newFile.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.println(targetDirectory.toString());
        
		gestionnaireArtistes.saveImage(artiste, savePath);
	}
	
	private String getFileExtension (String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		
		return extension;
	}
	
	public void afficherAlbum (Album album, JLabel label) {
		String path = gestionnaireAlbums.getImage(album);
		
		ImageIcon imageIcon = new ImageIcon(path);
		
		Image img = imageIcon.getImage();
		Image imageResized = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		
		ImageIcon image = new ImageIcon(imageResized);
		
		label.setIcon(image);
	}
}
