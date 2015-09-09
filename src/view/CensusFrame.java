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
import controller.CensusGUI;
import controller.DatabaseController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.Person;
import net.miginfocom.swing.MigLayout;
import sun.java2d.SunGraphicsEnvironment;

/**
 * The main GUI component. Extend JFrame class and implements CensusGUI interface.
 * 
 * @author Csaba Farkas R00117945
 */
public class CensusFrame extends JFrame implements CensusGUI {
    
    //--------------------------------------------------------------------------
    //Static final variables used as labels and colum names etc.
    private static final String QUERY_ALL_LABEL = "<html>Query All</html>";                       
    private static final String SHOW_MORE_LABEL = "<html>Show More</html>";
    private static final String SHOW_LESS_LABEL = "<html>Show Less</html>";
    private static final String ADD_BUTTON_LABEL = "<html>Add...</html>";
    private static final String MODIFY_BUTTON_LABEL = "<html>Modify...</html>";
    private static final String DELETE_BUTTON_LABEL = "<html>Delete...</html>";
    private static final String BIRTHPLACE_COL = "Birthplace";
    private static final String OCCUPATION_COL = "Occupation";
    private static final String RELIGION_COL = "Religion";
    private static final String LITERACY_COL ="Literacy";
    private static final String IRISH_LANGUAGE_COL = "Irish Language";
    private static final String RELATION_TO_COL = "Relation to Head of Household";
    private static final String MARITAL_STATUS = "Marital Status";
    private static final String SPECIFIED_ILLNESS_COL = "Specified Illness";    
    private static final String MODIFY_DIALOG_LABEL = "Modify Person";
    private static final String ADD_DIALOG_LABEL = "Add New Person";
    private static final String FILTER_LABEL = "Filter: ";
    private static final String CANCEL_LABEL = "<html>Cancel</html>";
    
    //--------------------------------------------------------------------------
    private CensusTableModel censusTableModel;      //indicates table model used
    private TableColumnModel tableColumnModel;      //indicates table column model used
    private JTable censusTable;                     //indicates JTable
    private JButton queryAll;                       //indicates a JButton object
    private JButton addNew;                         //indicates a JButton object
    private JButton deleteButton;                   //indicates a JButton object
    private JButton showButton;                     //indicates a JButton object
    private JButton hideButton;                     //indicates a JButton object
    private JButton modifyButton;                   //indicates a JButton object
    private JButton cancelButton;                   //indicates a JButton object
    private JTextField filterField;                 //indicates a JTextField object
    private JLabel filterLabel;                     //indicates a JLabel object
    private final ArrayList<String> columnNames;    //ArraList that stores column names
    private HashMap hiddenColumns;                  //HashMap that stores columns hidden
    private TableRowSorter<CensusTableModel> sorter;              //TableRowSorter used when sorting/filtering
    private JPanel fieldPanel;                      //indicates a fieldPanel object
    private JTextArea textArea;                     //indicates a JTestArea object
    
    
    
