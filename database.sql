-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: database
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `username` varchar(30) NOT NULL,
  `hashed_password` char(128) NOT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address` varchar(30) NOT NULL,
  `phone_number` varchar(30) NOT NULL,
  `province` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `billing_address` varchar(30) NOT NULL,
  `postal_code` varchar(30) NOT NULL,
  `permissions` enum('Customer','Admin') NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('bob123','4f084eddf2adb77560ae303be62e31f2d1f6c50d65d1b649e90eeb3a4062281825e4eebc972c265aefac50eb69822ce8b19978a55e2eebc1f14f0a0d5d1484fd','w#T1_-(YP\00=c)pxf* &^4\'qM','bob','bobby','bob@gmail.com','3 street street','123-123-123','ontario','canada','3 street street','lo9b78','Customer'),('OtherBob123','cc86690d8409890436ee2401be4ef820f14e330d9556b4face9ad4c3bbe6ea88b325be11ef71995ba3d8c9b7a91d963d97b5310dc65accf3d9efda965264f56e','j\r{b*E:wq)w&I\06V3=c(\"O\"','bob','bobby','bob@gmail.com','3 street street','123-123-123','ontario','canada','3 street street','lo9b78','Customer'),('OtherOtherBob123','531b60bf6c6de71275418f5275d600f169aff5b13118c92cdaf14b96390fb67642748bb84be68f5c8b00543c479a2047425cbcdadfe3d9ee55e0d7c302cf2033',',nI(hPaff#\">^P?g\\iBZK*','bob','bobby','bob@gmail.com','3 street street','123-123-123','ontario','canada','3 street street','lo9b78','Customer'),('sam123','49177d9a389f5e275b8f5370c8e187ba352021150e7af3224949ef72132206fc9a57e525f8a5e852a4a28dae3b332b870b352be0399f08b890e109754ff64ada','|FF*Cu2_\r1b`^SXCT|bXQG.kS','sam','sammul','a@gmail.com','4 street street','123-123-123','Ontario','Canada','4 street street','V4M46','Customer'),('test123','6bdc42ecebdfb03d822509b27c3153b30c40068675517147db51165030254fe5db6c13ea3591025daace0fba1a5633108f465b35f3f5dac573c4dc594b1b980c','@QqY&$Y^7f{j@Qqd)3GZAs\"_{D\0','testificate','tessssssss','a@gmail.com','3 street street','123-123-123','ontario','canada','3 street street','lo9b78','Customer');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `current_cart`
--

DROP TABLE IF EXISTS `current_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `current_cart` (
  `costomer_username` varchar(10) NOT NULL,
  `item_id` varchar(10) NOT NULL,
  `Quantity` int NOT NULL,
  KEY `costomer_username` (`costomer_username`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `current_cart_ibfk_1` FOREIGN KEY (`costomer_username`) REFERENCES `accounts` (`username`),
  CONSTRAINT `current_cart_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_iD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `current_cart`
--

LOCK TABLES `current_cart` WRITE;
/*!40000 ALTER TABLE `current_cart` DISABLE KEYS */;
INSERT INTO `current_cart` VALUES ('test123','456',5),('test123','567',2),('test123','678',8);
/*!40000 ALTER TABLE `current_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `item_iD` varchar(10) NOT NULL,
  `item_name` varchar(30) NOT NULL,
  `category` varchar(30) NOT NULL,
  `description` varchar(100) NOT NULL,
  `ammount_in_stock` int NOT NULL,
  `price` double NOT NULL,
  `futureAvailability` date DEFAULT NULL,
  `image_path` varchar(50) NOT NULL,
  `Brand` varchar(30) NOT NULL,
  PRIMARY KEY (`item_iD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('456','Apple','Fruit','A red thing you eat',1000,2,NULL,'/asset/apple.jpg','FruitInc'),('563','Moscow Mule','Alchol','made form vodka, ginger, lime and a bit of mint',400,2.75,NULL,'/asset/moscowMule.jpeg','RussiaInc'),('567','Orange','Fruit','A food named after a colour',1000,3,NULL,'/asset/orange.jpg','FruitInc'),('678','Beer','Alchol','A drink to make you stupider',1000,2.75,NULL,'/asset/beer.jpg','Corona'),('890','Bread','Weat','A thing for sandwiches',1000,5,NULL,'/asset/bread.jpg','WheatInc');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_content`
--

DROP TABLE IF EXISTS `order_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_content` (
  `order_id` int NOT NULL,
  `item_iD` varchar(10) NOT NULL,
  `Quantity` int NOT NULL,
  KEY `order_id` (`order_id`),
  KEY `item_iD` (`item_iD`),
  CONSTRAINT `order_content_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_content_ibfk_2` FOREIGN KEY (`item_iD`) REFERENCES `items` (`item_iD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_content`
--

LOCK TABLES `order_content` WRITE;
/*!40000 ALTER TABLE `order_content` DISABLE KEYS */;
INSERT INTO `order_content` VALUES (1,'456',5),(1,'567',2),(1,'678',8),(2,'563',99),(2,'456',5),(2,'890',999),(3,'567',1);
/*!40000 ALTER TABLE `order_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_user_name` varchar(10) NOT NULL,
  `order_date` date NOT NULL,
  `order_time` time NOT NULL,
  `is_fulfilled` tinyint(1) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_user_name` (`customer_user_name`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_user_name`) REFERENCES `accounts` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'test123','2023-08-26','15:09:21',0),(2,'sam123','2023-08-26','15:10:42',0),(3,'sam123','2023-08-26','15:12:17',0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-26 17:43:31
