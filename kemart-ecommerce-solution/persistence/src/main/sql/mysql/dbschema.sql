delimiter $$

CREATE DATABASE `kemart` /*!40100 DEFAULT CHARACTER SET utf8 */$$

delimiter $$

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zipCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `billingaddress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zipCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cellphone` varchar(255) DEFAULT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `fixedlinePhone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `billingAddressId` bigint(20) DEFAULT NULL,
  `deliveryAddressId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAF12F3CB4241E7C1` (`billingAddressId`),
  KEY `FKAF12F3CBC1C8623B` (`deliveryAddressId`),
  CONSTRAINT `FKAF12F3CB4241E7C1` FOREIGN KEY (`billingAddressId`) REFERENCES `billingaddress` (`id`),
  CONSTRAINT `FKAF12F3CBC1C8623B` FOREIGN KEY (`deliveryAddressId`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `discount` decimal(19,2) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `productImageURL` varchar(255) DEFAULT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `value` decimal(19,2) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKED8DCCEF1C99816B` (`categoryId`),
  CONSTRAINT `FKED8DCCEF1C99816B` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8$$


