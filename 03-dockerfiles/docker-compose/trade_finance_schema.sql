-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: trade_finance
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `is_approved` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `request_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_luwrm9ufmuarkl1wb65k58skg` (`request_id`),
  CONSTRAINT `FKg0vqy3mb2rxt5l1wfijifg03v` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,'some-account-number-01',NULL,_binary '','bank01','BANK_APPROVED',1),(2,'accountNumber03',NULL,_binary '\0','bank03','REQUEST_INITIALIZED',2),(3,'accountNumber03',NULL,_binary '\0','bank03','REQUEST_INITIALIZED',3),(4,'some-account-number-test-01',NULL,_binary '','bank01','BANK_APPROVED',4);
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exporter`
--

DROP TABLE IF EXISTS `exporter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exporter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `estimation_date` datetime(6) DEFAULT NULL,
  `is_approved` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `price_withvat` decimal(38,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `request_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e3of86ev9l2p3i2k7o0ymln4k` (`request_id`),
  CONSTRAINT `FKc5ahnef24ecuphl64i2wyhqyu` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exporter`
--

LOCK TABLES `exporter` WRITE;
/*!40000 ALTER TABLE `exporter` DISABLE KEYS */;
INSERT INTO `exporter` VALUES (1,NULL,'2024-05-06 00:00:00.000000',_binary '','exporter01',332.00,398.40,'EXPORTER_APPROVED',1),(2,NULL,NULL,_binary '\0','exporter03',NULL,NULL,NULL,2),(3,NULL,NULL,_binary '\0','exporter03',NULL,NULL,NULL,3),(4,NULL,'2024-04-29 00:00:00.000000',_binary '','exporter01',300.00,360.00,'EXPORTER_APPROVED',4);
/*!40000 ALTER TABLE `exporter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `goods` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `request_status` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,121,'2024-04-26 13:54:50.689487','delivery-address-1','test@mail.com','iron','2231241241','APPROVED','2024-04-26 13:57:35.206826'),(2,22222,'2024-04-26 13:55:21.668153','test-address-2','test2@mail.com','steel','21392831','PENDING_BANK_APPROVAL','2024-04-26 13:55:21.668153'),(3,444,'2024-04-26 13:55:44.107625','test-address-3','test3@mail.com','iron','2147271','PENDING_BANK_APPROVAL','2024-04-26 13:55:44.108639'),(4,2,'2024-04-26 13:56:43.895370','test-address-5','test@mail.com','gold','21437268174','APPROVED','2024-04-26 13:59:28.036664');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_history`
--

DROP TABLE IF EXISTS `transaction_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `status_date` datetime(6) DEFAULT NULL,
  `request_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6rmuoeuqgjmawrba40bjfh9ee` (`request_id`),
  CONSTRAINT `FK6rmuoeuqgjmawrba40bjfh9ee` FOREIGN KEY (`request_id`) REFERENCES `request` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_history`
--

LOCK TABLES `transaction_history` WRITE;
/*!40000 ALTER TABLE `transaction_history` DISABLE KEYS */;
INSERT INTO `transaction_history` VALUES (1,'REQUEST_INITIALIZED','2024-04-26 13:54:50.730485',1),(2,'REQUEST_INITIALIZED','2024-04-26 13:55:21.679137',2),(3,'REQUEST_INITIALIZED','2024-04-26 13:55:44.119716',3),(4,'REQUEST_INITIALIZED','2024-04-26 13:56:43.907410',4),(5,'BANK_APPROVED','2024-04-26 13:57:15.440868',1),(6,'EXPORTER_APPROVED','2024-04-26 13:57:35.203829',1),(7,'BANK_APPROVED','2024-04-26 13:59:05.365153',4),(8,'EXPORTER_APPROVED','2024-04-26 13:59:28.034664',4);
/*!40000 ALTER TABLE `transaction_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-26 17:27:03
