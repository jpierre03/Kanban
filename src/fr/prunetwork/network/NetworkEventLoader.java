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

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.storage.reader.ExtractEventFromFile;
import fr.prunetwork.network.client.MyClient;
import fr.prunetwork.patterns.observer.Observable;
import fr.prunetwork.patterns.observer.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class NetworkEventLoader
		implements Observable {

	private MyClient mc = null; //initialisation de la variable locale

	public NetworkEventLoader(String hostName, int portNumber) {

		try {
			mc = new MyClient(hostName, portNumber);
		} catch (IOException e) {
			if (mc != null) {
				mc.fermer();
				mc = null; //on perd l'objet
			}
		}

		Runnable read = new Runnable() {

			@Override
			public void run() {
				while (mc != null && mc.isConnected()) {
					try {
						Event eventFromLine = ExtractEventFromFile.eventFromLine(mc.readFromServer());
						notifyObservers(eventFromLine);
						System.out.println(eventFromLine);
					} catch (Exception ex) {
						Logger.getLogger(NetworkEventLoader.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};

		new Thread(read).start();
	}

	private Collection<Observer> observers = new ArrayList<Observer>(10);

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void unregisterObserver(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers(Event object) {
		for (Observer observer : observers) {
			observer.notify(object);
		}
	}
}
