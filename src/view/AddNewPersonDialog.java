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

import controller.CensusController;
import controller.DatabaseController;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import model.Person;



/**
 * Opens a dialog with a form to add a new person to the database.
 * Extend AbstractDialog class.
 * 
 * @author Csaba Farkas R00117945
 */
class AddNewPersonDialog extends AbstractDialog {

    /**
     * Constructor method.
     * 
     * @param owner JFrame that is the owner of this dialog box.
     * @param title Title of dialog box.
     * @param width Width of owner.
     * @param height Height of owner.
     */
    public AddNewPersonDialog(JFrame owner, String title, int width, int height) {
        super(owner, title, width, height);
        
        //Set text in add button to 'Add' and attach an ActionListener
        getAddOrModifyButton().setText(AbstractDialog.ADD_PERSON_BUTTON_LABEL);
        //ActionListener parsing data from form and calling controller to create new
        //Person object.
        getAddOrModifyButton().addActionListener((ActionEvent e) -> {
            
            String surname = getSurnameField().getText();
            String forename = getForenameField().getText();
            String townland = getTownlandField().getText();
            String _DED = getDEDfield().getText();
            String county = getCountyField().getText();
            int age = (int) getAgeComboBox().getSelectedItem();
            String sex = null;
            if(getMaleButton().isSelected()) {
                sex = "M";
            }
            else if(getFemaleButton().isSelected()) {
                sex = "F";
            }
            String birthplace = getBirthplaceField().getText();
            String occupation = getOccupationField().getText();
            String religion = getReligionField().getText();
            String literacy = getLiteracyField().getText();
            String irishLanguage = getIrishLanguageField().getText();
            String relationTo = getRelationToField().getText();
            String maritalStatus = getMaritalStatusField().getText();
            String specifiedIllness = getSpecifiedIllnessField().getText();
            
            Person person = new Person(surname, forename, townland, _DED, county, age,
                                sex, birthplace, occupation, religion, literacy, irishLanguage,
                                relationTo, maritalStatus, specifiedIllness);
            DatabaseController.getInstance().getCensusDataPersistor().addNewEntry(person);
            CensusController.getInstance().getCensusDataModel().addPerson(person);
            CensusController.getInstance().getGUIReference().refresh();
            dispose();
            
        });
    }

    
    
}
