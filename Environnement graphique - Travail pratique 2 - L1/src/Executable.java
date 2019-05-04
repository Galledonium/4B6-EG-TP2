import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import vue.Identification;

public class Executable {

	public static void main(String[] args) {
		try {
			for(LookAndFeelInfo feeling: UIManager.getInstalledLookAndFeels()) {
				if("Windows".equals( feeling.getName() )) {
					UIManager.setLookAndFeel( feeling.getClassName() );
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Identification fenetre = new Identification();
		fenetre.setVisible(true);
	}

}
