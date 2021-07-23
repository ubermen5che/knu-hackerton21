CREATE DATABASE  IF NOT EXISTS `knu_guard` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `knu_guard`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 3.36.121.93    Database: knu_guard
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.04.1

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
-- Table structure for table `crawl_data`
--

DROP TABLE IF EXISTS `crawl_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crawl_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `univ_name` varchar(20) DEFAULT NULL,
  `college` varchar(30) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `exposure_sdate` datetime(6) DEFAULT NULL,
  `exposure_edate` datetime(6) DEFAULT NULL,
  `disinfection` varchar(20) DEFAULT NULL,
  `latitude` decimal(16,14) NOT NULL,
  `longitude` decimal(17,14) NOT NULL,
  `crawled_date` datetime(6) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`latitude`,`longitude`),
  KEY `crawl_data_ibfk_1` (`univ_name`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crawl_data`
--

LOCK TABLES `crawl_data` WRITE;
/*!40000 ALTER TABLE `crawl_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `crawl_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `id` varchar(45) NOT NULL,
  `pw` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('knu@knu.ac.kr','knu1308@');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `m_id` varchar(50) NOT NULL,
  `univ_name` varchar(20) NOT NULL,
  `pw` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `m_latitude` decimal(10,7) NOT NULL,
  `m_longitude` decimal(11,7) NOT NULL,
  PRIMARY KEY (`m_id`),
  KEY `univ_name` (`univ_name`),
  CONSTRAINT `member_ibfk_1` FOREIGN KEY (`univ_name`) REFERENCES `univ_info` (`u_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('serepa03@gmail.com','경북대학교','12345','spring',123.1230000,58.1231230);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `univ_data`
--

DROP TABLE IF EXISTS `univ_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `univ_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subject` varchar(100) DEFAULT NULL,
  `univ_name` varchar(20) DEFAULT NULL,
  `college` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `disinfection` varchar(20) DEFAULT NULL,
  `lat` decimal(16,14) NOT NULL,
  `lng` decimal(17,14) NOT NULL,
  `crawled_date` datetime(6) DEFAULT NULL,
  `cont` varchar(200) DEFAULT NULL,
  `confirmed_date` datetime(6) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `univ_data`
--

LOCK TABLES `univ_data` WRITE;
/*!40000 ALTER TABLE `univ_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `univ_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `univ_info`
--

DROP TABLE IF EXISTS `univ_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `univ_info` (
  `u_name` varchar(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `latitude` decimal(10,7) NOT NULL,
  `longitude` decimal(11,7) NOT NULL,
  `url` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`u_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `univ_info`
--

LOCK TABLES `univ_info` WRITE;
/*!40000 ALTER TABLE `univ_info` DISABLE KEYS */;
INSERT INTO `univ_info` VALUES ('경북대학교','대구광역시 북구 대학로 80 경북대학교',35.8888360,128.5324744,'http://knu.ac.kr/wbbs/wbbs/bbs/btin/list.action?bbs_cde=34&menu_idx=224');
/*!40000 ALTER TABLE `univ_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'knu_guard'
--

--
-- Dumping routines for database 'knu_guard'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-23 12:48:44
