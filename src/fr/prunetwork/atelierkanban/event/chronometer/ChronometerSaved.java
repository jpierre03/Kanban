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
package fr.prunetwork.atelierkanban.event.chronometer;

import java.util.StringTokenizer;

import fr.prunetwork.atelierkanban.entities.Chronometer;
import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.storage.EventStore;
import fr.prunetwork.atelierkanban.utilities.DateFormatter;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class ChronometerSaved extends AbstractChronometerEvent {

	private Chronometer chronometer;

	public ChronometerSaved() {
		chronometer = Chronometer.getChronometer();
	}

	/**
	 * @return the c
	 */
	public Chronometer getChronometer() {
		return chronometer;
	}

	@Override
	public StringBuilder toSave() {
		StringBuilder sb = EventStore.genericLine();

		sb.append(this.getClass().getSimpleName());
		sb.append("|");
		sb.append(new DateFormatter(getChronometer().getBeginDate()).toHHMMSS());
		sb.append("|");
		sb.append(getChronometer().read());
		sb.append("|");
		sb.append(getChronometer().toString());
		sb.append("\n");

		return sb;
	}

	@Override
	public Event toLoad(StringTokenizer stringTokenizer) {
		ChronometerSaved event = null;

		event = new ChronometerSaved();

		return event;
	}
}
