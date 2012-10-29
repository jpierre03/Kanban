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

package fr.prunetwork.network;

import fr.prunetwork.atelierkanban.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class NetworkEventLoaderTest {

	public static void main(String args[]) {
		try {
			NetworkEventLoader nel = new NetworkEventLoader(Constants.DEFAULT_HOSTNAME, Constants.DEFAULT_PORT_NUMBER);

		} catch (Exception ex) {

		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(NetworkEventLoaderTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private NetworkEventLoaderTest() {
	}
}
