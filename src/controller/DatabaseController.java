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
package controller;

/**
 * Singleton controller class connecting the database to the other parts of the
 * program. 
 * 
 * @author Csaba Farkas R00117945
 */

public class DatabaseController {

    /**
    * DatabaseController is a singleton class. It means that it is created before the    
    * program is compiled. It has only one instance in memory and it is called each
    * time other parts of the program want to communicate with the database.
    */
    private static DatabaseController instance;
    
    /**
     * Static method returning an instance of DatabaseController.
     * 
     * @return an instance of DatabaseController
     */
    public static DatabaseController getInstance() {
        if(instance == null) {
            instance = new DatabaseController();
        }
        return instance;
    }
    
  
    private CensusDataPersistor censusDataPersistor;    //Instance variable indicating a CensusDataPersistor object

    /**
     * Setter method. 
     * @param censusDataPersistor The data persistor that the program uses.
     */
    public void setCensusDataPersistor(CensusDataPersistor censusDataPersistor) {
        this.censusDataPersistor = censusDataPersistor;
    }

    /**
     * Getter method.
     * 
     * @return Data persistor object.
     */
    public CensusDataPersistor getCensusDataPersistor() {
        return this.censusDataPersistor;
    }
    
    
    
    
    
}
