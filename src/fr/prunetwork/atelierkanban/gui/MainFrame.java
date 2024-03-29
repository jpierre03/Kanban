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

package fr.prunetwork.atelierkanban.gui;

import fr.prunetwork.atelierkanban.Constants;
import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.storage.reader.ExtractEventFromFile;
import fr.prunetwork.atelierkanban.storage.writer.StoreDataToFile;

import javax.swing.JFileChooser;
import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 * @author garciaf
 */
public class MainFrame
		extends javax.swing.JFrame {

	private static final long         serialVersionUID = 2010090927L;
	private static final JFileChooser chooser          = new JFileChooser();

	/** Creates new form MainFrame */
	public MainFrame() {
		this.setTitle("Kankan-MainFrame");
		initComponents();

		kanbanPlanningPanel1.setProductName("1B-E2");
		kanbanPlanningPanel2.setProductName("1C-D2");
	}

	public void setProductNames(String product1, String product2) {
		kanbanPlanningPanel1.setProductName(product1);
		kanbanPlanningPanel2.setProductName(product2);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		kanbanPlanningPanel1 = new fr.prunetwork.atelierkanban.gui.KanbanPlanningPanel();
		chronometerPanel1 = new fr.prunetwork.atelierkanban.gui.ChronometerPanel();
		kanbanPlanningPanel2 = new fr.prunetwork.atelierkanban.gui.KanbanPlanningPanel();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		saveAsMenuItem = new javax.swing.JMenuItem();
		openMenuItem = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
		getContentPane().add(kanbanPlanningPanel1, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		getContentPane().add(chronometerPanel1, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
		getContentPane().add(kanbanPlanningPanel2, gridBagConstraints);

		fileMenu.setText("File");

		saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
																		 java.awt.event.InputEvent.CTRL_MASK));
		saveAsMenuItem.setText("Save as ...");
		saveAsMenuItem.setActionCommand("SaveAs");
		saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveAsMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(saveAsMenuItem);

		openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
																	   java.awt.event.InputEvent.CTRL_MASK));
		openMenuItem.setText("Open simulation");
		openMenuItem.setToolTipText("Open a saved execution, and play it again");
		openMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(openMenuItem);

		menuBar.add(fileMenu);

		editMenu.setText("Edit");
		menuBar.add(editMenu);

		setJMenuBar(menuBar);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);

		int returnVal = chooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				StoreDataToFile.getStoreToFile().commit(chooser.getSelectedFile().getAbsolutePath());
			} catch (IOException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}//GEN-LAST:event_saveAsMenuItemActionPerformed

	private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);

		int returnVal = chooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
// long work
			Thread t = new Thread() {

				@Override
				public void run() {

					Collection<Event> createEventCollection =
							ExtractEventFromFile.createEventCollection(
									chooser.getSelectedFile().getAbsolutePath());

					initKanbanPlannings();
					for (final Event event : createEventCollection) {
						//notify UI component, have to be done in EDT
						try {
							EventQueue.invokeAndWait(new Runnable() {

								@Override
								public void run() {
									notifyKanbanPlanningPanels(event);
								}
							});
						} catch (InterruptedException ex) {
							Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
						} catch (InvocationTargetException ex) {
							Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
						}
						if (Constants.MILISECONDS_BETWEEN_LOADED_EVENTS > 0) {
							try {
								Thread.sleep(Constants.MILISECONDS_BETWEEN_LOADED_EVENTS);
							} catch (InterruptedException ex) {
								Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					}
				}
			};
			t.start();
		}
	}//GEN-LAST:event_openMenuItemActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private fr.prunetwork.atelierkanban.gui.ChronometerPanel    chronometerPanel1;
	private javax.swing.JMenu                                   editMenu;
	private javax.swing.JMenu                                   fileMenu;
	private fr.prunetwork.atelierkanban.gui.KanbanPlanningPanel kanbanPlanningPanel1;
	private fr.prunetwork.atelierkanban.gui.KanbanPlanningPanel kanbanPlanningPanel2;
	private javax.swing.JMenuBar                                menuBar;
	private javax.swing.JMenuItem                               openMenuItem;
	private javax.swing.JMenuItem                               saveAsMenuItem;
	// End of variables declaration//GEN-END:variables

	public void notifyKanbanPlanningPanels(Event event) {
		kanbanPlanningPanel1.notify(event);
		kanbanPlanningPanel2.notify(event);
	}

	public void initKanbanPlannings() {
		kanbanPlanningPanel1.getKp().initValues();
		kanbanPlanningPanel2.getKp().initValues();
	}
}
