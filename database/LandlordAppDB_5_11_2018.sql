CREATE DATABASE  IF NOT EXISTS `landlordappdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `landlordappdb`;
-- MySQL dump 10.16  Distrib 10.3.9-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: landlordappdb
-- ------------------------------------------------------
-- Server version	10.3.9-MariaDB

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
-- Table structure for table `accommodations`
--

DROP TABLE IF EXISTS `accommodations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accommodations` (
  `accommodationid` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `duedate` datetime NOT NULL,
  `price` double NOT NULL,
  `landlord` int(11) DEFAULT NULL,
  `tenant` int(11) DEFAULT NULL,
  `duelastsentdate` datetime DEFAULT NULL,
  `longitude` double DEFAULT 23,
  `latitude` double DEFAULT 23,
  PRIMARY KEY (`accommodationid`),
  KEY `fk_accommodation_landlord_users` (`landlord`),
  KEY `fk_accommodation_tenant_users` (`tenant`),
  CONSTRAINT `fk_accommodation_landlord_users` FOREIGN KEY (`landlord`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_accommodation_tenant_users` FOREIGN KEY (`tenant`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accommodations`
--

LOCK TABLES `accommodations` WRITE;
/*!40000 ALTER TABLE `accommodations` DISABLE KEYS */;
INSERT INTO `accommodations` VALUES (1,'Demo address One','2018-12-06 00:00:00',580,1,3,'2018-10-01 00:00:00',23,23),(2,'Demo address Two','2018-11-06 00:00:00',600,2,4,'2018-10-01 00:00:00',22,22),(3,'Demo address Three','2018-11-07 00:00:00',620,2,5,'2018-10-02 00:00:00',21,21);
/*!40000 ALTER TABLE `accommodations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankaccounts`
--

DROP TABLE IF EXISTS `bankaccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bankaccounts` (
  `bankaccountid` int(11) NOT NULL AUTO_INCREMENT,
  `accountnumber` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`bankaccountid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankaccounts`
--

LOCK TABLES `bankaccounts` WRITE;
/*!40000 ALTER TABLE `bankaccounts` DISABLE KEYS */;
INSERT INTO `bankaccounts` VALUES (1,'ONE1111111',1000.11),(2,'TWO2222222',2000.22),(3,'THREE3333333',3000.33),(4,'FOUR4444444',4000.44),(5,'FIVE5555555',5000.55);
/*!40000 ALTER TABLE `bankaccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `messageid` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `timesent` datetime NOT NULL,
  `image` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0',
  `sender` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `contextaccommodation` int(11) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageid`),
  KEY `fk_messages_users_sender` (`sender`),
  KEY `fk_messages_users_receiver` (`receiver`),
  KEY `fk_messages_accommodations` (`contextaccommodation`),
  CONSTRAINT `fk_messages_accommodations` FOREIGN KEY (`contextaccommodation`) REFERENCES `accommodations` (`accommodationid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_messages_users_receiver` FOREIGN KEY (`receiver`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_messages_users_sender` FOREIGN KEY (`sender`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (3,'Hello, tenant!','2018-10-22 00:00:00',NULL,'\0',1,3,1,NULL),(4,'Hi, landlord!','2018-10-22 00:00:00',NULL,'\0',3,1,1,NULL);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratings` (
  `ratingid` int(11) NOT NULL AUTO_INCREMENT,
  `rating` double DEFAULT NULL,
  `rateduser` int(11) DEFAULT NULL,
  `sourceuser` int(11) DEFAULT NULL,
  PRIMARY KEY (`ratingid`),
  KEY `fk_ratings_users` (`rateduser`),
  KEY `fk_ratings_users_source` (`sourceuser`),
  CONSTRAINT `fk_ratings_users` FOREIGN KEY (`rateduser`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ratings_users_source` FOREIGN KEY (`sourceuser`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (1,5,1,3),(2,5,1,4),(3,4,1,5),(4,4,3,1);
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `transactionid` int(11) NOT NULL AUTO_INCREMENT,
  `dateoftransaction` datetime NOT NULL,
  `transactionamount` double NOT NULL,
  `landlord_bank_account` int(11) NOT NULL,
  `tenant_bank_account` int(11) NOT NULL,
  `accommodation` int(11) NOT NULL,
  PRIMARY KEY (`transactionid`),
  KEY `fk_transactions_tenant_bankaccounts` (`tenant_bank_account`),
  KEY `fk_transactions_lanlord_bankaccounts` (`landlord_bank_account`),
  KEY `fk_transactions_accommodations` (`accommodation`),
  CONSTRAINT `fk_transactions_accommodations` FOREIGN KEY (`accommodation`) REFERENCES `accommodations` (`accommodationid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_lanlord_bankaccounts` FOREIGN KEY (`landlord_bank_account`) REFERENCES `bankaccounts` (`bankaccountid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_tenant_bankaccounts` FOREIGN KEY (`tenant_bank_account`) REFERENCES `bankaccounts` (`bankaccountid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `islandlord` bit(1) DEFAULT NULL,
  `lastname` varchar(255) NOT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(20) NOT NULL,
  `bank_account` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `fk_users_bankaccounts` (`bank_account`),
  CONSTRAINT `fk_users_bankaccounts` FOREIGN KEY (`bank_account`) REFERENCES `bankaccounts` (`bankaccountid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Landlord','','One','Landlordov','0881223344',NULL,'https://upload.wikimedia.org/wikipedia/en/c/c3/OnePunchMan_manga_cover.png'),(2,'Landlord','','Two','Landlordov','0882334455',NULL,'https://cdn141.picsart.com/238024093041202.jpg?c256x256'),(3,'Tenant','\0','One','Tenant','0883445566',NULL,'https://pa1.narvii.com/6346/4e3d1c497f74f6a4b0126678412eda26835bab09_hq.gif'),(4,'Tenant','\0','Two','Tenant','0884556677',NULL,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpgr61M1YVHKZnjW7pvziMhLA0HtI3yswEnQDs9sIyLqMbw9QG'),(5,'Tenant','\0','Three','Tenant','0885667788',NULL,'https://pa1.narvii.com/5972/69811b91c35efcc5feb32b2b25819e0bbc2c6c9d_128.gif'),(6,'Tenant','\0','Four','Tenant','0885667790',NULL,'https://pm1.narvii.com/6390/6abc12d28fd2e7450cd5eedae73363c3c98eccbf_128.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-05 15:10:51
