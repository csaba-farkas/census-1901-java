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
 * Class that creates a JDialog for a form to modify a person.
 * @author Csaba Farkas R00117945
 * Date: 03-May-2015
 */
public class ModifyPersonDialog extends AbstractDialog {

    private Person personSelected;
    
    /**
     * Constructor method.
     * 
     * @param owner JFrame object owner of this JDialog.
     * @param title Title of this frame.
     * @param width Width of owner frame.
     * @param height Height of owner frame.
     * @param selectedRow Indicating the selected Person in the list (not in the view)
     */
    public ModifyPersonDialog(JFrame owner, String title, int width, int height, int selectedRow) {
        super(owner, title, width, height);
        this.personSelected = CensusController.getInstance().getCensusDataModel().getPersonAtRowNumber(selectedRow);
        System.out.println(this.personSelected);
        getAddOrModifyButton().setText(AbstractDialog.MODIFY_PERSON_BUTTON_LABEL);
        getSurnameField().setText(this.personSelected.getSurname());
        getForenameField().setText(this.personSelected.getForename());
        getTownlandField().setText(this.personSelected.getTownland());
        getDEDfield().setText(this.personSelected.getDED());
        getCountyField().setText(this.personSelected.getCounty());
        getAgeComboBox().setSelectedItem((int) this.personSelected.getAge());
        if(this.personSelected.getSex() != null) {
            switch (this.personSelected.getSex()) {
                case "M":
                    getMaleButton().setSelected(true);
                    break;
                case "F":
                    getFemaleButton().setSelected(true);
                    break;
            }
        }
        getBirthplaceField().setText(this.personSelected.getBirthplace());
        System.out.println("Bp: " + this.personSelected.getBirthplace());
        getOccupationField().setText(this.personSelected.getOccupation());
        getReligionField().setText(this.personSelected.getReligion());
        getLiteracyField().setText(this.personSelected.getLiteracy());
        getIrishLanguageField().setText(this.personSelected.getIrishLanguage());
        getRelationToField().setText(this.personSelected.getRelationToHeadOfHousehold());
        getMaritalStatusField().setText(this.personSelected.getMaritalStatus());
        getSpecifiedIllnessField().setText(this.personSelected.getSpecifiedIllnesses());
        
        //ActionListener attached to add 
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
            Object[] primaryKey = CensusFrame.getPrimaryKey(this.personSelected);
            DatabaseController.getInstance().getCensusDataPersistor().modifyEntry(primaryKey, person);
            CensusController.getInstance().getCensusDataModel().modifyPerson(selectedRow, person);
            CensusController.getInstance().getGUIReference().refresh();
            dispose();
            
        });
    }
}
