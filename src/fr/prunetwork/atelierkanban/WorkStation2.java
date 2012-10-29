/*
 * Copyright (C) 2010-2012 Jean-Pierre PRUNARET
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.prunetwork.atelierkanban;

import fr.prunetwork.atelierkanban.gui.MainFrame;
import fr.prunetwork.atelierkanban.gui.SplashScreenWindows;
import fr.prunetwork.atelierkanban.storage.EventStore;
import fr.prunetwork.network.NetworkEventStore;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Start the Application
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class WorkStation2 {

	/** @param args the command line arguments */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		SplashScreenWindows splashScreen = new SplashScreenWindows();

		MainFrame frame = new MainFrame();
		frame.setProductNames("2G-H3", "NONE");
		EventStore es = new EventStore();
		NetworkEventStore nes = new NetworkEventStore(Constants.DEFAULT_HOSTNAME, Constants.DEFAULT_PORT_NUMBER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();

		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
		}

		splashScreen.masquer();
		frame.setLocationRelativeTo(frame.getParent());
		frame.setVisible(true);
	}

	private WorkStation2() {
	}
}
