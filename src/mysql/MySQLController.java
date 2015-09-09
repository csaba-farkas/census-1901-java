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
package mysql;

import controller.CensusController;
import controller.CensusDataPersistor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.CensusData;
import model.Person;

/**
 * Data persistor class. Handles connection to the database and any insert/update/
 * delete operations.
 * 
 * @author Csaba Farkas R00117945
 */
public class MySQLController implements CensusDataPersistor{

    //Instance variables
    private final String URL = "jdbc:mysql://79.170.44.155:3306/cl36-census";    //Location of database
    private final String USER_NAME = "cl36-census";                            //Database username
    private final String PASSWORD = "censusProject";                                 //Database password
    private Connection connection;                                      //Connection object
    private CensusData censusData;                                      //CensusData object
    
    /**
     * Getter method.
     * 
     * @return CensusData object.
     */
    @Override
    public CensusData getCensusData() {
        return this.censusData;
    }

    /**
     * Setter method.
     * 
     * @param censusData CensusData object.
     */
    @Override
    public void setCensusData(CensusData censusData) {
        this.censusData = censusData;
    }
    
    /**
     * Method create a connection and logs into the database.
     */
    private void connectAndLogin() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex) {
            System.err.println("Error: unable to load driver class!");
            System.err.println(ex.getMessage());
        }
        
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            System.err.println("Error: wrong username or password!");
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * After every read or write method used the connection must be closed.
     * Otherwise the data is lost.
     */
    private void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Error: connection was not established!");
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Retrieve all data from database. 
     */
    @Override
    public void retreiveAllData() {
        
        //Create connection.
        this.connectAndLogin();
        
        try {
            Statement statement = this.connection.createStatement();
            String query = "SELECT * FROM 1901_census";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    
                    Person person = new Person();
                    
                    person.setSurname(resultSet.getString("Surname"));
                    person.setForename(resultSet.getString("Forename"));
                    person.setTownland(resultSet.getString("Townland"));
                    person.setDED(resultSet.getString("DED"));
                    person.setCounty(resultSet.getString("County"));
                    person.setAge(resultSet.getInt("Age"));
                    person.setSex(resultSet.getString("Sex"));
                    person.setBirthplace(resultSet.getString("Birthplace"));
                    person.setOccupation(resultSet.getString("Occupation"));
                    person.setReligion(resultSet.getString("Religion"));
                    person.setLiteracy(resultSet.getString("Literacy"));
                    person.setIrishLanguage(resultSet.getString("Irish_Language"));
                    person.setRelationToHeadOfHousehold(resultSet.getString("Relation_to_Head_of_Household"));
                    person.setMaritalStatus(resultSet.getString("Marital_Status"));
                    person.setSpecifiedIllnesses(resultSet.getString("Specified_Illness"));
                    
                    this.censusData.addPerson(person);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //Set the data used by the program to the data that was retrieved.
        CensusController.getInstance().setCensusDataModel(this.censusData);
        this.close();
    }
    
    /**
     * Add new person to database.
     * 
     * @param person new Person object to be added to the database.
     */
    @Override
    public void addNewEntry(Person person) {
        
        this.connectAndLogin();
        try {
            
            String query =  "INSERT INTO 1901_census (" +
                            "Surname, " +
                            "Forename, " + 
                            "Townland, " + 
                            "DED, " + 
                            "County, " +
                            "Age, " +
                            "Sex, " +
                            "Birthplace, " +
                            "Occupation, " +
                            "Religion, " +
                            "Literacy, " +
                            "Irish_Language, " +
                            "Relation_to_Head_of_Household, " +
                            "Marital_Status, " +
                            "Specified_Illness" +
                            ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, person.getSurname());
            preparedStatement.setString(2, person.getForename());
            preparedStatement.setString(3, person.getTownland());
            preparedStatement.setString(4, person.getDED());
            preparedStatement.setString(5, person.getCounty());
            preparedStatement.setInt(6, (Integer)person.getAge());
            preparedStatement.setString(7, person.getSex());
            preparedStatement.setString(8, person.getBirthplace());
            preparedStatement.setString(9, person.getOccupation());
            preparedStatement.setString(10, person.getReligion());
            preparedStatement.setString(11, person.getLiteracy());
            preparedStatement.setString(12, person.getIrishLanguage());
            preparedStatement.setString(13, person.getRelationToHeadOfHousehold());
            preparedStatement.setString(14, person.getMaritalStatus());
            preparedStatement.setString(15, person.getSpecifiedIllnesses());
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Database Error" , JOptionPane.ERROR_MESSAGE);
        }
        this.close();
    }

    /**
     * Remove a person from the database.
     * 
     * @param primaryKey Indicates the primary key of the record to be deleted.
     */
    @Override
    public void removeEntry(Object[] primaryKey) {
        this.connectAndLogin();
        
        try {
            String query = "DELETE FROM 1901_census WHERE Surname=? AND Forename=? AND Townland=? AND Age=? AND Birthplace=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            
            preparedStatement.setString(1, (String) primaryKey[0]);
            preparedStatement.setString(2, (String) primaryKey[1]);
            preparedStatement.setString(3, (String) primaryKey[2]);
            preparedStatement.setInt(4, (Integer) primaryKey[3]);
            preparedStatement.setString(5, (String) primaryKey[4]);
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Database Error" , JOptionPane.ERROR_MESSAGE);
        }
        this.close();
    }

    /**
     * Modify person.
     * 
     * @param primaryKey Indicates the primary key of the record to be updated.
     * @param person  Indicates the new person object replacing the old one.
     */
    @Override
    public void modifyEntry(Object[] primaryKey, Person person) {
        this.connectAndLogin();
        try {
            String query = "UPDATE 1901_census SET " +
                            "Surname = ?, " +
                            "Forename = ?, " + 
                            "Townland = ?, " + 
                            "DED = ?, " + 
                            "County = ?, " +
                            "Age = ?, " +
                            "Sex = ?, " +
                            "Birthplace = ?, " +
                            "Occupation = ?, " +
                            "Religion = ?, " +
                            "Literacy = ?, " +
                            "Irish_Language = ?, " +
                            "Relation_to_Head_of_Household = ?, " +
                            "Marital_Status = ?, " +
                            "Specified_Illness = ?" +
                            "WHERE Surname = ? AND Forename = ? AND Townland = ? AND Age = ? AND Birthplace = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            //Details of modified person
            preparedStatement.setString(1, person.getSurname());
            preparedStatement.setString(2, person.getForename());
            preparedStatement.setString(3, person.getTownland());
            preparedStatement.setString(4, person.getDED());
            preparedStatement.setString(5, person.getCounty());
            preparedStatement.setInt(6, (Integer)person.getAge());
            preparedStatement.setString(7, person.getSex());
            preparedStatement.setString(8, person.getBirthplace());
            preparedStatement.setString(9, person.getOccupation());
            preparedStatement.setString(10, person.getReligion());
            preparedStatement.setString(11, person.getLiteracy());
            preparedStatement.setString(12, person.getIrishLanguage());
            preparedStatement.setString(13, person.getRelationToHeadOfHousehold());
            preparedStatement.setString(14, person.getMaritalStatus());
            preparedStatement.setString(15, person.getSpecifiedIllnesses());
            //Where PK = ?, ?, ?, ?
            preparedStatement.setString(16, (String) primaryKey[0]);
            preparedStatement.setString(17, (String) primaryKey[1]);
            preparedStatement.setString(18, (String) primaryKey[2]);
            preparedStatement.setInt(19, (Integer) primaryKey[3]);
            preparedStatement.setString(20, (String) primaryKey[4]);
            
            preparedStatement.execute();
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Database Error" , JOptionPane.ERROR_MESSAGE);
        }
        
        this.close();
    }

}
