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

package fr.prunetwork.atelierkanban.storage;

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.event.EventDispatcher;
import fr.prunetwork.atelierkanban.storage.writer.StoreDataToFile;
import fr.prunetwork.atelierkanban.utilities.DateFormatter;
import fr.prunetwork.patterns.observer.Observer;

import java.util.Date;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class EventStore
		implements Observer {

	public EventStore() {
		EventDispatcher.getEventDispatcher().registerObserver(this);
	}

	public static StringBuilder genericLine() {
		StringBuilder sb = new StringBuilder(30);

		Date d = new Date();
		sb.append(d.getTime());
		sb.append("|");
		sb.append(new DateFormatter(d).toHHMMSS());
		sb.append("|");

		return sb;
	}

	/** @param event  */
	@Override
	public void notify(Event event) {
		saveNotification(event);
	}

	private void saveNotification(Event event) {
		if (event != null) {
			StoreDataToFile.getStoreToFile().add(event.toSave());
		}
	}
}
