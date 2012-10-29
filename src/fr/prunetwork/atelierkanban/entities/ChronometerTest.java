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
package fr.prunetwork.atelierkanban.entities;

import fr.prunetwork.atelierkanban.event.EventDispatcher;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerStart;

import java.util.logging.Level;
import java.util.logging.Logger;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class ChronometerTest {

	public static void main(String args[]) {
		Chronometer chronometer = Chronometer.getChronometer();
		EventDispatcher.getEventDispatcher().notifyObservers(new ChronometerStart());
		int i = 0;
		while (i < 10) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(Chronometer.class.getName()).
						log(Level.SEVERE, null, ex);
			}
			System.out.println(chronometer.read());
		}
	}

	private ChronometerTest() {
	}
}
