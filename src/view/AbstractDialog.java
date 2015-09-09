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

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;

/**
 * An abstract class that is extended by the dialog classes in the program.
 * @author Csaba Farkas R00117945
 */
public abstract class AbstractDialog extends JDialog {

    //Final static variables. Labels etc. 
    public static final String SURNAME_LABEL = "<html><span style='color: red;'>*</span> Surname: </html>";
    public static final String FORENAME_LABEL = "<html><span style='color: red;'>*</span> Forename: </html>";
    public static final String TOWNLAND_LABEL = "<html><span style='color: red;'>*</span> Townland: </html>";
    public static final String DED_LABEL = "DED: ";
    public static final String COUNTY_LABEL = "County: ";
    public static final String AGE_LABEL = "<html><span style='color: red;'>*</span> Age: </html>";
    public static final String SEX_LABEL = "Sex: ";
    public static final String MALE_LABEL = "Male";
    public static final String FEMALE_LABEL = "Female";
    public static final String BIRTHPLACE_LABEL = "<html><span style='color: red;'>*</span> Birthplace: </html>";
    public static final String OCCUPATION_LABEL = "Occupation: ";
    public static final String RELIGION_LABEL = "Religion: ";
    public static final String LITERACY_LABEL = "Literacy: ";
    public static final String IRISH_LANGUAGE_LABEL = "Irish Language: ";
    public static final String RELATION_TO_LABEL = "Relation to Head of Household: ";
    public static final String MARITAL_STATUS = "Marital status: ";
    public static final String SPECIFIED_ILLNESS_LABEL = "Specified Illness: ";
    public static final String ADD_PERSON_BUTTON_LABEL = "Add Person";
    public static final String MODIFY_PERSON_BUTTON_LABEL = "Modify";
    public static final String CLOSE_BUTTON_LABEL = "Cancel";
    public static final Integer[] AGE_VALUES = getAgeValues();
    public static final String BORDER_TITLE = "Person Details";
    public static final String MANDATORY_LABEL = "<html><span style='color: red;'>*</span>" + 
                                                 "<span style='font-style: italic; color: gray'> Fields are required</span></html>";
    
    //Instance variables
    private JTextField surnameField;        //inidicating JTextFields
    private JTextField forenameField;
    private JTextField townlandField;
    private JTextField _DEDfield;
    private JTextField countyField;
    private JTextField birthplaceField;
    private JTextField occupationField;
    private JTextField religionField;
    private JTextField literacyField;
    private JTextField irishLanguageField;
    private JTextField relationToField;
    private JTextField maritalStatusField;
    private JTextField specifiedIllnessField;
    private JComboBox ageComboBox;          //indicating a JComboBox for age values
    private JRadioButton maleButton;        //indicating radio buttons (male/female)
    private JRadioButton femaleButton;
    private JButton addOrModifyButton;      //indicating JButtons
    private JButton closeButton;
    private boolean comboItemHasFocus;          //boolean values used by DocumentListener
    private boolean requiredFieldsHasData;
    
