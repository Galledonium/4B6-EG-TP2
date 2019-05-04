package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controleur.ControleurModificationArtiste;
import modele.Artiste;

public class ModificationArtiste extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JLabel lblNom;
	private JLabel lblMembre;
	
	private JTextField txtNom;
	
	private JCheckBox checkBoxMembre;
	
	private JButton btnModifier;
	private JButton btnAnnuler;
	
	private ControleurModificationArtiste controleur;
	
	private GestionArtistes parent;
	
	private Artiste artiste;
	
	public ModificationArtiste (GestionArtistes parent, Artiste artiste) {
		setTitle("Modification de l'artiste");
		setSize(600, 320);
		setResizable(false);
		getContentPane().setLayout(null);
		centerWindow();
		
		controleur = new ControleurModificationArtiste();
		
		this.parent = parent;
		this.artiste = artiste;
		
		title = new JLabel();
		lblNom = new JLabel();
		lblMembre = new JLabel();
		
		txtNom = new JTextField();
		
		checkBoxMembre = new JCheckBox();
		
		btnModifier = new JButton();
		btnAnnuler = new JButton();
		
		btnModifier.addActionListener(new BoutonListener());
		btnAnnuler.addActionListener(new BoutonListener());
		
		buildInterface();
	}
	
	private void buildInterface () {
		title.setText("Modification de l'artiste : " + artiste.getNom());
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setBounds(30, 10, 600, 60);
		
		getContentPane().add(title);
		
		lblNom.setText("Nom");
		lblNom.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNom.setBounds(100, 100, 200, 35);
		
		getContentPane().add(lblNom);
		
		txtNom.setText(artiste.getNom());
		txtNom.setBounds(245, 100, 200, 35);
		
		getContentPane().add(txtNom);
		
		lblMembre.setText("Membre");
		lblMembre.setFont(new Font("Arial", Font.PLAIN, 25));
		lblMembre.setBounds(100, 145, 200, 35);
		
		getContentPane().add(lblMembre);
		
		checkBoxMembre.setSelected(artiste.getMembre());
		checkBoxMembre.setBounds(245, 145, 200, 35);
		
		getContentPane().add(checkBoxMembre);
		
		btnModifier.setText("Modifier");
		btnModifier.setBounds(100, 220, 150, 35);
		
		getContentPane().add(btnModifier);
		
		btnAnnuler.setText("Annuler");
		btnAnnuler.setBounds(292, 220, 150, 35);
		
		getContentPane().add(btnAnnuler);
	}
	
	private class BoutonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnModifier) {
				artiste.setNom(txtNom.getText());
				artiste.isMembre(checkBoxMembre.isSelected());
				
				controleur.modifierArtiste(artiste);
				
				// The parent needs to be shaken ... he's getting old ?
				parent.shake();
				
				dispose();
			}else if (e.getSource() == btnAnnuler) {
				txtNom.setText(null);
				checkBoxMembre.setSelected(false);
				
				dispose();
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