    //--------------------------------------------------------------------------
    /**
     * Constructor uses the inherited constructor method. 
     * It initializes some of the instance variables. Creates different parts of 
     * the frame and insert them to the right position. It also positions the 
     * frame so it appears in full screen mode without the task bar. 
     * 
     * @param title 
     */
    public CensusFrame(String title) {
        super(title);
        
        //Get the columns that are hidden initially and store them in $columnNames
        this.columnNames = new ArrayList<>();
        this.columnNames.add(CensusFrame.BIRTHPLACE_COL);
        this.columnNames.add(CensusFrame.OCCUPATION_COL);
        this.columnNames.add(CensusFrame.RELIGION_COL);
        this.columnNames.add(CensusFrame.LITERACY_COL);
        this.columnNames.add(CensusFrame.IRISH_LANGUAGE_COL);
        this.columnNames.add(CensusFrame.RELATION_TO_COL);
        this.columnNames.add(CensusFrame.MARITAL_STATUS);
        this.columnNames.add(CensusFrame.SPECIFIED_ILLNESS_COL);
        
        //Also, create a HashMap where table columns that are removed can be stored
        this.hiddenColumns = new HashMap();
        
        //Create a JPanel, call various methods to create different parts of the frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(createTablePanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonsPanel(), BorderLayout.EAST);
        
        //Create JPanel that contains the filter label and field
        this.fieldPanel = new JPanel(new MigLayout());
        this.filterLabel = new JLabel(CensusFrame.FILTER_LABEL);
        fieldPanel.add(this.filterLabel);
        this.filterField = new JTextField(40);
        
        //Whenever filterText changes, invoke newFilter. - !Filtering!
        this.filterField.getDocument().addDocumentListener(
                //DocumentListener calls newFilter method any time filterField updated
                new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        this.fieldPanel.add(this.filterField);
        //Hide fieldPanel initially
        this.fieldPanel.setVisible(false);
        
        mainPanel.add(this.fieldPanel, BorderLayout.NORTH);
        
        this.getContentPane().add(mainPanel);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Next piece of code makes JFrame to appear full screen without taskbar
        //And sets the minimum size of the frame to 700x500 pixels
        this.setMinimumSize(new Dimension(700, 500));
        GraphicsConfiguration config = this.getGraphicsConfiguration();
        Rectangle usableBounds = SunGraphicsEnvironment.getUsableBounds(config.getDevice());
        setMaximizedBounds(usableBounds);
        setExtendedState(MAXIMIZED_BOTH);
        
        this.setVisible(true);
        
    }

    //--------------------------------------------------------------------------
    /**
     * Method included in the implemented interface. This would allow the program 
     * to be implemented with a different type of GUI if it was used on different 
     * platforms. This is one major advantage of MVC design. In this case, this 
     * method fires a GUI update when data was inserted/deleted/modified.
     */
    @Override
    public void refresh() {
        this.censusTableModel.fireTableDataChanged();
    }

    //--------------------------------------------------------------------------
    /**
     * Function that creates the table on the screen. I used JScrollPane class 
     * when created this panel so the user can scroll down or to the side when 
     * the table is too big to be displayed on the screen. Within this function 
     * some of the field variables (table, table model) are initialized and 
     * created.
     * 
     * @return JScrollPane object
     */
    private Component createTablePanel() {
        
        //Create table, table model, table column model and sorter. Attach them together.
        JPanel centerPanel = new JPanel();
        BoxLayout box = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(box);
        this.censusTable = new JTable();
        this.censusTable.setAutoCreateRowSorter(true);      //Clicking on column headers sort columns
        this.censusTableModel = new CensusTableModel(CensusController.getInstance().getCensusDataModel().getCensusData());
        this.censusTableModel.setPeople(CensusController.getInstance().getCensusDataModel().getCensusData());
        this.censusTable.setModel(this.censusTableModel);
        this.tableColumnModel = this.censusTable.getColumnModel();
        this.censusTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);         //Disable multiple selection
        this.sorter = new TableRowSorter<>(this.censusTableModel);
        this.censusTable.setRowSorter(this.sorter);
        
        this.censusTable.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            int viewRow = this.censusTable.getSelectedRow();
            if (viewRow < 0) {
                //Selection got filtered away.
                this.textArea.setText("");
            } else {
                /*
                When a row is selected, and on of the columns are sorted, the indexes of the model and the
                view became different. Jave provides a method to convert the index of the view to the
                index of the model. 'convertRowIndexToModel(viewIndex)'
                */
                int modelRow = this.censusTable.convertRowIndexToModel(viewRow);
                this.textArea.setText(CensusController.getInstance().getCensusDataModel().getCensusData().get(modelRow).toString());
            }
        });
        
        //Get table header, change color and font. Attach CensusTableRenderer, create border
        JTableHeader tableHeader = this.censusTable.getTableHeader();
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD));
        tableHeader.setBackground(new Color(107, 161, 237));
        this.censusTable.setDefaultRenderer(Object.class, new CensusTableRenderer());
        hideColumns();
        JScrollPane tablePane = new JScrollPane(this.censusTable); 
        tablePane.setBorder(BorderFactory.createTitledBorder(""));
        centerPanel.add(tablePane);
       
        //Create textARea and customize it
        this.textArea = new JTextArea();
        //this.textArea.setLineWrap(true);
        //this.textArea.setWrapStyleWord(true);
        this.textArea.setEditable(false);
        this.textArea.setBorder(BorderFactory.createTitledBorder(""));
        centerPanel.add(this.textArea);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return centerPanel;
    }

    //--------------------------------------------------------------------------
    /**
     * Method that creates the panel on the right hand side that has the buttons. 
     * When the program starts, there is only one button at present: 'Query All'. 
     * There is an ActionListener attached to this button, that calls the 
     * DatabaseController to connect to the database and retrieve all the data. 
     * Then CensusController notifies the GUI reference (this JFrame) that some 
     * of the data that is displayed was changed. The GUI updates itself and 
     * displays all of the records. All of the other buttons ('Show more', 
     * 'Show less', 'Add', 'Delete' etc.) are displayed at this time and the 
     * 'Query All' button disappears.
     * 
     * @return a JPanel with all of the buttons
     */
    private Component createButtonsPanel() {
        
        JPanel panelWithButtons = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panelWithButtons, BoxLayout.Y_AXIS);
        panelWithButtons.setLayout(boxLayout);
        
        JPanel queryPanel = new JPanel();
        //----------------------------------------------------------------------
        //Query All button
        this.queryAll = new JButton(CensusFrame.QUERY_ALL_LABEL);       
        this.queryAll.addActionListener((ActionEvent e) -> {
            DatabaseController.getInstance().getCensusDataPersistor().retreiveAllData();
            CensusController.getInstance().getGUIReference().refresh();
            queryPanel.setVisible(false);
            this.showButton.setVisible(true);
            this.addNew.setVisible(true);
            this.modifyButton.setVisible(true);
            this.deleteButton.setVisible(true);
            this.filterLabel.setVisible(true);
            this.filterField.setVisible(true);
            this.fieldPanel.setVisible(true);
            this.cancelButton.setVisible(true);
        });
        queryPanel.add(this.queryAll);
        
        panelWithButtons.add(queryPanel);
        
        //----------------------------------------------------------------------
        //Show More button - initially hidden
        this.showButton = new JButton(CensusFrame.SHOW_MORE_LABEL);
        this.showButton.setVisible(false);            
        //Add ActionListener: show hidden columns when Show More is clicked on
        this.showButton.addActionListener((ActionEvent e) -> {
            
            this.censusTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            ArrayList<Object> keySet = new ArrayList<>();
            for(Object o : this.hiddenColumns.keySet()) {
                keySet.add(o);
            }
            
            for(int i = 0; i < keySet.size(); i++) {
                Object o = hiddenColumns.remove(keySet.get(i));
                if (o == null) {
                    return;
                }
                this.tableColumnModel.addColumn((TableColumn) o);
            }
            //Set width of columns if all of the columns are shown
            this.tableColumnModel.getColumn(0).setPreferredWidth(100);
            this.tableColumnModel.getColumn(1).setPreferredWidth(100);
            this.tableColumnModel.getColumn(2).setPreferredWidth(150);
            this.tableColumnModel.getColumn(3).setPreferredWidth(150);
            this.tableColumnModel.getColumn(4).setPreferredWidth(100);
            this.tableColumnModel.getColumn(5).setPreferredWidth(40);
            this.tableColumnModel.getColumn(6).setPreferredWidth(40);
            this.tableColumnModel.getColumn(7).setPreferredWidth(125);
            this.tableColumnModel.getColumn(8).setPreferredWidth(125);
            this.tableColumnModel.getColumn(9).setPreferredWidth(125);
            this.tableColumnModel.getColumn(10).setPreferredWidth(100);
            this.tableColumnModel.getColumn(11).setPreferredWidth(125);
            this.tableColumnModel.getColumn(12).setPreferredWidth(150);
            this.tableColumnModel.getColumn(13).setPreferredWidth(100);
            this.tableColumnModel.getColumn(14).setPreferredWidth(100);
            
            this.hiddenColumns = new HashMap();
            //Hide 'Show More' button and show 'Show Less' button
            this.showButton.setVisible(false);
            this.hideButton.setVisible(true);
        
        });
        panelWithButtons.add(this.showButton);
        
        //panelWithButtons.add(Box.createVerticalStrut(5));
        //----------------------------------------------------------------------
        //Show Less button - initially hidden
        this.hideButton = new JButton(CensusFrame.SHOW_LESS_LABEL);
        this.hideButton.setVisible(false);
        //Add ActionListener: hide columns other than the first 7
        this.hideButton.addActionListener((ActionEvent e) -> {
            this.censusTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            hideColumns();
            //Hide 'Show Less' button and show 'Show More' button
            this.showButton.setVisible(true);
            this.hideButton.setVisible(false);
        });
        
        panelWithButtons.add(this.hideButton);
        
        panelWithButtons.add(Box.createVerticalStrut(5));
        
        //----------------------------------------------------------------------
        //Add button - initially hidden
        this.addNew = new JButton(CensusFrame.ADD_BUTTON_LABEL);
        this.addNew.setVisible(false);
        //Add ActionListener - Create a new addNewPersonDialog when clicked on
        this.addNew.addActionListener((ActionEvent e) -> {
            AddNewPersonDialog addNewPersonDialog = new AddNewPersonDialog(CensusFrame.this, 
                                                                           CensusFrame.ADD_DIALOG_LABEL, 
                                                                           CensusFrame.this.getSize().width, 
                                                                           CensusFrame.this.getSize().height);
        });
        panelWithButtons.add(this.addNew);
        
        panelWithButtons.add(Box.createVerticalStrut(5));
        
        //----------------------------------------------------------------------
        //Modify button - initially hidden and disabled
        this.modifyButton = new JButton(CensusFrame.MODIFY_BUTTON_LABEL);
        this.modifyButton.setEnabled(false);        //Gets enabled when row is selected
        this.modifyButton.setVisible(false);
        //Add ActionListener - Create a new modifyPersonDialog when clicked on
        this.modifyButton.addActionListener((ActionEvent e) -> {
            ModifyPersonDialog modifyPersonDialog = new ModifyPersonDialog(CensusFrame.this, 
                                                                          CensusFrame.MODIFY_DIALOG_LABEL, 
                                                                          CensusFrame.this.getSize().width, 
                                                                          CensusFrame.this.getSize().height, 
                                                                          getModelRowIndex());
        });
        panelWithButtons.add(this.modifyButton);
        
        panelWithButtons.add(Box.createVerticalStrut(5));
        
        //----------------------------------------------------------------------
        //Delete button - initially hidden and disabled
        this.deleteButton = new JButton(CensusFrame.DELETE_BUTTON_LABEL);
        this.deleteButton.setEnabled(false);        //Gets enabled when row is selected
        this.deleteButton.setVisible(false);
        //Add ActionListener - Confirm deletion with user and delet person from list
        this.deleteButton.addActionListener((ActionEvent e) -> {
            
            int rowSelected = this.censusTable.getSelectedRow();
            
            String warning = "Are you sure you want to delete this person?";
            int answer = JOptionPane.showConfirmDialog(rootPane, warning);
            
            if(answer == JOptionPane.YES_OPTION) {
                //Controller deletes the person from the database and from CensusData object by using the primary key
                Object[] primaryKey = getPrimaryKey(CensusController.getInstance().getCensusDataModel().getPersonAtRowNumber(getModelRowIndex()));
                DatabaseController.getInstance().getCensusDataPersistor().removeEntry(primaryKey);
                CensusController.getInstance().getCensusDataModel().removePerson(CensusController.getInstance().getCensusDataModel().getPersonAtRowNumber(getModelRowIndex()));
                CensusController.getInstance().getGUIReference().refresh();
            }   
            
        });
        panelWithButtons.add(this.deleteButton);
        panelWithButtons.add(Box.createVerticalStrut(5));
        
        //----------------------------------------------------------------------
        //Cancel button - initially hidden
        this.cancelButton = new JButton(CensusFrame.CANCEL_LABEL);
        this.cancelButton.setVisible(false);
        this.cancelButton.addActionListener((ActionEvent e) -> {
            int answer = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to exit?");
                if(answer != JOptionPane.NO_OPTION && answer != JOptionPane.CANCEL_OPTION) {
                    dispose();
                }
        });
        panelWithButtons.add(this.cancelButton);
        
     
        //----------------------------------------------------------------------
        //Enable delete and modify buttons if an element is selected
        this.censusTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(this.censusTable.getSelectedRow() != -1) {
                this.deleteButton.setEnabled(true);
                this.modifyButton.setEnabled(true);
            }
            else {
                this.deleteButton.setEnabled(false);
                this.modifyButton.setEnabled(false);
            }
        });
        
        //Customize buttons
        JButton[] buttons = {this.queryAll, this.showButton, this.hideButton, this.addNew, this.deleteButton, this.modifyButton, this.cancelButton};
        for(JButton button : buttons) {
            button.setBackground(new Color(204, 255, 229));
        }
        
        panelWithButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        return panelWithButtons;
    }

    /**
     * Method hiding columns. Remove columns and place them into hiddendColumns
     * HahsMap.
     */
    private void hideColumns() {
                
        for(String columnName : this.columnNames) {
            int index = this.tableColumnModel.getColumnIndex(columnName);
            TableColumn column = this.tableColumnModel.getColumn(index);
            this.hiddenColumns.put(columnName, column);
            this.tableColumnModel.removeColumn(column);
        }
    }

    /**
     * Get primary key static method.
     * 
     * @param personAtRowNumber Indicates a Person object.
     * @return an Object array.
     */
    public static Object[] getPrimaryKey(Person personAtRowNumber) {
        
        Object[] primaryKey = new Object[5];
        primaryKey[0] = personAtRowNumber.getSurname();
        primaryKey[1] = personAtRowNumber.getForename();
        primaryKey[2] = personAtRowNumber.getTownland();
        primaryKey[3] = personAtRowNumber.getAge();
        primaryKey[4] = personAtRowNumber.getBirthplace();
        
        return primaryKey;
    }

    /**
     * Private method used when filtering. Creates a RowFilter object and assign
     * the value (in lower case) that is read from filterField. Then the RowFilter 
     * object is set to be the RowFilter of the sorter (keeps only those rows in
     * the view where regex parsed from filterField is at present)
     */
   private void newFilter() {
        RowFilter<CensusTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            if(this.tableColumnModel.getColumnCount() == 7) {
                //Convert regexFilter values to lowercase with (?i)
                rf = RowFilter.regexFilter(("(?i)" + this.filterField.getText()), 0, 1, 2, 3, 4, 5, 6);
            }
            else {
                rf = RowFilter.regexFilter(("(?i)" + this.filterField.getText()));
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        this.sorter.setRowFilter(rf);
    }
    
    /**
     * This is one of the most important methods. After sorting the table the index
     * of elements in the model becomes different from the index of elements in the
     * view.
     * Java keeps track of these changes and provides a method called convertRowIndexToModel,
     * to convert a row number to an index number.
     * 
     * @return an integer indicating the position of an element in the list.
     */
    private int getModelRowIndex() {
        int viewRow = this.censusTable.getSelectedRow();
        int modelRow = this.censusTable.convertRowIndexToModel(viewRow);
        return modelRow;
   }
}