    //--------------------------------------------------------------------------
    /**
     * Constructor method calls the inherited JDialog constructor with JFrame 
     * owner and String title as parameters.
     * 
     * @param owner is a JFrame object
     * @param title of the frame
     * @param width of the owner frame
     * @param height of the owner frame
     */
    public AbstractDialog(JFrame owner, String title, int width, int height) {
        
        super(owner, title);
        this.getContentPane().add(createPersonDetailsPanel());
        pack();
        
        this.comboItemHasFocus = false;
        this.requiredFieldsHasData = false;
        //Position the dialog in the center of the screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int censusDialogWidth = this.getSize().width;
        int censusDialogHeight = this.getSize().height;
        int locationX = (dimension.width - censusDialogWidth)/2;
        int locationY = (dimension.height - censusDialogHeight)/2;
        this.setLocation(locationX, locationY);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Method that creates the form in the dialog box
     * @return a JPanel object.
     */
    private JPanel createPersonDetailsPanel() {
        
        /*
        For the form I used MigLayout. This type of layout is not part of the core
        Java API but it is a free open source library. For more information, please
        visit http://www.miglayout.com/.
        */
        
        //Main panel is creted and layout is set to MigLayout with some gapping
        JPanel mainPanel = new JPanel(new MigLayout());
        JPanel personDetailsPanel = new JPanel(new MigLayout("", "10[]2[]7[]2[]10", "10[]10[]10"));
        
        /*
        MigLayout divides a panel into equal sized chunks. Every element that we
        insert gets the same size of space. When we want an element to go into a 
        new row, we use the 'wrap' @param.
        */
        JLabel mandatoryLabel = new JLabel(AbstractDialog.MANDATORY_LABEL);
        personDetailsPanel.add(mandatoryLabel, "span");
        //Create first row of form including surname and forename labels and fields
        JLabel surnameLabel = new JLabel(AbstractDialog.SURNAME_LABEL);
        personDetailsPanel.add(surnameLabel);
        this.surnameField = new JTextField(10);
        this.surnameField.getDocument().addDocumentListener(new CensusFieldListener());
        personDetailsPanel.add(this.surnameField);
        JLabel forenameLabel = new JLabel(AbstractDialog.FORENAME_LABEL);
        personDetailsPanel.add(forenameLabel);
        this.forenameField = new JTextField(10);
        this.forenameField.getDocument().addDocumentListener(new CensusFieldListener());
        personDetailsPanel.add(this.forenameField, "wrap");
        
        /*
        Create second row including Townland label and field. I wanted this field
        to be longer than the fields in the first row so I used the "span, grow"
        @param.
        */        
        JLabel townLandLabel = new JLabel(AbstractDialog.TOWNLAND_LABEL);
        personDetailsPanel.add(townLandLabel);
        this.townlandField = new JTextField();
        this.townlandField.getDocument().addDocumentListener(new CensusFieldListener());
        personDetailsPanel.add(this.townlandField, "span, grow");
        
        //Same scenario in the third row, DED label and field included
        JLabel _DEDLabel = new JLabel(AbstractDialog.DED_LABEL);
        personDetailsPanel.add(_DEDLabel);
        this._DEDfield = new JTextField();
        personDetailsPanel.add(this._DEDfield, "span, grow");
        
        /*
        Fourth row has the county label and field. Also it has the age label, and
        a combo box with values 0-120 so the user cannot enter any other number.
        */
        JLabel countyLabel = new JLabel(AbstractDialog.COUNTY_LABEL);
        personDetailsPanel.add(countyLabel);
        this.countyField = new JTextField(10);
        personDetailsPanel.add(this.countyField);
        JLabel ageLabel = new JLabel(AbstractDialog.AGE_LABEL);
        personDetailsPanel.add(ageLabel);
        this.ageComboBox = new JComboBox(AbstractDialog.AGE_VALUES);
        this.ageComboBox.addActionListener((ActionEvent e) -> {
            this.comboItemHasFocus = this.ageComboBox.getSelectedItem() != null;
            enableAddPersonButton(this.comboItemHasFocus);
        });
        personDetailsPanel.add(this.ageComboBox, "wrap");
        
        /*
        Fifth row contains the 'sex' label and two radio buttons (male and female)
        grouped together. In the database, sex is an enum that only accepts 'M' or
        'F' as valid values. So depending on what the user chooses I pass one of
        those two values to the Database controller when creating a new entry.
        */
        JLabel sexLabel = new JLabel(AbstractDialog.SEX_LABEL);
        personDetailsPanel.add(sexLabel);
        this.maleButton = new JRadioButton(AbstractDialog.MALE_LABEL);
        this.maleButton.setMnemonic(KeyEvent.VK_M);     //Can be selected with Alt+M
        this.maleButton.setActionCommand(AbstractDialog.MALE_LABEL);
        personDetailsPanel.add(this.maleButton);        
        this.femaleButton = new JRadioButton(AbstractDialog.FEMALE_LABEL);
        this.femaleButton.setMnemonic(KeyEvent.VK_F);   //Can be selected with Alt+F
        this.femaleButton.setActionCommand(AbstractDialog.FEMALE_LABEL);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.maleButton);
        buttonGroup.add(this.femaleButton);
        personDetailsPanel.add(this.femaleButton, "wrap");
        
        //Sixth row contains birthplace and occupation labels and fields
        JLabel birthplaceLabel = new JLabel(AbstractDialog.BIRTHPLACE_LABEL);
        personDetailsPanel.add(birthplaceLabel);
        this.birthplaceField = new JTextField(10);
        this.birthplaceField.getDocument().addDocumentListener(new CensusFieldListener());
        personDetailsPanel.add(this.birthplaceField);
        JLabel occupationLabel = new JLabel(AbstractDialog.OCCUPATION_LABEL);
        personDetailsPanel.add(occupationLabel);
        this.occupationField = new JTextField(10);
        personDetailsPanel.add(this.occupationField, "wrap");
        
        //Seventh row has religion and literacy labels and fields
        JLabel religionLabel = new JLabel(AbstractDialog.RELIGION_LABEL);
        personDetailsPanel.add(religionLabel);
        this.religionField = new JTextField(10);
        personDetailsPanel.add(this.religionField);
        JLabel literacyLabel = new JLabel(AbstractDialog.LITERACY_LABEL);
        personDetailsPanel.add(literacyLabel);
        this.literacyField = new JTextField(10);
        personDetailsPanel.add(this.literacyField, "wrap");
        
        /*
        In the 8th row the label is significantly longer than the labels I had
        in the previous rows so the whole form was looking a bit strange. So I
        made the label occupy 2 'chunks' of the 8th row.
        For the same reasons I used the same method in the rest of the form.
        */
        JLabel irishLanguageLabel = new JLabel(AbstractDialog.IRISH_LANGUAGE_LABEL);
        personDetailsPanel.add(irishLanguageLabel, "span 2");
        this.irishLanguageField = new JTextField(10);
        personDetailsPanel.add(this.irishLanguageField, "span, grow");
        
        JLabel relationToLabel = new JLabel(AbstractDialog.RELATION_TO_LABEL);
        personDetailsPanel.add(relationToLabel, "span 2");
        this.relationToField = new JTextField(10);
        personDetailsPanel.add(this.relationToField, "span, grow");
        
        JLabel maritalStatusLabel = new JLabel(AbstractDialog.MARITAL_STATUS);
        personDetailsPanel.add(maritalStatusLabel, "span 2");
        this.maritalStatusField = new JTextField(10);
        personDetailsPanel.add(this.maritalStatusField, "span, grow");
        JLabel specifiedIllnessLabel = new JLabel(AbstractDialog.SPECIFIED_ILLNESS_LABEL);
        personDetailsPanel.add(specifiedIllnessLabel, "span 2");
        this.specifiedIllnessField = new JTextField(10);
        personDetailsPanel.add(this.specifiedIllnessField, "span, grow");
              
        //The next panel has two buttons: Add Person and Cancel
        JPanel buttonsPanel = new JPanel(new MigLayout("", "100[]10[]5[]", ""));
        buttonsPanel.add(new JLabel(""));
        this.addOrModifyButton = new JButton();
        this.addOrModifyButton.setBackground(new Color(204, 255, 229));
        this.addOrModifyButton.setEnabled(false);
        buttonsPanel.add(this.addOrModifyButton);
        
        this.closeButton = new JButton(AbstractDialog.CLOSE_BUTTON_LABEL);
        this.closeButton.setBackground(new Color(204, 255, 229));
        //Add actionListener to cancel button
        this.closeButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        buttonsPanel.add(this.closeButton);
        
        //Add a border and customize it.
        TitledBorder titleBorder = BorderFactory.createTitledBorder(AbstractDialog.BORDER_TITLE);
        titleBorder.setTitleColor(java.awt.Color.GRAY);
        personDetailsPanel.setBorder(titleBorder);
        mainPanel.add(personDetailsPanel, "wrap");
        mainPanel.add(buttonsPanel);
        
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        return mainPanel;
    }

