package vue;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import controleur.ControleurChoixTraitement;

public class ChoixTraitement extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	//Déclaration de l'attribut label de la vue
	private JLabel titre;
	
	//Déclaration des attributs button de la vue
	private JButton choixTraitementArtistes;
	private JButton choixTraitementAlbums;
	private JButton choixQuitter;
	
	//Déclaration de l'attribut toolbar de la vue
	private JToolBar barreOutils;
	
	private ControleurChoixTraitement controleur;
	
	public ChoixTraitement() {
		
		setTitle("Choix du traitement");
		setSize(600, 400);
		setResizable(false);
		setLayout(null);
		
		controleur = new ControleurChoixTraitement(this);
		
		barreOutils = new JToolBar();
		
		titre = new JLabel("VEUILLEZ S\u00C9LECTIONNER UN TRAITEMENT \u00C0 EFFECTUER");
		titre.setBounds(125, 0, 400, 100);
		
		//Initiatisation du bouton Traitement des artistes et de ses paramètres
		choixTraitementArtistes = new JButton("Traitement des artistes");
		choixTraitementArtistes.setBounds(75, 80, 200, 200);
		choixTraitementArtistes.addMouseListener(new ButtonListener());
		
		//Initiatisation du bouton Traitement des albums et de ses paramètres
		choixTraitementAlbums = new JButton("Traitement des albums");
		choixTraitementAlbums.setBounds(300, 80, 200, 200);
		choixTraitementAlbums.addMouseListener(new ButtonListener());
		
		choixTraitementAlbums.setEnabled(false);
		
		//Initiatisation du bouton Quitter et de ses paramètres
		choixQuitter = new JButton("Quitter");
		choixQuitter.setBounds(510, 330, 80, 35);
		choixQuitter.addMouseListener(new ButtonListener());
		
		//Ajout du bouton Quitter dans le toolbar et ajout de ses paramètres
		barreOutils.add(choixQuitter);
		barreOutils.setBounds(0, 325, 600, 75);
		barreOutils.setBackground(Color.LIGHT_GRAY);
		barreOutils.setFloatable(false);
		
		//Ajout des éléments crées dans le JDialog
		add(titre);
		add(choixTraitementArtistes);
		add(choixTraitementAlbums);
		add(choixQuitter);
		
		getContentPane().add(barreOutils);
		
	}
	
	private class ButtonListener extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(e.getSource() == choixTraitementArtistes) {
				controleur.traiterArtiste();
			}else if(e.getSource() == choixTraitementAlbums) {
				
			}else if(e.getSource() == choixQuitter) {
				
				dispose();
				
			}
		}
	}
}
