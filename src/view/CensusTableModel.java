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

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Person;

/**
 * Table model class connecting to JTable and providing the data structure.
 * 
 * @author Csaba Farkas R00117945
 */
public class CensusTableModel extends DefaultTableModel {

    private static final int NUMBER_OF_COLUMNS = 15;     //Surname, Forename, Townland/Street, DED, County, Age, Sex...
    
    
    //Column indicies
    private static final int SURNAME_COL =          0;
    private static final int FORENAME_COL =         1;
    private static final int TOWNLAND_COL =         2;
    private static final int DED_COL =              3;
    private static final int COUNTY_COL =           4;
    private static final int AGE_COL =              5;
    private static final int SEX_COL =              6;
    private static final int BIRTHPLACE_COL =       7;
    private static final int OCCUPATION_COL =       8;
    private static final int RELIGION_COL =         9;
    private static final int LITERACY_COL =         10;
    private static final int IRISH_LANGUAGE_COL =   11;
    private static final int RELATION_TO_COL =      12;
    private static final int MARITAL_STATUS =       13;
    
    private ArrayList<Person> people;

    /**
     * Constructor method.
     * 
     * @param people an ArrayList object.
     */
    public CensusTableModel(ArrayList<Person> people) {
        super();
        this.people = people;
    }

    /**
     * Setter method.
     * 
     * @param people an ArrayList object.
     */
    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }
    
    /**
     * Getter.
     * 
     * @return number of columns in table model. 
     */
    @Override
    public int getColumnCount() {
        return CensusTableModel.NUMBER_OF_COLUMNS;
    }
    
    /**
     * Getter method.
     * 
     * @param columnIndex Indicating the position of the column of which name is to be returned.
     * @return name of a particular column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex == CensusTableModel.SURNAME_COL) {
            return "Surname";
        }
        else if(columnIndex == CensusTableModel.FORENAME_COL) {
            return "Forename";
        }
        else if(columnIndex == CensusTableModel.TOWNLAND_COL) {
            return "Townland/Street";
        }
        else if(columnIndex == CensusTableModel.DED_COL) {
            return "DED";
        }
        else if(columnIndex == CensusTableModel.COUNTY_COL) {
            return "County";
        }
        else if(columnIndex == CensusTableModel.AGE_COL) {
            return "Age";
        }
        else if(columnIndex == CensusTableModel.SEX_COL){
            return "Sex";
        }
        else if(columnIndex == CensusTableModel.BIRTHPLACE_COL) {
            return "Birthplace";
        }
        else if(columnIndex == CensusTableModel.OCCUPATION_COL) {
            return "Occupation";
        }
        else if(columnIndex == CensusTableModel.RELIGION_COL) {
            return "Religion";
        }
        else if(columnIndex == CensusTableModel.LITERACY_COL) {
            return "Literacy";
        }
        else if(columnIndex == CensusTableModel.IRISH_LANGUAGE_COL) {
            return "Irish Language";
        }
        else if(columnIndex == CensusTableModel.RELATION_TO_COL) {
            return "Relation to Head of Household";
        }
        else if(columnIndex == CensusTableModel.MARITAL_STATUS) {
            return "Marital Status";
        }
        else {
            return "Specified Illness";
        }
    }
    
    /**
     * Getter method.
     * 
     * @return the number of rows in table model (number of elements in list).
     */
    @Override
    public int getRowCount() {
        if(this.people != null) {
            return this.people.size();
        }
        else {
            return 0;
        }
    }
    
    /**
     * Get value from a particular cell.
     * 
     * @param row Indicating row number of data.
     * @param column Indicating column number of data.
     * @return an object stored at specified row number.
     */
    @Override
    public Object getValueAt(int row, int column) {
        Person currentPerson = this.people.get(row);
        
        if(column == CensusTableModel.SURNAME_COL) {
            return currentPerson.getSurname();
        }
        else if(column == CensusTableModel.FORENAME_COL) {
            return currentPerson.getForename();
        }
        else if(column == CensusTableModel.TOWNLAND_COL) {
            return currentPerson.getTownland();
        }
        else if(column == CensusTableModel.DED_COL) {
            return currentPerson.getDED();
        }
        else if(column == CensusTableModel.COUNTY_COL) {
            return currentPerson.getCounty();
        }
        else if(column == CensusTableModel.AGE_COL) {
            return currentPerson.getAge();
        }
        else if(column == CensusTableModel.SEX_COL) {
            return currentPerson.getSex();
        }
        else if(column == CensusTableModel.BIRTHPLACE_COL) {
            return currentPerson.getBirthplace();
        }
        else if(column == CensusTableModel.OCCUPATION_COL) {
            return currentPerson.getOccupation();
        }
        else if(column == CensusTableModel.RELIGION_COL) {
            return currentPerson.getReligion();
        }
        else if(column == CensusTableModel.LITERACY_COL) {
            return currentPerson.getLiteracy();
        }
        else if(column == CensusTableModel.IRISH_LANGUAGE_COL) {
            return currentPerson.getIrishLanguage();
        }
        else if(column == CensusTableModel.RELATION_TO_COL) {
            return currentPerson.getRelationToHeadOfHousehold();
        }
        else if(column == CensusTableModel.MARITAL_STATUS) {
            return currentPerson.getMaritalStatus();
        }
        else {
            return currentPerson.getSpecifiedIllnesses();
        }
        
    }
    
    /**
     * Sets cells in table not-editable.
     * 
     * @param row indicates row number of data.
     * @param column indicates column number of data.
     * @return false in any case.
     */
    @Override
    public boolean isCellEditable(int row, int column){  
          return false;  
      }
}
