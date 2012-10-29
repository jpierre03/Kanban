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

package fr.prunetwork.atelierkanban.entities;

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.event.EventDispatcher;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerReset;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerStart;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerStop;
import fr.prunetwork.patterns.observer.Observer;

import java.util.Date;

/**
 * Gestion d'un chronomètre.
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class Chronometer
		implements Observer {

	private Date beginDate, endDate;
	private boolean isWaiting;
	private static Chronometer c = new Chronometer();

	public static Chronometer getChronometer() {
		return c;
	}

	private Chronometer() {
		isWaiting = true;
		beginDate = null;
		EventDispatcher.getEventDispatcher().registerObserver(this);
	}

	/** Change d'état courant pour se placer en mode d'attente si et seulement si enAttente est true. */
	private void setWaitingState(boolean waitingState) {
		if (isWaiting != waitingState) {
			if (isWaiting) {
				beginDate = new Date();
			} else {
				endDate = new Date();
			}
			isWaiting = waitingState;
		}
	}

	/** Déclenche le chronométrage */
	private void start() {
		setWaitingState(false);
	}

	/** Arrête le chronométrage */
	private void stop() {
		setWaitingState(true);
	}

	/** Remet à zéro le chronomètre */
	private void reset() {
		beginDate = null;
		isWaiting = true;
	}

	/**
	 * Retourne le nombre de secondes qui se sont écoulées entre l'appel à "start" et l'instant courant (si le
	 * chronométrage n'a pas encore été arrêté) ou l'instant d'arrêt du chronométrage.
	 *
	 * @return time in second since start
	 */
	public int read() {
		long upperLevel;

		if (getBeginDate() == null) {            /* Le chronomètre n'a pas été déclenché depuis son
			initialisation ou sa réinitialisation. */
			return 0;
		} else {
			if (isWaiting) {
				upperLevel = getEndDate().getTime();
			} else {
				upperLevel = new Date().getTime();
			}

			return (int) (upperLevel - getBeginDate().getTime())
				   / 1000;
		}
	}

	/** @return the beginDate */
	public Date getBeginDate() {
		return beginDate;
	}

	/** @return the endDate */
	public Date getEndDate() {
		Date aDate = null;
		if (endDate != null) {
			aDate = endDate;
		} else {
			aDate = new Date();
		}
		return aDate;
	}

	@Override
	public String toString() {
		int elapsedTime = read();

		int hour = elapsedTime / 3600;
		int min = elapsedTime / 60 - hour * 60;
		int sec = elapsedTime - min * 60 - hour * 60 * 60;

		return hour + ":" + min + ":" + sec;
	}

	public void notify(Event event) {
		if (event instanceof ChronometerStart) {
			this.start();
		}
		if (event instanceof ChronometerStop) {
			this.stop();
		}
		if (event instanceof ChronometerReset) {
			this.reset();
		}
	}
}
