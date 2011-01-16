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
package fr.prunetwork.atelierkanban.example;

import java.io.*;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 * @author garciaf
 */
public class ReadWriteFile {

		public static void main(String[] args) {
				String chaine = "";
//        String fichier = "fichiertexte.txt";
				String fichier =
					   "/home/jpierre03/SVN/team-war/PFE/Donnee/db_risk_Sample.csv";

				//lecture du fichier texte
				try {
						InputStream ips = new FileInputStream(fichier);
						InputStreamReader ipsr = new InputStreamReader(ips);
						BufferedReader br = new BufferedReader(ipsr);
						String ligne;
						while ((ligne = br.readLine()) != null) {
								System.out.println(ligne);
								chaine += ligne + "\n";

						}
						br.close();
				} catch (Exception e) {
						System.out.println(e.toString());
				}

				//création ou ajout dans le fichier texte
				try {
						FileWriter fw = new FileWriter(fichier + ".bak");
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter fichierSortie = new PrintWriter(bw);
						fichierSortie.println(
								chaine + "\n test de lecture et écriture !!");
						fichierSortie.close();
						System.out.println("Le fichier " + fichier + ".bak"
										   + " a été créé!");
				} catch (Exception e) {
						System.out.println(e.toString());
				}
		}
}
