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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * C'est dans cette classe que les protocoles d'échange sont définis (cycle de lecture/ecriture)
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class CommunicationClientServeur implements Runnable {

	/**
	 */
	private boolean DEBUG = true;
	/**
	 */
	private Socket client = null;
	/**
	 */
	private BufferedReader entree;
	/**
	 */
	private PrintWriter sortie;
	/**
	 */
	private boolean onContinue = true;
	/**
	 */
	private MyServerMultiClient msMC = null;

	/**
	 * permet de définir quel est l'objet qui a instancié cet objet
	 * Est utilisé pour avoir un accès aux méthodes publiques
	 *
	 * @param msMC
	 * @return
	 */
	public CommunicationClientServeur setMonServeurMulticlient(MyServerMultiClient msMC) {
		this.msMC = msMC;
		return this;
	}

	/**
	 * @param socket Est un socket pour communiquer avec le client
	 */
	public CommunicationClientServeur(Socket socket) {
		client = socket;

		try {
			// on recupère les canaux de communication
			// avec des filtres de lecture ecriture de donnees texte
			entree = new BufferedReader(new InputStreamReader(client.getInputStream()));
			sortie = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			System.err.println("PB création des streams");
			System.exit(1);
		}
		if (entree == null) {
			System.err.println("pas d'entrée !!!");
		}
		if (sortie == null) {
			System.err.println("pas de sortie !!!");
		}
	}

	/**
	 * Lecture d'un message envoyé par le serveur
	 * @return le message envoyé par le serveur
	 */
	public String lireClient() {
		String ligne = null;
		try {
			ligne = entree.readLine();
		} catch (IOException e) {
			System.err.println("rien a lire");
		}
		return ligne;
	}

	/** Envoie des données au client.
	 * @param ligne les caractères à envoyer
	 */
	public void ecrireClient(String ligne) {
		if (sortie == null) {
			System.out.println("pas de sortie !!! : écrire ?");
		} else {
			sortie.println(ligne);
			sortie.flush();
//			System.out.println("CommunicationClientServeur " + this + "message : " + ligne);
		}
	}

	/**
	 * Pour afficher un message avec tous les personnes connectées
	 * @param ligne un message
	 */
	public void ecrireTousClient(String ligne) {
//		System.err.println("ecrireTousClient");
		if (msMC != null) {
			if (ligne != null) {
				msMC.ecrireTousClient(ligne);
//				System.err.println("ecrireTousClient - ok");
			}

		} else {
			if (ligne != null) {
				ecrireClient(ligne);
			}
//			System.err.println("ecrireTousClient - msMC ==null");
		}
	}

	/** teste la connexion.
	 *@return un booléen notifiant l'état de la connexion
	 */
	public boolean clientOK() {
		return client.isConnected();
	}

	/** Fermeture du socket.
	 * @return
	 */
	public CommunicationClientServeur fermer() {
		// il faut fermer "proprement" les stream avant les Sockets
		onContinue = false;
		try {
			entree.close();
			sortie.close();
			if (client != null) {
				client.close();
			}
//			System.out.println("Fermeture ok");
		} catch (IOException e) {
//			System.err.println("Erreur à la fermeture des flux!");
		}
		return this;
	}

	/**
	 *
	 */
	@Override
	protected void finalize() {
		// méthode executee par le ramasse miettes avant de libérer la mémoire
		// pb : on ne sait jamais trop quand !!!
		fermer();
	}

	/**
	 *
	 */
	@Override
	public void run() {
		while (onContinue && clientOK()) {
			String message = lireClient();
			if (message != null) {
				ecrireTousClient(message);
				System.out.println("ecrireClient(" + message + ")");
			} else {
				onContinue = false;
			}
		}
	}
}
