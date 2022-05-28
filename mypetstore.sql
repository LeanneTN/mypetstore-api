/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 5.7.19 : Database - mypetstore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mypetstore` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mypetstore`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `userid` varchar(80) NOT NULL,
  `password` varchar(80) NOT NULL,
  `email` varchar(80) DEFAULT NULL,
  `firstname` varchar(80) DEFAULT NULL,
  `lastname` varchar(80) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `addr1` varchar(80) DEFAULT NULL,
  `addr2` varchar(40) DEFAULT NULL,
  `city` varchar(80) DEFAULT NULL,
  `state` varchar(80) DEFAULT NULL,
  `zip` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `phone` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`userid`,`password`,`email`,`firstname`,`lastname`,`status`,`addr1`,`addr2`,`city`,`state`,`zip`,`country`,`phone`) values 
('1','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('10','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('11','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('12','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('123','e10adc3949ba59abbe56e057f20f883e','5@qq.com','HS','Hello',NULL,'World','','','','','China','123'),
('1234','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('12345','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('123456','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('22','e10adc3949ba59abbe56e057f20f883e','678@qq.com','KK','Jack',NULL,'UAS','','CS','','65554','China','123456'),
('23','e10adc3949ba59abbe56e057f20f883e','123456@.com','Hello','Kei',NULL,'长沙','中南大学','长沙','湖南','123456','USA','46546'),
('33','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('5','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('6','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('7','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('8','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('9','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('AA','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('aaaaa','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('j2ee','e10adc3949ba59abbe56e057f20f883e','yourname@yourdomain.com','AAA','XYX',NULL,'901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','13213761071');

/*Table structure for table `bannerdata` */

DROP TABLE IF EXISTS `bannerdata`;

CREATE TABLE `bannerdata` (
  `favcategory` varchar(80) NOT NULL,
  `bannername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`favcategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bannerdata` */

insert  into `bannerdata`(`favcategory`,`bannername`) values 
('BIRDS','./images/banner_birds.gif'),
('CATS','./images/banner_cats.gif'),
('DOGS','./images/banner_dogs.gif'),
('FISH','./images/banner_fish.gif'),
('REPTILES','./images/banner_reptiles.gif');

/*Table structure for table `cartitem` */

DROP TABLE IF EXISTS `cartitem`;

CREATE TABLE `cartitem` (
  `itemid` varchar(10) NOT NULL,
  `productid` varchar(10) NOT NULL,
  `descn` varchar(255) DEFAULT NULL,
  `instock` tinyint(1) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `listprice` decimal(10,2) DEFAULT NULL,
  `totalprice` decimal(10,2) DEFAULT NULL,
  `buyername` varchar(80) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `checked` tinyint(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cartitem` */

insert  into `cartitem`(`itemid`,`productid`,`descn`,`instock`,`quantity`,`listprice`,`totalprice`,`buyername`,`attr1`,`checked`,`image`,`name`) values 
('EST-13','RP-LI-02','Friendly green friend',1,11,18.50,203.50,'j2ee','Green Adult',1,'../../images/lizard1.gif','Iguana'),
('EST-18','AV-CB-01','Great companion for 75 years',1,2,193.50,387.00,'j2ee','Adult Male',1,'./images/bird2.gif','Amazon Parrot');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `catid` varchar(10) NOT NULL,
  `name` varchar(80) DEFAULT NULL,
  `descn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`catid`,`name`,`descn`) values 
('BIRDS','Birds','<image src=\"../images/birds_icon.gif\"><font size=\"5\" color=\"blue\"> Birds</font>'),
('CATS','Cats','<image src=\"../images/cats_icon.gif\"><font size=\"5\" color=\"blue\"> Cats</font>'),
('DOGS','Dogs','<image src=\"../images/dogs_icon.gif\"><font size=\"5\" color=\"blue\"> Dogs</font>'),
('FISH','Fish','<image src=\"../images/fish_icon.gif\"><font size=\"5\" color=\"blue\"> Fish</font>'),
('REPTILES','Reptiles','<image src=\"../images/reptiles_icon.gif\"><font size=\"5\" color=\"blue\"> Reptiles</font>');

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `itemid` varchar(10) NOT NULL,
  `qty` int(11) NOT NULL,
  PRIMARY KEY (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `inventory` */

insert  into `inventory`(`itemid`,`qty`) values 
('EST-1',54),
('EST-10',10000),
('EST-11',9998),
('EST-12',9998),
('EST-13',9997),
('EST-14',9999),
('EST-15',10000),
('EST-16',10000),
('EST-17',10000),
('EST-18',9999),
('EST-19',9999),
('EST-2',9998),
('EST-20',10000),
('EST-21',10000),
('EST-22',10000),
('EST-23',10000),
('EST-24',10000),
('EST-25',10000),
('EST-26',10000),
('EST-27',10000),
('EST-28',10000),
('EST-3',10000),
('EST-4',9997),
('EST-5',10000),
('EST-6',9999),
('EST-7',10000),
('EST-8',10000),
('EST-9',10000);

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `itemid` varchar(10) NOT NULL,
  `productid` varchar(10) NOT NULL,
  `listprice` decimal(10,2) DEFAULT NULL,
  `unitcost` decimal(10,2) DEFAULT NULL,
  `supplier` int(11) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `attr1` varchar(80) DEFAULT NULL,
  `attr2` varchar(80) DEFAULT NULL,
  `attr3` varchar(80) DEFAULT NULL,
  `attr4` varchar(80) DEFAULT NULL,
  `attr5` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `fk_item_2` (`supplier`),
  KEY `itemProd` (`productid`),
  CONSTRAINT `fk_item_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`),
  CONSTRAINT `fk_item_2` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`suppid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`itemid`,`productid`,`listprice`,`unitcost`,`supplier`,`status`,`attr1`,`attr2`,`attr3`,`attr4`,`attr5`) values 
('EST-10','K9-DL-01',18.50,12.00,2,'P','Spotted Adult Female',NULL,NULL,NULL,NULL),
('EST-11','RP-SN-01',18.50,12.00,2,'P','Venomless',NULL,NULL,NULL,NULL),
('EST-12','RP-SN-01',18.50,12.00,2,'P','Rattleless',NULL,NULL,NULL,NULL),
('EST-13','RP-LI-02',18.50,12.00,1,'P','Green Adult',NULL,NULL,NULL,NULL),
('EST-14','FL-DSH-01',58.50,12.00,1,'P','Tailless',NULL,NULL,NULL,NULL),
('EST-15','FL-DSH-01',23.50,12.00,1,'P','With tail',NULL,NULL,NULL,NULL),
('EST-16','FL-DLH-02',93.50,12.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),
('EST-17','FL-DLH-02',93.50,12.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),
('EST-18','AV-CB-01',193.50,92.00,2,'P','Adult Male',NULL,NULL,NULL,NULL),
('EST-19','AV-SB-02',15.50,2.00,2,'P','Adult Male',NULL,NULL,NULL,NULL),
('EST-2','FI-SW-01',16.50,10.00,1,'P','Small',NULL,NULL,NULL,NULL),
('EST-20','FI-FW-02',5.50,2.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),
('EST-21','FI-FW-02',5.29,1.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),
('EST-22','K9-RT-02',135.50,100.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),
('EST-23','K9-RT-02',145.49,100.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),
('EST-24','K9-RT-02',255.50,92.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),
('EST-25','K9-RT-02',325.29,90.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),
('EST-26','K9-CW-01',125.50,92.00,1,'P','Adult Male',NULL,NULL,NULL,NULL),
('EST-27','K9-CW-01',155.29,90.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),
('EST-28','K9-RT-01',155.29,90.00,1,'P','Adult Female',NULL,NULL,NULL,NULL),
('EST-3','FI-SW-02',18.50,12.00,1,'P','Toothless',NULL,NULL,NULL,NULL),
('EST-4','FI-FW-01',18.50,12.00,1,'P','Spotted',NULL,NULL,NULL,NULL),
('EST-5','FI-FW-01',18.50,12.00,1,'P','Spotless',NULL,NULL,NULL,NULL),
('EST-6','K9-BD-01',18.50,12.00,1,'P','Male Adult',NULL,NULL,NULL,NULL),
('EST-7','K9-BD-01',18.50,12.00,1,'P','Female Puppy',NULL,NULL,NULL,NULL),
('EST-8','K9-PO-02',18.50,12.00,1,'P','Male Puppy',NULL,NULL,NULL,NULL),
('EST-9','K9-DL-01',18.50,12.00,1,'P','Spotless Male Puppy',NULL,NULL,NULL,NULL);

/*Table structure for table `lineitem` */

DROP TABLE IF EXISTS `lineitem`;

CREATE TABLE `lineitem` (
  `orderid` int(11) NOT NULL,
  `linenum` int(11) NOT NULL,
  `itemid` varchar(10) NOT NULL,
  `quantity` int(11) NOT NULL,
  `listprice` decimal(10,2) NOT NULL,
  `descn` varchar(255) DEFAULT NULL,
  `totalprice` decimal(10,2) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `attr1` varchar(80) DEFAULT NULL,
  `name` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`linenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `lineitem` */

insert  into `lineitem`(`orderid`,`linenum`,`itemid`,`quantity`,`listprice`,`descn`,`totalprice`,`image`,`attr1`,`name`) values 
(1001,1001,'EST-11',1,18.50,'Doubles as a watch dog',18.50,'../../images/lizard1.gif','Venomless','Rattlesnake'),
(1002,1002,'EST-19',2,15.50,'Great stress reliever',31.00,'../../images/bird1.gif','Adult Male','Finch'),
(1002,1003,'EST-14',1,58.50,'Great for reducing mouse populations',58.50,'../../images/cat1.gif','Tailless','Manx'),
(1003,1004,'EST-4',1,18.50,'Fresh Water fish from Japan',18.50,'../../images/fish3.gif','Spotted','Koi');

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `info` varchar(80) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`index`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;

/*Data for the table `log` */

insert  into `log`(`index`,`info`,`username`) values 
(1,'account j2ee sign in at 2022-03-13 23:29:47','j2ee'),
(2,'account j2ee j2ee view the AV-CB-01 at 2022-03-13 23:29:54','j2ee'),
(3,'account j2ee j2ee view the AV-CB-01 at 2022-03-13 23:29:57','j2ee'),
(4,'account j2ee j2ee view the AV-CB-01 at 2022-03-13 23:31:38','j2ee'),
(5,'account j2ee j2ee view the AV-CB-01 at 2022-03-13 23:34:36','j2ee'),
(6,'account j2ee j2ee view the AV-CB-01 at 2022-03-13 23:35:19','j2ee'),
(7,'account j2ee j2ee view the AV-CB-01 at 2022-03-13 23:36:21','j2ee'),
(8,'account j2ee j2ee view the AV-SB-02 at 2022-03-13 23:36:25','j2ee'),
(9,'account j2ee j2ee view the K9-BD-01 at 2022-03-13 23:36:28','j2ee'),
(10,'account j2ee j2ee view the RP-LI-02 at 2022-03-13 23:36:30','j2ee'),
(11,'account j2ee j2ee view the FL-DLH-02 at 2022-03-13 23:36:33','j2ee'),
(12,'account j2ee j2ee view the FI-FW-01 at 2022-03-13 23:36:37','j2ee'),
(13,'account j2ee j2ee view the FI-FW-02 at 2022-03-13 23:36:40','j2ee'),
(14,'account j2ee j2ee view the FI-SW-01 at 2022-03-13 23:36:41','j2ee'),
(15,'account j2ee j2ee view the FI-SW-02 at 2022-03-13 23:36:43','j2ee'),
(16,'account j2ee j2ee view the K9-BD-01 at 2022-03-13 23:36:45','j2ee'),
(17,'account j2ee j2ee view the K9-PO-02 at 2022-03-13 23:36:47','j2ee'),
(18,'account j2ee viewed the item EST-8 at 2022-03-13 23:36:48','j2ee'),
(19,'account j2ee sign in at 2022-03-14 09:54:27','j2ee'),
(20,'account j2ee j2ee view the AV-CB-01 at 2022-03-14 09:54:31','j2ee'),
(21,'account j2ee viewed the item EST-18 at 2022-03-14 09:54:33','j2ee'),
(22,'account j2ee viewed the item EST-18 at 2022-03-14 09:56:54','j2ee'),
(23,'account j2ee viewed the item EST-18 at 2022-03-14 09:57:03','j2ee'),
(24,'account j2ee viewed the item EST-18 at 2022-03-14 09:57:49','j2ee'),
(25,'account j2ee viewed the item EST-18 at 2022-03-14 09:59:24','j2ee'),
(26,'account j2ee viewed the item EST-18 at 2022-03-14 09:59:27','j2ee'),
(27,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 09:59:35','j2ee'),
(28,'account j2ee viewed the item EST-4 at 2022-03-14 09:59:36','j2ee'),
(29,'account j2ee sign in at 2022-03-14 10:14:54','j2ee'),
(30,'account j2ee viewed the item EST-4 at 2022-03-14 10:16:50','j2ee'),
(31,'account j2ee viewed the item EST-4 at 2022-03-14 10:17:08','j2ee'),
(32,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 10:24:17','j2ee'),
(33,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 10:24:23','j2ee'),
(34,'account j2ee viewed the item EST-4 at 2022-03-14 10:24:27','j2ee'),
(35,'account j2ee sign in at 2022-03-14 11:20:53','j2ee'),
(36,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 11:20:55','j2ee'),
(37,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 11:20:56','j2ee'),
(38,'account j2ee viewed the item EST-4 at 2022-03-14 11:21:00','j2ee'),
(39,'account j2ee sign in at 2022-03-14 11:22:47','j2ee'),
(40,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 11:22:50','j2ee'),
(41,'account j2ee viewed the item EST-4 at 2022-03-14 11:22:51','j2ee'),
(42,'account j2ee sign in at 2022-03-14 12:06:17','j2ee'),
(43,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 12:06:20','j2ee'),
(44,'account j2ee viewed the item EST-4 at 2022-03-14 12:06:22','j2ee'),
(45,'account j2ee viewed the item EST-4 at 2022-03-14 12:10:07','j2ee'),
(46,'account j2ee sign in at 2022-03-14 12:13:57','j2ee'),
(47,'account j2ee sign in at 2022-03-14 12:20:06','j2ee'),
(48,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 12:20:08','j2ee'),
(49,'account j2ee viewed the item EST-4 at 2022-03-14 12:20:09','j2ee'),
(50,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 12:28:33','j2ee'),
(51,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 12:28:34','j2ee'),
(52,'account j2ee j2ee view the K9-BD-01 at 2022-03-14 12:28:58','j2ee'),
(53,'account j2ee j2ee view the K9-BD-01 at 2022-03-14 12:28:59','j2ee'),
(54,'account j2ee j2ee view the K9-BD-01 at 2022-03-14 12:29:00','j2ee'),
(55,'account j2ee j2ee view the K9-BD-01 at 2022-03-14 12:29:01','j2ee'),
(56,'account j2ee j2ee view the K9-BD-01 at 2022-03-14 12:30:03','j2ee'),
(57,'account j2ee j2ee view the K9-BD-01 at 2022-03-14 12:30:03','j2ee'),
(58,'account j2ee j2ee view the K9-BD-01 at 2022-03-14 12:30:37','j2ee'),
(59,'account j2ee sign in at 2022-03-14 14:04:12','j2ee'),
(60,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 14:04:19','j2ee'),
(61,'account j2ee j2ee view the RP-LI-02 at 2022-03-14 14:04:22','j2ee'),
(62,'account j2ee add item EST-13 at 2022-03-14 14:07:35','j2ee'),
(63,'account j2ee j2ee view the K9-BD-01 at 2022-03-14 14:07:39','j2ee'),
(64,'account j2ee add item EST-6 at 2022-03-14 14:07:40','j2ee'),
(65,'account j2ee add item EST-6 at 2022-03-14 14:07:42','j2ee'),
(66,'account j2ee j2ee view the AV-SB-02 at 2022-03-14 14:09:29','j2ee'),
(67,'account j2ee add item EST-19 at 2022-03-14 14:09:30','j2ee'),
(68,'account j2ee j2ee view the RP-LI-02 at 2022-03-14 14:10:00','j2ee'),
(69,'account j2ee j2ee view the FI-SW-02 at 2022-03-14 14:10:04','j2ee'),
(70,'account j2ee add item EST-3 at 2022-03-14 14:10:05','j2ee'),
(71,'account j2ee j2ee view the FL-DSH-01 at 2022-03-14 14:14:31','j2ee'),
(72,'account j2ee add item EST-15 at 2022-03-14 14:14:33','j2ee'),
(73,'account j2ee j2ee view the K9-RT-02 at 2022-03-14 14:14:44','j2ee'),
(74,'account j2ee add item EST-22 at 2022-03-14 14:14:45','j2ee'),
(75,'account j2ee remove item EST-4 at 2022-03-14 14:16:21','j2ee'),
(76,'account j2ee remove item EST-13 at 2022-03-14 14:16:26','j2ee'),
(77,'account j2ee remove item EST-22 at 2022-03-14 14:16:28','j2ee'),
(78,'account j2ee remove item EST-15 at 2022-03-14 14:16:29','j2ee'),
(79,'account j2ee remove item EST-3 at 2022-03-14 14:16:30','j2ee'),
(80,'account j2ee remove item EST-19 at 2022-03-14 14:16:31','j2ee'),
(81,'account j2ee remove item EST-6 at 2022-03-14 14:16:31','j2ee'),
(82,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 14:16:37','j2ee'),
(83,'account j2ee add item EST-4 at 2022-03-14 14:16:38','j2ee'),
(84,'account j2ee remove item EST-4 at 2022-03-14 14:16:39','j2ee'),
(85,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 14:16:43','j2ee'),
(86,'account j2ee add item EST-4 at 2022-03-14 14:16:44','j2ee'),
(87,'account j2ee remove item EST-4 at 2022-03-14 14:16:45','j2ee'),
(88,'account j2ee j2ee view the FI-FW-01 at 2022-03-14 14:16:47','j2ee'),
(89,'account j2ee add item EST-4 at 2022-03-14 14:16:48','j2ee'),
(90,'account j2ee j2ee view the K9-DL-01 at 2022-03-14 14:16:54','j2ee'),
(91,'account j2ee add item EST-10 at 2022-03-14 14:16:55','j2ee'),
(92,'account j2ee j2ee view the FL-DLH-02 at 2022-03-14 14:16:57','j2ee'),
(93,'account j2ee viewed the item EST-16 at 2022-03-14 14:16:58','j2ee'),
(94,'account j2ee add item EST-16 at 2022-03-14 14:16:59','j2ee'),
(95,'account j2ee remove item EST-4 at 2022-03-14 14:17:00','j2ee'),
(96,'account j2ee remove item EST-10 at 2022-03-14 14:17:00','j2ee'),
(97,'account j2ee j2ee view the K9-DL-01 at 2022-03-14 14:17:05','j2ee'),
(98,'account j2ee add item EST-9 at 2022-03-14 14:17:06','j2ee'),
(99,'account j2ee sign in at 2022-03-15 12:39:52','j2ee'),
(100,'account j2ee j2ee view the FI-FW-01 at 2022-03-15 12:40:06','j2ee'),
(101,'account j2ee viewed the item EST-4 at 2022-03-15 12:40:09','j2ee'),
(102,'account j2ee sign out at 2022-03-15 12:40:20','j2ee'),
(103,'account j2ee sign in at 2022-03-15 12:42:51','j2ee'),
(104,'account j2ee sign out at 2022-03-15 12:44:54','j2ee'),
(105,'account j2ee sign in at 2022-03-15 12:45:14','j2ee'),
(106,'account j2ee j2ee view the FI-FW-01 at 2022-03-15 12:46:23','j2ee'),
(107,'account j2ee add item EST-4 at 2022-03-15 12:46:24','j2ee'),
(108,'account 22 sign out at 2022-03-16 16:10:41','22'),
(109,'account 23 sign out at 2022-03-16 16:10:54','23'),
(110,'account j2ee sign in at 2022-03-17 12:47:07','j2ee'),
(111,'account j2ee sign in at 2022-03-17 16:23:31','j2ee'),
(112,'account j2ee j2ee view the 222 at 2022-03-17 16:23:35','j2ee'),
(113,'account j2ee j2ee view the AV-SB-02 at 2022-03-17 16:23:39','j2ee'),
(114,'account j2ee viewed the item EST-19 at 2022-03-17 16:23:40','j2ee'),
(115,'account j2ee add item EST-19 at 2022-03-17 16:23:42','j2ee'),
(116,'account j2ee j2ee view the RP-SN-01 at 2022-03-17 16:23:44','j2ee'),
(117,'account j2ee viewed the item EST-12 at 2022-03-17 16:23:45','j2ee'),
(118,'account j2ee add item EST-12 at 2022-03-17 16:23:46','j2ee'),
(119,'account j2ee j2ee view the K9-BD-01 at 2022-03-17 16:23:49','j2ee'),
(120,'account j2ee add item EST-6 at 2022-03-17 16:23:50','j2ee'),
(121,'account j2ee update his info at 2022-03-17 16:31:17','j2ee'),
(122,'account j2ee update his info at 2022-03-17 16:31:28','j2ee'),
(123,'account j2ee j2ee view the 111 at 2022-03-17 16:38:17','j2ee'),
(124,'account j2ee viewed the item EST-1 at 2022-03-17 16:38:21','j2ee'),
(125,'account j2ee j2ee view the 111 at 2022-03-17 16:38:39','j2ee'),
(126,'account j2ee j2ee view the FI-SW-01 at 2022-03-17 16:49:17','j2ee'),
(127,'account j2ee add item EST-2 at 2022-03-17 16:49:18','j2ee'),
(128,'account j2ee j2ee view the RP-SN-01 at 2022-03-17 16:49:21','j2ee'),
(129,'account j2ee add item EST-11 at 2022-03-17 16:49:21','j2ee'),
(130,'account j2ee j2ee view the FI-FW-01 at 2022-03-17 16:56:34','j2ee'),
(131,'account j2ee viewed the item EST-4 at 2022-03-17 16:56:35','j2ee'),
(132,'account j2ee sign out at 2022-03-17 17:02:40','j2ee'),
(133,'account j2ee sign in at 2022-03-17 17:03:11','j2ee'),
(134,'account j2ee update his info at 2022-03-17 17:03:17','j2ee'),
(135,'account j2ee sign in at 2022-03-17 17:58:38','j2ee'),
(136,'account j2ee sign in at 2022-03-17 19:11:18','j2ee'),
(137,'account j2ee update his info at 2022-03-17 19:11:48','j2ee'),
(138,'account j2ee update his info at 2022-03-17 19:11:52','j2ee'),
(139,'account j2ee j2ee view the FI-FW-01 at 2022-03-17 19:12:34','j2ee'),
(140,'account j2ee add item EST-4 at 2022-03-17 19:12:35','j2ee'),
(141,'account 123 123 view the RP-LI-02 at 2022-03-17 19:35:49','123'),
(142,'account 123 add item EST-13 at 2022-03-17 19:35:50','123'),
(143,'account 123 123 view the AV-CB-01 at 2022-03-17 19:35:56','123'),
(144,'account 123 add item EST-18 at 2022-03-17 19:35:57','123'),
(145,'account 123 123 view the RP-LI-02 at 2022-03-17 19:36:45','123'),
(146,'account 123 add item EST-13 at 2022-03-17 19:36:46','123'),
(147,'account j2ee sign in at 2022-03-17 23:32:14','j2ee'),
(148,'account j2ee sign out at 2022-03-17 23:32:43','j2ee'),
(149,'account j2ee sign in at 2022-03-17 23:33:53','j2ee'),
(150,'account j2ee sign out at 2022-03-17 23:34:37','j2ee'),
(151,'account j2ee sign in at 2022-03-17 23:34:56','j2ee'),
(152,'account j2ee sign out at 2022-03-17 23:35:17','j2ee'),
(153,'account j2ee sign in at 2022-03-17 23:39:27','j2ee'),
(154,'account j2ee sign out at 2022-03-17 23:39:31','j2ee'),
(155,'account j2ee sign in at 2022-03-17 23:39:41','j2ee'),
(156,'account j2ee sign out at 2022-03-17 23:39:44','j2ee'),
(157,'account j2ee sign in at 2022-03-17 23:43:09','j2ee'),
(158,'account j2ee sign out at 2022-03-17 23:43:16','j2ee'),
(159,'account j2ee sign in at 2022-03-18 08:08:03','j2ee'),
(160,'account j2ee sign out at 2022-03-18 08:08:07','j2ee'),
(161,'account 123 sign in at 2022-03-18 08:10:52','123'),
(162,'account 123 sign out at 2022-03-18 08:11:08','123'),
(163,'account 123 sign in at 2022-03-18 08:15:11','123'),
(164,'account 123 123 view the FI-FW-01 at 2022-03-18 08:15:18','123'),
(165,'account 123 add item EST-4 at 2022-03-18 08:15:19','123'),
(166,'account 123 123 view the RP-LI-02 at 2022-03-18 08:15:21','123'),
(167,'account 123 add item EST-13 at 2022-03-18 08:15:22','123'),
(168,'account 123 123 view the TEST at 2022-03-18 08:15:25','123'),
(169,'account 123 123 view the 44 at 2022-03-18 08:15:28','123'),
(170,'account 123 123 view the 111 at 2022-03-18 08:15:31','123'),
(171,'account 123 add item EST-1 at 2022-03-18 08:15:33','123'),
(172,'account 123 sign in at 2022-03-18 08:39:06','123'),
(173,'account 123 123 view the FI-FW-01 at 2022-03-18 08:39:16','123'),
(174,'account 123 add item EST-4 at 2022-03-18 08:39:17','123'),
(175,'account 123 123 view the FL-DSH-01 at 2022-03-18 08:39:19','123'),
(176,'account 123 add item EST-14 at 2022-03-18 08:39:20','123'),
(177,'account 123 123 view the FI-SW-01 at 2022-03-18 08:39:23','123'),
(178,'account 123 add item EST-2 at 2022-03-18 08:39:24','123'),
(179,'account 123 123 view the AV-CB-01 at 2022-03-18 08:41:39','123'),
(180,'account 123 viewed the item EST-18 at 2022-03-18 08:41:40','123'),
(181,'account 123 sign out at 2022-03-18 08:42:12','123'),
(182,'account j2ee sign in at 2022-04-05 14:32:50','j2ee'),
(183,'account j2ee j2ee view the FL-DSH-01 at 2022-04-05 14:32:54','j2ee'),
(184,'account j2ee add item EST-14 at 2022-04-05 14:32:55','j2ee'),
(185,'account j2ee j2ee view the TEST at 2022-04-05 14:32:56','j2ee'),
(186,'account j2ee j2ee view the RP-LI-02 at 2022-04-05 14:32:59','j2ee'),
(187,'account j2ee add item EST-13 at 2022-04-05 14:33:00','j2ee');

/*Table structure for table `orderinfo` */

DROP TABLE IF EXISTS `orderinfo`;

CREATE TABLE `orderinfo` (
  `orderid` int(11) NOT NULL,
  `linenum` int(11) NOT NULL,
  PRIMARY KEY (`orderid`,`linenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderinfo` */

insert  into `orderinfo`(`orderid`,`linenum`) values 
(1001,1001),
(1002,1002),
(1002,1003),
(1003,1004);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL,
  `username` varchar(80) DEFAULT NULL,
  `orderdate` timestamp NULL DEFAULT NULL,
  `shipaddress1` varchar(80) DEFAULT NULL,
  `shipaddress2` varchar(80) DEFAULT NULL,
  `shipcity` varchar(80) DEFAULT NULL,
  `shipstate` varchar(80) DEFAULT NULL,
  `shipzip` varchar(20) DEFAULT NULL,
  `shipcountry` varchar(20) DEFAULT NULL,
  `billaddress1` varchar(80) DEFAULT NULL,
  `billaddress2` varchar(80) DEFAULT NULL,
  `billcity` varchar(80) DEFAULT NULL,
  `billstate` varchar(80) DEFAULT NULL,
  `billzip` varchar(20) DEFAULT NULL,
  `billcountry` varchar(20) DEFAULT NULL,
  `courier` varchar(80) DEFAULT NULL,
  `totalprice` decimal(10,2) DEFAULT NULL,
  `billtofirstname` varchar(80) DEFAULT NULL,
  `billtolastname` varchar(80) DEFAULT NULL,
  `shiptofirstname` varchar(80) DEFAULT NULL,
  `shiptolastname` varchar(80) DEFAULT NULL,
  `creditcard` varchar(80) DEFAULT NULL,
  `expirydate` varchar(32) DEFAULT NULL,
  `cardtype` varchar(80) DEFAULT NULL,
  `locale` varchar(80) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`orderid`,`username`,`orderdate`,`shipaddress1`,`shipaddress2`,`shipcity`,`shipstate`,`shipzip`,`shipcountry`,`billaddress1`,`billaddress2`,`billcity`,`billstate`,`billzip`,`billcountry`,`courier`,`totalprice`,`billtofirstname`,`billtolastname`,`shiptofirstname`,`shiptolastname`,`creditcard`,`expirydate`,`cardtype`,`locale`,`status`,`timestamp`) values 
(1001,'j2ee','2022-04-24 11:05:32','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','UPS',18.50,'AAA','XYX','AAA','XYX','123465','2022/05/01','Visa','CSU','待发货','2022-04-24 11:05:32'),
(1002,'j2ee','2022-04-24 11:06:53','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','UPS',89.50,'AAA','XYX','AAA','XYX','123456','2022/05/01','Visa','CSU','待发货','2022-04-24 11:06:53'),
(1003,'j2ee','2022-04-24 19:49:11','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','901 San Antonio Road','MS UCUP02-206','Palo Alto','CA','94303','USA','UPS',18.50,'AAA','XYX','AAA','XYX','123456','2022/05/01','Visa','CSU','待发货','2022-04-24 19:49:11');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `productid` varchar(10) NOT NULL,
  `category` varchar(10) NOT NULL,
  `name` varchar(80) DEFAULT NULL,
  `descn` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`productid`),
  KEY `productCat` (`category`),
  KEY `productName` (`name`),
  CONSTRAINT `fk_product_1` FOREIGN KEY (`category`) REFERENCES `category` (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`productid`,`category`,`name`,`descn`,`image`) values 
('AV-CB-01','BIRDS','Amazon Parrot','Great companion for 75 years','./images/bird2.gif'),
('AV-SB-02','BIRDS','Finch','Great stress reliever','./images/bird1.gif'),
('FI-FW-01','FISH','Koi','Fresh Water fish from Japan','./images/fish3.gif'),
('FI-FW-02','FISH','Goldfish','Fresh Water fish from China','./images/fish2.gif'),
('FI-SW-01','FISH','Angelfish','Salt Water fish from Australia','./images/fish1.gif'),
('FI-SW-02','FISH','Tiger Shark','Salt Water fish from Australia','./images/fish4.gif'),
('FL-DLH-02','CATS','Persian','Friendly house cat, doubles as a princess','./images/cat1.gif'),
('FL-DSH-01','CATS','Manx','Great for reducing mouse populations','./images/cat1.gif'),
('K9-BD-01','DOGS','Bulldog','Friendly dog from England','./images/dog2.gif'),
('K9-CW-01','DOGS','Chihuahua','Great companion dog','./images/dog4.gif'),
('K9-DL-01','DOGS','Dalmation','Great dog for a Fire Station','./images/dog5.gif'),
('K9-PO-02','DOGS','Poodle','Cute dog from France','./images/dog6.gif'),
('K9-RT-01','DOGS','Golden Retriever','Great family dog','./images/dog1.gif'),
('K9-RT-02','DOGS','Labrador Retriever','Great hunting dog','./images/dog5.gif'),
('RP-LI-02','REPTILES','Iguana','Friendly green friend','./images/lizard1.gif'),
('RP-SN-01','REPTILES','Rattlesnake','Doubles as a watch dog','./images/lizard1.gif');

/*Table structure for table `profile` */

DROP TABLE IF EXISTS `profile`;

CREATE TABLE `profile` (
  `userid` varchar(80) NOT NULL,
  `langpref` varchar(80) DEFAULT NULL,
  `favcategory` varchar(30) DEFAULT NULL,
  `mylistopt` tinyint(1) DEFAULT NULL,
  `banneropt` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `profile` */

insert  into `profile`(`userid`,`langpref`,`favcategory`,`mylistopt`,`banneropt`) values 
('1',NULL,NULL,0,0),
('10',NULL,NULL,0,0),
('11',NULL,NULL,0,0),
('12',NULL,NULL,0,0),
('123',NULL,NULL,NULL,NULL),
('1234',NULL,NULL,0,0),
('12345',NULL,NULL,0,0),
('123456',NULL,NULL,0,0),
('22',NULL,NULL,NULL,NULL),
('23',NULL,NULL,NULL,NULL),
('33',NULL,NULL,0,0),
('5',NULL,NULL,0,0),
('6',NULL,NULL,0,0),
('7',NULL,NULL,0,0),
('8',NULL,NULL,0,0),
('9',NULL,NULL,0,0),
('AA',NULL,NULL,0,0),
('aaaaa',NULL,NULL,0,0),
('j2ee','Chinese','CATS',1,0);

/*Table structure for table `sequence` */

DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
  `name` varchar(30) NOT NULL,
  `nextid` int(11) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sequence` */

insert  into `sequence`(`name`,`nextid`) values 
('linenum',1004),
('ordernum',1003);

/*Table structure for table `signon` */

DROP TABLE IF EXISTS `signon`;

CREATE TABLE `signon` (
  `username` varchar(25) NOT NULL,
  `password` varchar(80) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `signon` */

insert  into `signon`(`username`,`password`) values 
('1','e10adc3949ba59abbe56e057f20f883e'),
('10','e10adc3949ba59abbe56e057f20f883e'),
('11','e10adc3949ba59abbe56e057f20f883e'),
('12','e10adc3949ba59abbe56e057f20f883e'),
('123','e10adc3949ba59abbe56e057f20f883e'),
('1234','e10adc3949ba59abbe56e057f20f883e'),
('12345','e10adc3949ba59abbe56e057f20f883e'),
('123456','e10adc3949ba59abbe56e057f20f883e'),
('22','e10adc3949ba59abbe56e057f20f883e'),
('23','e10adc3949ba59abbe56e057f20f883e'),
('33','e10adc3949ba59abbe56e057f20f883e'),
('5','202cb962ac59075b964b07152d234b70'),
('6','e10adc3949ba59abbe56e057f20f883e'),
('7','e10adc3949ba59abbe56e057f20f883e'),
('8','e10adc3949ba59abbe56e057f20f883e'),
('9','e10adc3949ba59abbe56e057f20f883e'),
('AA','202cb962ac59075b964b07152d234b70'),
('aaaaa','e10adc3949ba59abbe56e057f20f883e'),
('j2ee','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `suppid` int(11) NOT NULL,
  `name` varchar(80) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `addr1` varchar(80) DEFAULT NULL,
  `addr2` varchar(80) DEFAULT NULL,
  `city` varchar(80) DEFAULT NULL,
  `state` varchar(80) DEFAULT NULL,
  `zip` varchar(5) DEFAULT NULL,
  `phone` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`suppid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `supplier` */

insert  into `supplier`(`suppid`,`name`,`status`,`addr1`,`addr2`,`city`,`state`,`zip`,`phone`) values 
(1,'XYZ Pets','AC','600 Avon Way','','Los Angeles','CA','94024','212-947-0797'),
(2,'ABC Pets','AC','700 Abalone Way','','San Francisco ','CA','94024','415-947-0797');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
