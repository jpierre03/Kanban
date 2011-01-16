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
package fr.prunetwork.atelierkanban.storage;

import java.util.Date;

import org.lsis.haimes.patterns.observer.Observer;

import fr.prunetwork.atelierkanban.entities.Chronometer;
import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.event.EventDispatcher;
import fr.prunetwork.atelierkanban.event.control.Save;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerSaved;
import fr.prunetwork.atelierkanban.event.kanban.KanbanAdded;
import fr.prunetwork.atelierkanban.event.kanban.KanbanRemoved;
import fr.prunetwork.atelierkanban.storage.writer.StoreDataToFile;
import fr.prunetwork.atelierkanban.utilities.DateFormatter;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public final class EventSaver implements Observer {

	public EventSaver() {
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

	/**
	 *
	 * @param event
	 */
	@Override
	public void notify(Event event) {
		saveNotification(event);
	}

	private void saveNotification(Event event) {
		if(event!=null){
			StoreDataToFile.getStoreToFile().add(event.toSave());
		}
//		if (event instanceof KanbanAdded) {
//			KanbanAdded ka = (KanbanAdded) event;
//			StoreDataToFile.getStoreToFile().add(ka.toSave());
//		}
//		if (event instanceof KanbanRemoved) {
//			KanbanRemoved kr = (KanbanRemoved) event;
//			StoreDataToFile.getStoreToFile().add(kr.toSave());
//		}
//		if (event instanceof ChronometerSaved) {
//			ChronometerSaved cs = (ChronometerSaved) event;
//			StoreDataToFile.getStoreToFile().add(cs.toSave());
//		}
	}
}
