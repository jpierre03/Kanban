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
package fr.prunetwork.atelierkanban.event.kanban.index;

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.storage.EventStore;
import java.util.StringTokenizer;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class KanbanGreenIndexChanged extends AbstractKanbanIndexEvent {

	private int greenIndexLevel;

	public KanbanGreenIndexChanged(int greenIndexLevel) {
		this.greenIndexLevel = greenIndexLevel;
	}

	/**
	 * @return the kanbanCount
	 */
	public int getGreenIndexLevel() {
		return greenIndexLevel;
	}

	@Override
	public StringBuilder toSave() {
		StringBuilder sb = EventStore.genericLine();

		sb.append(this.getClass().getSimpleName());
		sb.append("|");
		sb.append(greenIndexLevel);
		sb.append("|");
		sb.append(getProductName());
		sb.append("\n");

		return sb;
	}

	@Override
	public Event toLoad(StringTokenizer stringTokenizer) {
		KanbanGreenIndexChanged event = null;

		if (stringTokenizer.hasMoreTokens()) {
			String count = stringTokenizer.nextToken();
			event = new KanbanGreenIndexChanged(Integer.parseInt(count));

			if (stringTokenizer.hasMoreTokens()) {
				String productName = stringTokenizer.nextToken();
				event.setProductName(productName);
			}
		}
		return event;
	}
}
