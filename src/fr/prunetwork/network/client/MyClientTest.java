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

package fr.prunetwork.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class MyClientTest {

	/** Constructeur de la classe de test */
	public MyClientTest() {
	}

	/**
	 * Le programme commence ici. Le test se fait en réalisant un connexion du client sur un serveur en console
	 *
	 * @param args Ce sont les paramètres de la ligne de commande
	 */
	public static void main(String[] args) {
// Pour tester un protocole simple de communication
// Bien entendu il faut que serveur et client soient compatibles

// initialisation du client
// on peut passer server et port par la ligne de commande

		MyClient mc = null; //initialisation de la variable locale
		try {
			if (args.length > 1) {
				mc = new MyClient(args[0], Integer.parseInt(args[1]));
			} else {
				mc = new MyClient("localhost", 2222);
			}
		} catch (IOException e) {
			if (mc != null) {
				mc.fermer();
				mc = null; //on perd l'objet
			}
		}

		if (mc != null) {
// Que dit le serveur ?
			String ligne = mc.readFromServer();
//Requete au serveur a partir de la console
			BufferedReader console = new BufferedReader(
					new InputStreamReader(System.in));
			String entree;
			try {
				while ((entree = console.readLine()) != null) {
					mc.writeToServer(entree);
					ligne = mc.readFromServer();
					if (ligne != null) {
						System.out.println("Server :" + ligne);// pour voir
						if (ligne.equals("Bye")) {
							mc.fermer();
							break;
						}
					}
				}
				console.close();
			} catch (IOException e) {
			}
			mc.fermer();
		}
	}
}
