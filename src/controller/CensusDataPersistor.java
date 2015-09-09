/* 
 * Copyright (C) 2015 Csaba
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
package controller;

import model.CensusData;
import model.Person;

/**
 * ADT interface containing the method signatures that have to be implemented by
 * the data persistor class in use.
 * 
 * @author Csaba Farkas R00117945
 *
 */
public interface CensusDataPersistor {

    
    public void retreiveAllData();
    public void addNewEntry(Person person);
    public void removeEntry(Object[] primaryKey);
    public void modifyEntry(Object[] primaryKey, Person person);
    public void setCensusData(CensusData censusData);
    public CensusData getCensusData();
}
