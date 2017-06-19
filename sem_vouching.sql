/*
SQLyog Enterprise Trial - MySQL GUI v7.11 
MySQL - 5.7.17-log : Database - sem_vouching
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`sem_vouching` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sem_vouching`;

/*Table structure for table `co_corre` */

DROP TABLE IF EXISTS `co_corre`;

CREATE TABLE `co_corre` (
  `corre_id` int(11) NOT NULL AUTO_INCREMENT,
  `corre_english` text,
  `corre_chinese` text,
  `cc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`corre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `co_corre` */

/*Table structure for table `co_correcategory` */

DROP TABLE IF EXISTS `co_correcategory`;

CREATE TABLE `co_correcategory` (
  `cc_id` int(11) NOT NULL AUTO_INCREMENT,
  `cc_content` varchar(100) DEFAULT NULL,
  `cc_fatherid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `co_correcategory` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
