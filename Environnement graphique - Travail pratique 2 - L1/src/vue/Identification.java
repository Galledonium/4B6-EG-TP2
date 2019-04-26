package vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.ControleurIdentification;

public class Identification extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private JLabel lblUsername;
	private JLabel lblPassword;
	
	private JTextField txtUsername;
	private JPasswordField pwPassword;
	
	private ArrayList<JButton> formButtons;
	
	private ControleurIdentification controleur;
	
	private int defaultButtonXValue;
	private int defaultButtonYValue;
	private int defaultButtonWidth;
	private int defaultButtonHeight;
	
	public Identification() {
		setTitle("Gestion des Albums");
		setSize(680, 360);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		centerWindow();
		
		controleur = new ControleurIdentification(this);
		
		title = new JLabel();
		lblUsername = new JLabel();
		lblPassword = new JLabel();
		
		txtUsername = new JTextField();
		pwPassword = new JPasswordField();
		
		resetDefaultButtonsBoundValue();
		
		formButtons = new ArrayList<JButton>();
		
		buildInterface();
		
		addActionsToAllButtons();
	}
	
	private void buildInterface () {
		getContentPane().add(createDefaultTitle());
		createUsernameInputField();
		createPasswordInputField();
		createButtons();
	}
	
	private JLabel createDefaultTitle() {
		title.setText("Connexion \u00E0 l'application");
		title.setFont(new Font("Arial", Font.PLAIN, 32));
		/*
		* Place le titre dans la fenêtre en recevant les paramètres suivants
		* (La position x dans la fenêtre, la position y dans la fenêtre, la largeur du conteneur, la hauteur du conteneur)
		*/
		title.setBounds(35, 10, 350, 60);
		
		return title;
	}
	
	private void createUsernameInputField () {
		lblUsername.setText("Nom d'utilisateur");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 25));
		lblUsername.setBounds(150, 100, 200, 35);
		
		getContentPane().add(lblUsername);
		
		txtUsername.setBounds(360, 100, 200, 35);
		
		getContentPane().add(txtUsername);
	}
	
	private void createPasswordInputField () {
		lblPassword.setText("Nom d'utilisateur");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 25));
		lblPassword.setBounds(150, 145, 200, 35);
		
		getContentPane().add(lblPassword);
		
		pwPassword.setBounds(360, 145, 200, 35);
		
		getContentPane().add(pwPassword);
	}
	
	private void createButtons () {
		addButton("Valider");
		addButton("Quitter");
		
		addButtons();
	}
	
	private void addButton (String name) {
		JButton button = new JButton(name);
		
		button.setName(name);
		
		formButtons.add(button);
	}
	
	private void addButtons () {
		for (JButton button : formButtons) {
			getContentPane().add(button);
		}
		
		shiftRightAllButtons();
	}
	
	private void resetDefaultButtonsBoundValue () {
		defaultButtonXValue = 150;
		defaultButtonYValue = 240;
		defaultButtonWidth = 165;
		defaultButtonHeight = 50;
	}
	
	private void shiftRightAllButtons () {
		int index = 0;
		
		for (JButton button : formButtons) {
			if (index == 0) {
				button.setBounds(defaultButtonXValue, defaultButtonYValue, defaultButtonWidth, defaultButtonHeight);
				
				index++;
			}
			else if (index > 0) {
				button.setBounds(defaultButtonXValue + 243, defaultButtonYValue, defaultButtonWidth, defaultButtonHeight);
				
				index++;
			}
		}
		
		resetDefaultButtonsBoundValue();
	}
	
	private void addActionsToAllButtons () {
		for (JButton button : formButtons) {
			button.addActionListener(new ButtonListener());
		}
	}
	
	// Accesseurs
	public String getUsername () {
		return txtUsername.getText();
	}
	
	public char[] getPassword () {
		return pwPassword.getPassword();
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
	
	private class ButtonListener implements ActionListener {
		
		private JButton findButton (String name) {
			JButton buttonSearched = null;
			
			for (JButton button : formButtons) {
				if (button.getText() == name) {
					buttonSearched = button;
				}
			}
			
			return buttonSearched;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == findButton("Quitter")) {
				controleur.quitter();
			}else if (e.getSource() == findButton("Valider")) {
				controleur.valider();
			}
		}
		
	}
}
