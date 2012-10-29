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
package fr.prunetwork.atelierkanban.storage.reader;

import fr.prunetwork.atelierkanban.Constants;
import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.gui.MainFrame;
import fr.prunetwork.atelierkanban.storage.EventStore;

import javax.swing.JFrame;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jpierre03+teamwar@prunetwork.fr
 * @author garciaf
 */
public class ExtractEventFromFileTest {

	public static void main(String[] args) {
		String fichier = Constants.SHORT_FILE;

		MainFrame frame = new MainFrame();
		EventStore es = new EventStore();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		Collection<Event> createEventCollection = ExtractEventFromFile.createEventCollection(fichier);

		frame.setLocationRelativeTo(frame.getParent());
		frame.setVisible(true);

		for (Event event : createEventCollection) {
			frame.notifyKanbanPlanningPanels(event);
			try {
				Thread.sleep(750);
			} catch (InterruptedException ex) {
				Logger.getLogger(ExtractEventFromFileTest.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private ExtractEventFromFileTest() {
	}
}
