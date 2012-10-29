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

package fr.prunetwork.network.server;

import fr.prunetwork.atelierkanban.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class MyServerMultiClient {

	/** Détermine le nombre de threads qui sont exécutés en même temps */
	private static Executor                               executor     = Executors.newFixedThreadPool(100);
	/** Contient la référence de la socket */
	private        ServerSocket                           serverSocket = null;
	/** Pour savoir si on continue ou non l'execution */
	private        boolean                                onContinue   = true;
	/** Pour savoir si la communication est terminée */
	private        boolean                                isEnded      = false;
	/** pour savor combien de temps on doit attendre le client */
	private        int                                    dureeTimeout = 1000000;
	/**
	 */
	private        Collection<CommunicationClientServeur> listComm     = new ArrayList<CommunicationClientServeur>();

	/** @param port  */
	public MyServerMultiClient(int port) {// création d'un serveur
		CommunicationClientServeur comm = null;
		if (port < 1023) {// ports reserves
			System.err.println("erreur de choix du port ! 2222 par defaut");
			port = Constants.DEFAULT_PORT_NUMBER;
		}
		try {
			// on cree un serveur sur le port specifie & sur touttes les @IP de la machine
			serverSocket = new ServerSocket(port);
			System.out.println("J'ecoute le port: " + port + "...");
		} catch (IOException e) {
			System.err.println("Impossible d'écouter le port " + port);
			System.exit(1);
		}

		while (onContinue == true) {
			/** s'il y a une requete sur le portServeur
			 * on cree un Socket pour communiquer avec le client
			 * On attend jusqu'a ce qu'il y ait une requete
			 */
			try {
				/** attend au max x Secondes*/
				serverSocket.setSoTimeout(dureeTimeout * 1000);
			} catch (SocketException ex) {
				System.err.println("On quitte : setSoTimeOut");
				System.exit(1);
			}
			try {
				//client = serveur.accept(); //"client" est le Socket
				final Socket connexion = serverSocket.accept();
				comm = new CommunicationClientServeur(connexion);
				comm.setMonServeurMulticlient(this);
				listComm.add(comm);
//				comm.ecrireClient("Bonjour nouveau client !");
				System.out.println("Connexion Acceptée");
			} catch (SocketTimeoutException e) {
				System.err.println("On quitte : TimeOut");
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Client refusé !.");
				System.exit(1);
			}
			executor.execute(comm);
		}
	}

	/** Pout finir proprement le serveur */
	public void end() {
		Iterator<CommunicationClientServeur> it = getListComm().iterator();
		/** On parcours la liste de tous les client connectés et on ferme la connexion*/
		while (it.hasNext()) {
			getListComm().remove(it.next().fermer());
		}
		try {
			serverSocket.close();
		} catch (IOException ex) {
			Logger.getLogger(MyServerMultiClient.class.getName()).log(Level.SEVERE, null, ex);
		}
		/** A ce stade, toutes les communications doivent être terminées*/
		isEnded = true;
	}

	/**
	 * Pour savoir si on laisse le serveur lancé
	 *
	 * @return on continue ou on arrete le serveur (True/false)
	 */
	public synchronized boolean getOnContinue() {
		return onContinue;
	}

	/**
	 * Le serveur est il arrêté ? (True/False)
	 *
	 * @return
	 */
	public synchronized boolean isEnded() {
		return isEnded;
	}

	/**
	 * Pour écrire à toui
	 *
	 * @param ligne
	 */
	public void ecrireTousClient(String ligne) {
		if (ligne != null) {
			Iterator<CommunicationClientServeur> iterator = getListComm().iterator();
			/** on envoie le message à tous les membres de la liste*/
			while (iterator.hasNext()) {
				//iterator.next().ecrireClient(ligne);
				CommunicationClientServeur ccs = iterator.next();
//				System.out.println("ecrireTousClient - next " + ccs);
				ccs.ecrireClient(ligne);
			}
		}
	}

	/** @return the listComm */
	private synchronized Collection<CommunicationClientServeur> getListComm() {
		return listComm;
	}

	/** @param listComm the listComm to set */
	private synchronized void setListComm(Collection<CommunicationClientServeur> listComm) {
		this.listComm.clear();
		this.listComm.addAll(listComm);
	}
}
