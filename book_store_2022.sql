-- MySQL dump 10.13  Distrib 5.7.31, for Win64 (x86_64)
--
-- Host: localhost    Database: book_store_2022
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `isbn` varchar(32) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `author` varchar(32) DEFAULT NULL,
  `publisher` varchar(32) DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `summary` text,
  `all_num` int(11) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `last_num` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('123456789',1,'test','2','1','2022-03-18 00:00:00',2.00,'s28780375.jpg','1',20,'',0,1),('9782511360809',1,'流浪地球','刘慈欣','中国华侨出版社','2016-06-23 16:17:01',38.00,'s28780375.jpg','在太阳灭亡的瞬间，一切都平息了，每个人怀揣着恐惧和希望踏上漫长的流浪之旅……',4,'流浪;科幻',0,1),('9787511360809',1,'流浪地球','刘慈欣','中国华侨出版社','2016-06-23 16:17:01',38.00,'s28780375.jpg','在太阳灭亡的瞬间，一切都平息了，每个人怀揣着恐惧和希望踏上漫长的流浪之旅……',5,'流浪;科幻',6,1),('97875440878',1,'解忧杂货店','[日] 东野圭吾','南海出版公司','2014-05-01 10:40:31',39.50,'s2726411.jpg','现代人内心流失的东西，这家杂货店能帮你找回——',2,'治愈',0,1),('9787544087888',1,'解忧杂货店','[日] 东野圭吾','南海出版公司','2014-05-01 10:40:31',39.50,'s2726411.jpg','现代人内心流失的东西，这家杂货店能帮你找回——',2,'治愈',2,1),('978754420878',1,'解忧杂货店','[日] 东野圭吾','南海出版公司','2014-05-01 10:40:31',39.50,'s2726411.jpg','现代人内心流失的东西，这家杂货店能帮你找回——',5,'治愈',2,1),('9787544270878',1,'解忧杂货店','[日] 东野圭吾','南海出版公司','2014-05-01 10:40:31',39.50,'s2726411.jpg','现代人内心流失的东西，这家杂货店能帮你找回——',5,'治愈',1,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow_book`
--

DROP TABLE IF EXISTS `borrow_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow_book` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `isbn` varchar(32) DEFAULT NULL,
  `reader_id` int(11) DEFAULT NULL,
  `borrow_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `fine` decimal(10,0) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow_book`
--

LOCK TABLES `borrow_book` WRITE;
/*!40000 ALTER TABLE `borrow_book` DISABLE KEYS */;
INSERT INTO `borrow_book` VALUES (00000000001,'9787511360809',1,'2019-04-18 13:57:01','2019-06-17 13:57:01',0,0),(00000000002,'9787511360809',1,'2019-04-18 13:59:52','2019-06-17 13:59:52',0,0),(00000000003,'9787511360809',1,'2019-04-18 14:00:18','2019-06-17 14:00:18',0,0),(00000000004,'9787511360809',1,'2019-04-18 14:00:27','2019-06-17 14:00:27',0,0),(00000000005,'9787544270878',1,'2019-06-23 16:05:34','2019-08-22 16:05:34',0,0),(00000000006,'9782511360809',1,'2022-03-12 19:04:58','2022-05-11 19:04:58',0,0),(00000000007,'9782511360809',1,'2022-03-12 19:07:20','2022-05-11 19:07:20',0,0),(00000000008,'9782511360809',1,'2022-03-12 19:08:49','2022-05-11 19:08:49',0,0),(00000000009,'9782511360809',1,'2022-03-12 19:45:19','2022-05-11 19:45:19',0,1),(00000000010,'9787511360809',4,'2022-03-12 21:53:25','2022-05-11 21:53:25',0,0),(00000000011,'9787511360809',4,'2022-03-12 22:01:54','2022-05-11 22:01:54',0,0),(00000000014,'9787544087888',4,'2022-03-13 21:11:46','2022-05-12 21:11:46',0,0),(00000000015,'9782511360809',4,'2022-03-15 21:37:34','2022-05-14 21:37:34',0,0),(00000000016,'9787511360809',4,'2022-03-15 21:38:20','2022-05-14 21:38:20',0,0),(00000000017,'97875440878',4,'2022-03-15 21:38:24','2022-05-14 21:38:24',0,0),(00000000018,'9787544270878',1,'2022-03-15 22:33:10','2022-05-14 22:33:10',0,1),(00000000019,'97875440878',4,'2022-03-15 22:33:59','2022-05-14 22:33:59',0,0),(00000000020,'97875440878',1,'2022-03-16 22:18:06','2022-05-15 22:18:06',0,1);
/*!40000 ALTER TABLE `borrow_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `category_name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'文学小说');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reader` (
  `id` int(11) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `regdate` datetime DEFAULT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (1,'老六',31,1,'','2019-04-03 20:05:07',1),(4,'老气',0,0,'15026553311','2022-03-12 21:40:31',1);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader_level`
--

DROP TABLE IF EXISTS `reader_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reader_level` (
  `id` int(11) NOT NULL,
  `max_br_num` int(11) NOT NULL,
  `max_br_day` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader_level`
--

LOCK TABLES `reader_level` WRITE;
/*!40000 ALTER TABLE `reader_level` DISABLE KEYS */;
INSERT INTO `reader_level` VALUES (1,10,100);
/*!40000 ALTER TABLE `reader_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `type` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0000000001,'user','123456',1,1),(0000000004,'admin','123456',0,1),(0000000005,'test','123456',1,1),(0000000006,'ddd','123456',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'book_store_2022'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-17 23:14:22
