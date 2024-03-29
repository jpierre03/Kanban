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

package fr.prunetwork.atelierkanban.storage.writer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 * @author garciaf
 */
public class StoreDataToFileTest {

	public static void main(String arg[]) {
		StoreDataToFile storeDataToFile =
				StoreDataToFile.getStoreToFile();

		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		storeDataToFile.add("vncdnbcvlnbvj,n!vb,n:;,v!\n");
		try {
			storeDataToFile.commit("FileXXXXXXXXXXXX");
		} catch (IOException ex) {
			Logger.getLogger(StoreDataToFileTest.class.getName()).
					log(Level.SEVERE, null, ex);
		}
	}
}
