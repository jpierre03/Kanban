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
package fr.prunetwork.network.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class MyServerMultiClientTest {

	/**
	 * Constructeur par défaut
	 */
	public MyServerMultiClientTest() {
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
//	Pour tester un protocole simple de communication
//	Bien entendu il faut que serveur et client soient compatibles

		/**initialisation du serveur*/
		MyServerMultiClient ms;
		if (args.length > 0) {
			/** Récupère le n° de port depsui la console*/
			ms = new MyServerMultiClient(Integer.parseInt(args[0]));
		} else {
			ms = new MyServerMultiClient(0);
		}
		System.out.println("On communique");

		/** Contient la ligne a échanger*/
		String ligne;
		while (ms != null && ms.getOnContinue()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(MyServerMultiClientTest.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		System.out.println("On termine");
		ms.end();
	}
}
