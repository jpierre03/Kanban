/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.prunetwork.atelierkanban;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Gestion d'un chronomètre.
 * @author jpierre03
 */
public class Chronometer {

	private Date beginDate, endDate;
	private boolean isWaiting;
	private Collection<Integer> times = new ArrayList<Integer>();

	public Chronometer() {
		isWaiting = true;
		beginDate = null;
	}

	/**
	 * Change d'état courant pour se placer en mode d'attente si et seulement
	 * si enAttente est true.
	 */
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

	/**
	 * Déclenche le chronométrage
	 */
	public void start() {
		setWaitingState(false);
	}

	/**
	 * Arrête le chronométrage
	 */
	public void stop() {
		setWaitingState(true);
	}

	/**
	 * Remet à zéro le chronomètre
	 */
	public void reset() {
		beginDate = null;
		isWaiting = true;
	}

	/**
	 * Retourne le nombre de secondes qui se sont écoulées entre l'appel à
	 * "start" et l'instant courant (si le chronométrage n'a pas encore
	 * été arrêté) ou l'instant d'arrêt du chronométrage.
	 * @return time in second since start
	 */
	public int read() {
		long upperLevel;

		if (beginDate == null) {
			/* Le chronomètre n'a pas été déclenché depuis son
			initialisation ou sa réinitialisation. */
			return 0;
		} else {
			if (isWaiting) {
				upperLevel = endDate.getTime();
			} else {
				upperLevel = new Date().getTime();
			}

			return (int) (upperLevel - beginDate.getTime()) / 1000;
		}
	}

	/**
	 * @return the times
	 */
	public Collection<Integer> getTimes() {
		return Collections.unmodifiableCollection(times);
	}

	public void save(){
		times.add(read());
	}

	public static void main(String args[]) {
		Chronometer chronometer = new Chronometer();
		chronometer.start();
		int i = 0;
		while (i < 10) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(Chronometer.class.getName()).log(Level.SEVERE, null, ex);
			}
			System.out.println(chronometer.read());
		}
	}
}
