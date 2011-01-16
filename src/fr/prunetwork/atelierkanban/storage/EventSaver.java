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

import fr.prunetwork.atelierkanban.Constants;
import fr.prunetwork.atelierkanban.event.Event;
import java.util.Date;

import fr.prunetwork.atelierkanban.entities.Chronometer;
import fr.prunetwork.atelierkanban.event.EventDispatcher;
import fr.prunetwork.atelierkanban.event.Save;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerSaved;
import fr.prunetwork.atelierkanban.event.kanban.KanbanAdded;
import fr.prunetwork.atelierkanban.event.kanban.KanbanRemoved;
import fr.prunetwork.atelierkanban.storage.writer.StoreDataToFile;
import fr.prunetwork.atelierkanban.utilities.DateFormatter;
import org.lsis.haimes.patterns.observer.Observer;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public final class EventSaver implements Observer {

    public EventSaver() {
        EventDispatcher.getEventDispatcher().registerObserver(this);
    }

    private static StringBuilder genericLine() {
        StringBuilder sb = new StringBuilder();

        Date d = new Date();
        sb.append(d.getTime());
        sb.append("|");
        sb.append(new DateFormatter(d).toHHMMSS());
        sb.append("|");

        return sb;
    }

    private static void kanbanAdded(int currentKanbanCount) {
        StringBuilder sb = genericLine();

        sb.append(KanbanAdded.class.getSimpleName());
        sb.append("|");
        sb.append(currentKanbanCount);
        sb.append("\n");

        StoreDataToFile.getStoreToFile().add(sb.toString());
    }

    private static void kanbanRemoved(int currentKanbanCount) {
        StringBuilder sb = genericLine();

        sb.append(KanbanRemoved.class.getSimpleName());
        sb.append("|");
        sb.append(currentKanbanCount);
        sb.append("\n");

        StoreDataToFile.getStoreToFile().add(sb.toString());
    }

    private static void saveTime(Chronometer c) {
        StringBuilder sb = genericLine();

        sb.append(Save.class.getSimpleName());
        sb.append("|");
        sb.append(new DateFormatter(c.getBeginDate()).toHHMMSS());
        sb.append("|");
        sb.append(c.read());
        sb.append("|");
        sb.append(c.toString());
        sb.append("\n");

        StoreDataToFile.getStoreToFile().add(sb.toString());
    }

    /**
     * 
     * @param event
     */
    @Override
    public void notify(Event event) {
        if (event instanceof KanbanAdded) {
            KanbanAdded ka = (KanbanAdded) event;
            kanbanAdded(ka.getKanbanCount());
        }
        if (event instanceof KanbanRemoved) {
            KanbanRemoved kr = (KanbanRemoved) event;
            kanbanRemoved(kr.getKanbanCount());
        }
        if (event instanceof ChronometerSaved) {
            ChronometerSaved cs = (ChronometerSaved) event;
            saveTime(cs.getChronometer());
        }
    }
}
