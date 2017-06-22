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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `co_corre` */

insert  into `co_corre`(`corre_id`,`corre_english`,`corre_chinese`,`cc_id`) values (1,'{Commerce Department (of the US government)<br/>< 请填写欲征询的政府机构名称，如商务部Commerce Department，外经贸部MOFTEC(Ministry of Foreign Trade and Economic Cooperation), 俄罗斯大使馆Russian Embassy> }<br/>[Thank you for your long-standing support! |Thank you for your help from all aspects in these years.|Our co-operation has been very successful in a long period of time.]We [sincerely|genuinely] hope to [expand our business|broaden our business|establish our business] in {all kinds of cotton products<请填写欲开展贸易的相关产品>}, but there are no ideal partners in {China<请填写欲开展贸易的国家或地区>}. [It will be appreciated | We would be grateful]that you provide us some information about {those companies who are professional and reliable in this line<请填写希望得到哪些相关信息>}, so that we can [establish business relationship| establish cooperative relationship|build trade relation ship| enter into business relations] with them.<br/>[We are looking forward your to early reply!|We shall be grateful if you will reply at an early date.|Your early reply is appreciated.| We are anticipating your answer.] <br/>Yours sincerely{American Cotton Company<请填写己方公司名称>','××商务部: <br/>感谢你们长期以来对我们的支持.目前我们殷切希望能够扩大各种棉制品的贸易,但在中国地区尚没有理想的合作伙伴. 因此,请你们为我们介绍几家专业可靠,有实力的进口商,建立业务往来关系. <br/>此致谢意,盼复. <br/>美国棉织品总公司',5);

/*Table structure for table `co_correcategory` */

DROP TABLE IF EXISTS `co_correcategory`;

CREATE TABLE `co_correcategory` (
  `cc_id` int(11) NOT NULL AUTO_INCREMENT,
  `cc_content` varchar(100) DEFAULT NULL,
  `cc_fatherid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `co_correcategory` */

insert  into `co_correcategory`(`cc_id`,`cc_content`,`cc_fatherid`) values (1,'向某些机构请求介绍贸易关系',NULL),(2,'政府机构',1),(3,'非政府机构',1),(4,'其他',1),(5,'国外进口商向本国商务部咨询国外可合作伙伴',2),(6,'中国进口商致电波兰大使馆经济贸易处询问贸易信息',2),(7,'向贸促会征询贸易信息',3),(8,'向贸易展览会索要与会名单及相关参阅',3),(9,'向总部咨询适合的分支机构',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
