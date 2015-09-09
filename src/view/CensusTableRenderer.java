/* 
 * Copyright (C) 2015 Csaba Farkas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Csaba Farkas R00117945
 * 
 * Class that extends DefaultTableCellRenderer. I personlised the table using this
 * class i.e. setting the background color of the rows and headers, setting background
 * color when 'hasFocus' or 'isSelected' etc.
 */
public class CensusTableRenderer extends DefaultTableCellRenderer {
    
    public CensusTableRenderer() {
        super();
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        //Set the background color of every second row to a lighter blue than the header
        if(row%2 != 0) {
            this.setBackground(new Color(205, 224, 250));
        }

        //Set the background color of the other rows to white
        else {
            this.setBackground(Color.white);
        }

        //Set the background color of a row when selected to a light gray color
        if(isSelected) {
            this.setBackground(new Color(184, 187, 191));
        }

        //Set the background color of the cell when it has focus to a darker gray color
        if(hasFocus) {
            this.setBackground(new Color(123, 138, 135));
        }
        
        //Align cell values to CENTER
        this.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return this;
    }
        

}
