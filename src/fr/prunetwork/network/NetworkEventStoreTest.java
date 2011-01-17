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
package fr.prunetwork.network;

import fr.prunetwork.atelierkanban.Constants;
import fr.prunetwork.atelierkanban.event.kanban.KanbanAdd;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class NetworkEventStoreTest {

	public static void main(String args[]) {
		NetworkEventStore nes = new NetworkEventStore(Constants.DEFAULT_HOSTNAME, Constants.DEFAULT_PORT_NUMBER);

		nes.notify(new KanbanAdd());
	}

	private NetworkEventStoreTest() {
	}
}
