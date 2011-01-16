/*
 *  Copyright (C) 2010 Team-W@R (team-war@prunetwork.fr)
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
package fr.prunetwork.atelierkanban.storage.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+twar@prunetwork.fr)
 * @author garciaf
 */
public class StoreDataToFile {

    StringBuilder sb = new StringBuilder();

    public StoreDataToFile() {
    }

    public void add(String chaine) {
        sb.append(chaine);
    }

    public String getBuffer() {
        return sb.toString();
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public void commit(String file) throws IOException {
        //création ou ajout dans le file texte
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter outputFilePrintWriter = new PrintWriter(bw);
        outputFilePrintWriter.println(sb.toString());
        outputFilePrintWriter.close();
        System.out.println(file + " has been created !");
    }
}
