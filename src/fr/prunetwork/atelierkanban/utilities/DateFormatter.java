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

package fr.prunetwork.atelierkanban.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 * @author garciaf
 */
public class DateFormatter
		extends Date {

	private static final long serialVersionUID = 201001111551L;

	public DateFormatter(Date date) {
		super(date.getTime());
	}

	public DateFormatter(int timeStamp) {
		super(timeStamp);
	}

	public DateFormatter(String timeStamp) {
		super(Long.parseLong(timeStamp));
	}

	@Override
	public String toString() {
		return toISO8601();
	}

	public String toISO8601() {
		/**
		 * Formatteur pour que les dates s'affichent au format ISO 8601
		 */
		SimpleDateFormat dateTime = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS",
				Locale.getDefault());
		return dateTime.format(this);
	}

	public String toHHMMSS() {
		/**
		 * Formatteur pour que les dates s'affichent au format ISO 8601
		 */
		SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss",
														 Locale.getDefault());
		return dateTime.format(this);
	}
}
