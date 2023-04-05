/*
SQLyog Enterprise v13.1.1 (64 bit)
MySQL - 10.4.27-MariaDB : Database - projektni_rad
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`projektni_rad` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `projektni_rad`;

/*Table structure for table `game` */

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `game_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `home_team` varchar(50) NOT NULL,
  `home_goals` int(11) NOT NULL,
  `away_team` varchar(30) NOT NULL,
  `away_goals` int(11) NOT NULL,
  PRIMARY KEY (`game_id`),
  KEY `game_ibfk_1` (`home_team`),
  KEY `game_ibfk_2` (`away_team`),
  CONSTRAINT `game_ibfk_1` FOREIGN KEY (`home_team`) REFERENCES `team` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `game_ibfk_2` FOREIGN KEY (`away_team`) REFERENCES `team` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `game` */

insert  into `game`(`game_id`,`home_team`,`home_goals`,`away_team`,`away_goals`) values 
(3,'Real Madrid',5,'Barcelona',2),
(8,'Paris Saint Germain',3,'Barcelona',2),
(9,'Chelsea',3,'Real Madrid',3),
(10,'Partizan',0,'Crvena Zvezda',0),
(11,'Real Madrid',3,'Atletico Madrid',2),
(12,'Fulham',0,'Manchester City',3),
(13,'Paris Saint Germain',1,'Manchester United',1);

/*Table structure for table `game_goalscorer` */

DROP TABLE IF EXISTS `game_goalscorer`;

CREATE TABLE `game_goalscorer` (
  `game_id` bigint(20) NOT NULL,
  `player_id` bigint(20) NOT NULL,
  `goals` int(11) NOT NULL,
  PRIMARY KEY (`game_id`,`player_id`),
  KEY `game_goalscorer_ibfk_2` (`player_id`),
  CONSTRAINT `game_goalscorer_ibfk_2` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `game_goalscorer_ibfk_3` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `game_goalscorer` */

insert  into `game_goalscorer`(`game_id`,`player_id`,`goals`) values 
(3,3,2),
(3,5,3),
(3,16,2),
(8,4,2),
(8,18,3),
(9,5,2),
(9,16,1),
(9,26,1),
(9,27,1),
(9,28,1),
(11,16,3),
(11,23,2),
(13,19,1),
(13,32,1);

/*Table structure for table `league` */

DROP TABLE IF EXISTS `league`;

CREATE TABLE `league` (
  `league_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `nation` varchar(50) DEFAULT NULL,
  `division` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`league_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `league` */

insert  into `league`(`league_id`,`name`,`nation`,`division`) values 
(1,'Barclays Premier League','England','1'),
(2,'La Liga','Spain','1'),
(3,'Linglong Super liga','Serbia','1'),
(5,'Serie A','Italy','1'),
(6,'EFL Championship','England','2'),
(7,'Ligue 1','France','1'),
(8,'Champions League','International','1'),
(9,'Europe League','International','2');

/*Table structure for table `leagueteams` */

DROP TABLE IF EXISTS `leagueteams`;

CREATE TABLE `leagueteams` (
  `league_id` bigint(20) NOT NULL,
  `team_name` varchar(50) NOT NULL,
  `points` int(11) DEFAULT NULL,
  PRIMARY KEY (`league_id`,`team_name`),
  KEY `leagueteams_ibfk_2` (`team_name`),
  CONSTRAINT `leagueteams_ibfk_2` FOREIGN KEY (`team_name`) REFERENCES `team` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `leagueteams_ibfk_3` FOREIGN KEY (`league_id`) REFERENCES `league` (`league_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `leagueteams` */

insert  into `leagueteams`(`league_id`,`team_name`,`points`) values 
(1,'Chelsea',25),
(1,'Fulham',13),
(1,'Manchester City',34),
(1,'Manchester United',32),
(2,'Atletico Madrid',13),
(2,'Barcelona',10),
(2,'Real Madrid',7),
(3,'Crvena Zvezda',39),
(3,'Partizan',33),
(5,'Juventus',25),
(7,'Paris Saint Germain',36),
(8,'Chelsea',5),
(8,'Manchester City',9),
(8,'Real Madrid',6),
(9,'Barcelona',10),
(9,'Crvena Zvezda',2);

