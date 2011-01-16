/*
 *  Copyright (C) 2010 Team-W@R (team-war@prunetwork.fr)
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
package fr.prunetwork.atelierkanban.storage.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerReset;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerSaved;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerStart;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerStop;
import fr.prunetwork.atelierkanban.event.kanban.KanbanAdd;
import fr.prunetwork.atelierkanban.event.kanban.KanbanAdded;
import fr.prunetwork.atelierkanban.event.kanban.KanbanNameWrapper;
import fr.prunetwork.atelierkanban.event.kanban.KanbanRemove;
import fr.prunetwork.atelierkanban.event.kanban.KanbanRemoved;
import fr.prunetwork.atelierkanban.utilities.DateFormatter;

/**
 *
 * @author jpierre03+teamwar@prunetwork.fr
 * @author garciaf
 */
public class ExtractEventFromFile {

	public static Collection<Event> createEventCollection(String fichier) {
		Collection<Event> events = new ArrayList<Event>(50);

		//lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;

//            // remove the first line
//            br.readLine();

			while ((ligne = br.readLine()) != null) {
				ligne = formatString(ligne);

				StringTokenizer stringTokenizer = new StringTokenizer(ligne, "|");

				Event event = instanciateEventFromTokenizer(stringTokenizer);
				if (event != null) {
					System.out.println(event + "---------" + ligne);
					events.add(event);
				} else {
					System.out.println("---------" + ligne);
				}
			}
			br.close();
		} catch (Exception e) {
		}
		return events;
	}

	private static Event instanciateEventFromTokenizer(StringTokenizer stringTokenizer) throws Exception {
		Event event = null;
		Date date;
		//token1 - date (timestamp)
		if (stringTokenizer.hasMoreTokens()) {
			String dateString = stringTokenizer.nextToken();
			date = new DateFormatter(dateString);

			//token2 - date human
			if (stringTokenizer.hasMoreTokens()) {
				String dateHumanString = stringTokenizer.nextToken();

				//token3 - class name
				if (stringTokenizer.hasMoreTokens()) {
					String operationString = stringTokenizer.nextToken();

					for (Event loadableEvent : loadableEvent()) {
						if (loadableEvent.getClass().getSimpleName().equals(operationString)) {
							event = loadableEvent.toLoad(stringTokenizer);
						}
					}
				}
			}
		}
		return event;
	}

	private static Collection<Event> loadableEvent() {
		Collection<Event> loadableEvents = new ArrayList<Event>(10);

		//Chronometer
		loadableEvents.add(new ChronometerReset());
		loadableEvents.add(new ChronometerSaved());
		loadableEvents.add(new ChronometerStart());
		loadableEvents.add(new ChronometerStop());

		//kanban
		loadableEvents.add(new KanbanAdd());
		loadableEvents.add(new KanbanAdded(10));
		loadableEvents.add(new KanbanNameWrapper());
		loadableEvents.add(new KanbanRemove());
		loadableEvents.add(new KanbanRemoved(10));

		return loadableEvents;
	}

	private static String formatString(String ligne) {
//        ligne = ligne.replace("_", "");
		return ligne;
	}

	private ExtractEventFromFile() {
	}
}
