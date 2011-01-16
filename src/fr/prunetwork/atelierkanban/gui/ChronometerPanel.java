/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChronometerPanel.java
 *
 * Created on 7 janv. 2011, 16:11:16
 */
package fr.prunetwork.atelierkanban.gui;

import fr.prunetwork.atelierkanban.Chronometer;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.UIManager;

/**
 *
 * @author jpierre03
 */
public class ChronometerPanel extends javax.swing.JPanel {

	protected Chronometer c;
	private final Timer timer = new Timer();

	/** Creates new form ChronometerPanel */
	public ChronometerPanel() {
		initComponents();
		c = new Chronometer();

		final Runnable doUpdateCursor = new Runnable() {

			public void run() {
				int elapsedTime = c.read();

				int hour = (int) Math.floor(elapsedTime / 3600);
				int min = (int) Math.floor(elapsedTime / 60);
				int sec = (int) Math.floor(elapsedTime) - min * 60 - hour * 60 * 60;

				timeLabel.setText(hour + ":" + min + ":" + sec);
			}
		};

		TimerTask updateCursorTask = new TimerTask() {

			public void run() {
				EventQueue.invokeLater(doUpdateCursor);
			}
		};

		timer.schedule(updateCursorTask, 0, 500);
	}

	public int read() {
		return c.read();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		JFrame frame = new JFrame();
		ChronometerPanel cp = new ChronometerPanel();
		frame.add(cp);
		frame.pack();
		frame.setVisible(true);
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
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        timeLabel.setFont(timeLabel.getFont().deriveFont(timeLabel.getFont().getStyle() | java.awt.Font.BOLD, timeLabel.getFont().getSize()+10));
        timeLabel.setText("hh:mm:ss");
        timeLabel.setMaximumSize(new java.awt.Dimension(200, 50));
        timeLabel.setMinimumSize(new java.awt.Dimension(200, 50));
        timeLabel.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 3;
        add(timeLabel, gridBagConstraints);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
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
        gridBagConstraints.gridy = 2;
        add(resetButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

	private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
		c.start();
	}//GEN-LAST:event_startButtonActionPerformed

	private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
		c.stop();
	}//GEN-LAST:event_stopButtonActionPerformed

	private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
		c.reset();
	}//GEN-LAST:event_resetButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton resetButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}