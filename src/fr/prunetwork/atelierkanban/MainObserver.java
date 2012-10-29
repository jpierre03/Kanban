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
package fr.prunetwork.atelierkanban;

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.event.kanban.KanbanAdded;
import fr.prunetwork.atelierkanban.gui.ObservatorFrame;
import fr.prunetwork.atelierkanban.gui.SplashScreenWindows;
import fr.prunetwork.atelierkanban.storage.EventStore;
import fr.prunetwork.network.NetworkEventLoader;
import fr.prunetwork.patterns.observer.Observer;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Start the Application
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class MainObserver {

	/** @param args the command line arguments */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		SplashScreenWindows splashScreen = new SplashScreenWindows();

		final ObservatorFrame frame = new ObservatorFrame();
		EventStore es = new EventStore();
		NetworkEventLoader nel = new NetworkEventLoader(Constants.DEFAULT_HOSTNAME, Constants.DEFAULT_PORT_NUMBER);
//		nel.registerObserver(EventDispatcher.getEventDispatcher());

		Observer observer = new Observer() {

			@Override
			public void notify(final Event event) {
				if (EventQueue.isDispatchThread() != true) {
					Runnable notifyRunnable = new Runnable() {

						@Override
						public void run() {
//							frame.notifyKanbanPlanningPanels(event);
							if (event instanceof KanbanAdded) {
								KanbanAdded ka = (KanbanAdded) event;
								ka.setKanbanCount(ka.getKanbanCount() - 1);
								frame.notifyKanbanPlanningPanels(ka);
							} else {
								frame.notifyKanbanPlanningPanels(event);
							}
						}
					};
					try {
						EventQueue.invokeAndWait(notifyRunnable);
					} catch (InterruptedException ex) {
						Logger.getLogger(MainObserver.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InvocationTargetException ex) {
						Logger.getLogger(MainObserver.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};

		nel.registerObserver(observer);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();

		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
		}

		splashScreen.masquer();
		frame.setLocationRelativeTo(frame.getParent());
		frame.setVisible(true);
	}

	private MainObserver() {
	}
}
