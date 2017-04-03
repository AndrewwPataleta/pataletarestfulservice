-- MySQL dump 10.13  Distrib 5.6.35, for Linux (x86_64)
--
-- Host: localhost    Database: SparepartsDB
-- ------------------------------------------------------
-- Server version	5.6.35

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
-- Current Database: `SparepartsDB`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `SparepartsDB` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `SparepartsDB`;

--
-- Table structure for table `Requests`
--

DROP TABLE IF EXISTS `Requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Requests` (
  `idRequests` int(11) NOT NULL AUTO_INCREMENT,
  `Requestscol` varchar(45) NOT NULL,
  PRIMARY KEY (`idRequests`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Requests`
--

LOCK TABLES `Requests` WRITE;
/*!40000 ALTER TABLE `Requests` DISABLE KEYS */;
INSERT INTO `Requests` VALUES (1,'кулиса+бмв+36'),(2,'капот+бмв+36'),(3,'капот+бмв+39'),(4,'капот+бмв+39'),(5,'капот+бмв+39'),(6,'капот+бмв+39'),(7,'капот+бмв+39'),(8,'капот+бмв+39'),(9,'капот+бмв+39'),(10,'капот+бмв+39');
/*!40000 ALTER TABLE `Requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sparepart`
--

DROP TABLE IF EXISTS `Sparepart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sparepart` (
  `idSparepart` int(11) NOT NULL AUTO_INCREMENT,
  `vendorCode` varchar(45) DEFAULT 'Неизвестно',
  `nameSparepart` varchar(60) DEFAULT NULL,
  `daysOfDelivery` varchar(10) DEFAULT 'Уточняйте',
  `quantity` varchar(10) DEFAULT 'Уточняйте',
  `producer` varchar(60) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `characteristics` varchar(300) DEFAULT 'Нет описания',
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSparepart`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sparepart`
--

LOCK TABLES `Sparepart` WRITE;
/*!40000 ALTER TABLE `Sparepart` DISABLE KEYS */;
INSERT INTO `Sparepart` VALUES (3,'2357080','Диск сцепления',NULL,NULL,'KIA',0,'1999; 2л; Бензин; Инжектор; Джип (5-дверный); зелёный; + корзина;','+375447029600'),(4,'3097891','Диск сцепления',NULL,NULL,'Fiat',0,'1996; 1.8л; Бензин; Инжектор; Хэтчбэк 5 дв.; серебристый;','+375447954674'),(5,'1820705','Диск сцепления',NULL,NULL,'Ford',0,'2004; 2л; Дизель; TDCI; Универсал; серебристый;','+375447354679'),(6,'1728871','Диск сцепления',NULL,NULL,'Ford',0,'1989; 1.8л; Бензин; Инжектор;','+375447354679'),(7,'1600631','Цилиндр сцепления главный',NULL,NULL,'Land Rover',0,'1995; 3.9л; Бензин; Инжектор; Джип (5-дверный); тёмно-зелёный;','+375296154671'),(8,'2858038','Диск сцепления',NULL,NULL,'Honda',0,'1999; 1.6л; Бензин; Инжектор; Джип (5-дверный); синий; +корзина;','+375447354672'),(9,'2357080','Диск сцепления',NULL,NULL,'KIA',0,'1999; 2л; Бензин; Инжектор; Джип (5-дверный); зелёный; + корзина;','+375447029600'),(10,'3097891','Диск сцепления',NULL,NULL,'Fiat',0,'1996; 1.8л; Бензин; Инжектор; Хэтчбэк 5 дв.; серебристый;','+375447954674'),(11,'1820705','Диск сцепления',NULL,NULL,'Ford',0,'2004; 2л; Дизель; TDCI; Универсал; серебристый;','+375447354679'),(12,'1728871','Диск сцепления',NULL,NULL,'Ford',0,'1989; 1.8л; Бензин; Инжектор;','+375447354679'),(13,'1600631','Цилиндр сцепления главный',NULL,NULL,'Land Rover',0,'1995; 3.9л; Бензин; Инжектор; Джип (5-дверный); тёмно-зелёный;','+375296154671'),(14,'2858038','Диск сцепления',NULL,NULL,'Honda',0,'1999; 1.6л; Бензин; Инжектор; Джип (5-дверный); синий; +корзина;','+375447354672');
/*!40000 ALTER TABLE `Sparepart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserRequests`
--

DROP TABLE IF EXISTS `UserRequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRequests` (
  `idUserRequests` int(11) NOT NULL AUTO_INCREMENT,
  `loginIdUser` varchar(45) NOT NULL,
  `idBusketRequests` int(11) NOT NULL,
  PRIMARY KEY (`idUserRequests`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRequests`
--

LOCK TABLES `UserRequests` WRITE;
/*!40000 ALTER TABLE `UserRequests` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserRequests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basketRequests`
--

DROP TABLE IF EXISTS `basketRequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basketRequests` (
  `idbasketRequests` int(11) NOT NULL,
  `idRelateBasket` int(11) NOT NULL,
  `idSparepart` int(11) NOT NULL,
  `dateRequest` date NOT NULL,
  PRIMARY KEY (`idbasketRequests`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basketRequests`
--

LOCK TABLES `basketRequests` WRITE;
/*!40000 ALTER TABLE `basketRequests` DISABLE KEYS */;
/*!40000 ALTER TABLE `basketRequests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-03 17:55:31
