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


/**
 * Person class.
 * 
 * @author Csaba Farkas R00117945
 */
public class Person {

    //Instance variables as requested
    private String surname;
    private String forename;
    private String townland;
    private String DED;
    private String county;
    private int age;
    private String sex;
    private String birthplace;
    private String occupation;
    private String religion;
    private String literacy;
    private String irishLanguage;
    private String relationToHeadOfHousehold;
    private String maritalStatus;
    private String specifiedIllnesses;

    /**
     * Constructor method.
     * 
     * @param surname Indicating surname.
     * @param forename Indicating forename.
     * @param townland Indicating townland.
     * @param DED Indicating District Electoral Division.
     * @param county Indicating county.
     * @param age Indicating age.
     * @param sex Indicating sex.
     * @param birthplace Indicating birth place.
     * @param occupation Indicating occupation.
     * @param religion Indicating religion.
     * @param literacy Indicating level of literacy.
     * @param irishLanguage Indicating if person can speak in Irish.
     * @param relationToHeadOfHousehold Indicating person's relation to the head of household.
     * @param maritalStatus Indicating marital status.
     * @param specifiedIllnesses Indicating any special illnesses.
     */
    public Person(String surname, 
                  String forename, 
                  String townland, 
                  String DED, 
                  String county, 
                  int age, 
                  String sex, 
                  String birthplace, 
                  String occupation, 
                  String religion, 
                  String literacy, 
                  String irishLanguage, 
                  String relationToHeadOfHousehold, 
                  String maritalStatus, 
                  String specifiedIllnesses) {
        
                    this.surname = surname;
                    this.forename = forename;
                    this.townland = townland;
                    this.DED = DED;
                    this.county = county;
                    this.age = age;
                    this.sex = sex;
                    this.birthplace = birthplace;
                    this.occupation = occupation;
                    this.religion = religion;
                    this.literacy = literacy;
                    this.irishLanguage = irishLanguage;
                    this.relationToHeadOfHousehold = relationToHeadOfHousehold;
                    this.maritalStatus = maritalStatus;
                    this.specifiedIllnesses = specifiedIllnesses;
    }

    /**
     * Constructor method.
     * An empty constructor used by the Database persistor class.
     */
    public Person() {
        
    }
    
    /**
     * Getter method.
     * 
     * @return surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Getter method. 
     * 
     * @return forename.
     */
    public String getForename() {
        return forename;
    }

    /**
     * Getter method.
     * 
     * @return townland.
     */
    public String getTownland() {
        return townland;
    }

    /**
     * Getter method.
     * 
     * @return District Electoral Division.
     */
    public String getDED() {
        return DED;
    }

    /**
     * Getter method.
     * 
     * @return county.
     */
    public String getCounty() {
        return county;
    }

    /**
     * Getter method. 
     * 
     * @return age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter method.
     * 
     * @return sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Getter method.
     * 
     * @return birth place.
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * Getter method.
     * 
     * @return occupation.
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Getter method.
     * 
     * @return religion. 
     */
    public String getReligion() {
        return religion;
    }

    /**
     * Getter method.
     * 
     * @return level of literacy.
     */
    public String getLiteracy() {
        return literacy;
    }

    /**
     * Getter method.
     * 
     * @return Irish language.
     */
    public String getIrishLanguage() {
        return irishLanguage;
    }

    /**
     * Getter method.
     * 
     * @return person's relation to head of household.
     */
    public String getRelationToHeadOfHousehold() {
        return relationToHeadOfHousehold;
    }

    /**
     * Getter method.
     * 
     * @return marital status.
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Getter method.
     * 
     * @return specified illnesses.
     */
    public String getSpecifiedIllnesses() {
        return specifiedIllnesses;
    }

    /**
     * Setter.
     * 
     * @param surname Indicates surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Setter.
     * 
     * @param forename Indicates forename.
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Setter.
     * 
     * @param townland Indicates townland.
     */
    public void setTownland(String townland) {
        this.townland = townland;
    }

    /**
     * Setter.
     * 
     * @param DED Indicates DED.
     */
    public void setDED(String DED) {
        this.DED = DED;
    }

    /**
     * Setter.
     * 
     * @param county Indicates county.
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * Setter.
     * 
     * @param age Indicates age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Setter.
     * 
     * @param sex Indicates sex.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Setter.
     * 
     * @param birthplace Indicates birth place.
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * Setter.
     * 
     * @param occupation Indicates occupation.
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Setter.
     * 
     * @param religion Indicates religion.
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * Setter.
     * 
     * @param literacy Indicates level of literacy.
     */
    public void setLiteracy(String literacy) {
        this.literacy = literacy;
    }

    /**
     * Setter.
     * 
     * @param irishLanguage Indicates if person can speak in Irish.
     */
    public void setIrishLanguage(String irishLanguage) {
        this.irishLanguage = irishLanguage;
    }

    /**
     * Setter.
     * 
     * @param relationToHeadOfHousehold Indicates person's relation to head of household.
     */
    public void setRelationToHeadOfHousehold(String relationToHeadOfHousehold) {
        this.relationToHeadOfHousehold = relationToHeadOfHousehold;
    }

    /**
     * Setter.
     * 
     * @param maritalStatus Indicates marital status.
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Setter.
     * 
     * @param specifiedIllnesses Indicates specified illnesses.
     */
    public void setSpecifiedIllnesses(String specifiedIllnesses) {
        this.specifiedIllnesses = specifiedIllnesses;
    }

    /**
     * Override toString method.
     * 
     * @return a string containing all the information about a particular person.
     */
    @Override
    public String toString() {
        String surnameStr = "Surname: ";
        String forenameStr = "Forename: ";
        String townlandStr = "Townland: ";
        String ded = "DED: ";
        String countyStr = "County: ";
        String ageStr = "Age: ";
        String sexStr = "Sex: ";
        String birthplaceStr = "Birthplace: ";
        String occupationStr = "Occupation: ";
        String religionStr = "Religion: ";
        String literacyStr = "Literacy: ";
        String irishL = "Irish Language: ";
        String rel = "Relation to Head of Houshold: ";
        String marStat = "Marital Status: ";
        String specIll = "Specified Illnesses: ";
    
        String person = surnameStr + this.surname + "\n" +
                        forenameStr + this.forename + "\n" + 
                        townlandStr + this.townland + "\n" + 
                        ded + this.DED + "\n" +
                        countyStr + this.county + "\n" +
                        ageStr + this.age + "\n" + 
                        sexStr + this.sex + "\n" +
                        birthplaceStr + this.birthplace + "\n" +
                        occupationStr + this.occupation + "\n" + 
                        religionStr + this.religion + "\n" + 
                        literacyStr + this.literacy + "\n" + 
                        irishL + this.irishLanguage + "\n" +
                        rel + this.relationToHeadOfHousehold + "\n" +
                        marStat + this.maritalStatus + "\n" +
                        specIll + this.specifiedIllnesses;
        
        return person;
                
    }
    
    
    
}
