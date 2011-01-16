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
package fr.prunetwork.atelierkanban.gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.storage.reader.ExtractEventFromFile;
import fr.prunetwork.atelierkanban.storage.writer.StoreDataToFile;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 * @author garciaf
 */
public class MainFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 2010090927L;
	private static final JFileChooser chooser = new JFileChooser();

	/** Creates new form MainFrame */
	public MainFrame() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        kanbanPlanningPanel1 = new fr.prunetwork.atelierkanban.gui.KanbanPlanningPanel();
        chronometerPanel1 = new fr.prunetwork.atelierkanban.gui.ChronometerPanel();
        controlPanel = new fr.prunetwork.atelierkanban.gui.ControlPanel();
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
        getContentPane().add(kanbanPlanningPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(chronometerPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(controlPanel, gridBagConstraints);

        fileMenu.setText("File");

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setText("Save as ...");
        saveAsMenuItem.setActionCommand("SaveAs");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
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

					for (final Event event : createEventCollection) {
						//notify UI component, have to be done in EDT
						try {
							EventQueue.invokeAndWait(new Runnable() {

								public void run() {
									getKanbanPlanningPanel().notify(event);
								}
							});
						} catch (InterruptedException ex) {
							Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
						} catch (InvocationTargetException ex) {
							Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
						}
						try {
							Thread.sleep(150);
						} catch (InterruptedException ex) {
							Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			};
			t.start();
		}
	}//GEN-LAST:event_openMenuItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private fr.prunetwork.atelierkanban.gui.ChronometerPanel chronometerPanel1;
    private fr.prunetwork.atelierkanban.gui.ControlPanel controlPanel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private fr.prunetwork.atelierkanban.gui.KanbanPlanningPanel kanbanPlanningPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    // End of variables declaration//GEN-END:variables

	public KanbanPlanningPanel getKanbanPlanningPanel() {
		return kanbanPlanningPanel1;
	}
}
