/*
 *  Copyright (C) 2010 Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
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
package fr.prunetwork.atelierkanban;

import fr.prunetwork.atelierkanban.event.Event;
import javax.swing.JFrame;
import javax.swing.UIManager;

import fr.prunetwork.atelierkanban.gui.MainFrame;
import fr.prunetwork.atelierkanban.gui.SplashScreenWindows;
import fr.prunetwork.atelierkanban.storage.EventSaver;
import fr.prunetwork.atelierkanban.storage.reader.ExtractEventFromFile;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Start the Replay-Application
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class Replay {

	public static void main(String[] args) {
		String fichier = Constants.SHORT_FILE;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		SplashScreenWindows splashScreen = new SplashScreenWindows();

		MainFrame frame = new MainFrame();
		EventSaver es = new EventSaver();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();

		try {
			Thread.sleep(250);
		} catch (InterruptedException ex) {
		}
		Collection<Event> createEventCollection = ExtractEventFromFile.createEventCollection(fichier);

		frame.setLocationRelativeTo(frame.getParent());
		frame.setVisible(true);
		splashScreen.masquer();


		for (Event event : createEventCollection) {
			frame.getKanbanPlanningPanel().notify(event);
			try {
				Thread.sleep(750);
			} catch (InterruptedException ex) {
				Logger.getLogger(Replay.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private Replay() {
	}
}
