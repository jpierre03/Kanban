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
package fr.prunetwork.network;

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.event.EventDispatcher;
import fr.prunetwork.network.client.MyClient;
import fr.prunetwork.patterns.observer.Observer;

import java.io.IOException;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class NetworkEventStore
		implements Observer {

	private MyClient mc = null; //initialisation de la variable locale

	public NetworkEventStore(String hostName, int portNumber) {

		try {
			mc = new MyClient(hostName, portNumber);
		} catch (IOException e) {
			if (mc != null) {
				mc.fermer();
				mc = null; //on perd l'objet
			}
		}
		EventDispatcher.getEventDispatcher().registerObserver(this);
	}

	/** @param event  */
	@Override
	public void notify(Event event) {
		sendNotification(event);
	}

	private void sendNotification(Event event) {
		if (event != null
			&& mc != null
			&& mc.isConnected()) {
			mc.writeToServer(event.toSave().toString());
		}
	}
}
