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

package fr.prunetwork.atelierkanban.event.kanban;

import fr.prunetwork.atelierkanban.Constants;
import fr.prunetwork.atelierkanban.event.AbstractEvent;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public abstract class AbstractKanbanEvent
		extends AbstractEvent {

	private String productName = Constants.DEFAULT;

	/** @return the productName */
	public String getProductName() {
		return productName;
	}

	/** @param productName the productName to set */
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
