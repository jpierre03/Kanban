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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.table.DefaultTableModel;

import fr.prunetwork.atelierkanban.entities.Chronometer;
import fr.prunetwork.atelierkanban.event.EventDispatcher;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerReset;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerSaved;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerStart;
import fr.prunetwork.atelierkanban.event.chronometer.ChronometerStop;
import fr.prunetwork.atelierkanban.utilities.DateFormatter;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class ChronometerPanel
		extends javax.swing.JPanel {

	private static final long serialVersionUID = 201001081652L;
	private static final Chronometer c = Chronometer.getChronometer();
	private final Timer timer = new Timer();

	/** Creates new form ChronometerPanel */
	public ChronometerPanel() {
		initComponents();

		final Runnable doUpdateCursor = new Runnable() {

			@Override
			public void run() {

				timeLabel.setText(c.toString());
			}
		};

		TimerTask updateCursorTask = new TimerTask() {

			@Override
			public void run() {
				EventQueue.invokeLater(doUpdateCursor);
			}
		};
		timer.schedule(updateCursorTask, 0, 500);
	}

	public int read() {
		return c.read();
	}

	public void addCurrentDateInTable() {
		synchronized (getTimeTable().getModel()) {
			int modelSize = getTimeTable().getModel().getRowCount();

			if (c.getBeginDate() != null) {

				EventDispatcher.getEventDispatcher().notifyObservers(new ChronometerStop());

				if (getTimeTable().getModel() instanceof DefaultTableModel) {
					DefaultTableModel model = (DefaultTableModel) getTimeTable().
							getModel();

					// Add a row in the model
					model.insertRow(0, new Object[]{
								modelSize,
								new DateFormatter(
								(c.getBeginDate())).toHHMMSS(),
								new DateFormatter(
								(c.getEndDate())).toHHMMSS(),
								c.read(),
								c.toString()});
					getTimeTable().repaint();
				}
				EventDispatcher.getEventDispatcher().notifyObservers(new ChronometerSaved());
				EventDispatcher.getEventDispatcher().notifyObservers(new ChronometerReset());

			}
		}
	}

	/**
	 * @return the timeTable
	 */
	public javax.swing.JTable getTimeTable() {
		return timeTable;
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

        timeLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        timeScrollPane = new javax.swing.JScrollPane();
        timeTable = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        timeLabel.setFont(timeLabel.getFont().deriveFont(timeLabel.getFont().getStyle() | java.awt.Font.BOLD, timeLabel.getFont().getSize()+10));
        timeLabel.setText("hh:mm:ss");
        timeLabel.setMaximumSize(new java.awt.Dimension(200, 50));
        timeLabel.setMinimumSize(new java.awt.Dimension(200, 50));
        timeLabel.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(timeLabel, gridBagConstraints);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(saveButton, gridBagConstraints);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(startButton, gridBagConstraints);

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(stopButton, gridBagConstraints);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(resetButton, gridBagConstraints);

        timeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number", "Start", "End", "Duration", "(hh:mm:ss)"
            }
        ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = -3112302369139891665L;
			boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        timeTable.setColumnSelectionAllowed(true);
        timeScrollPane.setViewportView(timeTable);
        timeTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(timeScrollPane, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

	private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
		EventDispatcher.getEventDispatcher().notifyObservers(new ChronometerStart());
	}//GEN-LAST:event_startButtonActionPerformed

	private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
		EventDispatcher.getEventDispatcher().notifyObservers(new ChronometerStop());
	}//GEN-LAST:event_stopButtonActionPerformed

	private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
		EventDispatcher.getEventDispatcher().notifyObservers(new ChronometerReset());
	}//GEN-LAST:event_resetButtonActionPerformed

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
		addCurrentDateInTable();
	}//GEN-LAST:event_saveButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton resetButton;
    javax.swing.JButton saveButton;
    javax.swing.JButton startButton;
    javax.swing.JButton stopButton;
    javax.swing.JLabel timeLabel;
    javax.swing.JScrollPane timeScrollPane;
    javax.swing.JTable timeTable;
    // End of variables declaration//GEN-END:variables
}
