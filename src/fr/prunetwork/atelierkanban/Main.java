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
package fr.prunetwork.atelierkanban;

import javax.swing.JFrame;
import javax.swing.UIManager;

import fr.prunetwork.atelierkanban.gui.MainFrame;
import fr.prunetwork.atelierkanban.gui.SplashScreenWindows;
import fr.prunetwork.atelierkanban.storage.EventStore;

/**
 * Start the Application
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

        SplashScreenWindows splashScreen = new SplashScreenWindows();

        JFrame frame = new MainFrame();
        EventStore es=new EventStore();
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
}
