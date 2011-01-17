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
package fr.prunetwork.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class MonClientTest{

	/**
	 * Constructeur de la classe de test
	 */
	public MonClientTest(){
	}

	/**
	 * Le programme commence ici.
	 * Le test se fait en réalisant un connexion du client sur un serveur en console
	 * @param args Ce sont les paramètres de la ligne de commande
	 */
	public static void main(String[] args){
// Pour tester un protocole simple de communication
// Bien entendu il faut que serveur et client soient compatibles

// initialisation du client
// on peut passer server et port par la ligne de commande

		MonClient mc = null; //initialisation de la variable locale
		try{
			if(args.length > 1){
				mc = new MonClient(args[0], Integer.parseInt(args[1]));
			} else{
				mc = new MonClient("localhost", 2222);
			}
			System.out.println("mc = " + mc + "client:" + mc.getClient());
		} catch(IOException e){
			System.err.println("erreur de realisation du client" + mc);
			if(mc != null){
				mc.fermer();
				mc = null; //on perd l'objet
			}
		}

		if(mc != null){
// Que dit le serveur ?
			String ligne = mc.lireServeur();
			System.out.println("Serveur :" + ligne);
//Requete au serveur a partir de la console
			BufferedReader console = new BufferedReader(
					new InputStreamReader(System.in));
			String entree;
			try{
				while((entree = console.readLine()) != null){
					mc.ecrireServeur(entree);
					ligne = mc.lireServeur();
					System.out.println("Serveur :" + ligne);// pour voir
					if(ligne.equals("Au revoir")){
						break;
					}
				}

				console.close();

			} catch(IOException e){
				System.err.println("Erreur de lecture console");
			}

			mc.fermer();
		} else{
			System.err.println("Impossible de cr�er le client, mc ==null !!");
		}
	}
}
