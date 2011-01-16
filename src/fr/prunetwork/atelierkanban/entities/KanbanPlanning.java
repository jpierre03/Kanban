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

import fr.prunetwork.atelierkanban.event.Event;
import fr.prunetwork.atelierkanban.event.KanbanAdd;
import fr.prunetwork.atelierkanban.event.KanbanRemove;
import org.lsis.haimes.patterns.observer.Observer;

/**
 *
 * @author Jean-Pierre Prunaret (jpierre03+AtelierKanban@prunetwork.fr)
 */
public class KanbanPlanning implements Observer {

    private int upperLevel_red;
    private int lowerLevel_green;
    private int maxKanban;
    private int currentKanbanCount;

    public KanbanPlanning(int upperLevel_red,
            int lowerLevel_green,
            int maxKanban,
            int initialKanbanCount) {
        this.upperLevel_red = upperLevel_red;
        this.lowerLevel_green = lowerLevel_green;
        this.maxKanban = maxKanban;
        this.currentKanbanCount = initialKanbanCount;
    }

    private void addKanban() {
        synchronized (this) {
            setCurrentKanbanCount(getCurrentKanbanCount() + 1);
            if (getCurrentKanbanCount() >= getMaxKanban()) {
                setCurrentKanbanCount(getMaxKanban());
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

    /**
     * @return the maxKanban
     */
    public int getMaxKanban() {
        return maxKanban;
    }

    /**
     * @return the upperLevel_red
     */
    public int getUpperLevel_red() {
        return upperLevel_red;
    }

    /**
     * @return the lowerLevel_green
     */
    public int getLowerLevel_green() {
        return lowerLevel_green;
    }

    /**
     * @return the currentKanbanCount
     */
    public int getCurrentKanbanCount() {
        return currentKanbanCount;
    }

    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        String blueColor = " background-color: blue;";
        String grayColor = " background-color: gray;";
        String greenColor = " background-color: rgb(51, 204, 0);";
        String redColor = " background-color: red;";

        sb.append(
                "<table border=\"1\" cellpadding=\"2\" cellspacing=\"2\">");
        sb.append("<tbody>");
        for (int row = getMaxKanban(); row > 0; row--) {
            sb.append("<tr>");
            //col 1
            sb.append("<td style=\"width: 25px;");
            if (row == getMaxKanban()) {
                sb.append(blueColor);
            } else {
                if (row == getUpperLevel_red()) {
                    sb.append(redColor);
                } else {
                    if (row == getLowerLevel_green()) {
                        sb.append(greenColor);
                    }
                }
            }
            sb.append("\">");
            sb.append(row);
            sb.append("<br></td>");
            //col 2
            sb.append("<td style=\"width: 100px;");
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

    /**
     * @param upperLevel_red the upperLevel_red to set
     */
    public void setUpperLevel_red(int upperLevel_red) {
        this.upperLevel_red = upperLevel_red;
    }

    /**
     * @param lowerLevel_green the lowerLevel_green to set
     */
    public void setLowerLevel_green(int lowerLevel_green) {
        this.lowerLevel_green = lowerLevel_green;
    }

    /**
     * @param maxKanban the maxKanban to set
     */
    public void setMaxKanban(int maxKanban) {
        this.maxKanban = maxKanban;
    }

    /**
     * @param currentKanbanCount the currentKanbanCount to set
     */
    public void setCurrentKanbanCount(int currentKanbanCount) {
        this.currentKanbanCount = currentKanbanCount;
    }

    public void notify(Event event) {

        if (event instanceof KanbanAdd) {
            this.addKanban();
        }
        if (event instanceof KanbanRemove) {
            this.removeKanban();
        }
    }
}
