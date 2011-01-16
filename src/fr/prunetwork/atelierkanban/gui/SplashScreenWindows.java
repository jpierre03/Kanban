/*
 *  Copyright (C) 2010 Jean-Pierre Prunaret (jpierre03+kanban@prunetwork.fr)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.prunetwork.atelierkanban.gui;

import fr.prunetwork.atelierkanban.Constants;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.UIManager;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+kanban@prunetwork.fr)
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
