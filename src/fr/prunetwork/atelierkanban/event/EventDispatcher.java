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
package fr.prunetwork.atelierkanban.event;

import java.util.ArrayList;
import java.util.Collection;
import org.lsis.haimes.patterns.observer.Observable;
import org.lsis.haimes.patterns.observer.Observer;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class EventDispatcher implements Observable, Observer {

    private static EventDispatcher eventDispatcher = new EventDispatcher();

    private EventDispatcher() {
    }
    private Collection<Observer> observers = new ArrayList<Observer>();

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Event object) {
        for (Observer observer : observers) {
            observer.notify(object);
        }
    }

    public void notify(Event event) {
        notifyObservers(event);
    }

    /**
     * @return the eventDispatcher
     */
    public static EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }
}
