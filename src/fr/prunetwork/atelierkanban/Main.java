/*
 */
package fr.prunetwork.atelierkanban;

import fr.prunetwork.atelierkanban.gui.ChronometerPanel;
import fr.prunetwork.atelierkanban.gui.SplashScreenWindows;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Start the Application
 * @author jpierre03
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		SplashScreenWindows splashScreen = new SplashScreenWindows();

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		ChronometerPanel cp = new ChronometerPanel();
		frame.add(cp);
		frame.pack();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
		}

		splashScreen.masquer();
		frame.setVisible(true);
	}
}
