package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ModificationArtiste extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JLabel lblNom;
	private JLabel lblMembre;
	
	private JTextField txtNom;
	
	private JCheckBox checkBoxMembre;
	
	private GestionArtistes parent;
	
	public ModificationArtiste (GestionArtistes parent) {
		setTitle("Modification de l'artiste");
		setSize(580, 260);
		setResizable(false);
		getContentPane().setLayout(null);
		centerWindow();
		
		this.parent = parent;
		
		title = new JLabel();
		lblNom = new JLabel();
		lblMembre = new JLabel();
		
		txtNom = new JTextField();
		
		checkBoxMembre = new JCheckBox();
		
		buildInterface();
	}
	
	private void buildInterface () {
		title.setText("Modification de l'artiste");
		title.setFont(new Font("Arial", Font.PLAIN, 32));
		title.setBounds(35, 10, 350, 60);
		
		getContentPane().add(title);
		
		lblNom.setText("Nom");
		lblNom.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNom.setBounds(35, 100, 200, 35);
		
		getContentPane().add(lblNom);
		
		txtNom.setBounds(245, 100, 200, 35);
		
		getContentPane().add(txtNom);
		
		lblMembre.setText("Membre");
		lblMembre.setFont(new Font("Arial", Font.PLAIN, 25));
		lblMembre.setBounds(35, 145, 200, 35);
		
		getContentPane().add(lblMembre);
		
		checkBoxMembre.setBounds(245, 145, 200, 35);
		
		getContentPane().add(checkBoxMembre);
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
