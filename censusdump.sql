-- MySQL dump 10.13  Distrib 5.6.21, for Win64 (x86_64)
--
-- Host: localhost    Database: census
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `1901_census`
--

DROP TABLE IF EXISTS `1901_census`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `1901_census`
--

LOCK TABLES `1901_census` WRITE;
/*!40000 ALTER TABLE `1901_census` DISABLE KEYS */;
INSERT INTO `1901_census` VALUES ('a','a','a','','',3,NULL,'ab','','','','','','',''),('Calnan','Elizabeth','Kilbeg South','Kilbrogan','Cork',11,'F','Cork','Scholar','Roman Catholic','Can read and write',NULL,'Niece','Not married',NULL),('Calnan','Kate','Kilbeg South','Kilbrogan','Cork',50,'F','Cork',NULL,'Roman Catholic','Cannot read','Irish and english','Sister','Widow',NULL),('Calnan','Lawrence','Knocknanuss','Kilmoylerane','Cork',11,'M','Co Cork','Scholar','Roman Catholic','Read and write',NULL,'Son','Not married',NULL),('Calnan','Richard','Pleasant Street','Fitzwilliam','Dublin',29,'M','Co Cork','Medical Student','Roman Catholic','Read and write',NULL,'Boarder','Not married',NULL),('Calnan','Thomas','Dunowen','Ardfield','Cork',58,'M','Co Cork','Farmer','R Catholic','Read and write','Irish and english','Head of family','Married',NULL),('Kennedy','Agnes','Ballygowan','Ballygowan','Cork',5,'F','Co Down','','Unitarian','Cannot read','English','Daughter','Not married',''),('Kennedy','Anne','Cork Street','Merchant\'s Quay','Dublin',10,'F','Dublin city','Scholar','Roman Catholic','Read and write',NULL,'Daughter','Not married',NULL),('Kennedy','James','Ballygowan','Ballygowan','Down',23,'M','Dublin city','MAgl Labourer','Unitarian','Read and write','English','Head of Family','Married',NULL),('Kennedy','John','Cork Street','Merchant\'s Quay','Dublin',19,'M','Dublin city',NULL,'Roman Catholic','Read and write',NULL,'Son','Not married',NULL),('Kennedy','John','Cork Street','Merchant\'s Quay','Dublin',58,'M','Dublin city','Retired Van Driver','Roman Catholic','Read and write',NULL,'Head of Family','Married',NULL),('Kennedy','Kate','Cork Street','Merchant\'s Quay','Dublin',55,'F','Dublin city',NULL,'Roman Catholic','Read and write',NULL,'Wife','Married',NULL),('Kennedy','Walter','Cork Street','Merchant\'s Quay','Dublin',14,'M','Dublin city','Messenger','Roman Catholic','Read and write',NULL,'Son','Not married',NULL),('Kennedy','William','Cork','Merchant\'s Quay','Dublin',17,'M','Dublin city','Boot maker','Roman Catholic','Read and write','','Son','Not married',''),('O\'Brien','John','Briscoe Place','Fermoy Urban','Cork',25,'M','Cork','Gardener','Roman Catholic','Read and write','','Son','Not married',''),('O\'Brien','John','Carhoo','Killeagh','Cork',26,'M','Co Cork','Farmer\'\'s son','Roman Catholic','Read and write',NULL,'Son','Not married',NULL),('O\'Brien','John','Coronea','Skibberren Urban','Cork',22,'M','Co Kerry','Boot and shoe','Roman Catholic','Read and write','Irish','Boarder','Not married',NULL),('O\'Brien','John','Curragh','Skibbereen Rural','Cork',23,'M','Co Cork','Farmer\'s son','Roman Catholic','Read and write',NULL,'Son','Not married',NULL),('O\'Brien','John','Garragort','Liscarrol','Cork',28,'M','Co Cork','Farmer\'\'s son','Roman Catholic','Read and write',NULL,'Son','Not married',NULL),('O\'Brien','John','Island','Newmarket','Cork',26,'M','Co Cork','Clerk (Commercial)','Roman Catholic','Read and write',NULL,'Son','Not married',NULL),('O\'Brien','John','Kilbrittain','Kilbrittain','Cork',26,'M','Co Cork','Farmer\'\'s son','Roman Catholic','Read and write',NULL,'Son','Not married',NULL),('O\'Brien','John','Rathnacarton','Clenor','Cork',21,'M','Co Cork','Farmer\'\'s son','Roman Catholic','Read and write','English','Son','Not married',NULL),('O\'Brien','John','Ring','Kilmacdonagh','Cork',22,'M','Co Cork','Fisherman','Roman Catholic','Read and write','Irish','Son','Not married',''),('O\'Brien','John','Roman Street','North West Ward','Cork',21,'M','Co Cork Blarney','Laundryman','Roman Catholic','Read and write',NULL,'Head of family','Not married',NULL),('O\'Brien','Richard','Saint Finbarr\'s Road','Cork Urban No. 5','Cork',20,'M','Cork City','General Labourer','Catholic','Read and write','English','Son','Not Married',''),('OBrien','Richard','Ballinling West','Ballyfeard','Cork',23,'M','County Cork','Rural Letter Carrier','Roman Catholic','Read and write',NULL,'Son','Not Married',NULL),('OBrien','Richard','Ballylongane','Ballycotton','Cork',20,'M','Co Cork','Agricultural Labourers','Roman Catholic','Read and write',NULL,'Son','Not Married',NULL),('OBrien','Richard','Bluepool Upper','Kanturk','Cork',24,'M','Co Cork','Tinsmith','Roman Catholic','Cannot read',NULL,'Boarder','Married',NULL),('OBrien','Richard','Bullens Alley','Cork Urban No. 4','Cork',24,'M','Cork City','General Labourer','Roman Catholic','Read and write',NULL,'Son','Not Married',NULL),('OBrien','Richard','Liberty Street','Cork Urban No. 7','Cork',18,'M','Cork City','Baker','Roman Catholic','Read write',NULL,'Son','Not Married',NULL),('OBrien','Richard','Sallypark','Liscarroll','Cork',26,'M','Cork','Farmer Son','Roman Catholic','Read and write','','Son','Not Married',''),('Wiseman','William','Ballincollig','','Cork',50,'M','City of Cork','Carpenter Unemployed','Roman Catholic','Yes Can read and write','','Head of Family','Married','');
/*!40000 ALTER TABLE `1901_census` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dummy`
--

DROP TABLE IF EXISTS `dummy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dummy` (
  `col1` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dummy`
--

LOCK TABLES `dummy` WRITE;
/*!40000 ALTER TABLE `dummy` DISABLE KEYS */;
INSERT INTO `dummy` VALUES ('Csaba');
/*!40000 ALTER TABLE `dummy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `name` varchar(25) NOT NULL DEFAULT '',
  `age` int(11) DEFAULT NULL,
  `address` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-24 19:09:48
