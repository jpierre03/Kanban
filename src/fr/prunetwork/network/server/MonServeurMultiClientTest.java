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

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class MonServeurMultiClientTest{

	/**
	 * Constructeur par défaut
	 */
	public MonServeurMultiClientTest(){
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
//	Pour tester un protocole simple de communication
//	Bien entendu il faut que serveur et client soient compatibles

		/** Affiche ou non les messages échangés sur la console*/
		boolean afficheConsole = false;
		/**initialisation du serveur*/
		MonServeurMultiClient ms;
		if(args.length > 0){
			/** Récupère le n° de port depsui la console*/
			ms = new MonServeurMultiClient(Integer.parseInt(args[0]));
		} else{
			ms = new MonServeurMultiClient(0);
		}
		System.out.println("On communique");

		/** Envoie un message d'accueil*/
		ms.ecrireTousClient("Bienvenue sur MonServeur");

		/** Ecoute du client*/
		boolean continuer = true;
		/** Contient la ligne a échanger*/
		String ligne;
		while(continuer && ms != null && ms.getOnContinue()){
//			ligne = ms.lireClient();
//			if(ligne.equalsIgnoreCase("fin")){//peu importe la casse
//				continuer = false;
//				ms.ecrireTousClient("Au revoir");
//			}
//			if(afficheConsole){
//				System.out.println("Client : " + ligne);
//			}
//			ms.ecrireTousClient("recu :" + ligne);
			
		}
		System.out.println("On termine");
		ms.end();
	}
}
