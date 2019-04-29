package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.ControleurGestionArtistes;
import modele.Album;
import modele.Artiste;
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
	private JButton btnAnnulerRecherche;
	private JButton btnQuitter;
	private JButton btnRemplacer;
	private JButton btnNouveau;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnAnnuler;
	
	private JScrollPane artistTableScrollPane;
	private ModeleArtiste artistTableModel;
	private JTable tableArtistes;
	private DefaultTableCellRenderer centerRenderer;
	
	private ControleurGestionArtistes controleur;
	
	private JList<String> listeAlbums;
	private DefaultListModel<String> listeNomsAlbums;
	
	private ArrayList<Album> albums;
	
	private int defaultStartingX;
	private int defaultStartingY;
	
	private ModificationArtiste modification;
	
	private GestionArtistes vueArtisteCourant = this;
	
	private ChoixTraitement parent;
	
	public GestionArtistes() {
		setTitle("Gestion des Artistes");
		setSize(1100, 810);
		setResizable(false);
		getContentPane().setLayout(null);
		centerWindow();
		
		albums = new ArrayList<>();
		listeNomsAlbums = new DefaultListModel<String>();
		
		defaultStartingX = 40;
		defaultStartingY = 20;
		
		controleur = new ControleurGestionArtistes();
		modification = new ModificationArtiste(this);
		
		lblRecherche = new JLabel();
		lblArtistes = new JLabel();
		lblInformations = new JLabel();
		lblImageArtist = new JLabel();
		lblImageArtist = new JLabel();
		lblNumero = new JLabel();
		lblNom = new JLabel();
		lblMembre = new JLabel();
		
		checkBoxMembre = new JCheckBox();
		
		txtRechercher = new JTextField();
		txtRechercher.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				if(!txtRechercher.getText().isEmpty()) {
					
					btnRechercher.setEnabled(true);
					
				}else {
					
					btnRechercher.setEnabled(false);
					
				}
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				if(!txtRechercher.getText().isEmpty()) {
					
					btnRechercher.setEnabled(true);
					
				}else {
					
					btnRechercher.setEnabled(false);
					
				}
				
			}
			
		});
		
		txtNumero = new JTextField();
		
		txtNom = new JTextField();
		txtNom.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				if(!txtNom.getText().isEmpty()) {
					
					btnAjouter.setEnabled(true);
					
				}else {
					
					btnAjouter.setEnabled(false);
					
				}
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				if(!txtNom.getText().isEmpty()) {
					
					btnAjouter.setEnabled(true);
					
				}else {
					
					btnAjouter.setEnabled(false);
					
				}
				
			}
			
			
		});
		
		btnRechercher = new JButton();
		btnAnnulerRecherche = new JButton();
		btnQuitter = new JButton();
		btnRemplacer = new JButton();
		btnNouveau = new JButton();
		btnAjouter = new JButton();
		btnModifier = new JButton();
		btnSupprimer = new JButton();
		btnAnnuler = new JButton();
		
		listeAlbums = new JList<String>(listeNomsAlbums);
		
		artistTableModel = new ModeleArtiste(controleur.getListeArtistes());
		
		tableArtistes = new JTable(artistTableModel);
		tableArtistes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		artistTableScrollPane = new JScrollPane(tableArtistes);
		centerRenderer = new DefaultTableCellRenderer(); // Pour centrer
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tableArtistes.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Centrer seulement la première colonne
		
		// Ajout des listeners au boutons
		btnRechercher.addActionListener(new ButtonListener());
		btnQuitter.addActionListener(new ButtonListener());
		btnRemplacer.addActionListener(new ButtonListener());
		btnNouveau.addActionListener(new ButtonListener());
		btnAjouter.addActionListener(new ButtonListener());
		btnModifier.addActionListener(new ButtonListener());
		btnSupprimer.addActionListener(new ButtonListener());
		btnAnnuler.addActionListener(new ButtonListener());
		btnAnnulerRecherche.addActionListener(new ButtonListener());
		
		// Ajout de listener pour le tableau
		tableArtistes.addMouseListener(new MouseListener());
		
		buildInterface();
	}
	
	public void refreshTable () {
		artistTableModel.deleteData();
		artistTableModel.refresh(controleur.getListeArtistes());
	}
	
	public void setParent (ChoixTraitement parent){
		this.parent = parent;
	}
	
	public JTable getTableArtists() {
		
		return tableArtistes;
		
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
		btnRechercher.setEnabled(false);
		btnRechercher.setBounds(defaultStartingX + 380, defaultStartingY + 45, 150, 35);
		
		getContentPane().add(btnRechercher);
		
		btnAnnulerRecherche.setText("Annuler");
		btnAnnulerRecherche.setEnabled(false);
		btnAnnulerRecherche.setBounds(defaultStartingX + 540, defaultStartingY + 45, 150, 35);
		
		getContentPane().add(btnAnnulerRecherche);
	}
	
	private void addDefaultControlerButtons () {
		btnQuitter.setText("Quitter");
		btnQuitter.setBounds(defaultStartingX + 840, defaultStartingY + 45, 150, 35);
		
		getContentPane().add(btnQuitter);
		
		btnNouveau.setText("Nouveau");
		btnNouveau.setBounds(defaultStartingX + 840, defaultStartingY + 160, 150, 35);
		
		getContentPane().add(btnNouveau);
		
		btnAjouter.setText("Ajouter");
		btnAjouter.setEnabled(false);
		btnAjouter.setBounds(defaultStartingX + 840, defaultStartingY + 232, 150, 35);
		
		getContentPane().add(btnAjouter);
		
		btnModifier.setText("Modifier");
		btnModifier.setEnabled(false);
		btnModifier.setBounds(defaultStartingX + 840, defaultStartingY + 304, 150, 35);
		
		getContentPane().add(btnModifier);
		
		btnSupprimer.setText("Supprimer");
		btnSupprimer.setEnabled(false);
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
		txtNumero.setEditable(false);
		
		getContentPane().add(txtNumero);
		
		lblNom.setText("Nom");
		lblNom.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNom.setBounds(defaultStartingX, defaultStartingY + 550, 370, 45);
		
		getContentPane().add(lblNom);
		
		txtNom.setBounds(defaultStartingX + 110, defaultStartingY + 550, 370, 45);
		txtNom.setEditable(false);
		
		getContentPane().add(txtNom);
		
		lblMembre.setText("Membre");
		lblMembre.setFont(new Font("Arial", Font.PLAIN, 22));
		lblMembre.setBounds(defaultStartingX, defaultStartingY + 600, 370, 45);
		
		getContentPane().add(lblMembre);
		
		checkBoxMembre.setBounds(defaultStartingX + 110, defaultStartingY + 600, 370, 45);
		checkBoxMembre.setEnabled(false);
		
		getContentPane().add(checkBoxMembre);
		
		btnAnnuler.setText("Annuler");
		btnAnnuler.setEnabled(false);
		btnAnnuler.setBounds(defaultStartingX, defaultStartingY + 648, 150, 35);
		
		getContentPane().add(btnAnnuler);
		
		listeAlbums.setBounds(defaultStartingX + 500, defaultStartingY + 500, 290, 180);
		
		getContentPane().add(listeAlbums);
		
		// TODO Ajouter l'image de l'album sélectonné
	}
	
	
	private class ButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnRechercher) {
				
				btnAnnulerRecherche.setEnabled(true);
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
				
				artistTableModel.refresh(controleur.rechercherArtiste(txtRechercher.getText()));
				
				artistTableModel.fireTableDataChanged();
				
				
			}else if(e.getSource() == btnAnnulerRecherche) {
				
				txtRechercher.setText("");
				
				btnRechercher.setEnabled(false);
				btnAnnulerRecherche.setEnabled(false);
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
				
				artistTableModel.refresh(controleur.getListeArtistes());
				
				artistTableModel.fireTableDataChanged();
				
			}else if(e.getSource() == btnQuitter) {
				
				dispose();
				
				if (parent != null) { // Si il a une fenetre parent
					parent.setVisible(true);
				}
				
			}else if(e.getSource() == btnRemplacer) {
				
			}else if(e.getSource() == btnNouveau) {
				
				btnAnnuler.setEnabled(true);
				
				txtNumero.setText(Integer.toString(controleur.getLastIndex()));
				
				txtNom.setText("");
				txtNom.setEnabled(true);
				txtNom.setEditable(true);
				
				checkBoxMembre.setSelected(false);
				checkBoxMembre.setEnabled(true);
				
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);

				tableArtistes.clearSelection();
				
			}else if(e.getSource() == btnAjouter) {
				
				if(!txtNom.getText().isEmpty()) {
					
					Artiste artiste = new Artiste(Integer.parseInt(txtNumero.getText()), txtNom.getText(), checkBoxMembre.isSelected(), "images/artistes/default.png");
					
					controleur.addArtiste(artiste);
					
					txtNumero.setText("");
					txtNom.setText("");
					checkBoxMembre.setSelected(false);
					
					txtNom.setEnabled(false);
					txtNom.setEditable(false);
					checkBoxMembre.setEnabled(false);
					
					btnAjouter.setEnabled(false);
					btnAnnuler.setEnabled(false);
					
					refreshTable();
				}
				
			}else if(e.getSource() == btnModifier) {
				
				controleur.modifier(vueArtisteCourant);
				
			}else if(e.getSource() == btnSupprimer) {
				
				int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cet élément?", "Suppression", JOptionPane.YES_NO_OPTION);
				
				if(reponse == 0) {
					
					controleur.deleteArtiste((int)artistTableModel.getValueAt(tableArtistes.getSelectedRow(), 0));
					
					artistTableModel.refresh(controleur.getListeArtistes());
					
					artistTableModel.fireTableDataChanged();
					
				}else if(reponse == 1) {
					
					
					
				}
				
			}else if (e.getSource() == btnAnnuler) {
				btnAjouter.setEnabled(false);
				
				txtNumero.setText("");
				txtNom.setText("");
				checkBoxMembre.setSelected(false);
				
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
				
				btnAnnuler.setEnabled(false);
				
				tableArtistes.clearSelection();
			}
		}
	}
	
	private class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (tableArtistes.getSelectedRow() != -1) {
				
				btnModifier.setEnabled(true);
				btnSupprimer.setEnabled(true);
				
				albums.clear();
				listeNomsAlbums.clear();
				
				albums = controleur.getListeAlbums((int)artistTableModel.getValueAt(tableArtistes.getSelectedRow(), 0) );
				
				for(Album album : albums) {
					
					listeNomsAlbums.addElement(Integer.toString(album.getAnneeSortie()) + " - " + album.getTitre());
					
				}
			
			}else if(tableArtistes.getSelectedRow() == -1) {
				
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
				
				listeNomsAlbums.clear();
			
			}else if (e.getClickCount() == 2) {
				
				if (tableArtistes.getSelectedRow() != -1) {
					
					controleur.modifier(vueArtisteCourant);
				}
				
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
	
	public static void main(String[] args) { // TODO Retirer
		GestionArtistes fenetre = new GestionArtistes();
		fenetre.setVisible(true);
	}
}
