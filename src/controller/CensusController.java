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

/**
 * Singleton controller class connecting view with data model, not with database.
 * 
 * @author Csaba Farkas R00117945
 */
public class CensusController {

    //--------------------------------------------------------------------------
    /*
    Singleton class
    */
    private static CensusController instance;
    
    public static CensusController getInstance() {
        if(instance == null) {
            instance = new CensusController();
        }
        return instance;
    }
    
    private CensusData censusDataModel;
    private CensusGUI censusGUI;
    
    public CensusController() {
        
    }
    
    public void setCensusDataModel(CensusData censusDataModel) {
        this.censusDataModel = censusDataModel;
    }
    
    public CensusData getCensusDataModel() {
        return this.censusDataModel;
    }
    
    public void setGUIReference(CensusGUI censusGUI) {
        this.censusGUI = censusGUI;
    }
    
    public CensusGUI getGUIReference() {
        return this.censusGUI;
    }
    
}