/*Table structure for table `player` */

DROP TABLE IF EXISTS `player`;

CREATE TABLE `player` (
  `player_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `team` varchar(50) DEFAULT NULL,
  `age` int(2) DEFAULT NULL,
  `position` enum('goalkeeper','defender','midfielder','forward') DEFAULT NULL,
  `dtype` varchar(31) NOT NULL,
  PRIMARY KEY (`player_id`),
  KEY `player_ibfk_1` (`team`),
  CONSTRAINT `player_ibfk_1` FOREIGN KEY (`team`) REFERENCES `team` (`name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `player` */

insert  into `player`(`player_id`,`name`,`team`,`age`,`position`,`dtype`) values 
(1,'Aleksandar Mitrovic','Fulham',27,'forward','PlayerEntity'),
(2,'Thibaout Courtois','Real Madrid',30,'goalkeeper','PlayerEntity'),
(3,'Rapinha','Barcelona',26,'midfielder','PlayerEntity'),
(4,'Robert Lewandowski','Barcelona',35,'forward','PlayerEntity'),
(5,'Karim Benzema','Real Madrid',35,'forward','PlayerEntity'),
(12,'Sasa Lukic','Fulham',28,'midfielder','PlayerEntity'),
(16,'Vinicius Junior','Real Madrid',22,'forward','PlayerEntity'),
(17,'Sergio Ramos','Paris Saint Germain',37,'defender','PlayerEntity'),
(18,'Kylian Mbappe','Paris Saint Germain',24,'forward','PlayerEntity'),
(19,'Neymar','Paris Saint Germain',31,'forward','PlayerEntity'),
(20,'Filip Kostic','Juventus',30,'midfielder','PlayerEntity'),
(21,'Danilo','Juventus',31,'defender','PlayerEntity'),
(22,'Dusan Vlahovic','Juventus',23,'forward','PlayerEntity'),
(23,'Antoine Griezmann','Atletico Madrid',32,'forward','PlayerEntity'),
(24,'Stefan Savic','Atletico Madrid',32,'defender','PlayerEntity'),
(25,'Yannick Carrasco','Atletico Madrid',29,'midfielder','PlayerEntity'),
(26,'Mason Mount','Chelsea',24,'midfielder','PlayerEntity'),
(27,'Thiago Silva','Chelsea',38,'defender','PlayerEntity'),
(28,'N\'Golo Kante','Chelsea',32,'midfielder','PlayerEntity'),
(29,'Erling Haaland','Manchester City',22,'forward','PlayerEntity'),
(30,'Kevin De Bruyne','Manchester City',31,'midfielder','PlayerEntity'),
(31,'Aymeric Laporte','Manchester City',28,'defender','PlayerEntity'),
(32,'Marcus Rashford','Manchester United',25,'midfielder','PlayerEntity'),
(33,'Bruno Fernandes','Manchester United',28,'midfielder','PlayerEntity'),
(34,'David de Gea','Manchester United',32,'goalkeeper','PlayerEntity');

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `name` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `UK_g2l9qqsoeuynt4r5ofdt1x2td` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `team` */

insert  into `team`(`name`,`country`,`city`) values 
('Atletico Madrid','Spain','Madrid'),
('Barcelona','Spain','Barcelona'),
('Chelsea','England','London'),
('Crvena Zvezda','Serbia','Belgrade'),
('FCSB','Romania','Bucharest'),
('Fulham','England','London'),
('Juventus','Italy','Torino'),
('Manchester City','England','Manchester'),
('Manchester United','England','Manchester'),
('Paris Saint Germain','France','Paris'),
('Partizan ','Serbia','Belgrade'),
('Real Madrid','Spain','Madrid');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