    //Getter and setter methods
    public JTextField getSurnameField() {
        return surnameField;
    }

    
    public JTextField getForenameField() {
        return forenameField;
    }

    public JTextField getTownlandField() {
        return townlandField;
    }

    public JTextField getDEDfield() {
        return _DEDfield;
    }

    public JTextField getCountyField() {
        return countyField;
    }

    public JComboBox getAgeComboBox() {
        return ageComboBox;
    }

    public JRadioButton getMaleButton() {
        return maleButton;
    }

    public JRadioButton getFemaleButton() {
        return femaleButton;
    }

    public JTextField getBirthplaceField() {
        return birthplaceField;
    }

    public JTextField getOccupationField() {
        return occupationField;
    }

    public JTextField getReligionField() {
        return religionField;
    }

    public JTextField getLiteracyField() {
        return literacyField;
    }

    public JTextField getIrishLanguageField() {
        return irishLanguageField;
    }

    public JTextField getRelationToField() {
        return relationToField;
    }

    public JTextField getMaritalStatusField() {
        return maritalStatusField;
    }

    public JTextField getSpecifiedIllnessField() {
        return specifiedIllnessField;
    }
    
    public JButton getAddOrModifyButton() {
        return this.addOrModifyButton;
    }

    public void setAddOrModifyButton(JButton button) {
        this.addOrModifyButton = button;
    }
    
