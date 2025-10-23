-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: student_asset_sharing
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `item_name` varchar(100) DEFAULT NULL,
  `description` text,
  `category` varchar(50) DEFAULT NULL,
  `availability` enum('AVAILABLE','BORROWED') DEFAULT 'AVAILABLE',
  `image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,2,'book','biology text','Book','AVAILABLE',NULL),(2,2,'book','note','Electronics','AVAILABLE',NULL),(3,2,'book','book','Book','AVAILABLE',NULL),(4,2,'biology','heloo','Book','AVAILABLE',NULL),(5,2,'book','qwerghj','Book','AVAILABLE',NULL),(6,2,'book','hy','Tool','AVAILABLE',NULL),(7,2,'book','dfg','Book','AVAILABLE',NULL),(8,4,'abhi','for sale','Electronics','AVAILABLE',NULL),(9,5,'meow','cat','Book','AVAILABLE',NULL),(10,4,'dfgh','sdfg','Tool','AVAILABLE',NULL),(11,4,'wert','wer','Book','AVAILABLE',NULL),(12,4,'erthj','sdfghj','Book','AVAILABLE',NULL),(13,4,'sdfghj','qwerfgh','Other','AVAILABLE','C:\\Users\\Artemis\\Downloads\\161686.png'),(14,6,'jhniundfghjk','ghjk','Book','AVAILABLE',''),(15,6,'vbnm,','ffghjkl','Book','AVAILABLE','');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requests` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `request_name` varchar(100) NOT NULL,
  `description` text,
  `category` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'OPEN',
  `date_requested` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`request_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (1,4,'lab coat','','Electronics','OPEN','2025-10-21 14:16:34');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `trans_id` int NOT NULL AUTO_INCREMENT,
  `item_id` int DEFAULT NULL,
  `borrower_id` int DEFAULT NULL,
  `owner_id` int DEFAULT NULL,
  `request_date` date DEFAULT NULL,
  `approve_date` date DEFAULT NULL,
  `borrow_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `completed_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`trans_id`),
  KEY `item_id` (`item_id`),
  KEY `fk_transactions_borrower` (`borrower_id`),
  KEY `fk_transactions_owner` (`owner_id`),
  CONSTRAINT `fk_transactions_borrower` FOREIGN KEY (`borrower_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_transactions_owner` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`),
  CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`borrower_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,3,4,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(2,7,4,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(3,4,4,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(4,5,4,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(5,3,4,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(6,8,5,4,NULL,'2025-10-21','2025-10-21','2025-10-21',NULL,'RETURNED'),(7,8,5,4,NULL,NULL,NULL,NULL,NULL,'REJECTED'),(8,9,4,5,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(9,1,4,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(10,5,4,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(11,1,6,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(12,6,6,2,NULL,NULL,NULL,NULL,NULL,'REQUESTED'),(13,15,5,6,NULL,NULL,NULL,NULL,NULL,'REQUESTED');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_no` varchar(15) DEFAULT NULL,
  `dept` varchar(50) DEFAULT NULL,
  `role` enum('ADMIN','STUDENT') DEFAULT 'STUDENT',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Admin User','admin@uni.edu','\\/ZLM4JIdnTBazLuhEnAXdD9iVFAA2Ww7g5JdW3ncAaHxhi','1234567890','IT','ADMIN'),(2,'abhinav nr','abhinavnr08@gmail.com','$2a$10$pIUols821.Ybf0o5TvXItOCKM980J7sWKZdVvGkUY/u174BPLl7DW','9778188809','cse c','STUDENT'),(4,'Abhinav','abhinav9539725465@gmail.com','abhiomega1','9778188809','cse','STUDENT'),(5,'mememe','thelogospot64@gmail.com','abhiomega1','1234567890','me','STUDENT'),(6,'abhi','abhi123@gmail.com','1234','1234567890','cs','STUDENT'),(7,'23','2123','0000','1234567890','cs','STUDENT'),(8,'abhi','abhi1@gmail.com','123','1234567890','cs','STUDENT'),(9,'Arjun ','arj@gmail.com','00','123123','cs','STUDENT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'student_asset_sharing'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-23 22:52:44
