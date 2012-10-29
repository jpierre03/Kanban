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
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author Marc-Emmanuel Bellemare Jan. 2005
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class MyClient {

	/**
	 *
	 */
	private Socket clientSocket = null;
	/**
	 *
	 */
	private BufferedReader reader;
	/**
	 *
	 */
	private PrintWriter    writer;

	/**
	 * Le constructeur qui indique sur quel serveur se connecter et quel port solliciter
	 *
	 * @param hostName
	 * @param portNumber le numero de port
	 * @throws IOException si erreur de connexion
	 */
	public MyClient(String hostName, int portNumber)
			throws
			IOException {
		try {
			// Convertir la chaine de caractères "hostName" en une adresse IP valide du serveur
			InetAddress adresseIP = InetAddress.getByName(hostName);            //récupère l'adresse IP à partir du nom de machine
			//creer le Socket vers le serveur
			clientSocket = new Socket(adresseIP,
									  portNumber);                    // création d'un socket avec la config en param
			//on fixe un timeOut
			clientSocket.setSoTimeout(1000);                                    // on définit une durée au dela de laquelle on abandonne
		} catch (UnknownHostException e) {
			System.err.println("Server is unknown: " + hostName);
			throw e;
		} catch (SocketException e) {
			System.err.println("Connexion error or timeout");
			throw e;
		} catch (IOException e) {
			System.err.println("Connexion probleme with: " + hostName);
			throw e;
		}
		System.out.println("Connexion OK with: " + hostName);

		try {
			reader = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));        //crée un flux à partir du socket client
			writer = new PrintWriter(clientSocket.getOutputStream());            //crée un flux à partir du socket client
		} catch (IOException e) {
			System.err.println("PB creation des streams");
			System.exit(1);
		}
	}

	/**
	 * lit les caracteres envoyés par le serveur.
	 *
	 * @return un objet String qui contient l'ensemble des caractères lus
	 */
	public String readFromServer() {
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			//System.err.println("rien a lire");
		}
		return line;
	}

	/**
	 * Renvoie un identifiant de Socket
	 *
	 * @return un identifiant de Socket
	 */
	Socket getClientSocket() {
		return clientSocket;
	}

	/**
	 * Envoie des données au serveur.
	 *
	 * @param ligne les caractères à envoyer
	 */
	public void writeToServer(String ligne) {
		writer.println(ligne);
		writer.flush();
	}

	/**
	 * Teste la connexion.
	 *
	 * @return un booleen notifiant l'état de la connexion
	 */
	public boolean isConnected() {
		return clientSocket.isConnected();
	}

	/** Fermeture du socket & du lclient en général. */
	public void fermer() {
		try {
			reader.close();
			writer.close();
			if (clientSocket != null) {
				clientSocket.close();
			}
			System.out.println("Fermeture ok");
		} catch (IOException e) {
			System.err.println("Erreur à la fermeture des flux!");
		}
	}

	/** Méthode invoquée lors du passage du ramasse miette */
	@Override
	protected void finalize() {
		fermer();
	}
}
