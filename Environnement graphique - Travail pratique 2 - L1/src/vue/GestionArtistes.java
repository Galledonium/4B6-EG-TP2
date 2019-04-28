package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import controleur.ControleurGestionArtistes;
import modele.ModeleArtiste;

public class GestionArtistes extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblRecherche;
	private JLabel lblArtistes;
	private JLabel lblInformations;
	private JLabel lblImageArtist;
	private JLabel lblNumero;
	private JLabel lblNom;
	private JLabel lblMembre;
	
	private JCheckBox checkBoxMembre;
	
	private JTextField txtRechercher;
	private JTextField txtNumero;
	private JTextField txtNom;
	
	private JButton btnRechercher;
	private JButton btnQuitter;
	private JButton btnRemplacer;
	private JButton btnNouveau;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	
	private JScrollPane artistTableScrollPane;
	private ModeleArtiste artistTableModel;
	private JTable tableArtistes;
	
	private ArrayList<JComponent> listeElements;
	
	private ControleurGestionArtistes controleur;
	
	private JList<String> listeAlbums;
	
	private int defaultStartingX;
	private int defaultStartingY;
	
	public GestionArtistes() {
		setTitle("Gestion des Artistes");
		setSize(1100, 810);
		getContentPane().setLayout(null);
		centerWindow();
		
		defaultStartingX = 40;
		defaultStartingY = 20;
		
		controleur = new ControleurGestionArtistes();
		
		lblRecherche = new JLabel();
		lblArtistes = new JLabel();
		lblInformations = new JLabel();
		lblImageArtist = new JLabel();
		lblImageArtist = new JLabel();
		lblNumero = new JLabel();
		lblNom = new JLabel();
		lblMembre = new JLabel();
		
		checkBoxMembre = new JCheckBox();
		checkBoxMembre.setEnabled(false);
		
		txtRechercher = new JTextField();
		
		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		
		txtNom = new JTextField();
		txtNom.setEditable(false);
		
		btnRechercher = new JButton();
		btnQuitter = new JButton();
		btnRemplacer = new JButton();
		btnNouveau = new JButton();
		btnNouveau.addMouseListener(new ButtonListener());
		btnAjouter = new JButton();
		btnAjouter.addMouseListener(new ButtonListener());
		btnModifier = new JButton();
		btnSupprimer = new JButton();
		
		listeAlbums = new JList<String>();
		listeElements = new ArrayList<JComponent>();
		
		artistTableModel = new ModeleArtiste(controleur.getListeArtistes());
		tableArtistes = new JTable(artistTableModel);
		tableArtistes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		artistTableScrollPane = new JScrollPane(tableArtistes);
		
		listeElements.add(txtNumero);
		listeElements.add(txtNom);
		listeElements.add(checkBoxMembre);
		listeElements.add(txtRechercher);
		
		buildInterface();
	}
	
	private void buildInterface () {
		buildSearchZone();
		buildArtistsZone();
		buildInformationsZone();
		addDefaultControlerButtons();
	}
	
	private void buildSearchZone () {
		lblRecherche.setText("Rechercher un artiste");
		lblRecherche.setFont(new Font("Arial", Font.PLAIN, 22));
		lblRecherche.setBounds(defaultStartingX, defaultStartingY, 370, 45);
		
		getContentPane().add(lblRecherche);
		
		txtRechercher.setBounds(defaultStartingX, defaultStartingY + 45, 370, 35);
		
		getContentPane().add(txtRechercher);
		
		btnRechercher.setText("Rechercher");
		btnRechercher.setBounds(defaultStartingX + 380, defaultStartingY + 45, 150, 35);
		
		getContentPane().add(btnRechercher);
	}
	
	private void addDefaultControlerButtons () {
		btnQuitter.setText("Quitter");
		btnQuitter.setBounds(defaultStartingX + 840, defaultStartingY + 45, 150, 35);
		
		getContentPane().add(btnQuitter);
		
		btnNouveau.setText("Nouveau");
		btnNouveau.setBounds(defaultStartingX + 840, defaultStartingY + 160, 150, 35);
		
		getContentPane().add(btnNouveau);
		
		btnAjouter.setText("Ajouter");
		btnAjouter.setBounds(defaultStartingX + 840, defaultStartingY + 232, 150, 35);
		
		getContentPane().add(btnAjouter);
		
		btnModifier.setText("Modifier");
		btnModifier.setBounds(defaultStartingX + 840, defaultStartingY + 304, 150, 35);
		
		getContentPane().add(btnModifier);
		
		btnSupprimer.setText("Supprimer");
		btnSupprimer.setBounds(defaultStartingX + 840, defaultStartingY + 376, 150, 35);
		
		getContentPane().add(btnSupprimer);
	}
	
	private void buildArtistsZone () {
		lblArtistes.setText("Artistes");
		lblArtistes.setFont(new Font("Arial", Font.BOLD, 30));
		lblArtistes.setBounds(defaultStartingX, defaultStartingY + 110, 370, 35);
		
		getContentPane().add(lblArtistes);
		
		// TODO Ajouter le label lblImageArtist qui permet d'Afficher l'image d'un artiste sélectionné
		
		btnRemplacer.setText("Remplacer");
		btnRemplacer.setBounds(defaultStartingX, defaultStartingY + 330, 115, 35);
		
		getContentPane().add(btnRemplacer);
		
		artistTableScrollPane.setBounds(defaultStartingX + 210, defaultStartingY + 160, 580, 250);
		
		// TODO Ajouter le contenu du tableau
		
		getContentPane().add(artistTableScrollPane);
	}
	
	private void buildInformationsZone () {
		lblInformations.setText("Informations");
		lblInformations.setFont(new Font("Arial", Font.BOLD, 30));
		lblInformations.setBounds(defaultStartingX, defaultStartingY + 430, 370, 35);
		
		getContentPane().add(lblInformations);
		
		lblNumero.setText("Numéro");
		lblNumero.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNumero.setBounds(defaultStartingX, defaultStartingY + 500, 370, 45);
		
		getContentPane().add(lblNumero);
		
		txtNumero.setBounds(defaultStartingX + 110, defaultStartingY + 500, 370, 45);
		
		getContentPane().add(txtNumero);
		
		lblNom.setText("Nom");
		lblNom.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNom.setBounds(defaultStartingX, defaultStartingY + 550, 370, 45);
		
		getContentPane().add(lblNom);
		
		txtNom.setBounds(defaultStartingX + 110, defaultStartingY + 550, 370, 45);
		
		getContentPane().add(txtNom);
		
		lblMembre.setText("Membre");
		lblMembre.setFont(new Font("Arial", Font.PLAIN, 22));
		lblMembre.setBounds(defaultStartingX, defaultStartingY + 600, 370, 45);
		
		getContentPane().add(lblMembre);
		
		checkBoxMembre.setBounds(defaultStartingX + 110, defaultStartingY + 600, 370, 45);
		
		getContentPane().add(checkBoxMembre);
		
		listeAlbums.setBounds(defaultStartingX + 500, defaultStartingY + 500, 290, 180);
		
		getContentPane().add(listeAlbums);
		
		// TODO Ajouter l'image de l'album sélectonné
	}
	
	public ArrayList<JComponent> getElementList() {
		
		return listeElements;
		
	}
	
	private class ButtonListener extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(e.getSource() == btnNouveau) {
				
				
				
			}else if(e.getSource() == btnAjouter) {
				
				
				
			}
		}
	}

	private void centerWindow() {
		 int hauteur = getHeight();
		 int largeur = getWidth();		
	     Toolkit tk = Toolkit.getDefaultToolkit();
	     Dimension d = tk.getScreenSize();
	     int screenHeight = d.height;
	     int screenWidth = d.width;
	      
	     //vérifier la hauteur  de la fenêtre par rapport à l'écran
	     if (getHeight() > screenHeight) {
	       	 hauteur= screenHeight;
	     }
	      
	     //vérifier la largeur  de la fenêtre par rapport à l'écran
	     if (getWidth() > screenWidth) {
	       	largeur=screenWidth;
	     }
	       
	     //fixer la taille de la fenêtre
	     setSize(largeur, hauteur);
	     //positionner la fenêtre au centre de l'écran
	     setLocationRelativeTo (null);
	}
	
	public static void main(String[] args) {
		GestionArtistes fenetre = new GestionArtistes();
		fenetre.setVisible(true);
	}
}
