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

import fr.prunetwork.atelierkanban.KanbanPlanning;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class KanbanPlanningPanel extends javax.swing.JPanel {

    KanbanPlanning kp;

    /** Creates new form KanbanPlanning */
    public KanbanPlanningPanel() {
        initComponents();
        kp = new KanbanPlanning(7, 2, 15, 4);

        redSpinner.setValue(kp.getUpperLevel_red());
        greenSpinner.setValue(kp.getLowerLevel_green());
        totalKanbanSpinner.setValue(kp.getMaxKanban());
        refresh();
    }

    public void refresh() {
        kanbanEditorPane.setContentType("text/html");
        kanbanEditorPane.setText(kp.toHtml());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        KanbanPlanningPanel kpp = new KanbanPlanningPanel();
        frame.add(kpp);
        frame.pack();

        frame.setLocationRelativeTo(frame.getParent());
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

        kanbanScrollPane = new javax.swing.JScrollPane();
        kanbanEditorPane = new javax.swing.JEditorPane();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        greenSpinner = new javax.swing.JSpinner();
        redSpinner = new javax.swing.JSpinner();
        totalKanbanSpinner = new javax.swing.JSpinner();

        setLayout(new java.awt.GridBagLayout());

        kanbanEditorPane.setContentType("text/html");
        kanbanEditorPane.setEditable(false);
        kanbanEditorPane.setText("");
        kanbanScrollPane.setViewportView(kanbanEditorPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(kanbanScrollPane, gridBagConstraints);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(addButton, gridBagConstraints);

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(removeButton, gridBagConstraints);

        greenSpinner.setToolTipText("Green Index");
        greenSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                greenSpinnerStateChanged(evt);
            }
        });
        greenSpinner.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                greenSpinnerPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        add(greenSpinner, gridBagConstraints);

        redSpinner.setToolTipText("Red Index");
        redSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                redSpinnerStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        add(redSpinner, gridBagConstraints);

        totalKanbanSpinner.setToolTipText("Total Kanban");
        totalKanbanSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                totalKanbanSpinnerStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(totalKanbanSpinner, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        kp.addKanban();
        refresh();
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        kp.removeKanban();
        refresh();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void greenSpinnerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_greenSpinnerPropertyChange
        kp.setLowerLevel_green((int) greenSpinner.getValue());
        refresh();
    }//GEN-LAST:event_greenSpinnerPropertyChange

    private void greenSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_greenSpinnerStateChanged
        kp.setLowerLevel_green((int) greenSpinner.getValue());
        refresh();
    }//GEN-LAST:event_greenSpinnerStateChanged

    private void redSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_redSpinnerStateChanged
        kp.setUpperLevel_red((int) redSpinner.getValue());
        refresh();
    }//GEN-LAST:event_redSpinnerStateChanged

    private void totalKanbanSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_totalKanbanSpinnerStateChanged
        kp.setMaxKanban((int) totalKanbanSpinner.getValue());
        refresh();
    }//GEN-LAST:event_totalKanbanSpinnerStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JSpinner greenSpinner;
    private javax.swing.JEditorPane kanbanEditorPane;
    private javax.swing.JScrollPane kanbanScrollPane;
    private javax.swing.JSpinner redSpinner;
    private javax.swing.JButton removeButton;
    private javax.swing.JSpinner totalKanbanSpinner;
    // End of variables declaration//GEN-END:variables
}
