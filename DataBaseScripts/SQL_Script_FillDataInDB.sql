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
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `accommodations`
--

LOCK TABLES `accommodations` WRITE;
/*!40000 ALTER TABLE `accommodations` DISABLE KEYS */;
INSERT INTO `accommodations` VALUES (1,'1, Single responsibility Street, Principle District, Sofia','2019-01-06 00:00:00',220,1,3,NULL,23.3211663,42.6930578),(2,'2, Open–closed Street, Principle District, Sofia','2018-11-06 00:00:00',600,1,4,NULL,22.96067741,42.88655335),(3,'3, Liskov substitution Street, Principle District, Sofia','2018-11-07 00:00:00',620,1,5,NULL,23.3253347,42.6893097),(4,'4, Interface segregation Street, Principle District, Sofia','2018-11-08 00:00:00',427,2,6,NULL,23.3201707,42.6935498),(5,'5, Dependency inversion Street, Principle District, Sofia','2018-11-09 00:00:00',543,7,4,NULL,23.3461099,42.6538837);
/*!40000 ALTER TABLE `accommodations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bankaccounts`
--

LOCK TABLES `bankaccounts` WRITE;
/*!40000 ALTER TABLE `bankaccounts` DISABLE KEYS */;
INSERT INTO `bankaccounts` VALUES (1,'ONE1111111',1000.11),(2,'TWO2222222',2000.22),(3,'THREE3333333',3000.33),(4,'FOUR4444444',4000.44),(5,'FIVE5555555',5000.55);
/*!40000 ALTER TABLE `bankaccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (3,'Hello, tenant!','2018-10-22 00:00:00',NULL,'\0',1,3,1,NULL),(4,'Hi, landlord!','2018-10-22 00:00:00',NULL,'\0',3,1,1,NULL),(5,'MarchMessageTest','2018-03-22 00:00:00',NULL,'',1,3,1,NULL);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (1,1,1,3),(2,5,1,4),(3,4,1,5),(4,5,3,1),(8,4,4,1);
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Peter','','Landlord The One','Mighty','0881223344',NULL,'https://firebasestorage.googleapis.com/v0/b/chatbeta-76246.appspot.com/o/Landlord_1.jpg?alt=media&token=c1705df7-08e2-4ad0-b010-58fad5965fcb'),(2,'John','','Landlord The Second','Wise','0882334455',NULL,'https://firebasestorage.googleapis.com/v0/b/chatbeta-76246.appspot.com/o/Landlord_2.jpg?alt=media&token=7146a6db-9a9a-4297-9334-171e7dc06b51'),(3,'Zac','\0','Tenant The One','Boy','0883445566',NULL,'https://firebasestorage.googleapis.com/v0/b/chatbeta-76246.appspot.com/o/Tenant_3.jpg?alt=media&token=72840f31-b712-4f7e-927c-176c0eebde55'),(4,'Christian','\0','Tenant The Two','Dear','0884556677',NULL,'https://firebasestorage.googleapis.com/v0/b/chatbeta-76246.appspot.com/o/Tenant_1.jpg?alt=media&token=0b4759eb-436c-4efa-9e07-a4ccd7f84bb1'),(5,'Ibrahim','\0','Tenant The Three','Deer','0885667788',NULL,'https://firebasestorage.googleapis.com/v0/b/chatbeta-76246.appspot.com/o/Tenant_2.jpg?alt=media&token=8cd6b733-d54e-42d4-bc28-45824ae66728'),(6,'Nathaniel','\0','Tenant The Four','Octavian','0886778899',NULL,'https://firebasestorage.googleapis.com/v0/b/chatbeta-76246.appspot.com/o/Tenant_4.jpg?alt=media&token=67febb10-fead-47f9-9362-82ed346201c1'),(7,'Zigmund','','Landlord the Third','Pedestrian','0887889999',NULL,'https://firebasestorage.googleapis.com/v0/b/chatbeta-76246.appspot.com/o/Landlord_3.jpg?alt=media&token=8d0e9605-b57a-4b1e-9586-ce350e2894f9');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-13 14:25:13
