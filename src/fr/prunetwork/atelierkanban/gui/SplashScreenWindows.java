package fr.prunetwork.atelierkanban.gui;

import fr.prunetwork.atelierkanban.Constants;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.UIManager;

/**
 *
 * @author jpierre03
 */
public class SplashScreenWindows
		extends JWindow {

	/**
	 *
	 */
	private static final long serialVersionUID = 20100312L;

	/**
	 *
	 */
	public SplashScreenWindows() {
		build();

		afficher();
	}

	/**
	 *
	 */
	public void afficher() {
		setVisible(true);
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		SplashScreenWindows splashScreen = new SplashScreenWindows();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
		}

		splashScreen.masquer();
	}

	/**
	 *
	 */
	public void masquer() {
		setVisible(false);
		dispose();
	}

	/**
	 *
	 */
	private void build() {
		final JLabel component = new javax.swing.JLabel();

		component.setIcon(new ImageIcon(getClass().getResource(Constants.SPLASH_SCREEN_IMAGE)));
		add(component);
		pack();
		setLocationRelativeTo(getParent());
	}
}
