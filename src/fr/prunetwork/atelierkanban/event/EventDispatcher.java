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
package fr.prunetwork.atelierkanban.event;

import fr.prunetwork.patterns.observer.Observable;
import fr.prunetwork.patterns.observer.Observer;

import java.util.ArrayList;
import java.util.Collection;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class EventDispatcher
		implements Observable, Observer {

	private static EventDispatcher eventDispatcher = new EventDispatcher();

	private EventDispatcher() {
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

	@Override
	public void notify(Event event) {
		notifyObservers(event);
	}

	/** @return the eventDispatcher */
	public static EventDispatcher getEventDispatcher() {
		return eventDispatcher;
	}
}