    /**
     * A private function that returns an Integer array with values 0 - 120. 
     * Used in JComboBox.
     * 
     * @return Integer array.
     */
    private static Integer[] getAgeValues() {
        Integer[] ageValues = new Integer[121];
        for(int i = 0; i <= 120; i++) {
            ageValues[i] = i;
        }
        return ageValues;
    }

    /**
     * This function enables the add button if the name of the function called
     * this function is 'actionPerformed' and all the required text field has 
     * data content, or if this function was called by 'toggleAdd' function
     * (used by DocumentListener) and a value has been selected from the 
     * JComboBox containing age values.
     * 
     * @param enabled A boolean value.
     */
    private void enableAddPersonButton(boolean enabled) {
            //Get the name of the function that called this function from stackTrace.
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement e = stacktrace[3];
            String methodName = e.getMethodName();
            if(!enabled) {
                this.addOrModifyButton.setEnabled(false);
                return;
            }
            if(methodName.equals("actionPerformed")) {
                if(this.requiredFieldsHasData) {
                    this.addOrModifyButton.setEnabled(enabled);
                }
            }
            if(methodName.equals("toggleAdd")) {
                if(this.comboItemHasFocus) {
                    this.addOrModifyButton.setEnabled(enabled);
                }
            }
    }
    
    /**
     * An inner class implementing DocumentListener. Calls toggleAdd function
     * every time an element of the document is updated. (data entered, removed,
     * updated)
     */
    private class CensusFieldListener implements DocumentListener{

        public CensusFieldListener() {
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            toggleAdd();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            toggleAdd();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            toggleAdd();
        }
        
        //toglaAdd() function has an array containing all of the JTextField objects that are primary keys.
        //Those fields cannot be null.
        //If method is called, it enables 'Add' button only and only if all of the fields 
        //contain some data (only whitespaces are not considered as data)
        private void toggleAdd() {
            
            boolean enabled = true;
           
            JTextField[] jTextFields = {surnameField, forenameField, townlandField, birthplaceField,};
                
            for(JTextField field : jTextFields) {
                
                if(field.getText().trim().isEmpty()) {
                    enabled = false;
                    break;
                }
            }
            
            requiredFieldsHasData = enabled;
            enableAddPersonButton(enabled);
        }

        

    }
}
