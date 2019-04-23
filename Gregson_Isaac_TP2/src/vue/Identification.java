package vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Identification extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	
	private ArrayList<JLabel> formFieldNamesList;
	private ArrayList<JTextField> formFieldInputsList;
	private ArrayList<JButton> formButtons;
	
	private ChoixTraitement fenetreChoixTraitement;
	
	private int defaultXValue;
	private int defaultYValue;
	private int defaultWidth;
	private int defaultHeight;
	
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
		
		title = new JLabel();
		
		fenetreChoixTraitement = new ChoixTraitement();
		
		resetDefaultInputBoundValues();
		resetDefaultButtonsBoundValue();
		
		formFieldNamesList = new ArrayList<JLabel>();
		formFieldInputsList = new ArrayList<JTextField>();
		formButtons = new ArrayList<JButton>();
		
		buildInterface();
		
		addActionsToAllButtons();
	}
	
	private void buildInterface () {
		getContentPane().add(createDefaultTitle());
		createInputFields();
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
	
	private void createInputFields () {
		buildInputFieldWithName("Nom d'utilisateur");
		buildInputFieldWithName("Mot de passe");
		
		addInputFieldsWithName();
	}
	
	// Crée un input field avec un nom.
	private void buildInputFieldWithName (String fieldName) {
		JLabel name = new JLabel(fieldName);
		name.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JTextField field = new JTextField();
			
		formFieldNamesList.add(name);
		formFieldInputsList.add(field);
	}
	
	private void addInputFieldsWithName () {
		for (JLabel fieldName : formFieldNamesList) {
			getContentPane().add(fieldName);
		}
		
		for (JTextField fieldInput : formFieldInputsList) {
			getContentPane().add(fieldInput);
		}
		
		shiftDownAllFields();
	}
	
	private void shiftDownAllFields () {
		int index = 0;
		
		for (JLabel fieldName : formFieldNamesList) {
			// Le premier field name avec la valeur initiale
			if (index == 0) {
				fieldName.setBounds(defaultXValue, defaultYValue, defaultWidth, defaultHeight);
				
				index++;
			}
			// Les autres ajout seront décalés d'une certaine valeur vers le bas
			else if (index > 0) {
				// On augmente la valeur Y par défaut
				defaultYValue += 45;
				
				fieldName.setBounds(defaultXValue, defaultYValue, defaultWidth, defaultHeight);
				
				index++;
			}
		}
		
		// Reset l'indice
		index = 0;
		
		resetDefaultInputBoundValues();
		
		for (JTextField fieldInput : formFieldInputsList) {
			// Le premier field name avec la valeur initiale
			if (index == 0) {
				// On augmente la valeur X par défaut pour avoir un décalage entre le nom et le champ d'entré.
				defaultXValue += 210;
				
				fieldInput.setBounds(defaultXValue, defaultYValue, defaultWidth, defaultHeight);
				
				index++;
			}
			// Les autres ajout seront décalés d'une certaine valeur vers le bas
			else if (index > 0) {
				// On augmente la valeur Y par défaut pour avoir un décalage entre les champs supérieurs et ceux inférieurs.
				defaultYValue += 45;
				
				fieldInput.setBounds(defaultXValue, defaultYValue, defaultWidth, defaultHeight);
				
				index++;
			}
		}
	}
	
	private void resetDefaultInputBoundValues () {
		defaultXValue = 150;
		defaultYValue = 100;
		defaultWidth = 200;
		defaultHeight = 35;
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
				System.exit(0);
			}else if (e.getSource() == findButton("Valider")) {
				fenetreChoixTraitement.setVisible(true);
			}
		}
		
	}

}
