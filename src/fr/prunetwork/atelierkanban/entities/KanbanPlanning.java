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
package fr.prunetwork.atelierkanban.entities;

import fr.prunetwork.atelierkanban.Constants;
import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.event.EventDispatcher;
import fr.prunetwork.atelierkanban.event.kanban.AbstractKanbanEvent;
import fr.prunetwork.atelierkanban.event.kanban.KanbanAdd;
import fr.prunetwork.atelierkanban.event.kanban.KanbanRemove;
import fr.prunetwork.atelierkanban.event.kanban.index.KanbanBlueIndexChanged;
import fr.prunetwork.atelierkanban.event.kanban.index.KanbanGreenIndexChanged;
import fr.prunetwork.atelierkanban.event.kanban.index.KanbanRedIndexChanged;
import fr.prunetwork.patterns.observer.Observer;

/** @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr) */
public class KanbanPlanning
		implements Observer {

	private int upperLevel_redIndex;
	private int lowerLevel_greenIndex;
	private int maxKanban_blueIndex;
	private int currentKanbanCount;
	private String productName = Constants.DEFAULT;
	private final int INIT_KANBAN_LEVEL;
	private final int INIT_RED_LEVEL;
	private final int INIT_GREEN_LEVEL;
	private final int INIT_BLUE_LEVEL;

	public KanbanPlanning(int upperLevel_red,
						  int lowerLevel_green,
						  int maxKanban,
						  int initialKanbanCount) {

		INIT_RED_LEVEL = upperLevel_red;
		INIT_GREEN_LEVEL = lowerLevel_green;
		INIT_BLUE_LEVEL = maxKanban;
		INIT_KANBAN_LEVEL = initialKanbanCount;

		initValues();

		EventDispatcher.getEventDispatcher().registerObserver(this);
	}

	public final void initValues() {
		this.upperLevel_redIndex = INIT_RED_LEVEL;
		this.lowerLevel_greenIndex = INIT_GREEN_LEVEL;
		this.maxKanban_blueIndex = INIT_BLUE_LEVEL;
		this.currentKanbanCount = INIT_KANBAN_LEVEL;
	}

	private void addKanban() {
		synchronized (this) {
			setCurrentKanbanCount(getCurrentKanbanCount() + 1);
			if (getCurrentKanbanCount() >= getMaxKanban_blueIndex()) {
				setCurrentKanbanCount(getMaxKanban_blueIndex());
			}
		}
	}

	private void removeKanban() {
		synchronized (this) {
			setCurrentKanbanCount(getCurrentKanbanCount() - 1);
			if (getCurrentKanbanCount() <= 0) {
				setCurrentKanbanCount(0);
			}
		}
	}

	/** @return the maxKanban */
	public int getMaxKanban_blueIndex() {
		return maxKanban_blueIndex;
	}

	/** @return the upperLevel_red */
	public int getUpperLevel_redIndex() {
		return upperLevel_redIndex;
	}

	/** @return the lowerLevel_green */
	public int getLowerLevel_greenIndex() {
		return lowerLevel_greenIndex;
	}

	/** @return the currentKanbanCount */
	public int getCurrentKanbanCount() {
		return currentKanbanCount;
	}

	public String toHtml() {
		StringBuilder sb = new StringBuilder(300);
		String blueColor = " background-color: blue;";
		String grayColor = " background-color: gray;";
		String greenColor = " background-color: rgb(51, 204, 0);";
		String redColor = " background-color: red;";

		sb.append(
				"<table border=\"1\" cellpadding=\"2\" cellspacing=\"2\">");
		sb.append("<tbody>");
		for (int row = getMaxKanban_blueIndex(); row > 0; row--) {
			sb.append("<tr>");
			//col 1
			sb.append("<td style=\"width: 25px;");
			if (row == getMaxKanban_blueIndex()) {
				sb.append(blueColor);
			} else {
				if (row == getUpperLevel_redIndex()) {
					sb.append(redColor);
				} else {
					if (row == getLowerLevel_greenIndex()) {
						sb.append(greenColor);
					}
				}
			}
			sb.append("\">");
			sb.append(row);
			sb.append("<br></td>");
			//col 2
			sb.append("<td style=\"width: 80px;");
			if (row <= getCurrentKanbanCount()) {
				sb.append(grayColor);
			}
			sb.append("\"><br></td>");
			sb.append("</tr>");
		}

		sb.append("</tbody>");
		sb.append("</table>");
		sb.append("");

		return sb.toString();
	}

	/** @param upperLevel_red the upperLevel_red to set */
	public void setUpperLevel_red(int upperLevel_red) {
		this.upperLevel_redIndex = upperLevel_red;
	}

	/** @param lowerLevel_green the lowerLevel_green to set */
	public void setLowerLevel_green(int lowerLevel_green) {
		this.lowerLevel_greenIndex = lowerLevel_green;
	}

	/** @param maxKanban the maxKanban to set */
	public void setMaxKanban(int maxKanban) {
		this.maxKanban_blueIndex = maxKanban;
	}

	/** @param currentKanbanCount the currentKanbanCount to set */
	public void setCurrentKanbanCount(int currentKanbanCount) {
		this.currentKanbanCount = currentKanbanCount;
	}

	@Override
	public void notify(Event event) {
		if (event instanceof AbstractKanbanEvent) {
			AbstractKanbanEvent ake = (AbstractKanbanEvent) event;

			//Check if this component is interested
			// By default this componnent focus on it's product name (productNameTextField)
			if (ake.getProductName().equalsIgnoreCase(getProductName())) {

				if (event instanceof KanbanAdd) {
					this.addKanban();
				}
				if (event instanceof KanbanRemove) {
					this.removeKanban();
				}
				if (event instanceof KanbanBlueIndexChanged) {
					KanbanBlueIndexChanged kbic = (KanbanBlueIndexChanged) event;
					this.setMaxKanban(kbic.getBlueIndexLevel());
				}
				if (event instanceof KanbanRedIndexChanged) {
					KanbanRedIndexChanged kric = (KanbanRedIndexChanged) event;
					this.setUpperLevel_red(kric.getRedIndexLevel());
				}
				if (event instanceof KanbanGreenIndexChanged) {
					KanbanGreenIndexChanged kgic = (KanbanGreenIndexChanged) event;
					this.setLowerLevel_green(kgic.getGreenIndexLevel());
				}
			}
		}
	}

	/** @return the productName */
	public String getProductName() {
		return productName;
	}

	/** @param productName the productName to set */
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
