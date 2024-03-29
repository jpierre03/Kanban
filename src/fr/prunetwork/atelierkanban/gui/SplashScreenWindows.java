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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public final class SplashScreenWindows
		extends JWindow {

	/**
	 *
	 */
	private static final long serialVersionUID = 20100312L;

	/**
	 *
	 */
	public SplashScreenWindows() {
		build();

		afficher();
	}

	/**
	 *
	 */
	public void afficher() {
		setVisible(true);
	}

	/**
	 *
	 */
	public void masquer() {
		setVisible(false);
		dispose();
	}

	/**
	 *
	 */
	private void build() {
		final JLabel component = new javax.swing.JLabel();

		component.setIcon(new ImageIcon(getClass().getResource(
				Constants.SPLASH_SCREEN_IMAGE)));
		add(component);
		pack();
		setLocationRelativeTo(getParent());
	}
}
