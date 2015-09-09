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
package model;

import java.util.ArrayList;

/**
 * A class that can store Person objects in a sequential order. It provides direct
 * access. 
 * 
 * @author Csaba Farkas
 */
public class CensusData {

    private ArrayList<Person> censusData;   //Instance variable indicating an ArrayList object
    
    /**
     * Constructor method.
     */
    public CensusData() {
        this.censusData = new ArrayList<Person>();
    }
    
    /**
     * Getter method.
     * 
     * @return ArrayList object.
     */
    public ArrayList<Person> getCensusData() {
        return this.censusData;
    }
    
    /**
     * Getter method.
     * 
     * @param rowNumber Indicating the index of the object to return.
     * @return A Person object at index 'rowNumber'.
     */
    public Person getPersonAtRowNumber(int rowNumber) {
        return this.censusData.get(rowNumber);
    }
    
    /**
     * Add a person to the list.
     * 
     * @param person A Person object that is to be added to the list.
     */
    public void addPerson(Person person) {
        this.censusData.add(person);
    }
    
    /**
     * Modify a particular Person object in the list by replacing it with another.
     * 
     * @param index Indicating the position of Person object in list.
     * @param newPerson New Person object replacing the old one.
     */
    public void modifyPerson(int index, Person newPerson) {
        this.censusData.set(index, newPerson);
    }
    
    /**
     * Remove a person from the list.
     * 
     * @param person Person that is to be removed.
     */
    public void removePerson(Person person) {
        this.censusData.remove(person);
    }
    
}
