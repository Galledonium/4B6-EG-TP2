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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import controleur.ControleurGestionArtistes;
import modele.Album;
import modele.Artiste;
import modele.ModeleTableArtiste;

public class GestionArtistes extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblRecherche;
	private JLabel lblArtistes;
	private JLabel lblInformations;
	private JLabel lblImageArtiste;
	private JLabel lblImageAlbum;
	private JLabel lblNumero;
	private JLabel lblNom;
	private JLabel lblMembre;
	private JLabel lblAlbum;
	
	private JCheckBox checkBoxMembre;
	
	private JTextField txtRechercher;
	private JTextField txtNumero;
	private JTextField txtNom;
	
	private JButton btnRechercher;
	private JButton btnAnnulerRecherche;
	private JButton btnQuitter;
	private JButton btnNouveau;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnAnnuler;
	private JButton btnChange;
	private JButton btnSave;
	
	private JScrollPane artistTableScrollPane;
	private ModeleTableArtiste artistTableModel;
	private JTable tableArtistes;
	private DefaultTableCellRenderer centerRenderer;
	
	private ControleurGestionArtistes controleur;
	
	private JList<String> listeAlbums;
	private DefaultListModel<String> listeNomsAlbums;
	
	private ArrayList<Album> albums;
	
	private int defaultStartingX;
	private int defaultStartingY;
	
	private GestionArtistes vueArtisteCourant = this;
	
	private ChoixTraitement parent;
	
	private ModificationArtiste childModificationView;
	
	private boolean isChosen;
	
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
		
		lblRecherche = new JLabel();
		lblArtistes = new JLabel();
		lblInformations = new JLabel();
		lblImageArtiste = new JLabel();
		lblImageAlbum = new JLabel();
		lblNumero = new JLabel();
		lblNom = new JLabel();
		lblMembre = new JLabel();
		lblAlbum = new JLabel();
		
		checkBoxMembre = new JCheckBox();
		
		txtRechercher = new JTextField();
		txtRechercher.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				
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
		btnNouveau = new JButton();
		btnAjouter = new JButton();
		btnModifier = new JButton();
		btnSupprimer = new JButton();
		btnAnnuler = new JButton();
		btnChange = new JButton();
		btnSave = new JButton();
		
		listeAlbums = new JList<String>(listeNomsAlbums);
		
		artistTableModel = new ModeleTableArtiste(controleur.getListeArtistes());
		
		tableArtistes = new JTable(artistTableModel);
		tableArtistes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		artistTableScrollPane = new JScrollPane(tableArtistes);
		centerRenderer = new DefaultTableCellRenderer(); // Pour centrer
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tableArtistes.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Centrer seulement la première colonne
		
		// Ajout des listeners au boutons
		btnChange.addActionListener(new ButtonListener());
		btnSave.addActionListener(new ButtonListener());
		btnQuitter.addActionListener(new ButtonListener());
		btnRechercher.addActionListener(new ButtonListener());
		btnNouveau.addActionListener(new ButtonListener());
		btnAjouter.addActionListener(new ButtonListener());
		btnModifier.addActionListener(new ButtonListener());
		btnSupprimer.addActionListener(new ButtonListener());
		btnAnnuler.addActionListener(new ButtonListener());
		btnAnnulerRecherche.addActionListener(new ButtonListener());
		
		// Ajout de listener pour le tableau
		tableArtistes.addMouseListener(new MouseListener());
		tableArtistes.getSelectionModel().addListSelectionListener(new ArtisteSelectionListener());
		
		// Ajout de listener pour la liste
		listeAlbums.addListSelectionListener(new AlbumSelectionListener());
		
		isChosen = false;
		
		buildInterface();
	}
	
	public void refreshTable () {
		artistTableModel.deleteData();
		artistTableModel.refresh(controleur.getListeArtistes());
	}
	
	// Sa force le rafraichissement de la vue (Ex : enlever cette méthode de la classe Modification Artiste => Le tableau ne se met pas à jour à moins de cliquer sur une valeur du tableau)
	public void shake () {
		tableArtistes.repaint();
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
		
		btnModifier.setText("Modifier");
		btnModifier.setEnabled(false);
		btnModifier.setBounds(defaultStartingX + 840, defaultStartingY + 220, 150, 35);
		
		getContentPane().add(btnModifier);
		
		btnSupprimer.setText("Supprimer");
		btnSupprimer.setEnabled(false);
		btnSupprimer.setBounds(defaultStartingX + 840, defaultStartingY + 280, 150, 35);
		
		getContentPane().add(btnSupprimer);
		
		btnChange.setText("Changer");
		btnChange.setEnabled(false);
		btnChange.setBounds(defaultStartingX, defaultStartingY + 376, 110, 35);
		
		getContentPane().add(btnChange);
		
		btnSave.setText("Sauvegarder");
		btnSave.setEnabled(false);
		btnSave.setBounds(defaultStartingX + 120, defaultStartingY + 376, 110, 35);
		
		getContentPane().add(btnSave);
		
		btnAjouter.setText("Ajouter");
		btnAjouter.setEnabled(false);
		btnAjouter.setBounds(defaultStartingX + 85, defaultStartingY + 648, 150, 35);
		
		getContentPane().add(btnAjouter);
		
		btnAnnuler.setText("Annuler");
		btnAnnuler.setEnabled(false);
		btnAnnuler.setBounds(defaultStartingX + 245, defaultStartingY + 648, 150, 35);
		
		getContentPane().add(btnAnnuler);
	}
	
	private void buildArtistsZone () {
		lblArtistes.setText("Artistes");
		lblArtistes.setFont(new Font("Arial", Font.BOLD, 30));
		lblArtistes.setBounds(defaultStartingX, defaultStartingY + 110, 370, 35);
		
		getContentPane().add(lblArtistes);
		
		lblImageArtiste.setBounds(defaultStartingX, defaultStartingY + 160, 230, 200);
		
		getContentPane().add(lblImageArtiste);
		
		tableArtistes.getTableHeader().setReorderingAllowed(false);
		artistTableScrollPane.setBounds(defaultStartingX + 250, defaultStartingY + 160, 580, 250);
		
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
		
		lblAlbum.setText("Albums");
		lblAlbum.setFont(new Font("Arial", Font.BOLD, 18));
		lblAlbum.setBounds(defaultStartingX + 500, defaultStartingY + 450, 370, 35);
		
		getContentPane().add(lblAlbum);
		
		listeAlbums.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeAlbums.setBounds(defaultStartingX + 500, defaultStartingY + 500, 250, 180);
		
		getContentPane().add(listeAlbums);
		
		lblImageAlbum.setBounds(defaultStartingX + 770, defaultStartingY + 500, 250, 180);
		
		getContentPane().add(lblImageAlbum);
	}
	
	public void setChildModificationArtiste (ModificationArtiste child) {
		childModificationView = child;
	}
	
	public ModificationArtiste getChildModificationArtiste () {
		return childModificationView;
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnRechercher) {
				btnAnnulerRecherche.setEnabled(true);
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
				
				artistTableModel.refresh(controleur.rechercherArtiste(txtRechercher.getText()));
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
			}else if(e.getSource() == btnChange) {
				isChosen = controleur.changerImage(lblImageArtiste);
				
				if(isChosen) {
					btnSave.setEnabled(true);
				}
			}else if(e.getSource() == btnSave) {
				
				controleur.saveImage(getSelectedArtist(artistTableModel.getData()));
				
				isChosen = false;
				
				btnSave.setEnabled(false);
				
			}else if(e.getSource() == btnNouveau) {
				lblImageArtiste.setVisible(false);
				
				lblAlbum.setText("Albums");
				
				btnAnnuler.setEnabled(true);
				
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
				btnChange.setEnabled(false);
				
				txtNumero.setText(Integer.toString(controleur.getLastIndex()));
				
				txtNom.setText("");
				txtNom.setEnabled(true);
				txtNom.setEditable(true);
				
				checkBoxMembre.setSelected(false);
				checkBoxMembre.setEnabled(true);

				tableArtistes.clearSelection();
				
			}else if(e.getSource() == btnAjouter) {
				if(!txtNom.getText().isEmpty()) {
					Artiste artiste = new Artiste(Integer.parseInt(txtNumero.getText()), txtNom.getText(), checkBoxMembre.isSelected(), "src\\images\\default.png");
					
					controleur.addArtiste(artiste);
					
					txtNumero.setText("");
					txtNom.setText("");
					checkBoxMembre.setSelected(false);
					
					txtNom.setEditable(false);
					checkBoxMembre.setEnabled(false);
					
					btnAjouter.setEnabled(false);
					btnAnnuler.setEnabled(false);
					
					refreshTable();
				}
			}else if(e.getSource() == btnModifier) {
				if(tableArtistes.getSelectedRow() != -1) {
					controleur.modifier(vueArtisteCourant, getSelectedArtist(artistTableModel.getData()));
				}
			}else if(e.getSource() == btnSupprimer) {
				int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cet élément?", "Suppression", JOptionPane.YES_NO_OPTION);
				
				if(reponse == 0) {
					controleur.deleteArtiste((int)artistTableModel.getValueAt(tableArtistes.getSelectedRow(), 0));
					
					artistTableModel.refresh(controleur.getListeArtistes());
					artistTableModel.fireTableDataChanged();
					
					btnModifier.setEnabled(false);
					btnSupprimer.setEnabled(false);
				}else if(reponse == 1) {
					
				}
			}else if (e.getSource() == btnAnnuler) {
				btnAjouter.setEnabled(false);
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
				btnAnnuler.setEnabled(false);
				
				txtNumero.setText("");
				txtNom.setText("");
				
				txtNom.setEditable(false);
				
				checkBoxMembre.setEnabled(false);
				checkBoxMembre.setSelected(false);
				
				tableArtistes.clearSelection();
			}
		}
	}
	
	public Artiste getSelectedArtist (ArrayList<Artiste> list) {
		Artiste selectedArtist = null;
		
		for (Artiste artiste : list) {
			if (artiste.getID() == (int) artistTableModel.getValueAt(tableArtistes.getSelectedRow(), 0) &&
				artiste.getNom() == (String) artistTableModel.getValueAt(tableArtistes.getSelectedRow(), 1) &&
				artiste.getMembre() == (boolean) artistTableModel.getValueAt(tableArtistes.getSelectedRow(), 2)) {
				selectedArtist = artiste;
			}
		}
		
		return selectedArtist;
	}
	
	private class ArtisteSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (tableArtistes.getSelectedRow() != -1) {
				isChosen = false;
				
				btnModifier.setEnabled(true);
				btnSupprimer.setEnabled(true);
				btnChange.setEnabled(true);
				
				lblImageArtiste.setVisible(true);
				
				albums.clear();
				listeNomsAlbums.clear();
				
				lblAlbum.setText("Albums de " + getSelectedArtist(artistTableModel.getData()).getNom());
				
				albums = controleur.getListeAlbums((int)artistTableModel.getValueAt(tableArtistes.getSelectedRow(), 0) );
				
				for(Album album : albums) {
					listeNomsAlbums.addElement(Integer.toString(album.getAnneeSortie()) + " - " + album.getTitre());
				}
				
				controleur.afficherImageArtiste(getSelectedArtist(artistTableModel.getData()), lblImageArtiste);
			}else if(tableArtistes.getSelectedRow() == -1) {
				
				isChosen = false;
				
				btnModifier.setEnabled(false);
				btnSupprimer.setEnabled(false);
				btnChange.setEnabled(false);
				btnSave.setEnabled(false);
				
				lblImageArtiste.setVisible(false);
				
				listeNomsAlbums.clear();
				
				lblAlbum.setText("Albums");
			}
		}
	}
	
	private class AlbumSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (listeAlbums.getSelectedIndex() != -1) {
				lblImageAlbum.setVisible(true);
				
				controleur.afficherAlbum(albums.get(listeAlbums.getSelectedIndex()), lblImageAlbum);
			}else if (listeAlbums.getSelectedIndex() == -1) {
				lblImageAlbum.setVisible(false);
			}
		}
	}
	
	private class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				btnModifier.doClick();
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
}
