CENSUS DATA MANAGEMENT SOFTWARE
===============================
DEVELOPED BY CSABA FARKAS
DATE: 24/05/2015

INTRODUCTION
============
THIS SOFTWARE ALLOWS USERS TO MANAGE A DATABASE CONTAINING 
DATA PARSED FROM THE 1901 CENSUS DATABASE 
(http://www.census.nationalarchives.ie/).

REQUIREMENTS
============

    1. DATABASE
    -----------
    PLEASE CREATE YOUR OWN INSTANCE OF THE DATABASE
    BY CAREFULLY FOLLOWING THE INSTRUCTIONS OF ONE 
    OF THE METHODS BELOW:

     1. IN THE ROOT LIBRARY OF THIS PROJECT, THERE 
	IS A 'censusdump.sql' FILE. USERS CAN 
	RESTORE THIS DATABASE ON THEIR LOCAL MACHINE 
	USING THE FOLLOWING COMMAND IN THEIR COMMAND 
	LINE (OR TERMINAL):

        mysql -u [username] -p[password] < censusdump.sql

        **PLEASE ENTER YOUR USERNAME AND PASSWORD 
	WITHOUT THE BRACKETS.**

     2. ALTERNATIVELY, YOU CAN CREATE AN INSTANCE OF A 
	NEW (EMPTY) DATABASE USING THE FOLLOWING QUERIES:


        a. CREATE DATABASE AND USE NEW DATABASE:

           create database if not exists census;
           use census;


        b. CREATE TABLE USING THE FOLLOWING QUERY:
           CREATE TABLE `1901_census` (
            `Surname` varchar(100) NOT NULL DEFAULT '',
            `Forename` varchar(100) NOT NULL DEFAULT '',
            `Townland` varchar(100) NOT NULL DEFAULT '',
            `DED` varchar(100) DEFAULT NULL,
            `County` varchar(50) DEFAULT NULL,
            `Age` smallint(5) unsigned NOT NULL,
            `Sex` enum('M','F') DEFAULT NULL,
            `Birthplace` varchar(100) NOT NULL DEFAULT '',
            `Occupation` varchar(100) DEFAULT NULL,
            `Religion` varchar(50) DEFAULT NULL,
            `Literacy` varchar(100) DEFAULT NULL,
            `Irish_Language` varchar(100) DEFAULT NULL,
            `Relation_to_Head_of_Household` varchar(100) DEFAULT NULL,
            `Marital_Status` varchar(50) DEFAULT NULL,
            `Specified_Illness` varchar(255) DEFAULT NULL,
            PRIMARY KEY (`Surname`,`Forename`,`Townland`,`Age`,`Birthplace`)
            ) ENGINE=InnoDB DEFAULT CHARSET=latin1

    AFTER A LOCAL INSTANCE OF THE DATABASE IS CREATED, 
    PLEASE REPLACE THE CODE IN LINE 40-42 IN THE 
    mysql.MySQLController CLASS WITH THE FOLLOWING CODE, 
    ASSIGNING YOUR USERNAME AND PASSWORD TO $USER_NAME 
    AND $PASSWORD VARIABLES, AND COMPILE
    THE PROJECT AGAIN:

    private final String URL = "jdbc:mysql://localhost:3306/census";  
    private final String USER_NAME = "[YOUR USERNAME]";                    
    private final String PASSWORD = "[YOUR PASSWORD]";     

    **IF 3306 PORT NUMBER WOULDN'T WORK, PLEASE CHECK YOUR 
      MYSQL MANUAL TO FIND THE CORRECT PORT NUMBER**

    **IF YOU ARE USING ECLIPSE, YOU MUST COMPILE THE PROJECT 
      BY USING 'javac -classpath <path> COMMAND. MAKE SURE 
      THE CREATED CLASS FILES ARE STORED IN /build/classes/ FOLDER.**

    THE ABOVE METHODS PRESUMES THAT USERS HAVE MYSQL INSTALLED ON THEIR COMPUTER.
    HOWEVER, IF YOU DON'T HAVE MYSQL INSTALLED, THEN PLEASE DOWNLOAD IT FROM
    http://dev.mysql.com/downloads/.

    THE DATABASE CONNECTOR CAN BE FOUND IN THE libs FOLDER.


