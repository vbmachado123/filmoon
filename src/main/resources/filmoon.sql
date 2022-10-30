-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.17-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for filmoon
CREATE DATABASE IF NOT EXISTS `filmoon` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `filmoon`;

-- Dumping structure for table filmoon.client
CREATE TABLE IF NOT EXISTS `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table filmoon.client: ~2 rows (approximately)
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
REPLACE INTO `client` (`id`, `email`, `name`, `phone`) VALUES
	(1, 'contato@tevitto.com', 'Victor Balbino', '(11) 99897-1053'),
	(2, 'code@tevitto.com', 'Victor Machado', '(11) 99897-1053');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;

-- Dumping structure for table filmoon.item
CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `value_rent` double NOT NULL,
  `value_sale` double NOT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa5r9sug9g4f4kif98va9ro3l2` (`status_id`),
  KEY `FK95i2undeuwt7lb0uy1md9wkp9` (`type_id`),
  CONSTRAINT `FK95i2undeuwt7lb0uy1md9wkp9` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
  CONSTRAINT `FKa5r9sug9g4f4kif98va9ro3l2` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table filmoon.item: ~2 rows (approximately)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
REPLACE INTO `item` (`id`, `description`, `quantity`, `title`, `value_rent`, `value_sale`, `status_id`, `type_id`) VALUES
	(1, 'Teste de Descrição', 4, 'Teste de Titulo', 44.23, 123.33, 3, 1),
	(3, 'Um Teste de Item', 0, 'Um Brilho Eterno de uma mente sem Lembranças', 44.5, 50.4, 4, 1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

-- Dumping structure for table filmoon.rent
CREATE TABLE IF NOT EXISTS `rent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_hour` datetime DEFAULT NULL,
  `days` int(11) NOT NULL,
  `returned` bit(1) NOT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtl9uohe6mseu8h2e3r5jptf4l` (`client_id`),
  KEY `FKa0jd65qhtcis8culcjrr1o3rk` (`item_id`),
  CONSTRAINT `FKa0jd65qhtcis8culcjrr1o3rk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FKtl9uohe6mseu8h2e3r5jptf4l` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table filmoon.rent: ~2 rows (approximately)
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
REPLACE INTO `rent` (`id`, `date_hour`, `days`, `returned`, `client_id`, `item_id`) VALUES
	(1, '2022-10-29 19:23:10', 4, b'0', 2, 1),
	(2, '2022-10-29 19:23:10', 4, b'0', 2, 1);
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;

-- Dumping structure for table filmoon.sale
CREATE TABLE IF NOT EXISTS `sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_hour` datetime DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKon0o9ba5ajsnwivekhl1tfjiy` (`client_id`),
  KEY `FK4eb2ge42cm65dpvy5yj2foxvv` (`item_id`),
  CONSTRAINT `FK4eb2ge42cm65dpvy5yj2foxvv` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FKon0o9ba5ajsnwivekhl1tfjiy` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table filmoon.sale: ~2 rows (approximately)
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
REPLACE INTO `sale` (`id`, `date_hour`, `client_id`, `item_id`) VALUES
	(1, '2022-10-29 18:57:40', 1, 3),
	(2, '2022-10-29 19:00:26', 2, 3),
	(3, '2022-10-29 19:01:43', 2, 3);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;

-- Dumping structure for table filmoon.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table filmoon.status: ~4 rows (approximately)
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
REPLACE INTO `status` (`id`, `status`) VALUES
	(1, 'NOT_AVAILABLE'),
	(2, 'SALE'),
	(3, 'RENT'),
	(4, 'ALL');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;

-- Dumping structure for table filmoon.type
CREATE TABLE IF NOT EXISTS `type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table filmoon.type: ~3 rows (approximately)
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
REPLACE INTO `type` (`id`, `category`) VALUES
	(1, 'MOVIE'),
	(2, 'BOOK'),
	(3, 'TV_SHOW');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
